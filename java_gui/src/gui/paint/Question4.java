package gui.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 * ????????
 * 如果一个重量容器组件,覆盖了paint()方法,则这个paint()方法里面必须调用super.pait()方法,否则这个容器里面的轻组件会不显示,或者显示异常
 * @author zuoyang
 *
 */
public class Question4 extends JFrame{
	public static void main(String[] args) {
		
		//Question1是一个重容器组件
		Question4 t = new Question4();
		t.setBounds(200, 200, 800, 800);
		t.setLayout(null);
		
		//当前容器中有一个轻组件
		JButton button = new JButton("hello");
		button.setBounds(0,300,200,200);
		t.add(button);
		//问题:
		//按钮显示,背景图片闪一下就消失了
		//为什么?

		t.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		Image img = null;
		try {
			img = ImageIO.read(new File("/home/zuoyang/workspace/java_gui/src/gui/paint/test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, this);
		
		
	}
}
