package gui.image;

import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.*;
import java.awt.image.ImageProducer;
import java.awt.event.*;

public class LoadResource extends Applet {
	Image im;

	public void start() {
		URL url = null;
		try {
			url = new URL("http://a.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fe69291a015e6034a85edf72f9.jpg");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println(url);
			im = createImage((ImageProducer)url.getContent());
			if(im == null)
				System.out.println("null image");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void paint(Graphics g) {
		Insets insets = getInsets();
		g.drawImage(im, insets.left, insets.top, this);
	}
	public void update(Graphics g) {
		paint(g);
	}
}
