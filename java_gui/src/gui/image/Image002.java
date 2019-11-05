package gui.image;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图像的读取和加载:
 * 	当我们想想数字图片的时候,很可能会想到简单的图像格式,例如:通常用在数码照片中的jpeg图像格式,用在网页上的gif图像格式
 * 	所有的程序使用图像的时候,必须把首先把这些图像从外部格式转换为内部格式
 * 	java 2d可以使用javax,imageio包提供的图像输入输出api,把外部图像格式加载为BufferedImage格式
 * 	Image i/o内置支持gif,png,jpeg,bmp,wbmp格式
 * 	image i/o也支持扩展,可以扩展支持其他的格式
 * 	例如:TIFF和JPEG 2000有扩展插件可用
 * 	代码举例:
 * 	BufferedImage img = ImageIO.read(new File("strawberry.jpg"));
 * 	image i/o分析这个jpeg格式图像的内容,然后把他解码为可以通过java2d操作的BufferedImage
 * 
 * 	Image i/o除了可以由文件/url读取图像还可以从其他来源读取,例如:inputstream,参考image01.java
 * 
 * @author zuoyang
 *
 */
public class Image002 {
	public static void main(String[] args) throws IOException {
		BufferedImage img = ImageIO.read(new File("strawberry.jpg"));
	}
}
