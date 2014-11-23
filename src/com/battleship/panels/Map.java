package com.battleship.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.battleship.buttons.Rectangle;
import com.battleship.buttons.Ship;
import com.battleship.controllers.PlayerController;
import com.battleship.utils.Constants;

@SuppressWarnings("serial")
public class Map extends JPanel {

	private Rectangle[][] list;
	private PlayerController cntrl;
	private boolean isOwnMap;
	private List<Ship> ship;

	public Map(PlayerController ct, boolean isOwnMap) {
		super(new GridLayout(Constants.MAP_SIZE, Constants.MAP_SIZE));

		this.cntrl = ct;
		this.ship = new ArrayList<>();
		this.isOwnMap = isOwnMap;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		init();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		if (ship != null) {
			for (int i = 0; i < ship.size(); i++) {
				Ship temp = ship.get(i);
				BufferedImage ship = null;
				
				if (temp.getDir() == Constants.HORIZONTAL) {
					try {
						ship = ImageIO.read(new File("images/ship_"+temp.getSize()+"h.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g2.drawImage(ship, temp.getR().getX(),
							temp.getR().getY(), null);
				} else {
					try {
						ship = ImageIO.read(new File("images/ship_"+temp.getSize()+"v.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					g2.drawImage(ship, temp.getR().getX(),
							temp.getR().getY(), null);
				}

			}

		}

	}

	public void init() {
		list = new Rectangle[Constants.MAP_SIZE][Constants.MAP_SIZE];
		Rectangle temp;
		for (int i = 0; i < Constants.MAP_SIZE; i++) {
			for (int j = 0; j < Constants.MAP_SIZE; j++) {

				// create default ImageButton
				temp = new Rectangle(this.cntrl, isOwnMap);
				temp.setPreferredSize(new Dimension(20, 20));
				// Location of Button
				temp._setX(i);
				temp._setY(j);

				// Add Button to Panel
				add(temp);

				// Add temp to list to better handle
				list[i][j] = temp;

			}
		}
	}

	public void addShip(Rectangle r, int dir, int size) {

		Ship temp = new Ship(r, dir, size);

		ship.add(temp);

		repaint();
	}

	public Rectangle[][] getList() {
		return list;
	}

	public void setList(Rectangle[][] list) {
		this.list = list;
	}

}
