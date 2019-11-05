package gui.components;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 对话框：
 * 
 * dialog类是从window类继承而来的，和Frame类拥有共同的父类，与Frame一样，Dialog是有边框，标题的容器，
 * 
 *对话框和独立窗口(Frame对象实现)的主要区别：
 * 
 * 对话框可以设置为模态的(未关闭前，不能移动到其他焦点)和非模态的
 * 
 * 对话框不能独立存在，必须被其他容器拥有
 * 
 * 对话框依赖于独立窗口,当它所依赖的窗口关闭时，对话框也关闭
 * 
 * 当以来的的窗口最小化时，它也从屏幕上消失 ，当依赖的窗口恢复时，对话框也恢复了
 * 
 * 所有这些行为由awt自动控制完成,无须用户干预
 * 
 * @author Administrator
 * 
 */
public class Gui_13_Dialog
{
	public static Frame f = new Frame();

	public static void main(String[] args) throws InterruptedException
	{

		f.setLayout(null);
		f.setBounds(500, 500, 500, 500);

		// 注意，如果这个对话框是模态对话框,则
		// dialog.setVisible(true);必须放在f.setVisible(true);之后，否则f.setVisible(true);受阻,frame显示不出来
		// Dialog dialog = new Dialog(f,"确定？",true);
		Dialog dialog = new Dialog(f, "确定？");
		//如果这个对话框需要显示在窗口的上面，则要设置bounds,这个bounds和frame中的bounds都是根据屏幕的左上角来定位的
		//因为dialog继承window,他的设置bounds的坐标和window是一样的
		dialog.setBounds(550, 550, 100, 100);
		dialog.setVisible(true);

		f.setVisible(true);
		f.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}

		});

		// Button b = new Button("弹出对话框测试");
		// b.addActionListener(new ActionListener()
		// {
		// @Override
		// public void actionPerformed(ActionEvent e)
		// {
		// // 创建模态对话框，则这个对话框关闭了，才能点其他的
		// Dialog dialog1 = new Dialog(f, "确定删除？", true);
		// dialog1.setBounds(50, 50, 200, 200);
		// dialog1.setLayout(null);
		//
		// Button b1 = new Button("确定");
		// b1.setBounds(50, 50, 60, 30);
		// dialog1.add(b1);
		//
		// Button b2 = new Button("取消");
		// b2.setBounds(110, 50, 60, 30);
		// dialog1.add(b2);
		//
		// dialog1.setVisible(true);
		// }
		// });
		//
		// f.add(b);
		// b.setLocation(100, 100);
		// b.setSize(50, 50);
		// f.validate();

	}
}
