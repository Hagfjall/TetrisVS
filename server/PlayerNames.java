package server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class PlayerNames {
	private Map<Socket, String> names;
	private int counter;

	public PlayerNames() {
		names = new HashMap<Socket, String>(2);
		counter = 0;
	}

	/**
	 * return true if it was possible to set the name, otherwise false (if there
	 * already is two names for example)
	 * 
	 * @param name
	 * @param socket
	 * @return
	 */
	public boolean setName(String name, Socket socket) {
		if (counter++ >= 2) {
			return false;
		}
		names.put(socket, name);
		return true;
	}

	/**
	 * return the playername for the other player, i.e opponent the <b>socket</b> or null if there isn't anyone else
	 * 
	 * @param socket
	 * @return
	 */
	public String getPlayername(Socket socket) {
		for(Socket s : names.keySet()) {
			if(!s.equals(socket)) {
				return names.get(s);
			}
		}
		return null;
	}
}
