package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInfo {
	private final String url = "jdbc:mysql://localhost:3306/DSFinal"; // DATABASE URL
	private final String user = "root";
	private final String password = "chocolate430";
	
	public String getUrl() {
		return url;
	}
	public String getUser() {
		return user;
	}
	public String getPassword() {
		return password;
	}
	
	public Connection connect() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	
}
