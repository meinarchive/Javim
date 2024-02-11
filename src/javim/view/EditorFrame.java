package javim.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditorFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	
	private EditorTextArea editorTextArea = new EditorTextArea();
	private EditorScrollPane editorScrollPane = new EditorScrollPane(editorTextArea);
	private EditorFileLabel editorFileLabel = new EditorFileLabel();
	
	private JPanel southPanel = new JPanel(new BorderLayout());

	public EditorFrame() {
		initializeFrame();
	}

	private void initializeFrame() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
				
		southPanel.add(editorFileLabel, BorderLayout.SOUTH); 

		add(editorScrollPane, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
		
		editorTextArea.requestFocusInWindow();
	}

	public static int getFrameWidth() {
		return FRAME_WIDTH;
	}

	public static int getFrameHeight() {
		return FRAME_HEIGHT;
	}
}
