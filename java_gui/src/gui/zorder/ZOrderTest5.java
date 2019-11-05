package gui.zorder;

import java.awt.AWTEvent;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;
import java.awt.event.HierarchyEvent;

public class ZOrderTest5 extends Frame{
	public static void main(String[] args) {
		ZOrderTest5 frame = new ZOrderTest5();
		frame.setBounds(new Rectangle(500, 500));
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		
		Button b1 = new Button("11111111111111111111111");
		b1.setBounds(30,205,500,30);
		b1.setBackground(Color.GRAY);
		frame.add(b1);

		Button b2 = new Button("2222222222222222222222");
		b2.setBounds(130,60,200,200);
		b2.setBackground(Color.green);
		frame.add(b2);
		
		Button b3 = new Button("33333333333333333333333");
		b3.setBounds(100,100,200,200);
		b3.setBackground(Color.blue);
		frame.add(b3);
		
		Button b4 = new Button("44444444444444444444444");
		b4.setBounds(140,140,100,200);
		b4.setBackground(Color.yellow);
		frame.add(b4);
		
		Button b5 = new Button("55555555555555555555555");
		b5.setBounds(180,180,200,200);
		b5.setBackground(Color.pink);
		frame.add(b5);
		
		
		//我们这里分析一下container.add()方法:
		//Container.add()方法，最终调用的都是Container.addImpl();
		
		//如果add和add的重载方法中没有提供索引参数，则默认的索引是-1,从源代码可以看出来，下面举例一个:
//		 public Component add(Component comp) {
//		        addImpl(comp, null, -1);
//		        return comp;
//		    }
		
		//对于addImpl方法，查看方法说明
		//如果 comp 是此容器的子容器，则有效范围是 [-1, getComponentCount()-1]
		//如果组件不是此容器的子容器，则有效范围是 [-1, getComponentCount()]
		//这个索引值越低则在最下面显示，值越高则显示在最上面
//		protected void addImpl(Component comp, Object constraints, int index) {
//	        synchronized (getTreeLock()) {
//	            GraphicsConfiguration thisGC = this.getGraphicsConfiguration();
//
//	            if (index > component.size() || (index < 0 && index != -1)) {
//	                throw new IllegalArgumentException(
//	                          "illegal component position");
//	            }
//		
//	            .....
//
//				 //index == -1 means add to the end.
//		        if (index == -1) {
//					//默认情况下add()方法传过来的index是-1，此时arraylist对象component.add(),相当于直接把这个组件放到组件集合的末尾了
//		            component.add(comp);
//		        } else {
//					//如果add()方法传递了一个特定的index,则ArrayList.add()方法把这个组件放到数组集合component的特定的下坐标索引中
//		            component.add(index, comp);
//		        }
//	        }
//	    }
		//由此可以看出来：如果容器中的组件有重叠，会按照这个容器中的一个变量()的下标顺序进行显示
		//<ArrayList>索引靠前的(先添加到容器中的)/索引值最小的，组件会显示在所有组件的最上面
		//索引值越大/索引越靠后,则组件会显示在最底层
		
		frame.setVisible(true);
		
		
	}
}
