package gui.lookAndFeel;

	/*
	 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
	 *
	 * Redistribution and use in source and binary forms, with or without
	 * modification, are permitted provided that the following conditions
	 * are met:
	 *
	 *   - Redistributions of source code must retain the above copyright
	 *     notice, this list of conditions and the following disclaimer.
	 *
	 *   - Redistributions in binary form must reproduce the above copyright
	 *     notice, this list of conditions and the following disclaimer in the
	 *     documentation and/or other materials provided with the distribution.
	 *
	 *   - Neither the name of Oracle or the names of its
	 *     contributors may be used to endorse or promote products derived
	 *     from this software without specific prior written permission.
	 *
	 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
	 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
	 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
	 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
	 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
	 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
	 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
	 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
	 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
	 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
	 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
	 */ 


	/*
	 * LookAndFeelDemo.java is a Java SE 6 example that requires
	 * one other file (TestTheme.java).
	 */
	import javax.swing.*;          
	import java.awt.*;
	import java.awt.event.*;

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.*;

	public class LookAndFeelTest01 implements ActionListener {
	    private static String labelPrefix = "Number of button clicks: ";
	    private int numClicks = 0;
	    final JLabel label = new JLabel(labelPrefix + "0    ");

	    // Specify the look and feel to use by defining the LOOKANDFEEL constant
	    // Valid values are: null (use the default), "Metal", "System", "Motif",
	    // and "GTK"
	    final static String LOOKANDFEEL = "Metal";
	    
	    // If you choose the Metal L&F, you can also choose a theme.
	    // Specify the theme to use by defining the THEME constant
	    // Valid values are: "DefaultMetal", "Ocean",  and "Test"
	    final static String THEME = "Test";
	    

	    public Component createComponents() {
	        JButton button = new JButton("I'm a Swing button!");
	        button.setMnemonic(KeyEvent.VK_I);
	        button.addActionListener(this);
	        label.setLabelFor(button);

	        JPanel pane = new JPanel(new GridLayout(0, 1));
	        pane.add(button);
	        pane.add(label);
	        return pane;
	    }

	    public void actionPerformed(ActionEvent e) {
	        numClicks++;
	        label.setText(labelPrefix + numClicks);
	    }

	    private static void initLookAndFeel() {
	        String lookAndFeel = null;
	        try{
	            lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
//	            lookAndFeel = UIManager.getSystemLookAndFeelClassName();
//	            lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
//	            lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
//	            lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
	            UIManager.setLookAndFeel(lookAndFeel);
	            //如果Lookandfeel是一个MetalLookAndFeel对象，则他还可以设置一个主题
//	            MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
//	            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
//	            MetalLookAndFeel.setCurrentTheme(new MyTheme());
//	            UIManager.setLookAndFeel(new MetalLookAndFeel()); 
	        }catch (Exception e) {
            	e.printStackTrace();
            } 
	    }

	    private static void createAndShowGUI() {
	        initLookAndFeel();
	        //JFrame.setDefaultLookAndFeelDecorated(true);

	        JFrame frame = new JFrame("SwingApplication");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        LookAndFeelTest01 app = new LookAndFeelTest01();
	        Component contents = app.createComponents();
	        frame.getContentPane().add(contents, BorderLayout.CENTER);
	        frame.pack();
	        frame.setVisible(true);
	    }

	    public static void main(String[] args) {
	        javax.swing.SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                createAndShowGUI();
	            }
	        });
	    }
	}
	
class MyTheme extends DefaultMetalTheme {

	    public String getName() { return "Toms"; }

	    private final ColorUIResource primary1 = new ColorUIResource(255, 255, 0);
	    private final ColorUIResource primary2 = new ColorUIResource(0, 255, 255);
	    private final ColorUIResource primary3 = new ColorUIResource(255, 0, 255);

	    protected ColorUIResource getPrimary1() { return primary1; }
	    protected ColorUIResource getPrimary2() { return primary2; }
	    protected ColorUIResource getPrimary3() { return primary3; }

	}