package gui.lookAndFeel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

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
public class LookAndFeel02 {
		
	public static void main(String[]args){
		
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLayout(null);
		
		
		JButton button = new JButton("hello");
		button.setBounds(100, 100, 150, 150);
		frame.add(button);
		
		//设置lookAndFeel
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		JButton button2 = new JButton("hello2");
		button2.setBounds(200, 200, 150, 150);
		frame.add(button2);
		
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//可以发现第一个按钮是系统默认主题风格，第二个是更改后的主题风格
//		UIManager.setLookAndFeel(newLookAndFeel);//设置当前的界面样式，但不更新已有的组件的ui
		
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//休眠3秒，调用SwingUtils.updateComponentTreeUI()来更新组件
		SwingUtilities.updateComponentTreeUI(frame);
		//该方法会递归的为包含在这个方法的容器中的所有组件更新ui
		//当然，如果想更新按钮，可以直接调用
//		SwingUtilities.updateComponentTreeUI(button);
		
	}
}
