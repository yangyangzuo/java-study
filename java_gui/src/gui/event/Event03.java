package gui.event;

import java.awt.Button;
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
 * @author zuoyang
 *
 */
public class Event03 extends Frame{
	public static Button030 b1 = new Button030("Button030");
	public static Button031 b2 = new Button031("Button031");
	public static void main(String[]args){
		Event03 f = new Event03();
		f.setLayout(new FlowLayout());
		f.add(b1);
		f.add(b2);
		f.setSize(200,200);
		f.setVisible(true);
		//事件的传播机制:
		//事件是由产生的组件传播到最外层容器,但是，如果这个传播链上任意一组件的handelEvent()方法返回true,则该组件就不会把事件再向上传播
		//这个例子可以看出来
		//当在Button030上操作时，Button030.HandelEvent()方法返回true,此时，表示事件已经可以自己完全处理了，不用在向上传播了,所以Event03.HandelEvent()方法没有执行
		//当在Button031上操作时，Button031.HandelEvent()方法返回false,此时，表示事件不归自己处理或者没有处理完全，需要向上传播了,所以父组件Event03.HandelEvent()方法执行
		//如果组件的handleEvent()方法返回true,表示不再把事件向父类组件传播
		//如果返回false,表示向上传播给父类组件
		//如果调用super.handleEvent()方法，则根据父类的handleEvent()方法决定是否需要传播
		
		//基于传播事件模型，你需要覆盖事件处理方法中返回的boolean值决定事件是否应被传播给他的容器
		//handelEent()和相关的辅助方法(如mouseDown(),mouseUp()等)返回一个boolean值,表示事件是否应被传播给组件的容器
		//返回true,表示事件被完全处理，不需要传播
		//返回false,表示事情没有被完全处理，需要传播
		
	}

	/**
	 * awt有一个事件处理器,事件处理器负责接受发生的事件并对事件做出处理
	 * 
	 * 注意:handleEvent(Event event)方法是自动被调用的,该方法通过事件的id来判断事件类型,查看源码可以看出来
	 * 在处理对应的事件的时候，可以覆盖handleEvent()方法，也可以覆盖对应的事件方法,例如:mouseEnter()方法
	 * 从源码也可以看出来这些对应的操作方法里面，什么也没有做，他们存在就是为了被覆盖，以便赋予他们特定的功能
	 */
	@Override
	public boolean handleEvent(Event evt) {
		System.out.println("Event03.handleevent");
		return super.handleEvent(evt);
	}
	
}

class Button030 extends Button{
	public Button030(String name){
		super(name);
	}
	@Override
	public boolean handleEvent(Event evt) {
		System.out.println("Button030.handleevent");
		return true;
	}
}

class Button031 extends Button{
	public Button031(String name){
		super(name);
	}
	@Override
	public boolean handleEvent(Event evt) {
		System.out.println("Button031.handleevent");
		//如果覆盖handleEvent()方法，则当前这个组件一般不应该直接返回false,应该调用super.handleEvent(), 让超类决定是否需要传播
		return false;
	}
}