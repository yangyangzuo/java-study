package gui.jframe;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * http://docs.oracle.com/javase/tutorial/uiswing/components/rootpane.html#layeredpane
 * http://docs.oracle.com/javase/tutorial/uiswing/components/layeredpane.html
 * 
 * @author zuoyang
 *
 */
public class JLayeredPaneTest1 extends JPanel implements ActionListener, MouseMotionListener {
	private String[] layerStrings = { "Yellow (0)", "Magenta (1)", "Cyan (2)", "Red (3)", "Green (4)" };
	private Color[] layerColors = { Color.yellow, Color.magenta, Color.cyan, Color.red, Color.green };

	private JLayeredPane layeredPane;
	private JLabel dukeLabel;
	private JCheckBox onTop;
	private JComboBox layerList;

	// Action commands
	private static String ON_TOP_COMMAND = "ontop";
	private static String LAYER_COMMAND = "layer";

	// Adjustments to put Duke's toe at the cursor's tip.
	private static final int XFUDGE = 40;
	private static final int YFUDGE = 57;

	public JLayeredPaneTest1(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		ImageIcon icon = null;
		try {
			icon = new ImageIcon(new URL("http://docs.oracle.com/javase/tutorial/uiswing/examples/components/RootLayeredPaneDemoProject/src/components/images/dukeWaveRed.gif"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		// Create and set up the layered pane.
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(300, 310));
		layeredPane.setBorder(BorderFactory.createTitledBorder("Move the Mouse to Move Duke"));
		layeredPane.addMouseMotionListener(this);

		// This is the origin of the first label added.
		Point origin = new Point(10, 20);

		// This is the offset for computing the origin for the next label.
		int offset = 35;

		// Add several overlapping, colored labels to the layered pane
		// using absolute positioning/sizing.
		for (int i = 0; i < layerStrings.length; i++) {
			JLabel label = createColoredLabel(layerStrings[i], layerColors[i], origin);
			layeredPane.add(label, new Integer(i));
			origin.x += offset;
			origin.y += offset;
		}

		// Create and add the Duke label to the layered pane.
		dukeLabel = new JLabel(icon);
		dukeLabel.setBounds(15, 225, icon.getIconWidth(), icon.getIconHeight());
		layeredPane.add(dukeLabel, new Integer(2), 0);

		// Add control pane and layered pane to this JPanel.
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(createControlPanel());
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(layeredPane);
	}


	// Create and set up a colored label.
	private JLabel createColoredLabel(String text, Color color, Point origin) {
		JLabel label = new JLabel(text);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setBounds(origin.x, origin.y, 140, 140);
		return label;
	}

	// Create the control pane for the top of the frame.
	private JPanel createControlPanel() {
		onTop = new JCheckBox("Top Position in Layer");
		onTop.setSelected(true);
		onTop.setActionCommand(ON_TOP_COMMAND);
		onTop.addActionListener(this);

		layerList = new JComboBox(layerStrings);
		layerList.setSelectedIndex(2); // cyan layer
		layerList.setActionCommand(LAYER_COMMAND);
		layerList.addActionListener(this);

		JPanel controls = new JPanel();
		controls.add(layerList);
		controls.add(onTop);
		controls.setBorder(BorderFactory.createTitledBorder("Choose Duke's Layer and Position"));
		return controls;
	}

	// Make Duke follow the cursor.
	public void mouseMoved(MouseEvent e) {
		dukeLabel.setLocation(e.getX() - XFUDGE, e.getY() - YFUDGE);
	}

	public void mouseDragged(MouseEvent e) {
	} // do nothing

	// Handle user interaction with the check box and combo box.
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		if (ON_TOP_COMMAND.equals(cmd)) {
			if (onTop.isSelected())
				layeredPane.moveToFront(dukeLabel);//把组件移动到当前层的所有组件的前面
			else
				layeredPane.moveToBack(dukeLabel);//把组件移动到当前层的所有组件的最下面

		} else if (LAYER_COMMAND.equals(cmd)) {
			int position = onTop.isSelected() ? 0 : 1;
			layeredPane.setLayer(dukeLabel, layerList.getSelectedIndex(), position);//把一个组件，放置到一个具体层级的，具体索引位置
		}
	}

	
	private static void createAndShowGUI(){
		JFrame frame = new JFrame("LayeredPaneDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500,500);
		// Create and set up the content pane.
		JComponent newContentPane = new JLayeredPaneTest1();
//		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater( ()->createAndShowGUI() );
	}
}