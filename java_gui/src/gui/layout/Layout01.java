package gui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Window;

public class Layout01 extends Frame {
	public static void main(String[] args) {
		Layout01 f = new Layout01();
		// f.setUndecorated(true);
		f.add(new Button("NORTH"), BorderLayout.NORTH);
		f.add(new Button("EAST"), BorderLayout.EAST);
		f.add(new Button("SOUTH"), BorderLayout.SOUTH);
		f.add(new Button("WEST"), BorderLayout.WEST);
		f.add(new Button("CENTER"), BorderLayout.CENTER);
		f.setSize(800, 600);

		f.setVisible(true);
	}

}
