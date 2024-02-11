package javim.view;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class EditorScrollPane extends JScrollPane {
	private static final long serialVersionUID = 1L;
	
	private EditorTextArea editorTextArea;

	public EditorScrollPane(EditorTextArea editorTextArea) {
		super(editorTextArea);
		
		this.editorTextArea = editorTextArea;
		
		initializeScrollPane();
	}

	private void initializeScrollPane() {
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		setBorder(null);
	}

	public EditorTextArea getEditorTextArea() {
		return editorTextArea;
	}

	public void setEditorTextArea(EditorTextArea editorTextArea) {
		this.editorTextArea = editorTextArea;
	}
}
