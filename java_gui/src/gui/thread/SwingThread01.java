package gui.thread;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * java doc中的java.swing包有如下说明: 
 * 
 * Warning: Swing is not thread safe. For more information see Swing's Threading Policy.
 * 
 * http://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html#threading
 * 
 * 
 * @author gudandan
 *
 */
public class SwingThread01 extends JFrame{
	//翻译:
	//http://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html#threading
	//swing的线程策略:
	//一般来说swing不是线程安全的.所有的swing组件以及他们相关的类，除非有特别说明，否则必须在事件调度线程(事件分发线程event dispatching thread)上进行访问
	//swing会响应用户的动作事件，然后进行处理
	//例如:点击一个按钮，会通知被添加到这个按钮上所有ActionListener
	//根据用户的动作而生成的所有事件，都会被分发到事件分发线程(event dispatching thread),所以程序员一般不会受到影响
	//但是，当swing应用程序构建和显示的时候，会受到影响
	//当调用swing中的main方法和applet中的方法时，不会转移到事件分发线程中去调用
	//因此在构建和显示swing应用程序和applet时，我们必须把控制转交事件分发线程
	//首选的方式是通过invokeLater来转移控制给事件分发线程
	//invokeLater方法安排Runnable在事件调度线程上处理
	//看如下例子
//	public class MyApp implements Runnable {
//	    public void run() {
//	        // Invoked on the event dispatching thread.
//	        // Construct and show GUI.
//			JFrame frame = new JFrame();
//			frame.setVisible(true);
//	    }
//
//	    public static void main(String[] args) {
//	        SwingUtilities.invokeLater(new MyApp());
//	    }
//	}
//	或者
//	public class MyApp extends JFrame{
//	    MyApp(String[] args) {
//	        // Invoked on the event dispatching thread.
//	        // Do any initialization here.
//			this.setSize(200,200);
//	    }
//
//	    public void show() {
//	        // Show the UI.
//			this.setVisble(true);
//		    }
//
//	    public static void main(final String[] args) {
//	        // Schedule a job for the event-dispatching thread:
//	        // creating and showing this application's GUI.
//	        SwingUtilities.invokeLater(new Runnable() {
//	            public void run() {
//	                new MyApp(args).show();
//	            }
//	        });
//	    }
//	}
	
	//事件分发线程也会对swing组件设置model时影响
	//例如:JTable设置Tableodel时，这个tableModel应该通过事件分发线程来设置
	//如果你在一个另外的线程中修改组件的model,则可能会异常，或者组件显示损坏
	//由于所有的事件被在事件分发线程上传递，所以要小心处理事件
	//尤其是在执行一个长期运行的任务,例如:网络io或者计算密集型处理,
	//当一个较长时间任务通过事件调度线程执行时，此时将阻塞事件调度线程调度任何其他线程。
	//即:事件分发线程在执行当前这个任务，当前这个任务又比较耗时，此时，事件分发线程就没有闲暇去调度别的事件线程了
	//此时事件线程就会被阻塞,应用程序可能会出现无反应
	//所以，当我们构建显示一个swing程序时，应该通过以下方式执行
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new SwingThread01();
			}
		});
	}
	
	public SwingThread01(){
		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}




















