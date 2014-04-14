package gui;

import java.awt.Dimension;

import game.Game;

import javax.swing.JPanel;

public class TetrisPanel extends JPanel {
	private static final long serialVersionUID = -4465366882639674458L;
	private Game game;

	public TetrisPanel(Game game){
		this.game = game;
		col = game.getWidth() + 1;
		row = b.getHeight() + 1;
		width = col * (square + 2);
		height = row * (square + 1);
		board = b;
		setPreferredSize(new Dimension(width, height));
	}
	
}
