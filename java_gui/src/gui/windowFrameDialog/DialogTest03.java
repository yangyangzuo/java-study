package gui.windowFrameDialog;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class DialogTest03 extends Applet {
	Button launchButton = new Button("Show Dialog ...");
	Dialog dialog;
	Frame myFrame;

	public void init() {
		add(launchButton);

		launchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Point scrnLoc = myFrame.getLocationOnScreen();
				Dimension dialogSize = dialog.getSize();

				dialog.setLocation(
					scrnLoc.x - dialogSize.width - 2,
				    scrnLoc.y);

				showStatus(null);
				dialog.show();
				showStatus("Dialog shown");//该句代码被阻塞，当模态对话框关闭，当前代码才会执行
			}
		});
	}
	public void start() {
	 	Button doneButton = new Button("Done");

		myFrame = getFrame(DialogTest03.this);
		dialog  = new Dialog(myFrame, "Simple Dialog", true);//创建一个模态对话框

		dialog.add(doneButton);
		dialog.setBounds(new Rectangle(200, 200));
		dialog.setResizable(false);

		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				dialog.dispose();
				showStatus(null);
			}
		});
		dialog.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) {
				System.out.println("Window Closing");
				dialog.dispose();
			}
			public void windowClosed(WindowEvent event) {
				System.out.println("Window Closed");
			}
		});
	}
    static Frame getFrame(Component c) {
		Frame frame = null;

        while((c = c.getParent()) != null) {
            if(c instanceof Frame)
                frame = (Frame)c;
        }
        return frame;
    }
}
