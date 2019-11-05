package gui.components;


import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 
 * paint(Graphics g):
 * 
 * paint()方法在第一次生成框架/图象的时候执行，或者当改变尺寸大小，
 * 
 * 或者最小化后再最大话，或者失去焦点再得到焦点的时候都 awt线程都会自动调用执行该方法
 * 
 * 当组件大小改变或者隐藏后又显示，awt线程都会重新绘制组件组件上原来
 * 
 * 绘制的图形就不复存在了，这一过程称为"曝光",曝光时，awt现成会自动调用paint()方法
 * 
 * repaint():
 * 
 * 当程序显示调用repaint()方法的时候，repaint()方法会调用update(Graphics g),
 * 
 * repaint()实际上产生了一个调用另一方法update()的awt线程
 * 
 * 然后update(Graphics g)方法会先清除当前的显示再调用paint(Graphics g)方法
 * 
 * 
 * update(Graphiscs g):
 * 
 * update方法会先清除组件表面的内容(用组件背景颜色覆盖组件)，在调用Paint()方法，清除组件表面内容的时候，会造成延迟，造成闪烁
 * 
 * 
 * repaint()方法测试
 * 
 * 在程序中调用repaint()方法，该方法会调用update()方法， 而update()方法又会调用paint()方法
 * 
 * 
 * 重量级和轻量级组件的repaint() 无论是重量级还是轻量级组件，在显示出来的时候就会调用paint(g); 
 * 不同的是调用repaint()时，对于重量级组件会首先调用upate(g), 然后upate(g)在调用paint(g),
 * 而在调用paint(g)，upate(g)会首先刷新组件背景。
 * 我们有时候要双缓冲的时候就可以重写upate(g).
 * 
 * 但是对于轻量级组件，调用repaint(g)时，不会调用upate(g),
 * 而是很快调用paint(g),而这时候的paint(g)方法中就需要super.paint(g）来帅新背景。。
 * 
 * 
 * paint(Graphics g),update(Graphics g),repaint()方法关系详细见图paint.jpg
 * 
 * @author Administrator
 * 
 */
public class RepaintTest
{

	public static void main(String[] args)
	{
		MyFrame m = new MyFrame();
		m.setSize(200, 300);
		m.setVisible(true);
		m.addMouseListener(m.new MyMouse());
	}

}

class MyFrame extends Frame
{
	private int x = 30, y = 40;

	@Override
	public void paint(Graphics g)
	{
		System.out.println("paint(Graphics g)执行了------------");
		// 需要得到鼠标惦记的位置
		Color c = g.getColor();
		g.setColor(Color.red);
		g.drawString("a", x, y);
		g.setColor(c);
	}

	@Override
	public void repaint()
	{
		System.out.println("repaint()执行了------------");
		super.repaint();
	}

	@Override
	public void update(Graphics g)
	{
		System.out.println("update(Graphics g)执行了------------");
		super.update(g);
	}

	class MyMouse extends MouseAdapter
	{

		@Override
		public void mouseClicked(MouseEvent e)
		{
			x = e.getX();
			y = e.getY();
			repaint();
		}

	}
}
