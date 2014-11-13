import java.awt.Dimension;


public class ConfigurableOption {
	private static int DIALOG_WIDTH  = 482;
	private static int DIALOG_HEIGHT = 411;
	private static int WINDOW_WIDTH  = 800;
	private static int WINDOW_HEIGHT = 600;
	
	private static Dimension dialogDimension = new Dimension(DIALOG_WIDTH, DIALOG_HEIGHT);
	private static Dimension windowDimension = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
	
	public static Dimension getDialogDimension()
	{
		return dialogDimension;
	}
	
	public static Dimension getWindowDimension()
	{
		return windowDimension;
	}
}