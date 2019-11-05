package gui.opacity;

import java.awt.GraphicsDevice;
import java.awt.GraphicsDevice.WindowTranslucency;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
/**
 * 窗体透明原理:
 * 参考文档2:https://docs.oracle.com/javase/tutorial/uiswing/misc/trans_shaped_windows.html
 * @author zuoyang
 *
 */
	public class Opacity09 extends JFrame {
	    public Opacity09() {
	        super("TranslucentWindow");
	        setLayout(new GridBagLayout());
	        setSize(300,200);
	        setLocationRelativeTo(null);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	        //Add a sample button.
	        add(new JButton("I am a Button"));
	    }
	 
	    public static void main(String[] args) {
	        // Determine if the GraphicsDevice supports translucency.
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        GraphicsDevice gd = ge.getDefaultScreenDevice();
	 
	        //If translucent windows aren't supported, exit.
	        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.TRANSLUCENT)) {
	            System.err.println("Translucency is not supported");
	                System.exit(0);
	        }
	        
	        
	        SwingUtilities.invokeLater(new Runnable() {
	        // Create the GUI on the event-dispatching thread
	            @Override
	            public void run() {
	            	
	            	//1和2取一种即可 
	            	//1.如果使用look and feel,则必须在jframe创建之前设置,否则无法使用窗体背景透明提示:The frame is decorated
	            	//JFrame.setDefaultLookAndFeelDecorated(true);
	            	
	            	Opacity09 tw = new Opacity09();
	            	
	            	//2.如果不使用装饰,则必须在setOpacity()方法之前设置,否则无法使用窗体背景透明,提示:The frame is decorated
	            	tw.setUndecorated(true);
	            	//注意:在设置透明度之前,必须setUndecorated(true)
	            	
	            	
	            	
	            	
	                // Set the window to 55% opaque (45% translucent).
	            	//窗体及其窗体里面的,等价于AWTUtilities.setWindowOpacity(Window, float)
	                tw.setOpacity(0.55f);
	                
	                // Display the window.
	                tw.setVisible(true);
	            }
	        });
	    }
	//文档翻译:
	//https://docs.oracle.com/javase/tutorial/uiswing/misc/trans_shaped_windows.html
	//因为公共api更新完之后,修改是不允许的,所以当(窗体的)半透明特性和特定形状特性添加到java SE 6 Update 10发布时，
	//是通过一个私有的类(com.sun.awt.AWTUtilities)来实现的.
	//当jdk7发布的时候,这些功能会添加到public AWT package中.
	//如下是AWTUtilities类的方法映射到jdk中的方法
	/*
	Method in Java SE 6 Update 10-----JDK 7 Equivalent
	AWTUtilities.isTranslucencySupported(Translucency)-----GraphicsDevice.isWindowTranslucencySupported(WindowTranslucency)
	AWTUtilities.isTranslucencyCapable(GraphicsConfiguration)-----GraphicsConfiguration.isTranslucencyCapable()
	AWTUtilities.setWindowOpacity(Window, float)-----Window.setOpacity(float)
	AWTUtilities.setWindowShape(Window, Shape)-----Window.setShape(Shape) 
	AWTUtilities.setWindowOpaque(window,boolean)-----Window.setBackground(Color) Passing new Color(0,0,0,alpha) to this method, where alpha is less than 255, installs per-pixel translucency.
	
	注意:通过设置shap有很多缺点,
	//参考Opacity08.java,Opacity11.java,特定形状的锯齿很严重
	//参考http://servasoft.com/ui%E5%BC%80%E5%8F%91/swing%E7%AC%AC%E4%BA%8C%E5%88%80%EF%BC%9A%E6%9E%9D%E9%97%B4%E6%96%B0%E7%BB%BF%E4%B8%80%E9%87%8D%E9%87%8D-2.html
	
	 */
	//注意上面的方法,jdk>=7.0
	
	
}
