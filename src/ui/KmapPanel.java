package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import config.ConfigurableOption;
import config.InputUtility;
import config.SharedData;

public class KmapPanel extends JPanel {

	protected static final AlphaComposite transcluentWhite = AlphaComposite
			.getInstance(AlphaComposite.SRC_OVER, 0.9f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 1);

	SharedData data;

	public KmapPanel(SharedData data) {
		this.data = data;
		setOpaque(false);
		setPreferredSize(ConfigurableOption.WINDOW_DIMENSION);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (data == null)
			return;
		if (data.getPlayer().isKmap()) {
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, 894, 620);
			g2.setComposite(opaque);
			g2.drawImage(DrawingUtility.kmap_background, null, 287, 150);
		}
	}
}
