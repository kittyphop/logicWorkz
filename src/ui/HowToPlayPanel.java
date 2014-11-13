package ui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HowToPlayPanel extends JPanel{
	
	JButton backButton;
	
	public HowToPlayPanel(final MenuDialog menuDialog)
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
		
		add(new JLabel("how to play panel"));
		add(backButton);
	}
}
