package gui.systemTray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

/**
 * 生成系统托盘图表
 * 
 * http://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html
 * 
 * @author Administrator
 * 
 */
public class SysteTray01 {

	public static void main(String args[]) {

		// 判断系统是否支持系统托盘
		if (!SystemTray.isSupported())
			return;
		// 创建系统托盘,如果系统不支持,调用getSystemTray()方法会抛出异常
		SystemTray tray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit().getImage("/home/zuoyang/workspace/java_gui/src/gui/components/tray.png");// 载入图片
		//创建系统托盘图标
		TrayIcon trayIcon = new TrayIcon(image, "影魔网络科技");
		try {
			// 如果操作系统或者java runtime认为无法添加系统托盘会抛出AWTException
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
}
