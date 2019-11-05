package gui.thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

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
public class SwingThread033 extends JFrame{
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new SwingThread033();
				
			}
		});
		//1.代码执行到这，此时initial thread完成任务，gui创建成功
		//2.此时只有gui的event dispatch thread会一直执行，不断把和gui相关的操作事件放到一个有序队列中event queue,然后逐个分发，让这些事件按序执行
	}
	public SwingThread033(){
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
				//event dispatch thread线程中是不能调用invodeAndWait()方法的，此时会造成死锁
				//invokeAndWait()方法会把当前代码放置到event queue队列的最后，然后等待这段代码执行完才能返回
				//此时，应为在edt线程上执行，代码被加到队列最后，当前的edt线程会等待该代码执行完，
				//但是当前代码又在队列上，等待edt的分发，所以造成了死锁
				//jdk不会执行如下代码，会直接报错
				//invokeAndWait()方法的原理是:
				//阻塞当前线程，直到runnable对象中的run方法执行完成,当前的invokeAndWait()方法才会返回
				//当前线程是edt线程，所以会阻塞,直到runnable对象中的run方法执行完，然而此时runnable被放到了edt线程事件队列的最后
				//所以runnable对象永远也不会执行，程序进入死锁
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						@Override
						public void run() {
							getData();
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
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




















































