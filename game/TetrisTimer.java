package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import network.client.KeyListener;

/**
 * Starts the timer and pulling the shape one step every time the timer fires.
 * 
 */
public class TetrisTimer {

	private KeyListener keyListener;

	public TetrisTimer(KeyListener keyListener, int delay) {
		this.keyListener = keyListener;
		Timer timer = new Timer(delay, new TimeListener());
		timer.setRepeats(true);
		timer.setInitialDelay(1000);
		timer.start();
	}

	private class TimeListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			keyListener.moveDown();
		}

	}

}
