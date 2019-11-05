package gui.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*; // for InvocationTargetException

public class SwingThreadTest03 extends JApplet {
	private JProgressBar pb = new JProgressBar();
	public static final JButton startButton = new JButton("start");
	
	public void init() {
		Container contentPane = getContentPane();
		
		contentPane.setLayout(new FlowLayout());
		contentPane.add(startButton);
		contentPane.add(pb);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetInfoThread3 t = new GetInfoThread3(SwingThreadTest03.this);
				t.start();

				// this is okay because actionPerformed
				// is called on the event dispatch thread
				startButton.setEnabled(false);
			}
		});
	}

	public JProgressBar getProgressBar() {
		return pb;
	}
}

class GetInfoThread3 extends Thread {
	Runnable getValue, setValue;
	int value, currentValue;

	public GetInfoThread3(final SwingThreadTest03 applet) {
		getValue = new Runnable() {
			public void run() {
				JProgressBar pb = applet.getProgressBar();
				
				try {
					System.out.println("开始获取进度条的值.......");
					Thread.currentThread().sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				currentValue = pb.getValue();
				System.out.println("获得进度条的值:" + currentValue);
			}
		};
		setValue = new Runnable() {
			public void run() {
				JProgressBar pb = applet.getProgressBar();
				pb.setValue(value);
				System.out.println("进度条的值设置为目标值,设置完毕");
			}
		};
	}

	public void run() {
			try {
				Thread.currentThread().sleep(500);

				// This is okay because the getValue's run()
				// is invoked on the event dispatch thread
				//进度条，目标值
				value = (int) (Math.random() * 100);
				System.out.println("进度条的目标值:" + value);
				
				//当前线程会阻塞，一直等到getValue对象中run()方法执行完，当前线程才会向下执行
				//这里可以解决一个问题:需要获取一个数据，获取需要时间，获取完后，再进行其他操作
				//但是这个代码是错误的，这是一个耗时的操作，不能放在edt线程中
				//这里吧一个耗时的操作放到了edt线程中，导致了程序无响应
				SwingUtilities.invokeAndWait(getValue);

				//如果当前进度条的值和要设置的目标值不一样，则重新把进度条设置为目标值
				if (currentValue != value) {
					System.out.println("当前进度条的值是" + currentValue + ",目标值是" + value);
					System.out.println("此时，要设置的值和目标值不一样，需要把进度条设置为目标值");
					SwingUtilities.invokeLater(setValue);
				}
				
				//恢复按钮状态
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						SwingThreadTest03.startButton.setEnabled(true);
					}
				});
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
