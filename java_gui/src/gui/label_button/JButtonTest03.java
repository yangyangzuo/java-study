package gui.label_button;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JButtonTest03 extends JApplet {
	JButton button = new JButton("button");

	public JButtonTest03() {
		Container contentPane = getContentPane();

		button.setRolloverIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/punch.gif"));
		button.setIcon(new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/label_button/open_hand.gif"));

		contentPane.setLayout(new FlowLayout());
		contentPane.add(button);

		//按钮激活时，会发生动作事件	
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("action!");
			}
		});
		
		//按钮关联属性改变时，会发生属性改变事件
		button.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				//按钮上点击并释放鼠标
				System.out.println(getButtonState());
			}
		});
		
	}
	private String getButtonState() {
		//按钮的状态和属性存储在一个ButtonModel对象上
		ButtonModel model = button.getModel();
		String state = "Button State: "; 

		//对于单选框和付款框
		state += model.isSelected() ? "selected" : "deselected";
		
		//鼠标在按钮上摁下(只要摁下就行，即使摁下了，鼠标离开了按钮，该函数还返回true)
		state += model.isPressed() ? ", pressed" : 
									 ", not pressed";
		
		//鼠标被摁下，而且鼠标还在按钮上(鼠标被摁下，如果鼠标离开了按钮，则该函数返回false)
		state += model.isArmed() ? ", armed" : ", disarmed";
		
		
		//鼠标是否移动到该按钮上
		state += model.isRollover() ? ", rollover" : 
									  ", not rollover";

		return state;
	}
}
