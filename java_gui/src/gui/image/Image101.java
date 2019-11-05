package gui.image;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
/**
 * 图像的加载与显示:
 * 		图像的加载与显示是采用异步方式进行
 * 	例如:
 * 		画图时有时一次画一条线，有时把图像一次性画出来，有的图像在显示的过程中不停的闪烁，有的图则是很平滑的显示
 * 
 * 	主要学习:
 * 		java.awt.Image类(一个抽象类，里面定义的方法提供了对图像信息的访问，这个类为图像提供引用)
 * 		java.awt.image包下的相关类及其方法(创建和处理图像的基本结构都在这个软件包中,当前包负责与生产图像有关的工作)
 * 
 * 	代码可以配套参考图书<Java2图形设计卷I：AWT>,第５章,加载和显示图像
 * 
 * 
 * 
 * @author zuoyang
 *
 */
public class Image101 extends Applet {
	public Image img = null;

	public void init() {

		long start = System.currentTimeMillis();
		System.out.println("加载图像开始...........");
		try {
			//此时虽然已经返回对图像的引用，但定义图像的位还没有被加载,java.awt.Image相关的图像位在需要之前是不被生产的
			//getImage()或者createImage()方法是立刻返回，不检查图像数据是否存在,当图像真正加载的时候，才会绘制图像
			//如果方法返回的是一个Image对象,则返回的是这个图像引用,是立即返回的,此时并没有把图像全部加载到内存中,即:图像数据并没有加载到内存中
			img = Toolkit.getDefaultToolkit().createImage(new URL("http://a.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fe69291a015e6034a85edf72f9.jpg"));
			//img = Toolkit.getDefaultToolkit().getImage(new URL("http://a.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fe69291a015e6034a85edf72f9.jpg"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		//此时并没有把图像加载到内存，只是得到图像的引用，所以图像的位信息，图像的宽度，高度等属性都得不到
		//从打印的信息可以看出来,加载只用了2毫秒,而且得到的宽度和高度都是-1
		//分析:
		//第一:2毫秒,只是代码执行完毕,一个网络图片不可能2毫秒就能加载到本地的电脑内存中
		//第二:得到的图片宽度和高度都是-1,说明此时并没有得到图像真是的宽度和高度
		System.out.println("加载图像结束...........:" + (end-start) + ":" + img.getWidth(this) + ":" + img.getHeight(this));
	}

	@Override
	public void paint(Graphics g) {
		//绘制图片到面板
		//awt中,很多图像操作是异步进行的，例如:下面drawImage()方法返回时，图像可能根本显示不出来
		//因为如果调用这个方法时，还没有加载图像，则什么也画不出来，只是产生与图像相关的位
		
		//drawImage()方法(官方api)说明:
		//在任何情况下都立刻返回，甚至在图像尚未完整加载，并且还没有针对当前输出设备完成抖动和转换的情况下也是如此。
		//如果图像已经完整加载，并且其像素不再发生更改，则 drawImage 返回 true。否则，drawImage 返回 false，
		//并且随着更多的图像可用或者到了绘制动画另一帧的时候，加载图像的进程将通知指定的图像观察者。
		
		
		//drawImage()方法绘制图像的关键是第４个参数
		//当图像生产者加载图像时，图像观察者可以监视其进展情况
		//drawImage()在第一次被调用时，没有任何图像资源被加载进来，所以也没有图像位可以绘制,drawImage()方法仅仅向对应的图像生产者注册图像观察者(即:this,当前这个Image101对象)
		//所以当该方法返回时，什么也没有绘制(每个java.awt.Image对象都对应一个图像生产者，后面讨论)
		//当drawImage()返回之后，只要有图像的新图像位被加载，则图像观察者中的imageUpdate()方法就会被调用,当前的图像观察者Image101没有覆盖imageUpdate()方法，所以默认调用
		//继承的imageUpdate()方法
		
		boolean r = g.drawImage(img, 0, 0, this);
		System.out.println("加载完成:" + r + "------" + img.getWidth(this) + ":" + img.getHeight(this));
	}

	
	
	
}




































