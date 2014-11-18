package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import logic.GameLogic;
import logic.gun.Gun;
import logic.gun.NormalGun;
import logic.gun.SpecialGunA;
import logic.gun.SpecialGunB;
import config.ConfigurableOption;
import config.InputUtility;
import config.SharedData;

public class GamePanel extends JPanel {

	protected static final AlphaComposite transcluentWhite = AlphaComposite
			.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 1);

	PlayPanel playPanel;
	SharedData data;

	public GamePanel(SharedData data) {

		this.data = data;

		setPreferredSize(ConfigurableOption.WINDOW_DIMENSION);
		setLayout(null);

		playPanel = new PlayPanel(data);
		playPanel.setBounds(15, 95, 717, 356);
		add(playPanel);

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				InputUtility.setKeyPressed(arg0.getKeyCode(), false);

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (!InputUtility.getKeyPressed(arg0.getKeyCode()))
					InputUtility.setKeyTriggered(arg0.getKeyCode(), true);
				InputUtility.setKeyPressed(arg0.getKeyCode(), true);

			}
		});

		addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent arg0) {
				InputUtility.setMouseLeftDown(false);

			}

			public void mousePressed(MouseEvent arg0) {
				if (arg0.getButton() == 1) {
					InputUtility.setMouseLeftDown(true);
				}
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
					InputUtility.setMouseX(e.getX());
					InputUtility.setMouseY(e.getY());
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(DrawingUtility.game_background, null, 0, 0);

		if (data == null)
			return;

		// if(data.getPlayer().isGameOver())
		// return;

		// score

		int score = data.getPlayer().getScore();
		String scoreText = "SCORE : " + score;
		Font font = new Font("MS Sans Serif", Font.BOLD, 30);
		g2.setFont(font);
		g2.setColor(Color.BLACK);
		g2.drawString(scoreText, 35, 537);

		// current gun

		Gun currentGun = data.getPlayer().getCurrentGun();
		BufferedImage gun_img = DrawingUtility.side_binary_switch;
		if (currentGun instanceof SpecialGunA)
			gun_img = DrawingUtility.side_push_button;
		if (currentGun instanceof SpecialGunB)
			gun_img = DrawingUtility.side_hex_keyboard;
		g2.drawImage(gun_img, null, 756, 98);

		// bullet amount

		int bulletAmount = data.getPlayer().getCurrentGun().getBulletAmount();
		if (currentGun instanceof NormalGun)
			bulletAmount = 99;
		font = new Font("MS Sans Serif", Font.PLAIN, 20);
		g2.setFont(font);
		g2.setColor(Color.BLACK);
		g2.drawString("Bullet : " + bulletAmount, 778, 245);

		// collected probe

		font = new Font("MS Sans Serif", Font.BOLD, 30);
		g2.setFont(font);
		g2.setColor(Color.BLACK);
		g2.drawString("PROBE : ", 260, 537);

		boolean[] probe = data.getPlayer().getCollectedProbe();
		for (int i = 0; i <= 3; i++) {
			int isCollect = 0;
			if (probe[i])
				isCollect = 1;
			g2.drawImage(DrawingUtility.probe_array[i][isCollect], null,
					400 + 40 * i, 507);
		}

		// time counter
		
		int time = data.getPlayer().getTime();
		g2.setColor(Color.BLACK);
		g2.drawRect(28, 555, 854, 12);
		if ((double) time / ConfigurableOption.MAX_TIME > 0.6)
			g2.setColor(Color.GREEN);
		else if ((double) time / ConfigurableOption.MAX_TIME > 0.3)
			g2.setColor(Color.YELLOW);
		else
			g2.setColor(Color.RED);
		g2.fillRect(29, 556, time * 853 / ConfigurableOption.MAX_TIME, 11);

		// level mission

		int level = data.getPlayer().getLevel();
		font = new Font("MS Sans Serif", Font.BOLD, 30);
		g2.setFont(font);
		g2.setColor(Color.BLACK);
		g2.drawString("MISSION : " + level + " x", 610, 537);

		// k-map

		if (InputUtility.getKeyPressed(KeyEvent.VK_K)) {
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, 894, 620);
			g2.setComposite(opaque);
			g2.drawImage(DrawingUtility.kmap_background, null, 287, 150);
		}

	}
}
