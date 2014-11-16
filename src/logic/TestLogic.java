package logic;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class TestLogic extends JPanel {

	GameLogic gameLogic;

	public TestLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		this.setSize(400, 400);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("TestLogic");
		// GameLogic logic = new GameLogic();
		// TestLogic test = new TestLogic(logic);

		JPanel panel = new JPanel() {
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				new Probe(0, 0, "K").render((Graphics2D) g);
			}
		};
		panel.setPreferredSize(new Dimension(400, 400));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

}
