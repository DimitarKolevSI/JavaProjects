package com.vmware.talentboost.jdbc.exercises;

import util.Constants;

import java.sql.*;

class HrComponent {

	// use Constants.JDBC_URL and user and password

	// Return true if Employee exists and it is deleted otherwise it is false
	public boolean deleteEmployee(String employeeNumber) throws Exception {

		// TODO: implement
		try
		{
			Connection connection = DriverManager.getConnection(Constants.JDBC_URL,Constants.USER_NAME,
				Constants.USER_PASSWORD);
			String statement = "DELETE FROM employees WHERE employee_id = " + employeeNumber;
		    PreparedStatement preparedStatement = connection.prepareStatement(statement);
		    int count = preparedStatement.getUpdateCount();
		    return count > 0?true:false;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

		
	}

	/**
	 * Returns the Primary Key of the Added Employee
	 *
	 * hireDate - is time in milliseconds
	 */
	public int addEmployee(String lastName, String firstName, String phoneNumber, String email, String salary,
						   String jobId, String hireDate) throws Exception {
		// TODO: implement
		Connection connection = DriverManager.getConnection(Constants.JDBC_URL,Constants.USER_NAME,
				Constants.USER_PASSWORD);
		PreparedStatement preparedStatement = connection.prepareStatement(
				"INSERT INTO employees " + "(last_name, first_name, phone_number, email, salary, job_id, hire_date)"+
				" VALUES (?,?,?,?,?,?,?)"
		);
		preparedStatement.setString(1,lastName);
		preparedStatement.setString(2,firstName);
		preparedStatement.setString(3,phoneNumber);
		preparedStatement.setString(4,email);
		preparedStatement.setInt(5,Integer.valueOf(salary));
		preparedStatement.setString(6,jobId);
		preparedStatement.setDate(7,new Date(Long.valueOf(hireDate)));

		preparedStatement.executeUpdate();

		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		int key = -1;
		if(resultSet.next()){
			key = resultSet.getInt(1);
		}

		resultSet.close();
		preparedStatement.close();
		connection.close();
		// HINT: Look up the Schema  -  you will need to cast some of the Strings !
		return key;
	}

	/**
	 * Return how many records have been updated
	 */
	public int replaceManager(String managerBeingReplaced, String replacementManager) throws Exception {

		// TODO: implement
		Connection connection = DriverManager.getConnection(Constants.JDBC_URL,Constants.USER_NAME,
				Constants.USER_PASSWORD);
		PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employees SET manager_id = ? WHERE" +
				" manager_id = ?");
		preparedStatement.setInt(1,Integer.valueOf(replacementManager));
		preparedStatement.setInt(2,Integer.valueOf(managerBeingReplaced));
		int count = preparedStatement.getUpdateCount();
		preparedStatement.close();
		connection.close();
		// HINT: Look up the Schema  -  you will need to cast some of the Strings !
		return count;
	}

}
