package ui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends JFrame {

	GamePanel gamePanel;

	public GameWindow() {

		setTitle("LogicWorkz");
		setIconImages(DrawingUtility.icons);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
