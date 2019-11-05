package gui.label_button;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ButtonTest00 {

	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setLayout(new FlowLayout());

		///设置按钮的文本值
		Button button1 = new Button("通过构造方法设置的");
		Button button2 = new Button();
		button2.setLabel("通过setLabel()设置的");
		//构造方法默认设置的是label的值,可以查看jdk源代码
		System.out.println(button1.getLabel() + ":" + button2.getLabel());
		
		f.add(button1);
		f.add(button2);
		
		
		//按钮是否被禁用?
		//Component的方法:disable()方法可以禁用一个组件，enable()可以激活一个组件,但是这两个方法都过期了
		//用Component的setEnable(boolean value)来代替
		//button1.disable();
		//button2.enable();
		button1.setEnabled(false);
		button2.setEnabled(true);
				
		f.setVisible(true);
	}

}
