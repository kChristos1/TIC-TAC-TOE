package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.GameModel;
import model.Player;
import model.PlayersCatalogue;
import view.MainAreaPanel;
import view.MainWindow;
import view.BoardCell;
import view.GameBoard;
import view.PlayerPanel;

public class GameController extends WindowAdapter {
	MainWindow view;
	GameModel model;
	
	/************************/
	/*****Constructors*****/
	public GameController() {			
	}
	
	/************************/
	/*****Methods*****/
	@Override
	public void windowClosing(WindowEvent event) {
		quit();
	}
	
	/*Method used only in the beginning of the whole game and loads previous players data*/
	
	public void start() {
		this.model = new GameModel(this);
		this.model.getPlayerCatalogue().loadPlayers();
		this.view= new MainWindow(this);
		this.view.addWindowListener(this);
		this.view.setVisible(true);
	}
	
	/*Used when button quit is pressed and closes the game's window and stores players data in file tuctactoe.ser*/
	
	public void quit() {	
		this.model.getPlayerCatalogue().storePlayers();
		System.out.println("bye bye...");		
		System.exit(0);
	}
	
	/*Used in Player Panel to allow user to choose a Player (fills player[0] and player[1] in class GameModel) 
	  and enables startGameButtons */
	
	public void selectPlayer(Player p, int pos) {
		this.model.selectPlayer(p, pos);
		if(this.getModel().ready()) { //when both players are set, enabling the startButtons
			System.out.println("Player " + pos + " set to " + p.getName());
			this.view.getRightPanel().getStartGameBtn().setEnabled(true);
			this.view.getLeftPanel().getStartGameBtn().setEnabled(true);
		}		
	}
	
	/*Method used to start only the first game and allows first transition from HallOfFame to GameBoard */
	
	public void startGame() {
		this.model.setGameBoard(new String [3][3]);
		this.view.getRightPanel().getStartGameBtn().setEnabled(false);//disables the startGameButtons
		this.view.getLeftPanel().getStartGameBtn().setEnabled(false);
		this.view.getLeftPanel().getSelectPlayerBtn().setEnabled(false);
		this.view.getRightPanel().getSelectPlayerBtn().setEnabled(false);
		this.view.getMainPanel().showCard(MainAreaPanel.BOARD);//changes perspective from HallOfFame to GameBoard
		this.view.getLeftPanel().getStartGameBtn().setEnabled(model.NoPlay());
		this.view.getRightPanel().getStartGameBtn().setEnabled(model.NoPlay());
		
	}
	
	/*Method used in DoneButton's action Listener: Updates stats(HallOfFame),makes transition from GameBoard to HallOfFame
	 *and prepares for next possible game(enables selectPlayer buttons)
	 */
	
	public void startNewGame() {
		//updating player's stats in the panels 
		this.model.setMoves(0);
		this.view.getMainPanel().showCard("HALL_OF_FAME");
		this.view.getLeftPanel().getSelectPlayerBtn().setEnabled(true);
		this.view.getRightPanel().getSelectPlayerBtn().setEnabled(true);
		this.view.getTopPanel().getDoneBtn().setEnabled(false);
		this.view.getLeftPanel().repaint();
	    this.view.getRightPanel().repaint();
	}
	
	/*Method used to stop an ongoing game in View if win-loss-tie has been detected,adds game in list Of Games and sets player's new stats */
	
	public void endGame() {
		//Winner is X
		if (this.model.checksForWin().equals("X")) {
			this.view.getTopPanel().getDoneBtn().setEnabled(true);
			this.view.drawWinningTriplet("X");
			this.model.addGame(this.model.getGamePlayers()[0],this.model.getGamePlayers()[1],1 );
			getView().getRightPanel().setPlayerStats(getView().getLeftPanel().getModel().getGamePlayers()[1].playerStats());
			getView().getLeftPanel().setPlayerStats(getView().getLeftPanel().getModel().getGamePlayers()[0].playerStats());
			getView().getMainPanel().getHallOfFame().drawPlayers();
			
		}
		
		//Winner is O
		if (this.model.checksForWin().equals("O")) {
			this.view.getTopPanel().getDoneBtn().setEnabled(true);
			this.view.drawWinningTriplet("O");
			this.model.addGame(this.model.getGamePlayers()[1],this.model.getGamePlayers()[0],1 );
			getView().getRightPanel().setPlayerStats(getView().getLeftPanel().getModel().getGamePlayers()[1].playerStats());
			getView().getLeftPanel().setPlayerStats(getView().getLeftPanel().getModel().getGamePlayers()[0].playerStats());
			getView().getMainPanel().getHallOfFame().drawPlayers();
		}
		
		//Its a tie
		if (this.model.checksForWin().equals("1")) {
			this.view.getTopPanel().getDoneBtn().setEnabled(true);
			this.view.drawWinningTriplet("ITS A TIE");
			// ITS A TIE IT DOES NOT MATTER THAT I HAVE PLACED "A WINNER".I handle the game as a tie in model.
			this.model.addGame(this.model.getGamePlayers()[0],this.model.getGamePlayers()[1],0);
			getView().getRightPanel().setPlayerStats(getView().getLeftPanel().getModel().getGamePlayers()[1].playerStats());
			getView().getLeftPanel().setPlayerStats(getView().getLeftPanel().getModel().getGamePlayers()[0].playerStats());
			getView().getMainPanel().getHallOfFame().drawPlayers();
			
		}		
	}
	

	/*Method used to add a player in the already existing player Catalogue.*/
	
	public void addPlayer(Player newPlayer) {
		this.model.getPlayerCatalogue().addPlayer(newPlayer);
	}
	
	/*********************************/
	/************getters**************/
	
	public GameModel getModel() {
		return model;
	}
	
	public MainWindow getView() {
		return view;
	}

	
			
	
}