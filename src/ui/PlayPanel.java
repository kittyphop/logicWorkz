package ui;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import config.ConfigurableOption;

public class PlayPanel extends JPanel{
	
	//IRenderableHolder
	
	public PlayPanel()
	{
		setPreferredSize(ConfigurableOption.getPlayPanelDimension());
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(DrawingUtility.play_panel, null, 0, 0);
		
		//for each object in IRenderableHolder : object.render(g);
	}
}
