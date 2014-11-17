package ui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import logic.GameLogic;
import config.ConfigurableOption;
import config.InputUtility;

public class GamePanel extends JPanel {

	protected static final AlphaComposite transcluentWhite = AlphaComposite
			.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 1);

	PlayPanel playPanel;
	GameLogic logic;

	public GamePanel(GameLogic logic) {

		this.logic = logic;

		setPreferredSize(ConfigurableOption.WINDOW_DIMENSION);
		setLayout(null);

		playPanel = new PlayPanel(logic);
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
		playPanel.repaint();

		/*
		 * 
		 * //code for draw current gun
		 * 
		 * Gun currentGun = logic.getPlayer().getCurrentGun(); BufferedImage
		 * gun_img; if(currentGun instanceOf GunA) gun_img =
		 * DrawingUtility.binary_switch; else if(currentGun instanceOf GunB)
		 * gun_img = DrawingUtility.push_button; else gun_img =
		 * DrawingUtility.hex_keyboard;
		 * 
		 * g2.drawImage(gun_img,null,300,200); // edit x and y here
		 * 
		 * //code for draw score
		 * 
		 * int score = logic.getPlayer().getScore(); g2.drawString(""+score,
		 * 400, 50); // edit x and y here
		 * 
		 * //code for draw collected probe
		 * 
		 * boolean[] probe = logic.getPlayer().getCollectedProbe();
		 * 
		 * for(int i=0;i<=3;i++) { int isCollect = 0; if(probe[i]) isCollect =
		 * 1; BufferedImage probe_img = DrawingUtility.probe[i][isCollect];
		 * g2.drawImage(probe_img, null, 350, 150 + 50*i); // edit x and y here
		 * 
		 * }
		 * 
		 * // code for draw time counter ...
		 * 
		 * //code for draw level mission
		 * 
		 * int level = logic.getPlayer().getLevel(); ...
		 * 
		 * 
		 * //code for draw kmap
		 * 
		 * if(gameLogic.kmapTime()){ g2.setComposite(transcluentWhite);
		 * g2.setColor(Color.WHITE); g2.fillRect(0,0,894,620);
		 * g2.setComposite(opaque); }
		 */

		// test

		if (logic.getPlayer().isPause()) {
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.BLACK);
			g2.fillRect(0, 0, 894, 620);
			g2.setComposite(opaque);
			g2.drawImage(DrawingUtility.menu_background, null, 100, 100);
		}
	}
}
