package gui.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingThreadTest02 extends JApplet {
	JProgressBar pb = new JProgressBar();
	public final JButton startButton = new JButton("start");
	
	public void init() {
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new FlowLayout());
		contentPane.add(startButton);
		contentPane.add(pb);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetInfoThread2 t = new GetInfoThread2(SwingThreadTest02.this);
				t.start();

				// this is ok, because actionPerformed
				// is called on the event dispatch thread
				System.out.println("1:" + SwingUtilities.isEventDispatchThread());
				startButton.setEnabled(false);
			}
		});
	}

	public JProgressBar getProgressBar() {
		return pb;
	}
}

class GetInfoThread2 extends Thread {
	Runnable runnable;
	int value;

	public GetInfoThread2(final SwingThreadTest02 applet) {
		runnable = new Runnable() {
			public void run() {
				System.out.println("3:" + SwingUtilities.isEventDispatchThread());
				applet.getProgressBar().setValue(value);
				applet.startButton.setEnabled(true);
			}
		};
	}

	public void run() {
		try {
			System.out.println("连接服务器,开始获取数据..");
			Thread.currentThread().sleep(2000);
			value = (int) (Math.random() * 100);
			System.out.println("获取到数据:" + value);
			
			System.out.println("2:" + SwingUtilities.isEventDispatchThread());
			
			//这样做才是正确的做法
			SwingUtilities.invokeLater(runnable);//把更新gui组件操作放到edt队列中
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
