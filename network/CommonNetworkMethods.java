package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommonNetworkMethods {
	
	
	public static String readString(DataInputStream in) throws IOException {
		int length = in.readInt();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(in.readChar());
		}
		return sb.toString();

	}
	
	public static void sendString(DataOutputStream out, String msg) throws IOException {
		int length = msg.length();
		out.writeInt(length);
		out.write(msg.getBytes());
	}

}
