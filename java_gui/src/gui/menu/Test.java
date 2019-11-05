package gui.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Test extends JApplet {
	Icon 
		dukeStanding = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/menu/duke_standing.gif"),
		dukeWaving = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/menu/duke_waving.gif"),
		dukeStandingSmall = 
				new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/menu/duke_standing_small.gif"),
		dukeWavingSmall = new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/menu/duke_waving_small.gif");

	public void init() {
		Container contentPane = getContentPane();
		Action[] actions = {
						new NewAction(),
						new OpenAction(),
						new CutAction(),
						new CopyAction(),
						new PasteAction(),
						new ExitAction()
		};
		JToolBar toolbar = new JToolBar();
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");

		JRadioButton 
			menubarDuke = new JRadioButton(dukeStandingSmall),
			menuDuke = new JRadioButton(dukeStandingSmall),
			toolbarDuke = new JRadioButton(dukeStanding);
					
		menuDuke.setRolloverIcon(dukeWavingSmall);
		menubarDuke.setRolloverIcon(dukeWavingSmall);
		toolbarDuke.setRolloverIcon(dukeWaving);

		menubar.add(menubarDuke);
		toolbar.add(toolbarDuke);
		fileMenu.add(menuDuke);

		for(int i=0; i < actions.length; ++i) {
			fileMenu.add(actions[i]);

			if(i != actions.length-1)
				 toolbar.add(actions[i]);

			if(i == 2 || i == actions.length-2){
				toolbar.addSeparator();
				fileMenu.addSeparator();
			}
		}
		menubar.add(fileMenu);

		contentPane.add(toolbar, BorderLayout.NORTH);
		getRootPane().setJMenuBar(menubar);
		System.out.println(contentPane.getClass().getName());
		LayoutManager lm = contentPane.getLayout();
		System.out.println(lm.getClass());

	}
	class NewAction extends AbstractAction {
		public NewAction() {
			super("New ...", new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/menu/new.gif"));
		}
		public void actionPerformed(ActionEvent event) {
			showStatus("new");
		}
	}
	class OpenAction extends AbstractAction {
		public OpenAction() {
			super("Open ...", new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/menu/open.gif"));
		}
		public void actionPerformed(ActionEvent event) {
			showStatus("open");
		}
	}
	class CutAction extends AbstractAction {
		public CutAction() {
			super("Cut", new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/menu/cut.gif"));
		}
		public void actionPerformed(ActionEvent event) {
			showStatus("cut");
		}
	}
	class CopyAction extends AbstractAction {
		public CopyAction() {
			super("Copy", new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/menu/copy.gif"));
		}
		public void actionPerformed(ActionEvent event) {
			showStatus("copy");
		}
	}
	class PasteAction extends AbstractAction {
		public PasteAction() {
			super("Paste", new ImageIcon("/Users/gudandan/Desktop/jeeworkspace/java_gui/src/gui/menu/paste.gif"));
		}
		public void actionPerformed(ActionEvent event) {
			showStatus("paste");
		}
	}
	class ExitAction extends AbstractAction {
		public ExitAction() {
			super("Exit");
			putValue(Action.SMALL_ICON, dukeWavingSmall);
		}
		public void actionPerformed(ActionEvent event) {
			System.exit(0);
		}
	}
}
