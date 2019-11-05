package gui.print;

import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Test implements Printable {
	/**
	 * 
	 * @param Graphic指明打印的图形环境
	 * 
	 * @param PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×842点）
	 * 
	 * @param pageIndex指明页号
	 * 
	 **/
	public int print(Graphics graphics, PageFormat pf, int pageIndex) throws PrinterException {
		if (pageIndex != 0)
			return NO_SUCH_PAGE;
		// 要打印的内容
		graphics.drawString("河南郑州", 0, 10);
		return PAGE_EXISTS;

	}

	public static void main(String[] args) {
		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPrintable(new Test());// 设置打印类
		try {
			// 可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
			if (job.printDialog()) {
				job.print();
			}
		} catch (PrinterException e) {
			e.printStackTrace();
		}

	}

}