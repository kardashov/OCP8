package stas.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

class DbCreateTable {
    public static void main(String[] args) {
        // Get connection, execute query, get the result set
        // and print the entries from the result rest
        try {
            Connection connection = DbConnector.connectToDb();
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            int result = statement
                    .executeUpdate("CREATE TABLE DEPARTMENTS2 ("
                            + "DEPARTMENT_ID NUMBER(4,0), "
                            + "DEPARTMENT_NAME VARCHAR2(30 BYTE) , "
                            + "MANAGER_ID NUMBER(6,0), "
                            + "LOCATION_ID NUMBER(4,0)) ");
            System.out.println("Table DEPARTMENTS2 successfully created: " + result);

        } catch (SQLException sqle) {

            sqle.printStackTrace();
            System.exit(-1);
        }
    }
}