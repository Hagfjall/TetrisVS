package network.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

import network.CommonNetworkMethods;
import network.ProtocolConstants;

public class ServerOutputHandler extends Thread {

	private Vector<Socket> allConnections;
	private TetrisMailbox m;
	private String[] names;
	private long rndSeed;

	public ServerOutputHandler(Vector<Socket> allConnections, TetrisMailbox m,
			long rndSeed) {
		this.allConnections = allConnections;
		names = new String[2];
		this.m = m;
		this.rndSeed = rndSeed;
	}

	public void addPlayername(String name) {
		if (allConnections.size() == 1) {
			names[0] = name;
		} else if (allConnections.size() == 2) {
			names[1] = name;
		}
	}
	
	public void setRandomSeed(long rnd) {
		rndSeed = rnd;
	}

	public void initiateGameOnClients() {
		DataOutputStream out;
		try {
			out = new DataOutputStream(allConnections.get(0).getOutputStream());
			CommonNetworkMethods.sendString(out, names[1]);
			CommonNetworkMethods.sendLong(out, rndSeed);
			out = new DataOutputStream(allConnections.get(1).getOutputStream());
			CommonNetworkMethods.sendString(out, names[0]);
			CommonNetworkMethods.sendLong(out, rndSeed);
		} catch (IOException e1) {
			// TODO AUTO
			e1.printStackTrace();
		}
	}

	@Override
	public void run() {

		while (true) {
			Input msg = m.get();
			DataOutputStream out;
			int[] msgarr = msg.getMessage();
//			if(msgarr[0] == ProtocolConstants.QUIT) {
//				allConnections.remove(msg.getSocket());
//			}
			Iterator<Socket> it = allConnections.iterator();
			while (it.hasNext()) {
				Socket s = it.next();
				if (!s.equals(msg.getSocket())) {
					try {
						out = new DataOutputStream(s.getOutputStream());
						for (int i = 0; i < msgarr.length; i++) {
							out.write(msgarr[i]);
						}
					} catch (IOException e) {
						System.out.println("OutputHanlder: client " + s
								+ " is removed");
						try {
							s.close();
						} catch (IOException e1) {
						}
						it.remove();
					}
				}
			}
		}
	}

}
