package gui.components;

import java.awt.Frame;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 文本区，文本域显示可选择的文本并允许用户编辑文本，
 * 
 * 文本区是一个多行文本域(TextArea)
 * 
 * (TtextField)文本域是单行的，用户可以设置他们的前景色和背景色，但是不能改变基本显示特性
 * 
 * TextArea,TextField(-->父类TextComponent-->父类Component)
 * 
 * @author Administrator
 * 
 */
public class Gui_04_TextFieldTest
{

	public static void main(String[] args)
	{
		Frame f = new Frame();
		TextField textField = new TextField();
		f.add(textField);
		// 这里输入的是什么显示的就是什么，如果你想模拟密码输入框f.setEchoChar('*');
		// 设置回显字符
		// public void setEchoChar(char c)设置此文本字段的回显字符。
		// 回显字符对于不应将用户输入回显到屏幕上的文本字段很有用，例如输入密码的文本字段。
		// 设置 echoChar = 0 允许将用户输入再次回显到屏幕。即输什么显示什么
		System.out.println("默认回显字符:" + (int) textField.getEchoChar());

		// 手动设置模拟回显字符,这样就可以模拟密码输入框了
		textField.setEchoChar('*');

		System.out.println("默认回显字符:" + (int) textField.getEchoChar() + "--"
				+ textField.getEchoChar());

		// 文本框不可以编辑
		textField.setEditable(false);
		//组件获得焦点
		textField.requestFocus();
		
		// actionListener()方法对于textField对象组件是在输入域敲回车键进行监听
		textField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// 得到最初发生 Event 的对象,从父类EventObject中继承的方法
				TextField t = (TextField) e.getSource();
				// 得到输入的文本内容
				String content = t.getText();
				System.out.println(content);
				// 清除内容
				t.setText("");
			}
		});
		f.pack();
		f.setVisible(true);
	}

}
