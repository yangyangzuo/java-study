package gui.label_button;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

public class JButtonTest01 {
	public static void main(String[] args) {
		//注意:按钮中的内容，及其内容中的文本和图片对齐方式的设置和JLabel类似，可以参考JLableTest**.java
		JFrame f = new JFrame();
		f.setSize(500, 500);
		f.setLocationRelativeTo(null);
		f.setLayout(null);
		
		//JButton继承了AbstractButton,其中AbstarctButton中维护了7个状态下的图标
		
		
		JButton button = new JButton("hello");
		button.setBounds(100, 100, 150, 50);
		//1.按钮启用且未被选中时的图标
		button.setIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/open_hand.gif"));
		//2.鼠标摁下时按钮显示的图标
		button.setPressedIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/pointing.gif"));
		//3.按钮被禁用时的图标
		button.setDisabledIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/penguin.gif"));
		//button.setEnabled(false);
		//4.按钮选取时显示的图标(JRadioButton,JCheckBox,JMenuBar)
		button.setSelectedIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/slate.gif"));
		//5.鼠标移动到按钮上时显示的图标
		button.setRolloverIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/punch.gif"));
		//6.鼠标移动到一个禁用的，且被选取的图标(JRadioButton,JCheckBox,JMenuBar)
		button.setDisabledSelectedIcon(null);//用不到
		//7.鼠标移动到已经被选中的按钮的图标(JRadioButton,JCheckBox,JMenuBar)
		button.setRolloverSelectedIcon(null);//用不到
		f.add(button);
		
		
		//测试选中时的图标切换,原有的一个小点图标被替换了
		JRadioButton jrb = new JRadioButton("单选框");
		jrb.setIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/punch.gif"));
		jrb.setSelectedIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/slate.gif"));
		jrb.setBounds(100, 300, 150,150);
		f.add(jrb);
		JCheckBox jcb = new JCheckBox("复选框");
		jcb.setIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/punch.gif"));
		jcb.setSelectedIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/slate.gif"));
		jcb.setBounds(300, 300, 150,150);
		f.add(jcb);
		
		
		
		
		
		
		//设置一个背景透明的按钮
		JButton button2 = new JButton();
		button2.setBounds(100, 200, 48, 48);//宽度和高度定的要和图片大小相等
		//给按钮上设置一个图像
		button2.setIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/open_hand.gif"));
		//鼠标移动到按钮上，可以设置一个图像
		button2.setRolloverIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/punch.gif"));
		button2.setContentAreaFilled(false);//该方法可以支持按钮透明
		button2.setBorderPainted(false);//该方法指明不要绘制边框
		f.add(button2);
		
		f.setVisible(true);
	}
}
