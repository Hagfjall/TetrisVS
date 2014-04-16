package gui;

import game.Game;

import java.awt.BorderLayout;

import javax.swing.JPanel;

public class StatsPanel extends JPanel {

	private static final long serialVersionUID = 2860351428068208802L;
	private StatPanel localPanel, opponentPanel;

	public StatsPanel(String localName, String opponentName, Game localGame,
			Game opponentGame) {
		setLayout(new BorderLayout());
		localPanel = new StatPanel(localName, localGame);
		opponentPanel = new StatPanel(opponentName, opponentGame);
		add(localPanel, BorderLayout.NORTH);
		add(opponentPanel, BorderLayout.SOUTH);

	}

}
