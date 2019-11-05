package gui.image;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

public class Test2 extends Applet {
	/*
	 * Written by Jim Graham. This applet draws a big image scaled to its width
	 * and height as specified in the <APPLET> tag, and a small image scaled by
	 * the same ratio as the big image and positioned in the center of it.
	 */
	Image bigimg, smallimg;
	int smallx, smally, smallw, smallh;
	boolean sizeknown = false;
	boolean errored = false;

	public void init() {
		try {
			bigimg = Toolkit.getDefaultToolkit().createImage(new URL("http://a.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fe69291a015e6034a85edf72f9.jpg"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			smallimg = Toolkit.getDefaultToolkit().createImage(new URL("http://h.hiphotos.baidu.com/image/h%3D200/sign=4e41f0768a025aafcc3279cbcbecab8d/562c11dfa9ec8a13fc75e156ff03918fa0ecc01d.jpg"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		positionImages();
	}

	public boolean imageUpdate(Image theimg, int infoflags, int x, int y, int w, int h) {
		if ((infoflags & (ERROR)) != 0) {
			errored = true;
		}
		if ((infoflags & (WIDTH | HEIGHT)) != 0) {
			positionImages();
		}
		boolean done = ((infoflags & (ERROR | FRAMEBITS | ALLBITS)) != 0);
		// Repaint immediately if we are done, otherwise batch up
		// repaint requests every 100 milliseconds
		repaint(done ? 0 : 100);
		return !done;
	}

	public synchronized void positionImages() {
		Dimension d = size();
		int bigw = bigimg.getWidth(this);
		int bigh = bigimg.getHeight(this);
		smallw = smallimg.getWidth(this);
		smallh = smallimg.getHeight(this);
		if (bigw < 0 || bigh < 0 || smallw < 0 || smallh < 0) {
			return;
		}
		smallw = smallw * d.width / bigw;
		smallh = smallh * d.height / bigh;
		smallx = (d.width - smallw) / 2;
		smally = (d.height - smallh) / 2;
		sizeknown = true;
	}

	public synchronized void paint(Graphics g) {
		Dimension d = size();
		int appw = d.width;
		int apph = d.height;
		if (errored) {
			// The images had a problem - just draw a big red rectangle
			g.setColor(Color.red);
			g.fillRect(0, 0, appw, apph);
			return;
		}
		// Scale the big image to the width and height of the applet
		g.drawImage(bigimg, 0, 0, appw, apph, this);
		if (sizeknown) {
			// Scale the small image to the central region calculated above.
			g.drawImage(smallimg, smallx, smally, smallw, smallh, this);
		}
	}
}