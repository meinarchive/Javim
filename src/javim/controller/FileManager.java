package javim.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import javim.view.EditorFileLabel;

public class FileManager {
	private static final String CONFIG_FILE = "config.properties";

	private EditorFileLabel editorFileLabel;
	private String workingDirectory;

	public FileManager(EditorFileLabel editorFileLabel) {
		this.editorFileLabel = editorFileLabel;
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
				if (configDir != null && !configDir.isEmpty()) {
					this.workingDirectory = configDir;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			saveSettings();
		}
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
			editorFileLabel.getEditorFile().setFileName(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
