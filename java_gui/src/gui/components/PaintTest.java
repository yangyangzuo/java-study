package gui.components;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * paint()方法测试
 * 
 * 当组件第一次在屏幕上显示的时候，AWT线程会自动调用paint()方法来绘制组件
 * 
 * @author Administrator
 * 
 */
public class PaintTest
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		PaintTest p = new PaintTest();
		MyFrame f = p.new MyFrame();
		f.setSize(200, 300);
		
		
		f.setVisible(true);
	}

	@SuppressWarnings("serial")
	class MyFrame extends Frame
	{

		@Override
		public void paint(Graphics g)
		{
			// 得到并保存系统默认画笔的颜色
			Color c = g.getColor();
			g.setColor(Color.red);
			g.drawString("画一个字符串", 20, 40);
			// 把画笔的颜色变回原样
			g.setColor(c);
		}

	}

}
