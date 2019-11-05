package gui.image;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Image102 extends Applet {
	public Image img = null;

	public void init() {

		long start = System.currentTimeMillis();
		System.out.println("加载图像开始...........");
		try {
			////////////////一次性把图形写入的第一种方法
			//bufferdImage，把图像完全加载到内存的一个缓冲区，加载的时间可能会长，但是写入的时候，可以一次性写入
			img = (Image)ImageIO.read(new URL("http://f.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fe8429ca47596034a85edf7246.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		//图像加载完，就可以得到图像的全部信息，比如：图相位，宽高等属性
		System.out.println("加载图像结束...........:" + (end-start) + ":" + img.getWidth(this) + ":" + img.getHeight(this));
		
	}

	@Override
	public void paint(Graphics g) {
		//绘制图片到面板
		//drawImage()方法
		//在任何情况下都立刻返回，甚至在图像尚未完整加载，并且还没有针对当前输出设备完成抖动和转换的情况下也是如此。
		//如果图像已经完整加载，并且其像素不再发生更改，则 drawImage 返回 true。否则，drawImage 返回 false，
		//并且随着更多的图像可用或者到了绘制动画另一帧的时候，加载图像的进程将通知指定的图像观察者。
		
		//根据打印的结果发现,只打印了一次,说明,paint()方法调用第一次的时候,图像数据就已经加载完了
		boolean r = g.drawImage(img, 0, 0, this);
		System.out.println("加载完成:" + r + "------" + img.getWidth(this) + ":" + img.getHeight(this));
	}

	
	
}
