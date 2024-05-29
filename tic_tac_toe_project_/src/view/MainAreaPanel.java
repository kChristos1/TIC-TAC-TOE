package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.GameController;

public class MainAreaPanel extends GamePanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String HOF = "HALL_OF_FAME";
	public static final String BOARD = "GAME_BOARD";
		
	HallOfFame hallOfFame;
	JPanel gameBoard;
	CardLayout cards;
	
	/************************/
	/*****Constructors*****/
	
	public MainAreaPanel(GameController gc) {
		super(gc);
		this.cards= new CardLayout();
		this.setLayout(this.cards);
		this.setPreferredSize(new Dimension(MainWindow.WIDTH - 2 * MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT - MainWindow.TOP_HEIGHT));
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(new Color(197, 228, 235),1,true));		
	
		hallOfFame = new HallOfFame(this.gc);			
		gameBoard = new GameBoard(this.gc);					
		this.add(HOF,hallOfFame);
		this.add(BOARD,gameBoard);
	}
	
	/*Switches perspective from HallToFame to GameBoard and the other way around.*/
	
	public void showCard(String s) {		
		cards.show(this, s);		
	}

	/*********************************/
	/********getters setters**********/
	
	public HallOfFame getHallOfFame() {
		return hallOfFame;
	}


	public void setHallOfFame(HallOfFame hallOfFame) {
		this.hallOfFame = hallOfFame;
	}
	
}