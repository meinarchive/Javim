package javim.view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import javim.controller.CommanderManager;
import javim.controller.FileManager;

public class EditorFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;

	private EditorTextArea editorTextArea = new EditorTextArea();
	private EditorScrollPane editorScrollPane = new EditorScrollPane(editorTextArea);
	private EditorFileLabel editorFileLabel = new EditorFileLabel();
	private CommanderTextArea commanderTextArea = new CommanderTextArea();
	private EditorInfoLabel editorInfoLabel = new EditorInfoLabel();
	private FileManager fileManager = new FileManager(editorFileLabel, editorTextArea, editorInfoLabel);

	private JPanel southPanel = new JPanel(new BorderLayout());
	private LineNumberComponent lineNumberComponent;

	public EditorFrame() {
		initializeFrame();
		setupDocumentListener();

		new CommanderManager(this, fileManager, editorInfoLabel);
	}

	private void initializeFrame() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		southPanel.add(editorInfoLabel, BorderLayout.NORTH);
		southPanel.add(editorFileLabel, BorderLayout.SOUTH);

		add(commanderTextArea, BorderLayout.NORTH);
		lineNumberComponent = new LineNumberComponent(editorTextArea);
		editorScrollPane.setRowHeaderView(lineNumberComponent);
		add(editorScrollPane, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		commanderTextArea.setVisible(false);
		editorTextArea.requestFocusInWindow();

	}

	private void setupDocumentListener() {
		editorTextArea.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				updateLineCount();
			}

			public void removeUpdate(DocumentEvent e) {
				updateLineCount();
			}

			public void changedUpdate(DocumentEvent e) {

			}
		});
	}

	private void updateLineCount() {
		SwingUtilities.invokeLater(() -> {
			Document doc = editorTextArea.getDocument();
			int lineCount = doc.getDefaultRootElement().getElementCount();
			editorFileLabel.updateLineCount(lineCount);
			lineNumberComponent.setLineCount(lineCount);
		});
	}

	public static int getFrameWidth() {
		return FRAME_WIDTH;
	}

	public static int getFrameHeight() {
		return FRAME_HEIGHT;
	}

	public EditorScrollPane getEditorScrollPane() {
		return editorScrollPane;
	}

	public CommanderTextArea getCommanderTextArea() {
		return commanderTextArea;
	}

	public EditorFileLabel getEditorFileLabel() {
		return editorFileLabel;
	}
}
