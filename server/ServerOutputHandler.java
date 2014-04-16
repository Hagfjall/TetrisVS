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
			System.out.println("OutputHandler: waiting for messages");
			Input msg = m.get();
			int[] msgarr = msg.getMessage();
			System.out.println("OutputHandler: sending message " + msgarr[0]);
					Iterator<Socket> it = allConnections.iterator();
					while (it.hasNext()) {
					   Socket s = it.next(); // must be called before you can call i.remove()
					   if(!s.equals(msg.getSocket())) {
						   try {
								DataOutputStream out = new DataOutputStream(
										s.getOutputStream());
								for (int i = 0; i < msgarr.length; i++) {
									out.write(msgarr[i]);
								}

							} catch (IOException e) {
								System.out.println("OutputHanlder: client " + s + " is removed");
								it.remove();
							}
					   }
					}
//			for (Socket s : allConnections) {
//				if (!s.equals(msg.getSocket())) {
//
//					try {
//						DataOutputStream out = new DataOutputStream(
//								s.getOutputStream());
//						for (int i = 0; i < msgarr.length; i++) {
//							out.write(msgarr[i]);
//						}
//
//					} catch (IOException e) {
//						System.out.println("OutputHanlder: client " + s + " is removed");
//						allConnections.remove(s);
//					}
//				}
//			}
		}

	}

}
