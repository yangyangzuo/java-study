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
public class SwingThread04 extends JFrame{
	//翻译:
	//http://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html#threading
	
	//worker thread and SwingWorker
	//当一个swing程序需要执行一个比较耗时的任务,它经常使用一个workder thread，也就是background thread(后台线程也称为守护线程)
	//如果主线程结束，即使后台线程没结束，程序也会杀死后台线程，并结束程序
	//每个运行在worker thread上的任务，都被描述为javax.swing.SwingWorker的一个实例
	//SwingWorker是一个抽象类，你必须继承他，来创建一个SwingWorker对象,匿名的内部类是创建一个简单的SwingWorker的好办法
	//SwingWorker提供了大量的通信和控制特性:
	//1.SwingWorkder子类可以定义一个方法，当后台任务完成后，自动的在event dispatch thread上调用执行
	//2.SwingWorker实现了java.util.concurrent.Future.这个接口允许后台任务提供一个返回值给其他的线程,这个接口中的其他方法允许
	//取消后台任务，并且可以知道后台任务是否完成，或者是否被取消
	//3.后台任务可以通过调用SwingWorker.publish提供一个中间的结果,会造成在event dispatch thread上执行SwingWorker.process
	//4.后台任务可以定义一些bound properties,改变这些properties trigger events,可以导致在event dispatch thread上执行事件处理方法
	//注意:
	//javax.swing.SwingWorker类是在java se 6被添加到API中的,另一个类也叫SwingWorker,被广泛的应用于相同的目的.这个旧的SwingWorder
	//不是java平台标准的一部分，不是jdk的一部分
	//新的javax.swing.SwingWorker完全是一个新类.他的功能不是旧的SwingWorker类的一个超集.两个类中的方法名称不一样
	//而且旧的SwingWorker的实例可以被重用，新的javax.swing.SwingWorker的实例是为后台任务而准备的
	
}




















































