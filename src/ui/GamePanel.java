package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import logic.gun.Gun;
import logic.gun.NormalGun;
import logic.gun.SpecialGunA;
import logic.gun.SpecialGunB;
import config.ConfigurableOption;
import config.InputUtility;
import config.SharedData;

public class GamePanel extends JPanel {

	protected static final AlphaComposite transcluentWhite = AlphaComposite
			.getInstance(AlphaComposite.SRC_OVER, 0.9f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 1);

	PlayPanel playPanel;
	KmapPanel kmapPanel;
	SharedData data;

	public GamePanel(SharedData data) {

		this.data = data;

		setPreferredSize(ConfigurableOption.WINDOW_DIMENSION);
		setLayout(null);

		kmapPanel = new KmapPanel(data);
		kmapPanel.setBounds(0, 0, 894, 620);
		add(kmapPanel);

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
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(DrawingUtility.game_background, null, 0, 0);

		if (WindowManager.getStatus() != WindowManager.GAME_STATUS)
			return;

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
		if ((double) time / ConfigurableOption.MAX_TIME > 2.0 / 3)
			g2.setColor(Color.GREEN);
		else if ((double) time / ConfigurableOption.MAX_TIME > 1.0 / 3)
			g2.setColor(Color.YELLOW);
		else
			g2.setColor(Color.RED);
		g2.fillRect(29, 556, time * 853 / ConfigurableOption.MAX_TIME, 11);

		// level mission

		int level = data.getPlayer().getLevel();
		int remain_mon = data.getPlayer().getLevel();
		font = new Font("MS Sans Serif", Font.BOLD, 30);
		g2.setFont(font);
		g2.setColor(Color.BLACK);
		if (level <= 10)
			g2.drawString("LEVEL " + level + " : ", 610, 537);
		else
			g2.drawString("BOSS : ", 610, 537);
		g2.drawString("x " + remain_mon, 820, 537);

		BufferedImage mon;
		if (level == 1)
			mon = DrawingUtility.not;
		else if (level == 2)
			mon = DrawingUtility.or;
		else if (level == 3)
			mon = DrawingUtility.and;
		else if (level == 4)
			mon = DrawingUtility.dFF;
		else if (level == 5)
			mon = DrawingUtility.jkFF;
		else if (level == 6)
			mon = DrawingUtility.hexDisplay;
		else if (level == 7)
			mon = DrawingUtility.pla;
		else if (level == 8)
			mon = DrawingUtility.mux;
		else if (level == 9)
			mon = DrawingUtility.asciiDisplay;
		else if (level == 10)
			mon = DrawingUtility.adder;
		else
			mon = DrawingUtility.ic74163;

		double w = mon.getWidth();
		double h = mon.getHeight();

		if (w > h) {
			h *= 45 / w;
			w = 45;
		} else {
			w *= 45 / h;
			h = 45;
		}
		
		g2.drawImage(mon, (int) (760 + (45 - w) / 2),
				(int) (505 + (45 - h) / 2), (int) w, (int) h, null);

	}
}
