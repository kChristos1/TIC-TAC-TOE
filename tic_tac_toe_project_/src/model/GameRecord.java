package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Iterator;

@SuppressWarnings("serial")
public class GameRecord implements Comparable<GameRecord> , Serializable{

	private final int NUM_OF_PLAYERS = 2; //one game is played by 2 players
	private Player[] player; //the 2 players that played the game
	private LocalDateTime startingTime; 
	public String tests;
	
	/*****************************************/

	public GameRecord(Player winner,Player loser) {
		this.player = new Player[NUM_OF_PLAYERS]; 
		player[0]=winner;
		player[1]=loser;
		double score[]=new double[2]; 
		score[0]=winner.getScore();
		score[1]=loser.getScore();
		this.startingTime = LocalDateTime.now();	
	}
	
	//Used in GameRecordTests
	public GameRecord(Player winner, Player loser, LocalDateTime timeStamp,String t){
		this.tests=t;
		this.player = new Player[NUM_OF_PLAYERS]; 
		player[0]=winner;
		player[1]=loser;
		double score[]=new double[2];
		score[0]=winner.getScore();
		score[1]=loser.getScore();
		this.startingTime = timeStamp;	
	}
	
	/*****************************************/

	public float getScoreOfPlayer(int i) {
		return player[i].getScore();
	}
	
	
	
	/*
	 * BestGames method:
	 * finds the bestGamed of a player
	 * the int i parameter defines 
	 * whether the game is a tie or not
	------------------------------------
	->current is played now 
    ->"this." is the game that's being compared to current - the previous one
	->current > previous => returns -1
	->current < previous => returns 1 
	->current = previous => returns 0
	->IF i=0 => tie
	->IF i=1 => win/loss
	  */
	public int findBestGame(GameRecord current, Player wantedPlayer,int i) {
		if(i==0) { //checking if previous is tie 
			if(wantedPlayer.getName().equals(current.player[0].getName()) && i==1) { //checking if current is win WHILE previous is tie , if so its better, else its worse
				return -1; 
			}else if(wantedPlayer.getName().equals(current.player[1].getName()) && i==1) { 
				return 1;  
			
			}else if(!(wantedPlayer.getName().equals(this.player[0].getName())) && !(wantedPlayer.getName().equals(this.player[1].getName())) && i==0) { //checking if current is tie WHILE previous is tie (final check for current while previous is tie)
				if((this.player[0].getScore() == this.player[1].getScore()) || (current.player[0].getScore() == current.player[1].getScore()) || 
						(this.player[0].getScore() < this.player[1].getScore() && current.player[0].getScore() < current.player[1].getScore()) ||
						(this.player[0].getScore() > this.player[1].getScore() && current.player[0].getScore() > current.player[1].getScore())) {
					if(current.startingTime.isAfter(this.startingTime)){
								return -1;//current game is better
							}else if(this.startingTime.isAfter(current.startingTime)) {
								return 1;//previous game was better
							}else if(this.startingTime.isEqual(current.startingTime)) {
								return 0;
							}
				}
			}
		//all checks for current game  WHILE the previous is a tie have finished
		//now lets check what happens with the previous game (we have only examined the tie case)
		}else if (wantedPlayer.getName().equals(this.player[0].getName()) && i==1) { //checking if previous game was win  
		 	if(wantedPlayer.getName().equals(current.player[0].getName()) && i==1) { //then lets check if the current is a win 
					if(this.player[0].getScore() < this.player[1].getScore() && current.player[0].getScore() > current.player[1].getScore()) {
						return 1;
						//previous game of wantedPlayer was a win so it's better
					}else if(this.player[0].getScore() > this.player[1].getScore() && current.player[0].getScore() < current.player[1].getScore()) {
						return -1;
						//previous game was a loss => current game is better	
					}else if((this.player[0].getScore() == this.player[1].getScore()) || (current.player[0].getScore() == current.player[1].getScore()) || 
							(this.player[0].getScore() < this.player[1].getScore() && current.player[0].getScore() < current.player[1].getScore()) ||
							(this.player[0].getScore() > this.player[1].getScore() && current.player[0].getScore() > current.player[1].getScore())) {
						//in this case where the results where win-win or loss-loss AND the opponents where 
						if(current.startingTime.isAfter(this.startingTime)){
									return -1;//current game is better
								}else if(this.startingTime.isAfter(current.startingTime)) {
									return 1;//previous game was better
								}else if(this.startingTime.isEqual(current.startingTime)) {
									return 0;
								}
						}
			 }else 
				 return 1; //the current game was a loss WHILE the previous is a win (see line 105)		
		}else if(wantedPlayer.getName().equals(this.player[1].getName()) && i==1) {//if this code runs it means that the previous == loss 
				if(wantedPlayer.getName().equals(current.player[1].getName()) && i==1) { //checking if the current is loss WHILE the previous is loss 
					if(this.player[1].getScore() < this.player[0].getScore() && current.player[1].getScore() > current.player[0].getScore()) { 
						return 1;//the previous game was better
					}else if(this.player[1].getScore() > this.player[0].getScore() && current.player[1].getScore() < current.player[0].getScore()) {
						return -1;//the current game is better
					}else if((this.player[0].getScore() == this.player[1].getScore()) || (current.player[0].getScore() == current.player[1].getScore()) || 
							(this.player[0].getScore() < this.player[1].getScore() && current.player[0].getScore() < current.player[1].getScore()) ||
							(this.player[0].getScore() > this.player[1].getScore() && current.player[0].getScore() > current.player[1].getScore())) {
						if(current.startingTime.isAfter(this.startingTime)){
							return -1;
						}else if(this.startingTime.isAfter(current.startingTime)) {
							return 1;
						}else if(this.startingTime.isEqual(current.startingTime)) {
							return 0;
						}
				}
					}else 
						return -1;//means that current is win => its better since the previous is loss 	
		}
		//all checks for previous and current game have been examined ,
		//the method will return 123 if something went wrong 
		return 123;
	}
	
	
	
	/*
	 * Overriding the method because we want 
	 * to be able to create ChartList<GameRecord> objects 
	 * in other parts of the program 
	 */
	@Override
	public int compareTo(GameRecord o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*****************************************/

	public Player getPlayer(int i) {
		return this.player[i];
	}
	
	
	
	public LocalDateTime getDuration() {
		return startingTime;
	}
	
	

	public void setDuration(LocalDateTime duration) {
		this.startingTime = duration;
	}
}


