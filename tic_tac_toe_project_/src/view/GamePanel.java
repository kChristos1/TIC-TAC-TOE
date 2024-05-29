package view;

/* Changes for Lab 08 
 * This Abstract class binds controller to all GUI panels...
 * */


import javax.swing.JPanel;

import control.GameController;
import model.GameModel;

public abstract class GamePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	GameController gc;
	
	/************************/
	/*****Constructors*****/
	public GamePanel(GameController gc) {
		super();
		this.gc = gc;
	}
	
	/*********************************/
	/************getters**************/
	protected GameController getController() {
		return this.gc;
	}	

	public GameModel getModel() {
		return gc.getModel();
	}
}