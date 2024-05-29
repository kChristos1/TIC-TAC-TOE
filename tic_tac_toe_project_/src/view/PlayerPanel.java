package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import control.GameController;
import model.PlayersCatalogue;
import model.Player;

public class PlayerPanel extends GamePanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton selectPlayerBtn;
	JButton startGameBtn;
	int pos;
	String currentPlayer;
	JTextField plName;
	JLabel plMark;
	JTextArea plStats;
	String[] allPlayers;
	int numOfPlayers=0;
	
	/************************/
	/*****Constructors*****/
	
	public PlayerPanel(GameController c, int pos) {
		super(c);
		
		this.pos=pos;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT - MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(new Color(153, 204, 255),1));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setBackground( Color.LIGHT_GRAY);
		
		//selectPlayerBtn
		selectPlayerBtn = new JButton("Choose Player");
		selectPlayerBtn.setPreferredSize(new Dimension(150,40));
		selectPlayerBtn.setAlignmentX(CENTER_ALIGNMENT);
		selectPlayerBtn.addActionListener((e)->{changePlayer();});
		this.add(selectPlayerBtn);
		
		plName = new JTextField();
		plName.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,40));
		plName.setMaximumSize(plName.getPreferredSize() );
		plName.setAlignmentX(CENTER_ALIGNMENT);
		plName.setBackground(Color.gray);
		plName.setHorizontalAlignment(JTextField.CENTER);
		plName.setEnabled(false);
		
		plMark = new JLabel(pos==0? "X" : "O");
		plMark.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,80));
		plMark.setAlignmentX(CENTER_ALIGNMENT);
		plMark.setHorizontalAlignment(JTextField.CENTER);
		plMark.setEnabled(false);
		Font markf = new Font("SansSerif", Font.PLAIN,90);
		plMark.setBackground(Color.white);
		plMark.setFont(markf);
		
		//startGameBtn
		startGameBtn = new JButton("Start Game");
		startGameBtn.setPreferredSize(new Dimension(200, 40));
		startGameBtn.setAlignmentX(CENTER_ALIGNMENT);
		startGameBtn.addActionListener((e)-> {
			/*Is in charge of who makes the first move in the beginning depending on the type of Players (Human or AI).
			 *Also unfolds the game AI vs AI as no clock is used. 
			 */
			gc.startGame(); //once the startGameBtn is pressed we want to start playing
			if(pos == 0) {
				if(gc.getModel().getGamePlayers()[0].getName().equals("MrBean") ) {
					gc.getModel().setMover(true); //if mover is true ,it means that its X's turn
					gc.getModel().randomMove();
					if(gc.getModel().getGamePlayers()[1].getName().equals("Hal")) {
						this.getModel().aiGame();//unfolds AI vs AI game.
					}
				}else {
					if(gc.getModel().getGamePlayers()[0].getName().equals("Hal") ) {
					gc.getModel().setMover(true); //if mover is true ,it means that its X's turn
					gc.getModel().hal();
					if(gc.getModel().getGamePlayers()[1].getName().equals("MrBean") ) {
						this.getModel().aiGame();//unfolds AI vs AI game.
					}
					
				}
				else
					gc.getModel().setMover(true);
			}
			}
			else if(pos==1){ //position == 1 
				if(gc.getModel().getGamePlayers()[1].getName().equals("MrBean")) {
					gc.getModel().setMover(false); //if mover is true ,it means that its O's turn
					gc.getModel().randomMove();
					if(gc.getModel().getGamePlayers()[0].getName().equals("Hal") ) {
						this.getModel().aiGame();//unfolds AI vs AI game.
					}
				}
				else {
					if(gc.getModel().getGamePlayers()[1].getName().equals("Hal")) {
						gc.getModel().setMover(false); //if mover is true ,it means that its O's turn
						gc.getModel().hal();
						if(gc.getModel().getGamePlayers()[0].getName().equals("MrBean") ) {
							this.getModel().aiGame();//unfolds AI vs AI game.
						}
					}
					else{
						//Human vs Human
						gc.getModel().setMover(false);
					}
				}
				
			}
		});
		
		this.startGameBtn.setEnabled(false);
		this.add(startGameBtn);
		
		plStats = new JTextArea(10,100);		
		plStats.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,400));
		plStats.setAlignmentX(CENTER_ALIGNMENT);
		Font statsf = new Font("Times New Roman", Font.PLAIN,20);
		plStats.setFont(statsf);
		plStats.setEnabled(false);		
		plStats.setBackground(Color.gray);
		plStats.setMargin(new Insets(10, 10, 10, 10));

		this.add(plMark);
		this.add(plName);		
		this.add(plStats);
	}

	/************************/
	/*****Methods*****/
	
	public void changePlayer() {
		
		//Get the list of all players
		this.allPlayers = new String[gc.getModel().getPlayerCatalogue().getPlayers().size()];
		for(int i =0 ; i<gc.getModel().getPlayerCatalogue().getPlayers().size() ; i++) {
			allPlayers[i] =gc.getModel().getPlayerCatalogue().getPlayersName(i);
			}
		Arrays.sort(allPlayers);
		
		//Show Player Selection Dialog
		String selPlayer = (String) JOptionPane.showInputDialog(this, 
				"Choose a Player...",
				"Player selection",
				JOptionPane.PLAIN_MESSAGE,
				null,
				allPlayers,
				currentPlayer
				);
			this.currentPlayer=selPlayer;
			//gc.selectPlayer(gc.getModel().getPlayerCatalogue().findPlayerByName(selPlayer),pos);
			if(selPlayer!=null) {
				if(checkingGameValidity(gc.getModel().getPlayerCatalogue().findPlayerByName(selPlayer))==0) {
					gc.selectPlayer(gc.getModel().getPlayerCatalogue().findPlayerByName(selPlayer),pos);
					this.setPlayerStats(gc.getModel().getPlayerCatalogue().findPlayerByName(selPlayer).playerStats());//shows selplayer's(current) stats
					this.plName.setText(selPlayer);//changed to selplayer
				}
				else {
					return;
				}
				if(pos == 0) {
					this.gc.getModel().setMover(true);
				}
				else {
					this.gc.getModel().setMover(false);
				}
			this.repaint();
			}
		
	}
	
	/*Checks if a player is already selected.
	 * 
	 */
	public int checkingGameValidity(Player selPlayer) {
		if (selPlayer==gc.getModel().getGamePlayers()[pos==0?1:0]) {
			JOptionPane.showMessageDialog(gc.getView(), 						
					"Player already selected",
					"Ooops...",
					JOptionPane.ERROR_MESSAGE);
			return 1;
		}	
		return 0;
	}   
		
	
	/*********************************/
	/********getters setters**********/
	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public JTextField getPlName() {
		return plName;
	}
	
	public JTextArea getPlStats() {
		return plStats;
	}
	
	public void setPlayerStats(String stats) {
		this.plStats.setText(stats);
	}

	public JButton getSelectPlayerBtn() {
		return selectPlayerBtn;
	}


	public JComponent getStartGameBtn() {
		return this.startGameBtn;
	}


}