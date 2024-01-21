package Question2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCEmployeeTable {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/GL";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Asraf@2003";

	public static void main(String[] args) {
		try {
			// Step 1: Establish the connection

			Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

			// Step 2: Create a statement
			Statement statement = connection.createStatement();

			//1.create the table required for the following operation

			// Create employee table if it does not exist
			createEmployeeTable(statement);

			// 1.2.Insert 5 records
			insertRecords(statement);

			//  1.2.Modify Email_Id column
			modifyEmailColumn(statement);

			//  1.3.Insert 2 records and check
			insertRecords(statement);

			//  1.4.Update employee Id 3
			updateEmployeeDetails(statement);

			//  1.5.Delete records 3 and 4
			deleteEmployeeRecords(statement);

			//  1.6.Remove all records
			removeAllRecords(statement);


			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void createEmployeeTable(Statement statement) throws SQLException {
	    String createTableQuery = "CREATE TABLE IF NOT EXISTS employee (" +
	            "id INT PRIMARY KEY," +
	            "name VARCHAR(50)," +
	            "email_id VARCHAR(30) NOT NULL," +
	            "phone_number VARCHAR(15)" +
	            ")";
	    statement.executeUpdate(createTableQuery);
	    System.out.println("Employee table created successfully.");
	}

	private static void insertRecords(Statement statement) throws SQLException {
	    for (int i = 1; i <= 5; i++) {
	        String insertQuery = "INSERT IGNORE INTO employee (id, name, email_id, phone_number) VALUES " +
	                "(" + i + ", 'Employee" + i + "', 'email" + i + "@example.com', '123456789" + i + "')";
	        statement.executeUpdate(insertQuery);
	    }
	    System.out.println("5 records inserted successfully.");
	}

	private static void modifyEmailColumn(Statement statement) throws SQLException {
	    String modifyQuery = "ALTER TABLE employee MODIFY COLUMN email_id VARCHAR(30) NOT NULL";
	    statement.executeUpdate(modifyQuery);
	    System.out.println("Email_Id column modified successfully.");
	}

	

	private static void updateEmployeeDetails(Statement statement) throws SQLException {
	    String updateQuery = "UPDATE employee SET name = 'Karthik', phone_number = '1234567890' WHERE id = 3";
	    statement.executeUpdate(updateQuery);
	    System.out.println("Employee details updated successfully.");
	}

	private static void deleteEmployeeRecords(Statement statement) throws SQLException {
	    String deleteQuery = "DELETE FROM employee WHERE id IN (3, 4)";
	    statement.executeUpdate(deleteQuery);
	    System.out.println("Records for employee Id 3 and 4 deleted successfully.");
	}

	private static void removeAllRecords(Statement statement) throws SQLException {
	    String deleteAllQuery = "DELETE FROM employee";
	    statement.executeUpdate(deleteAllQuery);
	    System.out.println("All records deleted from the employee table.");
	}
}
