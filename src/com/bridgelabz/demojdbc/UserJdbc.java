package com.bridgelabz.demojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserJdbc {

	private static final String CREATE_TABLE = "create table user(\r\n" + " id int(3) primary key, \r\n"
			+ "name varchar(20), \r\n" + "email varchar(100), \r\n" + "address varchar(20), \r\n"
			+ "password varchar(20) \r\n" + ");";

	public static void main(String[] args) throws SQLException {
		UserJdbc userJdbc = new UserJdbc();
		userJdbc.createTable();
	}

	// try-with-resource statement to close resources automatically.
	public void createTable() throws SQLException {
		
		System.out.println(CREATE_TABLE);
		// 1. Establishing a connection
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
				"root", "Manali@123");
				// 2. create statement using connection object
				Statement statement = connection.createStatement();) {
			// 3. Execute Query or update Query
			boolean result = statement.execute(CREATE_TABLE);
			// 4. print the result
			System.out.println(result);
		} catch (SQLException e) {
			// print SQL Exception
			printSQLException(e);
		}
	}

	public static void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				// e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.err.println("Cause " + t);
					t = t.getCause();
				}

			}
		}
	}

}
