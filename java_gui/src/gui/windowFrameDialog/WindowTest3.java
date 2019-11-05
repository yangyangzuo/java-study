package gui.windowFrameDialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.TextField;
import java.awt.Window;

public class WindowTest3 {
	public static void main(String[] args) {
		Frame frame = new Frame();
		frame.setVisible(true);
		
		Window window = new Window(frame);//给window添加一个所有者，此时当前文本域可以获得焦点，可以输入文字
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
