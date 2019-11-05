package gui.lookAndFeel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.sun.java.swing.plaf.motif.MotifLookAndFeel;

/**
 * 官方书籍:
 * Java Look and Feel Design Guidelines
 * http://www.oracle.com/technetwork/java/jlf-135985.html
 * 
 * 官方文档:
 * http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/index.html
 * 
 * 其他文档:
 * 
 * 
 * @author zuoyang
 *
 */
public class LookAndFeel01 {
	public static void main(String[] args) {
		//获得系统支持的lookAndFeel
		UIManager.LookAndFeelInfo installs[] = UIManager.getInstalledLookAndFeels();
		for(int i = 0;i<installs.length;i++){
			System.out.println(installs[i].getClassName() + ":" + installs[i].getName());
//			javax.swing.plaf.metal.MetalLookAndFeel:Metal
//			javax.swing.plaf.nimbus.NimbusLookAndFeel:Nimbus
//			com.sun.java.swing.plaf.motif.MotifLookAndFeel:CDE/Motif
//			com.apple.laf.AquaLookAndFeel:Mac OS X
		}
		String name1 = UIManager.getCrossPlatformLookAndFeelClassName();//跨平台的默认lookAndFeel
		String name2 = UIManager.getSystemLookAndFeelClassName();//当前系统的LookAndFeel
		System.out.println(name1);
		System.out.println(name2);
		
		//其他系统中支持的lookAndFeel
//		"com.sun.java.swing.plaf.windows.WindowsLookAndFeel",windows风格
//		"com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel",Windows Classic风格
//		"com.sun.java.swing.plaf.gtk.GTKLookAndFeel";linux GTK风格
		
		//设置lookAndFeel
		try {
			//通过对象方式
			UIManager.setLookAndFeel(new MetalLookAndFeel());
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
			UIManager.setLookAndFeel(new MotifLookAndFeel());
//			UIManager.setLookAndFeel(new AquaLookAndFeel());
			//通过字符串方式
//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
//			UIManager.setLookAndFeel("com.apple.laf.AquaLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLayout(null);
		JButton button = new JButton("hello");
		button.setBounds(100, 100, 150, 150);
		frame.add(button);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
