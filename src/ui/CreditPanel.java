package ui;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import config.ConfigurableOption;

public class CreditPanel extends JPanel{
	
	JButton backButton;
	
	public CreditPanel()
	{
		setPreferredSize(ConfigurableOption.DIALOG_DIMENSION);
		setLayout(null);
		
		// back button
		backButton = new JButton("Back");
		backButton.setBounds(203, 367, 76, 23);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				WindowManager.setStatus(WindowManager.MENU_STATUS);
			}
		});
		
		add(backButton);
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(DrawingUtility.credit_background, null, 0, 0);
	}
}
