package gui.systemTray;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/**
 * 生成系统托盘图表
 * 
 * http://docs.oracle.com/javase/tutorial/uiswing/misc/systemtray.html
 * 
 * @author Administrator
 * 
 */
public class SysteTray02 {

	public static void main(String args[]) {

		// 判断系统是否支持系统托盘
		if (!SystemTray.isSupported())
			return;

		// 创建系统托盘,如果系统不支持,调用getSystemTray()方法会抛出异常
		SystemTray tray = SystemTray.getSystemTray();
		Image image = Toolkit.getDefaultToolkit()
				.getImage("/Users/gudandan/Desktop/jeeworkspace/nevermore/images/titileImage1.png");// 载入图片
		// 创建系统托盘图标
		TrayIcon trayIcon = new TrayIcon(image);
		trayIcon.setImageAutoSize(true);
		try {
			// 如果操作系统或者java runtime认为无法添加系统托盘会抛出AWTException
			tray.add(trayIcon);
		} catch (AWTException e) {
			e.printStackTrace();
		}

		
		// 系统托盘图标包含:提示文本信息,弹出菜单,气球消息,一系列相关监听事件


		// 1.主框架,鼠标点击系统托盘时,显示主窗体
		final JFrame frame = new JFrame("hello");
		frame.setBounds(200, 200, 500, 500);
		//点击叉号时,隐藏窗体
		//frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);//默认就是HIDE_ON_CLOSE,所以不写也行
		//DO_NOTHING_ON_CLOSE,什么都不做
		//HIDE_ON_CLOSE,隐藏当前窗体
		//DISPOSE_ON_CLOSE,销毁当前窗体,不影响其他窗体和线程
		//EXIT_ON_CLOSE,退出程序,关闭虚拟机,相关的窗体和程序都会退出,如果有多个窗体和应用程序和当前程序关联,则都会关闭退出
		// 给系统托盘图标添加一个事件监听,当鼠标点击托盘的时候,主体框架显示
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
			}
		};
		trayIcon.addActionListener(listener);

		
		
		
		
		//2.设置托盘提示文字:
		//系统提示文字在windows换行:\n或者\r,或者\r\n都行
		//linux不支持换行
		StringBuffer sb = new StringBuffer("QQ:大叔爱萝莉(502372554)");
		sb.append("\r声音:开启");
		sb.append("\r\n消息提醒框:关闭");
		trayIcon.setToolTip(sb.toString());

		
		
		
		
		
		// 3.创建弹出菜单
		//awt提供弹出菜单太丑,我们自己画个面板显示
		PopupMenu popup = new PopupMenu();
		MenuItem open = new MenuItem("open ");
		open.addActionListener(listener);
		MenuItem exitItem = new MenuItem("exit ");
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		popup.add(open);
		popup.add(exitItem);
		trayIcon.setPopupMenu(popup);
	}
}
