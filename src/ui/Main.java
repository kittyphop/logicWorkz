package ui;

import javax.swing.UIManager;

import logic.GameLogic;
import config.InputUtility;

public class Main {
	public static void main(String[] args) {

		WindowManager.runGame();

		/*
		 * GameLogic logic = new GameLogic(); WindowManager.runGame(logic);
		 * 
		 * while(true) { repaint(); logic.update(); InputUtility.postUpdate(); }
		 */

		// test
		/*
		 * while (true) { WindowManager.getCurrentWindowPanel().repaint();
		 * InputUtility.postUpdate(); }
		 */
	}
}
