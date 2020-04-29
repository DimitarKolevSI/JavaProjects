package com.vmware.talentboost.jdbc.examples;


import util.Constants;

import java.sql.*;

/**
 * Statement.executeUpdate only returns the number of affected records
 * <p>
 * Statement.executeQuery returns data, encapsulated in a ResultSet object (a cursor)
 */
public class UpdateExamples {

    public static void main(String[] args) throws Exception {

        try {
            int newSalary = 4000;
            updateEmployeeSalary(newSalary, "TGATES");
            System.out.println("Salary changed to " + newSalary);

            //updateEmployeeSalarySQLInjection(3600, "TGATES");
            //updateEmployeeSalarySQLInjection(3600, "TGATES'; DROP TABLE IF EXISTS important_table; -- ");
            String query = "SELECT * FROM employees";
            try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);
                 Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                 ResultSet resultSet = statement.executeQuery(query)) {
                System.out.printf("%10s|%10s%n","email","salary");
                System.out.println("------------------------");
                while(resultSet.next()) {
                    String email = resultSet.getString("email");
                    String salary = resultSet.getString("salary");
                    System.out.printf("%10s|%10s%n",email,salary);
                }


            } catch (Exception exception) {
                util.ExceptionHandler.handleException(exception);
            }
        } catch (Exception exception) {
            util.ExceptionHandler.handleException(exception);
        }
    }

    public static void updateEmployeeSalary(int newSalary, String email) throws Exception {

        String query = "UPDATE employees "
                + "SET salary=? "
                + "WHERE email = ?";

        try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);

             PreparedStatement preparedStatement = connection.prepareStatement(query);) {

            preparedStatement.setInt(1, newSalary);
            preparedStatement.setString(2, email);

            System.out.println(preparedStatement.toString());

            int count = preparedStatement.executeUpdate();
            System.out.println("Number of updated records " + count);

        }
    }

    public static void updateEmployeeSalarySQLInjection(int newSalary, String email) throws Exception {

        String query = "UPDATE employees "
                + "SET salary=  " + newSalary
                + "WHERE email = '" + email + "'";

        try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);
             Statement statement = connection.createStatement();) {

            int count = statement.executeUpdate(query);
            System.out.println("Salary of id with mail " + email + " updated sucessfully");

        }
    }
}
