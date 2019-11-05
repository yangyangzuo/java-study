package gui.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 二、问题的解决
 * 
 * 知道了闪烁产生的原因，我们就有了更具针对性的解决闪烁的方案。已经知道update(Graphics g)是造成闪烁的主要原因， 那么就从这里入手。
 * 
 * (1）尝试这样重载update(Graphics g)函数（基于代码段一修改）：
 * 
 * public void update(Graphics scr) { paint(scr); }
 * 
 * 以上代码在重绘小圆之前没有用背景色重绘整个画面，而是直接调用paint(Graphics g)函数，
 * 
 * 这就从根本上避免了上述的那幅空白画面。 看看运行结果，闪烁果然消除了 ！
 * 
 * 但是更大的问题出现了，不同时刻绘制的小圆重叠在一起形成了一条线！
 * 
 * 这样的结果我们更不能接受了。为什么会这样呢？
 * 
 * 仔细分析一下，重载后的update (Graphics g)函数中没有了任何清屏的操作，
 * 
 * 每次重绘都是在先前已经绘制好的图象的基础上，当然会出现重叠的现象了。
 */

public class DoubleBuffer2 extends Frame// 主类继承Frame类
{
	public PaintThread2 pT;// 绘图线程
	public int ypos = -80; // 小圆左上角的纵坐标

	public DoubleBuffer2()// 构造函数
	{
		pT = new PaintThread2(this);
		this.setResizable(false);
		this.setSize(300, 300); // 设置窗口的首选大小
		this.setVisible(true); // 显示窗口
		pT.start();// 绘图线程启动
	}

	@Override
	public void paint(Graphics scr) // 重载绘图函数
	{
		scr.setColor(Color.RED);// 设置小圆颜色
		scr.fillOval(90, ypos, 80, 80); // 绘制小圆
	}

	@Override
	public void update(Graphics g)
	{
		this.paint(g);
	}

	public static void main(String[] args)
	{
		DoubleBuffer2 DB = new DoubleBuffer2();// 创建主类的对象
		DB.addWindowListener(new WindowAdapter()// 添加窗口关闭处理函数
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				});
	}
}

class PaintThread2 extends Thread// 绘图线程类
{
	DoubleBuffer2 DB;

	public PaintThread2(DoubleBuffer2 DB) // 构造函数
	{
		this.DB = DB;
	}

	public void run()// 重载run()函数
	{
		while (true)// 线程中的无限循环
		{
			try
			{
				sleep(30); // 线程休眠30ms
			} catch (InterruptedException e)
			{
			}
			DB.ypos += 5; // 修改小圆左上角的纵坐标
			if (DB.ypos > 300) // 小圆离开窗口后重设左上角的纵坐标
				DB.ypos = -80;
			DB.repaint();// 窗口重绘
		}
	}
}
