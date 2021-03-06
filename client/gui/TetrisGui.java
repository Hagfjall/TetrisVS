package client.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.game.Game;
import client.network.KeyListener;

public class TetrisGui extends JFrame {
	private static final long serialVersionUID = 2648768791410347791L;
	private JPanel localPanel, opponentPanel, statsPanel;
	
	
	
	public TetrisGui(String localName, String opponentName, Game localGame, Game opponentGame, KeyListener keyListener){
		super("TetrisVS");
		localPanel = new GamePanel(localName,localGame);
		opponentPanel = new GamePanel(opponentName,opponentGame);
		statsPanel = new StatsPanel(localName,opponentName,localGame,opponentGame);
		setLayout(new BorderLayout());
		addKeyListener(keyListener);
		add(localPanel,BorderLayout.WEST);
		add(opponentPanel,BorderLayout.CENTER);
		add(statsPanel,BorderLayout.EAST);
		pack();
		setBackground(Color.white);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
