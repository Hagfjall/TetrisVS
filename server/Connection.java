package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Connection extends Thread {
	private Socket s;
	ByteBuffer buffer;

	public Connection(Socket in) {
		s = in;
	}

	public void run() {
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
