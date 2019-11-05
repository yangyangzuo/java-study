package gui.image;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.net.URL;

import javax.imageio.ImageIO;

public class Image107 extends Applet {
	public Image img = null;
	public ImageObserver observer;
	public void init() {

		long start = System.currentTimeMillis();
		System.out.println("加载图像开始...........");
		try {
			img = Toolkit.getDefaultToolkit().createImage(new URL("http://a.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fe69291a015e6034a85edf72f9.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		//图像加载完，就可以得到图像的全部信息，比如：图相位，宽高等属性
		System.out.println("加载图像结束...........:" + (end-start) + ":" + img.getWidth(this) + ":" + img.getHeight(this));
		
		
		//我们这里自己定义一个观察者
		observer = new ImageObserver() {
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				System.out.println(infoflags + ":" + x + ":" + y + ":" + width + ":" + height);
				if(infoflags != this.ALLBITS){
					//数据没有完全加载完,或者加载出错,需要持续加载
					return true;
				}else{
					//不做增量绘制,当所有图像数据都加载完成后,再绘制到屏幕上
					//此时根据打印的日志可以看出来,paint()方法只在最后调用了一次
					repaint();
					return false;//图像被完全加载了
				}
			}
		};
		
	}

	@Override
	public void paint(Graphics g) {
		//绘制图片到面板
		//drawImage()方法
		//在任何情况下都立刻返回，甚至在图像尚未完整加载，并且还没有针对当前输出设备完成抖动和转换的情况下也是如此。
		//如果图像已经完整加载，并且其像素不再发生更改，则 drawImage 返回 true。否则，drawImage 返回 false，
		//并且随着更多的图像可用或者到了绘制动画另一帧的时候，加载图像的进程将通知指定的图像观察者。
		boolean r = g.drawImage(img, 0, 0, observer);
		System.out.println(r + ":" + img.getWidth(this) + ":" + img.getHeight(this));
	}

	@Override
	public void update(Graphics g) {
		System.out.println("update");
		super.update(g);
	}
	
	
}
