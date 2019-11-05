package gui.foundation;


import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 测试frame的pack()方法
 * 
 * @author Administrator
 * 
 */
public class TitleTest
{
	//方法1
//	public static void main(String[] args)
//	{
//		Frame t = new Frame();
//        t.setSize(600,300);
//        t.setFont(new Font("System", Font.PLAIN, 14));
//        Font f = t.getFont();
//        FontMetrics fm = t.getFontMetrics(f);
//        int x = fm.stringWidth("Hello Center");
//        int y = fm.stringWidth(" ");
//        int z = t.getWidth()/2 - (x/2);
//        int w = z/y;
//        String pad ="";
//        pad = String.format("%"+w+"s", pad);
//        t.setTitle(pad+"Hello Center");
//        t.setVisible(true);
//	}
	
	//方法2
	public static void main(String[] args) {  
		JFrame frame = new JFrame("利用JFrame创建窗口"); // 创建指定标题的JFrame窗口对象  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭按钮的动作为退出窗口  
		frame.setSize(400, 300);                          // 设置窗口大小  
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); // 获得显示器大小对象  
		Dimension frameSize = frame.getSize();             // 获得窗口大小对象  
		if (frameSize.width > displaySize.width)  
		frameSize.width = displaySize.width;           // 窗口的宽度不能大于显示器的宽度  

		frame.setLocation((displaySize.width - frameSize.width) / 2,  
		(displaySize.height - frameSize.height) / 2); // 设置窗口居中显示器显示  
		frame.setVisible(true);                          // 设置窗口为可见的，默认为不可见  
		}  
}
