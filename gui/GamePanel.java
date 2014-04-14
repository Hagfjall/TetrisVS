package gui;

import game.Game;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private TetrisPanel tetrisPanel;
	private JLabel nameLabel;

	public GamePanel(String name, Game game) {
		tetrisPanel = new TetrisPanel(game);
		nameLabel = new JLabel(name);

		setLayout(new BorderLayout());
		add(nameLabel, BorderLayout.NORTH);
		add(tetrisPanel, BorderLayout.SOUTH);
		
	}

}
