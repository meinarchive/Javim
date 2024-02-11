package javim.view;

import javax.swing.JTextArea;

import javim.utils.EditorConstants;

public class EditorTextArea extends JTextArea {
	private static final long serialVersionUID = 1L;

	public EditorTextArea() {
		initializeTextArea();
	}

	private void initializeTextArea() {
		setBackground(EditorConstants.DEFAULT_BACKGROUND);
		setForeground(EditorConstants.DEFAULT_FOREGROUND);
		setCaretColor(EditorConstants.DEFAULT_FOREGROUND);
		setFont(EditorConstants.DEFAULT_FONT);
	}
}
