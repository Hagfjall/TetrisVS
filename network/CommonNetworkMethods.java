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
//	
//	public static byte readPowerup(DataInputStream in) throws IOException {
//		byte type = in.readByte();
//		if(type != ProtocolConstants.POWERUP) {
//			return -1;
//		}
//		return in.readByte();
//	}

	public static String readString(DataInputStream in) throws IOException {
		byte type = in.readByte();
		if (type != ProtocolConstants.STRING)
			return null;
		int length = in.readInt();
		byte[] data=new byte[length];
		in.readFully(data);
		return new String(data,"UTF-8");
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
	
//	public static void sendPowerup(DataOutputStream out, byte powerupType) throws IOException {
//		out.write(ProtocolConstants.POWERUP);
//		out.write(powerupType);
//	}

}
