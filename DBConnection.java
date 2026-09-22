package project;

import java.sql.*;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/rental_db";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException("MySQL JDBC Driver missing in Classpath!", e);
		}
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
