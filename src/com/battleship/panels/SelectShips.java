package com.battleship.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.battleship.controllers.PlayerController;
import com.battleship.utils.Constants;

@SuppressWarnings("serial")
public class SelectShips extends JPanel {

	public static int SELECTED_SHIP_SIZE = 0;

	// Counters
	private int cntr_carrier = 0;
	private int cntr_battleship = 0;
	private int cntr_submarine = 0;
	private int cntr_destroyer = 0;
	private int cntr_boat = 0;

	// Buttons
	private JButton b1 = new JButton();
	private JButton b2 = new JButton();
	private JButton b3 = new JButton();
	private JButton b4 = new JButton();
	private JButton b5 = new JButton();
	private JButton b6 = new JButton();

	//Player
	private PlayerController playerCnt;
	
	public SelectShips( PlayerController playerCT) {
		super(new GridLayout(3, 2, 5, 5));
		this.playerCnt = playerCT;
		initButtons();
	}

	private void initButtons() {

		b1.setText((Constants.CARRIER_COUNT - cntr_carrier) + " "
				+ "Carriers Left");
		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (SELECTED_SHIP_SIZE != 0) {
					JOptionPane.showMessageDialog(null,
							"You Cannot Select More Than One Ship at a time");
					return;
				}
				SELECTED_SHIP_SIZE = Constants.CARRIER;
				cntr_carrier++;
				if (cntr_carrier == Constants.CARRIER_COUNT) {
					b1.removeActionListener(this);
					b1.setEnabled(false);
				}
				b1.setText((Constants.CARRIER_COUNT - cntr_carrier) + " "
						+ "Carriers Left");
			}
		});

		b2.setText((Constants.BATTLESHIP_COUNT - cntr_battleship) + " "
				+ "BattleShips Left");
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (SELECTED_SHIP_SIZE != 0) {
					JOptionPane.showMessageDialog(null,
							"You Cannot Select More Than One Ship at a time");
					return;
				}
				SELECTED_SHIP_SIZE = Constants.BATTLESHIP;
				cntr_battleship++;
				if (cntr_battleship == Constants.BATTLESHIP_COUNT) {
					b2.removeActionListener(this);
					b2.setEnabled(false);
				}
				b2.setText((Constants.BATTLESHIP_COUNT - cntr_battleship) + " "
						+ "BattleShips Left");
			}
		});

		b3.setText((Constants.SUBMARINE_COUNT - cntr_submarine) + " "
				+ "SubMarine Left");
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (SELECTED_SHIP_SIZE != 0) {
					JOptionPane.showMessageDialog(null,
							"You Cannot Select More Than One Ship at a time");
					return;
				}
				SELECTED_SHIP_SIZE = Constants.SUBMARINE;
				cntr_submarine++;
				if (cntr_submarine == Constants.SUBMARINE_COUNT) {
					b3.removeActionListener(this);
					b3.setEnabled(false);
				}
				b3.setText((Constants.SUBMARINE_COUNT - cntr_submarine) + " "
						+ "SubMarine Left");
			}
		});

		b4.setText((Constants.DESTROYER_COUNT - cntr_destroyer) + " "
				+ "Destroyer Left");
		b4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (SELECTED_SHIP_SIZE != 0) {
					JOptionPane.showMessageDialog(null,
							"You Cannot Select More Than One Ship at a time");
					return;
				}
				SELECTED_SHIP_SIZE = Constants.DESTROYER;
				cntr_destroyer++;
				if (cntr_destroyer == Constants.DESTROYER_COUNT) {
					b4.removeActionListener(this);
					b4.setEnabled(false);
				}
				b4.setText((Constants.DESTROYER_COUNT - cntr_destroyer) + " "
						+ "Destroyer Left");
			}
		});

		b5.setText((Constants.BOAT_COUNT - cntr_boat) + " " + "Boat Left");
		b5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (SELECTED_SHIP_SIZE != 0) {
					JOptionPane.showMessageDialog(null,
							"You Cannot Select More Than One Ship at a time");
					return;
				}
				SELECTED_SHIP_SIZE = Constants.BOAT;
				cntr_boat++;
				if (cntr_boat == Constants.BOAT_COUNT) {
					b5.removeActionListener(this);
					b5.setEnabled(false);
				}
				b5.setText((Constants.BOAT_COUNT - cntr_boat) + " " + "Boat Left");
			}
		});

		b6.setText("Done");
		b6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cntr_battleship == Constants.BATTLESHIP_COUNT
						&& cntr_boat == Constants.BOAT_COUNT
						&& cntr_carrier == Constants.CARRIER_COUNT
						&& cntr_destroyer == Constants.DESTROYER_COUNT
						&& cntr_submarine == Constants.SUBMARINE_COUNT) {
					// Selection is made now game may begin
					playerCnt.notifyGCSelectionFinished();
				}else{
					JOptionPane.showMessageDialog(null,
							"There are ships to put!!");
					return;
				}
			}
		});

		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.add(b6);
	}

}
