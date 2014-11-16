package ui;

import javax.swing.UIManager;

import config.InputUtility;

public class Main {
	public static void main(String[] args) {
		
		WindowManager.runGame();
		
		/*
			GameLogic logic = new GameLogic();
			Windowmanager window = new WindowManager(logic);
			
			logic.start();
			
			while(true)
			{
				repaint();
				logic.update();
				InputUtility.postUpdate();
			}
			
		 */
		
		//test
		
		while(true)
		{
			WindowManager.getCurrentWindowPanel().repaint();
			InputUtility.postUpdate();
		}
	}
}
