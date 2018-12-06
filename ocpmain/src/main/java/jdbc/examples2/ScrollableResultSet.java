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

public class ScrollableResultSet {

    public static void main(String args[]) {

        String url = "jdbc:mySubprotocol:myDataSource";
        Connection con;
        Statement stmt;
        try {

            Class.forName("myDriver.ClassName");

        } catch (java.lang.ClassNotFoundException e) {
            System.err.print("ClassNotFoundException: ");
            System.err.println(e.getMessage());
        }

        try {

            con = DriverManager.getConnection(url,
                    "myLogin", "myPassword");

            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet srs = stmt.executeQuery("SELECT * FROM COFFEES");

            srs.absolute(4);
            int rowNum = srs.getRow(); // rowNum should be 4
            System.out.println("rowNum should be 4 " + rowNum);
            srs.relative(-3);
            rowNum = srs.getRow(); // rowNum should be 1
            System.out.println("rowNum should be 1 " + rowNum);
            srs.relative(2);
            rowNum = srs.getRow(); // rowNum should be 3
            System.out.println("rowNum should be 3 " + rowNum);

            srs.absolute(1);
            System.out.println("after last? " + srs.isAfterLast());
            if (!srs.isAfterLast()) {
                String name = srs.getString("COF_NAME");
                float price = srs.getFloat("PRICE");
                System.out.println(name + "     " + price);
            }

            srs.afterLast();
            while (srs.previous()) {
                String name = srs.getString("COF_NAME");
                float price = srs.getFloat("PRICE");
                System.out.println(name + "     " + price);
            }

            srs.close();
            stmt.close();
            con.close();

        } catch (BatchUpdateException b) {
            System.err.println("-----BatchUpdateException-----");
            System.err.println("SQLState:  " + b.getSQLState());
            System.err.println("Message:  " + b.getMessage());
            System.err.println("Vendor:  " + b.getErrorCode());
            System.err.print("Update counts:  ");
            int[] updateCounts = b.getUpdateCounts();
            for (int i = 0; i < updateCounts.length; i++) {
                System.err.print(updateCounts[i] + "   ");
            }
            System.out.println("");

        } catch (SQLException ex) {
            System.err.println("-----SQLException-----");
            System.err.println("SQLState:  " + ex.getSQLState());
            System.err.println("Message:  " + ex.getMessage());
            System.err.println("Vendor:  " + ex.getErrorCode());
        }
    }
}

