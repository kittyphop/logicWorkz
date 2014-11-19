package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import logic.IRenderable;
import config.ConfigurableOption;
import config.SharedData;

public class PlayPanel extends JPanel {

	protected static final AlphaComposite transcluentWhite = AlphaComposite
			.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 1);

	SharedData data;

	public PlayPanel(SharedData data) {
		this.data = data;
		setPreferredSize(ConfigurableOption.PLAYPANEL_DIMENSION);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(DrawingUtility.play_panel, null, 0, 0);

		if (WindowManager.getStatus() != WindowManager.GAME_STATUS)
			return;

		ArrayList<IRenderable> list = data.getGameList();
		for (int i = 0; i < list.size(); i++)
			list.get(i).render(g2);

		if (data.getPlayer().isPause()) {
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, ConfigurableOption.PLAYPANEL_WIDTH,
					ConfigurableOption.PLAYPANEL_HEIGHT);
			g2.setComposite(opaque);

			Font font = new Font("MS Sans Serif", Font.BOLD, 30);
			g2.setFont(font);
			g2.setColor(Color.WHITE);
			FontMetrics metrics = g2.getFontMetrics(font);
			Rectangle2D rect = metrics.getStringBounds("PAUSE", g2);
			g2.drawString(
					"PAUSE",
					(ConfigurableOption.PLAYPANEL_WIDTH - (int) rect.getWidth()) / 2,
					(ConfigurableOption.PLAYPANEL_HEIGHT - (int) rect
							.getHeight()) / 2 + 20);

		}
	}
}
