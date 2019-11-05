package gui.layout;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Insets;

/**
 * borderlayout
 * 
 * @author zuoyang
 *
 */
public class Layout02 extends Frame
{
	public static void main(String[] args) {
		Layout02 f = new Layout02();
//		f.setUndecorated(true);
		
		BorderLayout borderLayout = new BorderLayout(20,20);//组件之间的空白区
		//或者
//		borderLayout.setVgap(20);
//		borderLayout.setHgap(20);
		f.setLayout(borderLayout);
		
		f.add(new Button("NORTH"),BorderLayout.NORTH);
		f.add(new Button("EAST"),BorderLayout.EAST);
		f.add(new Button("SOUTH"),BorderLayout.SOUTH);
		f.add(new Button("WEST"),BorderLayout.WEST);
		f.add(new Button("CENTER"),BorderLayout.CENTER);
		f.setSize(800,600);
		
		
		f.setVisible(true);
	}
	
	

	@Override
	public void paint(Graphics g) {
		Dimension size = this.getSize();
		//绘制上面空白区域
		g.setColor(Color.red);
		g.fillRect(0, 0, size.width, this.getInsets().top);
		//绘制下面空白区域
		g.setColor(Color.BLACK);
		g.fillRect(0, size.height-this.getInsets().top, size.width, size.height - this.getInsets().top);
		super.paint(g);
	}



	//框架容器内，组件可放置区距离框架边框，上下左右的空白区
	@Override
	public Insets getInsets() {
		return new Insets(100,100,100,100);
	}
		
	
		
}
