package gui.components;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

public class Gui_17_Event
{
	/**
	 * 常见事件模型：
	 * 
	 * Component类中的事件：
	 * 
	 * public boolean action(Event evt, Object what)已过时。 从 JDK version 1.1
	 * 开始，应该将此组件作为组件（激发动作事件）上的 ActionListener 来注册。
	 * 当典型事件针对组件发生时调用，例如：按下一个按钮或者下拉列表项目被选中
	 * 
	 * 
	 * keyDown(Event evt,int key)已过时。 从 JDK version 1.1 开始，由
	 * processKeyEvent(KeyEvent) 取代。
	 * 按牛被按下，组件拥有焦点时调用,第二个子变量是按下的键并且是冗余的，是从evt.key处复制来的
	 * 
	 * keyup(Event evt,int key)已过时。 从 JDK version 1.1 开始，由
	 * processKeyEvent(KeyEvent) 取代。 按键被释放，组件拥有焦点时调用
	 * 
	 * 等等事件
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * EventObject： 所有事件状态对象都将从其派生的根类。
	 * 
	 * java.awt.AWTEvent: 所有 AWT 事件的根事件类。此类及其子类取代了原来的 java.awt.Event 类
	 * 
	 * 在EventObject类中，有一个getSource()方法，用来返回出发事件的对象
	 * ComponentEvent类提供有一个getComponent()方法，用来返回触发事件的组件，这两个方法都可以实现返回一个事件源对象
	 * 
	 * 
	 * awt事件类型分为：低级事件和高级事件
	 * 
	 * 
	 * 低级事件：是基于组件和容器的事件，例如：鼠标进入组件，单击组件等
	 * 
	 * ComponentEvent组件事件，发生于组件大小改变，移动，显示，隐藏时
	 * 
	 * CotianerEvent容器事件,发生于添加/删除一个组件时
	 * 
	 * MouseEvent鼠标事件，发生于添加键盘上的一个键被按下或者释放时
	 * 
	 * WindowEvent
	 * 
	 * KeyEvent
	 * 
	 * FocusEvent
	 * 
	 * 高级事件：基于语义的事件，可以不和特定的动作关联，而是依赖于出发此事件的类，
	 * 比如在TextField中按enter键会触发ActionEvent事件
	 * 
	 * ActionEvent动作事件，发生于对应按钮单击，菜单选择，列表框选择，在文本框中按回车键时
	 * 
	 * AdjustmentEvent调整事件，发生于用户在滚动条上移动滑块时
	 * 
	 * ItemEvent项目事件，发生于用户从一组选择框或者列表框选者时
	 * 
	 * TextEvent文本事件，发生于文本框或者文本域中的内容发生改变时
	 * 
	 * 
	 * 
	 */
	public static void main(String[] args)
	{
		Frame f = new Frame();
		f.setSize(200, 200);

		Button button3 = new Button("south Button");
		f.add(button3, BorderLayout.SOUTH);
		// component类上的 boolean action(Event evt, Object what)已过时。
		// 从 JDK version 1.1 开始，应该将此组件作为组件（激发动作事件）上的 ActionListener 来注册。
		// Event 类已废弃，只可用于向后兼容。它已经由 AWTEvent 类及其子类所取代
		// button3.action(evt, what)

		f.setVisible(true);
	}

}
