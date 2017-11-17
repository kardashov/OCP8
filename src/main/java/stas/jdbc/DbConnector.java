package stas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnector {
	public static Connection connectToDb() throws SQLException {
		Properties prop = new Properties();
//		prop.put("oracle.net.encryption_client", "REQUIRED");
//		prop.put("oracle.net.encryption_types_client", "( DES40 )");
//		prop.put("oracle.net.crypto_checksum_client", "REQUESTED");
//		prop.put("oracle.net.crypto_checksum_types_client", "( MD5 )");
		String url = "jdbc:oracle:thin://localhost:1521/";		
		prop.put("user", "hr");
		prop.put("password", "11111");
		
		String database = "orcl";
		String userName = "hr";
		String password = "11111";
		return DriverManager.getConnection(url + database, prop);
	}
}
