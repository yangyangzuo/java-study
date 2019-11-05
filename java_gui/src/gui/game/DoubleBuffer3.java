package gui.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 二、问题的解决
 * 
 * (2）使用双缓冲： 这是本文讨论的重点。
 * 
 * 所谓双缓冲，就是在内存中开辟一片区域，作为后台图象，程序对它进行更新、修改，绘制完成后再显示到屏幕上。
 * 
 * 2.1、重载paint(Graphics g)实现双缓冲：
 * 
 * 这种方法要求我们将双缓冲的处理放在paint(Graphics g)函数中，那么具体该怎么实现呢？
 * 
 * 先看下面的代码（基于代码段一修改）：
 * 
 * 在DoubleBuffer类中添加如下两个私有成员：
 * 
 * private Image iBuffer; private Graphics gBuffer;
 * 
 * 重载paint(Graphics scr)函数：
 * 
 * public void paint(Graphics scr)
 * 
 * { if(iBuffer==null)
 * 
 * {
 * 
 * iBuffer=createImage(this.getSize().width,this.getSize().height);
 * 
 * gBuffer=iBuffer.getGraphics();
 * 
 * }
 * 
 * gBuffer.setColor(getBackground());
 * 
 * gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
 * 
 * 
 * gBuffer.setColor(Color.RED);
 * 
 * gBuffer.fillOval(90,ypos,80,80);
 * 
 * 
 * scr.drawImage(iBuffer,0,0,this);
 * 
 * }
 * 
 */
public class DoubleBuffer3 extends Frame// 主类继承Frame类
{
	public PaintThread3 pT;// 绘图线程
	public int ypos = -80; // 小圆左上角的纵坐标
	// 在DoubleBuffer类中添加如下两个私有成员
	private Image iBuffer;
	private Graphics gBuffer;

	public DoubleBuffer3()// 构造函数
	{
		pT = new PaintThread3(this);
		this.setResizable(false);
		this.setSize(300, 300); // 设置窗口的首选大小
		this.setVisible(true); // 显示窗口
		pT.start();// 绘图线程启动
	}

	public void paint(Graphics scr)
	{
		// 图片缓冲用一张就可以了，如果不判断，每次多创建一张，浪费内存
		// 注意：这里设置了缓冲图片的大小，如果窗体的大小改变了，那么缓冲图片的大小也需要改变,默认设置的是
		// 一张图片，如果窗体动态改变，那么图片也需要动态改变，1可以创建动态图片，每次都创建一张，但是浪费内存
		// 2判断窗体改变事件，如果窗体改变了大小，再重新生成一个和窗体大小一样的缓冲图片
		if (iBuffer == null)
		{
			// Component的方法：public Image createImage(int width,int height)
			// 一幅屏幕外可绘制的图像，可用于双缓冲。如果组件是不可显示的，则返回值可能为 null。如果
			// GraphicsEnvironment.isHeadless() 返回 true，则经常发生这种情况。
			iBuffer = createImage(this.getSize().width, this.getSize().height);
			// 得到缓冲图象的画笔
			gBuffer = iBuffer.getGraphics();
		}
		// 把图象画在屏幕的缓冲图象上

		// 使缓冲图片的背景和当前屏幕的背景颜色保持一致
		gBuffer.setColor(getBackground());
		// 刷新缓冲图片,用背景色刷新缓冲图片，注意：这个缓冲图片和窗口一样大,这里的刷新方式是画一个和窗口一样大小的矩形来覆盖
		gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
		// 开始画图象，先设置要画的图象的颜色
		gBuffer.setColor(Color.RED);
		// 把图象画在缓冲图片上
		gBuffer.fillOval(90, ypos, 80, 80);
		// 缓冲图象画好后，在把缓冲图象直接画在屏幕上
		scr.drawImage(iBuffer, 0, 0, this);// 最后一个参数为null也可以
	}

	public static void main(String[] args)
	{
		DoubleBuffer3 DB = new DoubleBuffer3();// 创建主类的对象
		DB.addWindowListener(new WindowAdapter()// 添加窗口关闭处理函数
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				});
	}
}

class PaintThread3 extends Thread// 绘图线程类
{
	DoubleBuffer3 DB;

	public PaintThread3(DoubleBuffer3 DB) // 构造函数
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

// 分析上述代码：
// 我们首先添加了两个成员变量iBuffer(imageBuffer)和gBuffer(graphicsBuffer)作为缓冲（这就是所谓的双缓冲名字的来历）。
// 在paint(Graphics scr)函数中，首先检测如果iBuffer为null，则创建一个和屏幕上的绘图区域大小一样的缓冲图象，
// 再取得iBuffer的Graphics类型的对象的引用，并将其赋值给gBuffer，
// 然后对gBuffer这个内存中的后台图象先用fillRect(int,int,int,int)清屏，再进行绘制操作，完成后将iBuffer直接绘制到屏幕上。
// 这段代码看似可以完美地完成双缓冲，但是，运行之后我们看到的还是严重的闪烁！为什么呢？
// 回想上文所讨论的，问题还是出现在update(Graphics g)函数！这段修改后的程序中的update(Graphics
// g)函数还是我们从父类继承的。
// 在update(Graphics g)中，clearRect(int,int,int,int)对前端屏幕进行了清屏操作，而在paint(Graphics
// g)中，对后台图象又进行了清屏操作。
// 那么如果保留后台清屏，去掉多余的前台清屏应该就会消除闪烁。
// 所以，我们只要重载update(Graphics g)即可： public void update(Graphics scr) {
// paint(scr);}
// 这样就避开了对前端图象的清屏操作，避免了屏幕的闪烁。虽然前面一样的方法重载update(Graphics g)，
// 这里我们把清屏操作放在了后台图象上，消除了闪烁的同时也获得了预期的动画效果。

