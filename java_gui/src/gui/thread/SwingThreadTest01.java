package gui.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SwingThreadTest01 extends JApplet {
	JProgressBar pb = new JProgressBar();
	public final JButton startButton = new JButton("start");

	public void init() {
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new FlowLayout());
		contentPane.add(startButton);
		contentPane.add(pb);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetInfoThread01 t = new GetInfoThread01(SwingThreadTest01.this);
				t.start();

				// this is ok, because actionPerformed
				// is called on the event dispatch thread
				//当前方法actionPerFormed()是在edt上执行的
				System.out.println("1:" + SwingUtilities.isEventDispatchThread());//true
				startButton.setEnabled(false);
			}
		});
	}

	public JProgressBar getProgressBar() {
		return pb;
	}
}

class GetInfoThread01 extends Thread {
	SwingThreadTest01 applet;

	public GetInfoThread01(SwingThreadTest01 applet) {
		this.applet = applet;
	}

	public void run() {
			try {
				System.out.println("连接服务器,开始获取数据..");
				Thread.currentThread().sleep(2000);
				int value = (int) (Math.random() * 100);
				System.out.println("获取到数据:" + value);
				
				
				// this is not ok, because it is not called
				// on the event dispatch thread
				//注意:当前方法不是在edt上执行的
				System.out.println("2:" + SwingUtilities.isEventDispatchThread());
				//这里要特别注意:虽然当前gui组件更新能更新操作，但是没有在edt上执行，很可能会发生不可预料的错误
				//当前的代码是错误的示范
				applet.getProgressBar().setValue(value);
				applet.startButton.setEnabled(true);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
}
