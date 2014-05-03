package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import network.CommonNetworkMethods;

public class InitiateConnectionClient {
	private String opponentName;
	private long randomSeed;

	public InitiateConnectionClient(Socket socket, String name) {
		DataInputStream in;
		DataOutputStream out;
		try {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			CommonNetworkMethods.sendString(out, name);
			opponentName = CommonNetworkMethods.readString(in);
			randomSeed = CommonNetworkMethods.readLong(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public long getRndSeed() {
		return randomSeed;
	}

	public String getOpponentName() {
		return opponentName;
	}

}
