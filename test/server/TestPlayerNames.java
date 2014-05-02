package test.server;

import java.net.Socket;

import network.server.PlayerNames;

public class TestPlayerNames {
	public static void main(String[] args) {
		new TestPlayerNames().run();
	}

	public void run() {
		PlayerNames p = new PlayerNames();
		Socket s;
		for(int i = 0; i < 5; i++) {
			s = new Socket();
			System.out.println(p.setName("name " + i, s));
		}
	}
}
