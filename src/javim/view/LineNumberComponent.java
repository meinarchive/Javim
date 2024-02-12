package javim.view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import javim.utils.EditorConstants;

public class LineNumberComponent extends JComponent {
	private static final long serialVersionUID = 1L;

	private final EditorTextArea editorTextArea;
	private int lineCount = 1;

	public LineNumberComponent(EditorTextArea editorTextArea) {
		this.editorTextArea = editorTextArea;
		setPreferredSize(new Dimension(30, editorTextArea.getHeight()));

		setBackground(EditorConstants.DEFAULT_BACKGROUND);
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Paint the background yourself
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		// Set font and color for numbers
		g.setFont(EditorConstants.DEFAULT_FONT);
		g.setColor(EditorConstants.SECONDARY_FOREGROUND);

		// Determine the y offset for the first line
		int lineHeight = editorTextArea.getFontMetrics(editorTextArea.getFont()).getHeight();
		int ascent = editorTextArea.getFontMetrics(editorTextArea.getFont()).getAscent();
		int lineOffset = editorTextArea.getInsets().top + ascent;

		// Draw line numbers
		for (int line = 0; line < lineCount; line++) {
			int y = lineOffset + (line * lineHeight);
			g.drawString(Integer.toString(line + 1), 0, y);
		}
	}

}
