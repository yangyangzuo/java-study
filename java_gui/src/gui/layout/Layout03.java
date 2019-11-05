package gui.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * cardlayout
 * @author zuoyang
 *
 */
public class Layout03 extends Frame {
	
	static Panel contentPanel = new Panel();
	static CardLayout cardLayout = new CardLayout();
	
	public static void main(String[] args) {
		Layout03 f = new Layout03();
		// f.setUndecorated(true);

		Panel topPanel = new Panel();
	
		f.add(topPanel,BorderLayout.NORTH);
		f.add(contentPanel,BorderLayout.CENTER);
		
		
		//卡片内容
		
		contentPanel.setLayout(cardLayout);
		contentPanel.add(new Button("first1"));
		contentPanel.add(new Button("first2"));
		contentPanel.add(new Button("first3"));
		contentPanel.add(new Button("first4"));
		
		
		//控制选项卡片显示
		Button prev = new Button("<<");
		Button next = new Button(">>");
		topPanel.add(prev);
		topPanel.add(next);
		prev.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.previous(contentPanel);
			}
		});
		next.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(contentPanel);
			}
		});
		
				
				
				
				
		
		f.setSize(800, 600);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
	}

}
