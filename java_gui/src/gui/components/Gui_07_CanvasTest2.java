package gui.components;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;

public class Gui_07_CanvasTest2 extends Canvas
{
	Container pappy;
	Image image;
	boolean trueSizeKonwn = false;
	Dimension miniSize;
	int w, h;

	public Gui_07_CanvasTest2(Image image, Container parent, int initialWidth,
			int initialHeight)
	{
		if (image == null)
		{
			System.err.println("canvas got invlid image object");
			return;
		}
		this.image = image;
		pappy = parent;
		w = initialWidth;
		h = initialHeight;
		miniSize = new Dimension(w, h);
	}

	public Dimension preferredSize()
	{
		return minimumSize();
	}

	public synchronized Dimension minimumSize()
	{
		return miniSize;
	}
	
	@Override
	public void paint(Graphics g)
	{
		if(image !=null)
		{
			if(!trueSizeKonwn)
			{
				int imageWidth = image.getWidth(this);
				int imageHeight = image.getHeight(this);
				if(imageWidth>0&&imageHeight>0)
				{
					trueSizeKonwn = true;
					w = imageWidth;
					h = imageHeight;
					miniSize = new Dimension(w,h);
					resize(w,h);
					pappy.layout();
					pappy.repaint();
				}
			}
			g.drawRect(0, 0, w-1, h-1);
			g.drawImage(image,0,0,this);
		}
	}

	public static void main(String[] args)
	{
		Frame f = new Frame();
		
		
		f.setSize(500,500);
		f.setVisible(true);
	}
}





















