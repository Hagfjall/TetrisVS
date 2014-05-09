package gui;

import game.Game;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 2008163774465947571L;
	private TetrisPanel tetrisPanel;
	private JLabel nameLabel;

	public GamePanel(String name, Game game) {
		tetrisPanel = new TetrisPanel(game);
		nameLabel = new JLabel("\n" +name);

		setLayout(new BorderLayout());
		add(nameLabel, BorderLayout.CENTER);
		nameLabel.setVerticalAlignment(SwingConstants.CENTER);
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Serif",Font.PLAIN,22));
		add(tetrisPanel, BorderLayout.SOUTH);
	}
}
