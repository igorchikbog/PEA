package hangman_package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ScoreboardFile {

	private static final String FILE_NAME = "scoreboard.ser";
	private Scoreboard scoreboard;

	public ScoreboardFile() {
		scoreboard = new Scoreboard();
	}// ScoreboardFile()

	public boolean deserializeScoreboard() {

		boolean scoreboardDeserialized;
		try {
			FileInputStream inStream = new FileInputStream(FILE_NAME);
			ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
			scoreboard = (Scoreboard) objectInputFile.readObject();
			scoreboardDeserialized = true;

			objectInputFile.close();
		} catch (FileNotFoundException e) {
			 scoreboardDeserialized = false;
		} catch (IOException e) {
			 scoreboardDeserialized = false;
		} catch (ClassNotFoundException e) {
			 scoreboardDeserialized = false;
		}

		return scoreboardDeserialized;
	}// deserializeScoreboard()

	public boolean saveScoreboard(Scoreboard scoreboard) {

		boolean scoreboardSerialized;

		try {
			FileOutputStream outStream = new FileOutputStream(FILE_NAME);
			ObjectOutputStream outputFile = new ObjectOutputStream(outStream);
			outputFile.writeObject(scoreboard);
			scoreboardSerialized = true;
			outputFile.close();
		} catch (FileNotFoundException e) {
			scoreboardSerialized = false;
		} catch (IOException e) {
			scoreboardSerialized = false;
		}

		return scoreboardSerialized;
	}// saveScoreboard(Scoreboard)

	public Scoreboard getScoreboard() {
		return this.scoreboard;
	}// getScoreboard()
}// ScoreboardFile class
