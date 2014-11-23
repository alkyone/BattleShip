package com.battleship.panels;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Stats extends JPanel {

	private JLabel score;
	private JLabel left;
	
	
	private int squareCount = 17;
	private int hitSquare = 0;
	
	public Stats() {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		init();
	}
	public void init(){
		score = new JLabel();
		left = new JLabel();
		
		prepare();
		
		add(score);
		add(left);
		
	}
	public void prepare(){
		score.setBackground(Color.ORANGE);
		score.setText("Hit : " + hitSquare );
		left.setText("Ships Left :" + (squareCount - hitSquare));
	}
	public void increaseScore(){
		this.hitSquare++;
		prepare();
	}

	public boolean isGameFinished(){
		
		if((squareCount - hitSquare) == 0 ){
			
			return true;
		}
		
		return false;
	}
	
	public int getSquareCount() {
		return squareCount;
	}

	public void setSquareCount(int squareCount) {
		this.squareCount = squareCount;
	}
}
