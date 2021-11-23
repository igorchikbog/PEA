package hangman_package;

import java.io.Serializable;


public class Scoreboard implements Serializable {

	private static final long serialVersionUID = 1L;
	private DoublyLinkedList<Player> scoreboard;
	private int currPlayer;

	public Scoreboard() {
		scoreboard = new DoublyLinkedList<Player>();
		currPlayer = 0;
	}//Scoreboard()
	
	public DoublyLinkedList<Player> getScoreboard() {
		return scoreboard;
	}//getScoreboard()

	
	public void setCurrPlayer(int currPlayer) {
		this.currPlayer = currPlayer;
	}

	public boolean retrieveScoreboard() {
		ScoreboardFile scoreboardFile = new ScoreboardFile();

		if (scoreboardFile.deserializeScoreboard()) {
			scoreboard = scoreboardFile.getScoreboard().getScoreboard();
			return true;
		} else {
			return false;
		}
	}//retrieveScoreboard()

	public String[] retrieveUserNames() {
		String[] usernames;

		if (scoreboard.getLength() > 0) {
			usernames = new String[scoreboard.getLength()];

			for (int i = 0; i < scoreboard.getLength(); i++) {
				usernames[i] = scoreboard.getElementAt(i).getUsername();
			}

		} else
			usernames = null;

		return usernames;
	}//retrieveUserNames()

	public void addUser(Player user) {
		if (scoreboard.getLength() == 0) {
			scoreboard.add(user);

			for (int i = 0; i < scoreboard.getLength(); i++) {
				scoreboard.getElementAt(i).toString();
			}
		} else {
			findSpot(user);
		}
	}// addUser();
	
	public Player nextUser() {
		Player user;
		if(scoreboard.getLength() == 0) {
			user = null;
		} else if(currPlayer == scoreboard.getLength()) {
			user = null;
		} else {
			user = scoreboard.getElementAt(currPlayer++);	
		}	
		return user;
	}// nextUser()
	
	private void findSpot(Player user) {
		int index = 0;
		boolean foundSpot = false;
		String userAfter = scoreboard.getElementAt(index).getUsername();

		while (!foundSpot && index < scoreboard.getLength()) {
			if (userAfter.compareToIgnoreCase(user.getUsername()) > 0 && index != 0) {
				scoreboard.add(user, index);
				foundSpot = true;
			} else if (userAfter.compareToIgnoreCase(user.getUsername()) > 0) {
				scoreboard.add(user);
				foundSpot = true;
			} else if (scoreboard.getLength() == 1) {
				scoreboard.add(user, 1);
				foundSpot = true;
			} else if (index + 1 != scoreboard.getLength()) {
				userAfter = scoreboard.getElementAt(++index).getUsername();
			} else {
				scoreboard.add(user, ++index);
				foundSpot = true;
			}
		}
	}//findSpot(Player)

	public boolean saveScoreboard() {
		ScoreboardFile file = new ScoreboardFile();
		return file.saveScoreboard(this);
	}//saveScoreboard()

	public boolean checkForUser(Player user) {
		boolean userExists = false;

		for (int i = 0; i < scoreboard.getLength(); i++) {
			if(scoreboard.getElementAt(i).equals(user))
				userExists = true;
		}

		return userExists;
	}// checkForUser(Player)

	public Player findUser(String username) {
		Player tempUser = new Player(username);

		for (int i = 0; i < scoreboard.getLength(); i++) {
			Player currUser = scoreboard.getElementAt(i);
			if (currUser.equals(tempUser)) {
				return currUser;
			}
		}

		return null;
	}//findUser()

	@Override
	public String toString() {
		String users = "";
		for (int i = 0; i < scoreboard.getLength(); i++) {
			users += scoreboard.getElementAt(i).getUsername();
			users += "\n";
		}
		return users;
	}// toString()

}// Scoreboard class
