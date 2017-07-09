package stas.jdbc;
import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
public class _MainJDBC {
	private static final String USERNAME = "dbuser";
	private static final String PASSWORD = "dbpassword"; 
	private static final String url = "jdbc:mysql://localhost/explorecalifornia";
//	private static final String url = "jdbc:derby:zoo;create=true";
	public static void main(String[] args) throws SQLException {

		
		
		try (Connection conn = DriverManager.getConnection(url, USERNAME, PASSWORD); 
				Statement stmt = conn.createStatement()) {
			/*stmt.executeUpdate("CREATE TABLE species (" + "id INTEGER PRIMARY KEY, " + "name VARCHAR(255), "
					+ "num_acres DECIMAL)");
			stmt.executeUpdate("CREATE TABLE animal (" + "id INTEGER PRIMARY KEY, " + "species_id integer, "
					+ "name VARCHAR(255), " + "date_born TIMESTAMP)");
			
			
			stmt.executeUpdate("INSERT INTO species VALUES (1, 'African Elephant', 7.5)");
			stmt.executeUpdate("INSERT INTO species VALUES (2, 'Zebra', 1.2)");
			stmt.executeUpdate("INSERT INTO animal VALUES (1, 1, 'Elsa', '2001−05−06 02:15')");
			stmt.executeUpdate("INSERT INTO animal VALUES (2, 2, 'Zelda', '2002−08−15 09:12')");
			stmt.executeUpdate("INSERT INTO animal VALUES (3, 1, 'Ester', '2002−09−09 10:36')");
			stmt.executeUpdate("INSERT INTO animal VALUES (4, 1, 'Eddie', '2010−06−08 01:24')");
			stmt.executeUpdate("INSERT INTO animal VALUES (5, 2, 'Zoe', '2005−11−12 03:44')");*/
			System.out.println("====================Get DB Metadata============================");
			DatabaseMetaData metaData = conn.getMetaData();
			System.out.println(metaData.getNumericFunctions());
			System.out.println(metaData.getDatabaseProductName());
			System.out.println(metaData.getDriverName());
			ResultSet metadataRs = metaData.getTables(null, "%", "%", new String[] {"TABLE"});
			

			
			
//			DriverManager.
//			myStmt = conn.createStatement();
//			myStmt = conn.createStatement(resultSetType, resultSetConcurrency)
//			myStmt = conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
			
			System.out.println("=================Create and execute Statement====================");
			
			
			Statement myStmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, 
															/*TYPE_SCROLL_INSENSITIVE
															  TYPE_SCROLL_SENSITIVE*/
/*			with SCROLL_INSENSITIVE , you have a static view of what the ResultSet looked like when you
			did the query. If the data changed in the table, you will see it as it was when you did the
			query. With TYPE_SCROLL_SENSITIVE,you would see the latest data when scrolling through the ResultSet*/
													ResultSet.CONCUR_READ_ONLY);
											         		/*CONCUR_UPDATABLE*/
			
			myStmt = conn.createStatement();
			int result = stmt.executeUpdate("insert into species values(10, 'Deer', 3)");
			System.out.println(result); // 1   --prints the number of affected rows
			result = stmt.executeUpdate("update species set name = '' where name = 'None'");
			System.out.println(result); // 0
			result = stmt.executeUpdate("delete from species where id = 10");
			System.out.println(result); // 1
			
			result = stmt.executeUpdate("select * from animal");//*This throws a SQLException similar to the following:
																//A result was returned when none was expected.*/
			
			
			ResultSet rs = stmt.executeQuery("select * from species"); // execute QUERY
			
			rs = stmt.executeQuery("update species where 1=1");//SQLException when using executeQuery() with SQL that
														//changes the database: No results were returned by the query.
			
			
			System.out.println("=================Using of Statement.execute(...)====================");
			String sql = "select 1";
			boolean isResultSet = stmt.execute(sql );
			if (isResultSet) {
				rs = stmt.getResultSet();
				System.out.println("ran a query");
			} else {
				result = stmt.getUpdateCount();
				System.out.println("ran an update");
			}
			
			
			System.out.println("=========================Prepared statements=============================");
			PreparedStatement ps = conn.prepareStatement("delete from animal where name = ?");
			ps.setString(1, "my name"); // The JDBC driver takes care of all the escaping for you. This is convenient
			ps.execute();
			
			System.out.println("=====================Getting Data from a ResultSet=======================");
			Map<Integer, String> idToNameMap = new HashMap<>();
				rs = stmt.executeQuery("select id, name from species");
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				idToNameMap.put(id, name);
			}
			System.out.println(idToNameMap); // {1=African Elephant, 2=Zebra}
//			rs.getInt("badColumn"); // throws SQLException

			System.out.println("======================Scrolling ResultSet================================");
			
			
		}
	}
}
