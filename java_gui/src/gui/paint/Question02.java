package gui.paint;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

/**
 * 
 * 如果一个重量容器,覆盖了paint()方法,则这个paint()方法里面必须调用super.pait()方法,否则这个容器里面的轻组件会不显示
 * @author zuoyang
 *
 */
public class Question02 extends Frame{
	public static void main(String[] args) {
		
		//Question2是一个重容器组件
		Question02 t = new Question02();
		t.setBounds(200, 200, 800, 800);
		t.setLayout(null);
		
		//当前容器中有一个轻组件
		JButton button = new JButton("hello");
		button.setBounds(0,200,200,200);
		t.add(button);
		//问题:
		//执行后,发现按钮没有显示,
		//1.把鼠标移动到按钮坐标上,你发现此时按钮显示了
		//2.缩放窗口,挡住按钮一部分,在放大窗口,发现按钮一部分显示不了
		
		Button button2 = new Button("hello2");
		button2.setBounds(330,200,200,200);
		t.add(button2);
		
		t.setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		//解决办法:
		//调用super.paint(g)方法,确保当前重容器重的轻量组件可以正确绘制
		super.paint(g);
		
		
		Image img = null;
		try {
			img = ImageIO.read(new File("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/paint/test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, this);
		System.out.println("ss");
		
		//解决办法:
		//调用super.paint(g)方法,确保当前重容器重的轻量组件可以正确绘制
//		 super.paint(g);
		
		
		//需要注意的是,只要调用super.paint(g)都可以确保重容器重的轻量组件被正常绘制出来,但是放置前面和后面是不一样的
		//分析过程：
		//super.paint()最终执行的是container.paint()方法
		
	}
}
