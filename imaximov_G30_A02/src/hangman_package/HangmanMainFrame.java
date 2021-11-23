package hangman_package;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class HangmanMainFrame extends JFrame implements ActionListener, WindowListener {

	private static final long serialVersionUID = 1L;
	private HangmanGameStart hangmanGameStart;
	private HangmanLogic game;
	private HangmanDrawerPanel hangmanPanel;

	private JTextField txtFldGuess;
	private JButton btnGuess;
	private JTextPane txtPaneWordDisplay;
	private JTextPane txtPaneGuessedLetters;
	private JTextField fldNumMistakes;
	private JLabel lblYourGuess;
	private JLabel lblMistakesLeft;

	private JMenuItem mntmScoreboard;
	private JMenuItem mntmSaveGame;
	private JMenuItem mntmNewGame;
	private JMenuItem mntmQuit;
	private JMenuItem mntmHint;

	private HangmanNewUserPanel enterUsernamePanel;
	private int initializeDictionaryOutput;
	private boolean gameInProgress = false;
	private HangmanUserPanel dropDownPanel;
	private JPanel panelGame;
	private JPanel guessedLettersPanel;
	private JLabel lblWelcome;
	private JLabel lblUser;
	private JMenuItem mntmRules;

	public HangmanMainFrame() throws HeadlessException {
		setBackground(new Color(0, 0, 128));
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Hangman");

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(174, 0, 255));
		setJMenuBar(menuBar);

		JMenu mnMenu = new JMenu("MENU");
		mnMenu.setBackground(new Color(255, 255, 255));
		mnMenu.setForeground(new Color(248, 248, 255));
		mnMenu.setFont(new Font("Calibri Light", Font.PLAIN, 13));
		menuBar.add(mnMenu);

		mntmScoreboard = new JMenuItem("Scoreboard");
		mntmScoreboard.setFont(new Font("Calibri", Font.PLAIN, 13));
		mntmScoreboard.setBackground(new Color(255, 255, 255));
		mntmScoreboard.addActionListener(this);
		mnMenu.add(mntmScoreboard);

		mntmSaveGame = new JMenuItem("Save Game");
		mntmSaveGame.setFont(new Font("Calibri", Font.PLAIN, 13));
		mntmSaveGame.setBackground(new Color(255, 255, 255));
		mntmSaveGame.addActionListener(this);
		mnMenu.add(mntmSaveGame);

		mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.setFont(new Font("Calibri", Font.PLAIN, 13));
		mntmNewGame.setBackground(new Color(255, 255, 255));
		mntmNewGame.addActionListener(this);
		mnMenu.add(mntmNewGame);

		mntmHint = new JMenuItem("Give Hint");
		mntmHint.setBackground(new Color(255, 255, 255));
		mntmHint.setFont(new Font("Calibri", Font.PLAIN, 13));
		mntmHint.addActionListener(this);
		mnMenu.add(mntmHint);

		mntmQuit = new JMenuItem("Quit");
		mntmQuit.setFont(new Font("Calibri", Font.PLAIN, 13));
		mntmQuit.setBackground(new Color(255, 255, 255));
		mntmQuit.addActionListener(this);

		mntmRules = new JMenuItem("Rules");
		mntmRules.setBackground(new Color(255, 255, 255));
		mntmRules.setFont(new Font("Calibri", Font.PLAIN, 13));
		mntmRules.addActionListener(this);
		mnMenu.add(mntmRules);
		mnMenu.add(mntmQuit);
		getContentPane().setLayout(null);

		panelGame = new JPanel();
		panelGame.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelGame.setBackground(new Color(255, 255, 255));
		panelGame.setBounds(10, 56, 678, 279);
		getContentPane().add(panelGame);
		panelGame.setLayout(null);

		txtPaneWordDisplay = new JTextPane();
		txtPaneWordDisplay.setBounds(38, 162, 409, 106);
		panelGame.add(txtPaneWordDisplay);
		txtPaneWordDisplay.setEditable(false);
		txtPaneWordDisplay.setFont(new Font("Calibri", Font.PLAIN, 30));

		lblYourGuess = new JLabel("Your Guess:");
		lblYourGuess.setBounds(3, 19, 123, 22);
		panelGame.add(lblYourGuess);
		lblYourGuess.setFont(new Font("Calibri", Font.PLAIN, 17));
		lblYourGuess.setHorizontalAlignment(SwingConstants.RIGHT);

		txtFldGuess = new JTextField();
		txtFldGuess.setBounds(131, 17, 75, 23);
		panelGame.add(txtFldGuess);
		txtFldGuess.setForeground(new Color(0, 0, 0));
		txtFldGuess.setFont(new Font("Calibri", Font.PLAIN, 13));
		txtFldGuess.setColumns(10);

		btnGuess = new JButton("Guess");
		btnGuess.setBounds(208, 18, 124, 23);
		panelGame.add(btnGuess);
		btnGuess.setBackground(new Color(174, 0, 255));
		btnGuess.setFont(new Font("Calibri", Font.PLAIN, 13));
		btnGuess.addActionListener(this);
		btnGuess.setToolTipText("Enter your single letter guess");
		btnGuess.setForeground(new Color(245, 255, 250));

		hangmanPanel = new HangmanDrawerPanel();
		hangmanPanel.setBounds(483, 0, 196, 277);
		panelGame.add(hangmanPanel);

		lblMistakesLeft = new JLabel("Mistakes Left:");
		lblMistakesLeft.setBounds(8, 42, 115, 23);
		panelGame.add(lblMistakesLeft);
		lblMistakesLeft.setFont(new Font("Calibri", Font.PLAIN, 18));
		lblMistakesLeft.setHorizontalAlignment(SwingConstants.RIGHT);

		fldNumMistakes = new JTextField();
		fldNumMistakes.setBounds(131, 40, 76, 23);
		panelGame.add(fldNumMistakes);
		fldNumMistakes.setBackground(Color.WHITE);
		fldNumMistakes.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fldNumMistakes.setEditable(false);
		fldNumMistakes.setColumns(10);

		JLabel lblGuessedLetters = new JLabel("Guessed Letters:");
		lblGuessedLetters.setBounds(2, 66, 126, 23);
		panelGame.add(lblGuessedLetters);
		lblGuessedLetters.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGuessedLetters.setFont(new Font("Calibri", Font.PLAIN, 18));

		guessedLettersPanel = new JPanel();
		guessedLettersPanel.setBounds(133, 65, 357, 51);
		panelGame.add(guessedLettersPanel);
		guessedLettersPanel.setBorder(new LineBorder(null, 0));
		guessedLettersPanel.setBackground(new Color(255, 255, 255));
		guessedLettersPanel.setLayout(null);

		enterUsernamePanel = new HangmanNewUserPanel();
		enterUsernamePanel.setVisible(false);
		enterUsernamePanel.setBounds(173, 56, 394, 269);
		enterUsernamePanel.setFrame(this);
		getContentPane().add(enterUsernamePanel);

		dropDownPanel = new HangmanUserPanel();
		dropDownPanel.setFrame(this);
		dropDownPanel.setVisible(false);
		dropDownPanel.setBounds(184, 56, 384, 269);
		getContentPane().add(dropDownPanel);

		lblWelcome = new JLabel("Welcome To Hangman");
		lblWelcome.setFont(new Font("Calibri", Font.BOLD, 22));
		lblWelcome.setBounds(31, 11, 211, 34);
		getContentPane().add(lblWelcome);

		lblUser = new JLabel("");
		lblUser.setFont(new Font("Calibri", Font.BOLD, 22));
		lblUser.setBounds(245, 11, 196, 34);
		getContentPane().add(lblUser);

		txtPaneGuessedLetters = new JTextPane();
		txtPaneGuessedLetters.setBounds(129, 326, 559, 33);
		getContentPane().add(txtPaneGuessedLetters);
		txtPaneGuessedLetters.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtPaneGuessedLetters.setEditable(false);

		addWindowListener(this);
	}// HangmanMainFrame()

	public static void main(String[] args) {
		HangmanMainFrame frame = new HangmanMainFrame();
		frame.setSize(711, 410);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.toggleGame(false);
	}// main(String[])

	private void initializeWindowActions() {
		gameInProgress = true;
		getRootPane().setDefaultButton(btnGuess);
	}// initializeWindowActions()

	public void toggleGame(boolean show) {
		hangmanPanel.setVisible(show);
		panelGame.setVisible(show);
		guessedLettersPanel.setVisible(show);
		fldNumMistakes.setVisible(show);
		lblMistakesLeft.setVisible(show);
		mntmSaveGame.setEnabled(show);
		mntmNewGame.setEnabled(show);
		mntmHint.setEnabled(show);
	}// toggleGame(boolean)

	public void displayUserDropDown() {
		String[] usernames = hangmanGameStart.getScoreboard().retrieveUserNames();
		dropDownPanel.setUsernames(usernames);
		dropDownPanel.setVisible(true);
	}// displayUserDropDown()

	public void displayEnterUsername() {
		if (initializeDictionaryOutput == 1) {
			enterUsernamePanel.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "Sorry there are no more words left!", "Out of Words To Play",
					JOptionPane.ERROR_MESSAGE);
		}
	}// displayEnterUsername()

	public void startGameAs(String username) {
		initializeWindowActions();
		hangmanGameStart.findUser(username);
		if (hangmanGameStart.getGame().isSavedGame(username))
			displayContinueSavedGame();
		else
			newGame();
	}// startGameAs()

	public boolean enterUsername(String username) {
		if (!username.isEmpty()) {
			if (hangmanGameStart.getScoreboard().findUser(username) != null) {
				return (displayWarning(username));

			} else {
				hangmanGameStart.addUser(username);
				initializeWindowActions();
				newGame();
				return true;
			}
		} else {
			JOptionPane.showMessageDialog(this, "You must enter a username to submit.", "No Username Entered",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}// enterUsername()

	private boolean displayWarning(String username) {
		String dialog = "The username: " + username + " is already taken.\nDo you want to continue as " + username
				+ "?\n";
		String title = "Username Taken";

		int choice = JOptionPane.showConfirmDialog(this, dialog, title, JOptionPane.YES_NO_OPTION);
		if (choice == 0) {
			startGameAs(username);
			return true;
		} else {
			return false;
		}

	}// displayWarning(String)

	private void displayContinueSavedGame() {
		String dialog = "You have a saved game on file.\nDo you want to continue where you left off?";
		String title = "Saved Game Available";
		int dictionaryExists;

		int choice = JOptionPane.showConfirmDialog(this, dialog, title, JOptionPane.YES_NO_OPTION);
		if (choice == 0) {
			if (hangmanGameStart.retrieveSavedGame() && !hangmanGameStart.getGame().getGameDone()) {
				game = hangmanGameStart.getGame();
				dictionaryExists = hangmanGameStart.initializeDictionary();
				if (dictionaryExists == 1) {
					toggleGame(true);
					lblUser.setText(game.getUser().getUsername());
					updateGame();
				} else {
					JOptionPane.showMessageDialog(this, "There are no more words left!", "Out Of Words",
							JOptionPane.ERROR_MESSAGE);
					gameInProgress = false;
				}
			} else {
				JOptionPane.showMessageDialog(this, "Your saved game is finished! A new game will be started for you.");
				newGame();
			}
		} else {
			newGame();
		}

	}// displayContinueSavedGame()

	private void displayCheckLetterOutput() {
		int checkLetterOutput = game.checkLetter(txtFldGuess.getText());

		if (checkLetterOutput == -1) {
			JOptionPane.showMessageDialog(this,
					"Your input was invalid.\nYour input must be a single letter guess.\nTo guess the whole phrase press the\n"
							+ "Guess whole word button.");
		} else if (checkLetterOutput == -2) {
			JOptionPane.showMessageDialog(this,
					"This letter was already guessed.\nTo see the guessed letter please refer to the bottom of the frame.");
		} else if (checkLetterOutput == -10) {
			gameOver();
		} else if (checkLetterOutput == 10) {
			gameWon();
		} else {
			updateGame();
		}
	}// displayCheckLetterOutput()

	public void toggleGameEnabled(boolean enable) {
		hangmanPanel.setEnabled(enable);
		txtFldGuess.setEnabled(enable);
		btnGuess.setEnabled(enable);
		txtPaneWordDisplay.setEnabled(enable);
	}// toggleGameEnabled()

	private void gameOver() {
		int choice = JOptionPane.showConfirmDialog(this, "You made too many mistakes!\nWould you like to play again?",
				"Game Over", JOptionPane.YES_NO_OPTION);
		if (choice == 0) {
			newGame();
		} else {
			updateGame();
			toggleGameEnabled(false);
		}
	} // gameOver()

	private void gameWon() {
		// modify user
		int choice = JOptionPane.showConfirmDialog(this, "You won the game!\nWould you like to play again?", "You win!",
				JOptionPane.YES_NO_OPTION);
		if (choice == 0) {
			newGame();
		} else {
			updateGame();
			toggleGameEnabled(false);
		}

	}// gameWon()

	private void newGame() {
		if (hangmanGameStart.getNewGame()) {
			game = hangmanGameStart.getGame();
			lblUser.setText(game.getUser().getUsername());
			toggleGame(true);
			toggleGameEnabled(true);
			enterUsernamePanel.setVisible(false);
			updateGame();
		} else {
			gameInProgress = false;
			toggleGame(false);
			JOptionPane.showMessageDialog(this, "There are no more words left to play!", "Out of Words",
					JOptionPane.ERROR_MESSAGE);
			hangmanGameStart.getDictionary().saveDictionary();
		}
	}// newGame()

	private void updateGame() {
		txtPaneWordDisplay.setText(game.getInterfaceLettersString());
		txtPaneGuessedLetters.setText(game.getGuessedLettersString());
		fldNumMistakes.setText("" + game.getMistakesLeft());
		hangmanPanel.setMistakes(game.getMistakesLeft());
		hangmanPanel.repaint();
	}// updateGame()

	private void displayScoreboard() {
		HangmanScoreboardFrame scoreboardFrame = new HangmanScoreboardFrame();
		scoreboardFrame.setVisible(true);
		scoreboardFrame.setSize(630, 370);
		scoreboardFrame.setLocationRelativeTo(null);
		scoreboardFrame.setVisible(true);
		scoreboardFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		scoreboardFrame.printScores(hangmanGameStart.getScoreboard());
	}// displayScoreboard()

	private void getHint() {
		if (game.giveHint() == 10)
			gameWon();
		else
			updateGame();
	}// getHint()

	public void displayRules() {
		System.out.println("Rules called");
		HangmanRulesFrame rulesFrame = new HangmanRulesFrame();
		rulesFrame.setVisible(true);
		rulesFrame.setSize(445, 375);
		rulesFrame.setLocationRelativeTo(null);
		rulesFrame.setVisible(true);
		rulesFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnGuess) {
			displayCheckLetterOutput();
			txtFldGuess.setText("");
		} else if (e.getSource() == mntmSaveGame) {
			hangmanGameStart.saveGame(game);
		} else if (e.getSource() == mntmNewGame) {
			newGame();
		} else if (e.getSource() == mntmScoreboard) {
			displayScoreboard();
		} else if (e.getSource() == mntmHint) {
			getHint();
		} else if (e.getSource() == mntmQuit) {
			// (How to close a JFrame)
			this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
		} else if (e.getSource() == mntmRules) {
			displayRules();
		}
	}// actionPerformed()

	@Override
	public void windowActivated(WindowEvent e) {

	}// windowActivated(WindowEvent)

	@Override
	public void windowClosed(WindowEvent e) {

	}// windowClosed(WindowEvent)

	@Override
	public void windowClosing(WindowEvent e) {
		if (gameInProgress) {
			hangmanGameStart.saveGame(game);
		}

	}// windowClosing(WindowEvent)

	@Override
	public void windowDeactivated(WindowEvent arg0) {

	}// windowDeactivated(WindowEvent)

	@Override
	public void windowDeiconified(WindowEvent arg0) {

	}// windowDeiconified(WindowEvent)

	@Override
	public void windowIconified(WindowEvent arg0) {

	}// windowIconified(WindowEvent)

	@Override
	public void windowOpened(WindowEvent e) {
		hangmanGameStart = new HangmanGameStart();
		initializeDictionaryOutput = hangmanGameStart.initializeDictionary();

		if (hangmanGameStart.getScoreboard().retrieveScoreboard()) {
			int choice = JOptionPane.showConfirmDialog(this, "Do you have an account already?", "Welcome to Hangman",
					JOptionPane.YES_NO_OPTION);
			if (choice == 0) {
				displayUserDropDown();
			} else {
				displayEnterUsername();
			}
		} else
			displayEnterUsername();

	}// windowOpened(WindowEvent)

	public boolean isGameInProgress() {
		return gameInProgress;
	}
}// HangmanMainFrame class
