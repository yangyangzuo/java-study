package gui.components;

import java.awt.Choice;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui_12_Choice
{
	public static void main(String[] args)
	{
		Frame f = new Frame();
		f.setLayout(null);
		f.setBounds(500,500,500,500);

		
		
		Choice choice = new Choice();
		choice.setLocation(200,200);
		choice.add("北京");
		choice.add("郑州");
		choice.add("南京");
		choice.add("武汉武汉武汉");
		f.add(choice);
		
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
