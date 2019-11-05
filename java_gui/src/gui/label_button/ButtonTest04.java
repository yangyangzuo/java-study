package gui.label_button;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonTest04 {
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setLayout(new FlowLayout());
		
		
		
		Button b1 = new Button("bb1");
		Button b2 = new Button("bb2");
		Button b3 = new Button("bb3");
		f.add(b1);
		f.add(b2);
		f.add(b3);
		//当多个组件同时注册一个监听器时，如何判断是哪个组件
		ActionListener l1 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//第一种方法,通过按钮的label值
				Button b = (Button)e.getSource();
				System.out.println("第一种方法判断:");
				if("bb1".equals(b.getLabel())){
					System.out.println("是b1");
				}else if("bb2".equals(b.getLabel())){
					System.out.println("是b2");
				}else if("bb3".equals(b.getLabel())){
					System.out.println("是b3");
				}
				//这种方法一大弊端是，如果组件名称改变，则代码需要改的地方比较多，容易遗漏也容易出错，这种方法不可取
			}
		};
		ActionListener l2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//第二种方法,通过比较事件源对象和添加监听器的对象
				Button b = (Button)e.getSource();
				System.out.println("第二种方法判断:");
				if(b1==b){
					System.out.println("是b1");
				}else if(b2==b){
					System.out.println("是b2");
				}else if(b3==b){
					System.out.println("是b3");
				}
				//这种方法的缺点是需要传递添加监听器的对象
			}
		};
		ActionListener l3 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//第二种方法,通过按钮上的actionCommand
				Button b = (Button)e.getSource();
				String command = b.getActionCommand();
				//或者
				//command = e.getActionCommand();
				System.out.println("第三种方法判断:");
				if("b1".equals(command)){
					System.out.println("是b1");
				}else if("b2".equals(command)){
					System.out.println("是b2");
				}else if("b3".equals(command)){
					System.out.println("是b3");
				}
				//这种方法的集成了前两种方法的优点,相对较好
				//actioncommand一旦设置就不轻易改变了，可以直接作为比较的对象
				//也不用传递按钮对象,可以直接通过e.getActionCommand()获得按钮的command
			}
		};
		b1.addActionListener(l1);
		b2.addActionListener(l1);
		b3.addActionListener(l1);
		b1.addActionListener(l2);
		b2.addActionListener(l2);
		b3.addActionListener(l2);
		b1.addActionListener(l3);
		b2.addActionListener(l3);
		b3.addActionListener(l3);
		f.setVisible(true);
	}
}
