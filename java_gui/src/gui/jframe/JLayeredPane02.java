package gui.jframe;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.IllegalComponentStateException;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

/**
 * layeredPane 使用举例
 * 分层面板是一个有深度的容器，对于几个重叠的组件，其中的一个组件可以放置在其他组件的上面
 * 
 * 对于rootPane里面的layeredPane来说，里面会放置一个menu bar和一个conentPane
 * layeredPane提供的zOrder可以让一些组件显示在另一些组件的上面
 * 
 * 如果你把一些组件放置在rootPane中的LyeredPane里面，注意:
 * 你的组件在和其他组件一起显示的时候，可能显示的不正常，
 * 组件被添加到layeredPane的时候，会有一个组件的显示深度值,
 * JLayeredPane 为 JFC/Swing 容器添加了深度，允许组件在需要时互相重叠。
 * Integer 对象指定容器中每个组件的深度，其中编号较高的组件位于其他组件之上。
 * 
 * http://docs.oracle.com/javase/tutorial/uiswing/components/rootpane.html#layeredpane
 * http://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html
 * 
 * @author zuoyang
 *
 */
public class JLayeredPane02 {
	public static void main(String[] args) {
		JFrame jf = new JFrame("hello");
		
		//表示层级的维度，值越小，越显示在最底层，值越大，越显示在所有层级的最上面
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		
		JLabel label1 = new JLabel("111111111111");
		label1.setBackground(Color.red);
		label1.setBounds(0, 0, 100, 100);
		label1.setOpaque(true);
		layeredPane.add(label1, new Integer(1));//设置层级为1,组件添加到对应的层级中去
//		layeredPane.setLayer(label1, 1);
//		layeredPane.add(label1);
		
		JLabel label2 = new JLabel("222222222222");
		label2.setBackground(Color.yellow);
		label2.setBounds(90, 10, 100, 100);
		label2.setOpaque(true);
		layeredPane.add(label2, new Integer(2));//设置层级为2,组件添加到对应的层级中去
		
		JLabel label3 = new JLabel("333333333333");
		label3.setBackground(Color.blue);
		label3.setBounds(60, 60, 100, 100);
		label3.setOpaque(true);
		layeredPane.add(label3, new Integer(2));//设置层级为2,组件添加到对应的层级中去
		
		//此时我们看出，层级2中的所有元素，都显示在层级1之上
		//而对于层级2来说，同层元素遵循zorder,所以先添加的label2显示在label3之上
		
		
		jf.add(layeredPane);
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
		
		
		
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
		}
		layeredPane.setLayer(label2, new Integer(-100));
		//5秒之后，我们把label放到了层级为-100，此时发现，他显示在了最下面
		
		//其实JlayerdPane定义了7个层级，当添加组件的时候，可以根据类中定义的7个维度来，当然你也可以自己定义维度
		//如果多人开发的话，无疑使用类中默认约定的5个层级要好的多
	}

}