package gui.print;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Oracle_03_PaginationExample implements Printable, ActionListener {

	int[] pageBreaks; // array of page break line positions.

	/* Synthesise some sample lines of text */
	String[] textLines;

	private void initTextLines() {
		if (textLines == null) {
			int numLines = 200;
			textLines = new String[numLines];
			for (int i = 0; i < numLines; i++) {
				textLines[i] = "This is line number " + i;
			}
		}
	}

	public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {

		Font font = new Font("Serif", Font.PLAIN, 10);
		FontMetrics metrics = g.getFontMetrics(font);
		int lineHeight = metrics.getHeight();

		if (pageBreaks == null) {
			initTextLines();
			int linesPerPage = (int) (pf.getImageableHeight() / lineHeight);
			int numBreaks = (textLines.length - 1) / linesPerPage;
			pageBreaks = new int[numBreaks];
			for (int b = 0; b < numBreaks; b++) {
				pageBreaks[b] = (b + 1) * linesPerPage;
			}
		}

		if (pageIndex > pageBreaks.length) {
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 * Since we are drawing text we
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		/*
		 * Draw each line that is on this page. Increment 'y' position by
		 * lineHeight for each line.
		 */
		int y = 0;
		int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex - 1];
		int end = (pageIndex == pageBreaks.length) ? textLines.length : pageBreaks[pageIndex];
		for (int line = start; line < end; line++) {
			y += lineHeight;
			g.drawString(textLines[line], 0, y);
		}

		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}

	public void actionPerformed(ActionEvent e) {
		PrinterJob job = PrinterJob.getPrinterJob();
		
		job.setPrintable(this,job.defaultPage());
		boolean ok = job.printDialog();
		if (ok) {
			try {
				job.print();
			} catch (PrinterException ex) {
				/* The job did not successfully complete */
				ex.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {

		try {
			String cn = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(cn); // Use the native L&F
		} catch (Exception cnf) {
		}
		JFrame f = new JFrame("Printing Pagination Example");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		JButton printButton = new JButton("Print Pages");
		printButton.addActionListener(new Oracle_03_PaginationExample());
		f.add("Center", printButton);
		f.pack();
		f.setVisible(true);
	}
}