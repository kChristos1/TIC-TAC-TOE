package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import control.GameController;
import model.Player;

public class TopPanel extends GamePanel{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton quitBtn;
	JButton doneBtn;
	JButton addPlayerBtn;
	Player newPlayer;
	
	/************************/
	/*****Constructors*****/
	
	public TopPanel(GameController gc) {
		super(gc);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH,MainWindow.TOP_HEIGHT));
		this.setBackground(new Color(179, 255, 244));
		
		//quitBtn
		quitBtn = new JButton("Quit App");	
		quitBtn.setPreferredSize(new Dimension(100, 40));
		quitBtn.addActionListener((e)->{this.gc.quit();});		
		
		//doneBtn
		doneBtn = new JButton("Done");		
		doneBtn.setPreferredSize(new Dimension(100, 40));		
		doneBtn.setEnabled(false);
		doneBtn.addActionListener((e)->{this.gc.startNewGame();});
		
		//addPlayerBtn
		addPlayerBtn = new JButton("Add Player");		
		addPlayerBtn.setPreferredSize(new Dimension(100, 40));	
		addPlayerBtn.setEnabled(true);
		addPlayerBtn.addActionListener((e)->{addPlayer();});
		
		add(addPlayerBtn);
		add(doneBtn);
		add(quitBtn);
	}

	/*Action listener for addPlayer button.Checks also for added name validity(1-20 char,!null,
	 * already existing player).
	 */
	public Player addPlayer() {
		String name = JOptionPane.showInputDialog("Enter the name of the player(length must be 1-20 characters");
		newPlayer=new Player(name);
		
		if (name.equals("") || name == null) {
		    JOptionPane.showMessageDialog(this, "You did not enter a name!Try again!");
		    return null;
		}		
		if(name.length()>20) {
			JOptionPane.showMessageDialog(gc.getView(), 						
			"The length of the name must be smaller than 20 characters",
			"Ooops...",
			JOptionPane.ERROR_MESSAGE);
			this.repaint();
			return null;
		}
		
		int index =  this.gc.getModel().getPlayerCatalogue().gamePlayers.size(); 
		for(int i = 0 ; i < index; i++)
		{
			if(name.equals(gc.getModel().getPlayerCatalogue().gamePlayers.get(i).getName())) {
				JOptionPane.showMessageDialog(this, "Already Exists!");
			    return null;
			}
		}
		gc.addPlayer(newPlayer);
		return newPlayer;
	}
	
	/*********************************/
	/********getters**********/
		
	public Player getPlayer() {
		return newPlayer;
	}
	
	public JButton getQuitBtn() {
		return quitBtn;
	}

	
	public JButton getDoneBtn() {
		return doneBtn;
	}	
	
}