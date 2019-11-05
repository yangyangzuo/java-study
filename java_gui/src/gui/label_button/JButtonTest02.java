package gui.label_button;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JRootPane;

public class JButtonTest02 extends JApplet {
	public JButtonTest02() {
		Container contentPane = getContentPane();
		JRootPane rootPane = getRootPane();
		JButton def = new JButton("default button");
		JButton reg = new JButton("regular button");
		
		rootPane.setDefaultButton(def);
		//设置为当前窗体的默认按钮，回车/空格可以直接激活 
		//回车键/空格键可以激活获得焦点的按钮,回车键只要按下就会一直激活，空格键按下后弹起才会触发一次激活
		contentPane.setLayout(new FlowLayout());
		contentPane.add(def);
		contentPane.add(reg);
	}
}
