package network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommonNetworkMethods {

	/**
	 * Makes sure that we are following the protocol, returns -1 if not
	 * @param in 
	 * @return long 
	 * @throws IOException if {in} is throwing a exception.
	 */
	public static long readLong(DataInputStream in) throws IOException {
		byte type = in.readByte();
		if (type != ProtocolConstants.LONG)
			return -1;
		return in.readLong();
	}

	/**
	 * Makes sure that we are following the protocol, returns null if not
	 * @param in
	 * @return the string sent 
	 * @throws IOException if {in} is throwing a exception.
	 */
	public static String readString(DataInputStream in) throws IOException {
		byte type = in.readByte();
		if (type != ProtocolConstants.STRING)
			return null;
		int length = in.readInt();
		byte[] data = new byte[length];
		in.readFully(data);
		return new String(data, "UTF-8");
	}

	/**
	 * Sending the long nbr according to the protocol.
	 * @param out
	 * @param nbr to send
	 * @throws IOException
	 */
	public static void sendLong(DataOutputStream out, long nbr)
			throws IOException {
		out.write(ProtocolConstants.LONG);
		out.writeLong(nbr);
	}

	public static void sendString(DataOutputStream out, String msg)
			throws IOException {
		int length = msg.length();
		out.write(ProtocolConstants.STRING);
		out.writeInt(length);
		out.write(msg.getBytes("UTF-8"));
	}

	public static void sendBoard(DataOutputStream out, byte[][] board)
			throws IOException {
		out.writeInt(board.length);
		out.writeInt(board[0].length);
		for (int i = 0; i < board.length; i++) {
			out.write(board[i]);
		}
	}

	public static byte[][] readBoard(DataInputStream in) throws IOException {
		int row = in.readInt();
		int col = in.readInt();
		byte[][] board = new byte[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				board[i][j] = in.readByte();
			}
		}
		return board;
	}
}

// public static void sendPowerup(DataOutputStream out, byte powerupType) throws
// IOException {
// out.write(ProtocolConstants.POWERUP);
// out.write(powerupType);
// }

