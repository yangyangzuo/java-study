package gui.windowFrameDialog;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * 程序加载前的闪屏
 * 
 * @author gudandan
 *
 */
public class WindowTest4 extends Frame {
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Window window;
	Image image;

	static public void main(String[] args) {
		new WindowTest4();
	}

	public WindowTest4() {
		ImageCanvas canvas;

		window = new Window(null);
		image = toolkit.getImage("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/windowFrameDialog/saint.gif");
		canvas = new ImageCanvas(image);

		window.add(canvas, "Center");

		int imgWidth = image.getWidth(this), imgHeight = image.getHeight(this);
		window.setSize(imgWidth, imgHeight);
		// window.pack();这里没有使用pack()方法，是因为solaris系统下，该方法无法自动设置窗体的大小，在window系统下可以，在其他linux下未测试
		window.setVisible(true);
		window.toFront();// 把窗口置于所有窗口的最前端，并且获得焦点
		window.setLocationRelativeTo(null);// 把窗体放置在屏幕的中央，该方法要放在window.setsize()方法之后，因为该方法计算坐标的时候需要使用窗体的大小

		try {
			Thread.currentThread().sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 闪屏结束后显示主窗体
		Frame f = new Frame();
		f.setBounds(0, 0, 300, 300);
		f.setBackground(Color.red);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

		window.dispose();// 退出程序之前，隐藏窗口并释放窗口占用的资源
		// System.exit(0);
	}
}

class ImageCanvas extends Canvas {
	private Image image;

	public ImageCanvas(Image image) {
		MediaTracker mt = new MediaTracker(this);
		mt.addImage(image, 0);

		try {
			mt.waitForID(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.image = image;
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
}
