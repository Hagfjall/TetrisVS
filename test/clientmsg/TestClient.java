package test.clientmsg;

import game.Game;

import java.io.IOException;
import java.net.Socket;

import client.NetworkInputHandler;
import client.NetworkOutputHandler;
import client.ProtocolConstants;

public class TestClient {

	public static void main(String[] args) throws InterruptedException {

		String addr = "localhost";
		int port = 3000;
		Socket server;
		try {
			server = new Socket(addr, port);
			System.out.println("Connected to "
					+ server.getInetAddress().getHostName());
			NetworkOutputHandler nout = new NetworkOutputHandler(server,
					"testclient2");
			Game local = new Game(22, 10);
			new Thread(new NetworkInputHandler(server, local)).start();
			for (int i = 0; i < 10; i++) {
				nout.sendKey(ProtocolConstants.LEFT);
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

}
