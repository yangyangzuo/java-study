package gui.opacity;

import java.awt.Color;
import java.awt.Frame;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;

public class Opacity01 {

	public static void main(String[] args) {
		JFrame jframe = new JFrame();
		jframe.setBackground(Color.YELLOW);
		jframe.setSize(200, 100);
		jframe.setTitle("lovemu");
		jframe.setLocationRelativeTo(null);
		// 发现背景颜色不是红色,为什么?
		// JFrame的面板里面是rootpane(layoutpane(contentpane,menupane),glasspane)
		// 最上面显示的contentpane和glasspane,而contentpane默认前景颜色是238,238,238是灰色的,所以这里显示的灰色的
		System.out.println(jframe.getContentPane().getBackground());//[r=238,g=238,b=238]
		jframe.setVisible(true);

		// 对于frame来说,显示的是他自己的颜色,所以是黄色
		Frame frame = new Frame();
		frame.setBackground(Color.yellow);
		frame.setBounds(0, 0, 200, 200);
		frame.setVisible(true);
	}

}