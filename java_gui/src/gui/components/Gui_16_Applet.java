package gui.components;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;

/**
 * 
 * Applet又称为Java小应用程序，是能够嵌入到一个HTML页面中，并且可通过Web浏览器下载和执行的一种Java类 。
 * Applet不需要main()方法，由Web浏览器中内嵌的Java虚拟机调用执行。
 * 
 * 
 * Applet的安全限制:
 * 
 * 因为applet是从远端服务器上下载并且在本地执行，所以安全性就显得格外重要。
 * 通过限制applet在沙箱(applet的运行环境)中运行，保证了对本地系统而言applet是安全的。
 * 
 * 
 * applet在沙箱中运行时：
 * 
 * ⑴不能运行任何本地可执行程序；
 * 
 * ⑵除了存放下载的applet的服务器外，applet不能和其它主机进行通信。
 * 
 * ⑶不能对本地文件系统进行读写
 * 
 * 
 * Applet的生命周期
 * 
 * init()：当浏览器加载applet，进行初始化的时候调用该方法。(初始化的时候执行，执行一次)
 * 
 * start()：在init()方法之后调用。当用户从其它页面转到包含applet的页面时，该方法也被调用。
 * 
 * stop()：在用户离开包含applet的页面时被调用。
 * 
 * destroy()：当applet不再被使用，或浏览器退出的时候，该方法被调用。
 * 
 * 
 * 
 * paint()方法
 * 
 * Applet本质上是图形方式的，我们应该在图形环境中绘制我们的显示内容。
 * 
 * 我们可以通过创建一个paint()方法在Applet的panel上绘图。只要Applet的显示需要刷新，paint()方法就会被浏览器环境调用。
 * 
 * 例如，当Applet的显示尺寸发生变化的时候，或浏览器窗口被最小化或被要求以图标方式显示时，这种调用就会发生。
 * 
 * 我们应该编写自己的paint()方法，以使它在任何时候被调用，都能正常地工作。
 * 
 * 对它的调用是异步产生的，且由Applet的运行环境而不是程序来驱动 。
 * 
 * paint()方法带有一个参数，它是java.awt.Graphics类的一个实例。
 * 
 * 这个参数总是建立该Applet的panel的图形上下文,我们可以用这个图形上下文在Applet中绘图或写入文本。
 * 
 * @author Administrator
 * 
 */
public class Gui_16_Applet extends Applet
{

	@Override
	public void destroy()
	{
		System.out.println("destroy");
	}

	@Override
	public void init()
	{
		System.out.println("init");
	}

	@Override
	public void start()
	{
		System.out.println("start");
	}

	@Override
	public void stop()
	{
		System.out.println("stop");
	}

	@Override
	public void paint(Graphics g)
	{
		g.drawString("hello", 30, 30);
	}
	
	 

}
