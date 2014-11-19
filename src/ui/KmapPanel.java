package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

import logic.gun.Gun;
import logic.gun.NormalGun;
import logic.gun.SpecialGunA;
import logic.gun.SpecialGunB;
import logic.gun.SpecialGunC;
import logic.kmap.Frame;
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

		addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				if (arg0.getButton() == 1) {
					// System.out.println("release");
					InputUtility.setMouseLeftUpTriggered(true);
					InputUtility.setMouseLeftDown(false);
				}
			}

			public void mousePressed(MouseEvent arg0) {
				if (arg0.getButton() == 1)
					// System.out.println("press");
					InputUtility.setMouseLeftDownTriggered(true);
			}

			public void mouseExited(MouseEvent arg0) {
				InputUtility.setMouseOnScreen(false);
			}

			public void mouseEntered(MouseEvent arg0) {
				InputUtility.setMouseOnScreen(true);
			}

			public void mouseClicked(MouseEvent arg0) {
			}
		});

		addMouseMotionListener(new MouseMotionListener() {

			public void mouseMoved(MouseEvent e) {
				if (InputUtility.isMouseOnScreen()) {
					InputUtility.setMouseX(e.getX());
					InputUtility.setMouseY(e.getY());
				}
			}

			public void mouseDragged(MouseEvent e) {
				if (InputUtility.isMouseOnScreen()) {
					// System.out.println("hold");
					InputUtility.setMouseX(e.getX());
					InputUtility.setMouseY(e.getY());
					InputUtility.setMouseLeftDown(true);
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (!data.getKmap().isRun())
			return;

		// background

		g2.setComposite(transcluentWhite);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, 894, 620);
		g2.setComposite(opaque);

		Font f = g2.getFont();

		// code for count down

		if (data.getRemainWaitingTime() > 0) {

			int number = data.getRemainWaitingTime() / 100 + 1;
			Font font = new Font("MS Sans Serif", Font.BOLD, 200);
			g2.setFont(font);
			g2.setColor(Color.WHITE);
			FontMetrics metrics = g2.getFontMetrics(font);
			Rectangle2D rect = metrics.getStringBounds("" + number, g2);
			g2.drawString(
					"" + number,
					(ConfigurableOption.WINDOW_WIDTH - (int) rect.getWidth()) / 2,
					(ConfigurableOption.WINDOW_HEIGHT - (int) rect.getHeight()) / 2 + 200);
			return;
		}

		// title

		Font font = new Font("MS Sans Serif", Font.BOLD, 50);
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		FontMetrics metrics = g2.getFontMetrics(font);
		Rectangle2D rect = metrics.getStringBounds("K-MAP Minigame !", g2);
		g2.drawString("K-MAP Minigame !",
				(ConfigurableOption.WINDOW_WIDTH - (int) rect.getWidth()) / 2,
				100);

		// time counter

		int time = data.getKmap().getTime();
		g2.setColor(Color.WHITE);
		g2.drawRect(50, 530, 794, 12);
		if ((double) time / ConfigurableOption.MAX_KMAP_TIME > 2.0 / 3)
			g2.setColor(Color.GREEN);
		else if ((double) time / ConfigurableOption.MAX_KMAP_TIME > 1.0 / 3)
			g2.setColor(Color.YELLOW);
		else
			g2.setColor(Color.RED);
		g2.fillRect(51, 531, time * 793 / ConfigurableOption.MAX_KMAP_TIME, 11);

		// remaining frame

		int remainFrame = data.getKmap().getRemainFrame();
		g2.drawImage(DrawingUtility.kmap_frame, null, 698, 250);
		font = new Font("MS Sans Serif", Font.BOLD, 30);
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		g2.drawString("x " + remainFrame, 780, 290);

		// correct k-map

		int score = data.getKmap().getScore();
		g2.drawImage(DrawingUtility.kmap_score, null, 660, 320);
		font = new Font("MS Sans Serif", Font.BOLD, 30);
		g2.setFont(font);
		g2.setColor(Color.WHITE);
		g2.drawString("x " + score, 780, 375);

		// k-map

		g2.drawImage(DrawingUtility.kmap_background, null, 287, 150);

		g2.setFont(f);
		g2.setColor(Color.BLACK);
		data.getKmap().render(g2);
		ArrayList<Frame> list = data.getKmapList();
		for (int i = 0; i < list.size(); i++)
			list.get(i).render(g2);
		data.getTemp().render(g2);

		if(WindowManager.getStatus()==WindowManager.MINIGAME_STATUS)
			return;
		
		// next gun

		int remainToGun = data.getKmap().getRemainToNextGun();
		Gun nextGun = data.getKmap().getNextGun();
		BufferedImage nextGunImg;
		if (nextGun instanceof NormalGun)
			nextGunImg = DrawingUtility.binarySwitch0;
		else if (nextGun instanceof SpecialGunA)
			nextGunImg = DrawingUtility.pushButton0;
		else if (nextGun instanceof SpecialGunB)
			nextGunImg = DrawingUtility.oneShot;
		else if (nextGun instanceof SpecialGunC)
			nextGunImg = DrawingUtility.hexKeyboard;
		else
			nextGunImg = null;

		font = new Font("MS Sans Serif", Font.BOLD, 30);
		g2.setFont(font);
		g2.setColor(Color.WHITE);

		if (nextGunImg == null) {
			nextGunImg = DrawingUtility.hexKeyboard;
			g2.drawString("You got ", 660, 455);
		} else {
			g2.drawString(remainToGun + " x for ", 680, 455);
		}

		double w = nextGunImg.getWidth();
		double h = nextGunImg.getHeight();

		if (w > h) {
			h *= 45 / w;
			w = 45;
		} else {
			w *= 45 / h;
			h = 45;
		}

		g2.setColor(Color.WHITE);
		g2.fillRect(787, 417, 51, 51);
		g2.drawImage(nextGunImg, (int) (790 + (45 - w) / 2),
				(int) (420 + (45 - h) / 2), (int) w, (int) h, null);

	}
}
