package stas.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class DbUpdate {
    public static void main(String[] args) {
        // Get connection, execute query, get the result set
        // and print the entries from the result rest
        try {
            Connection connection = DbConnector.connectToDb();
            connection.setAutoCommit(false);
            Statement statement = connection
                    .createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);

            ResultSet resultSet = statement.executeQuery("SELECT f.* FROM departments f");
            System.out
                    .println("ID \tDEPARTMENT_ID \tDEPARTMENT_NAME \tMANAGER_ID \t\tLOCATION_ID");

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("DEPARTMENT_ID") + "\t"
                        + resultSet.getString("DEPARTMENT_NAME") + "\t"
                        + resultSet.getInt("MANAGER_ID") + "\t"
                        + resultSet.getInt("LOCATION_ID"));
            }

//	  UPDATE ������
            resultSet.absolute(1);
            resultSet.updateString("DEPARTMENT_NAME", "New department2");
            resultSet.updateRow();

            System.out.println("After the update");
            resultSet.beforeFirst();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("DEPARTMENT_ID") + "\t"
                        + resultSet.getString("DEPARTMENT_NAME") + "\t"
                        + resultSet.getInt("MANAGER_ID") + "\t"
                        + resultSet.getInt("LOCATION_ID"));
            }


//	��������� ������� ������		
            resultSet.moveToInsertRow();
            resultSet.updateInt(1, 124);
            resultSet.updateString(2, "Inserted dept3");
//			resultSet.updateInt(3, 1700);   �������� NULL
            resultSet.updateInt(4, 1700);
            resultSet.insertRow();


            System.out.println("After insert");
            resultSet.beforeFirst();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("DEPARTMENT_ID") + "\t"
                        + resultSet.getString("DEPARTMENT_NAME") + "\t"
                        + resultSet.getInt("MANAGER_ID") + "\t"
                        + resultSet.getInt("LOCATION_ID"));
            }

            connection.commit();
            System.out.println(statement.getMaxRows());

        } catch (SQLException sqle) {

            sqle.printStackTrace();
            System.exit(-1);
        }
    }
}
