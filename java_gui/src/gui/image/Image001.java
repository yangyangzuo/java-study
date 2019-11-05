package gui.image;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 官方文档:
 * 	http://docs.oracle.com/javase/tutorial/2d/images/index.html
 * 
 * 	1.java.awt.Image表示所有的(矩形阵列像素的图形图像/二位图形图像)图形图像的超类
 * 	2.java.awt.image.BufferedImage类扩展了java.awt.Image,允许应用程序直接操作图像数据(例如:获取/设置像素颜色值)
 * 	BufferedImage是java2d(immediate-mode)及时成像绘图api的基础,这个类在内存中管理图像,提供了方法用来存储,翻译,获得像素数据
 * 	BufferedImage本质上是一个可以访问数据缓冲区的图像,因此直接操作BufferedImage可以更加高效
 * 	BufferImage有一个色彩模型(ColorModel)和一个栅格图像数据/栅栏图像数据(raster)
 * 	ColorModel提供了对图像的像素数据的分析解释
 * 	raster(栅栏具体指向如下功能):
 * 		表示图像的矩形坐标(直角坐标)
 * 		在内存重维护图像数据
 * 		提供一种机制,可以从一个单个的图像数据缓存,创建多个图像
 * 		提供访问一个图像的特定像素的方法
 * 
 * 3.java.awt.image.VolatileImage类也扩展java.awt.Image,可以在不受应用程序控制的情形下（例如，由操作系统或其他应用程序引起的情况）随时丢失其内容
 * 
 * @author zuoyang
 *
 */
public class Image001 {
	public static void main(String[] args) throws IOException {
		//获得图像的方法:
		
		//方法1:
        //在内存中创建一个类型为预定义图像类型之一的 BufferedImage
		//new BufferedImage(ColorModel cm, WritableRaster raster, boolean isRasterPremultiplied, Hashtable<?,?> properties) 
		//new BufferedImage(int width, int height, int imageType) 
		//new BufferedImage(int width, int height, int imageType, IndexColorModel cm) 
        BufferedImage bufferedImage = new BufferedImage(100, 100,BufferedImage.TYPE_INT_RGB);
        
        
		//方法2:
		//ImageIO.read(File input) 
		//ImageIO.read(ImageInputStream stream) 
		//ImageIO.read(InputStream input) 
		//ImageIO.read(URL input) 
		bufferedImage = ImageIO.read(new File(""));
		
		
		//方法3:
		//Toolkit.getDefaultToolkit().createImage(byte[] imagedata) 
		//Toolkit.getDefaultToolkit().createImage(byte[] imagedata, int imageoffset, int imagelength) 
		//Toolkit.getDefaultToolkit().createImage(ImageProducer producer) 
		//Toolkit.getDefaultToolkit().createImage(String filename) 
		//Toolkit.getDefaultToolkit().createImage(URL url)
		//Toolkit.getDefaultToolkit().getImage(String filename) 
		//Toolkit.getDefaultToolkit().getImage(URL url) 
		Image image = Toolkit.getDefaultToolkit().getImage("");
		
		//方法4:
		//java.awt.Component组件对象的方法
		//createImage(ImageProducer producer) 
		//createImage(int width, int height)
		Frame f = new Frame();
		image = f.createImage(100, 100);
		
        
        //方法5:
		//java.awt.GraphicsConfiguration对象的方法
		//BufferedImage	createCompatibleImage(int width, int height) 
		//BufferedImage	createCompatibleImage(int width, int height, int transparency) 
		//VolatileImage	createCompatibleVolatileImage(int width, int height) 
		//VolatileImage	createCompatibleVolatileImage(int width, int height, ImageCapabilities caps) 
		//VolatileImage	createCompatibleVolatileImage(int width, int height, ImageCapabilities caps, int transparency) 
		//VolatileImage	createCompatibleVolatileImage(int width, int height, int transparency) 
		
		//方法6:
//		ImageIcon(byte[] imageData) 
//		ImageIcon(byte[] imageData, String description) 
//		ImageIcon(Image image) 
//		ImageIcon(Image image, String description) 
//		ImageIcon(String filename) 
//		ImageIcon(String filename, String description) 
//		ImageIcon(URL location) 
//		ImageIcon(URL location, String description) 
        image  = new ImageIcon("").getImage();
        
        
        
	}
}













