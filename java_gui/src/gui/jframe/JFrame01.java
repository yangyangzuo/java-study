package gui.jframe;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * 开发工具netbeans：
 * https://docs.oracle.com/javase/tutorial/uiswing/learn/index.html
 * 
 *  界面美化:
 *  http://twaver.servasoft.com/?p=1259
 *  http://joshuaxiao.iteye.com/blog/707514
 * @author zuoyang
 *
 */
public class JFrame01
{
	public static void main(String[] args)
	{
		//是否提供窗口装饰效果
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame jf = new JFrame("JFrame");
		jf.setBounds(100, 100, 300, 300);
		// JFrame继承了Frame，默认也是BorderLayout
		//这里修改为FlowLayout
		jf.setLayout(new FlowLayout());
		
		//获得JFrame的内容面板，然后把要显示的内容放进去
		jf.getContentPane().setBackground(Color.RED);
		JButton jb  = new JButton("shit");
		jb.setBounds(100,100,200,200);
		jf.getContentPane().add(jb);
		
		jf.setVisible(true);
		// 与 Frame 不同，当用户试图关闭窗口时，JFrame 知道如何进行响应。用户关闭窗口时，默认的行为只是简单地隐藏JFrame。
		//要更改默认的行为，可调用方法 setDefaultCloseOperation(int)。
		//要使 JFrame 的行为与Frame实例相同，请使用setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)。

		// 即:如果不调用这个方法，试图关闭的时候系统默认会把这个窗体隐藏
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
