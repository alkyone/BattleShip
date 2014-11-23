package com.battleship.panels;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.battleship.controllers.GameController;

@SuppressWarnings("serial")
public class Menu extends JPanel {

	private JFrame frame;
	private Menu panel;

	public Menu(JFrame frame) {
		// TODO Auto-generated constructor stub
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.frame = frame;
		init();

	}

	public void init() {

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("images/game.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));

		JButton start = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GameController gc = new GameController();

				// Set up game Scene
				gc.setFrame(frame);

				frame.setVisible(false);

				frame.remove(panel);
				
				
				// Start Game
				gc.run();

			}
		});

		JButton quit = new JButton(new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				System.exit(1);

			}
		});

		start.setText("Start");
		quit.setText("Quit");

		JPanel p = new JPanel(new GridLayout(2, 1));
		p.setAlignmentX(CENTER_ALIGNMENT);
		p.add(start);
		p.add(quit);

		frame.setPreferredSize(new Dimension(320, 300));
		frame.setMinimumSize(new Dimension(320, 300));

		this.add(Box.createVerticalGlue());
		this.add(picLabel);
		this.add(Box.createVerticalGlue());
		this.add(p);

	}

	public void setPanel(Menu m) {

		this.panel = m;
	}

}
