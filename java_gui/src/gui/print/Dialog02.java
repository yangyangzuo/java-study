package gui.print;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

public class Dialog02 {
	public static void main(String[] args) {
		
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		
		//打印对话框
		printerJob.printDialog();
		
		
	}
}
