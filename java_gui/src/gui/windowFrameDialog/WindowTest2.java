package gui.windowFrameDialog;

import java.awt.Color;
import java.awt.TextField;
import java.awt.Window;

public class WindowTest2 {
	public static void main(String[] args) {
		
		Window window = new Window(null);//如果window不指定所有者，则当前window无法获得焦点，例如：当前window中的文本域无法获得焦点输入内容
		window.setBounds(0, 0, 500, 500);
		window.setLocationRelativeTo(null);
		window.setLayout(null);
		
		TextField t  = new TextField();
		t.setBounds(0, 0, 200, 50);
		t.setBackground(Color.red);
		window.add(t);
		
		window.setVisible(true);
	}
}
