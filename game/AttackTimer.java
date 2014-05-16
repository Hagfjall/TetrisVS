package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import network.client.Network;

public class AttackTimer {

		private Network network;

		public AttackTimer(Network network, int delay) {
			this.network = network;
			Timer timer = new Timer(delay, new AttackListener());
			timer.setRepeats(false);
			timer.start();
			network.sendAttackActivated();
		}

		private class AttackListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				network.sendAttackDeactivated();
			}

		}


	public AttackTimer() {
		// TODO Auto-generated constructor stub
	}

}
