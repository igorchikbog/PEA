package hangman_package;

public class HangmanGameStart {

	private HangmanLogic game;
	private Dictionary dictionary;
	private Scoreboard scoreboard;
	private Player currPlayer;
	private boolean gameInProgress = false;

	public HangmanGameStart() {
		this.game = new HangmanLogic();
		this.dictionary = new Dictionary();
		this.scoreboard = new Scoreboard();
		this.currPlayer = new Player();
	} // HangmanGameStart()

	public int initializeDictionary() {
		if (gameInProgress) {
			return 1;
		} else if (!dictionary.initializeDictionary()) {
			return -1;
		} else if (dictionary.getDictionary().getLength() == 0) {
			return -2;
		} else {
			gameInProgress = true;
			return 1;
		}
	} // initializeDictionary()

	public Player getCurrPlayer() {
		return currPlayer;
	}// getCurrPlayer()

	public void setCurrPlayer(Player currPlayer) {
		this.currPlayer = currPlayer;
	}// setCurrPlayer(Player)

	public boolean isGameInProgress() {
		return gameInProgress;
	}// isGameInProgress()

	public HangmanLogic getGame() {
		return game;
	}// getGame()

	public void setGame(HangmanLogic game) {
		this.game = game;
	}// setGame(HangmanLogic)

	public Dictionary getDictionary() {
		return dictionary;
	}// getDictionary()

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}// setDictionary(Dictionary)

	public Scoreboard getScoreboard() {
		return scoreboard;
	}// getScoreboard()

	public void setScoreboard(Scoreboard scoreboard) {
		this.scoreboard = scoreboard;
	}// setScoreboard(Scoreboard)

	public void addUser(String username) {
		this.currPlayer = new Player(username);
		scoreboard.addUser(currPlayer);
	}// addUser(String)

	public void findUser(String username) {
		this.currPlayer = scoreboard.findUser(username);
	}// findUser(String)

	public boolean retrieveSavedGame() {
		game = new HangmanLogic();
		game = game.retrieveSavedGame();
		return (game != null);
	}// retrieveSavedGame()

	public boolean getNewGame() {
		if (initializeDictionary() == 1) {
			String word = dictionary.getRandomWord();
			if (word == null) {
				return false;
			} else {
				this.game = new HangmanLogic(word, currPlayer);
				currPlayer.incrementTotalGames();
				game.initializeAnswer();
				return true;
			}
		}

		return false;
	}// getNewGame();

	public boolean saveGame(HangmanLogic game) {
		return (dictionary.saveDictionary() && game.saveGame() && scoreboard.saveScoreboard());
	}// saveGame(HangmanLogic)

}// HangmanGameStart class
