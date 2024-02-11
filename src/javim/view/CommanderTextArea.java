package javim.view;

import javax.swing.JTextArea;
import javax.swing.border.MatteBorder;

import javim.utils.EditorConstants;

public class CommanderTextArea extends JTextArea {
	private static final long serialVersionUID = 1L;

	public CommanderTextArea() {
		initializeTextArea();
	}

	private void initializeTextArea() {
		setBackground(EditorConstants.DEFAULT_BACKGROUND);
		setForeground(EditorConstants.DEFAULT_FOREGROUND);
		setCaretColor(EditorConstants.DEFAULT_FOREGROUND);
		setFont(EditorConstants.DEFAULT_FONT);
		setBorder(new MatteBorder(0, 0, 1, 0, EditorConstants.DEFAULT_FOREGROUND));
	}
}
