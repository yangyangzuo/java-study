package gui.jframe;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JFrame044 {

	public static void main(String[] args) throws InterruptedException {
		JFrame jframe = new JFrame();
		jframe.setSize(1200,800);
		jframe.setLayout(null);
		
		JPanel c1 = new JPanel();
		c1.setBounds(0, 0, 500, 500);
		c1.setBackground(Color.GREEN);
		JPanel c2 = new JPanel();
		c2.setBounds(600, 0, 500, 500);
		c2.setBackground(Color.RED);
		
		JButton b  = new JButton("mybutton");
		c1.add(b);
		
		
		jframe.add(c1);
		jframe.add(c2);
		
		
		
		
		jframe.setVisible(true);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//2秒后把按钮从面板c1,移动到面板c2中
		Thread.currentThread().sleep(2000);
		c2.add(b);
		jframe.repaint();
		
		
		//这里对于JFrame做个总结:
		//默认JFrame里面有rootpane,layeredpane,contentpane,menubar,glasspane
		//rootpane,我们一般不对他操作
		//contentPane,我们在里面放置我们的组件就好
		//menubar,在里面放置菜单就好
		//glasspane,是一个放置在顶层的简单Jpanel,也很好操作
		//JlayeredPane,这个比较复杂,后面有专门的分析
		
	}

}
