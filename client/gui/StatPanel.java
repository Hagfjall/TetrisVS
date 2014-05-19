package client.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import client.game.Game;
import client.game.attacks.Attack;

public class StatPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1373658622537354861L;
	private Game game;

	private static Icon singleShapeIcon = new ImageIcon(
			"resources/duplcBlocks.png");
	private static Icon invisibleIcon = new ImageIcon(
			"resources/invBlocks.png");
	private static Icon nullIcon = new ImageIcon(
			"resources/nullBlocks.png");
	

	private JLabel nameLabel, scoreLabel, attackLabel;

	public StatPanel(String name, Game game) {
		this.game = game;
		nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Serif", Font.PLAIN, 22));
		scoreLabel = new JLabel(0 + " p");
		scoreLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		attackLabel = new JLabel("Attack: ");
		attackLabel.setHorizontalTextPosition(JLabel.LEFT);
		attackLabel.setVerticalTextPosition(JLabel.BOTTOM);
		attackLabel.setFont(new Font("Serif", Font.PLAIN, 18));
		attackLabel.setIcon(nullIcon);

		setLayout(new BorderLayout());
		add(nameLabel, BorderLayout.NORTH);
		add(scoreLabel, BorderLayout.CENTER);
		add(attackLabel, BorderLayout.SOUTH);

		game.addObserver(this);
		update(null, null); // for getting all the information from the game to
							// start with
	}

	public void update(Observable o, Object arg) {
		Attack attack = game.getAttack();
		switch (attack.getType()) {
		case Attack.SINGLE_SHAPE:
			attackLabel.setIcon(singleShapeIcon);
			break;
		case Attack.INVISIBLE_GAME:
			attackLabel.setIcon(invisibleIcon);
			break;
		default:
			attackLabel.setIcon(nullIcon);
			break;
		}
		scoreLabel.setText(Integer.toString(game.getScore()) + " p");
	}

}
