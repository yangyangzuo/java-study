package gui.components;


import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * 修改截面内的默认的鼠标选取键图标
 * 
 * @author Administrator
 * 
 */
public class MouseImage
{
	/**
	 * 调用方法setCursor(MiNiMouseImage.getMiniMouseImage());
	 * 
	 * @return
	 */
	public static Cursor getMiniMouseImage()
	{
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("src/gui/tray.gif");
		Cursor c =  tk.createCustomCursor(img, new Point(10, 10), "use image is:mouse.gif");
		System.out.println(c.getName());
		return c;
	}
}
