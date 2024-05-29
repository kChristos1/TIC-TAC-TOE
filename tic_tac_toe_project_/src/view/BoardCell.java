package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.GameController;
import model.GameModel;
import model.Player;

@SuppressWarnings("serial")
public class BoardCell extends GamePanel implements MouseListener {

	public static final int CELL_PADDING = 10;

	int row, col;	

	public boolean highlighted;
	Player currentPlayer;
	
	/************************/
	/*****Constructors*****/
	
	public BoardCell(GameController gc, int row, int col) {
		super(gc);
		this.setBackground(Color.WHITE);
		this.row = row;
		this.col = col;
		this.addMouseListener(this);
		this.highlighted = false;
	}

	/************************/
	/*****Methods*****/
	
	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse entered cell " + this);
		this.highlight();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse exited on cell " + this);
		this.unHighlight();
	}

	private void highlight() {
		if (!highlighted && getModel().inPlay()) {
			highlighted = true;
			repaint();
		}
	}

	private void unHighlight() {
		if (highlighted && getModel().inPlay()) {
			highlighted = false;
			repaint();
		}

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBorder(new LineBorder(Color.DARK_GRAY, 1));

		String mark = getModel().getBoardMark(this.row, this.col);
		Graphics2D g2d = (Graphics2D) g;
		int size = this.getSize().width - 2 * CELL_PADDING;
		g2d.setStroke(new BasicStroke(6));
		if (mark == null) {
			if (highlighted) {
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(CELL_PADDING, CELL_PADDING, size, size);
			}
			return;
		} else if (mark.equals("X")) {
			g2d.drawLine(CELL_PADDING, CELL_PADDING, CELL_PADDING + size, CELL_PADDING + size);
			g2d.drawLine(CELL_PADDING + size, CELL_PADDING, CELL_PADDING, CELL_PADDING + size);
		} else {
			g2d.drawOval(CELL_PADDING, CELL_PADDING, size, size);
		}

	}

	@Override
	public String toString() {
		return "(" + this.row + "," + this.col + ")";
	}
	
	/*This method is called everytime a board cell is clicked on. Depending on the type of inGame players (Human or AI) it acts accordingly.
	 * It is used in AI's games for the second move and above.
	 */
				
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked on cell " + this);
		
		//Checks firstly for inGame State.
		if(getModel().getMoves() < 9 && getModel().checksForWin()=="1") {
			//Checks if one of the chosen players is MrBean(random AI player).
			if(gc.getModel().getGamePlayers()[0].getName().equals("MrBean") ||gc.getModel().getGamePlayers()[1].getName().equals("MrBean")) {
				//System.out.println(gc.getModel().getMover());
				
				//Human move.
				gc.getModel().makeMove(row, col);  //player plays
			
			//MrBean move.
			if(getModel().getMoves() < 9 && getModel().checksForWin()=="1") {
				this.gc.getModel().randomMove();
				repaint();
			}
		
	   }else{
		//Checks if one of the chosen players is Hal(perfect AI player).
		   if(gc.getModel().getGamePlayers()[0].getName().equals("Hal") ||gc.getModel().getGamePlayers()[1].getName().equals("Hal")) {
			   //System.out.println(gc.getModel().getMover());
			   gc.getModel().makeMove(row, col);  //player plays
			if(getModel().getMoves() < 9 && getModel().checksForWin()=="1") {
				gc.getModel().hal();//bean makes move
				repaint();
				}
		   }
	   }
		//Only human players game(neither MrBean nor Hal in game)
		if (getModel().inPlay()) {
			getModel().makeMove(row, col);
			repaint();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
}