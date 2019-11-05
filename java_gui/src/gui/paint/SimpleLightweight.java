package gui.paint;

import java.applet.Applet;
import java.awt.*;

public class SimpleLightweight extends Applet {
	private Image dining, paper;

	public void init() {
		dining = getImage(getCodeBase(),"/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/paint/Dining.gif"); 
		paper  = getImage(getCodeBase(),"/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/paint/paper.gif");
		add(new Lightweight(dining));
	}
	public void paint(Graphics g) {
		super.paint(g);
		Util.wallPaper(this, g, paper);
		
	}
}
class Lightweight extends Container {
	private Image   image;

	public Lightweight(Image image) {
		this.image = image;
		Util.waitForImage(this, image);
	}
	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, this);
	}
	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(this),
		                     image.getHeight(this));
	}
}
class Util {
    public static void waitForImage(Component component, 
                                    Image image) {
        MediaTracker tracker = new MediaTracker(component);
        try {
            tracker.addImage(image, 0);
            tracker.waitForID(0);
        }
        catch(InterruptedException e) { e.printStackTrace(); }
    }
    public static void wallPaper(Component component, 
                            Graphics  g, 
                            Image     image) {
        Dimension compsize = component.getSize();
        Util.waitForImage(component, image);

        int patchW = image.getWidth(component);
        int patchH = image.getHeight(component);

        for(int r=0; r < compsize.width; r += patchW) {
            for(int c=0; c < compsize.height; c += patchH) {
				g.drawImage(image, r, c, component);
			}
        }
    }
}