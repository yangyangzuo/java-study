package gui.translation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JFrame {

	public static void main(String[] args) {
		Animation t = new Animation();
		t.setSize(500, 500);
		t.setLocationRelativeTo(null);
		t.setLayout(null);
		t.getContentPane().setBackground(Color.red);
		
		MyPanel myPanel = new MyPanel();
		myPanel.setBounds(0, 0, 73,73);
		myPanel.setOpaque(false);
		t.add(myPanel);
		
		
		
		t.setVisible(true);
		new Thread(myPanel).start();
	}

}

class MyPanel extends JPanel implements Runnable {

	private BufferedImage bufferedImage;
	private int degree = 0;

	public MyPanel() {
		try {
			bufferedImage = ImageIO.read(new File("/home/zuoyang/workspace/nevermore/images/loadingAnimation.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.currentThread().sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			degree = degree + 10;
			repaint();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(rotateImage(bufferedImage, degree), 0, 0,73,73,this);
	}

	public static BufferedImage rotateImage(BufferedImage bufferedimage, int degree) {
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage bufferedImage;
		Graphics2D graphics2d;
		(graphics2d = (bufferedImage = new BufferedImage(w, h, type)).createGraphics())
				.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2d.rotate(Math.toRadians(degree), w / 2, h / 2);
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return bufferedImage;
	}
}