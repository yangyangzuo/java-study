package gui.label_button;

import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class JLabelTest01 extends JApplet implements SwingConstants {
	

	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		//定义一个文本标签
		JLabel label = new JLabel("Action!");
		//label标签里面可以包含一个图片和文字,也可以只包含其中的一个
		ImageIcon icon = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label/slate.gif");
		label.setFont(new Font("Times-Roman", Font.ITALIC, 20));
		label.setIcon(icon);
		label.setBackground(Color.red);
		//下面两个方法可以设置label标签里面的内容(包括图片和文字)相对于label标签画布的位置
		//如下内容设置位于label标签画布的左上角
		//设置内容在y方向的位置/垂直方向
		//继承的SwingConstants中的常量:TOP, CENTER (the default), or BOTTOM.
		label.setVerticalAlignment(SwingConstants.TOP);//可以是top,center,bottom，默认是center
		//设置内容在x方向的位置/水平方向
		//继承的SwingConstants中的常量:LEFT, CENTER, RIGHT, LEADING(the default) or TRAILING.
		//leading和Traling对于对于阅读习惯来说(从左到右/从右到左)的,
		//loading表示阅读习惯的头部开始的地方，对于从左到右，这个leading表示左边,trailing表示右边
		label.setHorizontalAlignment(SwingConstants.LEFT);//对于我们的阅读习惯来说这个是LEADING
		label.setBounds(0, 0, 200, 200);
		label.setOpaque(true);
		contentPane.add(label);
		
		
		JLabel label1 = new JLabel("Action!");
		label1.setFont(new Font("Times-Roman", Font.ITALIC, 20));
		label1.setIcon(icon);
		label1.setBackground(Color.green);
		label1.setVerticalAlignment(SwingConstants.BOTTOM);
		label1.setHorizontalAlignment(SwingConstants.LEFT);
		label1.setBounds(200, 0, 200, 200);
		label1.setOpaque(true);
		contentPane.add(label1);
		
		JLabel label2 = new JLabel("Action!");
		label2.setFont(new Font("Times-Roman", Font.ITALIC, 20));
		label2.setIcon(icon);
		label2.setBackground(Color.blue);
		label2.setVerticalAlignment(SwingConstants.BOTTOM);
		label2.setHorizontalAlignment(SwingConstants.RIGHT);
		label2.setBounds(400, 0, 200, 200);
		label2.setOpaque(true);
		contentPane.add(label2);
		
		//默认水平方向是LEADING(the default)，显示在左边
		//默认垂直方向是Center,在中间
		JLabel label3 = new JLabel("Action!");
		label3.setFont(new Font("Times-Roman", Font.ITALIC, 20));
		label3.setIcon(icon);
		label3.setBackground(Color.yellow);
		label3.setBounds(600, 0, 200, 200);
		label3.setOpaque(true);
		contentPane.add(label3);
		
		//按照上面的组合的话
		//JLabel中的内容相对于JLabel画布背景的位置有9种组合
		this.setSize(900, 800);
	}
}
