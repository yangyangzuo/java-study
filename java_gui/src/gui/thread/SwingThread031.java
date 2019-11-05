package gui.thread;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 参考文档:
 * 
 * swing中的并发
 * 
 * https://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html
 * 
 * @author gudandan
 *
 */
public class SwingThread031 extends JFrame {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new SwingThread031();

			}
		});
	}

	public SwingThread031() {
		this.setSize(200, 200);
		this.setLocationRelativeTo(null);

		JButton b = new JButton("hello");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 该方法可以判断，当前的执行线程是否在event dispatch thread上执行
				System.out.println(SwingUtilities.isEventDispatchThread());
				
				
				// 此时的解决方案：不能把这个长时间等待的操作放到event dispatch thread中处理,应该放到一个新的线程中去
				new Thread(r).start();
			}
		});

		this.add(b);
		this.setVisible(true);
	}

	Runnable r=new Runnable(){
		@Override public void run(){
			getData();
		}
	};

	public void getData(){
			try {
				System.out.println("开始取数据");
				Thread.currentThread().sleep(5000);
				System.out.println("取到数据");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
