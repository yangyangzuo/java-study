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
public class JLayeredPane01 {
	public static void main(String[] args) {
		JFrame jf = new JFrame("hello");
		
		//我们知道container.add()实际是调用了contianer.addImpl()方法
		//添加组件的时候，会根据一个添加先后，生成一个索引，来决定组件重叠时显示的优先顺序,gui.zorder包中有解释
		//在JLayeredpane中，他重写了这个addImple()方法,又添加了一个层级的概念，这个层级是一个维度
		//一个层级中的所有组件会覆盖底层级的所有组件
		//每个层级中又有一个组件的坐标索引，同一层级中的组件的索引，当组件重叠时，和Container中原理一样是一样的
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setLayout(null);
		
		JLabel label1 = new JLabel("111111111111");
		label1.setBackground(Color.red);
		label1.setBounds(0, 0, 100, 100);
		label1.setOpaque(true);
		layeredPane.add(label1, new Integer(1));//设置层级为1,组件添加到对应的层级中去
		
		JLabel label2 = new JLabel("222222222222");
		label2.setBackground(Color.yellow);
		label2.setBounds(30, 30, 100, 100);
		label2.setOpaque(true);
		layeredPane.add(label2, new Integer(1));//设置层级为1,组件添加到对应的层级中去
		
		JLabel label3 = new JLabel("333333333333");
		label3.setBackground(Color.blue);
		label3.setBounds(60, 60, 100, 100);
		label3.setOpaque(true);
		layeredPane.add(label3, new Integer(1));//设置层级为1,组件添加到对应的层级中去
		
		//注意:不要调用了add(label3,1),如果最后一个是int类型，则此时第二个参数就不表示层级了，具体查看Container.add的重载方法
		//上面几个lable都是在同一个层级，所以他们组件重叠时，显示顺序遵循zorder,即:先添加的显示在最上面
//		container.add(comp, Object);-->addImpl(comp, constraints, -1);此时传递第二个参数表示层级
//		container.add(comp, int);-->addImpl(comp, null, index);此时传递的第二个参数表示同一层中，放到一个具体的索引位置
		
		jf.add(layeredPane);
		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}

}