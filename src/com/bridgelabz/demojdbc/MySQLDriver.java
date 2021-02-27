package com.bridgelabz.demojdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDriver {
	 public static void main(String[] args) {
		 
		 //try-with-resource statement to close  resources automatically.
		 
		 //	1. get database connection
		 try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false", "root", "Manali@123");
		//  2. create statement
				 Statement statement = connection.createStatement();
		//  3. Execute statement
				 ResultSet resultSet = statement.executeQuery("select * from user");){
		//  4. Process Result
			 while(resultSet.next()) {
				 int id = resultSet.getInt("id");
				 String name = resultSet.getString("name");
				 String email = resultSet.getString("email");
				 String address = resultSet.getString("address");
				 String password = resultSet.getString("password");
				 System.out.println(id + ", " +name + ", " +email + ", " +address  + ", " +password);
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
			 
		 }
				 
	}

}
