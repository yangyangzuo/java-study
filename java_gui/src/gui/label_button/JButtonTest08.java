package gui.label_button;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JButtonTest08 extends JApplet {
	public JButtonTest08() {
		Container contentPane = getContentPane();
		JButton button = new JButton("button With Mnemonic");

		button.setMnemonic('M');

		contentPane.setLayout(new FlowLayout());
		contentPane.add(button);
	}
}
