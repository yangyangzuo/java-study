package gui.components;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;

/**
 * 
 * 
 * Canvas 组件表示屏幕上一个空白矩形区域，应用程序可以在该区域内绘图，或者可以从该区域捕获用户的输入事件。
 * 
 * 画布本身不做任何事情，它只是提供一种实现用户定制组件的途径，例如：画布可以作为图象/定制图形的显示区，另外
 * 
 * 当用户需要一种不同于缺省实现的控制(如定制的按钮)时，画布就十分有用
 * 
 * 
 * 
 * 应用程序必须为 Canvas 类创建子类，以获得有用的功能（如创建自定义组件）。必须重写 paint 方法，以便在 canvas 上执行自定义图形。
 * 
 * Canvas : 帆布,油画布,油画,风帆
 * 
 * 画布提供了一个空白(背景色)的空间,画布的空间可以用来绘图,显示文本，接受键盘/鼠标输入
 * 
 * 除非用setSize()显示的定义他的大小，否则他的大小就是0*0
 * 
 * 1.要使用画布，需要一个类扩展Canvas,然后实现paint方法
 * 
 * 2.在paint方法里面使用画笔Graphics来画出需要的界面，setColor方法相当于切换画笔,然后就可以使用 Graphics的方法来画界面
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class Gui_07_CanvasTest
{
	public static void main(String[] args)
	{
		//创建了一个对象，并覆盖了其中的一个方法
		Canvas c = new Canvas()
		{
			public void paint(Graphics g)
			{
				g.setColor(Color.RED);
				//注意因为frame取消了布局管理器，
				//所以这个坐标是冲图形窗口的左上角(包含标题栏)开始计算的
				g.drawString("hello", 30, 30);
			}
		};
		c.setSize(100, 100);
		c.setBackground(Color.YELLOW);

		Frame f = new Frame();
		f.setSize(200, 200);
		f.setLayout(null);
		f.add(c);
		f.setVisible(true);
	}
}
