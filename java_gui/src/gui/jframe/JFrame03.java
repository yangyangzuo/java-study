package gui.jframe;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JFrame03
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
		
		JButton jb  = new JButton("shit");
		jb.setBounds(100,100,200,200);
		
		
		//如果我们这里不获得内容面板，而是直接添加到JFrame里面呢？
		jf.add(jb);
		System.out.println(jb.getParent());//javax.swing.JPanel[null.contentPane,说明按钮jb被添加在了contentPane上,这个对象实际是一个new JPanel()，和上面源码相符
		System.out.println(jb.getParent().getParent());//javax.swing.JLayeredPane[null.layeredPane,说明了contentPane是放在了layeredPane,和上面分析相符
		System.out.println(jb.getParent().getParent().getParent());//javax.swing.JRootPane[,说明了layeredPane是放在了JRootPane,和上面分析相符
		System.out.println(jb.getParent().getParent().getParent().getParent());//javax.swing.JFrame[frame0，说明了JFrame是放在了Frame里面(JFrame继承了Frame),和上面分析相符
		System.out.println(jb.getParent().getParent().getParent().getParent().getParent());//null
		//发现依然没有什么问题，为什么？
		//看源码:
		//JFrame.add()方法实际调用继承的Contianer.add()方法,而Contianer.add()方法又调用了addImpl(comp, null, -1)方法，JFrame因为覆盖了addImpl()方法，
		//所以最后绕到了JFrame.addImpl()方法,源码如下:
//		protected void addImpl(Component comp, Object constraints, int index)
//	    {
//	        if(isRootPaneCheckingEnabled()) {
//	            getContentPane().add(comp, constraints, index);
//	        }
//	        else {
//	            super.addImpl(comp, constraints, index);
//	        }
//	    }
		//最后还是调用了getContentPane().add(),所以没有问题
		
		
		
		
		
		jf.setVisible(true);
		// 与 Frame 不同，当用户试图关闭窗口时，JFrame 知道如何进行响应。用户关闭窗口时，默认的行为只是简单地隐藏JFrame。
		//要更改默认的行为，可调用方法 setDefaultCloseOperation(int)。
		//要使 JFrame 的行为与Frame实例相同，请使用setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)。

		// 即:如果不调用这个方法，试图关闭的时候系统默认会把这个窗体隐藏
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
