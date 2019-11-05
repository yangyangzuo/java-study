package gui.print;

import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class PrintService01 {

	public static void main(String[] args) {
		
		// 打印机服务
		// 查看当前电脑有哪些可用的打印机(打印服务)
		PrintService[] services = PrinterJob.lookupPrintServices();
		for (int i = 0; i < services.length; i++) {
			System.out.println(services[i]);
			System.out.println(services[i].getName());
		}

		// 查看当前电脑默认使用的打印机服务
		javax.print.PrintService defaultPrintService = PrinterJob.getPrinterJob().getPrintService();
		System.out.println(defaultPrintService);

		// 当前电脑默认的打印机服务
		defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
		System.out.println(defaultPrintService);

	}

}
