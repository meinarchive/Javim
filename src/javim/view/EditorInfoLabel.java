package javim.view;

import java.awt.Dimension;

import javax.swing.JLabel;

import javim.utils.EditorConstants;

public class EditorInfoLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	private String mode = "NORMAL";
	private String status = "";
	private String output;

	public EditorInfoLabel() {
		initializeLabel();
	}

	private void initializeLabel() {
		updateLabel();

		setOpaque(true);
		setBackground(EditorConstants.DEFAULT_BACKGROUND);
		setForeground(EditorConstants.DEFAULT_FOREGROUND);
		setFont(EditorConstants.SMALL_FONT);
		setPreferredSize(new Dimension(EditorFrame.getFrameWidth(), 25));
	}

	private void updateLabel() {
		String output = "<html><body style='padding: 4px; padding-left: 10px;'>" + mode
				+ "\t" + status + "</body></html>";
		setText(output);
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
		updateLabel();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
		updateLabel();
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
