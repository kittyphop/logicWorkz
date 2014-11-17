

import javax.swing.UIManager;

import ui.WindowManager;
import logic.GameLogic;
import config.InputUtility;
import config.SharedData;

public class Main {
	public static void main(String[] args) {

		SharedData data = new SharedData();
		(new Thread(new WindowManager(data))).start();
		//GameLogic logic = new gameLogic(data);
		//logic.begin();
		
	}
}
