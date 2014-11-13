package ui;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

import config.ConfigurableOption;

public class MenuPanel extends JPanel {

	JButton newGameButton, minigameButton, howToPlayButton, creditButton,
			resetButton, closeButton;
	JList highscoreList; // JScrollPane ?
	JScrollPane highscorePane;

	public MenuPanel(final MenuDialog menuDialog) {
		setPreferredSize(ConfigurableOption.getDialogDimension());
		setLayout(null);

		// new game button
		newGameButton = new JButton("New Game");
		newGameButton.setBounds(380, 28, 75, 23);
		// add listener

		// minigame button
		minigameButton = new JButton("Minigame");
		minigameButton.setBounds(380, 93, 75, 23);
		// add listener

		// reset button
		resetButton = new JButton("Reset");
		resetButton.setBounds(380, 172, 75, 23);
		// add listener

		// credit button
		creditButton = new JButton("Credit");
		creditButton.setBounds(380, 203, 75, 23);
		creditButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuDialog.toCredit();
			}
		});

		// how to play button
		howToPlayButton = new JButton("How To Play");
		howToPlayButton.setBounds(348, 306, 107, 23);
		howToPlayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuDialog.toHowToPlay();
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
		String[] s = new String[10];
		s[0] = "A\t\t1000";               // cannot use tab ?
		s[1] = "B\t\t800";
		s[2] = "C\t\t700";
		s[3] = "D\t\t600";
		s[4] = "E\t\t500";
		s[5] = "F\t\t400";
		s[6] = "G\t\t300";
		s[7] = "H\t\t150";
		s[8] = "I\t\t100";
		s[9] = "J\t\t50";
		
		highscoreList = new JList(s);
		
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
