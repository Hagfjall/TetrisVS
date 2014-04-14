package gui;

import game.Game;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TetrisGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2648768791410347791L;
	private JPanel localPanel, opponentPanel, statsPanel;
	
	
	
	public TetrisGui(String localName, String opponentName, Game localGame, Game opponentGame){
		localPanel = new GamePanel(localName,localGame);
		
	}

}
