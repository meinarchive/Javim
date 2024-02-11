package javim;

import javax.swing.SwingUtilities;

import javim.view.EditorFrame;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new EditorFrame().setVisible(true));
	}
}
