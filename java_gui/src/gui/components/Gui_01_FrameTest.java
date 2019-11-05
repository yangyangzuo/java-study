package gui.components;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 组件：能完成一定功能的封装体
 * 
 * 容器：用户包含其他组件的组件
 * 
 * componet:Button, Canvas,Container, Label,List,TextComponent等
 * 
 * (Component 类是与菜单不相关的 Abstract Window Toolkit 组件的抽象超类)
 * 
 * container:panel,window
 * 
 * window:frame,dialog(window对象可以独立其他容器，独立存在)
 * 
 * 常见的界面组合方式：组件放在panel中，panel放在Frame上
 * 
 * 界面是画出来的，是叠加出来的
 * 
 * 即：通常panel用来界面的组合,一个panel就是一个界面,那么界面的切换，就相当于切换Frame里面的panel
 * 
 * @author Administrator
 * 
 */
public class Gui_01_FrameTest
{

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException
	{
		Frame f = new Frame();
		f.setSize(500, 500);
		
		
		//设置窗体是否有标题栏,默认有标题，即false
		//禁用或启用此窗体的装饰。只有在窗体不可显示时才调用此方法。
		//即在窗体显示之前(setVisible(true)方法之前调用，否在会抛异常)
		//f.setUndecorated(true);
		
		
		
		// 默认窗体是不可见的，需要设置这个值，组件才可以显示出来,jdk1.1以前用f.show()来显示窗体
		f.setVisible(true);

		

		
		
		
		// new WindowAdapter(){...}相当于new了WindowAdapter的一个匿名子类，其中可以覆盖他的方法
		f.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosed(WindowEvent e)
			{
				System.out.println("4");
			}

			@Override
			public void windowClosing(WindowEvent e)
			{
				System.out.println("1");
				e.getWindow().setVisible(false);
				System.out.println("2");
				e.getWindow().dispose();
				System.out.println("3");
			}
		});

		//validate()方法
		// 下面的组件，是在窗口程序显示出来后再添加组件，如果不调用validate()方法，每次执行该程序,组件可能显示不一样(不完全)，
		// 因为组件显示后，添加组件，需要通知awt,调用validate()方法来生成peer,组件才能正确的显示
		// api中:Container类的add()方法描述：
		// 如果已经将某个组件添加到显示的容器中，则必须在此容器上调用 validate，以显示新的组件。
		// 如果添加多个组件，那么可以在添加所有组件之后，通过只调用一次 validate 来提高效率。
		f.add(new Button("button1"), BorderLayout.SOUTH);
		f.add(new Button("button2"), BorderLayout.NORTH);
		f.add(new Button("button3"), BorderLayout.EAST);
		f.add(new Button("button4"), BorderLayout.WEST);
		f.add(new Button("button5"), BorderLayout.CENTER);
		f.validate();

		// add方法
		// Component add(Component comp) 将指定组件追加到此容器的尾部。
		//f.add(new Button("直接添加到末尾"));//需要改变窗口大小才能看见，被加在了button5的下面
		//f.validate();
		
		
		
		
		// Component add(Component comp, int index)将指定组件添加到此容器的给定位置上。
		//-1表示添加到组件的末尾 
		//f.add(new Button("被加在了button5的下面，需要改变窗体的大小，才能看见"),-1);
		//等价于f.add(new Button("被加在了button5的下面，需要改变窗体的大小，才能看见")); 
		/*
		Component[] components = f.getComponents();
		for(int i=0;i<components.length;i++)
		{
			System.out.println(i + ":" + ((Button)components[i]).getLabel());
			// 0:button1
			// 1:button2
			// 2:button3
			// 3:button4
			// 4:button5
		}
		//插入到索引为1的位置
		f.add(new Button("添加到0"),1);
		components = f.getComponents();
		for(int i=0;i<components.length;i++)
		{
			System.out.println(i + ":" + ((Button)components[i]).getLabel());
			// 0:button1
			// 1:添加到0
			// 2:button2
			// 3:button3
			// 4:button4
			// 5:button5
			//由结果可以看出来确实插入进去了,但是显示的时候，却显示在了中间的位置 
		}
		*/
		
		
		
		
		
		// void add(Component comp, Object constraints)将指定的组件添加到此容器的尾部。
		// void add(Component comp, Object constraints, int index)使用指定约束，将指定组件添加到此容器的指定索引所在的位置上
		
		
		
		// Component add(String name, Component comp)将指定组件添加到此容器中。
		//从 JDK version 1.1 开始，此方法已过时。请使用 add(Component, Object) 方法代替
		//System.out.println(BorderLayout.NORTH);
		//等价与f.add(new Button("过期的函数add(String name, Component comp)"), BorderLayout.NORTH);
		//等价与f.add(new Button("过期的函数add(String name, Component comp)"), "North");
		//f.add("North",new Button("过期的函数add(String name, Component comp)"));
		
		f.validate();
		
		
		
		//设置窗体的图标
		Image image = Toolkit.getDefaultToolkit().getImage("src/gui/title.png");
		f.setIconImage(image);
		//f.validate();
	}
}
