package gui.windowFrameDialog;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class DialogTest04 extends Frame {
	
	public static void main(String[] args) {
	DialogTest04 f = new DialogTest04();
	f.setBounds(new Rectangle(500, 500));
	final Dialog d = new DeadlockDialog(f);
	f.addMouseListener(new MouseAdapter() {
			public synchronized void mousePressed(MouseEvent e) {
				System.out.println("click");
				try {
					Thread.currentThread().sleep(5000);
				}
				catch(InterruptedException ex) {
					ex.printStackTrace();
				}
				d.setVisible(true);
			}
		});
	f.setLocationRelativeTo(null);
	f.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.drawString("5秒内点击2次鼠标", 120, 120);
	}
}
class DeadlockDialog extends Dialog {
	Button doneButton = new Button("doneButton");

	public DeadlockDialog(Frame f) {
		super(f, "Deadlock", true);
		add(doneButton);
		pack();

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}
