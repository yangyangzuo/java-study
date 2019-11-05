package gui.print;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;

public class Dialog01 {
	public static void main(String[] args) {
		
		PrinterJob printerJob = PrinterJob.getPrinterJob();
		
		//页面设置对话框
		//根据传入的pageformat对象初始化一个页面设置对话框
		//如果用户在对话框中点击确定，则会根据用户的选择创建一个pageformat实例返回,否则返回原始的未修改的pageFormat实例
		//一般打印的时候，不会提示用户进行打印页面设置
		PageFormat pageFormat = printerJob.pageDialog(new PageFormat());
		
		pageFormat = printerJob.pageDialog(printerJob.defaultPage());
		
		
		
	}
}
