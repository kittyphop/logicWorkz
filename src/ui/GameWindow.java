package ui;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

	public GameWindow() {

		setTitle("LogicWorkz");
		setIconImages(DrawingUtility.icons);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
}
