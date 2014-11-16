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

import config.ConfigurableOption;
import config.InputUtility;

public class GamePanel extends JPanel {

	protected static final AlphaComposite transcluentWhite = AlphaComposite
			.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, 1);
	
	PlayPanel playPanel;

	public GamePanel() {
		setPreferredSize(ConfigurableOption.WINDOW_DIMENSION);
		setLayout(null);

		playPanel = new PlayPanel();
		//playPanel.setBounds(15, 95, 717, 356);
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
				if(arg0.getButton()==1)
				{
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
		playPanel.paintComponent(g);
		/*
		//code for repaint current gun and score bar
		
		//code for draw kmap
		if(gameLogic.kmapTime()){
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.WHITE);
			g2.fillRect(0,0,894,620);
			g2.setComposite(opaque);
		 }
		 */
		
		//test
		if(InputUtility.getKeyPressed(KeyEvent.VK_SPACE))
		{
			g2.setComposite(transcluentWhite);
			g2.setColor(Color.BLACK);
			g2.fillRect(0,0,894,620);
			g2.setComposite(opaque);
			g2.drawImage(DrawingUtility.menu_background, null, 100, 100);
		}
	}
}
