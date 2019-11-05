package gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;

/**
 * 定义字体规格类测试,FontMetrics类对字体规格做了详细的规定
 * 
 * 详细见图
 * 
 * @author Administrator
 * 
 */
public class FontMetricsTest extends Frame
{

	public static void main(String[] args)
	{
		FontMetricsTest f = new FontMetricsTest();
		f.setSize(300, 400);
		f.setVisible(true);
		

	}

	@Override
	public void paint(Graphics g)
	{
		Font f = new Font("宋体", Font.BOLD, 20);
		
		Color c = g.getColor();
		g.setColor(Color.red);
		g.setFont(f);
		g.drawString("这是宋体", 80, 80);
		g.setColor(c);
	}

}
