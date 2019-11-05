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
public class Image103 extends Frame {
	public static Image img = null;

	public static void main(String[] args) {
		Image103 f = new Image103();
		f.setSize(800, 600);
		
		
		long start = System.currentTimeMillis();
		System.out.println("加载图像开始...........");
		try {
			//这里分析一下图像一点点是怎么加载的,首先,这里不能使用返回BufferedImage对象的方法,因为BufferedImage图像会把数据一次加载完
			//这里使用返回Image对象的方法
			//如果使用返回的对象是BUfferedImage,则不会调用imageUpdate()方法
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
		//g.drawImage()第一次执行的时候，图像没有一次加载完，则后来每次加载的时候，
		//图像生产者会调用图像观察者继承而来的imageUpdate()方法,imageUpdate()调用repaint(),repaint()调用paint()
		//图像观察者(第四个参数)都会得到通知,
		System.out.println("imageUpdate:" + infoflags);
		//ImageObserver接口重定义的常量
//		public static final int WIDTH = 1;		//00000001
//	    public static final int HEIGHT = 2;		//00000010
//	    public static final int PROPERTIES = 4;	//00000100
//	    public static final int SOMEBITS = 8;	//00001000
//	    public static final int FRAMEBITS = 16;	//00010000
//	    public static final int ALLBITS = 32;	//00100000
//	    public static final int ERROR = 64;		//01000000
//	    public static final int ABORT = 128;	//10000000
		
		
		if((infoflags&this.ALLBITS)==0){
			//(&)与操作稍微快点
			//infoflags值不是32,即数据没有加载完，需要持续加载
			
		}
		
		//if(infoflags!=32)等价于infoflags != this.ALLBITS
		if(infoflags != this.ALLBITS){
			//数据没有完全加载完,或者加载出错,需要持续加载
			return true;
		}else{
			//数据完全加载完了，此时在调用repaint(),把图像画出来
			this.repaint();
			return false;//图像被完全加载了
		}
		
		//根据打印的信息,我们可以看出来paint()方法,只调用了一次,当我们在imageupdate()方法中判断,图像加载完了,调用repait()方法,最终才调用paint()方法
		
	}
	
	
	
	
}
