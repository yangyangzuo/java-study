package gui.label_button;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JLabelTest02 extends JApplet implements SwingConstants {
	

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
		contentPane.add(label);
		
		
		
		//定义一个文本标签,标签的内容相对于标签画布的水平位置是中间
		JLabel label1 = new JLabel("Action!",SwingConstants.CENTER);
		label1.setVerticalAlignment(SwingConstants.CENTER);//设置标签的内容相对于标签画布的垂直位置是中间
		label1.setFont(new Font("Times-Roman", Font.ITALIC, 20));
		label1.setIcon(icon);
		label1.setForeground(Color.red);
		label1.setBackground(Color.green);
		label1.setBounds(200, 0, 200, 200);
		label1.setOpaque(true);
		//设置标签内的文本相对其图像的水平位置
		//SwingConstants对象中的:LEFT, CENTER, RIGHT, LEADING, or TRAILING (the default).
		label1.setHorizontalTextPosition(SwingConstants.CENTER);//设置文本位于图像水平方向的中间
		//SwingConstants对象中的:TOP, CENTER (the default), or BOTTOM.
		label1.setVerticalTextPosition(SwingConstants.CENTER);//文本相对于图像垂直方向的中间
		contentPane.add(label1);
		
		
		
		//定义一个文本标签,标签的内容相对于标签画布的水平位置是中间
		JLabel label2 = new JLabel("Action!",SwingConstants.CENTER);
		label2.setVerticalAlignment(SwingConstants.CENTER);//设置标签的内容相对于标签画布的垂直位置是中间
		label2.setFont(new Font("Times-Roman", Font.ITALIC, 20));
		label2.setIcon(icon);
		label2.setBackground(Color.blue);
		label2.setBounds(400, 0, 200, 200);
		label2.setOpaque(true);
		//设置标签内的文本相对其图像的水平位置
		//SwingConstants对象中的:LEFT, CENTER, RIGHT, LEADING, or TRAILING (the default).
		label2.setHorizontalTextPosition(SwingConstants.RIGHT);//设置文本位于图像水平方向的中间
		label2.setVerticalTextPosition(SwingConstants.BOTTOM);//文本相对于图像垂直方向的底部
		contentPane.add(label2);
		
		
		//定义一个文本标签,标签的内容相对于标签画布的水平位置是中间
		JLabel label3 = new JLabel("Action!",SwingConstants.CENTER);
		label3.setVerticalAlignment(SwingConstants.CENTER);//设置标签的内容相对于标签画布的垂直位置是中间
		label3.setFont(new Font("Times-Roman", Font.ITALIC, 20));
		label3.setIcon(icon);
		label3.setBackground(Color.yellow);
		label3.setBounds(600, 0, 200, 200);
		label3.setOpaque(true);
		//设置标签内的文本相对其图像的水平位置
		//SwingConstants对象中的:LEFT, CENTER, RIGHT, LEADING, or TRAILING (the default).
		label3.setHorizontalTextPosition(SwingConstants.CENTER);//设置文本位于图像水平方向的左边
		label3.setVerticalTextPosition(SwingConstants.TOP);
		contentPane.add(label3);
		
		//按照上面的组合的话
		//JLabel中的内容中，文本相对于图像的位置有9种组合
		//结合到前面的相对于JLabel画布的9中组合，一共有81种组合
		this.setSize(900, 800);
	}
}
