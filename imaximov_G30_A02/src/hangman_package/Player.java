package hangman_package;
import java.io.Serializable;

public class Player implements Serializable{

		private static final long serialVersionUID = 1L;
		private String username;
		private int totalWins;
		private int totalGames;


		public Player() {
			this.username = "unknown";
			this.totalWins = 0;
			this.totalGames = 0;
		}//Player()
		
		public Player(String uName) {
			this.username = uName;
			this.totalWins = 0;
			this.totalGames = 0;
		}//Player(String)
		
		public String getUsername() {
			return username;
		}// String getUsername()

		public void setUsername(String username) {
			this.username = username;
		}//setUsername(String)

		public int getTotalWins() {
			return totalWins;
		}//getTotalWins()

		public void setTotalWins(int totalWins) {
			this.totalWins = totalWins;
		}//setTotalWins(int)

		public int getTotalGames() {
			return totalGames;
		}//getTotalGames()

		public void setTotalGames(int totalGames) {
			this.totalGames = totalGames;
		}//setTotalGames(int)
		
		public void incrementTotalGames() {
			this.totalGames++;
		}// incrementTotalGames()
		
		public void incrementTotalWins() {
			this.totalWins++;
		}// incrementTotalWins()

		@Override 
		public boolean equals(Object o) {
			if(o instanceof Player) {
				if(((Player) o).getUsername().equals(this.username)) {
					return true;
				}
			}
			return false;
		}// equals(Object)
		
		@Override
		public String toString() {
			return this.getUsername() + " has played a total of "+ this.totalGames + " games and has won " + this.totalWins;	
		}// toString()

	}//Player class


