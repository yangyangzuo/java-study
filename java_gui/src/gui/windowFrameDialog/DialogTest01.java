package gui.windowFrameDialog;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Rectangle;
/**
 * 从进化的观点看，dialog位于window和frame之间,对话框不支持菜单条和图表化操作，但是对话框支持模态
 * @author gudandan
 *
 */
public class DialogTest01 {
	public static void main(String[] args) {
		Dialog dialog = new Dialog(new Frame());
		dialog.setBounds(new Rectangle(500, 500));
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
	}
}
