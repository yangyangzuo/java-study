package gui.peers;

import java.awt.AWTError;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.peer.FramePeer;

import sun.awt.HeadlessToolkit;

public class PeerTest00 extends Frame {
	
	//openjdk下载地址:
	//http://openjdk.java.net/faq/ ---> 搜索download--->http://download.java.net/openjdk/jdk7
	public static void main(String[] args) {
		
		
		//1.同位体是如何创建的
		//当一个组件绘制在屏幕上时，实际是调用这些组件的本地api,绘制出来
		//Componnet.addNotify()方法描述如下:
		//通过将此 Component 连接到一个本地操作系统屏幕资源，使其成为可显示的。
		//该方法应该由Toolkit内部调用，不应直接由程序调用。
		//换句话说，Component.addNotify()方法会被调用，以用来把组件绘制到屏幕上
		//这个方法实际就是调用不同的操作系统底层实现，来创建组件的同位体

		new Frame().setVisible(true);
		//我们可以分析setVisible()方法，观察组件是如何创建和显示的
		//分析setVisible()方法发现，最终调用的是window.show()方法
		//然后依次调用frame.notify(),window.notify(),component.notify(),container.notify()
		//这几个方法是用来创建同位体的
		//对于frame.notify()实际是Toolkit.getDefaultToolkit().createFrame(Frame)
		//但是Toolkit是个抽象类,其中protected abstract FramePeer createFrame(Frame target)是个抽象方法，那么如何调用的呢
		//查看Toolkit.getDefaultToolkit()方法:
		//他实际是通过加载一个toolkit类的实现来完成的
		//他查找系统变量为awt.toolkit的类名，然后根据这个类名new一个实例
		System.out.println(System.getProperty("awt.toolkit"));//mac系统下为sun.lwawt.macosx.LWCToolkit
		//根据系统变量来加载类名有什么好处呢？不同的操作系统，底层有自己的实现，可以有自己的类名,这样就可以方便的创建一个toolkit对象，
		//同时他对于这些创建同位体的方法都做了本地系统的实现,例如:createWindow(),createFrame(),createButton()等等
		//再看一下Toolkit类的说明:
		//Toolkit类是所有 Abstract Window Toolkit 实际实现的抽象超类。Toolkit 的子类被用于将各种组件绑定到特定本机工具包实现。
		//大多数应用程序不应直接调用该类中的任何方法。Toolkit 定义的方法是一种“胶水”，将 java.awt 包中与平台无关的类与 java.awt.peer 中的对应物连接起来。
		//Toolkit 定义的一些方法能直接查询本机操作系统。
		//说白了,toolkit就是加载本地操作系统上对应的实现，然后生成对应的组件,这个是联系本地操作系统和java组件的纽带
		
		

		
	}
}
