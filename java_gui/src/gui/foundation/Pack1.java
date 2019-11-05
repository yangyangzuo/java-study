package gui.foundation;


import java.awt.Frame;

public class Pack1
{

	public static void main(String[] args) throws InterruptedException
	{
		Frame f = new Frame();
		f.setSize(500, 500);
		
		//pack()方法是Window对象的一个方法,只有window对象或者他的子类(Frame,dialog等等)创建的对象才可以有这个方法
		//pack()方法:
		//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents.
		//调整此窗口的大小，
		//The resulting width and height of the window are automatically enlarged if either of dimensions is less than the minimum size as specified by the previous call to the setMinimumSize method.
		//以适合其子组件的首选大小和布局。
		//If the window and/or its owner are not displayable yet, 
		//如果该窗口和/或其所有者还不可显示，
		//both of them are made displayable before calculating the preferred size. 
		//则在计算首选大小之前都将变得可显示。
		//The Window is validated after its size is being calculated.
		//在计算首选大小之后，将会验证该窗口。
		 
		
		
		//如果前面设置了窗体的大小，但是这里又调用了pack()方法，则窗口尺寸会重新计算为包裹组件的大小，
		//不会显示为上面定义的大小(500*500),
		f.pack();
		//这里因为frame中没有其他组件,所以大小为0
		
		f.setVisible(true);
	}
}
