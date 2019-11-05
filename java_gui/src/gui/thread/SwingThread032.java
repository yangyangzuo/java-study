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
public class SwingThread032 extends JFrame{
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new SwingThread032();
				
			}
		});
		//1.代码执行到这，此时initial thread完成任务，gui创建成功
		//2.此时只有gui的event dispatch thread会一直执行，不断把和gui相关的操作事件放到一个有序队列中event queue,然后逐个分发，让这些事件按序执行
	}
	public SwingThread032(){
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
				//这里按钮被按下后，执行该方法，同时会立即有一个事件(按钮弹起恢复到原样)被放置到event queue中
				
				
				//错误的示范
				//此时当前线程已经event queue上了,执行如下代码，还是把如下代码放到event queue上，并不能解决问题
				//当执行到如下代码的时候，还是会阻塞edt线程
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						getData();
					}
				});
				
				
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




















































