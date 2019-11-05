package gui.components;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui_11_CheckBox
{
	public static void main(String[] args)
	{
		Frame f = new Frame();
		f.setSize(900,300);
		f.setLayout(null);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
			
		});
		
		
		
		
		//复选框
		Checkbox cb,cb1;
		//默认选中状态为false
		cb = new Checkbox("复选框1");
		cb1 = new Checkbox("复选框2",true);
		cb.setBounds(100,100,100,20);
		cb1.setBounds(100,130,100,20);
		f.add(cb);
		f.add(cb1);
		
		
		
		//单选框
		//创建一个分组，把复选框放到一个分组里，则这些复选框就成了单选框
		Checkbox cb2,cb3,cb4;
		CheckboxGroup sex = new CheckboxGroup();
		cb2 = new Checkbox("男",sex,true);
		cb2.setLocation(200, 100);
		cb2.setSize(50,30);
		//如果一个分组里面的元素有多个设置为了true,即默认选种状态，则后面的选种状态会覆盖前面的
		//因为一个复选框分组中只有一个可以被选种 
		cb3 = new Checkbox("女",sex,false);
		cb3.setLocation(250, 100); 
		cb3.setSize(50,30);
		f.add(cb2);
		f.add(cb3);
		//除了可以在构造方法执行外，还可以通过方法指定
		cb4 = new Checkbox("未知");
		cb4.setLocation(300, 100); 
		cb4.setSize(50,30);
		cb4.setCheckboxGroup(sex);
		f.add(cb4); 
		
		
		f.validate();
	
		
		

	}
}
