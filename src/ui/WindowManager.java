package ui;

import javax.swing.*;

import config.InputUtility;
import config.SharedData;
import logic.GameLogic;
import logic.TestLogic;

public class WindowManager implements Runnable{

	private final MenuDialog menuDialog;
	private JPanel currentDialogPanel;

	private final GameWindow gameWindow;
	private JPanel currentWindowPanel;
	
	private static int status;
	private static final int MENU_STATUS = 0;
	private static final int CREDIT_STATUS = 1;
	private static final int HOW_TO_PLAY_STATUS = 2;
	private static final int GAME_STATUS = 3;

	public WindowManager(SharedData data){

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		menuDialog = new MenuDialog();
		gameWindow = new GameWindow();
		status = 0;

	}

	public void run()
	{
		if(status == 0)
			dialogToMenu();
		else if(status == 1)
			dialogToCredit();
		else if(status == 2)
			windowToHowToPlay();
		else
			windowToGame();
			
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

	public void dialogToMenu() {
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
