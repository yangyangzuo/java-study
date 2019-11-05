package gui.print;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

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
 * 
 */
public class Print02  {

	/**
	 * 设置特定的打印区域
	 * @param args
	 */
	public static void main(String[] args) {

		//1.创建一个打印任务
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		
		
		/////////////////////////////////////////////////设置特定的打印区域
		PageFormat pageFormat = printerJob.defaultPage();
		//设置可以打印的区域
		Paper paper = new Paper();
		paper.setImageableArea(0, 5, paper.getWidth(), paper.getHeight());//此时会发现字体只打印了一半
		pageFormat.setPaper(paper);
		///////////////////////////////////////////////
		
		
		
		
		//使用特定的打印格式
		printerJob.setPrintable(new PrintContent02(), pageFormat);
		if (printerJob.printDialog()) {
			//向用户呈现一个对话框，用来更改打印作业的属性,这个对话框会显示当前的打印配置，比如:打印格式(横向/纵向)
			//如果不想呈现对话框，可以直接调用printerJob.print();
			try {
				//2.把打印任务发送给打印机
				printerJob.print();
			} catch (PrinterException e) {
				System.out.println(e);
			}
		}
	}

	
}

//实现要打印的内容,具体可以查看api中的Printable接口说明
class PrintContent02 implements Printable{
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		//System.out.println("pageIndex:" + pageIndex);
		//该方法如果返回表示PAGE_EXISTS,表示请求的页面被呈现,即:渲染(呈现)请求的页面
		//该方法如果返回表示NO_SUCH_PAGE,表示 pageIndex 太大以及请求的页面不存在
		if (pageIndex != 0)
			return NO_SUCH_PAGE;
		graphics.drawString("--------", 0, 5);
		graphics.drawString("河南郑州", 0, 10);
		return PAGE_EXISTS;
	}
	
}