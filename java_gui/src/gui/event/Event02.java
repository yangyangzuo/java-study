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
public class Event02 extends Frame{
	public static Button b1 = new Button("button1");
	public static Button b2 = new Button("button2");
	public static void main(String[]args){
		Event02 f = new Event02();
		f.setLayout(new FlowLayout());
		f.add(b1);
		f.add(b2);
		f.setSize(200,200);
		f.setVisible(true);
	}
	
	/**
	 * 例如:我们需要监听按钮的动作事件,可以覆盖action()方法
	 */
	@Override
	public boolean action(Event evt, Object what) {
		System.out.println("action");
		if(evt.target == b1){
			System.out.println("button1");
		}else if(evt.target == b2){
			System.out.println("button2");
		}
		return super.action(evt, what);
	}

	
	/**
	 * 例如:我们需要监听鼠标进入控件事件，可以覆盖该方法
	 * 
	 * 注意：这些事件处理方法，
	 * 如果返回false,则表示事件直接传播给容器，超类无法处理该类事件
	 * 返回true,表示当前事件已经被处理完成，不需要在向上传递
	 * 一般情况下，最好在处理完自己的事情同时，调用super.XX()方法，这样，将来父类修改代码的时候，不会造成什么影响
	 * 
	 * 
	 */
	@Override
	public boolean mouseEnter(Event evt, int x, int y) {
		System.out.println("mouseenter");
		return super.mouseEnter(evt, x, y);
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
//		System.out.println("handleevent");
		return super.handleEvent(evt);
	}
	
	/*
	 * Component.handleEvent()方法源码:
	 * 
	 * 从源码可以看出，覆盖handleEvent()方法，通过检查id判断事件类型，很不方便,因此里面又定义了一些方法方便判断操作
	 * 
	 *  public boolean handleEvent(Event evt) {
        switch (evt.id) {
        	//鼠标事件
          case Event.MOUSE_ENTER:
              return mouseEnter(evt, evt.x, evt.y);

          case Event.MOUSE_EXIT:
              return mouseExit(evt, evt.x, evt.y);

          case Event.MOUSE_MOVE:
              return mouseMove(evt, evt.x, evt.y);

          case Event.MOUSE_DOWN:
              return mouseDown(evt, evt.x, evt.y);

          case Event.MOUSE_DRAG:
              return mouseDrag(evt, evt.x, evt.y);

          case Event.MOUSE_UP:
              return mouseUp(evt, evt.x, evt.y);

          case Event.KEY_PRESS:
          case Event.KEY_ACTION:
              return keyDown(evt, evt.key);

          case Event.KEY_RELEASE:
          case Event.KEY_ACTION_RELEASE:
              return keyUp(evt, evt.key);
			//一个操作事件
          case Event.ACTION_EVENT:
              return action(evt, evt.arg);
          	//获得焦点,选中组件
          case Event.GOT_FOCUS:
              return gotFocus(evt, evt.arg);
             //失去焦点，不选中组件
          case Event.LOST_FOCUS:
              return lostFocus(evt, evt.arg);
        }
        return false;
    }
	 */
}

