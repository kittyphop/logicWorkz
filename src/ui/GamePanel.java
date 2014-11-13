package ui;
import config.ConfigurableOption;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel{
	public GamePanel()
	{
		setPreferredSize(ConfigurableOption.getWindowDimension());
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(DrawingUtility.game_background, null, 0, 0);
	}
}
