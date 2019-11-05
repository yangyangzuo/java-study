package gui.print;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * 打印机流程:
 * 	1.需要一个打印服务对象(本地的可用的打印机服务).
 * 		方法1:实现 java.awt.print.Printable 接口
 * 		方法2:Toolkit.getDefaultToolkit().getPrintJob
 * 		方法3:javax.print.PrintSerivceLookup 
 * 	2.把打印任务发送给打印机
 * 		方法1: java.awt.print.PrintJob.print()/ java.awt.print.PrintJob.printAll()
 * 		方法2: java.awt.print.PrinterJob 的 printDialog显示打印对话框，然后调用 print
 * 		方法3: javax.print.ServiceUI 的 printDialog显示打印对话框，然后调用 print
 * @author zuoyang
 *
 */
public class Print03  {

	public static BufferedImage[] images = new BufferedImage[2];
	
	static{
		try {
			images[0] = ImageIO.read(new FileInputStream("C:/Users/Administrator/Desktop/workspace/java_gui/src/gui/title.png"));
			images[1] = ImageIO.read(new FileInputStream("C:/Users/Administrator/Desktop/workspace/java_gui/src/gui/tray.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 打印多页的处理
	 * 前面的例子是针对打印一页的处理，这个例子为打印多页处理
	 * @param args
	 */
	public static void main(String[] args) {
		
		//1.创建一个打印任务
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		//2.填充打印内容
		printerJob.setPrintable(new PrintContent03(),printerJob.defaultPage());
		if (printerJob.printDialog()) {
			//向用户呈现一个对话框，用来更改打印作业的属性,这个对话框会显示当前的打印配置，比如:打印格式(横向/纵向)
			//如果不想呈现对话框，可以直接调用printerJob.print();
			try {
				//3.把打印任务发送给打印机
				printerJob.print();
			} catch (PrinterException e) {
				System.out.println(e);
			}
		}
	}
}

//实现要打印的内容,具体可以查看api中的Printable接口说明
class PrintContent03 implements Printable{
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			System.out.println(pageIndex);
		  if (pageIndex < Print03.images.length) {
		        graphics.drawImage(Print03.images[pageIndex], 0, 0, null);
		        return PAGE_EXISTS;
		    } else {
		        return NO_SUCH_PAGE;
		    }
	}
	
}