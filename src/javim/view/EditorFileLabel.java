package javim.view;

import javax.swing.JLabel;

import javim.model.EditorFile;
import javim.utils.EditorConstants;

public class EditorFileLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	private EditorFile editorFile;
	private int lineCount = 1;

	public EditorFileLabel() {
		this.editorFile = new EditorFile();

		updateFileNameDisplay();
		initializeFileLabel();
	}

	public void updateLineCount(int newLineCount) {
		this.lineCount = newLineCount;
		updateFileNameDisplay();
	}

	private void updateFileNameDisplay() {
		String formattedFileName = formatFileName(editorFile.getFileName());
		setText(formattedFileName);
	}

	private void initializeFileLabel() {
		setOpaque(true);
		setBackground(EditorConstants.DEFAULT_BACKGROUND);
		setForeground(EditorConstants.DEFAULT_FOREGROUND);
		setFont(EditorConstants.DEFAULT_FONT);
	}

	private String formatFileName(String fileName) {
		return "<html><body style='padding: 4px; padding-left: 10px;'>- " + fileName
				+ "      <span style='font-size: 10px; color: " + EditorConstants.SECONDARY_FOREGROUND_HTML + ";'>lns " + lineCount + "</span></body></html>";
	}

	public EditorFile getEditorFile() {
		return editorFile;
	}

	public void setEditorFile(EditorFile editorFile) {
		this.editorFile = editorFile;
		updateFileNameDisplay();
	}

	public void updateFileName(String newFileName) {
		this.editorFile.setFileName(newFileName);
		updateFileNameDisplay();
	}
}
