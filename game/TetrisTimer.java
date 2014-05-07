package game;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import network.client.KeyListener;

public class TetrisTimer{
	
	private KeyListener keyListener;

	public TetrisTimer(KeyListener keyListener, int delay) {
		Timer timer = new Timer(delay, new TimeListener());
		timer.setRepeats(true);
		timer.setInitialDelay(1000);
		timer.start();
		this.keyListener = keyListener;
	}

	private class TimeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			keyListener.moveDown();	
		}
		
	}

}
