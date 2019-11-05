package gui.windowFrameDialog;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
public class DialogTest02 {
	public static Frame f;
	public static void main(String[] args) {
		f = new Frame();
		f.setBounds(new Rectangle(500, 500));
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		Button b = new Button("hello");
		b.setBounds(50,50,50,50);
		f.add(b);
		f.setVisible(true);
		
		
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				final Dialog dialog = new Dialog(f,true);//当前对话框是模态对话框
				//模态对话框会阻塞他祖先中多有其他窗口的输入操作，而且还阻塞对话框显示操作线程的执行
				
				dialog.setBounds(new Rectangle(500, 500));
				dialog.setLocationRelativeTo(null);
				dialog.addWindowListener(new WindowAdapter() {

					@Override
					public void windowClosing(WindowEvent e) {
						dialog.dispose();
					}
				});
				dialog.setVisible(true);//如果当前是模态对话框，则当对话框显示的时候，对话框显示操作的线程执行
				//当模态对话框显示的时候，当前线程会阻塞，而对话框显示操作线程会执行，只有对话框线程关闭(对话框关闭)的时候，
				//当前主线程才会执行，即才会执行打印hello
				//下面的代码是执行不了的，只有关闭了模态对话框，才会打印出hello
				//如果上面的new Dialog(f,false);是非模态的，则不会阻塞，hello会立即打印出来
				System.out.println("hello");//当前代码会被阻塞
				
			}
		});
		
	}
}
