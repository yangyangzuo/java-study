package gui.jframe;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFrame02
{
	public static void main(String[] args)
	{
		//是否提供窗口装饰效果
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame jf = new JFrame("JFrame");
		jf.setBounds(100, 100, 300, 300);
		// JFrame继承了Frame，默认也是BorderLayout
		//这里修改为FlowLayout
		jf.setLayout(new FlowLayout());
		
		jf.getContentPane().setBackground(Color.RED);
		JButton jb  = new JButton("shit");
		jb.setBounds(100,100,200,200);
		jf.getContentPane().add(jb);
		jf.setVisible(true);

		//我们的组件为什么要放在ContentPane里面?
		
		//JFrame源码分析：
		//打开JFrame类的源码可以发现如下:
		//JFrame构造函数调用了frameInit()
		//frameInit()方法调用了setRootPane(createRootPane());代码中可以发现，实际是new JRootPane(),然后 JFrame.add(rootPane, BorderLayout.CENTER);
		//即:把一个JRootPane对象通过borderlayout添加到了框架的中央,那么JRootPane是什么东西呢？
		//JRootPane继承了JComponent，实际是一个JComponent对象,而JComponent又继承了Container
		//那么JRootPane里面又有什么东西?看JRootPane源码
		//JRootPane()构造函数执行了如下东西:
		//setGlassPane(createGlassPane());实际是JRootPane.add(new JPanel(),0)
		//setLayeredPane(createLayeredPane()); 实际是JRootPane.add(new JLayeredPane(), -1);
        //setContentPane(createContentPane());  实际是(setLayeredPane(createLayeredPane())函数中设置的layeredPane)layeredPane.add(new JPanel(), JLayeredPane.FRAME_CONTENT_LAYER);
		//在JRootPane的非构造方法里面有一个setJMenuBar()方法，实际执行了 layeredPane.add(menuBar, JLayeredPane.FRAME_CONTENT_LAYER);
		//说明可选的菜单是添加在JLayeredPane中的,这里就很明确了
		
		//由上面可以得出如下结论:
		//1.JFrame里面是一个JRootPane对象,这个对象是JFrame最底层的面板
		//2.JRootPane里面有2层面板GlassPanel和LayeredPane,而且GlassPanel在LayeredPane的上面(add()方法的第２个参数决定了面板的顺序),galssPanel显示在layeredPanel的上面，add()的索引顺序，可以参考gui.zorder中的原理讲解
		//设置完参数后，本来已经可以实现GlassPanel显示在最顶端，JRootPanel又做了一步确保这一点，他重写了Container.addImpl()方法，再次确保了这点，可以查看源代码
		//3.LayeredPane面板里面又添加了一个contentPane(可选的还可以添加一个JmenuBar,查看JFrame04.java)
		//4.jRootPane.setJMenuBar()方法可以看出来，JmenuBar实际会添加到layeredPane里面
		//层次结构就是这样
		
		//观察如下输出结果:
		//对于JFrame的内容面板分析,getParent()方法可以获得当前组件的父级容器
		System.out.println(jb.getParent());//javax.swing.JPanel[null.contentPane,说明按钮jb被添加在了contentPane上,这个对象实际是一个new JPanel()，和上面源码相符
		System.out.println(jb.getParent().getParent());//javax.swing.JLayeredPane[null.layeredPane,说明了contentPane是放在了layeredPane,和上面分析相符
		System.out.println(jb.getParent().getParent().getParent());//javax.swing.JRootPane[,说明了layeredPane是放在了JRootPane,和上面分析相符
		System.out.println(jb.getParent().getParent().getParent().getParent());//javax.swing.JFrame[frame0，说明了JFrame是放在了Frame里面(JFrame继承了Frame),和上面分析相符
		System.out.println(jb.getParent().getParent().getParent().getParent().getParent());//null
		//这里没有出现GlassPane,因为GlassPane和LayeredPane平级，而GlassPane里面没有放组件,所以这里没有显示
	}
}
