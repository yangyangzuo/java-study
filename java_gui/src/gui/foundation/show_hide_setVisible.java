package gui.foundation;

import java.awt.Frame;

public class show_hide_setVisible {

	public static void main(String[] args) throws InterruptedException {
		Frame f = new Frame();
		f.setSize(500, 500);
		// 组件的显示与隐藏
		// deprecated：
		// f.show();
		// f.show(true);
		// f.hide();
		f.setVisible(true);

	}
}
