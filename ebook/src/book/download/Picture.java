package book.download;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * ͼƬ������
 */
public final class Picture
{
	/**
	 * ͼƬ����
	 * 
	 * @param filePath
	 *            ͼƬ·��
	 * @param height
	 *            �߶�
	 * @param width
	 *            ���
	 * @param bb
	 *            �����ʱ�Ƿ���Ҫ����
	 */
	public static void resize(String filePath)
	{
		//994:1314
		//宽:高=994:1496=327:492
		
		int width = 327;
		int height;
		boolean bb = true;
		try
		{
			
			File picture = new File(filePath);  
	        BufferedImage sourceImg =ImageIO.read(new FileInputStream(picture));
	        //图片大小
	        //System.out.println(String.format("%.1f",picture.length()/1024.0));
	        //图片宽度和高度
	        //System.out.println(sourceImg.getWidth());  
	        //System.out.println(sourceImg.getHeight());
	        //根据图片的实际大小比例，等比缩放成宽度为327的图片
	        height = (sourceImg.getHeight()*width)/sourceImg.getWidth();
	        
			 //����ͼ���ļ���
            String newFile = filePath.substring(0,filePath.lastIndexOf("."))+"_mini"+filePath.substring(filePath.lastIndexOf("."));
            
            //如果缩略图文件存在，则不再次生成
            File _file = new File(newFile);
            if(_file.exists()){
            	return;
            }
            
			//����һ����ͼ
			  BufferedInputStream inBuff = null;
		      BufferedOutputStream outBuff = null;
		      try {
		            // �½��ļ���������������л���
		            inBuff = new BufferedInputStream(new FileInputStream(filePath));	
		          
		            // �½��ļ��������������л���		            
		            outBuff = new BufferedOutputStream(new FileOutputStream(newFile));
	
		            // ��������
		            byte[] b = new byte[1024 * 5];
		            int len;
		            while ((len = inBuff.read(b)) != -1) {
		                outBuff.write(b, 0, len);
		            }
		            // ˢ�´˻���������
		            outBuff.flush();
		        } finally {
		            // �ر���
		            if (inBuff != null)
		                inBuff.close();
		            if (outBuff != null)
		                outBuff.close();
		        }
			//�������Ŵ���
			double ratio = 0; // ���ű���
			File f = new File(newFile);
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height,
					BufferedImage.SCALE_SMOOTH);
			// �������
			if ((bi.getHeight() > height) || (bi.getWidth() > width))
			{
				if (bi.getHeight() > bi.getWidth())
				{
					ratio = (new Integer(height)).doubleValue()
							/ bi.getHeight();
				} else
				{
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(AffineTransform
						.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb)
			{
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage)itemp, "jpg", f);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException
	{
		String path = "/home/zuoyang/Desktop/files/";
		File dir = new File(path);
		File [] files = dir.listFiles();
		for(int i=0;i<files.length;i++){
			resize("/home/zuoyang/Desktop/files/" + files[i].getName());
		}
//		resize("E:/workspace/yayabooks/WebRoot/files/6.jpg");
	}
}