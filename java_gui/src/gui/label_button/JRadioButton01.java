package gui.label_button;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JRadioButton01 extends JApplet {
	public void init() {
		Container contentPane = getContentPane();

		contentPane.add(new PrintRangePanel(100, 101));
	}
}
class PrintRangePanel extends JPanel {
	JRadioButton  	printAll, printRange;
	JLabel     		startPage, endPage;
	JTextField     	startField, endField;

	public PrintRangePanel(int start, int end) {
		ButtonGroup group= new ButtonGroup();

		printAll   = new JRadioButton("Print All");
		printRange = new JRadioButton("Print Range");

		startPage = new JLabel("Start Page:");
		endPage   = new JLabel("End Page:");

		startField = new JTextField(Integer.toString(start));
		endField   = new JTextField(Integer.toString(end));

		add(printAll);  add(printRange);
		add(startPage); add(startField);
		add(endPage);   add(endField);

		printRange.setSelected(true);

		//单选框放到一个互斥的按钮组里就可以保证只选中一个
		group.add(printAll);
		group.add(printRange);

		printRange.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(printRange.isSelected()) {
					startField.setEnabled(true);
					endField.setEnabled  (true); 
					startPage.setEnabled (true); 
					endPage.setEnabled   (true); 

					//startPage.repaint();
					//endPage.repaint();

					startField.requestFocus();
				}
			}
		});
		printAll.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent event) {
				if(printAll.isSelected()) {
					startField.setEnabled(false);
					endField.setEnabled  (false);
					startPage.setEnabled (false);
					endPage.setEnabled   (false);

//					startPage.repaint();
//					endPage.repaint();
				}
			}
		});
	}
}
