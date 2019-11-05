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
 * 2.2重载update(Graphics g)实现双缓冲：
 * 
 * 这是比较传统的做法。也是实际开发中比较常用的做法(画图象的部分放在了paint方法中，第一次调用的时候，就会显示,
 * 如果把画的过程都放在了update方法中，第一次调用的时候不会显示图象)。我们看看实现这种方法的代码（基于代码段一修改）：
 * 
 * 在DoubleBuffer类中添加如下两个私有成员：
 * 
 * private Image iBuffer; private Graphics gBuffer;
 * 
 * 重载paint(Graphics scr)函数：
 * 
 * public void paint(Graphics scr)
 * 
 * { scr.setColor(Color.RED); scr.fillOval(90,ypos,80,80);
 * 
 * }
 * 
 * 重载update(Graphics scr)函数：
 * 
 * public void update(Graphics scr)
 * 
 * {
 * 
 * if(iBuffer==null)
 * 
 * {
 * 
 * iBuffer=createImage(this.getSize().width,this.getSize().height);
 * gBuffer=iBuffer.getGraphics();
 * 
 * }
 * 
 * gBuffer.setColor(getBackground());
 * gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
 * paint(gBuffer); scr.drawImage(iBuffer,0,0,this);
 * 
 * }
 * 
 * 
 * 
 */
public class DoubleBuffer4 extends Frame// 主类继承Frame类
{
	public PaintThread4 pT;// 绘图线程
	public int ypos = -80; // 小圆左上角的纵坐标
	// 在DoubleBuffer类中添加如下两个私有成员
	private Image iBuffer;
	private Graphics gBuffer;

	public DoubleBuffer4()// 构造函数
	{
		pT = new PaintThread4(this);
		this.setResizable(false);
		this.setSize(300, 300); // 设置窗口的首选大小
		this.setVisible(true); // 显示窗口
		pT.start();// 绘图线程启动
	}

	public void paint(Graphics scr)
	{
		scr.setColor(Color.RED);
		scr.fillOval(90, ypos, 80, 80);
	}

	public void update(Graphics scr)
	{
		// 图片缓冲用一张就可以了，如果不判断，每次多创建一张，浪费内存
		if (iBuffer == null)
		{
			// 创建缓冲图象
			iBuffer = createImage(this.getSize().width, this.getSize().height);
			// 的到缓冲图象的画笔
			gBuffer = iBuffer.getGraphics();
		}// 使缓冲图片的背景和当前屏幕的背景颜色保持一致
		gBuffer.setColor(getBackground());
		// 刷新缓冲图片,用背景色刷新缓冲图片，注意：这个缓冲图片和窗口一样大,这里的刷新方式是画一个和窗口一样大小的矩形来覆盖
		gBuffer.fillRect(0, 0, this.getSize().width, this.getSize().height);
		// 开始画图象
		paint(gBuffer);
		scr.drawImage(iBuffer, 0, 0, this);//最后一个参数为null也可以
	}

	public static void main(String[] args)
	{
		DoubleBuffer4 DB = new DoubleBuffer4();// 创建主类的对象
		DB.addWindowListener(new WindowAdapter()// 添加窗口关闭处理函数
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				});
	}
}

class PaintThread4 extends Thread// 绘图线程类
{
	DoubleBuffer4 DB;

	public PaintThread4(DoubleBuffer4 DB) // 构造函数
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

// 分析上述代码：我们把对后台图象的创建、清屏以及重绘等一系列动作都放在了update(Graphics scr)函数中，
// 而paint(Graphics g)函数只是负责绘制什么样的图象，以及怎样绘图，函数的最后实现了后台图象向前台绘制的过程。
// 运行上述修改后的程序，我们会看到完美的消除闪烁后的动画效果。
// 就像在电影院看电影，每张胶片都是在后台准备好的，播放完一张胶片之后，下一张很快就被播放到前台，自然不会出现闪烁的情形。
// 为了让读者能对双缓冲有个全面的认识现将上述双缓冲的实现概括如下：
// （1）定义一个Graphics对象gBuffer和一个Image对象iBuffer。按屏幕大小建立一个缓冲对象给iBuffer。然后取得iBuffer的Graphics赋给gBuffer。此处可以把gBuffer理解为逻辑上的缓冲屏幕，而把iBuffer理解为缓冲屏幕上的图象。
// （2） 在gBuffer（逻辑上的屏幕）上用paint(Graphics g)函数绘制图象。
// （3） 将后台图象iBuffer绘制到前台。
// 以上就是一次双缓冲的过程。注意，将这个过程联系起来的是repaint()函数。paint(Graphics
// g)是一个系统调用语句，不能由程序员手工调用。只能通过repaint()函数调用。

// 三、问题的扩展
// 1、关于闪烁的补充：
// 其实引起闪烁的不仅仅是上文提到的那样，多种物理因素也可以引起闪烁，
// 无论是CRT显示器还是LCD显示器都存在闪烁的现象。本文只讨论软件编程引起的闪烁。
// 但是即使双缓冲做得再好，有时也是会有闪烁，这就是硬件方面的原因了，
// 我们只能修改程序中的相关参数来降低闪烁（比如让画面动得慢一点），而不是编程方法的问题。

// 2、关于消除闪烁的方法的补充：
// 上文提到的双缓冲的实现方法只是消除闪烁的方法中的一种。如果在swing中，
// 组件本身就提供了双缓冲的功能，我们只需要进行简单的函数调用就可以实现组件的双缓冲，
// 在awt中却没有提供此功能。另外，一些硬件设备也可以实现双缓冲，每次都是先把图象画在缓冲中，
// 然后再绘制在屏幕上，而不是直接绘制在屏幕上，基本原理还是和文中的类似的。还有其他用软件实现消除闪烁的方法，
// 但双缓冲是个简单的、值得推荐的方法。

// 3、关于双缓冲的补充：
// 双缓冲技术是编写J2ME游戏的关键技术之一。双缓冲付出的代价是较大的额外内存消耗。
// 但现在节省内存已经不再是程序员们考虑的最首要的问题了，游戏的画面在游戏制作中是至关重要的，
// 所以以额外的内存消耗换取程序质量的提高还是值得肯定的。
// 4、双缓冲的改进：
// 有时动画中相邻的两幅画面只是有很少部分的不同，这就没必要每次都对整个绘图区进行清屏。
// 我们可以对文中的程序进行修改，使之每次只对部分屏幕清屏，这样既能节省内存，
// 又能减少绘制图象的时间，使动画更加连贯！
