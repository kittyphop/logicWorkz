package ui;

import javax.swing.*;

import config.SharedData;

public class WindowManager {

	private final MenuDialog menuDialog;
	private JPanel currentDialogPanel;

	private final GameWindow gameWindow;
	private JPanel currentWindowPanel;

	private final SharedData data;

	private static int status;
	public static final int MENU_STATUS = 0;
	public static final int CREDIT_STATUS = 1;
	public static final int HOW_TO_PLAY_STATUS = 2;
	public static final int GAME_STATUS = 3;
	public static final int MINIGAME_STATUS = 4;

	public WindowManager(SharedData data) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		menuDialog = new MenuDialog();
		gameWindow = new GameWindow();
		initialize();

		this.data = data;
		status = MENU_STATUS;
	}

	public void begin() {

		while (true) {

			try {
				Thread.sleep(20);
			} catch (Exception e) {
			}

			// debug
			// System.out.println(status);

			if (status == MENU_STATUS
					&& (!menuDialog.isVisible() || !(currentDialogPanel instanceof MenuPanel)))
				dialogToMenu();
			else if (status == CREDIT_STATUS
					&& (!menuDialog.isVisible() || !(currentDialogPanel instanceof CreditPanel)))
				dialogToCredit();
			else if (status == HOW_TO_PLAY_STATUS
					&& (menuDialog.isVisible() || !(currentWindowPanel instanceof HowToPlayPanel)))
				windowToHowToPlay();
			else if (status == GAME_STATUS
					&& (menuDialog.isVisible() || !(currentWindowPanel instanceof GamePanel)))
				windowToGame();

			// correct for minigamePanel

			else if (status == MINIGAME_STATUS
					&& (menuDialog.isVisible() || !(currentWindowPanel instanceof HowToPlayPanel)))
				windowToGame();

			currentDialogPanel.repaint();
			currentWindowPanel.repaint();
		}
	}

	public static void setStatus(int status) {
		WindowManager.status = status;
	}

	public static int getStatus() {
		return status;
	}

	public void initialize() {
		currentWindowPanel = new GamePanel(data);
		gameWindow.getContentPane().add(currentWindowPanel);
		gameWindow.getContentPane().validate();
		gameWindow.pack();
		currentWindowPanel.requestFocus();
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setVisible(true);

		menuDialog.setTitle("Welcome to LogicWorkz");
		currentDialogPanel = new MenuPanel();
		menuDialog.getContentPane().add(currentDialogPanel);
		menuDialog.getContentPane().validate();
		menuDialog.pack();
		currentDialogPanel.requestFocus();
		menuDialog.setLocationRelativeTo(null);
		menuDialog.setVisible(true);
	}

	public void dialogToMenu() {

		gameWindow.getContentPane().removeAll();
		currentWindowPanel = new GamePanel(data);
		gameWindow.getContentPane().add(currentWindowPanel);
		gameWindow.getContentPane().validate();
		gameWindow.pack();
		currentWindowPanel.requestFocus();
		gameWindow.setVisible(true);

		menuDialog.setTitle("Welcome to LogicWorkz");
		menuDialog.getContentPane().removeAll();
		currentDialogPanel = new MenuPanel();
		menuDialog.getContentPane().add(currentDialogPanel);
		menuDialog.getContentPane().validate();
		menuDialog.pack();
		currentDialogPanel.requestFocus();
		menuDialog.setVisible(true);
	}

	public void dialogToCredit() {
		menuDialog.setTitle("About LogicWorkz");

		menuDialog.getContentPane().removeAll();
		currentDialogPanel = new CreditPanel();
		menuDialog.getContentPane().add(currentDialogPanel);
		menuDialog.getContentPane().validate();
		menuDialog.pack();
		currentDialogPanel.requestFocus();
		menuDialog.setVisible(true);
	}

	public void windowToHowToPlay() {

		gameWindow.getContentPane().removeAll();
		currentWindowPanel = new HowToPlayPanel();
		gameWindow.getContentPane().add(currentWindowPanel);
		gameWindow.getContentPane().validate();
		gameWindow.pack();
		currentWindowPanel.requestFocus();
		menuDialog.setVisible(false);

	}

	public void windowToGame() {

		gameWindow.getContentPane().removeAll();
		currentWindowPanel = new GamePanel(data);
		gameWindow.getContentPane().add(currentWindowPanel);
		gameWindow.getContentPane().validate();
		gameWindow.pack();
		currentWindowPanel.requestFocus();
		menuDialog.setVisible(false);
	}

	public void windowToMinigame() {

		// temporary
		windowToHowToPlay();
	}
}
