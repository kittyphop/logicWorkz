import ui.WindowManager;
import logic.GameLogic;
import config.SharedData;

public class Main {
	public static void main(String[] args) {

		SharedData data = new SharedData();
		(new Thread(new GameLogic(data))).start();
		(new WindowManager(data)).begin();

	}
}
