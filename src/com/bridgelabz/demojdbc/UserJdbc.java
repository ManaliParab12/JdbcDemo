package com.bridgelabz.demojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserJdbc {

	private static final String UPDATE_USER_INTO_TABLE = "UPDATE user SET name = ? WHERE id = ?"; 

	public static void main(String[] args) throws SQLException {
		UserJdbc userJdbc = new UserJdbc();
		userJdbc.updateRecord();
	}

	public void updateRecord() throws SQLException {	
		System.out.println(UPDATE_USER_INTO_TABLE);
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
				"root", "Manali@123");

				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_INTO_TABLE)) {
			//setting placeholder values
			preparedStatement.setString(1, "Ram");
			preparedStatement.setInt(2, 1);	
			System.out.println(preparedStatement);

			int result = preparedStatement.executeUpdate();
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
