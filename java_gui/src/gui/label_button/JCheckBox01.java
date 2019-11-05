package gui.label_button;

import javax.swing.*; 
import java.awt.*;
import java.awt.event.*;

public class JCheckBox01 extends JApplet {
	public void init() {
		Container contentPane = getContentPane();
		contentPane.add(new PrintOptionsPanel(this), "Center");
	}
}
class PrintOptionsPanel extends JPanel {
	JCheckBox oddPages, evenPages, collate, lastFirst;
	Listener  listener = new Listener();
	JApplet   applet;

	public PrintOptionsPanel(JApplet applet) {
		this.applet = applet;
		oddPages  = new JCheckBox("Odd Pages");
		evenPages = new JCheckBox("Even Pages");
		collate   = new JCheckBox("Collate");
		lastFirst = new JCheckBox("Last Page First");

		oddPages.addItemListener (listener);
		evenPages.addItemListener(listener);
		collate.addItemListener  (listener);
		lastFirst.addItemListener(listener);

		add(oddPages);  
		add(evenPages);
		add(collate); 
		add(lastFirst);
	}
	class Listener implements ItemListener {
		public void itemStateChanged(ItemEvent event) {
			applet.showStatus(
				"Odd Pages: "  + oddPages.isSelected()  + ",  " +
				"Even Pages: " + evenPages.isSelected() + ",  " +
				"Collate: "    + collate.isSelected()   + ",  " +
				"Last Page First: " + lastFirst.isSelected());
		}
	}
}
