package gui.text;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import java.util.*;
/**
 * swing提供了5种文本组件,所有的文本组件都扩展javax.swing.text.JTextComponent类,而JTextComponnet又集成了JComponent类
 * swing的文本组件可以分为两种类型:简单文本组件和多风格文本组件
 * JTextField,JTextArea,JPasswordFile属于简单文本组件
 * JEditorPane和JTextPane是多风格文本组件,能一次显示多种字体和颜色
 * JEditorPane能显示不同格式的文本，例如:html和rtf
 * JTextPane能嵌入图片和组件
 * @author gudandan
 *
 */
public class Test extends JFrame {
	private JTextArea textArea = createTextArea();
	private Action[] actions = textArea.getActions();

	private JList actionList = createActionList(actions);
	private JSplitPane splitPane = new JSplitPane(
								JSplitPane.HORIZONTAL_SPLIT, 
								new JScrollPane(textArea),
								new JScrollPane(actionList));
	public Test() {
		Container contentPane = getContentPane();

		splitPane.setDividerLocation(150);

		contentPane.add(splitPane, BorderLayout.CENTER);
		contentPane.add(GJApp.getStatusArea(), 
						BorderLayout.SOUTH);

	}
	private JList createActionList(Action[] actions) {
		DefaultListModel model = new DefaultListModel();
		final JList list = new JList(model);

		list.setSelectionMode(
							ListSelectionModel.SINGLE_SELECTION);

		for(int i=0; i < actions.length; ++i) {
			model.addElement(actions[i]);
		}

		list.setCellRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(
									JList list, Object value,
									int index, boolean isSelected,
									boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value,
					index, isSelected, cellHasFocus);

				Action action = (Action)value;
				setText((String)action.getValue(Action.NAME));

				return this;
			}
		});
		list.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					Action source = 
					   (Action)actionList.getSelectedValue();

					textArea.requestFocus();
					source.actionPerformed(null);

					GJApp.showStatus("Action: " + 
							(String)source.getValue(Action.NAME));
				}
			}
		});

		return list;
	}
	private JTextArea createTextArea() {
		JTextArea textArea = new JTextArea(
			  "line one\nline two\nline three\nline four");

		textArea.setFont(new Font("Dialog", Font.ITALIC, 24));
		textArea.getCaret().setBlinkRate(0);
		return textArea;
	}
	public static void main(String args[]) {
		GJApp.launch(new Test(), 
					"Text Component Actions",300,300,450,300);
	}
}
class GJApp extends WindowAdapter {
	static private JPanel statusArea = new JPanel();
	static private JLabel status = new JLabel(" ");
	static private ResourceBundle resources;

	public static void launch(final JFrame f, String title,
							  final int x, final int y, 
							  final int w, int h) {
		launch(f,title,x,y,w,h,null);	
	}
	public static void launch(final JFrame f, String title,
							  final int x, final int y, 
							  final int w, int h,
							  String propertiesFilename) {
		f.setTitle(title);
		f.setBounds(x,y,w,h);
		f.setVisible(true);

		statusArea.setBorder(BorderFactory.createEtchedBorder());
		statusArea.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		statusArea.add(status);
		status.setHorizontalAlignment(JLabel.LEFT);

		f.setDefaultCloseOperation(
							WindowConstants.DISPOSE_ON_CLOSE);

		if(propertiesFilename != null) {
			resources = ResourceBundle.getBundle(
						propertiesFilename, Locale.getDefault());
		}

		f.addWindowListener(new WindowAdapter() {
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	static public JPanel getStatusArea() {
		return statusArea;
	}
	static public void showStatus(String s) {
		status.setText(s);
	}
	static Object getResource(String key) {
		if(resources != null) {
			return resources.getString(key);
		}
		return null;
	}
}
