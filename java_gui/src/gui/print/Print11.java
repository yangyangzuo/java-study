package gui.print;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.PrintJob;
import java.awt.Toolkit;

public class Print11 extends Frame {

	public static void main(String[] args) {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		//把一个组件通过打印机打印出来
		Frame frame = new Frame();
		frame.setSize(100, 100);
		frame.add(new Button("hello"));
		frame.setVisible(true);
		
		
		PrintJob printJob = tk.getPrintJob(frame, "打印任务标题", null,null);
		if(printJob!=null){
			Graphics pg = printJob.getGraphics();
			if (pg != null) {
				try {
					frame.printAll(pg);
				} finally {
					pg.dispose();
				}
			}
			printJob.end();
		}
	}
}
