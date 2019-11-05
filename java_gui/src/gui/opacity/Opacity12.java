package gui.opacity;

import java.awt.Color;

import javax.swing.JFrame;

public class Opacity12 extends JFrame {

	public static void main(String[] args) {
		
		Opacity12 frame = new Opacity12();
		frame.setSize(212, 73);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		//在一个背景透明的窗体上显示一个背景透明的组件需要4步:
		
		//注意这个例子和Opacity05.java的区别,本例子使用的官方jdk7中添加的方法,Opacity05使用的是com.sun.awt.AWTUtilities
		
		//设置窗体背景透明
		//1.
		frame.setUndecorated(true);
		//2.
		frame.setBackground(new Color(0,0,0,0f));//窗体背景透明
		
		//3.
		MyPanel05 myPanel05 = new MyPanel05();
		myPanel05.setOpaque(false);
		//4
		frame.add(myPanel05);
//		frame.setContentPane(myPanel05);
		
		
		frame.setVisible(true);
	}
}