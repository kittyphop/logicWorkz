package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

import logic.GameLogic;
import logic.IRenderable;
import config.ConfigurableOption;
import config.SharedData;

public class PlayPanel extends JPanel {

	SharedData data;

	public PlayPanel(SharedData data) {
		this.data = data;
		setPreferredSize(ConfigurableOption.PLAYPANEL_DIMENSION);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(DrawingUtility.play_panel, null, 0, 0);
		/*
		if (data != null) {
			ArrayList<IRenderable> list = data.getList();
			for (IRenderable i : list) {
				i.render(g2);
			}
		}
		*/
	}
}
