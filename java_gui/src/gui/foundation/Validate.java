package gui.foundation;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Validate extends Frame {
	public static void main(String[] args) {
		Validate f = new Validate();


		
		f.add(new MyPanel());
		
		
		
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

class MyPanel extends Panel implements ActionListener{
	
	private TextField field = new TextField("文字helloworld");
	private Button large = new Button("large");
	private Button small = new Button("small");
	
	public MyPanel(){
		this.add(field);
		this.add(large);
		this.add(small);
		this.setBackground(Color.gray);
		large.addActionListener(this);
		small.addActionListener(this);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Font font = field.getFont();
		int fontSize = font.getSize();
		String family = font.getFamily();
		int style = font.getStyle();
		
		if(e.getSource()==large){
			field.setFont(new Font(family,style,fontSize+2));
		}else if(e.getSource()==small){
			field.setFont(new Font(family,style,fontSize-2));
		}
		
		this.validate();
	}
	
}