package com.battleship.controllers;

import java.awt.Dimension;

import javax.swing.JFrame;

import com.battleship.buttons.Rectangle;
import com.battleship.panels.Finish;
import com.battleship.players.Player;
import com.battleship.utils.ButtonEnum;
import com.battleship.utils.Constants;

public class GameController {

	private Player player1;
	private Player player2;
	private JFrame frame;

	private int currentPanel = Constants.PLAYER_ONE;

	public GameController() {

		this.player1 = new Player(this);
		this.player2 = new Player(this);

	}

	public void notifySelectionDone() {

		if (player1.isSelected()) {
			if (player2.isSelected()) {
				// Game May Start
				startGame();
			} else {
				this.player2.loadBeginingPanel();
				if(!player2.isAuto()){
					loadPlayerPanels(Constants.PLAYER_TWO);
				}
				
			}
		}

	}

	public ButtonEnum makeShot(Rectangle r) {

		if (r.getType() == ButtonEnum.SHIP) {
			PlayerController.playSound("explosion.wav");
			r.setExplode();
			return r.getType();
		} else if (r.getType() == ButtonEnum.WATER) {
			PlayerController.playSound("fail.wav");
			r.setMissed();
			return r.getType();
		} else {
			return null;
		}

	}

	public ButtonEnum notifyMapsShotBeenMade(int player, int x, int y) {

		if (player == Constants.PLAYER_ONE) {

			Rectangle r = player2.getOwnMap().getList()[x][y];
			return makeShot(r);

		} else {
			Rectangle r = player1.getOwnMap().getList()[x][y];
			return makeShot(r);
		}

	}

	private void preparePlayerGamePanels() {
		// loads players panels
		this.player1.loadGamePanel();
		this.player2.loadGamePanel();
	}

	private void loadPlayerPanels(int player) {

		if (player == Constants.PLAYER_ONE) {
			frame.setVisible(false);
			if(player2.getPanel() != null){
				frame.remove(player2.getPanel());
			}
			
			frame.setTitle("BattleShip -Player 1-");

			frame.add(player1.getPanel());

			frame.setVisible(true);

		} else {
			// frame.removeAll();
			frame.setVisible(false);

			frame.setTitle("BattleShip -Player 2-");
			if(player2.getPanel() != null){
				frame.remove(player1.getPanel());
			}
			frame.add(player2.getPanel());

			frame.setVisible(true);
		}

	}

	public void changePlayerOfGame() {

		if (currentPanel == Constants.PLAYER_ONE) {

			frame.setVisible(false);

			frame.remove(player1.getPanel());

			frame.setTitle("BattleShip -Player 1-");

			frame.add(player2.getPanel());

			frame.setVisible(true);
			currentPanel = Constants.PLAYER_TWO;

			System.out.println("11");
		} else {
			System.out.println("22");
			frame.setVisible(false);

			frame.setTitle("BattleShip -Player 2-");
			frame.remove(player2.getPanel());
			frame.add(player1.getPanel());

			frame.setVisible(true);
			currentPanel = Constants.PLAYER_ONE;
		}

	}

	private void startGame() {
		frame.setVisible(false);
		frame.setPreferredSize(new Dimension(900, 600));
		frame.setMinimumSize(new Dimension(900, 600));
		frame.remove(player2.getPanel());

		PlayerController.GAME_STARTED = true;
		preparePlayerGamePanels();
		loadPlayerPanels(Constants.PLAYER_ONE);

	}

	public void run(){
		
		this.player1.loadBeginingPanel();
		frame.setPreferredSize(new Dimension(450, 600));
		frame.setMinimumSize(new Dimension(450, 600));
		if(!player1.isAuto()){
			loadPlayerPanels(Constants.PLAYER_ONE);
		}
	}

	public void isGameFinished() {

		if (player1.getStat().isGameFinished()) {
			finishGame(Constants.PLAYER_ONE);
		} else if (player2.getStat().isGameFinished()) {
			finishGame(Constants.PLAYER_TWO);
		}

	}

	public void finishGame(int player) {
		frame.setVisible(false);
		if (player == Constants.PLAYER_ONE) {
			frame.remove(player1.getPanel());
			frame.add(new Finish(player1));
			frame.setPreferredSize(new Dimension(250,500));
			frame.setMinimumSize(new Dimension(250, 500));

		} else if (player == Constants.PLAYER_TWO) {
			frame.remove(player1.getPanel());
			frame.add(new Finish(player2));
			frame.setPreferredSize(new Dimension(250, 500));
			frame.setMinimumSize(new Dimension(250, 500));
		}
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
}
