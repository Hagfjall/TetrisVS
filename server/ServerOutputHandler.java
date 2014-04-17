package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Iterator;
import java.util.Vector;

public class ServerOutputHandler extends Thread {

	private Vector<Socket> allConnections;
	private TetrisMailbox m;

	public ServerOutputHandler(Vector<Socket> allConnections, TetrisMailbox m) {
		this.allConnections = allConnections;

		this.m = m;
	}

	@Override
	public void run() {
		while (true) {
			Input msg = m.get();
			int[] msgarr = msg.getMessage();
			Iterator<Socket> it = allConnections.iterator();
			while (it.hasNext()) {
				Socket s = it.next(); 
				if (!s.equals(msg.getSocket())) {
					try {
						DataOutputStream out = new DataOutputStream(
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
