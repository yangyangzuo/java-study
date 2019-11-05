package gui.opacity;

import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSPARENT;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import java.awt.Color;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Opacity11 extends JFrame {
	public Opacity11() {
		super("ShapedWindow");
		setLayout(new GridBagLayout());

		//设置特定形状
		//可以看出来锯齿很严重
		// It is best practice to set the window's shape in
		// the componentResized method. Then, if the window
		// changes size, the shape will be correctly recalculated.
		addComponentListener(new ComponentAdapter() {
			// Give the window an elliptical shape.
			// If the window is resized, the shape is recalculated here.
			@Override
			public void componentResized(ComponentEvent e) {
				setShape(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
			}
		});

		setUndecorated(true);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(new JButton("I am a Button"));
	}

	public static void main(String[] args) {
		// Determine what the GraphicsDevice can support.
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		final boolean isTranslucencySupported = gd.isWindowTranslucencySupported(TRANSLUCENT);

		// If shaped windows aren't supported, exit.
		if (!gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT)) {
			System.err.println("Shaped windows are not supported");
			System.exit(0);
		}

		// If translucent windows aren't supported,
		// create an opaque window.
		if (!isTranslucencySupported) {
			System.out.println("Translucency is not supported, creating an opaque window");
		}

		// Create the GUI on the event-dispatching thread
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Opacity11 sw = new Opacity11();

				//windows窗体和里面的组件都透明
				// Set the window to 70% translucency, if supported.
				//if (isTranslucencySupported) {
				//	sw.setOpacity(1f);
				//}
				
				//windows窗体背景透明
				sw.setBackground(new Color(1,0,0,0.2f));

				// Display the window.
				sw.setVisible(true);
			}
		});
	}
}