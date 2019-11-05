package gui.setIconImage;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.apple.eawt.Application;
/**
 * windows系统下:
 * Window.setIconImage()方法，可以设置应用程序标题栏左上角图标，任务栏上的图标,任务管理器中的图标
 * 
 * linux系统下:
 * 
 * 
 * mac系统下:
 * 应用程序的左上角没有图标可以设置
 * dock上的图标设置，通过Application.getApplication().setDockIconImage(image);设置
 * 
 * @author gudandan
 *
 */
public class SetIconImageTest1 extends JFrame {
    public static void main(String []args){
        SwingUtilities.invokeLater(new Runnable(){
                @Override public void run(){
                    JFrame frame = new JFrame("Example");
                    frame.setSize(500, 500);
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    Image image = null;
                    try {
                    	//windows下的设置方式
						 image = ImageIO.read(new File("/Users/gudandan/Desktop/jeeworkspace/nevermore/images/titleImage.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    //mac系统设置dock上的图标
                    //参考文档:
                    //https://developer.apple.com/library/content/documentation/Java/Conceptual/Java14Development/07-NativePlatformIntegration/NativePlatformIntegration.html
                    //https://developer.apple.com/library/content/documentation/Java/Conceptual/Java14Development/03-JavaDeployment/JavaDeployment.html
                    //http://stackoverflow.com/questions/11253772/setting-the-default-application-icon-image-in-java-swing-on-os-x
                    //http://stackoverflow.com/questions/23378119/setimageicon-doesnt-set-jframe-icon-on-mac-swing-window
                    Application.getApplication().setDockIconImage(image);
                    //windows下设置任务栏图标
                    frame.setIconImage(image);
                    frame.setVisible(true);
                    frame.validate();
                }
            });
    }
}