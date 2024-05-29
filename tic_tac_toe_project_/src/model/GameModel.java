package model;

import java.util.Random;

import control.GameController;

public class GameModel {
	
	PlayersCatalogue  playerCatalogue;
	Player [] gamePlayers;		
	String[][] gameBoard;
	GameController gc;
 	Boolean mover;//X=TRUE O=FALSE
	int moves;
	String winner=null;
	Boolean gameEnded=false;
	
	/*****************************************/
	
	public GameModel(GameController gc) {
		this.gc=gc;
		gamePlayers = new Player[2];
		gameBoard= null;
		playerCatalogue= new PlayersCatalogue();
		moves=0;
		
	}
	
	/*****************************************/
	
	//sets game player in the wanted position, respecting the position bounds
	public void selectPlayer(Player player, int pos) {
		if (pos<0 && pos>1)return;
		gamePlayers[pos]=player;
	}
	
	

	public boolean ready() {
		return (gamePlayers[0] != null && gamePlayers[1] !=null);
	}
	
	
	
	public void startGame() {
		gameBoard= new String[3][3];
	}
	
	
	
	public boolean inPlay() {
		return gameBoard !=null && moves <9;
	}
	
	
	
	public boolean NoPlay() {
		return !inPlay();
	}
	

	
	public void checkDimValidity(int row, int col) {
		if (row <0 || col < 0 || row > 2 || col >2) {
			throw new IndexOutOfBoundsException(row + ","+col +" is not a valid board cell");
		}
	}
	
	
	
	public void checkMoveValidity(int row, int col) {
		checkDimValidity(row, col); 
		if (gameBoard[row][col]!=null ) {
			throw new IllegalArgumentException("Non playable cell");
		}
	}
	
	

	/*
	 * method that places X or O in the board, 
	 * changing "mover" each time an increases 
	 * the number of moves by one
	 */
	public int makeMove(int row, int col) {
		if(gameEnded==false) {
			if(inPlay()) {
				checkMoveValidity(row, col);
				gameBoard[row][col]=getMoverMark();
				mover=!mover; //changing who plays each time 
				String check= checksForWin();
				this.moves++;
				
				if(check!=null && check!="1") {
					gameEnded=true;
					gc.endGame();
				}else {
					if (moves==9) {
						System.out.println("Game Over");
						gc.endGame();
					}
				}
				return 0;
			}
		}
		System.out.println("GameOver");
		gameEnded=false;
		return 1;		
	}
	

	
	public String getMoverMark() {
		return mover? "X": "O";
	}
	
	
	
	/*
	 * method that checks for win, loss or tie 
	 * 	returns "X" <=> X wins 
	 * 	returns "O" <=> O wins 
	 * 	returns "1" <=> its a tie
	 */
	public String checksForWin() throws NullPointerException{
		if(moves<=9) {
		if(moves>=4) {
			if(gameBoard[0][0] == "X" && gameBoard[0][1] == "X" && gameBoard[0][2] == "X" ) {
				System.out.println("Winner is X");
				winner=gameBoard[0][0];
				return winner; 	
			}
			if(gameBoard[1][0] == "X" && gameBoard[1][1] == "X" && gameBoard[1][2] == "X" ) {
				System.out.println("Winner is X");
				winner=gameBoard[1][0];
				return winner; 
			}
			if(gameBoard[2][0] == "X" && gameBoard[2][1] == "X" && gameBoard[2][2] == "X" ) {
				System.out.println("Winner is X");
				winner=gameBoard[2][0];
				return winner; 
				
			}
			if(gameBoard[0][0] == "X" && gameBoard[1][0] == "X" && gameBoard[2][0] == "X" ) {
				System.out.println("Winner is X");
				winner=gameBoard[1][0];
				return winner; 
			}
			if(gameBoard[0][1] == "X" && gameBoard[1][1] == "X" && gameBoard[2][1] == "X" ) {
				System.out.println("Winner is X");
				winner=gameBoard[0][1];
				return winner; 
				
			}
			if(gameBoard[0][2] == "X" && gameBoard[1][2] == "X" && gameBoard[2][2] == "X" ) {
				System.out.println("Winner is X");
				winner=gameBoard[0][2];
				return winner; 
			}
			
			if(gameBoard[0][0] == "X" && gameBoard[1][1] == "X" && gameBoard[2][2] == "X" ) {
				System.out.println("Winner is X");
				winner=gameBoard[0][0];
				return winner; 
			}
			if(gameBoard[0][2] == "X" && gameBoard[1][1] == "X" && gameBoard[2][0] == "X" ) {
				winner=gameBoard[0][2];
				return winner; 
			}
			
			//checking for O
			
			if(gameBoard[0][0] == "O" && gameBoard[0][1] == "O" && gameBoard[0][2] == "O" ) {
				System.out.println("Winner is O");
				winner=gameBoard[0][0];
				return winner; 
				   
			}
			if(gameBoard[1][0] == "O" && gameBoard[1][1] == "O" && gameBoard[1][2] == "O" ) {
				System.out.println("Winner is O");
				winner=gameBoard[1][0];
				return winner; 
			}
			if(gameBoard[2][0] == "O" && gameBoard[2][1] == "O" && gameBoard[2][2] == "O" ) {
				System.out.println("Winner is O");
				winner=gameBoard[2][0];
				return winner; 
			}
			if(gameBoard[0][0] == "O" && gameBoard[1][0] == "O" && gameBoard[2][0] == "O" ) {
				System.out.println("Winner is O");
				winner=gameBoard[0][0];
				return winner; 
			}
			if(gameBoard[0][1] == "O" && gameBoard[1][1] == "O" && gameBoard[2][1] == "O" ) {
				System.out.println("Winner is O");
				winner=gameBoard[0][1];
				return winner; 
			}
			if(gameBoard[0][2] == "O" && gameBoard[1][2] == "O" && gameBoard[2][2] == "O" ) {
				System.out.println("Winner is O");
				winner=gameBoard[0][2];
				return winner; 
			}
			
			if(gameBoard[0][0] == "O" && gameBoard[1][1] == "O" && gameBoard[2][2] == "O" ) {
				System.out.println("Winner is O");
				winner=gameBoard[0][0];
				return winner; 
			}
			if(gameBoard[0][2] == "O" && gameBoard[1][1] == "O" && gameBoard[2][0] == "O" ) {
				System.out.println("Winner is O");
				winner=gameBoard[0][2];
				return winner; 
			}
		}else {
			winner="1";
			return "1";
			}
		}
		winner="1";
		return "1";
	}
	
	
	
	public void addGame(Player winner, Player loser, int i) {
		GameRecord endedGame=new GameRecord(winner,loser);
		
		if(winner!=null && loser!=null && i!=0) {
			
			winner.addNewGame(endedGame,i);//adds game to list of played games
			loser.addNewGame(endedGame,i);//adds game to list of played games
			
			winner.playerWins();//adds num of total game and win
			loser.playerLoses();//adds num of total game and loss
			
			winner.findScore();
			loser.findScore();
			
			System.out.println("For winner total games and total losses: "+winner.getTotalGames()+" "+winner.getTotalLosses());
			System.out.println("For loser total games and total losses: "+loser.getTotalGames()+" "+loser.getTotalLosses());
			System.out.println("Winners score :" + winner.getScore());
			System.out.println("Losers score: " +loser.getScore());
		
		}else {
			System.out.println("We are dealing with a tie");
			
			winner.addNewGame(endedGame,i);//adds game to list of played games
			loser.addNewGame(endedGame,i);//adds game to list of played games
			
			winner.itsTie();
			loser.itsTie();
			
			winner.findScore();
			loser.findScore();
			
		}
	}
	

	
	//returns the name of the wanted player
	public String getPlayersNames(int pos) {
		return gamePlayers[pos].getName();
	}
	
	
	
	//returns X or O (or null)
	public String getBoardMark(int row, int col) {
		checkDimValidity(row, col);
		return gameBoard[row][col];
	}

	
	
	/*
	 * ----> "MrBean moves" method. <----
	 * This method simply generates 
	 * a pair of two random integers
	 * from 0 to 2 [ nextInt(3) ]
	 * that will be passed as arguments in 
	 * the makMove() method
	 */
	public void randomMove() {
		Random randomMove = new Random();
		int row,col;
		int[] beanMoves=new int [2];
		while(true) {
			row = randomMove.nextInt(3);
			col = randomMove.nextInt(3);			 
			if(gc.getModel().getGameBoard()[row][col]==null) {
			break;
			}
		}
		beanMoves[0]=row;
		beanMoves[1]=col;
		makeMove(row,col);
	}
	
	
	
	/*
	 * ----> "Hal moves" method. <----
	 * same as the randomMove() method only
	 * that it generates the optimal
	 *  moves using minimax() algorithm
	 */
	public void hal() {
		int[] bestMove=new int [2];
		int score= Integer.MIN_VALUE;

		 for (int row = 0; row < 3; row++) {
	            for (int col = 0; col < 3; col++) {
	                if (gameBoard[row][col]==null) {
	                    gameBoard[row][col]=getMoverMark();
	                    int moveValue = this.minimax(gameBoard,0,false);
	                    gameBoard[row][col]=null;
	                    if (moveValue > score) {
	                        bestMove[0] = row;
	                        bestMove[1] = col;
	                        score=moveValue;
	                    }
	                }
	            }
		 }
	   		 makeMove(bestMove[0],bestMove[1]);	 
	}
	
	
	
	//the minimax method
	public int minimax(String gameBoard[][],int depth ,Boolean maximizing) {	
 		String result = this.gc.getModel().checksForWin();
		switch(result) {
		case "X":
			return 10;
		case "O":	
			return -10;
		case "1":
			return 0;
		}

		if(maximizing) {
			int maxScore = Integer.MIN_VALUE;
			for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if(gameBoard[row][col] == null) {	
                    	gameBoard[row][col]=gc.getModel().getMoverMark();
                    	int score = minimax(gameBoard,depth+1,false);
                    	gameBoard[row][col]=null;
                    	maxScore = Math.max(maxScore, score);
                    }
                }
             }
			return maxScore;
		}else {
			int	minScore=Integer.MAX_VALUE;
			 for (int row = 0; row < 3; row++) {
	                for (int col = 0; col < 3; col++) {
	                	if(gameBoard[row][col] == null) {	         
	                		gameBoard[row][col]=gc.getModel().getMoverMark();
	                		int score2 = minimax(gameBoard,depth+1, true);
	                		gameBoard[row][col]=null;
	                		minScore = Math.min(minScore, score2);
	                	}
	                }
	         }
			 return minScore;
		}
		
	}
	
	
	
	//method that puts two ai players (MrBean and Hal) play against each other 
	public void aiGame() {
		if(gc.getModel().getGamePlayers()[0].getName().equals("MrBean") ) {
			if(gc.getModel().getGamePlayers()[1].getName().equals("Hal")) {
				while(this.moves < 9 && this.checksForWin()=="1") {
					randomMove();
					if(this.moves < 9 && this.checksForWin()=="1") {
						hal(); 
				 
					}
				}
			}
		}if(gc.getModel().getGamePlayers()[0].getName().equals("Hal") ) {
			if(gc.getModel().getGamePlayers()[1].getName().equals("MrBean")) {
				while(this.moves < 9 && this.checksForWin()=="1") {
					hal();
					if(this.moves < 9 && this.checksForWin()=="1") {
						randomMove();
					}
				}
			}
		}
	}
	
	/*****************************************/
	//Setters and Getters:
	
	public Player[] getGamePlayers() {
		return gamePlayers;
	}
	
	

	public String[][] getGameBoard() {
		return gameBoard;
	}
	
	
	
	public int getMoves() {
		return moves;
	}
	
	

	public void setGameBoard(String[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	
	
	public PlayersCatalogue getPlayerCatalogue() {
		return playerCatalogue;
	}

	
	
	public void setPlayerCatalogue(PlayersCatalogue playerCatalogue) {
		this.playerCatalogue = playerCatalogue;
	}
	
	
	
	public Boolean getGameEnded() {
		return gameEnded;
	}

	
	
	public void setGameEnded(Boolean gameEnded) {
		this.gameEnded = gameEnded;
	}

	
	
	public void setMoves(int i) {
		this.moves=i;
		
	}
	
	
	
	public GameController getGc() {
		return gc;
	}

	
	
	public void setGc(GameController gc) {
		this.gc = gc;
	}

	
	
	public String getWinner() {
		return winner;
	}

	
	
	public void setWinner(String winner) {
		this.winner = winner;
	}

	
	
	public void setGamePlayers(Player[] gamePlayers) {
		this.gamePlayers = gamePlayers;
	}

	
	
	public boolean getMover() {
		return this.mover;
	}
	
	
	
	public void setMover(Boolean mover) {
		this.mover = mover;
	}

}
