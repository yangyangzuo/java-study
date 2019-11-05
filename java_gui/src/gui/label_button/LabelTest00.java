package gui.label_button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;


public class LabelTest00{
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(500,500);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		
		
		
		Label l1 = new Label("text");
		Label l2 = new Label("password");
		Label l3 = new Label("name",Label.RIGHT);//设置文本右对齐
		l1.setBounds(20, 20, 100, 100);l1.setBackground(Color.red);
		l2.setBounds(150, 150, 100, 100);l2.setBackground(Color.green);
		l3.setBounds(300, 300, 150, 150);l3.setBackground(Color.blue);
		l1.setAlignment(Label.LEFT);//设置文本左对齐
		l2.setAlignment(Label.CENTER);//设置文本居中对齐
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.setVisible(true);
	}
}
