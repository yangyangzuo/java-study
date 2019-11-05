package gui.components;

import java.awt.Container;
import java.awt.Frame;

import javax.swing.JLabel;

/**
 * 
 * Component提供了:
 * 
 * 1.绘制功能：Paint(),update(),repaint()方法用来绘制组件
 * 
 * 2.事件处理：Component类定义了通用的事件处理方handleEvent()方法和一组事件处理的专用方法 
 * (handleEvent已经过期，从 JDK version 1.1 开始，由 processEvent(AWTEvent) 取代) 
 * 
 * 3.字体控制
 * 
 * 4.颜色控制
 * 
 * 5.图象处理
 * 
 * 6.屏幕显示大小和位置控制 
 * @author Administrator
 * 
 */
public class Gui_01_Component
{

	public static void main(String[] args) throws InterruptedException
	{
		Frame f = new Frame();
		f.setSize(500, 500);
		//pack()方法是Window对象的一个方法,只有window对象或者他的子类(Frame,dialog等等)创建的对象才可以有这个方法
		//pack()方法:调整此窗口的大小，以适合其子组件的首选大小和布局
		//如果前面设置了窗体的大小，又调用了该方法，则窗口尺寸会变为适应包裹组件的大小，
		//不会显示为上面定义的大小(500*500),
		f.pack();
		//这里因为frame中没有其他组件,所以大小为0

		
		//是否显示标题和菜单
		//f.setUndecorated(true);

		f.setVisible(true);

	}
}
