import javax.swing.JFrame;

import com.battleship.panels.Menu;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame gameScene = new JFrame("BattleShip");
		
		Menu m = new Menu(gameScene);
		m.setPanel(m);
		
		gameScene.add(m);
		
		gameScene.pack();
		
		gameScene.setVisible(true);
		
		gameScene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
