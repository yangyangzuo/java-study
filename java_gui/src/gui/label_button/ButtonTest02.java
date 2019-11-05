package gui.label_button;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTest02 {
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setLayout(new FlowLayout());
		
		Button b1 = new Button("b1");
		f.add(b1);
		ActionListener l1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("listener1");
			}
		};
		ActionListener l2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("listener2");
			}
		};
		ActionListener l3 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("listener3");
			}
		};
		b1.addActionListener(l1);
		b1.addActionListener(l2);
		b1.addActionListener(l3);
		b1.addActionListener(l1);
		ActionListener[] listeners = b1.getActionListeners();//该方法可以获取按钮上的所有监听器
		for(int i=0;i<listeners.length;i++){
			System.out.println(listeners[i]);
			//可以看出，同一个按钮上面可以注册多个监听器
			//也可以对同一个监听器注册多次
			//事件发生时，按照被添加的监听器的顺序，去执行监听器里面的动作
		}
		f.setVisible(true);
	}
}
