package gui.event;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
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
public class Event15 extends Frame{
	
	
	
	public static void main(String[]args){
		Event15 f = new Event15();
		f.setLayout(new FlowLayout());
	
		//判断鼠标哪个键点击了的2种方法:
		
		//方法1:
		Button b1 = new Button("button1");
		b1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(	(e.getModifiers()&e.BUTTON1_MASK) != 0){
					//左键
					System.out.println("左键");
				}
				
				if( (e.getModifiers()&MouseEvent.BUTTON2_MASK) != 0){
					//中键
					System.out.println("中键");
				}
				
				if( (e.getModifiers()&MouseEvent.BUTTON3_MASK) != 0){
					//右键
					System.out.println("右键");
				}
			
				//或者
				
				if(	(e.getModifiers()&e.BUTTON1_MASK) == e.BUTTON1_MASK){
					//左键
					System.out.println("左键....");
				}
				
				if(	(e.getModifiers()&e.BUTTON2_MASK) == e.BUTTON2_MASK){
					//中键
					System.out.println("中键....");
				}
				
				if(	(e.getModifiers()&e.BUTTON3_MASK) == e.BUTTON3_MASK){
					//右键
					System.out.println("右键....");
				}	
			}
		});
		
		//方法2:
		Button b2 = new Button("button2");
		b2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)){
					System.out.println("左键");
				}
				if(SwingUtilities.isMiddleMouseButton(e)){
					System.out.println("中键");
				}
				if(SwingUtilities.isRightMouseButton(e)){
					System.out.println("右键");
				}
			}
		});
		
		
		f.add(b1);
		f.add(b2);
		f.setSize(500,500);
		f.setVisible(true);
	}
}

