package com.vmware.talentboost.jdbc.examples;


import util.Constants;

import java.sql.*;

/**
 * Three different ways of executing SQL statements:
 * <li> Statement (both static and dynamic SQL statements)
 * <li> PreparedStatement (semi-static SQL statements)
 * <li> CallableStatment (stored procedures)
 * <p><br/>
 *
 * PreparedStatement class: Precompiled, parametrized SQL statements:<br/>
 * <li> Structure is fixed
 * <li> Values of parameters are determined at run-time
 */
public class PreparedStatementExamples {


    public static void main(String[] args) throws Exception {
        try {
            try(Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);

                PreparedStatement preparedStatement =
                        connection.prepareStatement(
                                "SELECT * FROM locations where country_id = ? ");) {

                preparedStatement.setString(1, "UK");

                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state_province");
                    System.out.println("  " + city + "   " + state);
                }
            }

        } catch (Exception exception) {
            util.ExceptionHandler.handleException(exception);
        }
    }



}
