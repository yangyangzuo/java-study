package gui.paint;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JButton;

/**
 * 
 * 如果一个重量容器,覆盖了paint()方法,则这个paint()方法里面必须调用super.pait()方法,否则这个容器里面的轻组件会不显示,或者显示异常
 * 
 * @author zuoyang
 *
 */
public class Question01 extends Frame {
	public static void main(String[] args) throws InterruptedException {
		Question01 t = new Question01();
		t.setSize(600, 600);
		t.setLayout(null);

		JButton jbutton1 = new JButton("JButton");
		jbutton1.setBounds(20, 20, 100, 100);
		t.add(jbutton1);

		Button button2 = new Button("button2");
		button2.setBounds(150, 150, 100, 100);
		t.add(button2);

		t.setVisible(true);
	}

	@Override
	public void paint(Graphics g) {
		//解决办法:
		//调用super.paint(g)方法,确保当前重容器重的轻量组件可以正确绘制
		 super.paint(g);
	}

}
