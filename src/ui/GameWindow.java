package ui;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
	
	GamePanel gamePanel;
	
	public GameWindow() {
		
		gamePanel = new GamePanel();
		add(gamePanel);
		
		setTitle("LogicWorkz");
		setIconImages(DrawingUtility.icons);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);

		new MenuDialog();
	}
}
