package logic;

public class Probe implements ICollectible {

	private String letter;

	public Probe(String letter) {
		this.letter = letter;
	}

	public String getLetter() {
		return letter;
	}

	public void collect(Player player) {
		player.collectNewProbe(this);
	}

	public void update() {

	}

}
