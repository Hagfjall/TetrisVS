package gui;

import game.Game;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StatsPanel extends JPanel {

	private static final long serialVersionUID = 2860351428068208802L;
	private StatPanel localPanel, opponentPanel;

	public StatsPanel(String localName, String opponentName, Game localGame,
			Game opponentGame) {
		setLayout(new BorderLayout(50, 0));
		localPanel = new StatPanel(localName, localGame);
		localPanel.setBorder(new EmptyBorder(47, 10, 10, 10));
		opponentPanel = new StatPanel(opponentName, opponentGame);
		opponentPanel.setBorder(new EmptyBorder(10, 10, 20, 10));
		add(localPanel, BorderLayout.NORTH);

		add(opponentPanel, BorderLayout.SOUTH);

	}
}
