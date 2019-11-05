package gui.jframe;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * JFC:Java Foundation Classes
 * 
 * Swing是非线程安全的
 * 
 * Swing提供了一套gui组件，为了保证可移植性，它完全用java编写,所有的Swing都作为JComponet的子类来实现
 * 而JComponent类又是从Container类继承而来，Swing组件从JComponent继承如下功能：
 * 边框，双缓冲(Swing组件默认是双缓冲的),提示框
 * 
 * 
 * JFrame的结构体系：
 * 
 * 参考：api: JRootPane类和该类中的一个链接描述(请参阅 The Java Tutorial 中的 How to Use Root Panes
 * 一节)
 * 
 * 
 * JRootpane 由一个 glassPane 和一个可选的 menuBar 以及一个 contentPane组成。
 * 
 * JLayeredPane 负责管理 menuBar 和 contentPane
 * 
 * glassPane 位于所有窗格之上，以便能够截取鼠标移动。
 * 
 * 由于glassPane（与 contentPane 类似）可以是一个任意的组件，也可以设置 glassPane 来进行绘制。这样 glassPane
 * 上的线条和图像可涵盖其下的窗体，不受其边界的限制。 尽管 menuBar 组件为可选，但 layeredPane、contentPane 和
 * glassPane 总是存在的。试图将它们设置为 null 将生成异常。
 * 
 * 要将组件添加到 JRootPane（可选的菜单栏除外），您可以将对象添加到 JRootPane 的 contentPane，如下所示：
 * 
 * rootPane.getContentPane().add(child); 可用同样的方法设置布局管理器、移除组件以及列出子级等。所有这些方法都是在
 * contentPane 上而不是 JRootPane 上调用的。
 * 
 * @author Administrator
 * 
 */
public class JFrame022
{
	public static void main(String[] args)
	{
		JFrame jf = new JFrame("JFrame");
		jf.setBounds(100, 100, 300, 300);
		jf.setLayout(null);
		JButton b = new JButton("button");
		b.setBounds(100, 100, 60, 30);
		jf.add(b);
		

		//JFrame-->上面JRootPane
		System.out.println(jf==jf.getRootPane().getParent());
		
		
		//JRootPane-->上面JLayeredPane
		System.out.println(jf.getRootPane()==jf.getLayeredPane().getParent());
		//JRootPane-->上面glassPane 
		System.out.println(jf.getGlassPane());
		System.out.println(jf.getRootPane()==jf.getGlassPane().getParent());
		
		
		
		System.out.println(jf.getContentPane());
		//JLayeredPane-->上面contentPane
		System.out.println(jf.getLayeredPane()==jf.getContentPane().getParent());
		
		
		//新组件默认被添加到了contentPane上面
		System.out.println(b.getParent()==jf.getContentPane());
		
		/*
						JFrame
						   |
						   |(JFrame里面放置的是JRootPane)
						   |
						JRootPane
						   |
						   |(JRootPane里面放置的是JLayeredPane和glassPane,而且glassPane在JRootPane的上面)
						   |
				-----------------------------------------------------------------------------------------
				|																						|
				|																						|
			JLayeredPane																				|
			  	|																						|
			  	|																						|
			  	|(JLayeredPane里面放置了ContentPane和一个可选的JMenuBar)									|
			  	|																						|
			  	|--------------------------------														|
			  	|								|														|
			  	|								|														|
			ContentPane 					JMenuBar													|
				|																						|
				|																						|	
				|																						|
				|																						|
			新组件b(用户创建的组件，是放置在ContentPane里面)													|
																										|
																										|
																										|
																							glassPane(java.awt.Component)
			
		*/
		
		
		
		
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}

