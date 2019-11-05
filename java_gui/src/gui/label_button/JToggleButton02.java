package gui.label_button;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class JToggleButton02 extends JApplet {
	public JToggleButton02() {
		Container contentPane = getContentPane();
		ImageIcon icon = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/togglebuttonImage.gif");
		final JToggleButton button = new JToggleButton(icon);

		contentPane.setLayout(new FlowLayout());
		contentPane.add(button);

		//按钮选中或者取消选中的事件监听
		button.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int state = e.getStateChange();
				String s;

				if(state == ItemEvent.SELECTED) s = "selected";
				else s = "deselected";

				showStatus("Item Event: " + s);
			}
		});
	}
}
