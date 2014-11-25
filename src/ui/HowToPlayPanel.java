package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import config.ConfigurableOption;

public class HowToPlayPanel extends JPanel {

	private int page;

	public HowToPlayPanel() {

		page = 0;

		setPreferredSize(ConfigurableOption.WINDOW_DIMENSION);

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				page++;
				if (page == 2) {
					page = 0;
					WindowManager.setStatus(WindowManager.MENU_STATUS);
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});

	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if (page == 0)
			g2.drawImage(DrawingUtility.how_to_play_0, null, 0, 0);
		else
			g2.drawImage(DrawingUtility.how_to_play_1, null, 0, 0);

	}
}
