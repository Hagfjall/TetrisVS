package network.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Vector;

public class Server extends Thread {

	private Vector<Socket> allConnections;
	private TetrisMailbox m;
	private int port;

	public static void main(String[] args) {
		int port;
		if(args.length != 1) {
			System.out.println("Usage: server <port> \nUsing standard port 3000");
			port = 3000;
		}else {
			port = Integer.parseInt(args[0]);
		}
		new Server(port).start();
	}

	public Server(int port) {
		this.port = port;
		allConnections = new Vector<Socket>();
		m = new TetrisMailbox();
	}

	@Override
	public void run() {
		Random rnd = new Random();
		try (ServerSocket server = new ServerSocket(port)) {
			System.out.println("server up and running on port " + port);
			ServerOutputHandler output = new ServerOutputHandler(
					allConnections, m, rnd.nextLong());
			output.start();

			while (true) {
				Socket s = server.accept();
				allConnections.add(s);
				ServerInputHandler in = new ServerInputHandler(s, m, allConnections);
				in.start();
				output.addPlayername(in.getPlayername());
				if (allConnections.size() == 2) {
					output.setRandomSeed(rnd.nextLong());
					output.initiateGameOnClients();
				} else if (allConnections.size() > 2) {
					allConnections.add(s);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
