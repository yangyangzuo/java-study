package gui.gif;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AwtGifTest01 extends Frame {
	BufferedImage globe;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public static void main(String args[]) {
		Frame f = new AwtGifTest01();
		f.setSize(200, 200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	public AwtGifTest01() {
		super("globe");
			//注意：对于多帧图片，不能用bufferedImage来加载，bufferedImage只会加载显示第一帧
			try {
				globe = ImageIO.read(new File("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/gif/globe.gif"));
			} catch (IOException e) {
				e.printStackTrace();
			}

	}
	
	public void paint(Graphics g) {
	  	g.drawImage(globe,50,50,this);
	}

}
