package gui.paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * this program demonstrate Transparency in lightweight component
 * 
 * @author wangdq
 * 
 */
public class Question010 {
	public static void main(String[] args) {
		Frame frame = new Frame("Lightweight Transparency demo");
		NightSky sky = new NightSky();
		sky.add(new Moon());
		frame.add(sky, BorderLayout.CENTER);//一个容器里面有轻组件
		frame.pack();
		frame.setVisible(true);
	}
}

/**
 * Night sky container lightweight component
 * 
 */
class NightSky extends Container {

	public NightSky() {
		this.setBackground(Color.black);
		this.setLayout(new FlowLayout());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}

	@Override
	public void paint(Graphics g) {
		// draw background
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//当前容器中有轻组件,如果不调用super.paint(g)是绘制不出来的
		// draw children
		super.paint(g);
	}

}

/**
 * Moon component lightweight component
 * 通过扩展Compnent来构造轻组件
 */
class Moon extends Component {
	@Override
	public Dimension getPreferredSize() {
		//这个组件实际是200*200
		return new Dimension(200, 200);
	}
	public Moon() {
		this.setBackground(Color.green);
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, this.getWidth()-1, this.getHeight()-1);
		//从最终结果看出Moon组件的
	}


}
