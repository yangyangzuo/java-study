package gui.windowFrameDialog;

import java.awt.Window;

/**
 * Window 对象是一个没有边界和菜单栏的顶层窗口。窗口的默认布局是 BorderLayout。
 * 
 * 构造窗口时，它必须拥有窗体、对话框或其他作为其所有者定义的窗口。
 * 
 * 在多屏幕环境中，通过使用 Window(Window, GraphicsConfiguration) 构造 Window，可以在不同的屏幕设备上创建
 * Window。GraphicsConfiguration 对象是目标屏幕设备的 GraphicsConfiguration 对象之一。
 * 
 * @author gudandan
 *
 */
public class WindowTest1 {
	public static void main(String[] args) {
		Window window = new Window(null);
		window.setBounds(0, 0, 500, 500);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
