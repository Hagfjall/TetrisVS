package game;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import network.ProtocolConstants;
import client.NetworkOutputHandler;

public class TetrisTimer implements ActionListener {
	
	private Game localGame;
	private NetworkOutputHandler network;

	public TetrisTimer(Game local, NetworkOutputHandler nout) {
		localGame = local;
		network = nout;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		localGame.moveDown();
		network.sendKey(ProtocolConstants.MOVEDOWN);
	}
	

}
