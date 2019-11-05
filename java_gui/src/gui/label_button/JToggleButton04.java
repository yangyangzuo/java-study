package gui.label_button;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JToggleButton04 extends JApplet {
	public JToggleButton04() {
		Container contentPane = getContentPane();
		//此类用于为一组按钮创建一个多斥（multiple-exclusion）作用域。
		//使用相同的 ButtonGroup 对象创建一组按钮意味着“开启”其中一个按钮时，将关闭组中的其他所有按钮。
		//这个功能和单选按钮类似
		ButtonGroup group = new ButtonGroup();

		JToggleButton[] buttons = new JToggleButton[] {
			new JToggleButton(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/ballot_box.gif")),
			new JToggleButton(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/palette.gif")),
			new JToggleButton(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/light_bulb1.gif")),
			new JToggleButton(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/dining.gif")),
			new JToggleButton(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/scissors.gif")),
			new JToggleButton(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/tricycle.gif")),
		};
		Box box = Box.createVerticalBox();
		
		for(int i=0; i < buttons.length; ++i) {
			group.add(buttons[i]);
			box.add(Box.createVerticalStrut(5));
			box.add(buttons[i]);
		}
		box.add(Box.createVerticalStrut(5));
		contentPane.add(box);
	}
}
