package gui.game;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 这里做了稍微的改动，把移动的部分放到了paint方法中去调用
 * 
 * 但是问题依旧,还是先卡一卡再平滑移动
 * 
 * @author Administrator
 * 
 */
public class GameControlDelay2 extends Frame implements Runnable
{
	private int x = 40, y = 50;
	private int keyCode;

	public GameControlDelay2()
	{
		this.setSize(1024, 768);
		this.addKeyListener(new KeyAdapter()
		{

			@Override
			public void keyPressed(KeyEvent e)
			{
				keyCode = e.getKeyCode();
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
		// 这里重置摁键，让他停止移动
		keyCode = -1;
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
		new GameControlDelay2();
	}
}
