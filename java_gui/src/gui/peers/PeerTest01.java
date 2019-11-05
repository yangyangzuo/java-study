package gui.peers;

import java.awt.Component;
import java.awt.Frame;

public class PeerTest01 extends Frame {
	
	 
	
	//openjdk下载地址:
	//http://openjdk.java.net/faq/ ---> 搜索download--->http://download.java.net/openjdk/jdk7
	public static void main(String[] args) {
		
		
		//2.轻组件和重组件的由来
		
		//轻组件:完全通过java代码绘制，不通过本地操作系统的api实现
		//重组件:每个重组件和本地的操作系统对应着一个本地的api实现，通过调用本地api绘制一个组件
		
		//Component类的说明:
		//Component 类是与菜单不相关的 Abstract Window Toolkit 组件的抽象超类。
		//还可以直接扩展类 Component 来创建一个轻量级组件。轻量级组件是与本机不透明窗口不相关的组件。
		
		//实际上:通过直接扩展Componnet或者Container类就是一个轻组件
		//其他类是重组件
		//重组件和同位体有关(本地操作系统的实现),java.awt.peer包中定义每个重组件对应的一个本地系统的一个类
		//因为java.awt.peer包中的类不允许程序员直接操作，所以在javadoc中也没有给出该包的文档说明，可以直接看openjdk源码
		//例如:FramePeer,ButtonPeer,WindowPeer等等
		//其中有一个LightWeightPeer这个类，从类名上可以看出来，这是一个轻量级的peer，也就是轻组件名字的由来
		
		//我们定义一个组件mylightcomponent直接继承component
		//当这个组件被添加到框架中显示的时候，我们知道，他的notify方法会被调用
		Frame f = new Frame();
		f.add(new mylightcomponent());
		f.setVisible(true);
		
		//如果我们直接扩展一个Component类或者Container类，按照上面流程，这个组件绘制到屏幕上时，会调用addNotify()方法
		//我们通过debug，分析,可以发现最先调用的是Component.addNotify(),此时
		//this.peer = peer = getToolkit().createComponent(this);
		//调用的是Toolkit.getDefaultToolKit().createComponent(this);
		//此时创建的是NullComponentPeer对象，这个类被从java.awt.peer包中移到了sun.awt.NullComponentPeer
		//因此通过jdk源码直接是看不到的，可以在网上搜索该文件源码，或者下载openjdk查看
		//查看openjdk源码可以发现NULLComponentPeer实现了ComponnetPeer接口，
		//类说明:
//				Implements the LightweightPeer interface for use in lightweight components
//				that have no native window associated with them.  This gets created by
//				 default in Component so that Component and Container can be directly
//				 extended to create useful components written entirely in java.  These
//				 components must be hosted somewhere higher up in the component tree by a
//				 native container (such as a Frame).
//				 
//				  This implementation provides no useful semantics and serves only as a
//				 marker.  One could provide alternative implementations in java that do
//				  something useful for some of the other peer interfaces to minimize the
//				 native code.
//				
//				 This was renamed from java.awt.LightweightPeer (a horrible and confusing
//				 name) and moved from java.awt.Toolkit into sun.awt as a public class in
//				 its own file.
				//ComponentPeer是纯java实现，没有通过本地操作系统来实现一个组件的绘制，所以被称为轻组件的由来
				
				//相对的，我们看一下Window类,他的addNotify方法，通过调试，可以发现，他会创建一个WindowPeer,Toolkit.createWindow(this);
				//这个windowpeer会使用本地系统api创建
				
				
				//我们可以看到toolkit类的说明:
				//大多数应用程序不应该直接调用这个类的方法
				//Toolkit中定义的方法，是胶水，把java.awt包中的不依赖本地系统的类 和 java.awt.peer粘连起来
				//toolkit一些方法可以直接查询本地操作系统的相关信息,例如本地操作系统的分辨率,本地打印任务接口，读取本地的一个图像等
				//通过第二句话可以看出来:
				//java.awt包中的组件和java.awt.peer包中的同位体一一对应
				//每当要画一个组件，就需要创建一个对应的同位体,那么怎么创建同位体呢
				//toolkit类中定义了方法,比如:
				//createComponent()方法说明:
				//创建一个组件或者容器的同位体，这个同位体是没有窗体的,而且允许直接扩展Component或者Containerl类来创建一个存粹有java定义的无窗体组件

				//Toolkit是个抽象类,其中很多方法是抽象方法，getDefaultToolKit()方法中，我们可以发现他是加载一个对于本地操作系统的实现类
				//System.getProperty("awt.toolkit"),来实现的，mac中是sun.lwawt.macosx.LWCToolkit，不同的系统，实现peer调用操作本地操作系统的api
				//这样就实现了通过本地系统来创建组件的peer
		
	}
}
class mylightcomponent extends Component{
	
}