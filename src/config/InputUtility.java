package config;

public class InputUtility {

	private static volatile int mouseX, mouseY;
	private static volatile boolean mouseLeftDown, mouseOnScreen;
	private static volatile boolean mouseLeftDownTriggered,
			mouseLeftUpTriggered;
	private static volatile boolean[] keyPressed = new boolean[256];
	private static volatile boolean[] keyTriggered = new boolean[256];

	public static int getMouseX() {
		return mouseX;
	}

	public static void setMouseX(int mouseX) {
		InputUtility.mouseX = mouseX;
	}

	public static int getMouseY() {
		return mouseY;
	}

	public static void setMouseY(int mouseY) {
		InputUtility.mouseY = mouseY;
	}

	public static boolean isMouseLeftDown() {
		return mouseLeftDown;
	}

	public static void setMouseLeftDown(boolean mouseLeftDown) {
		InputUtility.mouseLeftDown = mouseLeftDown;
	}

	public static boolean isMouseOnScreen() {
		return mouseOnScreen;
	}

	public static void setMouseOnScreen(boolean mouseOnScreen) {
		InputUtility.mouseOnScreen = mouseOnScreen;
	}

	public static void setMouseLeftDownTriggered(boolean v) {
		InputUtility.mouseLeftDownTriggered = v;
	}

	public static boolean isMouseLeftDownTriggered() {
		return mouseLeftDownTriggered;
	}

	public static void setMouseLeftUpTriggered(boolean v) {
		InputUtility.mouseLeftUpTriggered = v;
	}

	public static boolean isMouseLeftUpTriggered() {
		return mouseLeftUpTriggered;
	}

	public static boolean getKeyPressed(int key) {
		if (key < 0 || key > 255)
			return false;
		return keyPressed[key];
	}

	public static void setKeyPressed(int key, boolean pressed) {
		if (key >= 0 && key <= 255)
			InputUtility.keyPressed[key] = pressed;
	}

	public static boolean getKeyTriggered(int key) {
		if (key < 0 || key > 255)
			return false;
		return keyTriggered[key];
	}

	public static void setKeyTriggered(int key, boolean pressed) {
		if (key >= 0 && key <= 255)
			InputUtility.keyTriggered[key] = pressed;
	}

	public static void postUpdate() {
		mouseLeftDownTriggered = false;
		mouseLeftUpTriggered = false;
		for (int i = 0; i < 256; i++)
			setKeyTriggered(i, false);
	}

	public static void reset() {
		mouseLeftDown = false;
		mouseLeftDownTriggered = false;
		mouseLeftUpTriggered = false;
		for (int i = 0; i < 256; i++) {
			setKeyPressed(i, false);
			setKeyTriggered(i, false);
		}
	}
}
