package javim.view;

import javax.swing.JFrame;

public class EditorFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 600;
	
	public EditorFrame() {
		initializeFrame();
	}

	private void initializeFrame() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static int getFrameWidth() {
		return FRAME_WIDTH;
	}

	public static int getFrameHeight() {
		return FRAME_HEIGHT;
	}
}
