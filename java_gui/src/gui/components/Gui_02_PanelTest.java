package gui.components;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;

/**
 * Panel的使用：
 * 
 * 和Frame一样,Panel提供空间用来连接任何Gui组件，包括其他面板,每个面扳都可以有它自己的布局管理程序
 * 
 * 一旦一个面扳对象创建，为了可见，他必须被添加到窗口/框架对象上
 * 
 * panel不能单独存在，只能存在于其他容器中(比如:frame),一个panel对象代表了一个区域,在这个区域内可以容纳其他的组件
 * 
 * 可以通过Panel类的默认构造方法来创建一个Panel对象,然后通过Panel的add()方法向Panel中添加组件
 * 
 * 如果要使panel可见，必须通过Fram/window的add()方法把Panel添加到Frame或者window中 
 * 
 * 用container中的add()
 * 
 * @author Administrator
 *
 */
public class Gui_02_PanelTest
{
	
	public static void main(String[] args)
	{
		Panel p = new Panel();
		p.setBackground(Color.RED);
		p.setSize(100, 100);
		
		Frame g = new Frame();
		g.add(p);
		g.setSize(300,300);
		g.setBackground(Color.YELLOW);
		//如果不把布局管理器设置为null,窗口的默认布局是 BorderLayout,添加组件时，会占据frame中的所有内容,
		g.setLayout(null);
		
		
		g.setVisible(true);
	}
}
