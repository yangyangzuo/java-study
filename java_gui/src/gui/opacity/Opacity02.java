package gui.opacity;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.awt.AWTUtilities;

public class Opacity02 extends JFrame {

	public Opacity02() {
		//打印查看contentpane的颜色
		System.out.println(this.getContentPane().getForeground());
		System.out.println(this.getContentPane().getBackground());
		this.getContentPane().setBackground(Color.red);
		this.getContentPane().setForeground(Color.green);
		
		JLabel label  = new JLabel("hello");
		label.setForeground(Color.yellow);
		this.add(label);
		
		this.setSize(200, 100);
		this.setLocationRelativeTo(null);
		//此时可以看到显示的是红色
	}

	public static void main(String[] args) {
		Opacity02 alpha = new Opacity02();
		alpha.setVisible(true);
	}

}