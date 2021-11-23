package hangman_package;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameFile {

	private static final String FILE_NAME = "game.ser";
	private HangmanLogic game;

	public GameFile() {
		game = new HangmanLogic();
	}// GameFile()

	public boolean deserializeGame() {

		boolean gameDeserialized;
		try {
			FileInputStream inStream = new FileInputStream(FILE_NAME);
			ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
			game = (HangmanLogic) objectInputFile.readObject();
			gameDeserialized = true;

			objectInputFile.close();
		} catch (FileNotFoundException e) {
			gameDeserialized = false;
		} catch (IOException e) {
			gameDeserialized = false;
		} catch (ClassNotFoundException e) {
			gameDeserialized = false;
		}

		return gameDeserialized;
	}// deserializeGame()

	public boolean saveGame(HangmanLogic game) {

		boolean gameSerialized;

		try {
			FileOutputStream outStream = new FileOutputStream(FILE_NAME);
			ObjectOutputStream outputFile = new ObjectOutputStream(outStream);
			outputFile.writeObject(game);
			gameSerialized = true;
			outputFile.close();
		} catch (FileNotFoundException e) {
			gameSerialized = false;
		} catch (IOException e) {
			gameSerialized = false;
		}

		return gameSerialized;
	}// saveGame(HangmanLogic)

	public HangmanLogic getGame() {
		return this.game;
	}// getGame()

}// GameFile class
