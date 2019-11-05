package gui.layout;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;

/**
 * gridlayout
 * 
 * @author zuoyang
 *
 */
public class Layout06 extends Frame {
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(500, 500);

		
		Panel panel = new Panel();
		panel.setBackground(Color.WHITE);
		f.add(panel);
		
		panel.setLayout(new GridLayout());
		for(int i=0;i<10;i++){
			panel.add(new Button("产品" + i));
		}
		
		
		
		
		
		
		
		
		f.setVisible(true);
	}

}
