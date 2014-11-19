package ui;

import javax.swing.JDialog;

public class MenuDialog extends JDialog {

	public MenuDialog() {

		setAlwaysOnTop(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	public void dispose() {
		System.exit(0);
	}
}
