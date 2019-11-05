package gui.image;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
/**
 * http://journals.ecs.soton.ac.uk/java/tutorial/ui/drawing/loadingImages.html
 * awt提供了２种方式用来跟踪图片的加载:
 * 	1.MediaTracker
 * 	2.ImageObserver
 * 	MediaTracker对于很多程序来说已经足够用了，你可以创建一个MediaTracker实例,告诉它来追踪一个或者多个图片，当你需要的时候，可以询问这些图片的加载情况
 * 	
 * @author zuoyang
 *
 */
public class Image105 extends Frame {
	public static Image img = null;

	public static void main(String[] args) {
		Image105 f = new Image105();
		f.setSize(800, 600);
		
		
		long start = System.currentTimeMillis();
		System.out.println("加载图像开始...........");
		try {
			//这里分析一下图像一点点是怎么加载的,首先,这里不能使用返回BufferedImage对象的方法,因为BufferedImage图像会把数据一次加载完
			//这里使用返回Image对象的方法
			img = Toolkit.getDefaultToolkit().createImage(new URL("http://a.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fe69291a015e6034a85edf72f9.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		//图像加载完，就可以得到图像的全部信息，比如：图相位，宽高等属性
		System.out.println("加载图像结束...........:" + (end-start) + ":" + img.getWidth(f) + ":" + img.getHeight(f));
		
		
		f.setVisible(true);
		
		
		
	}
		

	@Override
	public void paint(Graphics g) {
		//图像的加载过程:
		//1.当组件第一次显示的时候会调用paint()方法
		//2.g.drawImage()方法,会对图像进行检查,如果图像数据已经加载完,则直接绘制完成,函数结束
		//如果图像数据没有加载完,则先绘制加载的数据,有一个异步加载线程负责加载,每次加载一点,会通知图像观察者(ImageObserver),
		//ImageObserver会调用他的ImageUpdate()方法,
		//如果返回true,表示没有加载完成,继续加载
		//如果返回false,则表示加载完成,不在加载了,如果加载完成了,需要调用repaint()方法,把图像绘制出来
		
		
		boolean r = g.drawImage(img, 0, 0, this);
		System.out.println("加载完成:" + r + "------" + img.getWidth(this) + ":" + img.getHeight(this));
	}

	
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
		System.out.println("imageUpdate:" + infoflags);
		
		//根据Image100.java可以看出来,默认情况下,图像每次加载一点,会调用paint()方法
		//在Image103.java中,我们覆盖了ImageUpdate()方法,我们每次加载一点,都不调用repaint()方法,即:不绘制,当所有数据加载完了才调用repaint()方法,把图像一次性绘制出来
		
		//这里我们修改一下,修改为和默认程序一样,每次加载一点就调用repaint()方法绘制
		//此时发现paint()的打印信息,一直出现,说明一直在调用repaint()-->paint()方法
		
		//我们模拟系统的默认实现,每次加载一点图像数据,就调用repaint()绘制一点,但是程序显示不出来加载的图像,不知道位什么
		this.repaint();
		
		try{
		Thread.currentThread().sleep(200);
		}catch(Exception e){
			
		}
		
		
		if(infoflags != this.ALLBITS){
			//数据没有完全加载完,或者加载出错,需要持续加载
			return true;
		}else{
			return false;//图像被完全加载了
		}
		
		
	}
	
	
	
	
}
