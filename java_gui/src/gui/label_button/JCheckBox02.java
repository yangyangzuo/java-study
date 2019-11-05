package gui.label_button;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JCheckBox02 extends JApplet {
	public JCheckBox02() {
		Container contentPane = getContentPane();
		Icon icon = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/bulb.gif");
		JCheckBox[] checkboxes = new JCheckBox[] {
				new JCheckBox(),
				new JCheckBox(icon),
				new JCheckBox(icon, true),
				new JCheckBox("idea!"),
				new JCheckBox("idea!",true),
				new JCheckBox("idea!",icon),
				new JCheckBox("idea!",icon, true)
		};
		contentPane.setLayout(new FlowLayout());

		for(int i=0; i < checkboxes.length; ++i) {
//			checkboxes[i].setBorderPainted(true);
			contentPane.add(checkboxes[i]);

			if(checkboxes[i].getIcon() != null) {
			System.out.println("setting selected icon");
				checkboxes[i].setSelectedIcon(
					new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/bulb_bright.gif"));
			}
		}
	}
}
