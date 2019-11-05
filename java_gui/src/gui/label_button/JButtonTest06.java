package gui.label_button;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.WindowConstants;

// an example of implementing a custom UI (and border) to modify
// the look and feel for a JButton, creating a new kind of button
// in the process.

public class JButtonTest06 extends JApplet {
	public void init() {
		Container contentPane = getContentPane();
		Icon icon = new StringIcon2("icon"),
			 rolloverIcon = new StringIcon2("rollover"),
			 pressedIcon = new StringIcon2("pressed"),
			 disabledIcon = new StringIcon2("disabled"),
			 selectedIcon = new StringIcon2("selected"),
			 rolloverSelectedIcon = 
					new StringIcon2("rollover selected"),
			 disabledSelectedIcon = 
			 		new StringIcon2("disabled selected");

		final JToggleButton tbutton = new JToggleButton();
		final JButton button = new JButton();

		button.setRolloverEnabled(true);
		tbutton.setRolloverEnabled(true);

		button.setIcon(icon);
		button.setRolloverIcon(rolloverIcon);
		button.setRolloverSelectedIcon(rolloverSelectedIcon);
		button.setSelectedIcon(selectedIcon);
		button.setPressedIcon(pressedIcon);
		button.setDisabledIcon(disabledIcon);
		button.setDisabledSelectedIcon(disabledSelectedIcon);

		tbutton.setIcon(icon);
		tbutton.setRolloverIcon(rolloverIcon);
		tbutton.setRolloverSelectedIcon(rolloverSelectedIcon);
		tbutton.setSelectedIcon(selectedIcon);
		tbutton.setPressedIcon(pressedIcon);
		tbutton.setDisabledIcon(disabledIcon);
		tbutton.setDisabledSelectedIcon(disabledSelectedIcon);

		JComboBox cb = new JComboBox();
		cb.addItem("enabled");
		cb.addItem("disabled");

		cb.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					String item = (String)e.getItem();

					if(item.equals("enabled")) {
						button.setEnabled(true);
						tbutton.setEnabled(true);
					}
					else {
						button.setEnabled(false);
						tbutton.setEnabled(false);
					}
				}
			}
		});
		contentPane.setLayout(new FlowLayout());
		contentPane.add(cb);
		contentPane.add(button);
		contentPane.add(tbutton);
	}
	public static void main(String args[]) {
		final JFrame f = new JFrame();
		JApplet applet = new JButtonTest06();

		applet.init();

		f.setContentPane(applet.getContentPane());
		f.setBounds(100,100,300,150);
		f.setTitle("Test");
		f.setVisible(true);

		f.setDefaultCloseOperation(
			WindowConstants.DISPOSE_ON_CLOSE);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
class StringIcon2 implements Icon {
	private String s;

	public StringIcon2(String s) {
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
