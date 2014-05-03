package network.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class NetworkOutputHandler {

	private DataOutputStream out;

	public NetworkOutputHandler(Socket s) throws IOException  {
		out = new DataOutputStream(s.getOutputStream());
	}

	public void sendKey(byte key)  {
		try {
			out.write((int) key);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
