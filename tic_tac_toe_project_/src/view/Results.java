package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.GameController;

public class Results extends GamePanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField results;
	
	/************************/
	/*****Constructors*****/
	
	Results(GameController gc,String winner){
		
		super(gc);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		setSize(new Dimension(MainWindow.WIDTH,MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(new Color(197, 228, 235),1,true));
		
		results = new JTextField();
		results.setPreferredSize(new Dimension(600,40));
		results.setMaximumSize(results.getPreferredSize() );
		results.setAlignmentX(CENTER_ALIGNMENT);
		results.setHorizontalAlignment(JTextField.CENTER);
		results.setEnabled(false);
		Font font1 = new Font("SansSerif", Font.BOLD, 40);
		results.setFont(new Font("Times New Roman", Font.ITALIC, 25));
		add(results);
		results.setFont(font1);
		results.setSize(150,20);
		if(winner=="X"|| winner=="O") {
			this.results.setText("The winner is " + "" +winner);
		}else {
			this.results.setText(winner);
		}
		this.repaint();
		
		this.setVisible(true);
	}

	/*********************************/
	/********getters setters**********/
	
	public JTextField getResults() {
		return results;
	}
	public void setResults(JTextField results) {
		this.results = results;
	}
	
}