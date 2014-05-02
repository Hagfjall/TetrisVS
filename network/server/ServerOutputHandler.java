package network.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

import network.CommonNetworkMethods;

public class ServerOutputHandler extends Thread {

	private Vector<Socket> allConnections;
	private TetrisMailbox m;
	private String[] names;

	public ServerOutputHandler(Vector<Socket> allConnections, TetrisMailbox m) {
		this.allConnections = allConnections;
		names = new String[2];
		this.m = m;
	}

	public void addPlayername(String name) {
		if (names[0] == null) {
			names[0] = name;
		} else {
			names[1] = name;
		}
	}

	@Override
	public void run() {
		DataOutputStream out;
		try {
			out = new DataOutputStream(allConnections.get(0).getOutputStream());
			CommonNetworkMethods.sendString(out, names[1]);
			out = new DataOutputStream(allConnections.get(1).getOutputStream());
			CommonNetworkMethods.sendString(out, names[0]);
		} catch (IOException e1) {
			return;
		}
		while (true) {
			Input msg = m.get();
			int[] msgarr = msg.getMessage();
			Iterator<Socket> it = allConnections.iterator();
			while (it.hasNext()) {
				Socket s = it.next();
				if (!s.equals(msg.getSocket())) {
					try {
						out = new DataOutputStream(
								s.getOutputStream());
						for (int i = 0; i < msgarr.length; i++) {
							out.write(msgarr[i]);
						}

					} catch (IOException e) {
						System.out.println("OutputHanlder: client " + s
								+ " is removed");
						it.remove();
					}
				}
			}
		}
	}

}
