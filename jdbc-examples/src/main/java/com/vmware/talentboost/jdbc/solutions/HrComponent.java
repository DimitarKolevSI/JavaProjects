package com.vmware.talentboost.jdbc.solutions;

import util.Constants;

import java.sql.*;

class HrComponent {

    public boolean deleteEmployee(String employeeId) throws Exception {

        try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);

             PreparedStatement preparedStatement = connection
                     .prepareStatement("DELETE FROM employees WHERE employee_id = ?");) {
            preparedStatement.setInt(1, Integer.valueOf(employeeId));
            int count = preparedStatement.executeUpdate();
            return count > 0 ? true : false;
        }
    }

    public int addEmployee(String lastName, String firstName, String phoneNumber, String email, String salary,
                           String jobId, String hireDate) throws Exception {

        Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);

        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO employees " + "(last_name, first_name, phone_number, email, salary, job_id, hire_date) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)",

                Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setString(1, lastName);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, phoneNumber);
        preparedStatement.setString(4, email);
        preparedStatement.setInt(5, Integer.valueOf(salary));
        preparedStatement.setString(6, jobId);
        preparedStatement.setDate(7, new Date(Long.valueOf(hireDate)));

        preparedStatement.executeUpdate();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        int autogenkey = 0;

        if (resultSet.next()) {
            autogenkey = resultSet.getInt(1);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

        return autogenkey;

    }

    public int replaceManager(String managerBeingReplaced, String replacementManager) throws Exception {

        Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);

        PreparedStatement preparedStatement = connection
                .prepareStatement("UPDATE employees SET manager_id = ? WHERE manager_id = ?");

        preparedStatement.setInt(1, Integer.valueOf(replacementManager));
        preparedStatement.setInt(2, Integer.valueOf(managerBeingReplaced));
        int count = preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
        return count;

    }

}
