package gui.swing.processbar;

import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;

import java.awt.*;
import java.awt.event.*;

public class ProcessBar1 extends JApplet {
	private JProgressBar[] progressBars = {
			new JProgressBar(),
			new JProgressBar(),
			new JProgressBar(),
			new JProgressBar()
	};

	public void init() {
		Container contentPane = getContentPane();

		contentPane.setLayout(new FlowLayout());

		for(int i=0; i < progressBars.length; ++i) {
			JProgressBar pb = progressBars[i];

			pb.setUI(new BasicProgressBarUI());
			
			if(i == 0) {
				pb.setStringPainted(true);
				pb.setString("加载23%");//显示自定义字符串
			}
			if(i == 1) {
				pb.setOrientation(JProgressBar.VERTICAL);
				pb.setForeground(Color.yellow);
				pb.setMaximum(1000);
				pb.setValue(50);
				pb.setBorder(
					BorderFactory.createRaisedBevelBorder());
			}
			if(i == 2) {
				pb.setForeground(Color.blue);
				pb.setBorderPainted(false);
				pb.setValue(50);
				pb.setStringPainted(true);
			}
			if(i == 3) {
				pb.setOrientation(JProgressBar.VERTICAL);
				pb.setForeground(Color.red);
				pb.setValue(90);
				pb.setStringPainted(true);
				pb.setBorder(
					BorderFactory.createEtchedBorder());
			}
			contentPane.add(pb);
		}
	}
}
