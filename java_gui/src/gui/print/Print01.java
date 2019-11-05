package gui.print;

import java.awt.Graphics;
import java.awt.print.PageFormat;
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
 */
public class Print01  {

	/**
	 * 设置特定的打印格式
	 * @param args
	 */
	public static void main(String[] args) {
		
		//1.创建一个打印任务
		PrinterJob printerJob = PrinterJob.getPrinterJob();

		
		///////////////////////////////////////////////设置特定打印格式
		//ISO 216定义了A、B、C三组纸张尺寸,纸张规格如下:
		//A0	841 x 1189mm
		//A1	594 x 841mm
		//A2	420 x 594mm
		//A3	297 x 420mm
		//A4	210 x 297mm
		//A5	148 x 210mm
		//A6	105 x 148mm
		//A7	74 x 105mm
		//A8	52 x 74mm
		//A9	37 x 52mm
		//A10	26 x 37mm
		//B0	1000 x 1414mm
		//B1	707 x 1000mm
		//B2	500 x 707mm
		//B3	353 x 500mm
		//B4	250 x 353mm
		//B5	176 x 250mm
		//B6	125 x 176mm
		//B7	88 x 125mm
		//B8	62 x 88mm
		//B9	44 x 62mm
		//B10	31 x 44mm
		//C0	917 x 1297mm
		//C1	648 x 917mm
		//C2	458 x 648mm
		//C3	324 x 458mm
		//C4	229 x 324mm
		//C5	162 x 229mm
		//C6	114 x 162mm
		//C7	81 x 114mm
		//C8	57 x 81mm
		//C7/6 	81 x 162mm
		//DL	110 x 220mm
		//小票打印机纸的规格:
		//58(48)*210mm,5.8cm*21cm
		//58(48)*297mm,5.8cm*29.7cm
		//58(48)*3276mm,5.8cm*32.7cm		
		
		//PageFormat描述要打印的页面大小和方向
		//该类的getHeight(),getWidth(),getImageableWidth(),getImageableHeight()返回的结果都是以 1/72 英寸为单位
		//换算单位如下:
		//1磅(点)==1/72英寸
		//1英寸==25.4mm
		//1磅(点)==25.4/72mm=0.3527mm
		//1mm=72/25.4磅(点)=2.8346磅(点)

		//这里计算一下A4纸大概是多少磅(点)
		//A4==210 x 297mm == (210*72/25.4) x (297*72/25.4)磅(点) == 595.27 x 855.36磅(点)
		//小票纸
		//48x210mm == (48*72/25.4) x (210*72/25.4)磅(点) == 136.062 x 595.27磅(点)
		//48x297mm == (48*72/25.4) x (297*72/25.4)磅(点) == 136.062 x 841.889磅(点)
		//48x3276mm == (48*72/25.4) x (3276*72/25.4)磅(点) == 136.062 x 9286.299磅(点)
		
		//PageFormat描述了Page的打印方式(横向/纵向),Page的尺寸,可绘图区域大小
		//可绘图区域受大多数打印机的margin限制
		PageFormat pageFormat = printerJob.defaultPage();
		//返回页面的宽度和高度,136.20472440944883:595.1622047244094
		System.out.println(pageFormat.getWidth() + ":" + pageFormat.getHeight());
		//返回页面可成像区域的宽度和高度,-7.795275590551181:451.16220472440943
		System.out.println(pageFormat.getImageableWidth() + ":" + pageFormat.getImageableHeight());
		System.out.println("getOrientation:" + pageFormat.getOrientation());//默认是PageFormat.PORTRAIT,原点在左上方,纵向打印
		//如果向设置其他打印方式，可以调用pageFormat.setOrientation()方法来设置
		//例如:pageFormat.setOrientation(PageFormat.LANDSCAPE)设置为横向打印
		//在printerJob.printDialog()方法弹出的打印对话框里面,可以看出来当前要打印的文档是怎样的一个打印模式
		///////////////////////////////////////////////
		
		
		
		
		//2.填充打印内容和打印格式
		printerJob.setPrintable(new PrintContent01(), pageFormat);
		//printerJob.setCopies(2);//制定打印2份
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
class PrintContent01 implements Printable{
	@Override
	//print()方法被打印系统调用，类似组件在被绘制的时候Component.paint()方法被调用一样	
	//打印系统调用Printable.print()方法，按照pageIndex为0,1,2....直到print()方法返回NO_SUCE_PAGE
	//即:print(graphics,pageFormat,0),print(graphics,pageFormat,1),print(graphics,pageFormat,2).....
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {		
		System.out.println("pageIndex:" + pageIndex);
		//该方法如果返回表示PAGE_EXISTS,表示请求的页面被呈现
		//该方法如果返回表示NO_SUCH_PAGE,表示 pageIndex 太大以及请求的页面不存在
		
		//这里表示当前只打印一页,因为pageIndex从0开始索引,打印第一页的时候pageIndex为0,当打印第二页的时候pageIndex为1,此时表示已经不用打印了，所以返回NO_SUCH_PAGE
		if (pageIndex == 1){			
			return NO_SUCH_PAGE;//如果请求的page不存在,则应该返回NO_SUCH_PAGE,表示pageIndex索引指定的这个page不存在，不用打印渲染
		}
		else{
			graphics.drawString("河南郑州1", 0, 10);		
			return PAGE_EXISTS;//PAGE_EXISTS,表示当前页需要打印渲染
		}
	}
	//本地打印系统第一次调用print(*,*,0),此时绘制要打印的内容,返回NO_SUCH_PAGE,告知打印机，把当前内容在打印机中打印渲染出来
	//本地打印系统第二次调用print(*,*,1),此时要绘制的内容已经打印出来，因此返回NO_SUCH_PAGE,告诉打印机，绘制任务已经完成,结束打印任务
	//本程序只是针对打印一页的情况
	//注意print()方法的调用和Component.paint()方法调用是一样的,有一个线程会一直调用,直到当前 索引为pageIndex需要打印的内容完全绘制出来
	
}