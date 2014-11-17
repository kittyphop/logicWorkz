package ui;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import config.ConfigurableOption;

public class HowToPlayPanel extends JPanel{
	
	JButton backButton;
	
	public HowToPlayPanel()
	{
		setPreferredSize(ConfigurableOption.WINDOW_DIMENSION);
		
		// back button
		backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				WindowManager.setStatus(WindowManager.MENU_STATUS);
			}
		});
		
		add(new JLabel("how to play panel"));
		add(backButton);
	}
}
