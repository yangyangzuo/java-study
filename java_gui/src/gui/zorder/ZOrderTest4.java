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

public class ZOrderTest4 extends Frame{
	public static void main(String[] args) {
		ZOrderTest4 frame = new ZOrderTest4();
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
		
		//getComponentZOrder()方法说明
		//返回容器内组件的 z 顺序索引。
		//组件在 z 顺序层次结构中所处的位置越高，它的索引就越低。
		//具有最低 z 顺序索引的组件最后一个被绘制，而它在层次结构中高于其他所有子组件。
		//即:索引值越低,组件最后一个被绘制，那么就会显示在最上面
		System.out.println(frame.getComponentZOrder(b1));//0
		System.out.println(frame.getComponentZOrder(b2));//1
		System.out.println(frame.getComponentZOrder(b3));//2
		System.out.println(frame.getComponentZOrder(b4));//3
		System.out.println(frame.getComponentZOrder(b5));//4
		
		//这里分析一下getComponentZOrder()方法的源码:
//		public int getComponentZOrder(Component comp) {
//	        if (comp == null) {
//	            return -1;
//	        }
//	        synchronized(getTreeLock()) {
//	            // Quick check - container should be immediate parent of the component
//	            if (comp.parent != this) {
//	                return -1;
//	            }
//	            return component.indexOf(comp);
//	        }
//	    }
		//源码中可以看出来,这个zorder值实际是container中的一个组件集合(Component)的坐标索引,而且这个Component是一个ArrayList
		//查看Container中的定义可以看出来:
		//private java.util.List<Component> component = new java.util.ArrayList<Component>();
		
		
		
		//setComponentZOrder()方法说明:
		//将指定组件移动到容器中指定的 z 顺序索引。
		//z 顺序确定了绘制组件的顺序；具有最高 z 顺序的组件将第一个绘制，而具有最低 z 顺序的组件将最后一个绘制。
		//即:z值越高，越优先绘制,但是会显示在最底层
		
		//在组件重叠的地方，具有较低 z 顺序的组件将覆盖具有较高 z 顺序的组件。
		//此时b3会覆盖在b2上面
		//frame.setComponentZOrder(b3, 0);
		
		//如果在容器间移动的 index 不在 [0, getComponentCount()] 范围内，
		//或者在容器内移动的 index 不在 [0, getComponentCount()-1] 范围内
		
		//修改zorder值，可以改变层叠组件的显示顺序
//		frame.setComponentZOrder(b1, 0);//z值最小，显示在最上面
//		frame.setComponentZOrder(b2, 3);
//		frame.setComponentZOrder(b3, 2);
//		frame.setComponentZOrder(b4, 1);
//		frame.setComponentZOrder(b5, 4);//z值最大，显示在最底层
		
		//注意:如果把一个组件更换索引，则所有组件索引根据最新排序将自动更换，ArrayList里面不会存在2个相同的下标索引
		//此时相当于把索引为3的组件放在了最前面，则剩下的ArrayList中的组件自动索引重排
//		frame.setComponentZOrder(b3, 0);
//		System.out.println(frame.getComponentZOrder(b1));//1
//		System.out.println(frame.getComponentZOrder(b2));//2
//		System.out.println(frame.getComponentZOrder(b3));//0
//		System.out.println(frame.getComponentZOrder(b4));//3
//		System.out.println(frame.getComponentZOrder(b5));//4
		
		
		frame.setVisible(true);
		
		
	}
}
