package gui.lookAndFeel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 * 官方书籍:
 * Java Look and Feel Design Guidelines
 * http://www.oracle.com/technetwork/java/jlf-135985.html
 * 
 * 官方文档:
 * http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/index.html
 * 
 * 其他文档:
 * 
 * 
 * @author zuoyang
 *
 */
public class LookAndFeel03 {
		
	public static void main(String[]args){
		
		//文档翻译:
		//http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/index.html
		//swing的架构是通过把每个组件分离成多个不同的类来实现的:
		//对于JComponent的子类，都有一个相关联的ComponentUI的子类
		//例如:
		//对于JList(继承了Jcomponent)有一个相关的ListUI(集成了ComponentUI)
		//在swing文档中,ComponentUI的子类有很多个名称,例如:the ui,component ui,ui delegate,lookAndFeel delegate
		//都是指的ComponentUI的子类
		//开发者一般不需要和UI delegate直接交互,大多数情况，ui delegate主要是JComponent子类内部调用
		//通过JComponent子类提供的方法来访问UI delegate
		//例如:JComponent子类的所有绘制工作都是通过ui delegate来代理绘制的
		//通过代理来绘制,外观可以根据lookAndFeel的值不同而不同
		//可以为swing中定义的每个ComponentUI的子类提供一个具体lookandfeel实现
		
		//jre提供如下lookAndFeel:
		//1.CrossPlatformLookAndFeel,也叫作metal,在所有平台看起来是一样的，具有跨平台性，是java api
		//(javax.swing.plaf.metal)的一部分,如果你没有设置lookAndFeel,则默认使用这个lookAndFeel
		//2.SystemLookAndFeel,本地系统的lookandfeel,系统Lookandfeel,在runtime时被决定，程序可以获得这个lookandfeel名称
		//3.synnth,通过一个xml文件，创建一个你自己的look and feel
		//4.multiplexing,Multiplexing— a way to have the UI methods delegate to a number of different look and feel implementations at the same time.
		
		//sun公司的lookandfeel具有很大的通用性，这些通用性定义在基本的look and feel,在javax,swing.plaf.basic包中
		//其他图形用户接口lookandfeel(例如:windows,gtk)都是通过扩展javax,swing.plaf.basic来实现的
		//在api中可以看到4个包:
		//javax,swing.plaf.basic,基本的ui delegate被用来扩展，以便创建定制的个性化look and feel
		//javax.swing.plaf.metal,java lookandfeel,默认的java跨平台lookandfeel,这个默认的lookandfeel默认的主题是Ocean,所以这个lookandfeel又被称为ocean lookandfeel
		//javax.swing.plaf.multi,一个可以复用的lookandfeel,允许ui method在同一时间去代理操作一系列的look and feel
		//javax.swing.plaf.synth,一个可以通过xml文件容易配置的look and feel
		//javax.swing.plaf.nimbus,jdk7新出的一个lookandfeel
		
		//注意:如果要设置lookandfeel, 应该把初始化工作作为应用程序的第一步去操作，否则，无论你初始化什么样的lookandfeel,都是有风险的 
		//当一个静态变量，引用一个swing类，会导致look and feel被加载，这可能会导致意外发生
		//如果没有lookandfeel被执行，则默认的jre中的lookandfeel被加载进去
		
		//swing组件的lookandfeel的设置，通过javax.swing包中的UIManager类
		//当创建一个swing组件的时候，这个组件会询问ui manager,实现当前组件的lookandfeel的ui delegate是什么
		//例如:每个JLabel构造函数都会询问ui manager,对于lable类来说，哪个ui delegate对象比较合适,然后使用ui代理对象来实现所有的绘图操作和事件处理
		//1.通过编程指定lookandfeel
		//可以通过:UIManager.setLookAndFeel(LookAndFeel)或者UIManager.setLookAndFeel(String)
		//如下代码片段，使用跨平台的 java lookandfeel(javax.swing.plaf.metal.MetalLookAndFeel)
//		public static void main(String[] args) {
//			//程序的最开始先设置lookandfeel
//		    try {
//		            // Set cross-platform Java L&F (also called "Metal")
//		        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//				//或者UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//		    } 
//		    catch (Exception e) {}
//		    
//		    //创建和显示gui
//		    new SwingApplication();
//		}
		//2.也可以通过命令行(文档有说明，具体再查)
		//3.通过swing.properties文件(文档有说明，具体再查)
		
		//ui manager如何选择look and feel
		//当一个ui manager需要设置look and feel的时候，会发生如下步骤:
		//1.如果在需要look and feel之前，程序已经设置了look and feel,则ui manager尝试去创建这个look anf feel类的一个实例对象,如果成功了，则所有的组件都使用这个lookandfeel
		//2.如果程序没有成功设置look and feel,则ui manager使用swing.defaultlaf属性来执行look and feel,如果这个属性在swing.properties文件和命令行都指定了，则命令行指定的优先处理
		//3.如果上面的额步骤都没有一个合适的lookandfeel出现，则sun's jre使用java lookandfeel(javax.swing.plaf.metal.MetalLookAndFeel),对于其他的系统，例如apple,则使用apple默认的lookand feel
		
		
		
		//如何在程序开始后更改lookandfeel
		//可以使用UIManager.setLookAndFeel()来设置lookandfeel，即使程序的gui已经呈现,为了使已经显示的组件表现出使用新的lookandfeel,
		//可以对一个顶端容器执行SwingUtilities.updateComponentTreeUI()方法,这个容器内包含的所有组件都会使用新的lookandfeel
		//例如:
		//UIManager.setLookAndFeel("name");
		//SwingUtilities.updateComponentTreeUI(frame);
		//frame.pack();
		//下面代码举例说明了如何使用:
		
		JFrame frame = new JFrame();
		frame.setSize(500, 500);
		frame.setLayout(null);
		
		
		JButton button = new JButton("hello");
		button.setBounds(100, 100, 150, 150);
		frame.add(button);
		
		//设置lookAndFeel
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		JButton button2 = new JButton("hello2");
		button2.setBounds(200, 200, 150, 150);
		frame.add(button2);
		
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
		//可以发现第一个按钮是系统默认主题风格，第二个是更改后的主题风格
//		UIManager.setLookAndFeel(newLookAndFeel);//设置当前的界面样式，但不更新已有的组件的ui
		
		try {
			Thread.currentThread().sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//休眠3秒，调用SwingUtils.updateComponentTreeUI()来更新组件
		SwingUtilities.updateComponentTreeUI(frame);
		//该方法会递归的为包含在这个方法的容器中的所有组件更新ui
		//当然，如果想更新按钮，可以直接调用
//		SwingUtilities.updateComponentTreeUI(button);
		
	}
}
