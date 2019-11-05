package gui.paint;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Paint01 extends Applet {
	private Graphics oldg;
	private boolean first = true;

	
	public void paint(Graphics g) {
		if(first) {
			oldg = g.create();
			//oldg这个对象持有graphics一个引用，当该方法结束后，该对象并不会被销毁
			first = false;
		}
		oldg.drawLine(0,0,getSize().width-1, getSize().height-1);
		
		//如果你需要copy一个Graphics对象，则使用完后，应该对他销毁
		//if(oldg!=null)oldg.dispose();
	}
}
