package gui.paint;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * ????????
 * 如果一个重量容器组件,覆盖了paint()方法,则这个paint()方法里面必须调用super.pait()方法,否则这个容器里面的轻组件会不显示,或者显示异常
 * @author zuoyang
 *
 */
public class Question5 extends JFrame{
	public static void main(String[] args) {
		
		//Question1是一个重容器组件
		Question5 t = new Question5();
		t.setBounds(200, 200, 800, 800);
		t.setLayout(null);
		
		//当前容器中有一个轻组件
		JButton button = new JButton("hello");
		button.setBounds(0,300,200,200);
		t.add(button);

		
		t.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		//背景图片和按钮都正常显示
		//为什么?
		super.paint(g);
		
		Image img = null;
		try {
			img = ImageIO.read(new File("/home/zuoyang/workspace/java_gui/src/gui/paint/test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, this);
		
	}
}
