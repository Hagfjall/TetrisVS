package gui;

import game.Game;

import javax.swing.JPanel;

public class TetrisPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4465366882639674458L;
	private Game game;

	public TetrisPanel(Game game){
		this.game = game;
	}
	
}
