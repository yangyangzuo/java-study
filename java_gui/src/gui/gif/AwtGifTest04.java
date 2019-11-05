package gui.gif;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AwtGifTest04 extends Frame {
	Image globe;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public static void main(String args[]) {
		Frame f = new AwtGifTest04();
		f.setSize(200, 200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	public AwtGifTest04() {
		super("globe");
			globe = Toolkit.getDefaultToolkit().createImage("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/gif/globe.gif");
//		try {
//			MediaTracker mt = new MediaTracker(this);
//			mt.addImage(globe,0);
//			mt.waitForID(0);
//		}
//		catch(Exception e) { e.printStackTrace(); }
	}
	
	@Override
	public boolean imageUpdate(Image image, int flags, 
								int x, int y, int w, int h) {
		System.out.println(flags);
		//我们通过在这里设置一个延迟,来控制图片的帧的变换速度，从而解决gif图片的变化速率
		//这里添加一个限制，当gif动画的帧可以绘制的时候，在延迟变换的间隔
		if((flags & FRAMEBITS) != 0) {
			try {
				Thread.currentThread().sleep(200);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			repaint();
		}
	  	return true;
	}
	public void paint(Graphics g) {
	  	g.drawImage(globe,50,50,this);
	}
	//这里放置图片闪烁，可以覆盖update()方法
	public void update(Graphics g) {
		paint(g);
	}

}
