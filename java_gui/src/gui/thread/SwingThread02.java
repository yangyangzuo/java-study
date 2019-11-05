package gui.thread;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 参考文档:
 * 
 * swing中的并发
 * 
 * https://docs.oracle.com/javase/tutorial/uiswing/concurrency/index.html
 * 
 * @author gudandan
 *
 */
public class SwingThread02 extends JFrame{
	//翻译:
	//http://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html#threading
	//小心使用并发对于swing编程非常重要，一个写的好的swing程序，使用并发来创建图形用户接口，不会出现无响应，程序会对于用户的任何操作响应很及时
	//swing程序需要处理如下线程:
	//1.initial threads,这个线程用来执行初始化应用程序代码
	//2.the event dispatch thread,用来执行事件处理的代码。大多数用来和swing交互的代码必须在这个线程上执行
	//3.worker threads,也被称为background threads(后台线程),用来执行耗时的后台任务
	//程序员不需要些代码来创建这些线程，runtime和swing框架提供这些线程,程序员的任务是利用这些线程，创建及时响应的，易于维护的swing程序
	//像其他运行在java平台上的程序一样，swing程序可以创建额外的线程和线程池,但是对于基本的swing程序，上面描述的线程足够用了
	//其中worker thread需要着重讨论，因为运行他们的任务通过javax.swing.SwingWorker来创建，这个类有很多有用的特性，包括交互和协调worker thread任务和其他线程上的任务
	
	
	//initial treads:
	//每个程序都有一组线程,这些线程是应用程序逻辑的开始
	//在标准程序中，仅仅有一个这样的线程:这个线程用来执行程序的main方法
	//在applets中，initial线程用来构建applet对象，并执行applet的init()方法和start()方法
	//这些行为可能发生在一个单一的线程中，也可能发生在2个或者3个不同的线程中，取决于不同的java平台的实现
	//在本课中，我们称这些线程为initial threads
	//1.在swing程序中，initial threads不需要做太多工作
	//他们的最重要的工作是创建一个Runnable对象，用来初始化GUI,并安排这个Runnable对象在event dispath thread上执行
	//一旦gui被创建了,程序主要由gui事件来驱动，每个事件都会在事件分发线程上执行一个短任务
	//应用程序代码可以在event dispatch thread上指定额外的任务(if they complete quickly, so as not to interfere with event processing)或者一个worker thread(一个耗时的运行任务)
	//一个initial thread安排一个gui的创建任务，可以通过：
	//javax.swing.SwingUtilities.invodeLater或者javax.swing.SwingUtilities.invokeAndWait
	//这两个方法都需要一个参数:Runnable对象，用来定义一个任务 
	//invokeLater简单的分配一个任务，然后返回;invokeAndWait会等待这个任务完成后再返回
	//这两个方法可以不在其他线程中的代码加入到event queue队列中，这样就可以被edt派发，从而通过edt执行
	//这两个方法的区别:
	//invodeLater(runnable),把可以运行对象runnable放入event queue之后，invokeLater()方法就返回了
	//invokeAndWait(runnable),把可以运行对象runnable对象放入event queue之后，并没有立即返回，一直等待runnable对象中的run方法执行完之后,invokeAndWat()方法才返回
	
	
	//2.在applet程序中,gui的创建任务必须在init()方法中运行，并且使用invokeAndWait
	//如果不这样，init可能在gui创建之前就返回了,当浏览器启动一个applet可能会出现问题
	//在非applet程序中，分配一个gui的创建任务通常是initial thread最后才做的一件事,所以使用invodeLater和invodeAndWait都可以
	//为什么initial thread不简单的创建gui本身?
	//因为几乎所有的代码用来创建swing组件或者和swing组件交互，都必须运行在event dispatch thread上
	
	
	//举例:
	//swing程序，图形创建成功之后，所有的操作都是通过event dispatch thread来执行的，任何用户的操作，ui的绘制和更新
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new JFrame().setVisible(true);
			}
		});
		//代码执行到这,initial线程就结束了
		//initial执行了SwingUtilities.invokeLater()方法代码
		//SwingUtilities.invokeLater()负责把gui创建放置到了event queue之中，后面所有的交互到通过swing的event dispatch thread来处理了
		//可以查看event dispatch thread.jpg
	}
	
	//SwingUtilities.invokeLater(Runnable doRun)方法说明:
	//该方法会使doRun.run()方法在awt的event dispatch thread异步执行,
	//当前的event queue队列中的awt事件处理完成之后，就会执行doRun.run()方法，即:执行invokeLater()方法的时候，doRun.run()方法被添加到了event queue队列的最后
	//当前队列中的事件处理完成之后，就会执行doRun.run()方法
	//当一个应用程序需要更新gui的时候，可以调用invokeLater()方法，把更新gui的代码加入到event dispatch thread中去
	//如果当前线程已经处于event dispatching thread,你还在当前线程上执行该方法.例如:
	//在Jbuttton's actionlistener中，你调用SwingUtilities.invokeLater(doRun),此时doRun.run()方法代码，仍然会延迟，等待,直到当前event queue里面所有事件被处理完
	//Note that if the doRun.run() throws an uncaught exception the event dispatching thread will unwind (not the current thread).
	//任何一个线程中都可以调用invokeLater()这个方法，把runnable对象的run方法加入到event dispatch thread中
	//可以参考SwingThread032.java
	
	
	
	//SwingUtilities.invokeAndWait(Runnable doRun)方法说明:
	//该方法导致doRun.run()方法在awt event dispatching thread上同步执行
	//当这个方法调用时，当前这个代码块,即执行SwingUtilities.invokeAndWait()方法的当前线程会阻塞,直到event queue中的等待处理的awt events被处理完，然后doRun.run()方法才会返回
	//当doRun.run()方法返回之后，invokeAndWait()方法才会返回，此时，调用SwingUtilities.invokeAndWait()方法所在的线程才会继续向下执行
	//即:SwingUtilities.invokeAndWait()方法调用的时候，会把doRun对象的run()方法放入到event queue队列的最后，然后等待，队列中的事件处理完，然后等待run()方法执行返回，此时当前线程才会解除阻塞
	//当一个应用程序需要更新gui的时候，可以调用invokeAndWait()方法，把更新gui的代码加入到event dispatch thread中去,但是当前线程会阻塞
	//注意，不能再event dispatch thread上调用该方法，否则会造成死锁，参考SwingThread033.java
	
	
}




















































