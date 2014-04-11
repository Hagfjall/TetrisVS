package server;

import java.io.IOException;
import java.net.*;
import java.nio.channels.ServerSocketChannel;

public class Server {

	public static void main(String[] args) {

		new Server().start();
	}

	private void start() {

		try {
			ServerSocketChannel server = ServerSocketChannel.open();
			ServerSocket socket = server.socket();
			SocketAddress address = new InetSocketAddress(1337);
			socket.bind(address);

			while (true) {
				Socket s = socket.accept();
				Connection c = new Connection(s);
				c.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
