package gui.paint;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Paint02 extends Applet {
	private Graphics oldg;
	private boolean first = true;

	
	public void paint(Graphics g) {
		if(first) {
			oldg = g;
			first = false;
		}
		System.out.println(oldg);
		oldg.drawLine(0,0,getSize().width-1, getSize().height-1);
		//对于方法中传递的这个graphics，该方法结束后，方法结束后,系统帮助销毁了，不需要处理
		//当第一次显示后，可以通过改变applet大小，发现，线消失了，绘制不出来
	}
}
