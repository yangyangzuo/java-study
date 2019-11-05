package gui.systemTray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 生成系统托盘图表
 * 
 * http://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html
 * 
 * @author Administrator
 * 
 */
public class SysteTray03 {

	public static void main(String args[]) throws AWTException {

		// 判断系统是否支持系统托盘
		if (!SystemTray.isSupported())
			return;

		// 创建系统托盘,如果系统不支持,调用getSystemTray()方法会抛出异常
		SystemTray tray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit().getImage("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/components/tray.png");// 载入图片
		// 创建系统托盘图标
		TrayIcon trayIcon = new TrayIcon(image);
		trayIcon.setImageAutoSize(true);
		tray.add(trayIcon);

		
		
		// 程序主体
		final JFrame frame = new JFrame("hello");
		frame.setBounds(200, 200, 500, 500);
		// 自定义弹出菜单面板
		final JDialog popmenuDialog = new JDialog();
		popmenuDialog.setBounds(1050, 50, 200, 500);

		
		//1.创建一个鼠标点击监听事件,如果点击左键,则弹出程序主体,如果点击右键,弹出自定义菜单
		MouseListener listener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					// 如果是左键,显示程序
					frame.setVisible(true);
				} else if (SwingUtilities.isRightMouseButton(e)) {
					//如果是右键,则弹出自定义菜单
					popmenuDialog.setVisible(true);
				}
			}
		};
		trayIcon.addMouseListener(listener);
		
		//2.自定义菜单失去焦点时,隐藏
		popmenuDialog.addWindowFocusListener(new WindowAdapter() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				popmenuDialog.setVisible(false);
			}
		});

	}
}
