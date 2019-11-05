package gui.gif;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingGifTest01 extends JPanel {
	Image globe;
	Toolkit tk = Toolkit.getDefaultToolkit();

	public static void main(String args[]) {
		JFrame f = new JFrame();
		f.setSize(600, 600);
		f.setLayout(null);
		
		SwingGifTest01 l = new SwingGifTest01();
		l.setBounds(20, 20, 200, 200);
		
		f.add(l);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	public SwingGifTest01() {
			globe = Toolkit.getDefaultToolkit().createImage("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/gif/globe.gif");
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(globe, 0, 0, this);
	}
	
		
}
