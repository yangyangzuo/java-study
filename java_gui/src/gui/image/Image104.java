package gui.image;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import sun.security.action.GetPropertyAction;
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
public class Image104 extends Frame {
	public static Image img = null;
	public static Image104 f;
	public static int i = 1;

	public static void main(String[] args) {
		f = new Image104();
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

	



	/**
	 * 分析imageUpdate的Component中的源码实现
	 */
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
		//我么这里分析一下,系统的默认实现
		//imageUpdate()方法是ImageObserver接口中的方法,
		//官方api ImageObserver说明:用于在构造 Image 时，接收有关 Image 信息通知的异步更新接口。
		//即:当图像在加载/构造的时候,这个ImageObserver接口重的imageUpdate()方法会被调用
		
		//ImageObserver中的imageUpdate()方法说明:
		//当以前使用异步接口所请求的图像的信息变得可用时就调用此方法。
		//异步接口是将 ImageObserver 对象作为参数的方法调用，如 getWidth(ImageObserver) 和 drawImage(img, x, y, ImageObserver)。
		//这些方法要么将调用者注册为对有关总体图像本身的信息感兴趣（对于 getWidth(ImageObserver)），
		//要么注册为对有关图像输出版本的信息感兴趣（对于调用 drawImage(img, x, y, [w, h,] ImageObserver)）。
		//如果需要进一步的更新，则此方法应该返回 true，如果已获得所需的信息，则应返回 false。
		//使用 img 参数传入所跟踪的图像。组合各种常量即可形成 infoflags 参数，指示哪种图像信息现在可用。对 x、y、width 和 height 参数的解释取决于 infoflags 参数的内容。
		//infoflags 参数应该是对下列标志按位进行 OR 运算后的结果：WIDTH、HEIGHT、PROPERTIES、SOMEBITS、FRAMEBITS、ALLBITS、ERROR、ABORT。
		
		
		//Component中的imageUpdate()方法说明:
		//当有关图像的更多信息可用时调用 ImageObserver 的 imageUpdate 方法，该图像是以前使用诸如 Graphics 的 drawImage 方法等此类异步例程请求的。
		//如果系统属性 awt.image.incrementaldraw 丢失或其值为 true，则逐渐绘制图像。如果该系统属性为其他值，则在完全加载图像后才绘制它。
		//另外，如果是逐渐绘制图像，则将系统属性 awt.image.redrawrate 的值解释为一个整数，得到一个最快的重绘速率，单位是毫秒。如果该系统属性丢失或无法解释为一个整数，则重绘速率是每 100ms 一次。
		//如果 infoflags 表明已完全加载了图像，则返回 false；否则返回 true。
		
		
		//Component中的isInc(isIncrementalDrawing)和incRate(incrementalDrawingRate)变量说明:
		//这里稍作修改,具体查看Component类中的这两个变量定义
        //Static properties for incremental drawing.
        boolean isInc;//是否是增量绘制
        int incRate;//增量绘制的速度
        String s = java.security.AccessController.doPrivileged(new GetPropertyAction("awt.image.incrementaldraw"));
        isInc = (s == null || s.equals("true"));

        s = java.security.AccessController.doPrivileged(new GetPropertyAction("awt.image.redrawrate"));
        incRate = (s != null) ? Integer.parseInt(s) : 100;
		
        System.out.println(infoflags);
		//Frame类继承了Component类,而Component类实现了ImageObserver接口
		//源码如下:
		 int rate = -1;
	        if ((infoflags & (FRAMEBITS|ALLBITS)) != 0) {//当inforflags标志位为FRAMEBITS或者ALLBITS
	        	//如果是多帧图画(gif)的一帧可以绘制
	        	//或者单帧图画的所有内容都加载完毕了
	        	//则立即绘制
	            rate = 0;
	        } else if ((infoflags & SOMEBITS) != 0) {
	        	//如果过仅仅是一部分位被加载,且定义图画绘制方式是isIncremental(增量绘制)
	        	//则默认设定增量绘制时间间隔是100ms(incRate)
	            if (isInc) {
	                rate = incRate;
	                if (rate < 0) {
	                    rate = 0;
	                }
	            }
	        }

	        if (rate >= 0) {
	            repaint(rate,0, 0, f.getWidth(), f.getHeight());
	        }
	       
	        return (infoflags & (ALLBITS|ABORT)) == 0;//如果infoflags为ALLBITS或者ABORT,则返回true,表示加载完所有的图相位或者加载放弃了,否则,返回false,继续加载图像数据
	      //ImageObserver接口重定义的常量
//			public static final int WIDTH = 1;		//00000001
//		    public static final int HEIGHT = 2;		//00000010
//		    public static final int PROPERTIES = 4;	//00000100
//		    public static final int SOMEBITS = 8;	//00001000
//		    public static final int FRAMEBITS = 16;	//00010000
//		    public static final int ALLBITS = 32;	//00100000
//		    public static final int ERROR = 64;		//01000000
//		    public static final int ABORT = 128;	//10000000
		
	        //可以看出来系统默认awt.image.incrementaldraw值是null,所以默认实现是增量绘制的,即:一块一块绘制的,但是在程序显示的时候,图像并没有绘制出来
	}

	
}
