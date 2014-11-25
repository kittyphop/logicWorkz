/**
 * @author Kittiphop Phalakarn (5631009821) 
 * @version 17 Oct 2014
 * Lab4 (1/2014) in 2110215 Prog Meth
 */

package highscore;

public class ScoreParsingException extends Exception {

	private int errorType;

	public ScoreParsingException(int errorType) {
		this.errorType = errorType;
	}

	public String getMessage() {
		if (errorType == 0)
			return "No record score";
		if (errorType == 1)
			return "Wrong record format";
		return "";
	}
}
