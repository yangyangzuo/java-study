package gui.label_button;

import java.awt.Button;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * button可以构件一个带文本的按钮 
 * 
 * 
 * @author Administrator
 *
 */
public class ButtonTest03
{

	public static void main(String[] args)
	{
		Frame f = new Frame();
		f.setBounds(200, 200, 300, 300);
		
		
		Button b = new Button("press me");
		b.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//获得事件源对象
				// 得到最初发生 Event 的对象,从父类EventObject中继承的方法
				Button b = (Button) e.getSource();//这里是b
				System.out.println("command is :" + b.getActionCommand());
			}
		});
		
		f.add(b);
		f.setVisible(true);
	}

}
