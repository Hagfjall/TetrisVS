package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLTest {

	public static void main(String[] args) {
		new SQLTest().start();
	}
	
	public void start(){
		SQLServer s = new SQLServer();
		Connection c = s.getConnection();
		if(c == null){
			System.out.println("Connection c is null");
		}
		
		try {
			String sql = "Insert into Score (name, score,date) values(?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, "Testsson");
			ps.setInt(2, 1000);
			ps.setString(3, "Tisdag");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
