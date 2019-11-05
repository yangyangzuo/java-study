package gui.foundation;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
public class Graphics2 extends Applet{
	public static void main(String[] args) throws InterruptedException{
		Graphics2 t = new Graphics2();
//		t.setSize(600,600);
//		t.setUndecorated(true);
//		t.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println(this.getGraphics());
		System.out.println(g);
		this.setForeground(Color.red);
		System.out.println(g);
		System.out.println(this.getGraphics());
		g.drawLine(0, 0, this.getSize().width-1,this.getSize().height-1);
		
	}
}
