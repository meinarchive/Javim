package javim.controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import javim.view.CommanderTextArea;
import javim.view.EditorFrame;
import javim.view.EditorInfoLabel;
import javim.view.EditorScrollPane;

public class CommanderManager {
	private EditorFrame editorFrame;
	private FileManager fileManager;
	private EditorInfoLabel editorInfoLabel;

	public CommanderManager(EditorFrame editorFrame, FileManager fileManager, EditorInfoLabel editorInfoLabel) {
		this.editorFrame = editorFrame;
		this.fileManager = fileManager;
		this.editorInfoLabel = editorInfoLabel;

		initializeCommander();
	}

	private void initializeCommander() {
		JRootPane editorFrameRootPane = editorFrame.getRootPane();

		editorFrameRootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ALT, 0, true), "toggleCommanderVisibility");
		editorFrame.getCommanderTextArea().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "executeCommand");

		editorFrameRootPane.getActionMap().put("toggleCommanderVisibility", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				toggleCommanderVisibility();
			}
		});

		editorFrame.getCommanderTextArea().getActionMap().put("executeCommand", new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				String command = editorFrame.getCommanderTextArea().getText();
				runCommand(command);
				editorFrame.getCommanderTextArea().setText("");
				toggleCommanderVisibility();
			}
		});

		editorFrame.getCommanderTextArea().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
				}
			}
		});

	}

	protected void toggleCommanderVisibility() {
		EditorScrollPane editorScrollPane = editorFrame.getEditorScrollPane();
		CommanderTextArea commanderTextArea = editorFrame.getCommanderTextArea();

		commanderTextArea.setVisible(!commanderTextArea.isVisible());
		editorScrollPane.getEditorTextArea().setEditable(false);

		editorInfoLabel.setMode("COMMANDER");

		if (commanderTextArea.isVisible()) {
			commanderTextArea.requestFocusInWindow();
		} else {
			editorScrollPane.getEditorTextArea().requestFocus();
			editorScrollPane.getEditorTextArea().setEditable(true);
			editorInfoLabel.setMode("NORMAL");
		}
	}

	protected void runCommand(String command) {
		String content = editorFrame.getEditorScrollPane().getEditorTextArea().getText();

		if (command.startsWith("s ")) {
			String fileName = command.substring(2).trim();
			fileManager.saveFile(fileName, content);
			editorInfoLabel.setStatus(fileName + " saved");
		} else if (command.equals("s")) {
			fileManager.saveCurrentFile(content);
			editorInfoLabel.setStatus(editorFrame.getEditorFileLabel().getEditorFile().getFileName() + " saved");
		} else if (command.startsWith("o ")) {
			String fileName = command.substring(2).trim();
			fileManager.openFile(fileName, fileName);
		} else if (command.equals("cl")) {
			fileManager.closeFile();
			editorInfoLabel.setStatus("");
		} else if (command.equals("see wrd")) {
			editorInfoLabel.setStatus(fileManager.getWorkingDirectory());
		} else if (command.startsWith("set wrd ")) {
			String workingDirectory = command.substring(8).trim();
			fileManager.setWorkingDirectory(workingDirectory);
			editorInfoLabel.setStatus("Working directory set to: " + fileManager.getWorkingDirectory());
		} else if (command.equals("rset wrd")) {
			fileManager.revertToDefaultDirectory("");
			editorInfoLabel.setStatus("Working directory reverted to: " + fileManager.getWorkingDirectory());
		} else if (command.equals("term")) {
			String workingDirectory = fileManager.getWorkingDirectory();
			fileManager.openTerminal(workingDirectory);
		} else if(command.equals("q") || command.equals("q ")) {
			System.exit(0);
		} else if (!command.equals("")) {
			editorInfoLabel.setStatus("Unknown command");
		} else {
			editorInfoLabel.setStatus("");
		}
	}

}
