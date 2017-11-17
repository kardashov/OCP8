package stas.jdbc;
import java.sql.*;
public class DbConnectionMetaData {

	public static void main(String[] args) {
		// Get connection, execute query, get the result set
		// and print the entries from the result rest
		try {
			Connection connection = DbConnector.connectToDb();
			DatabaseMetaData metaData = connection.getMetaData();
			System.out.println("Displaying some of the database metadata from the Connection object");

			System.out.println("Database is: " + metaData.getDatabaseProductName() + " " + metaData.getDatabaseProductVersion());
			System.out.println("metaData.getDriverVersion(): " + metaData.getDriverName() + " " + metaData.getDriverVersion());
			System.out.println("metaData.getURL(): " + metaData.getURL());
			System.out.println("metaData.getUserName(): " + metaData.getUserName());
			System.out.println("metaData.getMaxRowSize(): " + metaData.getMaxRowSize());

		} catch (SQLException sqle) {

			sqle.printStackTrace();
			System.exit(-1);
		}
	}
}
