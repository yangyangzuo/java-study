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
public class Question03 extends Frame{
	public static void main(String[] args) {
		
		//Question1是一个重容器组件
		Question03 t = new Question03();
		t.setBounds(200, 200, 800, 800);
		t.setLayout(null);
		
		//当前容器中有一个轻组件
		JButton button = new JButton("hello");
		button.setBounds(0,300,200,200);
		t.add(button);
		//问题:
		//执行后,发现按钮没有显示,
		//1.把鼠标移动到按钮坐标上,你发现此时按钮显示了
		//2.缩放窗口,挡住按钮一部分,在放大窗口,发现按钮一部分显示不了
		
		
		
		t.setVisible(true);
	}
	@Override
	public void paint(Graphics g) {
		
		//解决办法:
		//调用super.paint(g)方法,确保当前重容器重的轻量组件可以正确绘制
//		super.paint(g);
		
		
		Image img = null;
		try {
			img = ImageIO.read(new File("/home/zuoyang/workspace/java_gui/src/gui/paint/test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, this);
		
		
		//解决办法:
		//调用super.paint(g)方法,确保当前重容器重的轻量组件可以正确绘制
		 super.paint(g);
		
		
		//需要注意的是,只要调用super.paint(g)都可以确保重容器重的轻量组件被正常绘制出来,但是放置前面和后面还是不一样的
		//当绘制的组件和paint()方法重绘制的内容有重叠时,后调用super.paint(),此时轻组件会覆盖在你绘制的图像上面
		//如果我们先绘制调用super.paint()方法,绘制轻组件,则后来我们绘制的自己的东西会覆盖轻量组件,但是重组件却不受这个影响
		//问题分析:
		//1.如果先调用super.paint(g);最终会调用轻组件的paint()方法,把轻组件绘制出来
		//2.代码向下执行g.drawImage(img, 0, 0, this);此时图片被绘制,覆盖前面绘制的轻组件
		//3.具体的执行过程可以通过debug分析,可以清除看到先绘制什么,后绘制什么
		 
		 //在一个重容器里面放置轻组件和重组件，会出现各种问题，最好不要这样使用
		
	}
}
