package gui.opacity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;
public class Opacity05 extends JFrame {

	public Opacity05() {
		this.setSize(212, 73);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	//参考文档:
	//Swing第三刀：做套ERP，要配得上我的登录界面！
	//http://servasoft.com/ui%E5%BC%80%E5%8F%91/swing%E7%AC%AC%E4%BA%8C%E5%88%80%EF%BC%9A%E6%9E%9D%E9%97%B4%E6%96%B0%E7%BB%BF%E4%B8%80%E9%87%8D%E9%87%8D-2.html
	public static void main(String[] args) {
		
		//在一个背景透明的窗体上显示一个背景透明的组件需要4步:
		//设置窗体背景透明
		//1.frame.setUndecorated(true)
		//2.AWTUtilities.setWindowOpaque(frame,false);
		//设置组件背景透明
		//3.JComponent.setOpaque(false)
		//把背景透明的组件添加到背景透明的窗体上
		//4.frame.add(JComponent);或者frame.setContentPane()
		
		
		//注意这个例子和Opacity12.java的区别,Opacity12.java使用的官方jdk7中添加的方法
		
		Opacity05 alpha = new Opacity05();
		//1.设置是否装饰,即是否去掉标题栏等
		//注意如果不设置setUndecorated,则调用AWTUtilities的方法会抛出异常java.awt.IllegalComponentStateException: The frame is decorated
		alpha.setUndecorated(true);
		//2.使用AWTUtilities设置窗体背景透明
		AWTUtilities.setWindowOpaque(alpha,false);
		//3.把要显示的内容添加到背景透明的窗体上
		MyPanel05 myPanel05 = new MyPanel05();
		//4.要显示组件自身的背景透明
		myPanel05.setOpaque(false);
		alpha.add(myPanel05);
		//alpha.setContentPane(myPanel05);
		
		alpha.setVisible(true);
	}
}


class MyPanel05 extends JPanel {
	public MyPanel05() {
		this.setBackground(Color.GREEN);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image = null;
		image = Toolkit.getDefaultToolkit().getImage("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/opacity/login_logo.png");
		g.drawImage(image, 0, 0, this);
	}
}