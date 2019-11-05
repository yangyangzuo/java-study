package gui.components;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * 
 * 作用：
 * 
 * 生成标题栏上的图标/或者摁alt+tab键时的切换图表
 * 
 * 1.利用Toolkit类生成image
 * 
 * Image createImage(byte[] imagedata)
 * 
 * 创建一幅图像，该图像对存储在指定字节数组中的图像进行解码。
 * 
 * 
 * abstract Image createImage(byte[] imagedata, int imageoffset, int
 * imagelength)
 * 
 * 创建一幅图像，该图像以指定偏移量和长度对存储在指定字节数组中的图像进行解码。
 * 
 * 
 * abstract Image createImage(ImageProducer producer)
 * 
 * 使用指定的图像生成器创建一幅图像。
 * 
 * 
 * abstract Image createImage(String filename)
 * 
 * 返回从指定文件获取像素数据的图像。
 * 
 * 
 * abstract Image createImage(URL url)
 * 
 * 返回一幅图像，该图像从指定 URL 获取像素数据。
 * 
 * 
 * abstract Image getImage(String filename)
 * 
 * 返回一幅图像，该图像从指定文件中获取像素数据，图像格式可以是 GIF、JPEG 或 PNG。
 * 
 * 
 * abstract Image getImage(URL url)
 * 
 * 返回一幅图像，该图像从指定 URL 获取像素数据。
 * 
 * 
 * 2.利用ImageIcon生成image
 * 
 * 
 * Image getImage()
 * 
 * 返回此图标的 Image。
 * 
 * 注意：
 * 
 * 1.图片如果找不到，用系统默认的图片
 * 
 * 2.可以用.gif,.jpg,.png类型的图片,其他类型好象不行
 * 
 * 3.图片会根据比例缩放大小
 * 
 * @author Administrator
 * 
 */
public class TitleImage
{
	/**
	 * 利用Tookkit类生成图标，只能修改具体的一个窗口的图标
	 * 
	 * createImage(),getImage()方法
	 * 
	 * @return
	 */
	public static Image getMiNiImageByToolkit()
	{
		return Toolkit.getDefaultToolkit().createImage("image/title.gif");
	}

	/**
	 * 利用ImageIcon类生成的图片，只能修改具体的一个窗口的图标
	 * 
	 * @return
	 */
	public static Image getMiNiImageByImageIcon()
	{
		return new ImageIcon("image/title.gif").getImage();
	}
	public static void main(String[] args) {
		JFrame f = new JFrame();
//		f.setUndecorated(true);
//		f.setIconImage(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/nevermore/images/titleImage1.png").getImage());
//		Image image = Toolkit.getDefaultToolkit().createImage("/Users/gudandan/Desktop/jeeworkspace/nevermore/images/titleImage1.png");
		Image image = null;
		try {
			image = ImageIO.read(new File("/Users/gudandan/Desktop/jeeworkspace/nevermore/images/titleImage1.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		f.setBounds(0,0,200,200);
		f.setLocationRelativeTo(null);
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f.setIconImage(image);
		f.setVisible(true);
	}
}
