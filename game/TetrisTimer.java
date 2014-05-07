package game;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import network.client.KeyListener;

public class TetrisTimer{
	
	private KeyListener keyListener;

	public TetrisTimer(KeyListener keyListener, int delay) {
		new Timer(delay, new TimeListener());
		this.keyListener = keyListener;
	}

	private class TimeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			keyListener.moveDown();	
		}
		
	}

}
