package model;

import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Player implements Comparable<Player>, Serializable{

	private static final long serialVersionUID = 1L;
	
	/*****************************************/
	
	private String name; //20 chars maximum
	private int totalGames;
	private int totalLosses; 
	private int totalWins;
	private int totalTies; 
	private float score;
	private ChartList<GameRecord> allGames;
	private ChartList<GameRecord> recentGames;
	private ChartList<GameRecord> bestGames;
	private String hisSymbol; //"X" OR "O"
	
	/*****************************************/
	
	public Player(String name){  
		this.totalGames = 0;
		this.totalLosses = 0;
		this.totalWins = 0;
		this.score = 0;
		this.totalTies = 0;
		this.allGames = new ChartList<GameRecord>(1);
		this.recentGames = new ChartList<GameRecord>(10);						
		this.bestGames = new ChartList<GameRecord>(10); 						
		this.name = name;
	}

	//constructor used in GameRecordTests
	public Player(String name,int totalGames,int totalLosses,int totalWins,int totalTies){  
		this.totalGames = totalGames;
		this.totalLosses = totalLosses;
		this.totalWins = totalWins;
		this.score = 0;
		this.totalTies = totalTies;
		this.allGames = new ChartList<GameRecord>(1);
		this.recentGames = new ChartList<GameRecord>(5);						
		this.bestGames = new ChartList<GameRecord>(5); 						
		this.name = name;
	}
		
	public Player(String name, String hisSymbol){  
			this.totalGames = 0;
			this.totalLosses = 0;
			this.totalWins = 0;
			this.score = 0;
			this.totalTies = 0;
			this.allGames = new ChartList<GameRecord>(1);
			this.recentGames = new ChartList<GameRecord>(5);						
			this.bestGames = new ChartList<GameRecord>(5); 						
			this.name = name;
			this.setHisSymbol(hisSymbol);
	}
	
	public Player() {
	}
	
	/*****************************************/
	
	//method that calculates and stores players score
	public float findScore(){
		if(totalGames == 0) {  			
			this.score = 0;
			return this.score;
		}else {
			this.score = (float) ( (50)*( (2 * totalWins + totalTies) / (float) totalGames) ) ;
		
		return this.score;
		}
	}
	
	
	
	//comparing two players with each other based on their scores
	@Override
	public int compareTo(Player other) {
		if(this.getScore() > other.getScore()) {
			return -1;
		} else if (this.getScore() < other.getScore()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	
	/*
	 * method that returns a string 
	 * containing the stats of a player
	 */
	public String playerStats() {
		DecimalFormat df = new DecimalFormat("##.##");
		StringBuffer message = new StringBuffer("");
		message.append("Total games played : "+ this.totalGames + 
	    "\n\n" + "Number of total wins: " + this.totalWins +
		"\n\n" + "Number of total losses: " + this.totalLosses );
		message.append("\n\n" + "Score: " + df.format(this.score));
		message.append("\n\n").append("Best Games are: ");
		GameRecord[] games = new GameRecord[5];
		//filling the array 
		for(int i =0 ; i<5; i++)
			games[i]=this.getBestGames().get(i);
		//printing
		for(int i=0; i<5; i++) {
			if(games[i]!=null) {
				message.append("\n"+ (i+1)+".").append(games[i].getPlayer(0).getName()).append(" VS ");
				message.append(games[i].getPlayer(1).getName());
			}	
		}
		String finalMessage = message.toString();
		return finalMessage;	
	}
	
	
	
	public void playerWins() { 
		totalGames++;
		totalWins++;
	}

	
	
	public void playerLoses() {
		totalGames++;
		totalLosses++;
	}

	
	
	public void itsTie() {
		totalGames++;
		totalTies++;
	}
	
	public void addNewGame(GameRecord game,int k) {
		this.allGames.addExtend(game);
		this.recentGames.addExtend(game);
		this.bestGames.addBestGame(game, this, k); 
	}
	
	/*****************************************/

	public String getName() {
		return name;
	}

	
	
	public void setName(String name) {
		this.name = name;
	}

	
	
	public int getTotalGames() {
		return totalGames;
	}

	
	
	public void setTotalGames(int totalGames) {
		this.totalGames = totalGames;
	}

	
	
	public int getTotalLosses() {
		return totalLosses;
	}

	
	
	public void setTotalLosses(int totalLosses) {
		this.totalLosses = totalLosses;
	}

	
	
	public int getTotalWins() {
		return totalWins;
	}

	
	
	public void setTotalWins(int totalWins) {
		this.totalWins = totalWins;
	}

	
	
	public int getTotalTies() {
		return totalTies;
	}

	
	
	public void setTotalTies(int totalTies) {
		this.totalTies = totalTies;
	}

	
	
	public float getScore() {
		return score;
	}

	
	
	public void setScore(float score) {
		this.score = score;
	}

	
	
	public ChartList<GameRecord> getAllGames() {
		return allGames;
	}

	
	
	public void setAllGames(ChartList<GameRecord> allGames) {
		this.allGames = allGames;
	}

	
	
	public ChartList<GameRecord> getRecentGames() {
		return recentGames;
	}

	
	
	public void setRecentGames(ChartList<GameRecord> recentGames) {
		this.recentGames = recentGames;
	}

	
	
	public ChartList<GameRecord> getBestGames() {
		return bestGames;
	}

	
	
	public void setBestGames(ChartList<GameRecord> bestGames) {
		this.bestGames = bestGames;
	}

	
	
	public String getHisSymbol() {
		return hisSymbol;
	}

	
	
	public void setHisSymbol(String hisSymbol) {
		this.hisSymbol = hisSymbol;
	}
	
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}