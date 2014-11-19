package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import logic.IRenderable;
import logic.gun.Gun;
import logic.gun.NormalGun;
import logic.gun.SpecialGunA;
import logic.gun.SpecialGunB;
import logic.gun.SpecialGunC;
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

		if (data.getRemainWaitingTime() > 0 && !data.getKmap().isRun()) {

			g2.setComposite(transcluentWhite);
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, ConfigurableOption.PLAYPANEL_WIDTH,
					ConfigurableOption.PLAYPANEL_HEIGHT);
			g2.setComposite(opaque);

			// current gun

			Gun currentGun = data.getPlayer().getCurrentGun();
			;
			BufferedImage gunImg;
			if (currentGun instanceof NormalGun)
				gunImg = DrawingUtility.binarySwitch0;
			else if (currentGun instanceof SpecialGunA)
				gunImg = DrawingUtility.pushButton0;
			else if (currentGun instanceof SpecialGunB)
				gunImg = DrawingUtility.oneShot;
			else
				gunImg = DrawingUtility.hexKeyboard;

			if (!(currentGun instanceof NormalGun)) {
				Font font = new Font("MS Sans Serif", Font.BOLD, 30);
				g2.setFont(font);
				g2.setColor(Color.WHITE);

				g2.drawString("Your gun is now", 200, 250);

				double w = gunImg.getWidth();
				double h = gunImg.getHeight();

				if (w > h) {
					h *= 45 / w;
					w = 45;
				} else {
					w *= 45 / h;
					h = 45;
				}

				g2.setColor(Color.WHITE);
				g2.fillRect(460, 215, 51, 51);
				g2.drawImage(gunImg, (int) (463 + (45 - w) / 2),
						(int) (218 + (45 - h) / 2), (int) w, (int) h, null);
			}
			// count down

			int plus = 0;
			if (currentGun instanceof NormalGun)
				plus = 50;

			int number = data.getRemainWaitingTime() / 100 + 1;
			Font font = new Font("MS Sans Serif", Font.BOLD, 100);
			g2.setFont(font);
			g2.setColor(Color.WHITE);
			FontMetrics metrics = g2.getFontMetrics(font);
			Rectangle2D rect = metrics.getStringBounds("" + number, g2);
			g2.drawString(
					"" + number,
					(ConfigurableOption.PLAYPANEL_WIDTH - (int) rect.getWidth()) / 2,
					(ConfigurableOption.PLAYPANEL_HEIGHT - (int) rect
							.getHeight()) / 2 + 50 + plus);

			return;
		}

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
							.getHeight()) / 2 + 30);

		}
	}
}
