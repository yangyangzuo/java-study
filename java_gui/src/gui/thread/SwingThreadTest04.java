package gui.thread;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class SwingThreadTest04 extends JApplet {
	private JProgressBar pb = new JProgressBar();

	public void init() {
		Container contentPane = getContentPane();
		final JButton startButton = new JButton("start");

		contentPane.setLayout(new FlowLayout());
		contentPane.add(startButton);
		contentPane.add(pb);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetInfoThread4 t = new GetInfoThread4(SwingThreadTest04.this);
				t.start();

				// this is not okay because invokeAndWait cannot
				// be called from the event dispatch thread
				//当前线程在edt上，edt线程上不能执行invodeAndWait()方法，否则会线程死锁
				try {
					SwingUtilities.invokeAndWait(new Runnable() {
						public void run() {
							startButton.setEnabled(false);
						}
					});
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	public JProgressBar getProgressBar() {
		return pb;
	}
}

class GetInfoThread4 extends Thread {
	Runnable runnable;
	int value;

	public GetInfoThread4(final SwingThreadTest04 applet) {
		runnable = new Runnable() {
			public void run() {
				JProgressBar pb = applet.getProgressBar();
				pb.setValue(value);
			}
		};
	}

	public void run() {
		while (true) {
			try {
				Thread.currentThread().sleep(500);

				// This is okay because the runnable's run()
				// is invoked on the event dispatch thread
				value = (int) (Math.random() * 100);
				SwingUtilities.invokeLater(runnable);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
