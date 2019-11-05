package gui.event;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
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
public class Event14 extends Frame{
	
	
	
	public static void main(String[]args){
		Event14 f = new Event14();
		f.setLayout(new FlowLayout());
		
		Button b1 = new Button("button1");
		//对组件button添加事件监听
		//事件监听类可以是自己定义的一个类，通过继承/实现特定的类
		//在一个特定组件上侦听鼠标移动事件
		b1.addMouseMotionListener(new MouseMotionAdapter(){
			@Override
			public void mouseMoved(MouseEvent e) {
				System.out.println(e.getX() + ":" + e.getY());
			}
		});
		
		f.add(b1);
		f.setSize(200,200);
		f.setVisible(true);
	}
}

