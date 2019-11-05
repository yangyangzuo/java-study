package gui.components;

import java.awt.CheckboxMenuItem;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * awt限制了菜单条的使用范围：菜单只能在菜单条中使用，菜单条只能在独立窗口中使用 
 * 
 * 菜单泛指固定在窗口应用程序上方的浮动式选项列表，他是由菜单栏(menubar)和菜单项(menuitem)组成
 * 
 * 菜单项又可以派生出菜单(menu)和复选菜单项(checkboxmenuitem)
 * 
 * MenuShortcut,MenuComponent 继承 Object
 * 
 * MenuBar,menuItem继承MenuComponent
 * 
 * Menu,CheckboxMenuItem继承MenuItem
 * 
 * PopupMenu 继承Menu
 * 
 * @author Administrator
 *
 */
public class Gui_15_MenuBar
{
	public static void main(String[] args)
	{
		Frame f = new Frame();
		f.setBounds(200, 200, 500, 350);
		//一个菜单栏组件是一个水平菜单。通过setMenuBar()方法，把菜单栏加入到一个框架中，并成为所有菜单树的根
		
		//frame.setmenubar(),menubar方法frame里面
		//menubar里面加menu(菜单栏里面加功能)
		MenuBar mb = new MenuBar();
		
		Menu file = new Menu("file");
		file.add(new MenuItem("new"));
		//加一条分割线 
		file.addSeparator();
		file.add(new MenuItem("open"));
		file.add(new MenuItem("close"));
		
		//可以默认选择的选项菜单
		file.add(new CheckboxMenuItem("我可以选中哦"));
		mb.add(file);
		
		Menu edit = new Menu("Edit");
		edit.add(new MenuItem("undo"));
		edit.add(new MenuItem("paste"));
		edit.add(new MenuItem("cut"));
		edit.add(new MenuItem("copy"));
		//Menu 继承了 MenuItem
		//menu里面可以加menu,构成了多级菜单
		Menu other = new Menu("other");
		other.add(new MenuItem("other1"));
		other.add(new MenuItem("other2"));
		other.add(new MenuItem("other3"));
		edit.add(other);
		mb.add(edit);
		
		
		
		//帮助菜单
		mb.setHelpMenu(new Menu("help"));
		
		
		//弹出菜单，快截键菜单
		Menu cool = new Menu("cool");
		mb.add(cool);
		

		// 菜单快捷方式是使用虚拟键代码（而不是字符）创建的。例如，Ctrl-a（假设 Control 是加速键）
		// 的菜单快捷方式将通过类似以下的代码创建：
		// MenuShortcut ms = new MenuShortcut(KeyEvent.VK_A, false);
		// 此加速键是与平台有关的，可通过 Toolkit.getMenuShortcutKeyMask() 得到。

		MenuItem cool1 = new MenuItem("我带快截键哦", new MenuShortcut(KeyEvent.VK_A,false));
		cool.add(cool1);
		MenuItem cool2 = new MenuItem("普通");
		cool.add(cool2);
		
		
		
		
		
		
		
		
		f.setMenuBar(mb);
		
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter()
		{

			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}

		});
	}
}
