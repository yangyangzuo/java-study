package gui.label_button;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JToggleButton01 extends JApplet {
	public JToggleButton01() {
		Container contentPane = getContentPane();
		ImageIcon icon = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/togglebuttonImage.gif");
		//JToggleButton是一种具有选取和取消选取状态的按钮，该类继承了AbstractButton,
		//单选框JRadioButton和复选框JCheckBox又集成了JToggleButton
		JToggleButton button = new JToggleButton(icon);
		//设置一个被选中后的按钮的图标
		button.setSelectedIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/clipboard.gif"));
		
		//当移动到一个被选中的按钮上面时，设置一个图像
		button.setRolloverSelectedIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/icon.gif"));
		contentPane.setLayout(new FlowLayout());
		contentPane.add(button);
	}
}
