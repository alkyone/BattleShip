package com.battleship.panels;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.battleship.players.Player;

@SuppressWarnings("serial")
public class Finish extends JPanel {

	private Player player;
	
	public Finish( Player winningPlayer ){
		super();
		
		this.player = winningPlayer;
	
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		init();
	}
	private void init(){
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("images/stewie.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		
		picLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel text = new JLabel("PLAYER " + (player.getPlayerNumber()+1));
		text.setPreferredSize(new Dimension(100, 50));
		
		text.setAlignmentX(CENTER_ALIGNMENT);
		
		add(text);
		add(picLabel);
		
		
		
	}
	/**
	 * Paneli Hazýrla!!
	 * */
	
}
