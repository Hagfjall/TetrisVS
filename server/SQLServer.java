package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServer {
	Connection c;

	public SQLServer() {
		c = null;
	}

	public boolean openConnection(String userName, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://sql3.freemysqlhosting.net/"
					+ userName, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("Connection to SQL database is up.");
		return true;
	}

	public Connection getConnection() {
		return c;
	}
}
