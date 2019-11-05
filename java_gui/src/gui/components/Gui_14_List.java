package gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui_14_List
{

	public static void main(String[] args) throws InterruptedException
	{
		Frame f = new Frame();
		
		f.setBounds(500, 500, 500, 500);

		//true表示可以多项选择 
		List lists = new List(3,false);
		lists.setBackground(Color.RED);
		lists.add("list1");
		lists.add("list2");
		lists.add("list3");
		lists.add("list4");
		lists.add("list5");
		lists.add("list6");
		lists.add("list7");
		lists.add("list8");

		
		f.add(lists,BorderLayout.NORTH);
		
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}

		});
		f.validate();
		
		
		
		Frame f1 = new Frame();
		f1.setBounds(100,100,200,200);
		List lists1 = new List(3,false);
		//这个lists1并没有只显示3行数据，因为borderLayout默认把这个list加在了中间，并占据整个位置
		lists1.setBackground(Color.RED);
		lists1.add("list1");
		lists1.add("list2");
		lists1.add("list3");
		lists1.add("list4");
		lists1.add("list5");
		lists1.add("list6");
		lists1.add("list7");
		lists1.add("list8");
		f1.add(lists1);
		f1.setVisible(true);
		
		
		
		
		
		Frame f2 = new Frame();
		f2.setBounds(100,330,200,200);
		f2.setLayout(null);
		List lists2 = new List(3,false);
		//这个lists并没有显示，因为布局管理器设置为null的时候，组件大小为0 
		lists2.setBackground(Color.RED);
		lists2.add("list1");
		lists2.add("list2");
		lists2.add("list3");
		lists2.add("list4");
		lists2.add("list5");
		lists2.add("list6");
		lists2.add("list7");
		lists2.add("list8");
		f2.add(lists2);
		f2.setVisible(true);
		
		
		
		Frame f3 = new Frame();
		f3.setBounds(100,330,200,200);
		f3.setLayout(new FlowLayout());
		List lists3 = new List(3,false);
		//这个lists可以正常显示，因为layout布局管理器允许组件拥有最合适的大小
		lists3.setBackground(Color.RED);
		lists3.add("list1");
		lists3.add("list2");
		lists3.add("list3");
		lists3.add("list4");
		lists3.add("list5");
		f3.add(lists3);
		f3.setVisible(true);
		
		
		
	}
}
