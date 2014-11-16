package logic;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import logic.monster.IC74163;

@SuppressWarnings("serial")
public class TestLogic extends JPanel {

	GameLogic gameLogic;

	public TestLogic(GameLogic gameLogic) {
		this.gameLogic = gameLogic;
		this.setPreferredSize(new Dimension(400, 400));
		setDoubleBuffered(true);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("TestLogic");
		final GameLogic logic = new GameLogic();
		TestLogic test = new TestLogic(logic);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(test);
		frame.pack();
		frame.setVisible(true);

		while (true) {
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
			}
			test.repaint();
			logic.update();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ArrayList<IRenderable> list = gameLogic.getList();
		for (IRenderable i : list) {
			i.render((Graphics2D) g);
		}
	}

}
