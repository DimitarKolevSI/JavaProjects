package com.vmware.talentboost.jdbc.examples;

import util.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A ResultSet is a very powerful cursor (navigating the results):
 * <li> previous(): moves one row back
 * <li> absolute(int num): moves to the row with the specified number
 * <li> relative (int num): moves forward or backward
 * <li> first() and last()
 */
public class NavigationExamples {

    public static void main(String[] args) throws Exception {

        int rowId = 2;
        int relativePos = 3;

        try (Connection connection = DriverManager.getConnection(Constants.JDBC_URL, Constants.USER_NAME, Constants.USER_PASSWORD);
             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet resultSet = statement
                     .executeQuery("SELECT * FROM locations " + "where country_id in ('JP', 'US') order by city asc")) {

            ProductNavUtility navUtility = new ProductNavUtility(resultSet);

            System.out.println("All");
            navUtility.printForward();

            System.out.println("First row");
            navUtility.printFirst();

            System.out.println("Last row");
            navUtility.printLast();

            System.out.println("Row at position " + rowId);
            navUtility.printAt(rowId);

            System.out.println("Row " + (relativePos < 0 ? "Beforе" : "After") + " row " + relativePos);
            navUtility.printRelative(relativePos);

            System.out.println("All  in reverse order");
            navUtility.printReverse();

        } catch (Exception exception) {
            util.ExceptionHandler.handleException(exception);
        }
    }

    public static class ProductNavUtility {

        ResultSet resultSet = null;

        public ProductNavUtility(ResultSet resultSet) {
            this.resultSet = resultSet;
        }

        public void printForward() throws Exception {
            while(resultSet.next()) {
                String name = resultSet.getString("city");
                System.out.println("   " + name);
            }
        }

        public void printFirst() throws Exception{
            if(resultSet.first()) {
                String name = resultSet.getString("city");
                System.out.println("   " + name);
            }
        }

        public void printLast() throws Exception{
            if(resultSet.last()) {
                String name = resultSet.getString("city");
                System.out.println("   " + name);
            }
        }

        public void printAt(int postion) throws Exception{
            if(resultSet.absolute(postion)) {
                String name = resultSet.getString("city");
                System.out.println("   " + name);
            }
        }

        public void printRelative(int postion) throws Exception{
            if(resultSet.relative(postion)) {
                String name = resultSet.getString("city");
                System.out.println("   " + name);
            }
        }

        public void printReverse() throws Exception {
            resultSet.afterLast();
            while (resultSet.previous()) {
                String name = resultSet.getString("city");
                System.out.println("   " + name);
            }
        }
    }

}
