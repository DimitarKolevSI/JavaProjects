package com.vmware.talentboost.jdbc.examples;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionPoolExamples {

    public static void main(String [] args) {

        for(int i = 0; i < 5; i++) {
            Thread t = new Thread(new ParallelQueryRunnable(), "ConnectionTest_" + i);
            t.start();
        }
    }

    public static  class ConnectionPool {

        private static HikariDataSource dataSource;

        static {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(Constants.JDBC_URL);
            config.setUsername(Constants.USER_NAME);
            config.setPassword(Constants.USER_PASSWORD);

            dataSource = new HikariDataSource(config);
            dataSource.setMaximumPoolSize(4);
        }

        public static Connection getConnection() throws SQLException {
            return dataSource.getConnection();
        }
    }


    public static class ParallelQueryRunnable implements Runnable{

        public void tryConnection() throws Exception {

            try(Connection con = ConnectionPool.getConnection();) {
                PreparedStatement preparedStatement
                        = con.prepareStatement("select * from regions");

                preparedStatement.execute();

                String msg = "Executing query on Thread " + Thread.currentThread().getName() + " --> using connection " + this.getConnectionId(con);
                System.out.println(msg);
            }
        }

        public String getConnectionId(Connection con) {
            String conId = con.toString();
            int lastPos = conId.length() - 2;
            conId = conId.substring(lastPos);
            return conId;
        }

        public void run() {
            for(int i = 0; i < 3; i++) {
                try {
                    tryConnection();
                } catch (Exception exception) {
                    ;
                }
            }
        }



        public static long sleepInterval() {
            Double randomDouble = ParallelQueryRunnable.randomize(0,500);
            return Math.round(randomDouble);
        }
        public static double randomize(double min, double max){
            double x = (int)(Math.random()*((max-min)+1))+min;
            return x;
        }

    }
}
