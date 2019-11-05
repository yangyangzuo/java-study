package gui.label_button;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JButtonTest04 extends JApplet {
	int clickDuration = 68;

	public JButtonTest04() {
		Container contentPane = getContentPane();
		JPanel controlPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		JButton doClick = new JButton("do click");
		final JButton clickMe = new JButton("click me");

		final JComboBox comboBox = new JComboBox(new Object[] {
				"68", "250", "500", "750", "1000"
		});

		controlPanel.add(new JLabel("Click Duration:"));
		controlPanel.add(comboBox);

		buttonPanel.add(doClick);
		buttonPanel.add(clickMe);

		contentPane.add(controlPanel, BorderLayout.NORTH);
		contentPane.add(buttonPanel, BorderLayout.CENTER);

		getRootPane().setDefaultButton(doClick);

		doClick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//执行按钮clickMe上的点击事件,里面的参数的值，表示按钮在按下的状态下持续的时间
				clickMe.doClick(clickDuration);
			}
		});

		//获取延迟的事件
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					clickDuration = Integer.parseInt((String)
									comboBox.getSelectedItem());
				}
			}
		});
	}
}
