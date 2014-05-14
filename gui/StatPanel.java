package gui;

import game.Game;
import game.attacks.Attack;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1373658622537354861L;
	private Game game;

	private static Icon singleShapeIcon = new ImageIcon(
			"resources/duplcBlocks.png");
	private static Icon invisibleIcon = new ImageIcon(
			"resources/invBlocks.png");
	private static Icon nullIcon = new ImageIcon(
			"resources/nullBlocks.png");
	

	private JLabel nameLabel, scoreLabel, powerupLabel;

	public StatPanel(String name, Game game) {
		this.game = game;
		nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		scoreLabel = new JLabel(0 + " p");
		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		powerupLabel = new JLabel("Powerup: ");
		powerupLabel.setHorizontalTextPosition(JLabel.LEFT);
		powerupLabel.setVerticalTextPosition(JLabel.BOTTOM);
		powerupLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		powerupLabel.setIcon(nullIcon);

		setLayout(new BorderLayout());
		add(nameLabel, BorderLayout.NORTH);
		add(scoreLabel, BorderLayout.CENTER);
		add(powerupLabel, BorderLayout.SOUTH);

		game.addObserver(this);
		update(null, null); // for getting all the information from the game to
							// start with

	}

	public void update(Observable o, Object arg) {
		Attack attack = game.getAttack();
		switch (attack.getType()) {
		case Attack.SINGLE_SHAPE:
			powerupLabel.setIcon(singleShapeIcon);
			break;
		case Attack.INVISIBLE_GAME:
			powerupLabel.setIcon(invisibleIcon);
			break;
		default:
			powerupLabel.setIcon(nullIcon);
			break;
		}
		scoreLabel.setText(Integer.toString(game.getScore()) + " p");
	}

}
