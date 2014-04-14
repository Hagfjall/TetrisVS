package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Connection extends Thread {
	private Socket s;
	ByteBuffer buffer;

	public Connection(Socket in) {
		s = in;
	}

	/**
	 * 1 Name of player, the next byte is the length of the name (String)
	 * 
	 * 2 Score (Integer)
	 * 
	 * 3 Left
	 * 
	 * 4 Right
	 * 
	 * 5 Up
	 * 
	 * 6 Down
	 * 
	 * 7 Space
	 * 
	 * 8 Powerup
	 */

	public void run() {
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			byte read = (byte) in.read();
			while (read != -1) {
				switch (read) {
				case 1:
					readString(in);
					break;
				case 2:
					readInt(in);
					break;
				}

				read = (byte) in.read();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private int readInt(BufferedReader in) {
		byte[] r = new byte[4];
		try {
			for (int i = 0; i < 4; i++) {
				r[i] = (byte) in.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ByteBuffer.wrap(r).getInt();
	}

	// What the hell should with the name when we has it?
	public String readString(BufferedReader in) {
		byte length;
		StringBuilder sb = new StringBuilder();
		try {
			length = (byte) in.read();
			for (int i = 0; i < length; i++) {
				sb.append(in.read());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
