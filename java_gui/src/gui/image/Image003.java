package gui.image;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图像的绘制:
 * 
 * 例如:drawImage(Image img, int x, int y, ImageObserver observer)
 * 	img表示要绘制的图像
 *	x,y代表图像的左上角的坐标位置
 *	imageObserver表示当图像异步加载的时候,通知应用程序跟新显示图像的内容,这个参数不常用,对于BufferedImage来说,是不需要的,
 *	所以,通常为null,(个人理解:因为,对于BufferedImage来说,提供的方法会把图像的所有数据一次性加载到内存中才会返回这个BufferedImage对象
 *	,不会异步的一点一点的加载)
 *	上述中,只有在绘制整个图像时,图像的像素映射到用户坐标空间为1:1
 *
 *	有时,我们需要绘制图像的一部分或者缩放图像用来覆盖一个特定的区域,或者需要转换位移/过滤图像.
 *	我们可以通过重载drawImage()方法,来完成这样的操作.例如:如下方法可以指定图像的特定区域,把他(缩放)绘制到一个特定的区域
 *	Graphics.drawImage(Image img,
       int dstx1, int dsty1, int dstx2, int dsty2,
       int srcx1, int srcy1, int srcx2, int srcy2,
       ImageObserver observer);
    src表示需要拷贝和绘制的图像源
    dst表示进行绘制的目标区域
    目标区域的宽度和高度:
 *	宽度(dstx2-dstx1)
 *	高度(dsty2-dsty1)
 *	如果图像源和要绘制的目标区域尺寸不同,则java2d会根据需要放大或者缩小
 *
 *	
 *
 * @author zuoyang
 *
 */
public class Image003 {
	public static void main(String[] args) throws IOException {
		
		//图像绘制
		// Graphics.drawImage()
		// drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer)
		// drawImage(Image img, int x, int y, ImageObserver observer)
		// drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer)
		// drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)
		// drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer)
		// drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer)
		
		
		
	}
}
