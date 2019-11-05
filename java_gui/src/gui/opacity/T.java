package gui.opacity;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;


public class T {
	public static void main(String[] args) {
		JFrame f = new JFrame("ss");
		f.getContentPane().setLayout(null);
		JButton config = new JButton(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/nevermore/images/config.png"));
//		JButton config = new JButton("aa");
		config.setBounds(40, 40, 200, 200);
//		config.setContentAreaFilled(false);
		config.setBorderPainted(false);
//		config.setOpaque(false);
		
		f.add(config);
		f.setUndecorated(true);
		f.setBackground(new Color(0,0,0,0));
		
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
}
