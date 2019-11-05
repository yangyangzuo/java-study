package gui.gif;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AwtGifTest02 extends Frame {
	Image globe;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public static void main(String args[]) {
		Frame f = new AwtGifTest02();
		f.setSize(200, 200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	public AwtGifTest02() {
		super("globe");
			//注意：对于多帧图片，不能用bufferedImage来加载，bufferedImage只会加载显示第一帧
			globe = Toolkit.getDefaultToolkit().createImage("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/gif/globe.gif");
		try {
			MediaTracker mt = new MediaTracker(this);
			mt.addImage(globe,0);
			mt.waitForID(0);
		}
		catch(Exception e) { e.printStackTrace(); }

	}
	//图片虽然可以显示出来，但是显示的太快了
	public void paint(Graphics g) {
	  	g.drawImage(globe,50,50,this);
	}
}
