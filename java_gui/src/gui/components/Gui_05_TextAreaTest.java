package gui.components;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Gui_05_TextAreaTest
{
	public static void main(String[] args)
	{
		Frame f = new Frame();
		f.setLayout(null);
		f.setSize(300, 300);
		
		TextArea ta = new TextArea("初始值");
		ta.setBounds(50, 50, 200, 200);
		f.add(ta);
		
		
		
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
