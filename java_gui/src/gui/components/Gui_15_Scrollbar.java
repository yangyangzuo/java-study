package gui.components;

import java.awt.Frame;
import java.awt.Scrollbar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Scrollbar:滚动条
 * 
 * 作用：
 * 
 * 1.用做设置值的滑动器
 * 
 * 2.用来部分的显示对于可用显示区太大的图象或文本
 * 
 * 
 * @author Administrator
 * 
 */
public class Gui_15_Scrollbar
{
	public static void main(String[] args)
	{
		Frame f = new Frame();
		f.setLayout(null);
		f.setBounds(200, 200, 500, 300);

		Scrollbar scrollbar = new Scrollbar();
		scrollbar.setSize(200,200);
		
		f.add(scrollbar);
		
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}

		});
	}
}
