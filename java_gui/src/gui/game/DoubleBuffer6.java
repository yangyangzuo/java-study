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
 * 
 * awt线程机制举例
 * 
 * 
 * 该程序有时候会报如下异常： Exception in thread "AWT-EventQueue-0"
 * java.lang.IllegalArgumentException: Width (0) and height (0) cannot be <= 0
 * 
 * 原因是awt线程比 this.setSize(300, 300); 这行代码先执行 所以再执行iBuffer = createImage(width,
 * height);或者gBuffer.fillRect(0, 0, width, height);
 * 的时候发现定义中的width和height为0,没法画图形，系统抛异常
 * 
 * 解决办法：
 * 
 * 1.如果你定义了窗口的变量，那么在定义的时候初始化,这样就可以 iBuffer = createImage(width,height);
 * gBuffer.fillRect(0, 0, width, height);直接引用width和height变量了
 * 
 * 2.如果你没有定义变量，那么在iBuffer = createImage(this.getWidht(),this.getHeight());
 * gBuffer.fillRect(0, 0,this.getWidht(),this.getHeight());这样引用
 * 
 * 
 * 
 * @author Administrator
 * 
 */
public class DoubleBuffer6 extends Frame// 主类继承Frame类
{
	public PaintThread6 pT;// 绘图线程
	public int ypos = -80; // 小圆左上角的纵坐标
	// 在DoubleBuffer类中添加如下两个私有成员
	private Image iBuffer;
	private Graphics gBuffer;
	private int width;
	private int height;
	private boolean resize = false;

	public DoubleBuffer6()// 构造函数
	{
		this.setResizable(true);
		this.setSize(300, 300); // 设置窗口的首选大小
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
		pT = new PaintThread6(this);
		pT.start();// 绘图线程启动
	}

	public void update(Graphics scr)
	{
		if (iBuffer == null || resize)
		{
			// 创建缓冲图象
			iBuffer = createImage(width, height);
			// 的到缓冲图象的画笔
			gBuffer = iBuffer.getGraphics();
		}
		gBuffer.setColor(getBackground());
		gBuffer.fillRect(0, 0, width, height);
		gBuffer.setColor(Color.RED);
		gBuffer.fillOval(90, ypos, 80, 80);
		scr.drawImage(iBuffer, 0, 0, this);
		resize = false;
	}

	public static void main(String[] args)
	{
		DoubleBuffer6 DB = new DoubleBuffer6();// 创建主类的对象
		DB.addWindowListener(new WindowAdapter()// 添加窗口关闭处理函数
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				});
	}
}

class PaintThread6 extends Thread// 绘图线程类
{
	DoubleBuffer6 DB;

	public PaintThread6(DoubleBuffer6 DB) // 构造函数
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
