package javim.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javim.view.EditorFileLabel;
import javim.view.EditorTextArea;

public class FileManager {
	private static final String CONFIG_FILE = "config.properties";

	private EditorFileLabel editorFileLabel;
	private String workingDirectory;
	private EditorTextArea editorTextArea;

	public FileManager(EditorFileLabel editorFileLabel, EditorTextArea editorTextArea) {
		this.editorFileLabel = editorFileLabel;
		this.editorTextArea = editorTextArea;
		this.workingDirectory = System.getProperty("user.dir");

		loadSettings();
	}

	private void loadSettings() {
		Properties properties = new Properties();
		File configFile = new File(CONFIG_FILE);

		if (configFile.exists()) {
			try (FileInputStream in = new FileInputStream(configFile)) {
				properties.load(in);
				String configDir = properties.getProperty("workingDirectory");
				if (isValidDirectory(configDir)) {
					this.workingDirectory = configDir;
				} else {
					revertToDefaultDirectory("Loaded working directory is invalid.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			revertToDefaultDirectory("Config file does not exist. Using default directory.");
		}
	}

	private boolean isValidDirectory(String directoryPath) {
		if (directoryPath == null || directoryPath.isEmpty()) {
			return false;
		}
		File directory = new File(directoryPath);
		return directory.exists() && directory.isDirectory(); 
	}

	protected void revertToDefaultDirectory(String reason) {
		this.workingDirectory = System.getProperty("user.dir"); 
		System.out.println(reason + "Working directory set to: " + this.workingDirectory);
		saveSettings(); 
	}

	private void saveSettings() {
		Properties properties = new Properties();
		properties.setProperty("workingDirectory", this.workingDirectory);
		try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
			properties.store(out, "Application Settings");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveFile(String fileName, String text) {
		String fullPath = workingDirectory.isEmpty() ? fileName : workingDirectory + File.separator + fileName;

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath))) {
			writer.write(text);
			editorFileLabel.updateFileName(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveCurrentFile(String text) {
		if (editorFileLabel.getEditorFile().getFileName() == "New file") {
			String defaultName = "Untitled.txt";
			saveFile(defaultName, text);
		} else {
			saveFile(editorFileLabel.getEditorFile().getFileName(), text);
		}
	}

	public void openFile(String path, String fileName) {
		String fullPath = workingDirectory.isEmpty() ? path : workingDirectory + "/" + path;

		StringBuilder text = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(fullPath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				text.append(line).append("\n");
			}
			this.editorTextArea.setText(text.toString());
			editorFileLabel.updateFileName(fileName);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to open file: " + e.getMessage());
		}
	}

	public void closeFile() {
		editorFileLabel.updateFileName("New file");
		this.editorTextArea.setText("");
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		File dir = new File(workingDirectory);
		if (dir.exists() && dir.isDirectory()) {
			this.workingDirectory = workingDirectory.endsWith(File.separator) ? workingDirectory
					: workingDirectory + File.separator;
		} else {
			this.workingDirectory = System.getProperty("user.dir");
			System.out.println(
					"Provided working directory is invalid. Reverting to default directory: " + this.workingDirectory);
		}
		saveSettings();
	}
}
