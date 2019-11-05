package gui.opacity;

import java.awt.Color;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;
/**
 * 如何设置一个背景透明的窗体?
 * 
 * @author zuoyang
 *
 */
public class Opacity04 extends JFrame {

	public Opacity04() {
		//给面板设置一个红色背景
		this.getContentPane().setBackground(Color.red);
		this.setResizable(false);
		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		//要设置一个不透明的窗体需要2步
		//1.frame.setUndecorated(true)
		//2.AWTUtilities.setWindowOpaque(frame,false);
		
		Opacity04 alpha = new Opacity04();
		//1.设置是否装饰,即是否去掉标题栏等
		//注意如果不设置setUndecorated,则调用AWTUtilities的方法会抛出异常java.awt.IllegalComponentStateException: The frame is decorated
		alpha.setUndecorated(true);
		//2.使用AWTUtilities设置窗体透明
		AWTUtilities.setWindowOpacity(alpha, 0.2f);//设置透明度,窗体和窗体里面的东西都透明
		//或者
		//AWTUtilities.setWindowOpaque(alpha,false);//窗体背景透明
		alpha.setVisible(true);
	}

}