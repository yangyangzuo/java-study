package gui.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * 图片的创建，生成，读取
 * 
 * 主要学习:
 * 
 * 抽象类Image及其子类BufferedImage,VolatileImage
 * 
 * 查看Tupian.doc文件，常见的验证码破解
 * 
 * @author Administrator
 * 
 */
public class test4
{
	public static void main(String[] args) throws IOException
	{
		// 在内存中创建图象
		int width = 60, height = 20;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics g = image.getGraphics();
		// Graphics2D g = image.createGraphics();

		// 设定背景色
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, width, height);

		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 画边框
		g.setColor(Color.RED);
		g.drawRect(0, 0, width - 1, height - 1);

		// 在图象中画内容
		for (int i = 0; i < 4; i++)
		{
			g.setColor(Color.BLACK);
			// 这里的字符串是一个一个画的，这样可以控制字符串之间的间距，以及他们的坐标
			// 当然也可以直接画出来一个整体的字符串
			g.drawString(new Random().nextInt(10) + "", 13 * i + 6, 16);
		}

		// 释放此图形的上下文以及它使用的所有系统资源。调用 dispose 之后，就不能再使用 Graphics 对象。
		g.dispose();

		// 输出图象到文件,工作区间的根目录下的test.jpg
		ImageIO.write(image, "JPEG", new File("/test.jpg"));

		// 读取一个网络文件，然后写入到本地
		BufferedImage bi = ImageIO.read(new URL(
				"http://img.duxiu.com/n/6A6D6A6E69686B706C716E3638343730353338/img6/5CAED5F4606BBFD0DF9EB5C5BCBFA3410B95D7C5DA148EA11A655DC331112AC8B83994E350DE9E06E8C1A16C990880EC9821708A93B43FA4D6A6A501452D76AB25B035A5C7351B3BE19A206AA8B6CC7094210E66EC4C6FE7E0525645F3C961256B36F5DC1B8167FE69B57E73DA7094F67133/b3/000020?.&uf=ssr&zoom=0"));
		//文件存在
		if(bi!=null)
		ImageIO.write(bi, "png", new File("c:/a.png"));
		else
			System.out.println("文件不存在");
		// 写入到一个网络输出流中
		// ImageIO.write(bi, "gif", response.getOutputStream());

	}
}
