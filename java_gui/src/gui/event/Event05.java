
package gui.event;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.Frame;
/**
 * 传播/继承事件模型:
 * 
 * jdk1.0时,awt没有任何面向对象手段,必须通过if语句的嵌套来侦测所谓的事件目标对象
 * 
 * 事件的处理，本质上是通过handleEvent(Event event)方法
 * 
 * 可以覆盖handleEvent()方法来处理事件,也可以覆盖具体的事件方法来处理(详细可以查看handleEvent()方法的源码)
 * 
 * 注意:handleEvent()方法是jdk1.0,awt刚出来的时候，事件模型的处理方法,到jdk1.1的时候，这些方法就被取消了
 * 
 * @author zuoyang
 *
 */

public class Event05 extends Frame{
	
	private static Button button = new Button("pressMe");
	private static Checkbox checkBox = new Checkbox("check");
	public static void main(String[]args){
		Event05 f = new Event05();
		f.setLayout(new FlowLayout());
		f.add(button);
		f.add(checkBox);
		f.setSize(200,200);
		f.setVisible(true);
	}

	/**
	 * 动作事件(action event),指下列组件中激活的事件:
	 * Button,Checkbox,TextField,Choice,MenuItem,List
	 * 只有这些组件产生的事件传播时才引起对action()方法的调用,其他组件不会引起action()方法的调用
	 * 
	 * 如果event.target是Button对象,则what表示button上的字符串
	 * 如果event.target是Checkbox对象,则what表示复选框选中为true,不选中为false
	 * 如果event.target是TextField对象,则what表示单行文本域中的字符串
	 * 如果event.target是Choice对象,则what表示菜单文本内容的字符串
	 * 如果event.target是MenuItem对象,则what表示菜被选择菜单项的字符串
	 * 如果event.target是List对象,则what表示被双击的项目的字符串
	 * 这些可以打印输出查看
	 * 
	 */
	@Override
	public boolean action(Event evt, Object what) {
		//System.out.println(what);
		if(evt.target instanceof Button){
			System.out.println("button action");
		}else if(evt.target instanceof Checkbox){
			System.out.println("Checkbox action");
		}
		
		//这里有一点是需要注意的，不要使用what的值来判断是哪个按钮进行的操作,因为如果按钮上的文字改变了，你需要同时修改action方法,工作量大，而且国际化也难控制　
		//最好的方法应该使用对象比较,Event01.java中是正确的方式
		//正确方式:
		if(evt.target == button)
			System.out.println("evt.target is button");
		else if(evt.target == checkBox)
			System.out.println("evt.target is checkBox");
		
		//错误的方式:(当按钮上的文字改变的话,当前action方法中的判断必须要同步改变)
		if(evt.target instanceof Button && "pressMe".equals(what)){
			System.out.println("evt.target is button");
		}
		
		//基于传播/继承事件模型的缺点:
		//1.如果把事件处理写在一个总的外层框架的HandleEvent()或者action()方法中，需要明确判断是哪个组件,这个组件在判断的时候，可能需要把组件定义位全局对象，用来做组件精确判定
		//2.如果把处理方法写在一个具体的组件里面，比如，覆盖一个mouseup()方法，则你自己需要继承这个组件，才可以覆盖这个特定的方法，例如你需要把一个按钮的处理方法写在这个按钮类里面，你需要继承这个按钮，然后在覆盖对象的方法
		return super.action(evt, what);
	}
	
}
