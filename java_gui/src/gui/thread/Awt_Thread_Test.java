package gui.thread;

import java.awt.Frame;

/**
 * JFrame类的setDefaultCloseOperation()方法说明:
 * AWT Threading Issues 
 * http://docs.oracle.com/javase/8/docs/api/java/awt/doc-files/AWTThreadIssues.html
 * 
 *
 * 
 * 
 * 
 * 
 * awt线程描述：
 * 
 * awt会图：
 * 
 * 更新显示由一种被称为AWT线程的独立的线程来完成。这个线程可用来处理与显示更新相关的两种情况。
 * 
 * (参考Awt_Thread_Test2.jpg)
 * 
 * 第一种情况是显露（exposure），它在首次显示时，或在部分显示已被破坏而必须刷新时出现
 * 显示的破坏可能发生在任何时刻，因此，我们的程序必须能在任意时刻更新显示。
 * 
 * 第二种情况是在程序重画带有新内容的画面时。这种重画可能会要求首先擦除原来的图像
 * 
 * paint(Graphics g)方法 当组件第一次显示，或受损的部分需要修复时被调用。
 * 除非必要，更新不会完全覆盖整个图形区域，而是严格限制在被破坏的范围内。
 * 
 * repaint()方法 对repaint()的调用可通知系统：你想改变显示，于是系统将调用paint()。 
 * 
 * repaint()方法api描述：
 * 
 * 如果此组件是轻量级组件，则此方法会尽快调用此组件的 paint 方法。否则此方法会尽快调用此组件的 update 方法。
 * 
 * 轻组件基于swing,已经实现了双缓冲，所以直接调用paint方法,重组件基于awt没有实现双缓冲 ,所以会调用update方法  
 * 
 * 
 * update(Graphics g)方法 repaint()实际上产生了一个调用另一方法update
 * ()的AWT线程。update方法通常清除当前的显示并调用paint()。
 * 
 * update()方法可以被修改 ，例如：为了减少闪烁可不清除显示而直接调用paint()。
 * 
 * 
 * 
 * 
 * 双缓冲 
 * @author Administrator
 * 
 */
public class Awt_Thread_Test
{
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setSize(200,200);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		try {
			Thread.currentThread().sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-------");
	}
	
}
