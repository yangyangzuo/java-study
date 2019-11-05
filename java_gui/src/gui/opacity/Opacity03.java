package gui.opacity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 如何设置一个背景透明的组件? 
 * 
 * JComponnet及其子类(轻量级swing组件)可以调用setOpaque(),使组件背景透明
 * 
 * JComponent.setOpaque()可以让一个组件背景透明
 * 
 * @author zuoyang
 *
 */
public class Opacity03 extends JDialog {
	public static void main(String[] args) throws InterruptedException {
		Opacity03 t = new Opacity03();
		t.setSize(500, 500);
		t.setLayout(null);
		// 这里定义contentpane背景颜色,主要是为了对比显示组件的背景是透明的
		t.getContentPane().setBackground(Color.red);

		// 定义一个自己的组件,显示出来
		MyPanel p = new MyPanel();
		p.setBounds(0, 0, 300, 300);
//		p.setOpaque(false);// 设置这个Jpanel组件背景透明,这样这个panel下面的组件就显示出来了
		t.add(p);

		
		t.setUndecorated(true);
		
		t.setVisible(true);
	}
}

class MyPanel extends JPanel {
	public MyPanel() {
		this.setBackground(Color.GREEN);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Image image = null;
		image = Toolkit.getDefaultToolkit().getImage("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/opacity/login_logo.png");
		g.drawImage(image, 0, 0, this);
	}

}
