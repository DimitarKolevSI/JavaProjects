package com.vmware.talentboost.jdbc.exercises;

import util.Constants;

import java.sql.*;

class JobsComponent {

	// list (to stdout) all products and their price, quantity
	public void printJobList() throws Exception {
		// use Constants.JDBC_URL
		// TODO: implement
		Connection connection = DriverManager.getConnection(Constants.JDBC_URL,
				Constants.USER_NAME,Constants.USER_PASSWORD);
		PreparedStatement preparedStatement = connection
				.prepareStatement("select job_id, job_title, min_salary, max_salary from jobs");
		ResultSet resultSet = preparedStatement.executeQuery();
		System.out.printf("%-45s|%-45s|%-45s|%-45s%n","job_id","job_title","min_salary", "max_salary");
		for(int i=0;i<250;i++) System.out.print("-");
		System.out.println();
		while(resultSet.next()){
			String job_id = resultSet.getString("job_id");
			String job_title = resultSet.getString("job_title");
			int min_salary = resultSet.getInt("min_salary");
			int max_salary = resultSet.getInt("max_salary");
			System.out.printf("%-45s|%-45s|%-45d|%-45d%n",job_id,job_title,min_salary,max_salary);
		}
	}

	// list (to stdout) all products (and their price, quantity) that have price greater than or equal to the lowPrice and less than or equal to highPrice
	public void printJobList(int lowestSalary, int highestSalary) throws Exception {

		// TODO: implement
		// Use Prepared statements
		Connection connection = DriverManager.getConnection(Constants.JDBC_URL,Constants.USER_NAME,
				Constants.USER_PASSWORD);
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM" +
				" jobs WHERE ? > min_salary AND max_salary < ?");
		preparedStatement.setInt(1,lowestSalary);
		preparedStatement.setInt(2,highestSalary);

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String name = resultSet.getString("job_id");
			String title = resultSet.getString("job_title");
			int minSalary = resultSet.getInt("min_salary");
			int maxSalary = resultSet.getInt("max_salary");
				System.out.format("%-45s %-45s %7d %7d \n", name, title, minSalary, maxSalary);
		}

	}

}
