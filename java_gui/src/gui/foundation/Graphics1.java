package gui.foundation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
/**
 * graphics对象的引用:
 * 	1.覆盖paint(),paintAll(),print(),printAll(),update(),paintComponents(),等
 * 	2.getGraphics(),create()
 * @author zuoyang
 *
 */
public class Graphics1 extends Frame{
	public static void main(String[] args) throws InterruptedException{
		Graphics1 t = new Graphics1();
		t.setSize(600,600);
		Graphics g = t.getGraphics();
//		if(g!=null)
//		System.out.println("0:" + g.getColor());
		t.setVisible(true);
		g = t.getGraphics();
		System.out.println("1:" + g.getColor());
	}
	
	@Override
	public void paint(Graphics g) {
		System.out.println("2:" + g.getColor());
		g.setColor(Color.red);
		System.out.println("3:" + g.getColor());
		Dimension d = this.getSize();
		System.out.println("4:" + this.getGraphics().getColor());
		g.drawRect(0, 0, (int)d.getWidth()-1,(int)d.getHeight()-1);
	}
	
//	Component.paint(Graphics g)
//	public void paint(Graphics g) {
//    }
//	Container.paint(Graphics g)
//    public void paint(Graphics g) {
//        if (isShowing()) {
//            synchronized (getObjectLock()) {
//                if (printing) {
//                    if (printingThreads.contains(Thread.currentThread())) {
//                        return;
//                    }
//                }
//            }
//            // The container is showing on screen and
//            // this paint() is not called from print().
//            // Paint self and forward the paint to lightweight subcomponents.
//            // super.paint(); -- Don't bother, since it's a NOP.
//            GraphicsCallback.PaintCallback.getInstance().
//                runComponents(getComponentsSync(), g, GraphicsCallback.LIGHTWEIGHTS);
//        }
//    }
//	Window.paint(Graphics g)
//	public void paint(Graphics g) {
//	    if (!isOpaque()) {
//	        Graphics gg = g.create();
//	        try {
//	            if (gg instanceof Graphics2D) {
//	                gg.setColor(getBackground());
//	                ((Graphics2D)gg).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC));
//	                gg.fillRect(0, 0, getWidth(), getHeight());
//	            }
//	        } finally {
//	            gg.dispose();
//	        }
//	    }
//	    super.paint(g);
//	}
	
}
