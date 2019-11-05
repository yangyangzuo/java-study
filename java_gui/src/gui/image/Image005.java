package gui.image;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *	图像的保存/写到本地
 *	
 *	Image i/o提供了一个简单方式,按照特定的图片格式保存图片:
 *
 *	static boolean ImageIO.write(RenderedImage im, String formatName,File output)  throws IOException
 *	
 *	标准图像格式:JPEG, PNG, GIF, BMP, WBMP
 *	每种格式都有优点和缺点:
 *	gif:支持动画和透明像素(transparent pixels),仅仅支持256色,不支持半透明
 *	png:比gif和jpg更好的无损颜色图像,支持半透明,不支持动画
 *	jpg:较好的摄影图像,压缩后,图像会失真,对于,文字,屏幕截图,应用程序应该保持原图
 *
 *	对于大多数应用程序来说,这些标准的图像格式已经足够	,
 *	Image i/o还提供了对于其他格式的插件的支持.
 *	ImageIO.getReaderFormatNames(),ImageIO.getWriterFormatNames()可以获得支持的格式名称
 *
 *
 *
 *	
 * @author zuoyang
 *
 */
public class Image005 {
	public static void main(String[] args) throws IOException {
		
		//保存图像到本地:s
		//ImageIO.write(RenderedImage im, String formatName, File output) 
		//ImageIO.write(RenderedImage im, String formatName, ImageOutputStream output) 
		//ImageIO.write(RenderedImage im, String formatName, OutputStream output)
		
		//打印当前系统支持的图片类型
		//读取图片支持的格式
		String	[]readerNames = ImageIO.getReaderFormatNames();
		for(int i=0;i<readerNames.length;i++){
			System.out.println(readerNames[i]);
		}
		//写图片支持的格式
		String	[]writerNames = ImageIO.getWriterFormatNames();
		for(int i=0;i<writerNames.length;i++){
			System.out.println(writerNames[i]);
		}
		
	}
}




















