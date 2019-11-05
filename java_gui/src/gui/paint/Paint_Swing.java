package gui.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;

/**
 * ################################## swing绘图机制: ###############################
 * 
 *   
 *  JComponent类是除顶层容器外所有 Swing 组件的基类。要使用继承自 JComponent 的组件，必须将该组件置于一个根为顶层 Swing 容器的包含层次结构（containment hierarchy）中。
 *  顶层 Swing 容器（如 JFrame、JDialog 和 JApplet）是专门为其他 Swing 组件提供绘制自身场所的组件。
 *  
 * 
 * 轻量组件和半透明
 * 由于轻量级组件借用的是他的重组件祖先屏幕资源来进行绘制,所以他们支持半透明
 * 这种工作机制是由后向前面绘制,先绘制后面的重组件窗口,然后轻组件在重组件的窗口上绘制
 * 因此,如果一个轻组件的一部分或者全部没有绘制出来,他下面的组件就会显示出来
 * 这也是为什么默认的update()方法里面轻组件不清除背景的原因,详细查看Container.update()源码
 * 
 * 
 * 
 * 
 * 
 * 	轻量级组件实现的窗口的一些特性(显示，隐藏，移动，改变尺寸等等),完全是用java代码写的,对于轻组件实现的这些特性功能函数,
 *sawt必须明确的告知这些轻组件进行绘制工作，轻组件框架也是调用repaint()来告知这些轻组件进行绘制
 *		
 *			－－－－－－－－－－－－－－
 *			轻量级组件，绘制机制:
 *				1.来自本地原生系统的系统触发绘制请求(The system-triggered paint request originates from the native system)
 *				  例如:轻量级组件的(重量级祖先)祖先组件首先显示,导致直接调用组件的paint()方法
 *				 (i.e. the lightweight's heavyweight ancestor is first shown), which results in a direct call to paint().
 *				2.来自于轻量框架的系统触发绘制请求(The system-triggered paint request originates from the lightweight framework)
 *					例如:轻量级组件大小改变，导致调用组件的update()方法,update()方法默认调用paint()方法
 *					换句话说，update()方法和paint()方法实际上和重组件没有什么区别，但是轻组件上不能使用 incremental painting
 *			
 *
 *			轻组件实际是在他的重组件祖先的窗口上绘制的，轻组件支持透明
 *			在绘制的过程中，从后往前绘制的，所以，如果轻组件的一部分或者所有都没有被绘制，则这个组件下面的组件就会被显示出来
 *			这也是为什么轻组件的update()方法，不会清除背景，如果清除背景，则轻组件下面的组件会闪现
 *			－－－－－－－－－－－－－－
 * 			提高绘制性能:
 * 				1.缩小需要绘制渲染的区域Using the clip region to narrow the scope of what is rendered. 
 * 				2.使用布局内部知识的范围缩小要绘制的子组件（轻量级的）。Using internal knowledge of the layout to narrow the scope of what children are painted (lightweights only).
 * 			.......
 * 
 * 	swing继承了awt的绘制模型,并对他进行了扩展,以便可以提高性能和扩展性
 * 	和awt一样,swing支持paint()方法和repaint()来触发update
 * 	另外swing内置了双缓冲和一些其他特性,例如:borders和ui delegate
 *  swing还提供了RepaintManager api,程序员可以定制更多的绘制机制
 *  
 *  双缓冲:
 *      public boolean isDoubleBuffered() 
 *      public void setDoubleBuffered(boolean o)
 *      
 *  swing双缓冲机制:使用一个屏幕外的容器层级结构来实现,一般是一个顶层窗口top-level window
 *  虽然这个属性可以设置在一个基本的组件上,但是,如果这个属性设置在一个特定的容器上,
 *  则不论这个容器内的轻组件设置的doubleBuffered为何值,这个容器内的所有轻组件都会被绘制在一个屏幕外的缓冲区里面
 *  默认情况下,所有的swing component的这个属性都是true,
 *  实际上，影响较大的是JRootPane,如果对于JRootPane设置了双缓冲，则所有JRootPane里的swing组件都打开了双缓冲 
 *  大多数情况,swing程序不需要对于double-buffering做处理,除非你决定打开或者关闭的时候.对于一个平滑的渲染图形的时候,你需要开着双缓冲(否则会造成闪烁)
 *  
 *  
 *  

 *  
 *  swing在jComponent上提供了一些额外的属性,用来提高内部绘制机制
 *  这些属性主要用来解决如下2个问题:(这2个问题可能会导致轻组件绘制是一个很昂贵的操作)
 *  1.tratransparency透明度:
 *  	当一个轻组件被绘制的时候,这个组件可能一部分/全部被绘制,可能一部分/全部是透明的,这就意味着,不论什么时候,这个轻组件被绘制,
 *  他下面的必须先被绘制,这就要求,先找到容器层级中的重祖先容器,然后把重祖先容器中的组件由最里面(下面)向最外面开始绘制
 *  2.overlapping components组件重叠:
 *  	当一个轻组件被绘制的时候,很可能有别的轻组件和他发生重叠,这就意味着,当一个轻组件被绘制的时候,其他组件只要和这个组件发生重叠,
 *  则其他组件和他重叠的部分也必须被绘制出来,这就会要求系统遍历容器层次结构,检查每个组件和他重叠部分
 *  
 *  为了提高不透明组件性能,swing对javax,swing.JCompnent增加了一个可以读写的属性opaque(不透明)
 *  public boolean isOpaque()     
 *  public void setOpaque(boolean o)
 *  true:表示组件同意绘制他所包含的所有区域
 *  false:组件不保证他的所有区域都会绘制
 *  
 *  这个opaque属性允许swing的绘制机制区检查,一个组件在绘制的时候,是否需要绘制他下面的组件
 *  opaque默认是由look and feel ui object来设置的,对于大多数的组件默认值是true
 *  
 *  一个常见的错误是,组件的opaque属性默认设置为true,但是又没有完全绘制他们的所有边界内的东西,这样就造成了相关的未被渲染区域的屏幕垃圾
 *  当一个组件被设计好之后,应该给opaque设置合适的值,确保明智的使用,因为这会浪费绘制时间
 *  
 *  这个opaque经常被误解,有时候表示:使组件的背景透明,然后,这并不是swing的opacity的严格解释
 *  例如:按钮,可能opaque属性位false,此时可能是为了让组件不要显示一个矩形的形状,但是实际上，没有他还是占用着一个矩形的空间，只是背景透明，你看不到
 * 	正如前面所说的，不透明的特性主要是和重绘repaint系统有关
 *  如果使用该属性设置组件的不透明度，最好通过一个属性记录一下
 *  对于某些组件来说，定义附加属性来控制如何应用透明度是比较好的做法
 *  例如:javax.swing.AbstractButton提供了ContentAreaFilled属性就是为了这个目的
 *  另一个需要注意的问题是,透明度和swing组件的边框属性有关
 *  通过border对象在组件上呈现的区域，仍然是这个组件的一部分
 *  这就意味着，如果一个组件是不透明的，他有责任负责填充边框所占用的区域部分
 *  如果你想让你一个组件下面的组件，通过他的透明边框区域部分显示出来 
 *  组件边框需要支持透明，通过border.isBorderOpaque()返回false,
 *  组件本身也需要设置为透明
 *  
 *  优化绘制:
 *  组件的重叠问题是很棘手的,即使他没有和当前同层中的组件重叠，也可能和其他层中的组件发生重叠 
 *  这种情况下，重绘这个有着很复杂层级关系的组件，会需要大量的层级树遍历组件，获得他们的重叠情况，确保正确的绘制
 *  为了减少不必要的遍历，swing增加了一个只读属性javax.Swing.JComponent.isOptimizedDrawingEnable
 *   public boolean isOptimizedDrawingEnabled()  
 *   true,表示没有直接的孩子组件和他重叠
 *   false,表示该组件不能保证没有直接的孩子组件和他重叠                          
 *  通过检查这个属性,swing可以快速的缩短检查重叠组件的重绘时间
 *  因为这个属性是只读，如果想改变返回值，只能覆盖，返回你所需要的值
 *  所有的标准swing组件对于这个属性都返回true,处理JLayeredPane,JDesktopPane,JViewPort
 *  
 *  
 *  
 *  The Paint Methods
 *  swing的绘制机制和awt相同,只不过swing把paint()方法拆分为3部分:
 *      protected void paintComponent(Graphics g)     
 *      protected void paintBorder(Graphics g)     
 *      protected void paintChildren(Graphics g)
 *  并按照顺序执行3个方法 
 *  	JComponent.paint()源码片段:
 *  	//先执行paintComponent()和paintBorder()
 *      if (!rectangleIsObscured(clipX,clipY,clipW,clipH)) {
            if (!printing) {
                paintComponent(co);
                paintBorder(co);
            }
            else {
                printComponent(co);
                printBorder(co);
            }
        }
        //再执行paintChildren()
        if (!printing) {
            paintChildren(co);
        }
        else {
            printChildren(co);
        }
 *  
 *  swing程序应该覆盖paintComponent()方法,而不是覆盖paint()方法,虽然可以覆盖paint()方法,但是你必须知道自己在做什么
 *  这样程序可以只覆盖他们需要处理的部分,例如:这个可以解决awt中不调用super.paint(),容器重的轻量组件不被绘制的问题
 *  
 *  
 *  Painting and the UI Delegate
 *  大多数标准的swing组件都有他们自己的look and feel实现，通过各自的look and feel对象（通常被称为ui delegates)来实现swing的可插拔式外观特性
 *  这意味着，几乎全部swing组件都是通过ui delegatesl来进行绘制
 *  查看paintComponent()源码:
 *   protected void paintComponent(Graphics g) {
        if (ui != null) {
            Graphics scratchGraphics = (g == null) ? null : g.create();
            try {
                ui.update(scratchGraphics, this);
            }
            finally {
                scratchGraphics.dispose();
            }
        }
    }
 *  
 *   public void update(Graphics g, JComponent c) {
        if (c.isOpaque()) {
            g.setColor(c.getBackground());
            g.fillRect(0, 0, c.getWidth(),c.getHeight());
        }
        paint(g, c);
    }
 *  ui delegates的绘制是按照如下步骤来进行的:
 *  paint()方法依次调用paintComponent(),paintBorder(),paintChildren()
 *  
 * 	paint()方法调用paintComponent()
 *  如果ui属性不为null,则paintComponent()执行ui.update();
 *  如果背景不透明,则ui.update()会使用背景颜色填充，然后调用ui.paint()方法绘制组件的内容
 *  这就意味着,如果一个swing组件的子类有ui delegate，当覆盖paintComponent()方法的时候，需要调用super.paintComponent()
 *  例如:
 *   public class MyPanel extends JPanel {         
 *   	protected void paintComponent(Graphics g) {             
 *   	// Let UI delegate paint first             
 *   	// (including background filling, if I'm opaque)                                        
 *   	super.paintComponent(g);              
 *   	// paint my contents next....         
 *   	}
 *   }                                                               
 *   如果因为某些原因(例如:你自己绘制的东西完全取代了组件的可视区域)，不想让ui代理进行绘制,可以不用调用super.paintComponent()
 * 	但是如果opaque设置true，不透明，则你自己需要填充背景颜色(可以减少组件重叠检查时间?)
 * 
 * 
 * 	paint processing:
 * 	swing在处理repait时和awt稍微有点不同,虽然最后都是导致paint()方法被执行
 * swing中绘制可以按照如下两种方式:
 * 1.绘制请求来自于第一个重组件祖先(JFrame,JDialog,JWindow,Japplet),
 * event dispatching thread执行重祖先组件的paint(),Container.paint()又递归调用他后代轻组件的paint()
 * 当第一个后代轻组件开始绘制的时候，JComponent.paint()执行如下:
 * JComponent.paint()源码:
 * public void paint(Graphics g) {
        boolean shouldClearPaintFlags = false;

        if ((getWidth() <= 0) || (getHeight() <= 0)) {
            return;
        }

        Graphics componentGraphics = getComponentGraphics(g);
        Graphics co = componentGraphics.create();
        try {
            RepaintManager repaintManager = RepaintManager.currentManager(this);
            Rectangle clipRect = co.getClipBounds();
            int clipX;
            int clipY;
            int clipW;
            int clipH;
            if (clipRect == null) {
                clipX = clipY = 0;
                clipW = getWidth();
                clipH = getHeight();
            }
            else {
                clipX = clipRect.x;
                clipY = clipRect.y;
                clipW = clipRect.width;
                clipH = clipRect.height;
            }

            if(clipW > getWidth()) {
                clipW = getWidth();
            }
            if(clipH > getHeight()) {
                clipH = getHeight();
            }

            if(getParent() != null && !(getParent() instanceof JComponent)) {
                adjustPaintFlags();
                shouldClearPaintFlags = true;
            }

            int bw,bh;
            boolean printing = getFlag(IS_PRINTING);
            if (!printing && repaintManager.isDoubleBufferingEnabled() &&
                !getFlag(ANCESTOR_USING_BUFFER) && isDoubleBuffered() &&
                (getFlag(IS_REPAINTING) || repaintManager.isPainting()))
            {
                repaintManager.beginPaint();
                try {
                    repaintManager.paint(this, this, co, clipX, clipY, clipW,
                                         clipH);
                } finally {
                    repaintManager.endPaint();
                }
            }
            else {
                // Will ocassionaly happen in 1.2, especially when printing.
                if (clipRect == null) {
                    co.setClip(clipX, clipY, clipW, clipH);
                }

                if (!rectangleIsObscured(clipX,clipY,clipW,clipH)) {
                    if (!printing) {
                        paintComponent(co);
                        paintBorder(co);
                    }
                    else {
                        printComponent(co);
                        printBorder(co);
                    }
                }
                if (!printing) {
                    paintChildren(co);
                }
                else {
                    printChildren(co);
                }
            }
        } finally {
            co.dispose();
            if(shouldClearPaintFlags) {
                setFlag(ANCESTOR_USING_BUFFER,false);
                setFlag(IS_PAINTING_TILE,false);
                setFlag(IS_PRINTING,false);
                setFlag(IS_PRINTING_ALL,false);
            }
        }
    }
 * if the component's doubleBuffered property is true and double-buffering is enabled on the component's RepaintManager, will convert the Graphics object to an appropriate offscreen graphics.
invokes paintComponent() (passing in offscreen graphics if doubled-buffered)
invokes paintBorder() (passing in offscreen graphics if doubled-buffered)
invokes paintChildren() (passing in offscreen graphics if doubled-buffered), which uses the clip and the opaque and optimizedDrawingEnabled properties to determine exactly which descendents to recursively invoke paint() on.
if the component's doubleBuffered property is true and double-buffering is enabled on the component's RepaintManager, copies the offscreen image to the component using the original on-screen Graphics object. 
Note: the JComponent.paint() steps #1 and #5 are skipped in the recursive calls to paint() (from paintChildren(), described in step#4) because all the lightweight components within a Swing window hierarchy will share the same offscreen image for double-buffering.
 * 
 * 
 * 
 * 2.绘制请求来自一个扩展javax,swing.JComponent对象的repaint()方法
 * JComponent.repaint()在component的RepaintManager上注册一个异步绘制请求,会使用invokeLater()把一个Runnable对象放置到event dispatching thread上
 * runnable对象在edt上执行，引起组件的RepaintManager执行组件的paintImmeditely()
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * swing绘制总结:
 * swing组件的paint()方法，不论是system-trigger，还是app-trigger触发的绘制请求,都会被执行
 * swing组件的 update()方法，对于swing组件永远也不会执行
 * 
 * 可以通过调用repaint()方法来触发一个paint(),但是不能直接调用paint()
 * 对于复杂的组件绘制输出，应该调用带参数的repaint()方法,用来指明哪些地方需要更新,如果不带参数，会导致整个组件被重绘
 * swing的paint()方法被划分为3个部分
 * paintComponent()
 * paintBorder()
 * paintChildren()
 * 我们应该覆盖paintComponent()方法而不是paint()方法
 * swing提供了2个属性来提高绘制性能:
 * opaque:是否需要绘制多有的bits
 * optimizedDrawingEnabled:组件的孩子中是否有重叠
 * 
 * 如果opaque为true,说明不透明,此时表示绘制包含的所有的位(包括在paintComponent()方法中清理组件背景),否则会造成屏幕垃圾
 * 如果opaque为false,或则optimizedDrawingEnabled属性为false,组件每次绘制会造成更多的处理,因此小心使用不透明特性和组件重叠特性检查
 * 
 * 如果swing组件有ui delegates(包括JPanel),应该调用super.paintComponent()在他们自己的paintComponent()方法中
 * 因为ui delegate有责任清除不透明组件的背景
 * 
 * swing内置支持双缓冲,通过JComponent.doubleBuffered属性,而且所有的Swing组件默认值都是true，需要注意的是，如果一个swing容器设置这个值为true
 * 则这个容器里面的所有子孙轻组件该属性都会改变为true
 * 
 * 当渲染复杂的组件，应该智能剪切绘制区域，缩小绘制区和剪切区域相交的区域
 * 
 * 
 * 
 * 
 * 
 * 
 * @author zuoyang
 *
 */
public class Paint_Swing extends JFrame{
	public static void main(String[] args) throws InterruptedException{
		Paint_Swing t = new Paint_Swing();
		t.setSize(500, 500);
		t.setLayout(null);
		//t.setUndecorated(true);
		t.getContentPane().setBackground(Color.red);
		
		MyPanel p = new MyPanel();
		p.setBounds(0,0,300,300);
		t.add(p);
		t.setVisible(true);
	}
}


class MyPanel extends JPanel{
	public MyPanel() {
		this.setBackground(Color.GREEN);
		this.setOpaque(false);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
		Image image = null;
		image = Toolkit.getDefaultToolkit().getImage("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/paint/test.jpg");
		g.drawImage(image, 0, 0, this);
		System.out.println("..");
		super.paintComponent(g);
	}

}












