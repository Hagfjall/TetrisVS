package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import network.client.KeyListener;

public class AttackTimer {

		private KeyListener keyListener;

		public AttackTimer(KeyListener keyListener, int delay) {
			this.keyListener = keyListener;
			Timer timer = new Timer(delay, new AttackListener());
			timer.setRepeats(false);
			timer.start();
			keyListener.startAttack();
		}

		private class AttackListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				keyListener.stopAttack();
			}

		}


	public AttackTimer() {
		// TODO Auto-generated constructor stub
	}

}
