package ui;

import javax.swing.*;

import config.InputUtility;
import logic.GameLogic;
import logic.TestLogic;

public class WindowManager {

	public static final MenuDialog menuDialog;
	private static JPanel currentDialogPanel;

	public static final GameWindow gameWindow;
	private static JPanel currentWindowPanel;

	static {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		menuDialog = new MenuDialog();
		gameWindow = new GameWindow();

	}

	public static void runGame() {

		gameWindow.getContentPane().removeAll();
		currentWindowPanel = new GamePanel(new GameLogic());
		gameWindow.getContentPane().add(currentWindowPanel);
		gameWindow.getContentPane().validate();
		gameWindow.pack();
		currentWindowPanel.requestFocus();

		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);

		dialogToMenu();
		menuDialog.setLocationRelativeTo(null);
		menuDialog.setVisible(true);
	}

	public static void dialogToMenu() {
		menuDialog.setTitle("Welcome to LogicWorkz");
		menuDialog.getContentPane().removeAll();
		currentDialogPanel = new MenuPanel();
		menuDialog.getContentPane().add(currentDialogPanel);
		menuDialog.getContentPane().validate();
		menuDialog.pack();
		currentDialogPanel.requestFocus();
		menuDialog.setVisible(true);
	}

	public static void dialogToCredit() {
		menuDialog.setTitle("About LogicWorkz");

		menuDialog.getContentPane().removeAll();
		currentDialogPanel = new CreditPanel();
		menuDialog.getContentPane().add(currentDialogPanel);
		menuDialog.getContentPane().validate();
		menuDialog.pack();
		currentDialogPanel.requestFocus();
		menuDialog.setVisible(true);
	}

	public static void windowToHowToPlay() {

		menuDialog.setVisible(false);

		gameWindow.getContentPane().removeAll();
		currentWindowPanel = new HowToPlayPanel();
		gameWindow.getContentPane().add(currentWindowPanel);
		gameWindow.getContentPane().validate();
		gameWindow.pack();
		currentWindowPanel.requestFocus();

	}

	public static void windowToGame() {

		GameLogic logic = new GameLogic();

		gameWindow.getContentPane().removeAll();
		currentWindowPanel = new GamePanel(logic);
		gameWindow.getContentPane().add(currentWindowPanel);
		gameWindow.getContentPane().validate();
		gameWindow.pack();
		currentWindowPanel.requestFocus();

		menuDialog.setVisible(false);

		/*
		 * JFrame frame = new JFrame("TestLogic"); TestLogic test = new
		 * TestLogic(logic);
		 * 
		 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 * frame.add(test); frame.pack(); frame.setVisible(true);
		 */

		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			currentWindowPanel.repaint();
			// test.repaint();
			logic.update();
			InputUtility.postUpdate();
		}
	}

	public static void windowToMinigame() {

		// temporary
		windowToHowToPlay();
	}

	public static JPanel getCurrentWindowPanel() {
		return currentWindowPanel;
	}

}
