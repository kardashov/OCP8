/*
 * @(#)Graph.java       1.7 98/07/17
 *
 * Copyright 1997, 1998, 1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */
import java.sql.*;

public class CreateUDTs {
	public static void main(String args[]) {

		String url = "jdbc:mySubprotocol:myDataSource";
		Connection con;
		Statement stmt;

		String createAddress = "CREATE TYPE ADDRESS (NUM INTEGER, " +
                        "STREET VARCHAR(40), CITY VARCHAR(40), " +
                        "STATE CHAR(2), ZIP CHAR(5))";

		String createManager = "CREATE TYPE MANAGER (MGR_ID INTEGER, " +
			"LAST_NAME VARCHAR(40), FIRST_NAME VARCHAR(40), " +
			"PHONE char(10))";

		try {

			Class.forName("myDriver.ClassName");

		} catch(java.lang.ClassNotFoundException e) {
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		try {
			con = DriverManager.getConnection(url,
								"myLogin", "myPassword");

			stmt = con.createStatement();

			stmt.executeUpdate(createAddress);
			stmt.executeUpdate("CREATE TYPE PHONE_NO AS CHAR(10)");
			stmt.executeUpdate(createManager);

			stmt.close();
			con.close();

        } catch(SQLException ex) {
            System.err.println("-----SQLException-----");
            System.err.println("SQLState:  " + ex.getSQLState());
            System.err.println("Message:  " + ex.getMessage());
            System.err.println("Vendor:  " + ex.getErrorCode());
        }
	}
}
