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
 * @author zuoyang
 *
 */
public class Question6 extends JFrame{
	public static void main(String[] args) {
		
		Question6 t = new Question6();
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
		
		
		Image img = null;
		try {
			img = ImageIO.read(new File("/home/zuoyang/workspace/java_gui/src/gui/paint/test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, this);
	
		
		//问题:
		//按钮显示,背景图片闪一下就消失了
		//为什么?
		super.paint(g);
	}
}
