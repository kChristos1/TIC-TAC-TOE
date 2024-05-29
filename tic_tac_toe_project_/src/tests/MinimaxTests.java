package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import control.GameController;
import model.GameModel;
import model.Player;
import view.GameBoard;

class MinimaxTests {

	private GameModel game; 
	private Player player1;
	private Player player2; 
	private GameController gc;


	@BeforeEach 
	void init() {
		this.gc = new GameController();
		this.game = new GameModel(gc); 
		gc.start();
		player1= new Player("Hal");
		player2= new Player("Christos");
	}
	
	@Test
	@DisplayName("Testing the minimax algorithm efficiency")
	void testHal() {
		game.selectPlayer(player1, 0);
		game.selectPlayer(player2, 1);
		
		String[][] gameBoard = new String[3][3];
		gameBoard[0][0] = "X";
		gameBoard[2][2] = "O";
		gameBoard[0][1] = "X";
		
		game.setMoves(3);
		game.setGameBoard(gameBoard);
		
		
		game.setMover(false);
		game.hal();
		
		/*	X| X| O 
		 *   |  |  
		 *   |  | O
		 */

		assertEquals(4,game.getMoves());
		assertEquals("X", game.getGameBoard()[0][0]);
		assertEquals("X", game.getGameBoard()[0][1]);
		assertEquals("O", game.getGameBoard()[0][2]); //hal's  "optimal" move
	}

}
