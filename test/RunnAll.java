package test;

import java.io.IOException;
import java.net.UnknownHostException;

import client.Tetris;
import network.server.Server;

public class RunnAll {

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		Server.main(args);
		String[] name1 = { "test1" };
		String[] name2 = { "test2" };
		Tetris.main(name1);
		Tetris.main(name2);
	}

}
