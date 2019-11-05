package gui.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//  Java双缓冲技术:
//  
// Java的强大特性让其在游戏编程和多媒体动画处理方面也毫不逊色。在Java游戏编程和动画编程中最常见的就是对于屏幕闪烁的处理。
// 本文从J2SE的一个再现了屏幕闪烁的Java Appilication简单动画实例展开，对屏幕闪烁的原因进行了分析，
// 找出了闪烁成因的关键：update(Graphics g)函数对于前端屏幕的清屏。由此引出消除闪烁的方法——双缓冲。
// 双缓冲是计算机动画处理中的传统技术，在用其他语言编程时也可以实现。本文从实例出发，着重介绍了用双缓冲消除闪烁的原理
// 以及双缓冲在Java中的两种常用实现方法（即在update(Graphics g)中实现和在paint(Graphics g)中实现），
// 以期读者能对双缓冲在Java编程中的应用能有个较全面的认识。

/**
 * 一、问题的引入
 * 
 * 在编写Java多媒体动画程序或用Java编写游戏程序的时候，我们得到的动画往往存在严重的闪烁（或图片断裂）。
 * 这种闪烁虽然不会给程序的效果造成太大的影响，但着实有违我们的设计初衷，也给程序的使用者造成了些许不便。
 * 闪烁到底是什么样的呢？下面的JavaApplication再现了这种屏幕闪烁的情况：
 */

public class DoubleBuffer1 extends Frame// 主类继承Frame类
{
	public PaintThread pT;// 绘图线程
	public int ypos = -80; // 小圆左上角的纵坐标

	public DoubleBuffer1()// 构造函数
	{
		pT = new PaintThread(this);
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
		
		//repaint方法也可以写在这里，比如：
		//1.休眠
		//2.repaint();
		//系统执行方式：paint->休眠->repaint()->update()->(paint()->休眠......循环)
	}

	public static void main(String[] args)
	{
		DoubleBuffer1 DB = new DoubleBuffer1();// 创建主类的对象
		DB.addWindowListener(new WindowAdapter()// 添加窗口关闭处理函数
				{
					public void windowClosing(WindowEvent e)
					{
						System.exit(0);
					}
				});
	}
}

class PaintThread extends Thread// 绘图线程类
{
	DoubleBuffer1 DB;

	public PaintThread(DoubleBuffer1 DB) // 构造函数
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

// 编译、运行上述例子程序后，我们会看到窗体中有一个从上至下匀速运动的小圆，但仔细观察，
// 你会发现小圆会不时地被白色的不规则横纹隔开，即所谓的屏幕闪烁，这不是我们预期的结果。
// 这种闪烁是如何出现的呢？
// 首先我们分析一下这段代码。DoubleBuffer的对象建立后，显示窗口，程序首先自动调用重载后的paint(Graphics
// g)函数，在窗口上绘制了一个小圆，绘图线程启动后，该线程每隔30ms修改一下小圆的位置，然后调用repaint()函数。

// 注意，这个repaint()函数并不是我们重载的，而是从Frame类继承而来的。
// 它先调用update(Graphics g)函数，update(Graphics g)再调用paint(Graphicsg)函数。
//问题就出在update(Graphics g)函数，我们来看看这个函数的源代码：

//repaint()-->会调用update()方法-->而update()方法，
//会先用当前组件的背景色来覆盖当前组件(相当于擦除当前屏幕上的图案)，然后在调用paint()方法来绘制图形

// public void update(Graphics g)
// {
// 	if (isShowing())
// 	{
// 		if (! (peer instanceof LightweightPeer))
// 		{
// 			g.clearRect(0, 0, width, height);
// 		}
// 		paint(g);
// 	}
// }
// 以上代码的意思是：（如果该组件是轻量组件的话）先用背景色覆盖整个组件，然后再调用paint(Graphics
// g)函数，重新绘制小圆。这样，我们每次看到的都是一个在新的位置绘制的小圆，前面的小圆都被背景色覆盖掉了。
// 这就像一帧一帧的画面匀速地切换，以此来实现动画的效果。
// 但是，正是这种先用背景色覆盖组件再重绘图像的方式导致了闪烁。在两次看到不同位置小圆的中间时刻，
// 总是存在一个在短时间内被绘制出来的空白画面（颜色取背景色）。但即使时间很短，如果重绘的面积较大的话花去的时间也是比较可观的，
// 这个时间甚至可以大到足以让闪烁严重到让人无法忍受的地步。
// 另外，用paint(Graphics g)函数在屏幕上直接绘图的时候，由于执行的语句比较多，程序不断地改变窗体中正在被绘制的图象，
// 会造成绘制的缓慢，这也从一定程度上加剧了闪烁。
// 就像以前课堂上老师用的旧式的幻灯机，放完一张胶片，老师会将它拿下去，这个时候屏幕上一片空白，直到放上第二张，
// 中间时间间隔较长。当然，这不是在放动画，但上述闪烁的产生原因和这很类似。

