package gui.game;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 创建可以改变窗口大小的程序
 * 
 * 注意：
 * 
 * 1.如果窗口可以改变大小，窗口中的图象必须是相对位置，不能用绝对位置，否则改变窗口后，图象可能显示不出来
 * 
 * 2.如果窗口可以改变大小的话，缓冲图片必须判断，创建的时候不能每次都创建一个新的,只有改变大小的时候再创建一个新的，
 * 否则，你在把窗口由大改变到小的时候会出现小小的闪烁，原理不清楚
 * 
 * 3.注意awt线程机制，具体例子看DoubleBuffer6
 * 
 * 
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class DoubleBuffer5 extends Frame// 主类继承Frame类
{
	public PaintThread5 pT;// 绘图线程
	public int ypos = -80; // 小圆左上角的纵坐标
	// 在DoubleBuffer类中添加如下两个私有成员
	private Image iBuffer;
	private Graphics gBuffer;
	private int width = 300;
	private int height = 200;
	private boolean resize = false;

	public DoubleBuffer5()// 构造函数
	{
		this.setResizable(true);
		this.setSize(width, height); // 设置窗口的首选大小
		this.setVisible(true); // 显示窗口
		this.addComponentListener(new ComponentAdapter()
		{

			@Override
			public void componentResized(ComponentEvent e)
			{
				width = e.getComponent().getWidth();
				height = e.getComponent().getHeight();
				resize = true;
			}

		});
		pT = new PaintThread5(this);
		pT.start();// 绘图线程启动
	}

	public void update(Graphics scr)
	{
		if (iBuffer == null || resize)
		{
			// 创建缓冲图象
			iBuffer = createImage(this.getWidth(), this.getHeight());
			// 的到缓冲图象的画笔
			gBuffer = iBuffer.getGraphics();
		}
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0, 0, this.getWidth(), this.getHeight());
		gBuffer.setColor(Color.RED);
		gBuffer.fillOval(90, ypos, 80, 80);
		scr.drawImage(iBuffer, 0, 0, this);
		resize = false;
	}

	public static void main(String[] args)
	{
		DoubleBuffer5 DB = new DoubleBuffer5();// 创建主类的对象
		DB.addWindowListener(new WindowAdapter()// 添加窗口关闭处理函数
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				});
	}
}

class PaintThread5 extends Thread// 绘图线程类
{
	DoubleBuffer5 DB;

	public PaintThread5(DoubleBuffer5 DB) // 构造函数
	{
		this.DB = DB;
	}

	public void run()// 重载run()函数
	{
		while (true)// 线程中的无限循环
		{
			try
			{
				sleep(20); // 线程休眠30ms
			} catch (InterruptedException e)
			{
			}
			DB.ypos += 5; // 修改小圆左上角的纵坐标
			if (DB.ypos > Toolkit.getDefaultToolkit().getScreenSize().height) // 小圆离开窗口后重设左上角的纵坐标
				DB.ypos = -80;
			DB.repaint();// 窗口重绘
		}
	}
}
