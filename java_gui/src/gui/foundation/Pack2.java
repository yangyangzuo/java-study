package gui.foundation;


import java.awt.Button;
import java.awt.Frame;

public class Pack2
{
	public static void main(String[] args)
	{
		Frame f = new Frame();
		f.add(new Button("button1"));
		// f.pack()的作用:不论你是否设置初始框架的大小，
		// 他都会根据组件大小，来修改最外层窗体大小
		f.pack();
		f.setVisible(true);
	}
}
