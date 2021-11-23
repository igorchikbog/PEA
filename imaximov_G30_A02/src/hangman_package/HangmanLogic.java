package hangman_package;

import java.io.Serializable;

public class HangmanLogic implements Serializable {

	private static final long serialVersionUID = 1L;
	private SinglyLinkedList<Character> answerLetters = new SinglyLinkedList<Character>();
	private SinglyLinkedList<Character> guessedLetters = new SinglyLinkedList<Character>();
	private Player player;
	private String answer;
	private int mistakesLeft;
	private char[] interfaceLetters;
	private boolean gameDone = false;

	public HangmanLogic() {
		this.answer = null;
		this.mistakesLeft = 6;
		this.interfaceLetters = null;
		this.player = new Player();
	}// HangmanLogic()

	public HangmanLogic(Player player) {
		this.answer = null;
		this.mistakesLeft = 6;
		this.interfaceLetters = null;
		this.player = player;
	}// HangmanLogic(Player)

	public HangmanLogic(String ans, Player player) {
		this.answer = ans;
		this.mistakesLeft = 6;
		this.interfaceLetters = null;
		this.player = player;
	}// HangmanLogic(String, Player)

	public String getInterfaceLettersString() {
		String interfaceLettersStr = "";
		for (char letter : interfaceLetters) {
			interfaceLettersStr += letter;
			interfaceLettersStr += " ";
		}

		return interfaceLettersStr;
	}// getInterfaceLettersString()

	public void initializeAnswer() {
		interfaceLetters = new char[answer.length()];
		for (int i = answer.length() - 1; i >= 0; i--) {
			answerLetters.add(answer.charAt(i));
			if (Character.isLetter(answer.charAt(i))) {
				interfaceLetters[i] = '_';
			} else {
				interfaceLetters[i] = answer.charAt(i);
			}
		}

	}// initializeAnswer()

	public int checkLetter(String letter) {
		if (validateLetter(letter)) {
			char let = letter.charAt(0);
			for (int i = 0; i < guessedLetters.getLength(); i++) {
				if (let == guessedLetters.getElementAt(i))
					return -2;
			}

			if (!checkForMatchingLetter(let)) {
				guessedLetters.add(let);
				mistakesLeft -= 1;
			}

			if (checkForLose()) {
				gameDone = true;
				unmaskWholeWord();
				return -10;
			} else if (checkForWin()) {
				gameDone = true;
				player.incrementTotalWins();
				return 10;
			}
			return 1;
		} else {
			return -1;
		}
	}// checkLetter(String)

	private boolean checkForLose() {
		return (mistakesLeft == 0);
	}// checkForLose()

	private boolean checkForWin() {
		boolean isEqual = true;

		for (int i = 0; i < answerLetters.getLength(); i++) {
			if (interfaceLetters[i] != answerLetters.getElementAt(i))
				isEqual = false;
		}

		return isEqual;
	}// checkForWin()

	private boolean checkForMatchingLetter(char letter) {
		boolean foundMatch = false;
		for (int i = 0; i < answerLetters.getLength(); i++) {
			if (Character.toLowerCase(letter) == Character.toLowerCase(answerLetters.getElementAt(i))) {
				interfaceLetters[i] = answerLetters.getElementAt(i);
				foundMatch = true;
			}
		}

		return foundMatch;
	}// checkForMatchingLetter(char)

	private boolean validateLetter(String letter) {
		if (letter.length() == 0)
			return false;
		else if (letter.length() > 1)
			return false;
		else if (!Character.isLetter(letter.charAt(0)))
			return false;

		return true;
	}// validateLetter(String)

	public int checkWord(String word) {
		if (word.length() == 0) {
			return -2;
		} else if (word.length() != answerLetters.getLength()) {
			mistakesLeft -= 1;
			return -1;
		} else if (checkForMatchingWord(word)) {
			unmaskWholeWord();
			player.incrementTotalWins();
			gameDone = true;
			return 10;
		} else if (checkForLose()) {
			unmaskWholeWord();
			return -10;
		} else {
			mistakesLeft -= 1;
			return -1;
		}

	}// checkWord(String)

	private boolean checkForMatchingWord(String word) {
		boolean matching = true;
		for (int i = 0; i < answerLetters.getLength(); i++) {
			if (Character.toLowerCase(word.charAt(i)) != Character.toLowerCase(answerLetters.getElementAt(i))) {
				matching = false;
			}
		}
		return matching;
	}// checkForMatchingWord(String)

	public boolean saveGame() {
		GameFile file = new GameFile();
		return (file.saveGame(this));
	}// saveGame()

	public HangmanLogic retrieveSavedGame() {
		GameFile file = new GameFile();
		if (file.deserializeGame())
			return file.getGame();
		else
			return null;
	}// retrieveSavedGame()

	public boolean isSavedGame(String username) {
		GameFile file = new GameFile();
		Player tempUser = new Player(username);
		return (file.deserializeGame() && file.getGame().getUser().equals(tempUser));
	}// isSavedGame(String)

	public int giveHint() {
		for (int i = 0; i < answerLetters.getLength(); i++) {
			if (interfaceLetters[i] != answerLetters.getElementAt(i)) {
				checkForMatchingLetter(answerLetters.getElementAt(i));
				if (checkForWin()) {
					player.incrementTotalWins();
					gameDone = true;
					return 10;
				}
				return 1;
			}
		}
		return -1;
	}// giveHint()

	private void unmaskWholeWord() {
		for (int i = 0; i < answerLetters.getLength(); i++) {
			interfaceLetters[i] = answerLetters.getElementAt(i);
		}
	}// unmaskWholeWord()

	public String getGuessedLettersString() {
		String guessedLettersString = "";
		for (int i = 0; i < guessedLetters.getLength(); i++) {
			guessedLettersString += guessedLetters.getElementAt(i);
			guessedLettersString += " ";
		}

		return guessedLettersString;
	}// getGuessedLettersString()

	public boolean getGameDone() {
		return gameDone;
	}// getGameDone()

	public void setGameDone(boolean gameDone) {
		this.gameDone = gameDone;
	}// setGameDone(boolean)

	public Player getUser() {
		return player;
	}// getUser()

	public void setUser(Player player) {
		this.player = player;
	}// setUser(Player)

	public SinglyLinkedList<Character> getAnswerLetters() {
		return answerLetters;
	}// getAnswerLetters()

	public void setAnswerLetters(SinglyLinkedList<Character> answer) {
		this.answerLetters = answer;
	}// setAnswerLetters(SinglyLinkedList<Character>)

	public char[] getInterfaceLetters() {
		return interfaceLetters;
	}// getInterfaceLetters()

	public void setInterfaceLetters(char[] interfaceLetters) {
		this.interfaceLetters = interfaceLetters;
	}// setInterfaceLetters(char[])

	public int getMistakesLeft() {
		return mistakesLeft;
	}// getMistakesLeft()

	public void setMistakesLeft(int mistakesLeft) {
		this.mistakesLeft = mistakesLeft;
	}// setMistakesLeft(int)

	public SinglyLinkedList<Character> getGuessedLetters() {
		return guessedLetters;
	}// getGuessedLetters()

	public void setGuessedLetters(SinglyLinkedList<Character> guessedLetters) {
		this.guessedLetters = guessedLetters;
	}// getGuessedLetters(SinglyLinkedList<Character>)

	public String getAnswer() {
		return answer;
	}// getAnswer()

	public void setAnswer(String answer) {
		this.answer = answer;
	}// setAnswer(String)

}// HangmanLogic class
