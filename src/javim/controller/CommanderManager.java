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
import javim.view.EditorScrollPane;

public class CommanderManager {
	private EditorFrame editorFrame;
	private FileManager fileManager;

	public CommanderManager(EditorFrame editorFrame, FileManager fileManager) {
		this.editorFrame = editorFrame;
		this.fileManager = fileManager;

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

		if (commanderTextArea.isVisible()) {
			commanderTextArea.requestFocusInWindow();
		} else {
			editorScrollPane.getEditorTextArea().requestFocus();
			editorScrollPane.getEditorTextArea().setEditable(true);
		}
	}

	protected void runCommand(String command) {
		String content = editorFrame.getEditorScrollPane().getEditorTextArea().getText();

		if (command.startsWith("s ")) {
			String fileName = command.substring(2).trim();
			fileManager.saveFile(fileName, content);
			System.out.println(fileName + " saved");
		} else if (command.equals("s")) {
			fileManager.saveCurrentFile(content);
		} else if (command.startsWith("o ")) {
			String fileName = command.substring(2).trim();
			fileManager.openFile(fileName, fileName);
		} else if (command.equals("cl")) {
			fileManager.closeFile();
		} else if (command.equals("see wrd")) {
            System.out.println("Working directory set to: " + fileManager.getWorkingDirectory());
		} else if (command.startsWith("set wrd ")) {
			String workingDirectory = command.substring(8).trim();
			fileManager.setWorkingDirectory(workingDirectory);
		} else if (command.equals("rset wrd")) {
			fileManager.revertToDefaultDirectory("");
		} else {
			System.out.println("Unknown command");
		}
	}

}
