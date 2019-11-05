package gui.jframe;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class JGlassPane01 extends JFrame {
	public static void main(String[] args) {
		JFrame jf = new JFrame("ss");
		jf.getContentPane().setBackground(Color.green);

		// 显示JFrame中默认的玻璃面板
		//源码中:JGlassPane实际是一个JPane
//		protected Component createGlassPane() {
//	        JComponent c = new JPanel();
//	        c.setName(this.getName()+".glassPane");
//	        c.setVisible(false);//不显示
//	        ((JPanel)c).setOpaque(false);//背景透明
//	        return c;
//	    }
		
		//我们修改适当属性就可以让他显示出来
		JComponent glassPane = (JComponent) jf.getGlassPane();
		glassPane.setVisible(true);
		glassPane.setBackground(Color.red);
		glassPane.setOpaque(true);// 玻璃面板不透明

		jf.setSize(500, 500);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
}
