package ui;

import java.io.*;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class HighScoreUtility {

	private static HighScoreRecord[] highScoreRecord = null;

	private static String readFileName = "highscore";

	public static void recordHighScore(int score) {
		if (!loadHighScore() || highScoreRecord == null) {
			JOptionPane.showMessageDialog(null,
					"Error loading highscore record", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		int index = highScoreRecord.length;
		for (int i = 0; i < highScoreRecord.length; i++) {
			if (score > highScoreRecord[i].getScore()) {
				index = i;
				break;
			}
		}
		if (index >= highScoreRecord.length) {
			JOptionPane.showMessageDialog(null, "Game over\nYour score is "
					+ score, "Game over", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String name = JOptionPane.showInputDialog(null,
					"Congratulation, you are ranked " + (index + 1)
							+ "\nPlease enter your name", "High score",
					JOptionPane.INFORMATION_MESSAGE);
			if (name == null)
				return;
			for (int i = highScoreRecord.length - 1; i >= index + 1; i--) {
				highScoreRecord[i] = highScoreRecord[i - 1];
			}
			highScoreRecord[index] = new HighScoreRecord(name, score);
			try {
				PrintStream out = new PrintStream(new File("highscore"));
				String record = "";
				for (int i = 0; i < 10; i++) {
					record += highScoreRecord[i].getRecord() + "\n";
				}
				out.print(getXORed(record));
				out.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Error saving high score record", "Error",
						JOptionPane.ERROR_MESSAGE);
				highScoreRecord = null;
				return;
			}
		}
	}

	public static String[] listTop10() {
		if (!loadHighScore() || highScoreRecord == null) {
			JOptionPane.showMessageDialog(null,
					"Error loading highscore record", "Error",
					JOptionPane.ERROR_MESSAGE);
			return HighScoreRecord.defaultRecord();
		}
		String[] highScoreList = new String[10];
		int rank = 0;
		for (HighScoreRecord record : highScoreRecord) {
			highScoreList[rank] = record.getRecord();
			rank++;
		}
		return highScoreList;
	}

	private static boolean loadHighScore() {
		File f = new File(readFileName);
		// If no high score, create default
		if (!f.exists()) {
			if (!createDefaultScoreFile())
				return false;
		}
		// Read high score -- if fail: delete the file, create default one and
		// read it again
		if (!readAndParseScoreFile(f)) {
			f.delete();
			if (!createDefaultScoreFile())
				return false;
			return readAndParseScoreFile(f);
		}
		return true;

	}

	private static boolean readAndParseScoreFile(File f) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(f));
			highScoreRecord = new HighScoreRecord[10];
			String str = "";
			int c;
			while ((c = in.read()) != -1) {
				str += (char) c;
			}
			in.close();
			String[] records = getXORed(str).split("\n");
			for (int i = 0; i < highScoreRecord.length; i++) {
				try {
					highScoreRecord[i] = new HighScoreRecord(records[i]);
				} catch (ScoreParsingException e) {
					System.err.println("Error parsing line " + (i + 1) + ", "
							+ e.getMessage());
					highScoreRecord[i] = new HighScoreRecord("ERROR_RECORD", 0);
				}
			}
			Arrays.sort(highScoreRecord);
			return true;
		} catch (Exception e) {
			highScoreRecord = null;
		}
		return false;
	}

	public static boolean createDefaultScoreFile() {
		try {
			PrintStream out = new PrintStream(new File("highscore"));
			String str = "";
			for (String s : HighScoreRecord.defaultRecord()) {
				str += s + "\n";
			}
			str = str.trim();
			out.print(getXORed(str));
			out.close();
			return true;
		} catch (IOException e1) {
			highScoreRecord = null;
			return false;
		}
	}

	private static final byte[] key = "RmAAq2b5d8fjgu9dhher".getBytes();

	// This method does both encryption and decryption
	private static String getXORed(String in) {
		byte[] inData = in.getBytes();
		for (int i = 0; i < inData.length; i++) {
			inData[i] ^= key[i % key.length];
		}
		return new String(inData);
	}

	public static void setReadFileName(String name) {
		readFileName = name;
	}
}
