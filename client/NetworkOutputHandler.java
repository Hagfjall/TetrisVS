package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import network.ProtocolConstants;

public class NetworkOutputHandler {

	private DataOutputStream out;

	public NetworkOutputHandler(Socket s, String name)  {
		try {
			out = new DataOutputStream(s.getOutputStream());
			sendName(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendKey(byte key)  {
		try {
			out.write((int) key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void sendName(String name)  {
		try {
			out.write(ProtocolConstants.STRING);
			out.writeInt(name.length());
			out.writeChars(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendScore(int score)  {
		try {
			out.write(ProtocolConstants.INT);
			out.writeInt(score);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
