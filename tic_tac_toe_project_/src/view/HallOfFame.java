package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import control.GameController;
import model.ChartList;
import model.Player;
import model.*;

public class HallOfFame extends GamePanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea bestPlayers; 
	
	/************************/
	/*****Constructors*****/
	
	public HallOfFame(GameController gc) {
		super(gc);
		this.setBackground(new Color(179, 255, 194));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(new Color(197, 228, 235)));
		this.bestPlayers = new JTextArea(20,100);
		this.bestPlayers.setPreferredSize(new Dimension(MainWindow.WIDTH - 2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.bestPlayers.setAlignmentX(CENTER_ALIGNMENT);
		this.bestPlayers.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		this.bestPlayers.setBackground(Color.gray);
		this.bestPlayers.setVisible(true);
		this.bestPlayers.setMargin(new Insets(10,10,10,10));
		this.drawPlayers();
		this.bestPlayers.setVisible(true);
		this.bestPlayers.setEnabled(false);
		this.add(bestPlayers);

	}
	
	/************************/
	/*****Methods*****/
 
	/*Draws HallOfFame*/
	
	public void drawPlayers() {		
		//stringBuffer to display the wanted message in the HOF 
		DecimalFormat df = new DecimalFormat("##.##");

		StringBuilder message = new StringBuilder("");
		message.append("\t       ").append("H A L L   O F   F A M E ").append("\n\n");
		
		int index = gc.getModel().getPlayerCatalogue().gamePlayers.size();
		Player[] allPlayers= new Player[index];
		for(int i = 0 ; i < index; i ++) {
			allPlayers[i] = gc.getModel().getPlayerCatalogue().gamePlayers.get(i);
		}
		Player[] sorted =gc.getModel().getPlayerCatalogue().SortMyList(); //returns the players sorted by score  [using compareTo and arrays.sort()]
		for(int i = 0; i < index;i++) {
			if(i<10) {
				message.append("\n").append("\t").append(i+1).append(".").append(sorted[i].getName()).append("\t\t").
				append(df.format(sorted[i].findScore())).append("%").append("\n");
			}
		}
		this.bestPlayers.setText(message.toString());
	}
	
}