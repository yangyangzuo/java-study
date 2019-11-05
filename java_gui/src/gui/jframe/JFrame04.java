package gui.jframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
/**
 * 文档翻译:
 * https://docs.oracle.com/javase/tutorial/uiswing/components/toplevel.html
 * https://docs.oracle.com/javase/tutorial/uiswing/components/rootpane.html
 * @author gudandan
 *
 */
public class JFrame04
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
		
		JButton jb  = new JButton("shit");
		jb.setBounds(100,100,200,200);
		jf.getContentPane().add(jb);
		
		
		
		//https://docs.oracle.com/javase/tutorial/uiswing/components/toplevel.html
		//https://docs.oracle.com/javase/tutorial/uiswing/components/rootpane.html
		//文档翻译:
		//swing支持顶层容器类JFrame,JDialog,JApplet,当使用这些类的时候，要注意一下几点:
		//1.当显示在屏幕上时,每一个gui组件必须是容器层次中的一部分,一个容器层次实际是组件构成的一个树状结构，有一个顶级容器作为根
		//2.每一个gui组件只能被包含一次，如果一个组件已经包含在一个容器中，你又把他加到另一个容器中，则这个组件会从先前的容器移动到另一个容器中,查看JFrame044.java
		//3.每一个顶层容器都有一个content pane(内容面板)用来包含(直接/间接包含)可视的组件,查看JFrame,JDialog,JApplet的源码
		//4.在顶层容器重可以选择性的添加一个menu bar(可选),这个菜单栏一般是位于顶层容器之中，但是在content pane之外
		//在外观上，比如mac os系统中，这个菜单可以自主选择所放置的位置，比如：屏幕的最上面(mac,linux系统一般在屏幕最上面有一个横栏，可以用来放置图标，菜单选项等等，和windows不太一样)
		
		
		JMenuBar menubar = new JMenuBar();
		menubar.setBackground(Color.GREEN);
		menubar.setPreferredSize(new Dimension(200, 20));
        jf.setJMenuBar(menubar);
		//查看源码可以知道,实际layeredPane.add(menuBar, JLayeredPane.FRAME_CONTENT_LAYER);菜单其实加在了layeredPane里面
        //根据前面的分析，知道layeredPane里面实际有contentPane和JMenuBar
		
		
		//顶层容器和容器层次结构:
        //每一个程序使用Swing组件都必须有一个顶层容器,这个顶层容器是是容器层次结构的根，包含了所以在顶层容器中的swing组件
        //一个基于swing的gui程序都有一个容器层次结构，并且这个根是Jframe,
        //如果一个应用程序有一个主窗口和２个对话框，那么这个程序有３个包含层次,因此有３个顶级容器
        //一个容器层次以Jframe作为根，另外两个以JDialog作为他们容器层次的根
        //一个基于swing的applet至少一个容器层次结构,这个根是JApplet,
        //例如:一个applet带有一个dialog,则他们有２个容器层次，一个是以JApplet作为根，一个以为JDialog作为根
        
        //////////////如何添加组件到内容面板
        //jframe.getContentPane().add(mylabel,BorderLayout.CENTER);
        //根据Jframe02.java中分析可以知道,contentPanel实际是layeredPane.add(new JPanel(), JLayeredPane.FRAME_CONTENT_LAYER);
        //contentPane其实是一个Jpanel,Japnel继承了JComponent,使用的BorderLayout
        //所以定制一个content pane很容易
        //需要注意的是jframe.getContentPane()放回的是一个Container对象，如果你想利用Jcomponent对象的特性，有２种方法
        //１种是((JComponent)jframe.getContentPane()).add(component),类型转换
        //2种是创建一个component作为content pane
        //例如:
        //Create a panel and add components to it.
        //JPanel contentPane = new JPanel(new BorderLayout());
        //contentPane.setBorder(someBorder);
        //contentPane.add(someComponent, BorderLayout.CENTER);
        //contentPane.add(anotherComponent, BorderLayout.PAGE_END);
        //jFrame.setContentPane(contentPane);
        
        //为了方便add(),remove(),setLayout()方法都已经被重写，指向了contentPane对象,这意味着
        //jframe.add(component)等价于jframe.getContentPane().add(component)
        //需要注意的是仅仅是这３个方法进行了重写，其他方法没有指向contetnPane,例如:getLayout()方法不会返回通过setLayout()方法设置的layout
		
		
        
        
        //每一个顶层容器依赖着一个隐藏在内部的root pane,
        //root pane包含着layered pane和glass pane
        //layered pane包含着content pane和menu pane
        //glass pane位于组件的最上面，一般用来和input event做交互,在这上面进行绘制还可以覆盖其他的任意一个组件
        
        
        
        
        
        
        
        
        
        jf.getGlassPane().setBackground(Color.red);
        ((JPanel)jf.getGlassPane()).setOpaque(false);
        jf.getGlassPane().setVisible(true);
        
        
        
        
		
		jf.setVisible(true);
		// 与 Frame 不同，当用户试图关闭窗口时，JFrame 知道如何进行响应。用户关闭窗口时，默认的行为只是简单地隐藏JFrame。
		//要更改默认的行为，可调用方法 setDefaultCloseOperation(int)。
		//要使 JFrame 的行为与Frame实例相同，请使用setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)。

		// 即:如果不调用这个方法，试图关闭的时候系统默认会把这个窗体隐藏
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
