package gui.label_button;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.event.*;

public class JButtonTest05 extends JApplet {
	public void init() {
		Container contentPane = getContentPane();
		Icon icon = new StringIcon("icon for JButton"),
			 rolloverIcon = new StringIcon("rollover"),
			 pressedIcon = new StringIcon("pressed"),
			 disabledIcon = new StringIcon("disabled"),
			 selectedIcon = new StringIcon("selected"),
			 rolloverSelectedIcon = 
					new StringIcon("rollover selected"),
			 disabledSelectedIcon = 
			 		new StringIcon("disabled selected");

		final JButton button = new JButton();

		button.setRolloverEnabled(true);

		//abstarctButton的7种按钮图标
		button.setIcon(icon);
		button.setRolloverIcon(rolloverIcon);
		button.setRolloverSelectedIcon(rolloverSelectedIcon);
		button.setSelectedIcon(selectedIcon);
		button.setPressedIcon(pressedIcon);
		button.setDisabledIcon(disabledIcon);
		button.setDisabledSelectedIcon(disabledSelectedIcon);

		JComboBox cb = new JComboBox();
		cb.addItem("enabled");
		cb.addItem("disabled");
		
		cb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String item = (String)e.getItem();

					if(item.equals("enabled")) {
						button.setEnabled(true);
					}
					else {
						button.setEnabled(false);
					}
				}
			}
		});
		contentPane.setLayout(new FlowLayout());
		contentPane.add(cb);
		contentPane.add(button);
	}
}
class StringIcon implements Icon {
	private String s;

	public StringIcon(String s) {
		this.s = s;
	}
	public int getIconWidth() { return 100; }
	public int getIconHeight() { return 100; }

	public void paintIcon(Component c, Graphics g, int x, int y) {
		FontMetrics fm = g.getFontMetrics();
		g.setColor(c.getForeground());
		g.drawString(s, 10, fm.getHeight());
	}
}
