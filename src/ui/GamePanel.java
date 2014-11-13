package ui;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import config.ConfigurableOption;

public class GamePanel extends JPanel{
	
	PlayPanel playPanel;
	
	public GamePanel()
	{
		setPreferredSize(ConfigurableOption.getWindowDimension());
		setLayout(null);
		
		playPanel = new PlayPanel();
		playPanel.setBounds(15, 95, 717, 356);
		add(playPanel);
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(DrawingUtility.game_background, null, 0, 0);
	}	
}
