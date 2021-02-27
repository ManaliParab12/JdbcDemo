package com.bridgelabz.demojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserJdbc {

	private static final String QUERY = "SELECT id, name, email, address, password FROM user WHERE id = ?";

	public static void main(String[] args) throws SQLException {
		UserJdbc userJdbc = new UserJdbc();
		userJdbc.retriveRecord();
	}

	public void retriveRecord() throws SQLException {		
		try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false",
				"root", "Manali@123");

				PreparedStatement preparedStatement = connection.prepareStatement(QUERY)) {
			//setting placeholder values
			preparedStatement.setInt(1, 2);			
			System.out.println(preparedStatement);

			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String email = resultSet.getString("email");
				String address = resultSet.getString("address");
				String password = resultSet.getString("password");
				System.out.println(id + ", " +name + ", " +email + ", " +address  + ", " +password);
			}
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
