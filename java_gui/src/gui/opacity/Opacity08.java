package gui.opacity;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

public class Opacity08 extends JFrame {

	public Opacity08() {
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		final Opacity08 jframe = new Opacity08();
		
		System.out.println(AWTUtilities.getWindowShape(jframe));
		
		//设定特定形状的窗体
		 jframe.addComponentListener(new ComponentAdapter() {
		     @Override
		     public void componentResized(ComponentEvent evt) {
		       Shape shape = null;
		       shape = new Ellipse2D.Float(0, 0, jframe.getWidth(), jframe.getHeight());
		       AWTUtilities.setWindowShape(jframe, shape);
		       //AWTUtilities.setWindowShape(jframe, null);//如果传递是shape是null,则窗体是默认的矩形
		       System.out.println(AWTUtilities.getWindowShape(jframe));
		     }
		});

		 //推荐使用componentresized()方法,可以根据当前的窗口大小,进行精确的设置形状。
		 //需要注意的是,当对窗体设置一个特定形状时,
		 //1.需要使用undecorated窗体
		 //2.窗体不能是全屏模式
		 //getWindowShape()可以获得当前窗体的形状
		 //Shape currentShape = AWTUtilities.getWindowShape(frame);
		 //如果窗体还没有设置对应的shape,则该方法返回null;
		 
		 
		 jframe.setUndecorated(true);
		 jframe.setVisible(true);
		 System.out.println(AWTUtilities.getWindowShape(jframe));
		
		
	}
}
