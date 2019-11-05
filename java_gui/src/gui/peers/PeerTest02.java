package gui.peers;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

/**
 * http://journals.ecs.soton.ac.uk/java/tutorial/ui/components/peer.html
 * 
 * 
 * 如果把一个组件添加到一个已经可见的容器中,你需要主动告诉awt，创建这个组件的同味体(peer),
 * @author zuoyang
 *
 */
public class PeerTest02 extends Frame {
	
	public static void main(String[] args) throws InterruptedException {
		//翻译文档:
		//http://journals.ecs.soton.ac.uk/java/tutorial/ui/components/peer.html
		//awt最开始设计的是一个和平台无关的API,但是预留了每个平台的外观和行为look and feel
		//例如:awt的button api只有一个类Button,但是这个按钮在windows,mac,linux显示的都不一样
		//awt看是提供了一个和平台无关的矛盾的api类Component,但是却利用不同平台代码来实现(peer)
		//具体来说，每个awt组件(Component,MenuComponent等等以及他们的子类)都有一个peer类，每一个component都有一个peer对象类控制这个对象的look and feel
		//下图:
		//(平台独立的api)							(特殊平台的实现)
		//java.awt.Button -----------------------ButtonPeer
		//											|
		//											|
		//								|------------------------|
		//								|			|			 |
		//						MacButtonPeer  linuxButtonPeer  windowsButtonPeer
		// Button peer在各个平台上通过实现ButtonPeer接口被实现
		// java.awt.Toolkit类定义了各种方法，描述每个组件对应的peer实现
		
		
		//peers是怎么被创建的?
		//peers是懒创建的,当他们的组件对象第一次被画在屏幕上时，同位体才会被创建
		//当你添加一个组件到一不可显示的容器上时，这个容器是没有peer的，当这个容器第一次显示，他的peer以及所有子组件的peers才会被创建
		//但是如果你添加一个组件到一个已经可以见的容器上时，你需要明确的告诉awt去创建这个组件的peer,可以通过调用validate()方法
		//Container.add()方法有这样说明:
		//如果已经将某个组件添加到显示的容器中，则必须在此容器上调用 validate，以显示新的组件。
		//如果添加多个组件，那么可以在添加所有组件之后，通过只调用一次 validate 来提高效率。
		
		
		
		PeerTest02 t = new PeerTest02();
		t.setLayout(new FlowLayout());
		t.setSize(500, 500);
		t.add(new Button("test"));
		t.setVisible(true);
		Thread.currentThread().sleep(2000);
		Button b = new Button("22test");
		t.add(b);
		t.validate();

		
		
		//peers是如何处理事件的?
	}
}






























