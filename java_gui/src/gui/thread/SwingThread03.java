package gui.thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 参考文档:
 * 
 * swing中的并发
 * 
 * https://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html
 * 
 * @author gudandan
 *
 */
public class SwingThread03 extends JFrame{
	//翻译:
	//http://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html#threading
	
	//event dispatch thread
	//swing的事件处理代码是运行在一个名字为event dispatch thead的线程上
	//大多数执行swing方法的代码都是在这个线程上运行
	//这是非常有必要的，因为swing对象的方法不是线程安全的:如果多个线程同时调用swing对象的方法，有线程干扰风险和内存不一致风险
	//在api规范中，一些组件的方法，明确说明了线程安全，则这些方法可以被任何其他线程调用都是安全的,如果没有声明是线程安全，则不行
	//对于没有声明线程安全的swing组件的方法，必须通过event dispatch thread执行.如果程序不准守这个原则，程序大多数时间在功能上还能正确执行
	//但是可能会出现难以预料的错误，而且这个错误很难被重现，无法调试
	//注意:这看来很奇怪，对于java平台，swing是一个很重要的部分，但是却不是线程安全的.因为事实上,创建一个线程安全的gui库，任何尝试都面临很多基本的问题
	//可以参考:https://community.oracle.com/blogs/kgh/2004/10/19/multithreaded-toolkits-failed-dream
	//考虑在event dispatch thread上运行的代码视为一系列shot tasks是很有用的
	//大多数任务是是通过事件处理方法来调用,例如:actionListener,actionPerformed,其他任务可以在程序代码中使用invokeLater或invodeAndWait来调用
	
	//在event dispatch thead上的任务必须很快完成，如果他们没有很快完成,则未处理的事件会阻塞，用户接口会变得迟钝，用户感觉程序无响应
	//如果想知道当前代码是否运行在event dispatch thread,可以调用javax.swing.swingutilities.isEventDispatchThread
	
	//对于swing来说,当gui创建以后，所有的gui操作都应该通过event dispatch thread来处理,参考event dispatch thread.jpg
	//event dispatch thread是swing创建的一个线程，主要负责把相关的事件放到一个队列中，然后把事件分发到对应的处理方法中
	//包括用户和gui的交互，gui的重绘和更新等,event dispatch thread对应一个 event queue，这样可以保证事件的序列化
	//event queue是一个有序序列，先进先出，把并行化处理装换为串行处理
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new SwingThread03();
				
			}
		});
		//1.代码执行到这，此时initial thread完成任务，gui创建成功
		//2.此时只有gui的event dispatch thread会一直执行，不断把和gui相关的操作事件放到一个有序队列中event queue,然后逐个分发，让这些事件按序执行
	}
	public SwingThread03(){
		this.setSize(200,200);
		this.setLocationRelativeTo(null);
		
		JButton b = new JButton("hello");
		b.addActionListener(new ActionListener() {
			//3.假如此时用户进行了和gui的交互，点击了按钮,因为swing的event dispatch thread会把gui的相关键盘，鼠标事件捕获，并且放到event queue中
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(SwingUtilities.isEventDispatchThread());//该方法可以判断，当前的执行线程是否在event dispatch thread上执行
				
				//4.event dispatch thread会不停的查看队列中是否有事件要处理，如果有，会把事件分发到对应的对象上，并根据具体代码执行响应操作.
				//用户的点击操作对应着actionPerformed()方法,此时会执行actionPerformed()方法
				//这里按钮被按下后，执行该方法
				getData();
				//5.因为getData()方法执行需要一定的事件，此时event queue被阻塞了
				//当按钮按下的这个事件执行完以后，event queue队列中的下一个事件
				//(此时队列中可能有很多事件，比如按钮弹起恢复到原样这个事件)才会被event dispatch thread分发执行
				//所以，这里会看到按钮被按下，等了一会，才被弹出，这是因为，弹出按钮这个事件，在getData()执行完后，才被分发，执行
				//这段代码反应了一个问题:
				//比如这里需要查询数据库操作，用时比较长，就会出现程序卡住了的假象
				//此时任何gui的操作，交互都被挂起，比如，你点击其他操作，拉伸窗口等等
			}
		});
		
		this.add(b);
		this.setVisible(true);
	}
	public void getData(){
		try {
			System.out.println("开始取数据");
			Thread.currentThread().sleep(5000);
			System.out.println("取到数据");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}




















































