package gui.paint;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 *　################################## awt绘图机制: ###############################
 * 
 * Component.paint()的api方法说明:
 * http://www.oracle.com/technetwork/java/painting-140037.html
 * 
 * 最开始的awt api是在jdk1.0中开发出来的，只有重组件存在
 * "heavyweight" means that the component has it's own opaque native window
 * 重组件意味着他们都有自己的本地平台不透明窗口,所以awt很大程度的依赖本地平台的绘制系统
 * (个人理解:这个本地平台不透明窗体,一个和操作系统有关的本地窗口，根据当前操作系统的实现不同而不同，这种组件的实现，是调用当前操系统的api来实现的，所以依赖当前操作系统
 * 而且，不同的操作系统，实现后的展现形式也不一样，比如一个窗口，有的按钮在左边，有的在右边，和java的平台一致性是有冲突的,所以后来就出现了swing来解决这个问题)
 * 
 * 轻量级组件是在jdk1.1中出现的
 * "lightweight" component is one that reuses the native window of its closest heavyweight ancestor
 * 轻量级组件会重用他最近的一个(重组件)祖先的本地平台不透明窗口
 * 所以awt在代码处理中，需要把轻量级组件的绘制也写出来
 * 因此，重量组件和轻量组件在绘制工作机制上稍微不同
 * 
 * 
 * 　通俗理解:
 * 　每个awt组件都有自己的操作平台窗口,在大型程序中，大量的这些窗口会占用较多的内存，故称为重组件
 * 　而swing是在他们的容器中把图像绘制出来，依托与awt重组件作为载体，相对于操作系统来说，他们并没有本地操作系统对应的窗口，占用的资源比较少，称为轻组件
 *  　awt是使用操作系统中对等的组件来进行操作，这些组件在操作系统中都有自己的本地不透明窗口，而swing本身没有本地操作系统对应窗体，他们依托与awt组件，所以他们可以透明
 *  可以有透明的背景，透明的背景可以使轻组件显示非矩形
 *  因为轻组件都是在其容器的窗口重绘制的，不是在自己的窗口重绘制，所以轻组件必须包含在一个重容器中，因此swing的窗体，小应用程序，窗口，对话框必须是重组件，以便提供一个
 *  可以在其绘制swing轻组件的窗口
 * 
 * 
 * 在jdk1.1以后，swing toolkit发布，it introduced its own spin on painting components. 
 * 大多数情况下,swing绘图机制和awt相识，甚至依赖与awt.但是swing也推出了一些新的机制和api,可以更容易的绘制
 * 
 * －－－－－－－－－－－－－－－－－－－－
 * awt中的绘图机制:
 * 	1.系统触发,系统触发绘制操作时，(系统会请求组件绘制他里面的内容)，通常由如下原因:
 * 		1.1 组件第一次在屏幕上可见(第一次显示，或者最小化，然后又显示出来)
 * 		１.2 组件尺寸大小改变(窗体大小改变了，会直接调用paint()方法,可以通过打印日志判断,或者代码调试)
 * 		１.3 组件被破坏，需要修复(例如:以前被隐藏的组件移动了，或者以前被隐藏的组件重新显示了)
 * 	２．应用程序触发
 * 		组件内部状态改变了，他需要更新组件内容(例如:一个按钮检测到被鼠标摁下，组件决定把按钮绘制成depressed状态的按钮)
 * －－－－－－－－－－－－－－－－－－－－
 * 
 * 
 * 	不管绘图机制如何被触发，AWT使用“回调”机制，这种机制对于重量级和轻量级组件相同的
 * 	即:绘制代码应该被放在组件的一个特定方法里面，子组件来重写这个方法，把要绘制的代码写到这个方法里面
 * 	java.awt.Component:   public void paint(Graphics g)    
 * 	当awt执行这个方法的时候，Graphics对象被预配置一个合适的状态属性，来绘制当前组件
 * 	graphics object's color = component's foreground property
 * 	Graphics object's font 	= component's font property.
 * 	The Graphics object's translation is set such that the coordinate (0,0) represents the upper left corner of the component.
 * 	The Graphics object's clip rectangle = the area of the component that is in need of repainting.
 * 	绘制代码必须graphics对象(或者由这个对象延伸而来的对象)来进行渲染绘制,可以按照需求自由改变graphics对象的特定属性
 * 	
 *	一般情况下，绘制代码不应该放到回调方法paint()以外的地方,因为有时候代码执行的时候，他并不适合绘制,例如:
 *	组件可见之前(此时grapchics对象可能为null)或者访问一个无效的graphics对象,此时是不可以绘制的
 *	Frame f = new Frame();
 *	f.setSize(500,500)
 *	Graphics g = f.getGraphics();//此时g为null
 *	f.setVisible(true);
 * 
 * 	同时也是不推荐程序直接调用paint()方法的,为了使应用程序触发绘制，awt提供了component对象中的如下方法，允许程序代码动态的请求绘制
 * 
 * 
 * 	－－－－－－－－－－－－－－
 * 	对于重组件，这两种触发机制在处理上稍微不同:(绘制机制如何发生)
 * 		对于系统触发绘制:
 * 			1.awt决定组件的一部分或者全部需要进行绘制(awt决定一部分组件或者所有组件需要绘制)
 * 			2.awt事件分发线程调用组件的paint()方法(awt决定事件分发线程来调用组件的paint()方法)
 * 			系统触发线程会直接调用paint()方法，我们无法控制
 * 		对于应用程序触发绘制:
 * 			1.程序决定组件的部分或者全部进行绘制(用来响应组件内部状态的改变),调用组件的repaint()方法，当然你也可以不响应，也就不用调用repaint()方法，也就不会绘制
 * 			(当组件内部状态改变时，程序代码来决定部分／全部组件的绘制)
 * 			2.响应组件内部状态改变也可以，合适的时候，都可以调用组件的repaint()进行绘制
　*	－－－－－－－－－－－－－－－－－－－－
 * 
 * 	public void repaint()      
 * 	public void repaint(long tm)      
 * 	public void repaint(int x, int y, int width, int height)      
 * 	public void repaint(long tm, int x, int y,int width, int height)             
 * 
 * 	如下代码展示了如何通过repaint()方法，来请求重绘
	MouseListener l = new MouseAdapter(){            
		public void mousePressed(MouseEvent e) {                
	  		MyButton b = (MyButton)e.getSource();                
	   		b.setSelected(true);                                        
	     	b.repaint();                        
	   	}             
	    public void mouseReleased(MouseEvent e) {                
	    	MyButton b = (MyButton)e.getSource();               
	    	b.setSelected(false); 
	    	b.repaint();
	 	}    
	 };                                        
 * 
 * 
 * 	当组件在进行复杂的渲染绘制时，应该传递参数给repaint()方法，制定需要更行的区域，不要执行一个无参数的repaint()方法，
 * 	不传递参数，会导致整个组件重绘，造成一些无用的绘制渲染
 * 
 * 	repaint()方法的详细执行过程:
 * 		1.程序代码调用组件的repaint(),此时会对awt注册一个异步请求，告诉awt这个组件需要重绘
 * 		2.awt的事件分发线程会调用这个组件的update()方法
 * 		3.如果组件没有覆盖update()方法，默认组件(容器组件)的update()会清除组件(重量级组件)的背景(查看下面重组件清除背景的原因解释)，然后简单的调用组件的paint()方法
 * 		4.程序员把需要绘制的内容写到paint()方法里面，就会被绘制出来
 * 	
 *		注意：如果组件第一次的repaint请求处理完之前，又注册了很多次repaint()请求，则多个绘制
 * 			请求会合并为一个单独的update()方法调用，多个请求绘制的内容合并到一个绘制区域，然后进行绘制
 * 		
 * 		重组件清除背景的原因解释:
 * 		注意:重组件，只要继承了Container,而又没有覆盖update()方法，则默认调用Contianer.update()方法,这个方法的源码里面会清除背景
 * 		Component.update()源码:
 * 		public void update(Graphics g) {
 *       	paint(g);
 *   	}
 *   	Contianer.update()源码
 *		public void update(Graphics g) {
 *			//会先判断是否是轻组件，如果不是轻组件，则会先清除组件的背景颜色，这由可能会造成闪烁
 *	    	if (isShowing()) {
 *	            if (! (peer instanceof LightweightPeer)) {
 *	                g.clearRect(0, 0, width, height);
 *	            }
 *	            paint(g);
 *	        }
 *   	}
 *   	如果一个组件是容器,此时调用的是Container.update()方法,该方法判断如果是重组件,会清除背景
 *   	为什么轻组件不清除背景?
 *   	因为轻组件是绘制在重组件之上的,绘制的时候,先绘制重组件,然后绘制轻组件,如果清除轻组件背景,则下面的重组件会显示出来,造成闪烁
 *   
 *   
 *   

 *   	默认情况下update()方法是调用paint()方法，那么调用repaint()方法后，注册的事件分发线程直接调用组件的paint()方法不就好了，这个update有什么用?
 *   
 *   	如果程序不需要把整个组件区域重绘，这个时候,update()就派上了用场,在update()方法重可以实现增量绘制(incremental painting.)
 *   	例如:在现有的组件的一个特定位置绘制一些内容,这样别的地方就不用重新绘制
 * 		事实上，大多数GUI组件不需要做增量绘制，所以大多数程序可以忽略update()方法和简单的重写当前组件的paint()方法就行了
 * 		这意味大多数组件,系统触发和应用程序触发绘制基本上是等价。
 * 
 * 		－－－－－－－－－－－－－－－－－轻量组件绘制－－－－－－－－－－－－－－－－－－－－
 * 			对于轻量级组件来说,他需要一个重量级组件作为依托窗体来进行绘制内容
 * 			当一个重量级的组件在绘制的时候,他必须要通知他所有的轻量级子孙组件进行绘制
 * 			查看Contianer.paint()方法api说明:
 * 			Paints the container. This forwards the paint to any lightweight components that are children of this container. 
 * 			绘制容器。该方法将Contianer.paint()方法会转发给所有此容器子组件的轻量级组件。
 * 			If this method is reimplemented, super.paint(g) should be called so that lightweight components are properly rendered. 
 * 			如果容器的此方法被重新实现，那么子容器应该调用 super.paint(g) 方法，从而可以正确地呈现容器内的轻量级组件。
 * 			If a child component is entirely clipped by the current clipping setting in g, paint() will not be forwarded to that child.
 * 			如果通过 g 中的当前剪切设置完全剪切某个子组件，则不会将 paint() 转发给这个子组件。
 * 			总结:如果一个容器覆盖了paint()方法,而且这个容器里面有轻组件,覆盖的方法里面要调用super.paint()方法,来保证当前容器里的轻组件能正常绘制
 * 
 * 			 public class MyContainer extends Container {         
 * 					public void paint(Graphics g) {             
 * 					// paint my contents first...             
 * 					// then, make sure lightweight children paint                                      
 * 					super.paint(g);        
 * 					//如果super.paint(g)这行代码丢失,该容器的所有轻量级组件都不会显示,这是jdk1.1中经常会出现的问题
 * 					}     
 * 			}                                        
 * 
 * 			注意:	
 * 			Container.update()的默认实现(上面的源码)中,没有使用递归调用子孙轻组件的update()或paint()方法
 *			这意味着任何重量级容器的子类，使用update()做增量绘画必须确保子孙的递归绘制
 *			幸运的是，很少有重量级的容器组件需要增量的绘画，所以这个问题不会影响大多数程序。
 *			
 * 
 * 
 * 	awt绘制向导:
 * 		1.大多数程序，只需要把绘制内容放到组件的paint()方法中即可
 * 		2.程序调用repaint()方法，触发一个线程调用update()->paint()方法,不应该直接调用paint()方法
 * 		3.如果绘制的组件比较复杂，应该调用带参数的repaint()方法，来保证一部分进行绘制，而不是整个组件进行重绘
 * 		4.因为repaint()方法会先调用update()方法，而update()方法又会调用paint()方法，所以，对于重组件来说,可以通过覆盖update()方法，来做增量绘制,轻组件不支持增量绘制
 * 		5.如果覆盖了Container.paint()方法，需要调用super.paint()方法，来确保该组件内的所有轻量子孙组件都被画出来
 * 
 * 
 * 
 * @author zuoyang
 *
 */
public class Paint_AWT extends Frame{
	public static void main(String[] args) throws InterruptedException{
		Paint_AWT t = new Paint_AWT();
		t.setSize(600,600);
		t.setLayout(null);
		
		
		Button button = new Button("button");
		button.setBounds(50,50,100,100);
		t.add(button);
//		Toolkit.getDefaultToolkit().beep();
		System.out.println(System.getProperty("awt.toolkit"));
		t.repaint();
		t.setVisible(true);
	}


	@Override
	public void paint(Graphics g) {
		Image img = null;
		try {
			img = ImageIO.read(new File("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/paint/test.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g.drawImage(img, 0, 0, this);
		
	}

	//绘制过程:
	//1.t.setVisible(true)
	//frame显示的时候，先调用
	
	
}
