package gui.event;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 授权事件模型(delegation event model):
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
public class Event13 extends Frame{
	
	public static Listener13 listener = new Listener13();
	public static Button b1 = new Button("button1");
	
	public static void main(String[]args){
		Event13 f = new Event13();
		f.setLayout(new FlowLayout());
		
		//对组件button添加事件监听
		//事件监听类可以是自己定义的一个类，通过继承/实现特定的类
		b1.addActionListener(listener);
		
		f.add(b1);
		f.setSize(200,200);
		f.setVisible(true);
	}
}
class Listener13 implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("button1 action");
		//按钮点击一次后，移除监听事件
		Event13.b1.removeActionListener(Event13.listener);
	}
	
}

