package gui.event;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
public class Event12 extends Frame{
	public static void main(String[]args){
		Event12 f = new Event12();
		
		//给当前窗体注册一个窗体监听事件
		//从前面的例子中，我们可以发现，注册一个监听器对象，如果直接实现这个接口，里面有很多方法都要实现，也许是空实现，但是你必须覆盖
		//api中提供了一个XXAdapter类，从源码就可以看出来，这个WindowAdapter实际对于WindowListener接口中的方法都是空实现，这样的一个好处是，我们通过这个类，只需要覆盖我们关注的方法即可
		f.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		f.setSize(200,200);
		f.setVisible(true);
	}
}

