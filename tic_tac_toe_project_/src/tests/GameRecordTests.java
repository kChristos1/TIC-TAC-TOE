package tests;

import java.time.*;
import control.*;
import model.*;
import app.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Sort {

	LocalDateTime timeStamp;
	GameController gc;
	GameModel model ;
	
	@BeforeEach
	void SetUp() {	
	    gc = new GameController();
	    model  = new GameModel(gc);
		timeStamp = LocalDateTime.now().minusDays(20);
	}

	@Test
	@DisplayName ("Testing bestGame list")
	void  testsBestGames() {
		//the player whose (two each time) games will be (generally) compared/tested 
		
		Player player1 = new Player("Christos",5,5,0,0); //player1 has 5 total games 5 losses 0 wins and ties 
		
		/* player2 and player3 are player1's opponents in the first case where we have two games that are 
		 *  different (win-loss win-tie)*/
		
		Player player2 = new Player("Helen",5,4,1,0); //random number of total games, wins, losses and ties.
		
		Player player3 = new Player("Alexandra",5,4,1,0); //again random... 
		
		/* player4 and player5 are player1's opponents in the second case where we have the same result 
		 * between two games (win-win loss-loss tie-tie) and we compare them by the opponents scores 
		 * When the opponent is strong the game is better in all the situations(3 and 4) */
		
		Player player4= new Player("Nikolas", 5,5,0,0); //the strong one 
		
		Player player5= new Player("Giorgos",5,0,5,0); //the weak one 
		
		/*player6 and player7 are player1's opponents when the result of games are same and the
		 * opponents of player1 each time have the same score , in this case 
		 * we compare the games based on which one is more recent */
		
		Player player6 = new Player("Eirini",5,0,5,0); //5 games in total, won all of them, 0 ties and losses 
		
		Player player7 = new Player("Konstantinos",5,0,5,0); //same performance as player 2 

		/******************************************************************************************************/

		//game1 and 2 belong in the first case when we have win-loss
		
		GameRecord game1 = new GameRecord(player2, player1, timeStamp.plusDays(10),"game1");   // a loss
		
		GameRecord game2 = new GameRecord(player1, player3, timeStamp.plusDays(10),"game2");  // a win

		/*game3 and game4 belong in the first case when we have win-tie
		 * !!! IMPORTANT !!! : since the game is a tie the placement 
		 * of the winner does not affect our program , we ignore the placement
		 * in ties*/
		
		GameRecord game3 = new GameRecord(player1, player3, timeStamp.plusDays(1), "game3"); //a win over a tie
		
		GameRecord game4 = new GameRecord(player1, player3, timeStamp.plusDays(1), "game4"); //tie

		//game5 and game6 belong in the second case when we have win-win 
		
		GameRecord game5 = new GameRecord(player1, player4, timeStamp.plusDays(1), "game5"); //wins against strong one
		
		GameRecord game6 = new GameRecord(player1, player5, timeStamp.plusDays(1), "game6");  //wins against weak
		
		//game7 and game8 belong in the second case when we have loss-loss
	
		GameRecord game7 = new GameRecord(player4, player1, timeStamp.plusDays(1), "game7"); //loss
		
		GameRecord game8 = new GameRecord(player5, player1, timeStamp.plusDays(1), "game8"); //loss

		//game9 and game10 belong in the second case when we have tie-tie 
		//remember that the placement of the winner does not effect the program in ties 
		
		GameRecord game9 = new GameRecord(player4, player1, timeStamp.plusDays(1), "game9");  //tie against strong opponent
		
		GameRecord game10 = new GameRecord(player1, player5, timeStamp.plusDays(1), "game10"); //tie against weak opponent
		
		//game10 and game11 belong in the third case when win-win and same scores of opponents
		
		GameRecord game11 = new GameRecord(player1, player6, timeStamp.plusDays(1), "game11");  // a less recent win
		
		GameRecord game12 = new GameRecord(player1, player7, timeStamp.plusDays(2), "game12"); //better (recent) + a win 
		
		//game13 and 14 belong in the third case too (loss-loss) and same scores of opponents
		
		GameRecord game13 = new GameRecord(player6, player1, timeStamp.plusDays(1), "game13"); 
		
		GameRecord game14 = new GameRecord(player7, player1, timeStamp.plusDays(2), "game14"); // a loss
		
		//game15 and 16 belong in the third case (tie-tie same scores) !placement does not effect 
		
		GameRecord game15 = new GameRecord(player1, player4, timeStamp.plusDays(1), "game15"); 
		
		GameRecord game16 = new GameRecord(player1, player5, timeStamp.plusDays(2), "game16");
		
		/******************************************************************************************************/
	
		player1.addNewGame(game1, 1);		
		
		player1.addNewGame(game2, 1); 		
		
		player1.addNewGame(game3, 1); 		
		
		player1.addNewGame(game4, 0); 	
		
		player1.addNewGame(game5, 1);	
		
		player1.addNewGame(game6, 1);	
		
		player1.addNewGame(game7, 1);	
		
		player1.addNewGame(game8, 1);
		
		player1.addNewGame(game9, 0);    	
		
		player1.addNewGame(game10, 0);		
		
		player1.addNewGame(game11, 1);		
		
		player1.addNewGame(game12, 1);		
		
		player1.addNewGame(game13, 1);		
		
		player1.addNewGame(game14, 1);	
		
		player1.addNewGame(game15, 0); 		
		
		player1.addNewGame(game16, 0); 		
 
		/******************************************************************************************************/
				
		assertEquals(player1.getBestGames().get(0).tests, game2.tests, "Should be game 2");
				
		assertEquals(player1.getBestGames().get(1).tests, game12.tests, "Should be game 12");
		
		assertEquals(player1.getBestGames().get(2).tests, game3.tests, "Should be game 3");
				
		assertEquals(player1.getBestGames().get(3).tests, game5.tests, "Should be game 5");
				
		assertEquals(player1.getBestGames().get(4).tests, game6.tests, "Should be game 6");
				
		assertEquals(player1.getBestGames().get(5).tests, game11.tests, "Should be game 11");
				
		assertEquals(player1.getBestGames().get(6).tests, game1.tests, "Should be game 1");
				
		assertEquals(player1.getBestGames().get(7).tests, game4.tests, "Should be game 4");
			
		assertEquals(player1.getBestGames().get(8).tests, game14.tests, "Should be game 14");
				
		assertEquals(player1.getBestGames().get(9).tests, game7.tests, "Should be game 7");
				
		assertEquals(player1.getBestGames().get(10).tests, game8.tests, "Should be game 8");
				
		assertEquals(player1.getBestGames().get(11).tests, game9.tests, "Should be game 9");
				
		assertEquals(player1.getBestGames().get(12).tests, game10.tests, "Should be game 10");
				
		assertEquals(player1.getBestGames().get(13).tests, game13.tests, "Should be game 13");
				
		assertEquals(player1.getBestGames().get(14).tests, game15.tests, "Should be game 15");
				
		assertEquals(player1.getBestGames().get(15).tests, game16.tests, "Should be game 16");
	}
}