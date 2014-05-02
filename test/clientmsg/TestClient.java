package test.clientmsg;

import game.Game;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

import network.ProtocolConstants;
import client.NetworkInputHandler;
import client.NetworkOutputHandler;

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
			Random rnd = new Random();
			while (true) {
				nout.sendKey((byte) (rnd.nextInt(5) + 3));
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
	}

}
