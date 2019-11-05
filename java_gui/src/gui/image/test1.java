package gui.image;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 图片的水印,文字水印,图片水印
 * 
 * @author Administrator
 * 
 */
public class test1
{
	/**
	 * 对图片设置文字水印
	 * 
	 * @param waterMartText
	 *            -- 文字
	 * @param targetImg
	 *            -- 目标图片
	 * @param fontName
	 *            -- 字体名
	 * @param fontStyle
	 *            -- 字体样式
	 * @param fontColor
	 *            -- 字体颜色
	 * @param fontSize
	 *            -- 字体大小
	 * @param x
	 *            -- x偏移量
	 * @param y
	 *            -- y偏移量
	 * @param degree
	 *            -- 偏转角度
	 * @throws IOException
	 */
	public void fontWaterMark(String waterMartText, String targetImg,
			String fontName, int fontStyle, Color fontColor, int fontSize,
			int x, int y, Integer degree) throws IOException
	{
		Image image = ImageIO.read(new File(targetImg));

		// 在内存中创建一个和目标图象大小一样的图片
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		// 得到这个内存中的图片的画笔
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();

		// 在内存中把要被添加水印的图片画到内存中
		g2d.drawImage(image, 0, 0, null);

		// 调整画笔的规则：透明度，合成规则，颜色，偏转角度
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.3f));
		g2d.setColor(fontColor);
		g2d.setFont(new Font(fontName, fontStyle, fontSize));
		// 设置偏转角度
		if (degree != null)
			g2d.rotate(Math.toRadians(degree));
		
		// 画水印文字
		g2d.drawString(waterMartText, x, y);
		//或者先把原点平移到指定的点，在画，默认是图片的左上角为原点
		// g2d.translate(x, y);
		// g2d.drawString(waterMartText, 0, 0);
		
		g2d.dispose();
		// 把合成的图片写入文件
		ImageIO.write(bufferedImage, "jpg", new File(targetImg));
	}

	public void fontWaterMark(String waterMartText, String targetImg,
			String fontName, int fontStyle, Color fontColor, int fontSize,
			int x, int y) throws IOException
	{
		fontWaterMark(waterMartText, targetImg, fontName, fontStyle, fontColor,
				fontSize, x, y, null);
	}

	public static void main(String[] args) throws IOException
	{
		test1 image = new test1();
		image.fontWaterMark("中国", "/test.jpg", "宋体", Font.BOLD, Color.RED, 50,
				200, 50,null);
	}
	
	/**
	 * 对图片设置图片水印
	 * 
	 * @param warterMarkImg
	 *            -- 水印文件
	 * @param targetImg
	 *            -- 目标文件
	 * @param x
	 *            -- x坐标
	 * @param y
	 *            -- y坐标
	 * @param degree
	 *            -- 偏转角度
	 * @throws IOException
	 */
	public void imageWaterMark(String warterMarkImg, String targetImg, int x,
			int y, Integer degree) throws IOException
	{
		Image image = ImageIO.read(new File(targetImg));

		// 在内存中创建一个和目标图象大小一样的图片
		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		// 得到这个内存中的图片的画笔
		Graphics2D g2d = (Graphics2D) bufferedImage.getGraphics();

		// 在内存中把要被添加水印的图片画到内存中
		g2d.drawImage(image, 0, 0, null);

		// 调整画笔的规则：透明度，合成规则，颜色,偏转角度
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.3f));
		g2d.setColor(Color.GRAY);
		if (degree != null)
		{
			g2d.rotate(Math.toRadians(degree));
		}
		// 画水印图片
		g2d.drawImage(ImageIO.read(new File(warterMarkImg)), x, y, null);
		g2d.dispose();
		// 把合成的图片写入文件
		ImageIO.write(bufferedImage, "jpg", new File(targetImg));
	}

	/**
	 * 对图片设置图片水印
	 * 
	 * @param warterMarkImg
	 *            -- 水印文件
	 * @param targetImg
	 *            -- 目标文件
	 * @param x
	 *            -- x坐标
	 * @param y
	 *            -- y坐标
	 * @throws IOException
	 */
	public void imageWaterMark(String warterMarkImg, String targetImg, int x,
			int y) throws IOException
	{
		imageWaterMark(warterMarkImg, targetImg, x, y, null);
	}


}
