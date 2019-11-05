package gui.windowFrameDialog;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
/**
 * 利用window对象构造提示信息框 
 * @author gudandan
 *
 */
public class WindowTest5 extends Applet {
	public void init() {
		BubblePanel left   = new BubblePanel("left");
		BubblePanel center = new BubblePanel("center");
		BubblePanel right  = new BubblePanel("right");

		add(left);
		add(center);
		add(right);
	}
}
class BubblePanel extends Panel {
	Bubble bubble;
	String bubbleText;

	public BubblePanel(String string) {
		bubbleText = string;
		this.setBounds(0, 0, 50, 50);
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent event) {
				BubblePanel canvas  = BubblePanel.this;
				Point scrnLoc = canvas.getLocationOnScreen();
				Dimension size    = getSize();

				if(bubble == null)
					bubble = new Bubble(canvas, bubbleText);

				bubble.setLocation(scrnLoc.x,
								   scrnLoc.y + size.height + 2);
				bubble.show();
			}
			public void mouseExited(MouseEvent event) {
				if(bubble != null && bubble.isShowing())
					bubble.dispose();
			}
		});
	}
	public Dimension getPreferredSize() {
		return new Dimension(50,50);
	}
	
	public void paint(Graphics g) {
		Dimension size = getSize();
		g.setColor(Color.red);
		g.drawRect(0,0,size.width-1,size.height-1);
	}
}


/**
 * 利用window对象构造提示信息框
 * @author gudandan
 *
 */
class Bubble extends Window {
	private String text;

	public Bubble(Component comp, String text) {
		super(getFrame(comp));
		this.text = text;
		setForeground(SystemColor.textText);
	}

	public Dimension getPreferredSize() {
		Graphics g = getGraphics();
		FontMetrics fm = g.getFontMetrics();

		return new Dimension(fm.stringWidth(text) + 4, fm.getHeight() + 4);
	}

	public void paint(Graphics g) {
		Dimension size = getSize();
		FontMetrics fm = g.getFontMetrics();

		g.drawRect(0, 0, size.width - 1, size.height - 1);
		g.drawString(text, 2, fm.getAscent() + 2);
	}

	public void show() {
		pack();
		super.show();
	}

	static Frame getFrame(Component c) {
		Frame frame = null;

		while ((c = c.getParent()) != null)
			if (c instanceof Frame)
				frame = (Frame) c;

		return frame;
	}
}