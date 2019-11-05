package gui.image;

import java.io.IOException;

/**
 *
 *	手动创建一个像素缓冲区的图像:
 *	new BufferedImage(width, height, type)
 *	new BufferedImage(width, height, type, colorModel)
 *	new BufferedImage(colorModel, raster, premultiplied, properties)
 *	
 *	还可以使用如下方法,这些方法可以分析显示器的分辨率,然后创建一个合适类型的图像
 *	Component.createImage(width, height)
 *	GraphicsConfiguration.createCompatibleImage(width, height)
 *	GraphicsConfiguration.createCompatibleImage(width, height, transparency)
 *	
 *
 *	java2d允许屏幕外的图像硬件加速,可以提供更好的性能:
 *	Image对象提供如下方法
 *	getCapabilities(GraphicsConfiguration gc) 方法使您可以确定图像是否正在加速。
 *	getAccelerationPriority()方法让你设定的加速度是如何重要的图像提示。
 *	setAccelerationPriority(float priority)方法获取一个关于加速度的重要提示。
 *
 *
 *	
 * @author zuoyang
 *
 */
public class Image004 {
	public static void main(String[] args) throws IOException {
		
		
	}
}




















