package gui;

import game.Game;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import network.client.KeyListener;

public class TetrisGui extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2648768791410347791L;
	private JPanel localPanel, opponentPanel, statsPanel;
	
	
	
	public TetrisGui(String localName, String opponentName, Game localGame, Game opponentGame, KeyListener keyListener){
		super("TetrisTWO");
		localPanel = new GamePanel(localName,localGame);
		opponentPanel = new GamePanel(opponentName,opponentGame);
		statsPanel = new StatsPanel(localName,opponentName,localGame,opponentGame);
		setLayout(new BorderLayout());
		addKeyListener(keyListener);
		add(localPanel,BorderLayout.WEST);
		add(opponentPanel,BorderLayout.CENTER);
		add(statsPanel,BorderLayout.EAST);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		localGame.start();
	}
	
	public static void showMessage(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

}
