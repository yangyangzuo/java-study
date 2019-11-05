package gui.swing.processbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProcessBar2 extends JApplet {
	private JProgressBar progressBar = new JProgressBar();
	private JButton startButton = new JButton("start");

	public void init() {
		Container contentPane = getContentPane();

		contentPane.setLayout(new FlowLayout());
		contentPane.add(startButton);
		contentPane.add(progressBar);

		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.yellow);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new UpdateThread()).start();
			}
		});
	}
	class UpdateThread extends Thread {
		Runnable update, finish;
		int value, min, max, increment;

		public UpdateThread() {
			max = progressBar.getMaximum(); 
			min = progressBar.getMinimum();

			update = new Runnable() {
				public void run() {
					value = progressBar.getValue() + increment;
					updateProgressBar(value);
				}
			};
			finish = new Runnable() {
				public void run() {
					updateProgressBar(min);
				}
			};
		}
		public void run() {
			startButton.setEnabled(false);

			while(value + increment <= max) {
				simulateTimeConsumingActivity();
				SwingUtilities.invokeLater(update);
			}
			SwingUtilities.invokeLater(finish);
			startButton.setEnabled(true);
		}
		private void updateProgressBar(int value) {
			progressBar.setValue(value);
		}
		private void simulateTimeConsumingActivity() {
			try {
				Thread.currentThread().sleep(100);
				increment = (max - min) / 100;
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
