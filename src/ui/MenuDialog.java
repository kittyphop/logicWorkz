package ui;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class MenuDialog extends JDialog{
	
	JPanel currentPanel;
		 
	public MenuDialog()
	{
		setTitle("Welcome to LogicWorkz");
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		currentPanel = new MenuPanel(this);
		getContentPane().add(currentPanel);
		getContentPane().validate();
		pack();
		setLocationRelativeTo(null);
		currentPanel.requestFocus();
		setVisible(true);
	}
	
	public void dispose()
	{
		System.exit(0);
	}
	
	public void toMenu()
	{
		getContentPane().removeAll();
		currentPanel = new MenuPanel(this);
		getContentPane().add(currentPanel);
		getContentPane().validate();
		pack();
		currentPanel.requestFocus();
	}
	
	public void toHowToPlay()
	{
		getContentPane().removeAll();
		currentPanel = new HowToPlayPanel(this);
		getContentPane().add(currentPanel);
		getContentPane().validate();
		pack();
		currentPanel.requestFocus();
		
	}
	
	public void toCredit()
	{
		getContentPane().removeAll();
		currentPanel = new CreditPanel(this);
		getContentPane().add(currentPanel);
		getContentPane().validate();
		pack();
		currentPanel.requestFocus();
	}
}
