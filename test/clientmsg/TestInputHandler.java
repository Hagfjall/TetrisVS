package test.clientmsg;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TestInputHandler implements Runnable {

	private Socket server;

	public TestInputHandler(Socket server) {
		this.server = server;

	}

	@Override
	public void run() {
		String address = server.toString();
		try {
			InputStream in = server.getInputStream();
			int input;
			while ((input = in.read()) != -1) {
				System.out.print(Character.toChars(input));
			}
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("closed connection to " + address);

	}
}
