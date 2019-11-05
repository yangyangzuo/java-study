package gui.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;

/**
 * 玻璃面板测试(glassPane test)
 * 
 * 当你想捕获事件或者绘制内容,在一块特定区域(这块区域包含一个或者多个组件),则glassPane很有用
 * 例如:你可以检测一个包含多个组件的区域上面的鼠标事件，通过glassPane来进行事件交互
 * 或者在一个包含多个组件的区域上面显示一张图片
 * 
 * 当前的应用程序举例说明了如何使用glassPane
 * 
 * http://docs.oracle.com/javase/tutorial/uiswing/components/rootpane.html#glasspane
 * @author gudandan
 *
 */
public class JGlassPaneTest {
	static private MyGlassPane myGlassPane;

	private static void createAndShowGUI() {
		
		JFrame frame = new JFrame("玻璃面板测试");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		//获得内容面板
		Container contentPane = frame.getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		//添加选择框
		JCheckBox changeButton = new JCheckBox("是否显示玻璃面板");
		changeButton.setSelected(false);
		contentPane.add(changeButton);
		//添加2个按钮
		contentPane.add(new JButton("Button 1"));
		contentPane.add(new JButton("Button 2"));

		//添加菜单栏
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.add(new JMenuItem("Do nothing"));
		menuBar.add(menu);
		frame.setJMenuBar(menuBar);

		//创建一个玻璃面板，覆盖菜单栏和内容面板，并监听事件 
		myGlassPane = new MyGlassPane(changeButton, menuBar, frame.getContentPane());
		changeButton.addItemListener(myGlassPane);//复选框注册事件，看是否被选择
		frame.setGlassPane(myGlassPane);

		frame.setSize(500, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		//通过事件分发线程来显示一个gui程序,因为swing是非线程安全的
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}

/**
 * 创建一个自己的GlassPane,用来在上面可以画点
 * 
 * 因为牵涉到玻璃面板我们要画一些点，所以需要重写paintComponent()方法，所以没有使用系统默认的GlassPane
 */
class MyGlassPane extends JComponent implements ItemListener {
	Point point;

	//如果复选框被选中，则设置当前组件为显示状态
	public void itemStateChanged(ItemEvent e) {
		setVisible(e.getStateChange() == ItemEvent.SELECTED);
	}

	protected void paintComponent(Graphics g) {
		if (point != null) {
			g.setColor(Color.red);
			g.fillOval(point.x - 10, point.y - 10, 20, 20);
		}
	}

	public void setPoint(Point p) {
		point = p;
	}

	public MyGlassPane(AbstractButton aButton, JMenuBar menuBar, Container contentPane) {
		CBListener listener = new CBListener(aButton, menuBar, this, contentPane);
		addMouseListener(listener);//玻璃面板添加鼠标点击监听事件
		addMouseMotionListener(listener);//玻璃面板添加鼠标移动监听事件
	}
}

/**
 * 监听checkbox的
 * Listen for all events that our check box is likely to be interested in.
 * Redispatch them to the check box.
 */
class CBListener extends MouseInputAdapter {
	Toolkit toolkit;
	Component liveButton;
	JMenuBar menuBar;
	MyGlassPane glassPane;
	Container contentPane;

	public CBListener(Component liveButton, JMenuBar menuBar, MyGlassPane glassPane, Container contentPane) {
		toolkit = Toolkit.getDefaultToolkit();
		this.liveButton = liveButton;
		this.menuBar = menuBar;
		this.glassPane = glassPane;
		this.contentPane = contentPane;
	}

	public void mouseMoved(MouseEvent e) {
		redispatchMouseEvent(e, false);
	}

	public void mouseDragged(MouseEvent e) {
		redispatchMouseEvent(e, false);
	}

	public void mouseClicked(MouseEvent e) {
		redispatchMouseEvent(e, false);
	}

	public void mouseEntered(MouseEvent e) {
		redispatchMouseEvent(e, false);
	}

	public void mouseExited(MouseEvent e) {
		redispatchMouseEvent(e, false);
	}

	public void mousePressed(MouseEvent e) {
		redispatchMouseEvent(e, false);
	}

	public void mouseReleased(MouseEvent e) {
		redispatchMouseEvent(e, true);
	}

	// A basic implementation of redispatching events.
	private void redispatchMouseEvent(MouseEvent e, boolean repaint) {
		Point glassPanePoint = e.getPoint();
		Container container = contentPane;
		
		//把玻璃面板中的坐标转换到内容面板中
		Point containerPoint = SwingUtilities.convertPoint(glassPane, glassPanePoint, contentPane);
		System.out.println(containerPoint);
		if (containerPoint.y < 0) { // we're not in the content pane
			if (containerPoint.y + menuBar.getHeight() >= 0) {
				//鼠标在菜单栏上
			} else {
				//鼠标移到了菜单栏目的上面，如果有标题栏，这里鼠标应该在标题栏上
			}
		} else {
			//鼠标在contentPane面板上
			//找出他具体的是在哪个组件上
			//可能在contentPane或者checkbox或者按钮上
			
			//查找container中包含坐标x,y的最深的一个后代组件
			//这里查找出来的组件是contentPane的后代组件，只可能是checkbox和2个button
			Component component = SwingUtilities.getDeepestComponentAt(container, containerPoint.x, containerPoint.y);
			System.out.println((component==container)?"contentPane":"");
			System.out.println((component==liveButton)?"checkBox":"");
			
			//这里判断，如果鼠标在checkbox按钮上，则此时不在glassPane上面画点
			//此时会取消checkbox按钮的勾选状态
			if ((component != null) && (component.equals(liveButton))) {

				//把glasspane上面的坐标转换到checkbox上的点
				Point componentPoint = SwingUtilities.convertPoint(glassPane, glassPanePoint, component);
				
				
				component.dispatchEvent(new MouseEvent(component, e.getID(), e.getWhen(), e.getModifiers(),
						componentPoint.x, componentPoint.y, e.getClickCount(), e.isPopupTrigger()));
			}
		}

		//在玻璃面板上画出鼠标所在的点
		if (repaint) {
			glassPane.setPoint(glassPanePoint);
			glassPane.repaint();
		}
	}
}