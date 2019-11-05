package gui.paint;

public class Test extends myframe{
	public static void main(String[] args) {
		new Test().setVisible();
	}
}
class myframe extends mywindow{
	
	public void show(){
		System.out.println("myframe.show()");
		addnotify();
	}
	public void addnotify(){
		System.out.println("myframe.addnotify");
		super.addnotify();
	}
}
class mywindow extends mycontainer{
	public void setVisible(){
		System.out.println("mywindow.setvisible()");
		super.setVisible();
	}
	public void show(){
		System.out.println("mywindow.show()");
	}
	public void addnotify(){
		System.out.println("mywindow.addnotify");
		super.addnotify();
	}
}
class mycontainer extends mycomponent{
	public void addnotify(){
		super.addnotify();
		System.out.println("mycontainer.addnotify");
	}
}
class mycomponent{
	public void setVisible(){
		System.out.println("mycomponent.setvisible()");
		show();
	}
	public void addnotify(){
		System.out.println("mycomponent.addnotify");
	}
	public void show(){
		System.out.println("mycomponent show()");
	}
}
