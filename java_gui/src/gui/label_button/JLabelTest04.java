package gui.label_button;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JLabelTest04 extends JApplet implements SwingConstants {
	

	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		//定义一个文本标签,标签的内容相对于标签画布的水平位置是中间
		JLabel label = new JLabel("Action!",SwingConstants.CENTER);
		//label标签里面可以包含一个图片和文字
		ImageIcon icon = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label/slate.gif");
		label.setFont(new Font("Times-Roman", Font.ITALIC, 20));
		label.setIcon(icon);
		label.setBackground(Color.red);
		label.setBounds(0, 0, 200, 200);
		label.setOpaque(true);
		//设置标签内的文本相对其图像的水平位置
		//SwingConstants对象中的:LEFT, CENTER, RIGHT, LEADING, or TRAILING (the default).
		label.setHorizontalTextPosition(SwingConstants.LEFT);//设置文本位于图像水平方向的左边
		//SwingConstants对象中的:TOP, CENTER (the default), or BOTTOM.
		label.setVerticalTextPosition(SwingConstants.TOP);//文本相对于图像垂直方向的上面
		
		//可以设置文本标签被禁用的状态,当被禁用的时候,系统提供一个默认的外观
		label.setEnabled(false);
		contentPane.add(label);
		
		
		
		//定义一个文本标签,标签的内容相对于标签画布的水平位置是中间
		JLabel label1 = new JLabel("Action!",SwingConstants.CENTER);
		label1.setFont(new Font("Times-Roman", Font.ITALIC, 20));
		label1.setIcon(icon);
		label1.setBackground(Color.blue);
		label1.setBounds(200, 0, 200, 200);
		label1.setOpaque(true);
		//设置标签内的文本相对其图像的水平位置
		//SwingConstants对象中的:LEFT, CENTER, RIGHT, LEADING, or TRAILING (the default).
		label1.setHorizontalTextPosition(SwingConstants.LEFT);//设置文本位于图像水平方向的左边
		//SwingConstants对象中的:TOP, CENTER (the default), or BOTTOM.
		label1.setVerticalTextPosition(SwingConstants.TOP);//文本相对于图像垂直方向的上面
		
		
		//我们还可以提供另一个图片,当文本标签被禁用的时候可以使用
		label1.setDisabledIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label/ladybug.gif"));
		label1.setEnabled(false);
		
		contentPane.add(label1);
		
		this.setSize(900, 800);
	}
}
