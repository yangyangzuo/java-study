package gui.label_button;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JRadioButton02 extends JApplet {
	public JRadioButton02() {
		Container contentPane = getContentPane();
		Icon icon = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/bulb.gif");
		JRadioButton[] radioButtons = new JRadioButton[] {
				new JRadioButton(),
				new JRadioButton(icon),
				new JRadioButton(icon, true),
				new JRadioButton("idea!"),
				new JRadioButton("idea!",true),
				new JRadioButton("idea!",icon),
				new JRadioButton("idea!",icon, true)
		};
		contentPane.setLayout(new FlowLayout());

		for(int i=0; i < radioButtons.length; ++i) {
			radioButtons[i].setBorderPainted(true);
			contentPane.add(radioButtons[i]);

			if(radioButtons[i].getIcon() != null) {
			System.out.println("setting selected icon");
				radioButtons[i].setSelectedIcon(
					new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/bulb_bright.gif"));
			}
		}
	}
}
