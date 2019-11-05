package gui.event;

import java.awt.Button;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Frame;
/**
 * 传播/继承事件模型:
 * 
 * jdk1.0时,awt没有任何面向对象手段,必须通过if语句的嵌套来侦测所谓的事件目标对象
 * 
 * jdk1.0时,这里我们覆盖component组件中的action方法来实现事件的监听
 * 
 * action()方法有２个参数，一个是事件类型，一个是事件对象
 * 
 * jdk1.0中基于传播/继承事件模型的事件对象是java.awt.Event类,但是到jdk1.1就废弃了，他被java.awt.AWTEvent类及其子类替代
 * 
 * @author zuoyang
 *
 */
public class Event01 extends Frame{
	public static Button b1 = new Button("button1");
	public static Button b2 = new Button("button2");
	public static void main(String[]args){
		Event01 f = new Event01();
		f.setLayout(new FlowLayout());
		f.add(b1);
		f.add(b2);
		f.setSize(200,200);
		f.setVisible(true);
	}
	
	@Override
	public boolean action(Event evt, Object what) {
		if(evt.target == b1){
			System.out.println("button1");
		}else if(evt.target == b2){
			System.out.println("button2");
		}
		return super.action(evt, what);
	}
	
}

