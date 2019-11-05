package gui.label_button;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JLabelTest03 extends JApplet implements SwingConstants {
	

	public void init() {
		Container contentPane = getContentPane();
		contentPane.setLayout(null);
		
		//定义一个文本标签,标签的内容相对于标签画布的水平位置是中间
		JLabel label = new JLabel("Action!",SwingConstants.CENTER);
		//label标签里面可以包含一个图片和文字
		ImageIcon icon = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/slate.gif");
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
		
		//如果jlabel中有文字和图像，这个方法可以设置文字和图像之间的距离
		label.setIconTextGap(30);
		contentPane.add(label);
		
		this.setSize(900, 800);
	}
}
