package gui.label_button;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JToggleButton03 extends JApplet {
	public JToggleButton03() {
		Container contentPane = getContentPane();
		Icon icon = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/beach_umbrella.gif");
		JToggleButton button_1 = new JToggleButton(),
					  button_2 = new JToggleButton(icon),
					  button_3 = new JToggleButton(icon, true),
					  button_4 = new  JToggleButton("beach"),
					  button_5 = new  JToggleButton("beach",true),
					  button_6 = new  JToggleButton("beach",icon),
					  button_7 = new  JToggleButton("beach",icon,
					  									true);
		contentPane.setLayout(new FlowLayout());
		contentPane.add(button_1);
		contentPane.add(button_2);
		contentPane.add(button_3);
		contentPane.add(button_4);
		contentPane.add(button_5);
		contentPane.add(button_6);
		contentPane.add(button_7);
	}
}
