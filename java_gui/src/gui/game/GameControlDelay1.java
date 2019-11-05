package gui.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 问题的引入：在游戏设计过程中键盘事件线程可能不能及时响应.
 * 
 * 出现的情况是：摁下一个键后，物体移动一下，卡一下，在继续连续移动。
 * 
 * 解决方法： 在摁下键的时候只让物体改变方向，在定义一个函数中来改变物体位置的值，在paint方法中调用这个函数
 * 
 * 但是，这样也不能完全解决问题。 有这样一种情况： 如果你设定了摁一个键后，物体朝一个方向移动，设置的是让他一直移动，
 * 
 * 发现，他移动的很平滑， 并不会象刚开始摁的是时候卡一下 再平滑的移动(看GameControlDelay3)，所以这里在停止他移动的时候也用键盘监听，
 * 
 * 监听他摁键离开的时候在取消他的移动
 * 
 * 先看下面的例子，注意为了方便起见，例子中并没有用双缓冲
 * 
 * @author Administrator
 * 
 */
public class GameControlDelay1 extends Frame implements Runnable
{
	private int x = 40, y = 50;

	public GameControlDelay1()
	{
		this.setSize(1024, 768);
		this.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					x += 5;
				else if (e.getKeyCode() == KeyEvent.VK_DOWN)
					y += 5;
				else if (e.getKeyCode() == KeyEvent.VK_LEFT)
					x -= 5;
				else if (e.getKeyCode() == KeyEvent.VK_UP)
					y -= 5;
			}
		});
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		new Thread(this).start();
	}

	@Override
	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(x, y, 50, 50);
	}

	@Override
	public void run()
	{
		while (true)
		{
			try
			{
				Thread.sleep(30);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			this.repaint();

		}
	}

	public static void main(String[] args)
	{
		new GameControlDelay1();
	}
}
