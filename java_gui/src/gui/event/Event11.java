package gui.event;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
/**
 * 授权事件模型(delegation event model):
 * 
 * http://docs.oracle.com/javase/tutorial/uiswing/events/index.html
 * 
 * jdk1.1中引入了一个新的基于授权的事件模型
 * 
 * 原理:组件激发事件，事件监听者监听和执行事件
 * 通过addXXXListener()方法向组件注册一个监听者，把监听者加入组件后，如果组件发生对应事件，则监听者适当方法被调用
 * 
 * 从jdk1.1开始，新事件模型中，java.awt.AWTEvent是所有AWT事件的根事件类。此类及其子类取代了原来的 java.awt.Event 类 
 * @author zuoyang
 *
 */
public class Event11 extends Frame{
	public static void main(String[]args){
		Event11 f = new Event11();
		f.setLayout(new FlowLayout());
		Button b1 = new Button("button1");
		
		//对组件button添加事件监听
		//事件监听类可以是自己定义的一个类，通过继承/实现特定的类
		//也可以写一个匿名类，例如本例子重的程序
		b1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//ActionEvent类继承了AWTEvent类
				System.out.println("button1 action");
			}
		});
		
		f.add(b1);
		
		//给当前窗体注册一个窗体监听事件
		f.addWindowListener(new WindowListener(){

			@Override
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				//当发生关闭窗体动作时，退出程序
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
		});
		f.setSize(200,200);
		f.setVisible(true);
	}
}

