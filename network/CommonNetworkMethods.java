package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommonNetworkMethods {

	/**
	 * Makes sure that we are following the protocol, returns -1 if not
	 * 
	 * @param in
	 * @return long
	 * @throws IOException
	 *             if {in} is throwing a exception.
	 */
	public static long readLong(DataInputStream in) throws IOException {
		byte type = in.readByte();
		if (type != ProtocolConstants.LONG)
			return -1;
		return in.readLong();
	}

	/**
	 * Makes sure that we are following the protocol, returns null if not
	 * 
	 * @param in
	 * @return the string sent
	 * @throws IOException
	 *             if {in} is throwing a exception.
	 */
	public static String readString(DataInputStream in) throws IOException {
		byte type = in.readByte();
		if (type != ProtocolConstants.STRING)
			return null;
		int length = in.readInt();
		byte[] data = new byte[length];
		in.readFully(data);
		return new String(data, "UTF-16");
	}

	/**
	 * Sending the long nbr according to the protocol.
	 * 
	 * @param out
	 * @param nbr
	 *            to send
	 * @throws IOException
	 */
	public static void sendLong(DataOutputStream out, long nbr)
			throws IOException {
		out.write(ProtocolConstants.LONG);
		out.writeLong(nbr);
	}

	/**
	 * Send the String msg accord to the protocol.
	 * 
	 * @param out
	 * @param msg
	 * @throws IOException
	 */
	public static void sendString(DataOutputStream out, String msg)
			throws IOException {
		byte[] sndMsg = msg.getBytes("UTF-16");
		int length = sndMsg.length;
		out.write(ProtocolConstants.STRING);
		out.writeInt(length);
		out.write(sndMsg);
	}

}
