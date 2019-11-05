package gui.label_button;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ButtonTest01 {

	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setLayout(new FlowLayout());

		Button b1 = new Button("b1");
		f.add(b1);
		System.out.println(b1.getLabel());//b1,获得按钮上的文本字符串
		System.out.println(b1.getActionCommand());//b1,获得按钮的动作命令名称，如果这个值为null,则返回label的值
		//源码:
//		public String getActionCommand() {
//	    	return (actionCommand == null? label : actionCommand);
//	    }
		f.setVisible(true);
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		b1.setLabel("bbbb");
		//我们设置actionCommand的值
		b1.setActionCommand("actioncommand value");
		System.out.println(b1.getActionCommand());//此时得到actioncommand value
		
	}

}
