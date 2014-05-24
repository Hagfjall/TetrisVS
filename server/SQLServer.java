package server;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServer {
	Connection c = null;
	{
		try {
			Class.forName("org.sqlite.JDBC");
			String path = "C:\\Users\\Johan\\Documents\\SQLliteDB\\TetrisScoreDB";
			c = DriverManager.getConnection("jdbc:sqlite:"+path);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public Connection getConnection() {
		return c;
	}
}
