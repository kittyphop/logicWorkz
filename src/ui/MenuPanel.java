package ui;

import highscore.HighScoreUtility;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import config.ConfigurableOption;
import config.SharedData;

public class MenuPanel extends JPanel {

	private JButton newGameButton, minigameButton, howToPlayButton,
			creditButton, resetButton, closeButton;
	private JList highscoreList;
	private JScrollPane highscorePane;
	private SharedData data;

	public MenuPanel(final SharedData data) {

		this.data = data;

		setPreferredSize(ConfigurableOption.DIALOG_DIMENSION);
		setLayout(null);

		// new game button
		newGameButton = new JButton("New Game");
		newGameButton.setBounds(348, 28, 107, 23);
		newGameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WindowManager.setStatus(WindowManager.GAME_STATUS);
				data.getPlayer().setGameOver(false);
			}
		});

		// minigame button
		minigameButton = new JButton("Minigame");
		minigameButton.setBounds(348, 93, 107, 23);
		minigameButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WindowManager.setStatus(WindowManager.MINIGAME_STATUS);
				data.setRemainWaitingTime();
				data.getKmap().setRun(true);
			}
		});

		// reset button
		resetButton = new JButton("Reset");
		resetButton.setBounds(380, 172, 75, 23);
		resetButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				HighScoreUtility.createDefaultScoreFile();
				WindowManager.setStatus(WindowManager.REFRESH_STATUS);
			}
		});

		// credit button
		creditButton = new JButton("Credit");
		creditButton.setBounds(380, 203, 75, 23);
		creditButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WindowManager.setStatus(WindowManager.CREDIT_STATUS);
			}
		});

		// how to play button
		howToPlayButton = new JButton("How To Play");
		howToPlayButton.setBounds(348, 306, 107, 23);
		howToPlayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				WindowManager.setStatus(WindowManager.HOW_TO_PLAY_STATUS);
			}
		});

		// close button
		closeButton = new JButton("Close");
		closeButton.setBounds(380, 367, 75, 23);
		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		// highscore list & Pane

		highscoreList = new JList<String>(HighScoreUtility.listTop10());
		highscoreList.setFont(new Font("Courier New", Font.PLAIN, 15));

		highscorePane = new JScrollPane();
		highscorePane.setViewportView(highscoreList);
		highscorePane.setBounds(26, 169, 336, 95);

		add(newGameButton);
		add(minigameButton);
		add(resetButton);
		add(creditButton);
		add(howToPlayButton);
		add(closeButton);
		add(highscorePane);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(DrawingUtility.menu_background, null, 0, 0);

	}
}
