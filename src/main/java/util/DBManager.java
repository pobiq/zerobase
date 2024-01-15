package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	public static Connection getConnection() {
		Connection conn = null;
	      try {
	         Class.forName("org.mariadb.jdbc.Driver");
	         
	         String url="jdbc:mariadb://localhost:3306/testdb3?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	         String user="testuser3";
	         String password = "zerobase";
	         
	         conn = DriverManager.getConnection(url, user, password);
	         
	      }catch(ClassNotFoundException | SQLException e) {
	         e.printStackTrace();
	      }
	      return conn;
	   }

	// select을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			rs.close();
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// DML(insert, update, delete)을 수행한 후 리소스 해제를 위한 메소드
	public static void close(Connection conn, PreparedStatement pstmt) {
		try {
			pstmt.close();
			conn.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
