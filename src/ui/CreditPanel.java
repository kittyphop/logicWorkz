package ui;
import config.ConfigurableOption;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CreditPanel extends JPanel{
	
	JButton backButton;
	
	public CreditPanel(final MenuDialog menuDialog)
	{
		setPreferredSize(ConfigurableOption.getDialogDimension());
		
		// back button
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				menuDialog.toMenu();
			}
		});
		
		add(new JLabel("credit panel"));
		add(backButton);
	}
}
