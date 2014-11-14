package ui;

import javax.swing.UIManager;

public class Main {
	public static void main(String[] args) {
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
		}catch(Exception e){}
		new GameWindow();
	}
}
