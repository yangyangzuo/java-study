package gui.opacity;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Opacity07 extends JFrame {

	public Opacity07() {
		this.setBackground(Color.red);
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		
		Opacity07 jframe = new Opacity07();
		//设置一个窗体渐变
		JPanel jPanel1 = new JPanel() {
		      protected void paintComponent(Graphics g) {
		          if (g instanceof Graphics2D) {
		              final int R = 0;
		              final int G = 0;
		              final int B = 0; 
		             Paint p =  new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0),
		                  getWidth(), getHeight(), new Color(R, G, B, 255), true);
		              Graphics2D g2d = (Graphics2D)g;
		              g2d.setPaint(p);
		              g2d.fillRect(0, 0, getWidth(), getHeight());

		       } else {
		    	   super.paintComponent(g);
		       }
		     }
		 };
		 jframe.add(jPanel1);
		 jframe.setVisible(true);
		
		
	}
}
