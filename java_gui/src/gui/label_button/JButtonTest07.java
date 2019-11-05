package gui.label_button;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JButtonTest07 extends JApplet {
	public JButtonTest07() {
		Container contentPane = getContentPane();
		JButton button = new JButton("button");

		button.setMargin(new Insets(50,25,10,5));
		contentPane.setLayout(new FlowLayout());
		contentPane.add(button);
	}
}
