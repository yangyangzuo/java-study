package gui.image;

import java.applet.Applet;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
public class Image100{
	
	public static void main(String[] args) throws IOException {
		//根据打印数据可以简单看出来Image和BufferedImage类的区别:
		//1.BufferedImage是带有缓存数据的图像对象,这个对象会把图像的数据全部加载到内存中,由打印信息看出来,此时已经打印出来宽度和高度,说明此时图像数据已经完全加载到内存
		BufferedImage bufferedImage = ImageIO.read(new File("/Users/gudandan/Desktop/jeeworkspace/nevermore/images/loading.png"));
		System.out.println(bufferedImage.getWidth() + ":" + bufferedImage.getHeight());
		
		//2.Image对象,返回的对象得不到宽度和高度,可以看出来,此时并没有把图像数据完全加载
		Image image = Toolkit.getDefaultToolkit().getImage("/Users/gudandan/Desktop/jeeworkspace/nevermore/images/loading.png");
		System.out.println(image.getWidth(null) + ":" + image.getHeight(null));

		Image image2 = Toolkit.getDefaultToolkit().createImage("/Users/gudandan/Desktop/jeeworkspace/nevermore/images/loading.png");
		System.out.println(image2.getWidth(null) + ":" + image2.getHeight(null));
	}
	
}
