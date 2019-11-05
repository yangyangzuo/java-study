package gui.windowFrameDialog;

import java.awt.Frame;
import java.awt.Rectangle;

/**
 * 如果window类作为awt窗口组件的基本模型，则frame类可以看做一个完全模型，frame包含边界，标题栏及其一个可选的菜单栏
 * 可以调整大小配置图标图像，最小化等等
 * frame不需要找一个框架作为他的所有者，框架是独立的窗口，而window和dialog需要提供一个所有者
 * 
 * @author gudandan
 *
 */
public class FrameTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f = new Frame();
		f.setBounds(new Rectangle(200, 200));
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}
