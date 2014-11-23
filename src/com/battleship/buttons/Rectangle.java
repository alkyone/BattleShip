package com.battleship.buttons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.battleship.controllers.PlayerController;
import com.battleship.utils.ButtonEnum;

@SuppressWarnings("serial")
public class Rectangle extends JPanel implements MouseListener {

	// Coordinates of button
	private int _x;
	private int _y;
	//private Color currentColor;
	private PlayerController cntrl;
	public final static int SIZE = 30;
	// enum
	private BufferedImage currentImage;
	
	private ButtonEnum type;
	private boolean ownMap;

	public Rectangle(PlayerController ct, Boolean isOwn) {
		// super();

		this.cntrl = ct;

		this.ownMap = isOwn;
		this.setBorder(BorderFactory.createLineBorder(new Color(0,206,209)));
		setWater();
		

	}

	public void setWater() {

		//this.currentColor = Color.BLUE;
		
		try {
			currentImage = ImageIO.read(new File("images/waters.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// set enum to indicate this button is water
		this.type = ButtonEnum.WATER;

		
		this.addMouseListener(this);
		invalidate();
		repaint();

	}

	public void setExplode() {
		// TODO find a explode Image

		//this.currentColor = Color.RED;

		try {
			currentImage = ImageIO.read(new File("images/exploded.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// set enum to indicate this button is exploded
		this.type = ButtonEnum.EXPLODED;

		// Click option is expired
		this.removeMouseListener(this);

		repaint();
	}

	public void setputShip() {
		// set enum to indicate the button belongs to a ship
		this.type = ButtonEnum.SHIP;
		this.setBorder(null);
		this.setOpaque(false);
		repaint();
	}

	public void setMissed() {
		//find a missed Image
		try {
			currentImage = ImageIO.read(new File("images/misseds.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// set enum to indicate the shot is missed
		this.type = ButtonEnum.MISSED;

		// Click option is expired
		this.removeMouseListener(this);

		invalidate();
		repaint();
	}

	public int _getX() {
		return _x;
	}

	public void _setX(int x) {
		this._x = x;
	}

	public int _getY() {
		return _y;
	}

	public void _setY(int y) {
		this._y = y;
	}

	public ButtonEnum getType() {
		return type;
	}

	public void setType(ButtonEnum type) {
		this.type = type;
	}

	public boolean isOwnMap() {
		return ownMap;
	}

	public void setOwnMap(boolean ownMap) {
		this.ownMap = ownMap;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if(type == ButtonEnum.SHIP){
			g2.setBackground(new Color(0, 0,  0,  255));
			return;
		}
		

		
		
		g.drawImage(currentImage, 0 , 0, null);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		cntrl.handleOnClick(this);

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
