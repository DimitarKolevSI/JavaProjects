package com.vmware.talentboost.jdbc.solutions;

import util.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class JobsComponent {

	public void printJobList() throws Exception {

		try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);

			 PreparedStatement preparedStatement = connection
					 .prepareStatement("select job_id, job_title, min_salary, max_salary from jobs");
			 ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {

				String name = resultSet.getString("job_id");
				String title = resultSet.getString("job_title");
				int minSalary = resultSet.getInt("min_salary");
				int maxSalary = resultSet.getInt("max_salary");

				System.out.format("%-45s %-45s %7d %7d \n", name, title, minSalary, maxSalary);

			}

		}
	}

	public void printJobList(int lowestSalary, int highestSalary) throws Exception {

		try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);

			 PreparedStatement preparedStatement = connection
						.prepareStatement("SELECT * FROM jobs "
								+ "WHERE min_salary > ?  AND max_salary < ?");) {

			preparedStatement.setInt(1, lowestSalary);
			preparedStatement.setInt(2, highestSalary);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {
					String name = resultSet.getString("job_id");
					String title = resultSet.getString("job_title");
					int minSalary = resultSet.getInt("min_salary");
					int maxSalary = resultSet.getInt("max_salary");

					System.out.format("%-45s %-45s %7d %7d \n", name, title, minSalary, maxSalary);
				}

			}
		}
	}

}
