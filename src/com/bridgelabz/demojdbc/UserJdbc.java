package com.bridgelabz.demojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserJdbc {

	private static final String DELETE_USER_FROM_TABLE = "DELETE FROM user  WHERE id = 2;"; 

	public static void main(String[] args) throws SQLException {
		UserJdbc userJdbc = new UserJdbc();
		userJdbc.deleteRecord();
	}

	public void deleteRecord() throws SQLException {
		
		System.out.println(DELETE_USER_FROM_TABLE);

		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
				"root", "Manali@123");

				Statement statement = connection.createStatement();) {

			int result = statement.executeUpdate(DELETE_USER_FROM_TABLE);
			System.out.println("No. of records affected: " +result);
		} catch (SQLException e) {
	
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
