package model;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

public class PlayersCatalogue {

	public ChartList<Player> gamePlayers;
	Player[] playerArray;
	
	/*****************************************/

	public PlayersCatalogue() {
		gamePlayers = new ChartList <Player>(1);

	}
	
	/*****************************************/

	//returns the player that has the wanted name 
	public Player findPlayerByName(String plName) {
		for(int i=0; i<gamePlayers.size();i++) {
			if(gamePlayers.get(i).getName().equals(plName)) {
				return gamePlayers.get(i);
			}
	    }				
		return null;
     }
	
	
	
	/*
	 * sorts players by score using the Arrays.sort(Player[])
	 * method, comparing two players based on their score as the
	 * compareTo() method says in Player class 
	 */
	public Player[] SortMyList() {
		Player[] playerArray=new Player[gamePlayers.size()];
		for(int i=0;i<gamePlayers.size();i++) {
			if(gamePlayers.get(i)!=null) {
				playerArray[i] = this.gamePlayers.get(i);
			}
		}
		
		java.util.Arrays.sort(playerArray);
		return playerArray;
	}
	

	
	/*
	 * returns a ChartList<String> object 
	 * that contains all the names of all the players 
	 * that exist in the catalogue 
	 */
	public ChartList<String> findPlayerNames(){
		ChartList<String> playerNames = new ChartList<String>(gamePlayers.size());
		
		for(int i=0; i<gamePlayers.size(); i++){
			if(gamePlayers.get(i)!=null){
				playerNames.add(gamePlayers.get(i).getName());
			}
		}
		return (playerNames);
	}
	
	
	
	public void addPlayer(Player player) {
		this.gamePlayers.addExtend(player);
	}
	
	

	/*
	 * method used to store all the players
	 * of the catalogue in the tuctactoe.ser 
	 * text file 
	 */
	public void storePlayers() {
		ObjectOutputStream  os = null;
		FileOutputStream fos = null ;	
		try {
			fos = new FileOutputStream("tuctactoe.ser");
			os = new ObjectOutputStream(fos);	
			for(int i = 0 ; i < this.gamePlayers.capacity() ; i++) {
				if(this.gamePlayers.get(i) !=null) {
						os.writeObject(this.gamePlayers.get(i));
						//System.out.println(this.gamePlayers.capacity());
						//System.out.println(this.gamePlayers.size());
						System.out.println("Just stored: "+ gamePlayers.get(i).getName());
				}
			}
		}catch (FileNotFoundException e) {
				System.out.println("Something went wrong while ");
			
		} catch (IOException e) {			
				e.printStackTrace();
		}finally {
				try {
					os.close(); 
					fos.close();
				}catch (Exception e) {
					}
			}
	}
		
	
	
	/*
	 * method used to load (get) 
	 * the players that exist in the 
	 * tuctactoe.ser text file 
	 */
	public void loadPlayers() {
		ObjectInputStream in = null;
		FileInputStream fin = null; 
		try { 
			fin = new FileInputStream("tuctactoe.ser");
			in = new ObjectInputStream(fin); 
		    while (fin.available() > 0){
		    	Player s = (Player)in.readObject();
				if(s!=null)
				this.addPlayer(s);
			}
			System.out.println("Players loaded from <<player.txt>> file");
			}catch (FileNotFoundException e) {
				} catch (IOException e) {			
					e.printStackTrace();
				} catch (ClassNotFoundException e) {	
				}finally {
					try {in.close(); fin.close();}catch (Exception e) {
					}
				}
	}
	

	/*****************************************/
	//getters and setters
	
	public String getPlayersName(int i) {
		return this.gamePlayers.get(i).getName();
	}
	
	
	
	public Player getPlayer(int i) {
		if (i<0 || i>gamePlayers.size()) {
			return null;
		}
		return (gamePlayers.get(i));
	}
	
	
		
	public ChartList<Player> getPlayers() {
		return (gamePlayers);
	}

}