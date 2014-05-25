package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;

public class SQLTest {

	public static void main(String[] args) {
		new SQLTest().start();
	}
	
	public void start(){
		SQLServer s = new SQLServer();
		String username = "sql341335";
		String password = "tY7!qZ3!";
		s.openConnection(username, password);
		Connection c = s.getConnection();
		if(c == null){
			System.out.println("Connection c is null");
		}
		
		try {
			String sql = "Insert into Score values(?,?,?,0)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "Test4");
			ps.setInt(2, 1000);
//			ps.setString(3, time.toString());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
