package gui.opacity;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Window;
import java.lang.reflect.Method;

import com.sun.awt.AWTUtilities;
/**
 * 窗体透明原理:
 * 
 * 参考文档1:http://www.oracle.com/technetwork/articles/javase/translucent-shaped-windows-139324.html
 * 
 * @author zuoyang
 *
 */
public class Opacity06 extends Frame {

	public Opacity06() {
		this.setBackground(Color.red);
		this.setSize(212, 73);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		
		Opacity06 frame = new Opacity06();
		
		
		//判断系统是否支持半透明窗体和特定形状窗体特性
		if(AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.TRANSLUCENT)){
			System.out.println("TRANSLUCENT:表示simple translucency特性,即:简单透明");
		}
		
		if(AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.PERPIXEL_TRANSPARENT)){
			System.out.println("PERPIXEL_TRANSPARENT:表示shaping特性,即:可以改变窗体形状");
		}
		
		if(AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.PERPIXEL_TRANSLUCENT)){
			System.out.println("PERPIXEL_TRANSLUCENT:per-pixel translucency,即:每像素半透明");
		}
		
//		if ((AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.PERPIXEL_TRANSLUCENT)) &&
//			      (AWTUtilities.isTranslucencyCapable())) {
//			      //perform translucency operations here
//		}
		
		
		//通过反射运行的好处,不用important引入相关的类
		//如果将来该类被移除,可以在程序中控制,比如:通过捕获异常来处理
		//如果不通过反射,则必须important对应的类,如果客户机没有对应的类,则程序无法运行
		frame.setUndecorated(true);
		frame.setLayout(new FlowLayout());
		frame.add(new Button("hello"));
		try {
			   Class<?> awtUtilitiesClass = Class.forName("com.sun.awt.AWTUtilities");
			   Method mSetWindowOpacity = awtUtilitiesClass.getMethod("setWindowOpacity", Window.class, float.class);
			   mSetWindowOpacity.invoke(null, frame, 0.5f);
			   //setWindowOpacity(frame,opacity)方法对于窗体和窗体里面的东西都具有同一个透明度
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		frame.setVisible(true);
		
		
		
		
		
	}
	
/*
 * jdk>=6.10
 * 文档翻译:
 * 	http://www.oracle.com/technetwork/articles/javase/translucent-shaped-windows-139324.html
 *	How to Create Translucent and Shaped Windows
 *	JavaFX脚本是一种新的语言，提供了一套API创建RIA。
 *	它也为你提供了对标准的java语言访问的能力。其中介绍了java SE 6u10释放的主要特征是创建半透明状的窗体的能力。
 *	这包括：
 *	可以使应用窗体半透明,
 *	可以设置应用窗体的形状
 *	这些特性对于java.awt.Window类及其子类都有效,例如:javax.swing.JFrame, javax.swing.JFDialog, java.awt.Frame.
 *
 *
 *	半透明窗体:
 *	半透明窗体有2种效果:
 *	1.简单半透明
 *		简单半透明可以使窗口均匀的半透明,当一个窗体被设置简单半透明效果时,窗体上的所有像素都被指定一个alpha值,可以决定当前的透明度
 *	值越小,窗体越透明,最小值表示完全透明,最大值表示完全不透明
 *	2.per-pixel每像素半透明
 *		和简单半透明一样,per-pixel半透明可以是窗体均匀透明,但是性能原因不推荐使用这种方式
 *		同时他还可以让你独立控制每个像素的不透明度,可以使窗体不均匀透明或者均匀透明
 *		例如,他可以使窗体由上到下,出现一个渐变效果,逐渐的不透明
 *		需要注意的是:如果使用每像素半透明特性,使窗口里的一个特定区域半透明,则这个区域可能是可以点击的,也可能是不可点击的,这个依赖具体的平台
 *	
 *	特定形状窗体:
 *		window shape特性可以把一个undecorated window设置任意形状
 *		当这种效果应用到窗体上时,这个窗体可以一部分透明,一部分不透明,这样就可以显示特定的形状
 *		
 *	
 *	API Overview:
 *	com.sun.awt.AWTUtilities
 *	这个类不是官方支持的api一部分,只是一个实现,将来可能被删除或者被移动,这个类应该通过反射来使用
 *	(Supported and public API will appear in the next major JDK release.)
 *	支持的公共api将会出现在下一个主要的jdk版本中,也就意味着jdk7会支持
 *
 *	
 *	使用这些特性:
 *	因为系统不一定支持这个特性,需要使用AWTUtilities.isTranslucencySupported()这个方法检测系统是否支持
 *	
 *	 PERPIXEL_TRANSPARENT 表示是否支持shaping特性
 *	 TRANSLUCENT 表示是否支持simple translucency特性
 *	 PERPIXEL_TRANSLUCENT 表示是否支持per-pixel translucency特性
 *		
 *  对于per-pixel translucency这个特性,还需要创建窗口的时候,需要使用一个兼容性的图形配置(compatible graphics configuration)
 *  isTranslucencyCapable这个方法可以检查图形窗口创建的时候,使用默认的兼容性图形配置,是否支持per-pixel translucency特性
 *  所以判断代码应该如下:
 *  if ((AWTUtilities.isTranslucencySupported(AWTUtilities.Translucency.PERPIXEL_TRANSLUCENT)) &&
      (AWTUtilities.isTranslucencyCapable(defaultTranslucencyCapableGC))) {
      //perform translucency operations here
	}
 *  
 *  如果默认的图形配置不是每个像素的透明度的能力，你可以通过所有可用的图形结构，找到一个能够透明。使用下面的代码片段：
 *  
	GraphicsEnvironment env =
           GraphicsEnvironment.getLocalGraphicsEnvironment();
       GraphicsDevice[] devices = env.getScreenDevices();
                     

      for (int i = 0; i < devices.length && translucencyCapableGC == null; i++) {

           GraphicsConfiguration[] configs = devices[i].getConfigurations();
           for (int j = 0; j < configs.length && translucencyCapableGC == null; j++) {
               if (AWTUtilities.isTranslucencyCapable(configs[j])) {
                   translucencyCapableGC = configs[j];
               }

           }
       }
 *
 *
 *	如果确定系统支持特性,可以把特性应用到顶级窗口上:
 *
 *	(使窗体透明)Making a Window Translucent:
 * 
	1.Setting the Opacity Level of a Window 
 *	控制窗口透明度一般可以使用AWTUtilities.setWindowOpacity()方法
 *	public static void setWindowOpacity(java.awt.Window paramWindow, float opacity)
 *	第一个参数表示java.awt.Window类或者他的子类,例如:java.swing.JFrame
 *	第二个参数表示不透明度,值越小,越透明,值的范围是[0f,1f],如果值超出范围,抛出IllegalArgumentException
 *	注意:这个特性不能应用到全屏窗口上,全屏模式下,如果opacity值小于1f,抛出IllegalArgumentException
 *	该方法会使窗体及其窗体里面的所有内容都具有相同的透明度
 *	
 *
 *
 *	获得窗口的不透明度
 *	float opacity = AWTUtilities.getWindowOpacity(frame);
 *
 *	
 *	2.Enabling Per-Pixel Translucency 
 *
 *	setWindowOpaque()方法可以使特定的窗口支持 per-pixel alpha特性
 *	public static void setWindowOpaque(Window paramWindow, boolean paramBoolean)
	AWTUtilities.setWindowOpaque(frame, false);
	参考Opacity07.java
 *
 *
 *	3.Setting the Shape of a Window 
 *	setWindowShape()方法可以设置窗口的形状
 *	public static void setWindowShape(Window paramWindow, Shape paramShape)
 *	paramShape是java.awt.Shape接口的实例,例如:Ellipse2D.Float,RoundRectangle2D.Float.
 *	参考Opacity08.java
 *
 *
 *
 *
 *	api说明:
 *	所有的方法都是public static(具体可以查看com.sun.awt.AWTUtilities源码) 
	enum Translucency:
		Represents the kinds of translucency supported by the underlying system
	boolean isTranslucencySupported(Translucency translucencyKind):
		Returns if the given level of translucency is supported by the underlying system
	void setWindowOpacity(Window window, float opacity) 
		Sets the opacity of a window
	float getWindowOpacity(Window window) 
		Returns the opacity of a window
	Shape getWindowShape(Window window)	
		Returns the shape of a window
	void setWindowShape(Window window, Shape shape)	
		Sets the shape of a window	
	void setWindowOpaque(Window window, boolean isOpaque)	
		Enables the per-pixel translucency for a window
	boolean isWindowOpaque(Window window)	
		Returns whether the window is opaque or translucent
	boolean isTranslucencyCapable(GraphicsConfiguration gc)
		Verifies whether a given graphics configuration supports the per-pixel translucency
 *  
 *
 *
 */
	
	
}
















