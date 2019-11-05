package free;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.sun.awt.AWTUtilities;

import twaver.TWaverUtil;
import twaver.swing.LabelValueLayout;

public class FreeLoginUI extends JFrame {
	private int width = 332;
	private int height = 344;
	private ImageIcon buttonIcon = FreeUtil.getImageIcon("login_button.png");
	private ImageIcon buttonRoverIcon = FreeUtil.getImageIcon("login_button_rover.png");
	private ImageIcon buttonPressedIcon = FreeUtil.getImageIcon("login_button_pressed.png");
	private ImageIcon logoIcon = FreeUtil.getImageIcon("login_logo.png");
	private String logoRoverURL = FreeUtil.getImageURL("login_logo_rover.png");
	private ImageIcon logoRoverIcon = TWaverUtil.getImageIcon(logoRoverURL);
	private JLabel logoLabel = createDraggableLabel(logoIcon);
	private JButton btnLogin = new JButton();
	private JPanel inputPane = new JPanel() {
		private String backgroundImageURL = FreeUtil.getImageURL("login_background.png");
		private TexturePaint paint = FreeUtil.createTexturePaint(backgroundImageURL);

		protected void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(paint);
			g2d.fillRect(0, 0, getWidth(), getHeight());
		}
	};
	private MouseAdapter moveWindowListener = new MouseAdapter() {
		private Point lastPoint = null;

		public void mousePressed(MouseEvent e) {
			lastPoint = e.getLocationOnScreen();
		}

		public void mouseDragged(MouseEvent e) {
			Point point = e.getLocationOnScreen();
			int offsetX = point.x - lastPoint.x;
			int offsetY = point.y - lastPoint.y;
			Rectangle bounds = getBounds();
			point.x += offsetX;
			point.y += offsetY;
			setBounds(bounds);
			lastPoint = point;
		}

		public void mouseEntered(MouseEvent e) {
			if (e.getSource() == logoLabel) {
				logoLabel.setIcon(logoRoverIcon);
			}
		}

		public void mouseExited(MouseEvent e) {
			if (e.getSource() == logoLabel) {
				logoLabel.setIcon(logoIcon);
			}
		}
	};

	public FreeLoginUI() {
		init();
	}

	private void init() {
		setDefaultCloseOperation(2);
		setUndecorated(true);
		AWTUtilities.setWindowOpaque(this, false);

		JPanel centerPane = new JPanel(new BorderLayout());
		centerPane.add(btnLogin, "South");
		
		centerPane.setOpaque(false);

		setContentPane(centerPane);

		setSize(width, height);
		TWaverUtil.centerWindow(this);

		btnLogin.setBorder(null);
		btnLogin.setMargin(null);
		btnLogin.setOpaque(false);
		btnLogin.setIcon(buttonIcon);
		btnLogin.setRolloverEnabled(true);
		btnLogin.setRolloverIcon(buttonRoverIcon);
		btnLogin.setPressedIcon(buttonPressedIcon);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setRequestFocusEnabled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}

		});
		JPanel topPane = new JPanel(new BorderLayout());

		logoLabel.setOpaque(false);
		topPane.setOpaque(false);

		logoLabel.addMouseListener(moveWindowListener);
		logoLabel.addMouseMotionListener(moveWindowListener);
		topPane.addMouseListener(moveWindowListener);
		topPane.addMouseMotionListener(moveWindowListener);

		topPane.add(logoLabel, "Center");
		topPane.add(createDraggableLabel(FreeUtil.getImageIcon("login_left_top.png")), "West");
		topPane.add(createDraggableLabel(FreeUtil.getImageIcon("login_right_top.png")), "East");

		centerPane.add(createDraggableLabel(FreeUtil.getImageIcon("login_left.png")), "West");
		centerPane.add(createDraggableLabel(FreeUtil.getImageIcon("login_right.png")), "East");
		centerPane.add(topPane, "North");
		centerPane.add(inputPane, "Center");

		inputPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 0, 0));
		inputPane.setLayout(new LabelValueLayout(10, 12, false));
		inputPane.add(createInputLabel("Server:"));
		inputPane.add(new FreeTextField("localhost"));
		inputPane.add(createInputLabel("Company:"));
		inputPane.add(new FreeSearchTextField());
		inputPane.add(createInputLabel("User Name:"));
		inputPane.add(new FreeTextField("admin"));
		inputPane.add(createInputLabel("Password:"));
		inputPane.add(new FreePasswordField());
		inputPane.add(createInputLabel("Language:"));
		inputPane.add(new FreeTextField(TWaverUtil.getLocale().toString()));
		inputPane.add(new JLabel());

		JCheckBox cbRememberMe = new JCheckBox("Remember me");
		cbRememberMe.setOpaque(false);
		setupComponent(cbRememberMe);
		inputPane.add(cbRememberMe);

		inputPane.addMouseListener(moveWindowListener);
		inputPane.addMouseMotionListener(moveWindowListener);
	}

	private JLabel createDraggableLabel(ImageIcon icon) {
		JLabel label = new JLabel(icon);
		label.addMouseListener(moveWindowListener);
		label.addMouseMotionListener(moveWindowListener);
		return label;
	}

	private JLabel createInputLabel(String text) {
		JLabel label = new JLabel(text);
		setupComponent(label);
		return label;
	}

	private void setupComponent(JComponent c) {
		c.setFont(FreeUtil.FONT_14_BOLD);
		c.setForeground(FreeUtil.DEFAULT_TEXT_COLOR);
	}

	protected void login() {
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				FreeUtil.setupLookAndFeel();
				FreeLoginUI ui = new FreeLoginUI();
				ui.setVisible(true);
			}
		});
	}
}
