package ui;

public class HighScoreRecord implements Comparable<HighScoreRecord> {

	private String name = "";
	private int score = 0;

	public HighScoreRecord(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public HighScoreRecord(String record) throws ScoreParsingException {
		if (record.indexOf(":") == -1)
			throw (new ScoreParsingException(1));
		int colonIndex = record.lastIndexOf(":");
		try {
			this.score = Integer.parseInt(record.substring(colonIndex + 1)
					.trim());
			this.name = record.substring(0, colonIndex).trim();
		} catch (NumberFormatException n) {
			throw (new ScoreParsingException(0));
		}
	}

	public String getRecord() {
		return name.trim() + ":" + score;
	}

	public static String[] defaultRecord() {
		return new String[] { "Adder:15000", "AsciiDisplay:12500", "Mux:10000",
				"PLA:8500", "HexDisplay:7000", "JK-FF:5000", "D-FF:4500",
				"AND:3000", "OR:2500", "NOT:1000" };
	}

	public int compareTo(HighScoreRecord o) {
		if (this.score > o.score)
			return -1;
		if (this.score < o.score)
			return 1;
		return 0;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

}
