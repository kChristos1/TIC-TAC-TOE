package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import control.GameController;
import model.GameModel;
import model.Player;
import model.PlayersCatalogue;
import view.GameBoard;

class GameModelTests {

	private GameModel game; 
	private Player player1;
	private Player player2; 
	private GameController gc;


	@BeforeEach 
	void init() {
		this.gc = new GameController();
		this.game = new GameModel(gc); 
		gc.start();
		player1= new Player("Christos");
		player2= new Player("Eirini");
	}
	
	
	@Test
	@DisplayName("Testing player selection")
	void playerSelection() {
		game.selectPlayer(player1, 0);
		game.selectPlayer(player2, 1);
		
		assertEquals(player1, game.getGamePlayers()[0]);
		assertEquals(player2, game.getGamePlayers()[1]);
	}

	
	@Test
	@DisplayName("Testing if the game state is succesfully calculated")
	void checkState() {
		game.selectPlayer(player1, 0);
		game.selectPlayer(player2, 1);
		
		GameModel game2 = new GameModel(this.gc);
		
		Player player3 = new Player("Panagiotis");
		Player player4 = null; 
		
		game2.selectPlayer(player3, 0);
		game2.selectPlayer(player4, 1);
		
		assertEquals(true ,game.ready());
	    assertEquals(false, game2.ready());
	    //game2 can't be ready, because player3 is set to null
	}
	
	
	@Test
	@DisplayName("Testing if in play or not")
	void checkInPlay() {
		game.startGame();
		game.setMoves(9);
		
		/*
		 * setting moves to 9 so the game is over 
		 * (in play must return false) 
		 */
		
		assertEquals(false, game.inPlay());
	}
	
	
	
	@Test
	@DisplayName("Testing if in play or not")
	void checkNoPlay() {
		game.startGame();
		game.setMoves(6);
		
		/*
		 * setting moves to 6 so the game still goes on 
		 * (NoPlay must return false) 
		 */
		
		assertEquals(false, game.NoPlay());
	}
	
	
	@Test 
	@DisplayName("Testing make move")
	void makeMoveTesting() {
		String[][] gb = new String[3][3]; 
		
		game.setMoves(4);
		
		gb[0][0]="O";
		gb[0][1]="O";
		gb[0][2]= "X"; 
		gb[1][0]= "X";
		
		game.setGameBoard(gb);
		//4 moves this far
		
		game.setMover(false);
		//we want final move (move5) to be made by "O" 

		game.makeMove(2, 2);
		//if makeMove works fine => 5 moves this far
		//and NOW mover must be the opponent "X" 
		//so the mover variable should be set to "false" by makeMove()
	
		assertEquals(5, game.getMoves());
		assertEquals(true , game.getMover());
		assertEquals("X", game.getMoverMark());
	}
	
	
	@Test 
	@DisplayName("Testing if the winner is spotted succesfully")
	void checkForWinTest() {
		String[][] gameBoard = new String[3][3];
		gameBoard[0][0] = "O";
		gameBoard[1][0] = "X";
		gameBoard[1][1] = "O";
		gameBoard[0][2] = "X";
		gameBoard[2][2] = "O"; 
		
		game.setMoves(5);
		game.setGameBoard(gameBoard);
		
		/*
		     O| X|
		     X| O|
		   	  |  | O
		 
		    ("O" wins) */
		
		assertEquals("O", game.checksForWin());
	}
	
	
	
}
