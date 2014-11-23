package com.battleship.controllers;

import java.io.File;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

import com.battleship.buttons.Rectangle;
import com.battleship.panels.Map;
import com.battleship.panels.SelectShips;
import com.battleship.panels.Stats;
import com.battleship.players.Player;
import com.battleship.utils.ButtonEnum;
import com.battleship.utils.Constants;

public class PlayerController {

	public static boolean GAME_STARTED = false;
	
	private Player player;

	public PlayerController(Player pl) {
		this.player = pl;
	};

	public void notifyGCSelectionFinished(){
		
		player.setSelected(true);
		player.getGc().notifySelectionDone();
		
	}
	//notifies other players map  that shot has been made!!
	private ButtonEnum notifyOtherMapToShoot( int x , int y){
		ButtonEnum be = player.getGc().notifyMapsShotBeenMade(player.getPlayerNumber(), x, y);
		if( be == ButtonEnum.EXPLODED){	
			Stats s = this.player.getStat();
			s.increaseScore();
			this.player.getGc().isGameFinished();
		}
		return be;
	}
	public void handleOnClick(Rectangle b) {
		
		if (GAME_STARTED && !b.isOwnMap()) {
			ButtonEnum type;
			type = notifyOtherMapToShoot(b._getX(), b._getY());
			
			if(type == ButtonEnum.EXPLODED){
				b.setExplode();
				JOptionPane.showMessageDialog(null, "Yess!! You Hit!! \n\n After pressing OK , Other Player will play ", "Hit Message", JOptionPane.INFORMATION_MESSAGE);
			}
			else if( type == ButtonEnum.MISSED){
				b.setMissed();
				JOptionPane.showMessageDialog(null, "Don't be sorry , you will try again! \n\n After pressing OK , Other Player will play ", "Missed Message", JOptionPane.INFORMATION_MESSAGE);
				
			}
			else{
				//do nothing
			}
			
			player.getGc().changePlayerOfGame();
			
		} else {
			if (SelectShips.SELECTED_SHIP_SIZE == 0) {
				// Do Nothing!!
				return;
			}
			if (b.getType() == ButtonEnum.WATER && b.isOwnMap()) {
				Object[] options = { "Vertical", "Horizontal" };

				int n = JOptionPane.showOptionDialog(null,
						"Vertical? or Horizontal?", "BattleShip",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, // do not use a
															// custom Icon
						options, // the titles of buttons
						options[0]); // default button title

				if (n == JOptionPane.YES_OPTION) {
					// Put Vertical
					if (isAvalaible(this.player.getOwnMap(), b._getX(),
							b._getY(), SelectShips.SELECTED_SHIP_SIZE,
							Constants.VERTICAL)) {
						putVertical(this.player.getOwnMap(), b._getX(),
								b._getY(), SelectShips.SELECTED_SHIP_SIZE);
						SelectShips.SELECTED_SHIP_SIZE = 0;
					} else {

						JOptionPane.showMessageDialog(null,
								"Ops The Place is not Avalaible!! \nPlease Try Again..");
					}

				} else if(n == JOptionPane.NO_OPTION) {
					// put Horizontal
					if (isAvalaible(this.player.getOwnMap(), b._getX(),
							b._getY(), SelectShips.SELECTED_SHIP_SIZE,
							Constants.HORIZONTAL)) {
						putHorizontal(this.player.getOwnMap(), b._getX(),
								b._getY(), SelectShips.SELECTED_SHIP_SIZE);

						SelectShips.SELECTED_SHIP_SIZE = 0;
					} else {
						JOptionPane.showMessageDialog(null,
								"Ops The Place is not Avalaible!!");
					}

				}
				else{
					//Do Nothing
					
				}
			}
		}
	}

	private void putVertical(Map m, int fx, int fy, int size) {

		Rectangle[][] list = m.getList();

		for (int i = 0; i < size; i++) {
			if(i == 0){
				m.addShip(list[fx][fy], Constants.VERTICAL, size);
			}
			list[fx + i][fy].setputShip();

		}
	}

	private void putHorizontal(Map m, int fx, int fy, int size) {

		Rectangle[][] list = m.getList();

		for (int i = 0; i < size; i++) {
			if(i == 0){
				m.addShip(list[fx][fy], Constants.HORIZONTAL, size);
			}
			list[fx][fy + i].setputShip();
			
		}
	}

	private boolean isAvalaible(Map m, int fx, int fy, int size, int direction) {

		Rectangle[][] list = m.getList();

		if (direction == Constants.VERTICAL) {
			for (int i = 0; i < size; i++) {

				if ((fx + i) >= Constants.MAP_SIZE
						|| list[fx + i][fy].getType() != ButtonEnum.WATER) {
					return false;
				}
			}
		} else {
			// HORIZONTAL
			for (int i = 0; i < size; i++) {

				if ((fy + i) >= Constants.MAP_SIZE
						|| list[fx][fy + i].getType() != ButtonEnum.WATER) {
					return false;
				}
			}
		}
		return true;
	}
	public void putShipsAutomatically(){
		
		
		
		Random r = new Random();
		Map m = player.getOwnMap();
		
		int x = r.nextInt(20);
		int y = r.nextInt(20);
		int dir = r.nextInt(2);
		
		while(!isAvalaible(m, x, y, Constants.BATTLESHIP, dir)){
			x = r.nextInt(20);
			y = r.nextInt(20);
			dir = r.nextInt(2);
		}
		putAccordingtoDirection(m,x,y,Constants.BATTLESHIP,dir);
		
		while(!isAvalaible(m, x, y, Constants.DESTROYER, dir)){
			x = r.nextInt(20);
			y = r.nextInt(20);
			dir = r.nextInt(2);
		}
		putAccordingtoDirection(m,x,y,Constants.DESTROYER,dir);
		
		while(!isAvalaible(m, x, y, Constants.BOAT, dir)){
			x = r.nextInt(20);
			y = r.nextInt(20);
			dir = r.nextInt(2);
		}
		putAccordingtoDirection(m,x,y,Constants.BOAT,dir);
		
		while(!isAvalaible(m, x, y, Constants.CARRIER, dir)){
			x = r.nextInt(20);
			y = r.nextInt(20);
			dir = r.nextInt(2);
		}
		putAccordingtoDirection(m,x,y,Constants.CARRIER,dir);
		
		
		while(!isAvalaible(m, x, y, Constants.SUBMARINE, dir)){
			x = r.nextInt(20);
			y = r.nextInt(20);
			dir = r.nextInt(2);
		}
		putAccordingtoDirection(m,x,y,Constants.SUBMARINE,dir);
		
		player.setSelected(true);
		notifyGCSelectionFinished();
	}
	private void putAccordingtoDirection( Map m , int fx , int fy , int size , int dir){
		if(dir == Constants.VERTICAL){
			putVertical(m, fx, fy, size);
		}
		else if(dir == Constants.HORIZONTAL){
			putHorizontal(m, fx, fy, size);
		}
	}
	
	public static synchronized void playSound(final String url) {
		  new Thread(new Runnable() {
		  // The wrapper thread is unnecessary, unless it blocks on the
		  // Clip finishing; see comments.
		    public void run() {
		      try {
		        Clip clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("sounds/"+url));
		        clip.open(inputStream);
		        clip.start(); 
		      } catch (Exception e) {
		        System.err.println(e.getMessage());
		      }
		    }
		  }).start();
		}
}
