package gui.print;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

/**
 * 
 * 官方文档:
 * http://docs.oracle.com/javase/tutorial/2d/printing/index.html
 * 
 * 主要学习:
 * java.awt.print
 * javax.print
 * 
 * 
 * 
 * 打印机流程:
 * 	1.任务控制:
 * 		创建一个打印任务，把它和对应的打印机关联起来,制定需要打印的份数,调用打印对话框和用户交互,用户确认打印信息
 * 	2.绘制页面内容(打印内容)
 * 	
 * 
 * 
 * 	1.需要一个打印服务对象(本地的可用的打印机服务).
 * 		方法1:实现 java.awt.print.Printable 接口,通过printerJob对象打印具体的内容
 * 		方法2:Toolkit.getDefaultToolkit().getPrintJob,通过PrintJob对象打印组件
 * 		方法3:javax.print.PrintSerivceLookup 
 * 	2.把打印任务发送给打印机
 * 		方法1: java.awt.print.PrintJob.print()/ java.awt.print.PrintJob.printAll()
 * 		方法2: java.awt.print.PrinterJob 的 printDialog显示打印对话框，然后调用 print
 * 		方法3: javax.print.ServiceUI 的 printDialog显示打印对话框，然后调用 print
 * 
 * 
 * 
 *		通过Printable接口来实现打印的流程:
//		开始打印工作之前，可以通过 PrinterJob.printDialog 来显示一个打印对话框。
//		它给用户一个机会以选择应该打印的页码范围，并可供用户改变打印设置。它是一个本地对话框。
//		事实上，当从一个 Printable 对象进行一个打印工作时，打印对象并不知道需要打印多少页。它只是不停地调用 print 方法。
//		只要 print 方法返回 Printable.PAGE_EXISTS 值，打印工作就不停地产生打印页，直到 print 方法返回 Printable.NO_SUCH_PAGE 时，打印工作才停止。
//		由于打印工作只有在打印完成后才进行准确的页数计算，所以在对话框上的页码范围是尚未初始化的 [1,9999]。我们可以通过构建一个 java.awt.print.Book 对象传递给打印对象；
//		也可以通过指定的格式计算需要打印的页数并传递给打印对象，使其准确地知道要打印多少页。
 * 
 * @author zuoyang
 *
 */
public class Print00  {

	public static void main(String[] args) {

		//1.创建一个打印任务
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		
		
		//2.填充打印内容
//		printerJob.setPrintable(new PrintContent00());
		printerJob.setPrintable(new PrintContent00(),printerJob.defaultPage());
		//如果setPrintable(*,pageFormat)方法，不传递pageFormat参数，则可能出现java.awt.print.PrinterException: Paper's imageable width is too small.
		//当可以打印区域或者宽度出现负值的时候，java会抛出上面异常
		//大多数打印机驱动程序有一个非打印边缘地区在纸张的边缘，所以java.awt的很可能会把打印内容推到打印机的不可打印区域
		
		//向用户呈现一个对话框，用户可以调整各种打印选项,例如:打印份数,页面打印格式(横向/纵向)，打印范围
		//如果用户同意打印则该方法返回true,否则返回false
		if (printerJob.printDialog()) {			
			//如果不想呈现对话框，可以直接调用printerJob.print();
			try {
				//3.把打印任务发送给打印机
				printerJob.print();
			} catch (PrinterException e) {
				//如果任务发送给打印机出问题时会抛异常
				//因为打印任务一旦发送给打印机，print()方法就会立即返回,所以用户程序无法检测卡纸或者纸用完的情况
				System.out.println(e);
			}
		}
	}

	
}

//实现要打印的内容,具体可以查看api中的Printable接口说明
//实现Printable接口，绘制要打印的内容
class PrintContent00 implements Printable{
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		System.out.println(pageIndex);
		//这里是针对要打印一页的情况
		if (pageIndex == 1)
			return NO_SUCH_PAGE;
		//要打印的内容
		graphics.drawString("河南郑州", 0, 10);
		return PAGE_EXISTS;
	}
	
}










