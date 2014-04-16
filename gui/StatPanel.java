package gui;

import game.Game;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1373658622537354861L;
	private Game game;

	private JLabel nameLabel, scoreLabel, powerupLabel;

	public StatPanel(String name, Game game) {
		nameLabel = new JLabel(name);
		nameLabel.setFont(new Font("Serif",Font.PLAIN,22));
		scoreLabel = new JLabel(0 + " p");
		scoreLabel.setFont(new Font("Serif",Font.PLAIN,18));
		powerupLabel = new JLabel("0 powerup");
		powerupLabel.setFont(new Font("Serif",Font.PLAIN,18));
		
		setLayout(new BorderLayout());
		add(nameLabel, BorderLayout.NORTH);
		add(scoreLabel, BorderLayout.CENTER);
		add(powerupLabel, BorderLayout.SOUTH);
		
		game.addObserver(this);
		this.game = game;
		update(null,null);

	}

	@Override
	public void update(Observable o, Object arg) {
		powerupLabel.setText("(POWERUPICON)"); // TODO ändra till icon
		scoreLabel.setText(Integer.toString(game.getScore()) + " p");
	}

}
