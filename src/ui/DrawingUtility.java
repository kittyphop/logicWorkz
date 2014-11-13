package ui;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class DrawingUtility {

	// icon
	
	private static Image getIcon(String directory) {
		ClassLoader loader = DrawingUtility.class.getClassLoader();
		try {
			return (new ImageIcon(loader.getResource(directory)).getImage());
		} catch (Exception e) {
			return null;
		}

	}

	private static Image small_icon = getIcon("res/small_icon.png");
	private static Image large_icon = getIcon("res/large_icon.png");
	public static List<Image> icons;
	
	static {
		icons = new ArrayList<Image>();
		icons.add(small_icon);
		icons.add(large_icon);
	}
	
	// image
	
	private static BufferedImage getImage(String directory) {
		ClassLoader loader = DrawingUtility.class.getClassLoader();
		try {
			return ImageIO.read(loader.getResource(directory));
		} catch (Exception e) {
			return null;
		}
	}
	
	//public static BufferedImage menu_background = getImage("res/menu_background.png");
	public static BufferedImage menu_background = getImage("res/temp.png");
	
	public static BufferedImage game_background = getImage("res/game_background.png");
}