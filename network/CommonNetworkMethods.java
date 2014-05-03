package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommonNetworkMethods {

	public static long readLong(DataInputStream in) throws IOException {
		byte type = in.readByte();
		if (type != ProtocolConstants.LONG)
			return -1;
		return in.readLong();
	}

	public static String readString(DataInputStream in) throws IOException {
		byte type = in.readByte();
		if (type != ProtocolConstants.STRING)
			return null;
		int length = in.readInt();
		byte[] data=new byte[length];
		in.readFully(data);
		return new String(data,"UTF-8");
//		StringBuilder sb = new StringBuilder(length);
//		for (int i = 0; i < length; i++) {
//			sb.append(in.readChar());
//		}
//		return sb.toString();

	}
	
	
	public static void sendLong(DataOutputStream out, long nbr) throws IOException {
		out.write(ProtocolConstants.LONG);
		out.writeLong(nbr);
	}

	public static void sendScore(DataOutputStream out, int score)
			throws IOException {
		out.write(ProtocolConstants.INT);
		out.writeInt(score);
	}

	public static void sendString(DataOutputStream out, String msg)
			throws IOException {
		int length = msg.length();
		out.write(ProtocolConstants.STRING);
		out.writeInt(length);
		out.write(msg.getBytes("UTF-8"));
	}

}
