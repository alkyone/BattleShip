package com.battleship.players;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.battleship.controllers.GameController;
import com.battleship.controllers.PlayerController;
import com.battleship.panels.Map;
import com.battleship.panels.SelectShips;
import com.battleship.panels.Stats;
import com.battleship.utils.Constants;

public class Player {

	private static int playerCount = 0;

	private boolean selected = false;
	private boolean auto = false;
	
	private Map ownMap;
	private Map rivalsMap;
	private Stats stat;
	private SelectShips ss;
	private PlayerController ct;
	private JPanel panel;
	private int playerNumber;
	// private String name;
	private GameController gc;

	public Player(GameController gc) {

		this.ct = new PlayerController(this);
		this.ownMap = new Map(ct, true);
		this.rivalsMap = new Map(ct, false);
		this.ss = new SelectShips(ct);
		this.stat = new Stats();
		this.gc = gc;
		if (playerCount == 0) {
			playerNumber = Constants.PLAYER_ONE;
		} else {
			playerNumber = Constants.PLAYER_TWO;
		}
		playerCount++;

	}

	public void loadBeginingPanel() {
		gc.getFrame().setVisible(false);
		
		panel = null;

		// Players Panel
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		getClass();

		int selection = JOptionPane.showOptionDialog(null,
				"Locate Ships Automatically Or Manually ? ", "Player "
						+ (this.playerNumber+1), JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, new String[] {
						"Automatic", "Manual" }, "default");
		if (selection == JOptionPane.OK_OPTION) {
			this.auto = true;
			
			ct.putShipsAutomatically();

		} else {

			// TextBoxes
			JPanel textPanel = new JPanel(new GridLayout(1, 2));
			JLabel text1 = new JLabel("Your Map");
			text1.setBorder(BorderFactory.createLineBorder(Color.black));
			text1.setHorizontalAlignment(JLabel.CENTER);
			textPanel.add(text1);
			panel.add(textPanel);

			// Maps
			JPanel panel1 = new JPanel(new GridLayout(1, 2));
			panel1.add(this.ownMap);
			panel1.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(panel1);

			JLabel text = new JLabel("Please Locate Your Ships..");
			text.setFont(text.getFont().deriveFont(20f));
			text.setAlignmentX(JPanel.CENTER_ALIGNMENT);
			panel.add(text);

			this.ss.setAlignmentX(Component.CENTER_ALIGNMENT);
			panel.add(this.ss);
		}
	}

	public void loadGamePanel() {
		// Players Panel
		panel = null;

		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// TextBoxes
		JPanel textPanel = new JPanel(new GridLayout(1, 2));
		JLabel text1 = new JLabel("Your Map");

		JLabel text2 = new JLabel("Enemy's Map");
		text1.setHorizontalAlignment(JLabel.CENTER);
		text2.setHorizontalAlignment(JLabel.CENTER);
		textPanel.add(text1);
		textPanel.add(text2);
		panel.add(textPanel);

		// Maps
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
		panel1.add(this.ownMap);
		panel1.add(stat);
		panel1.add(this.rivalsMap);

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("images/game2.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		JPanel p = new JPanel(new FlowLayout());
		p.add(picLabel);

		panel.add(panel1);
		panel.add(p);

	}

	public void notifyGCSelectionDone() {

		this.selected = true;

	}

	public Map getOwnMap() {
		return ownMap;
	}

	public void setOwnMap(Map ownMap) {
		this.ownMap = ownMap;
	}

	public Map getRivalsMap() {
		return rivalsMap;
	}

	public void setRivalsMap(Map rivalsMap) {
		this.rivalsMap = rivalsMap;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public GameController getGc() {
		return gc;
	}

	public void setGc(GameController gc) {
		this.gc = gc;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Stats getStat() {
		return stat;
	}

	public void setStat(Stats stat) {
		this.stat = stat;
	}

	public boolean isAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	

}
