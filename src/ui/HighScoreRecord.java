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
		return new String[] { "A:800", "B:350", "C:300", "D:250", "E:200",
				"F:100", "G:40", "H:30", "I:20", "J:10" };
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
