package gui.event;

import java.awt.Canvas;
import java.awt.Event;
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
public class Event04 extends Frame{
	public static void main(String[]args){
		Event04 f = new Event04();
		f.add(new Canvas041());
		f.setSize(200,200);
		f.setVisible(true);
	}

	
}

class Canvas041 extends Canvas{

	@Override
	public boolean mouseDown(Event evt, int x, int y) {
		System.out.println(whichButton(evt) + " mouseDown");
		return true;
	}

	@Override
	public boolean mouseDrag(Event evt, int x, int y) {
		System.out.println(whichButton(evt) + " mouseDrag");
		return true;
	}

	@Override
	public boolean mouseUp(Event evt, int x, int y) {
		System.out.println(whichButton(evt) + " mouseUp");
		return true;
	}
	
	//判断鼠标哪个键进行了点击操作
	private String whichButton(Event evt){
		if((evt.modifiers&evt.ALT_MASK) != 0)
			return "center key";
		else if((evt.modifiers&evt.META_MASK)!=0)
			return "right key";
		else
			return "left key";
		
	}
}
