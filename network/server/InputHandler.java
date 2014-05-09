package network.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.List;

import network.CommonNetworkMethods;

public class InputHandler extends Thread {
	private Socket socket;
	private InputStream is;
	private TetrisMailbox m;
	private String playerName;
	private List<Socket> allConnections;

	public InputHandler(Socket socket, TetrisMailbox m, List<Socket> allConnections) throws IOException {
		this.m = m;
		this.socket = socket;
		is = socket.getInputStream();
		playerName = CommonNetworkMethods.readString(new DataInputStream(socket
				.getInputStream()));
		this.allConnections = allConnections;
	}

	public String getPlayername() {
		return playerName;
	}

	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(is);
			int read;
			while ((read = in.read()) != -1) {
				int[] msg = new int[1];
				msg[0] = read;
				m.insert(new Input(socket,msg));
			}
			allConnections.remove(socket);
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
