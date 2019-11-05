package gui.components;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 布局管理器 ：
 * 
 * 容器中组件的布局通常是有布局管理器来控制，每个container(比如：Panel/Frame)都有一个和他相关的默认布局管理器，
 * 它可以通过setLayout来改变
 * 
 * 布局管理器负责决定布局方针以及其容器的每一个子组件的大小
 * 
 * 只有容器才可以添加其他的组件，添加的组件需要给他一个位置用来显示，所以java中的每个容器组件都有一个默认的布局管理器
 * 
 * 窗口(window对象，及其字类,比如：Frame)的默认布局是 BorderLayout
 * 
 * 面板(panel及其子类)的默认布局管理器是 FlowLayout 布局管理器。
 * 
 * 容器内的布局管理器完全控制容器内的所有组件，在这种情况下，用户使用java语言提供的setLocation(),setSize()和setBounds()
 * 
 * 等方法设置组件的属性，这些设置都会被布局管理器覆盖，因为布局管理器负责各个组件的大小和位置
 * 
 * 不同的布局管理器使用不同的算法和策略，容器可以通过选择不同的布局管理器来决定布局
 * 
 * 注意：
 * 
 * 如果把layout设置为null,则组件的大小默认为0，需要显示指定组件的的大小
 * 
 * 
 * 为了保证组件的平台独立性，awt中引入了peer模型来实现，但是还不足以保证GUI程序的平台独立性 ，因为平台的不同，组件在平台上的布局不同
 * 
 * 所以awt引入了布局管理器来管理组件的布局
 * 
 * 
 * 
 * 
 * 作用：
 * 
 * 负责决定布局方针以及容器的每个子组件的大小
 * 
 * FlowLayout:Panel(子类：Applet,JApplet),从上到下，从做到右，对组件逐行地进行定位,
 * 和其他布局管理器不同的是flowLayout布局管理器不限制它所管理的组件的大小，而是允许他们有自己的最佳大小
 * 
 * BorderLayout:window(子类：Dialog,Frame,JWindow等等)
 * 
 * GridLayout:用许多行和列来创建管理程序，然后组件就填充到由团粒程序规定的单元中,GridLayout
 * 类是一个布局处理器，它以矩形网格形式对容器的组件进行布置。容器被分成大小相等的矩形，一个矩形中放置一个组件
 * 他把区域分为行数和列数的网格状布局，然后组件按照由做至右，由上而下的次序排列填充到各个单元中
 * 网格布局管理器总是忽略组件的最佳大小，而是根据提供的行和列进行平分，该布局管理器中的所有单元格的宽度和高度都是一样的
 * 
 * 
 * CardLayout:卡片式布局,它将容器中的每个组件看作一张卡片。一次只能看到一张卡片，容器则充当卡片的堆栈。 当容器第一次显示时，第一个添加到
 * CardLayout 对象的组件为可见组件 能帮组用户实现多个成员共享同一个显示空间，并且一次只显示一个容器组件的内容
 * CardLayout把容器分为许多层，每层的显示空间占据整个容器的大小
 * 
 * GridBagLayout:在网格的基础上提供更复杂的布局管理器
 * 网格包布局管理器，它是在网格的基础上提供复杂的布局，是最灵活，最复杂的布局管理器，gridBagLayout不需要足见的尺寸一致，允许组件扩展到多行多列
 * 每个GridBagLayout对象都维护了一组动态的，矩形的网格单元，每个组件占有一个或多个单元，所占有的网格单元称为组件的显示区域
 * 
 * BoxLayout,DefaultMenuLayout,GroupLayout,OverlayLayout,SpringLayout等等
 * 
 * @author Administrator
 * 
 */
public class Gui_08_LayoutTest 
{
	public static void main(String[] args)
	{

		Frame f = new Frame("flowLayout");
		f.setBounds(300, 300, 300, 300);
		// 先取消Frame的默认Borderlayout布局管理器
		f.setLayout(null);

		// ////////////////////////////////////////
		// FlowLayout:Panel及其子类,Applet,JApplet
		// 流式布局管理器:从左到右，从上到下进行流式布局
		Panel p = new Panel();
		p.setBounds(100, 100, 200, 200);
		p.setBackground(Color.WHITE);
		p.add(new Button("button1"));
		p.add(new Button("button2"));
		p.add(new Button("button3"));
		p.add(new Button("button4"));
		p.add(new Button("button5"));

		// 流式布局管理器总是显示组件最合适的大小
		// 当改变窗体的大小的时候，组件还是显示为最佳的大小，组件的大小不会改变
		f.add(p);
		f.setSize(500, 500);
		f.setVisible(true);

		// ////////////////////////////////////////
		// BorderLayout:Window及其子类,Frame,Dialog,JFrame等等
		// 这是一个布置容器的边框布局，它可以对容器组件进行安排，并调整其大小，使其符合下列五个区域：北、南、东、西、中。
		// 每个区域最多只能包含一个组件，并通过相应的常量进行标识：NORTH、SOUTH、EAST、WEST、CENTER。
		// 当使用边框布局将一个组件添加到容器中时，要使用这五个常量之一
		Frame f1 = new Frame("borderLayout");

		p = new Panel();
		p.setSize(250, 300);
		p.setBackground(Color.BLACK);
		// 设置布局管理器为BorderLayout
		p.setLayout(new BorderLayout());
		p.add(new Button("button1"), BorderLayout.NORTH);
		p.add(new Button("button2"), BorderLayout.SOUTH);
		p.add(new Button("button3"), BorderLayout.CENTER);
		p.add(new Button("button4"), BorderLayout.EAST);
		p.add(new Button("button5"), BorderLayout.WEST);

		// 默认会放在中间 ，会覆盖中间的组件button3
		// p.add(new Button("Button6"));
		f1.add(p);
		f1.setSize(500, 500);
		f1.setVisible(true);

		// ////////////////////////////////////////
		// 网格布局管理器,总是忽略组件的最佳大小
		// 总是根据窗体的大小，按照网格布局管理器的行数/列数来分割
		// 总是先按行数优先的原则来填充元素 ,注意，当元素不足以/多于网格的划分个数时，注意元素的布局形式 
		// GridLayout
		Frame f2 = new Frame("GridLayou");
		// 3行，5列
		GridLayout gl = new GridLayout(3, 5);
		f2.setLayout(gl);
		f2.add(new Button("1"));
		f2.add(new Button("2"));
		f2.add(new Button("3"));
		f2.add(new Button("4"));
		f2.add(new Button("5"));
		f2.setVisible(true);

		
		// ////////////////////////////////////////
		// CardLayout,卡片式布局管理器 
		Frame f3 = new Frame("CardLayout");
		Button b1 = new Button("1");
		f3.add(b1);
		f3.add(new Button("2"));
		f3.add(new Button("3"));
		f3.add(new Button("4"));
		//这个布局管理器需要在添加组件的后面来设置，否则报错，很奇怪
		CardLayout cl = new CardLayout();
		f3.setLayout(cl);
		
		//b1添加切换卡片事件
		class Act implements ActionListener
		{
			private CardLayout cl;
			private Frame f;
			public Act(CardLayout cl,Frame f)
			{
				this.cl = cl;
				this.f = f;
			}
			@Override
			public void actionPerformed(ActionEvent e)
			{	
				//显示下一张卡片 
				cl.next(f);
			}
		}
		b1.addActionListener(new Act(cl,f3));
		
		f3.setVisible(true);
		
		
		
		
		
		
		
	}

}
