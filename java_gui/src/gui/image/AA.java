package gui.image;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFrame;

public class AA{
    public static void main(String[] args) throws Exception{
        JFrame f=new JFrame();
        MyCanvas mc=new MyCanvas();
//        Image image=Toolkit.getDefaultToolkit().getImage("http://a.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fe69291a015e6034a85edf72f9.jpg");
        Image image = Toolkit.getDefaultToolkit().getImage(new URL("http://a.hiphotos.baidu.com/image/pic/item/f11f3a292df5e0fe69291a015e6034a85edf72f9.jpg"));
        mc.setImage(image);
        f.add(mc);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(400,550);
        f.setVisible(true);
    }
}

class MyCanvas extends Canvas {
    private Image in;
    private int image_width;
    private int image_height;
    
    public void setImage(Image in){
        this.in=in;
    }
    
    public void paint(Graphics g){
        g.drawImage(in,0,0,this.getWidth(),this.getHeight(),this);
      
    }

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int w, int h) {
		  try {
				Thread.currentThread().sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// TODO Auto-generated method stub
		  System.out.println("--");
		return super.imageUpdate(img, infoflags, x, y, w, h);
	}    
    
}