package gui.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 这里把对键盘的松开操作放在了键盘的keyReleased监听里面了，移动相当平滑还不卡
 * 
 * 其实这个利用了画图的paint()方法的线程时间问题，物体连续的移动放在了paint()方法中，且线程paint()一直在执行，
 * 
 * 只是根据特定的时间让他停止，所以执行的时候间隙很短，几乎看不出来卡的效果
 * 
 * 
 * @author Administrator
 * 
 */
public class GameControlDelay3 extends Frame implements Runnable
{
	private int x = 40, y = 50;
	private int keyCode;

	public GameControlDelay3()
	{
		this.setSize(1024, 768);
		this.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyPressed(KeyEvent e)
			{
				keyCode = e.getKeyCode();
			}

			@Override
			public void keyReleased(KeyEvent e)
			{
				// 这里重置摁键，让他停止移动
				keyCode = -1;
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
		move();
		g.setColor(Color.RED);
		g.fillRect(x, y, 50, 50);
	}

	public void move()
	{
		if (keyCode == KeyEvent.VK_RIGHT)
			x += 5;
		else if (keyCode == KeyEvent.VK_DOWN)
			y += 5;
		else if (keyCode == KeyEvent.VK_LEFT)
			x -= 5;
		else if (keyCode == KeyEvent.VK_UP)
			y -= 5;

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
		new GameControlDelay3();
	}
}
