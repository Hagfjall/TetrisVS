package client.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import network.CommonNetworkMethods;

/**
 * Getting all the information needed from the other player in order to start a game. 
 *
 */
public class InitiateConnectionClient {
	private String opponentName;
	private long randomSeed;
	
	/**
	 * Initiates the connection, exchanges player name with the other client.
	 * @param socket
	 * @param name
	 * @throws IOException 
	 */

	public InitiateConnectionClient(Socket socket, String name) throws IOException {
		DataInputStream in;
		DataOutputStream out;
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			CommonNetworkMethods.sendString(out, name);
			opponentName = CommonNetworkMethods.readString(in);
			randomSeed = CommonNetworkMethods.readLong(in);
	}

	public long getRndSeed() {
		return randomSeed;
	}

	public String getOpponentName() {
		return opponentName;
	}

}
