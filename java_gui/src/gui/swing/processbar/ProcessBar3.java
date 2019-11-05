package gui.swing.processbar;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ProcessBar3 extends JApplet {
	private JProgressBar pb = new JProgressBar();

	public void init() {
		Container contentPane = getContentPane();
		final JButton startButton = new JButton("start");

		contentPane.setLayout(new FlowLayout());
		contentPane.add(startButton);
		contentPane.add(pb);

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				(new GetInfoThread(pb)).start();
			}
		});
		
		//为进度条添加时间监听
		pb.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int min = pb.getMinimum(), max = pb.getMaximum();
				int value = pb.getValue();

				showStatus("Min: " + min + ", Max: " + max +
							", Value: " + value);
			}
		});
		
		
	}
}
class GetInfoThread extends Thread {
	Runnable update, finish;
	JProgressBar pb;
	int value, min, max, increment;

	public GetInfoThread(JProgressBar progressBar) {
		pb = progressBar;

		max = pb.getMaximum(); 
		min = pb.getMinimum();

		update = new Runnable() {
			public void run() {
				value = pb.getValue() + increment;
				pb.setValue(value);
			}
		};
		finish = new Runnable() {
			public void run() {
				value = min;
				pb.setValue(value);
			}
		};
	}
	public void run() {
		while(value + increment <= max) {
			simulateTimeConsumingActivity();
			SwingUtilities.invokeLater(update);
		}
		SwingUtilities.invokeLater(finish);
	}
	private void simulateTimeConsumingActivity() {
		try {
			Thread.currentThread().sleep(1000);
			increment = (max - min) / 10;
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}
