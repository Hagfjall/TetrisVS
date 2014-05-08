package test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStream;

public class TestSendBoard {
	
	public static void main(String[] args) {
		byte[][] board = new byte[22][10];
		String s = new String(board[0]);
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(s.getBytes()));
		
		
		
	}

}
