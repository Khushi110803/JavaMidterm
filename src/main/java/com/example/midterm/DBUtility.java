package com.example.midterm;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtility {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/f23test1";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "student";

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();

        try {
            // Load the MySQL JDBC driver.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database.
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Create a SQL statement.
            Statement statement = connection.createStatement();

            // Execute a query to retrieve employee records.
            String query = "SELECT * FROM employees";
            ResultSet resultSet = statement.executeQuery(query);

            // Iterate through the result set and create Employee objects.
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                double salary = resultSet.getDouble("salary");
                String birthday = resultSet.getString("birthday");
                String department = resultSet.getString("department");

                Employee employee = new Employee(id, name, email, salary, birthday, department, birthday, department);
                employees.add(employee);
            }

            // Close the resources.
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that may occur during database access.
        }

        return employees;
    }
    public static void removeEmployee(int employeeId) {
        try {
            // Load the MySQL JDBC driver and establish a connection to the database.
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Create a prepared statement to delete the employee with the specified ID.
            String deleteQuery = "DELETE FROM employees WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, employeeId);

            // Execute the prepared statement to delete the employee.
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee with ID " + employeeId + " has been removed from the database.");
            } else {
                System.out.println("Employee with ID " + employeeId + " not found in the database.");
            }

            // Close the resources.
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle any exceptions that may occur during database access.
        }
}
