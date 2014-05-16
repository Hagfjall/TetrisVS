package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import network.client.Network;

public class AttackTimer {

	private Network network;
	private Timer timer;

	public AttackTimer(Network network, int delay) {
		this.network = network;
		timer = new Timer(delay, new AttackListener());
		timer.setRepeats(false);
	}

	public void start() {
		if (timer.isRunning())
			timer.restart();
		else
			timer.start();
		network.sendAttackActivated();
	}

	private class AttackListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			network.sendAttackDeactivated();
		}

	}

}
