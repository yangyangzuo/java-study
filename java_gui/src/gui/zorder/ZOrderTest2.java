package gui.zorder;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Rectangle;

import javax.swing.JButton;

public class ZOrderTest2 extends Frame {
	public static void main(String[] args) {
		ZOrderTest2 frame = new ZOrderTest2();
		frame.setBounds(new Rectangle(500, 500));
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);

		JButton b1 = new JButton("11111111111111111111111");
		b1.setBounds(30,205,500,30);
		b1.setBackground(Color.GRAY);
		frame.add(b1);

		JButton b2 = new JButton("2222222222222222222222");
		b2.setBounds(130,60,200,200);
		b2.setBackground(Color.green);
		frame.add(b2);
		
		JButton b3 = new JButton("33333333333333333333333");
		b3.setBounds(100,100,200,200);
		b3.setBackground(Color.blue);
		frame.add(b3);
		// 当前容器添加了3个轻组件，当组件重叠的时候，组件的显示顺序是，先添加的显示在最上面，后添加的显示在最下面

		frame.setVisible(true);

	}
}
