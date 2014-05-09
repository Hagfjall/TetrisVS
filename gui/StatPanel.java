package gui;

import game.Game;
import game.attacks.Attack;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1373658622537354861L;
	private Game game;

	private JLabel nameLabel, scoreLabel, powerupLabel;

	public StatPanel(String name, Game game) {
		nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		scoreLabel = new JLabel(0 + " p");
		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		powerupLabel = new JLabel("Powerup: ");
		powerupLabel.setHorizontalTextPosition(JLabel.LEFT);
		powerupLabel.setVerticalTextPosition(JLabel.BOTTOM);
		powerupLabel.setFont(new Font("Serif", Font.PLAIN, 18));

		setLayout(new BorderLayout());
		add(nameLabel, BorderLayout.NORTH);
		add(scoreLabel, BorderLayout.CENTER);
		add(powerupLabel, BorderLayout.SOUTH);

		game.addObserver(this);
		this.game = game;
		update(null, null);

	}

	public void update(Observable o, Object arg) {
		Attack pwrUp = game.getAttack();
		switch (pwrUp.getType()) {

		case Attack.SINGLEBLOCK:
			ImageIcon icon = new ImageIcon("resources/duplcBlocks.png");
			powerupLabel.setIcon(icon);
			break;
		case Attack.INVISIBLE:
			icon = new ImageIcon("resources/invBlocks.png");
			powerupLabel.setIcon(icon);
			break;
		default:
			powerupLabel.setIcon(null);
			break;
		}
		// powerupLabel.setText("(POWERUPICON)"); // TODO ändra till icon
		scoreLabel.setText(Integer.toString(game.getScore()) + " p");
	}

}
