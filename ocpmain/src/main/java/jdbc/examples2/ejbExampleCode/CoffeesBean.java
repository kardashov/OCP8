package jdbc.examples2.ejbExampleCode;/*
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

import javax.ejb.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class CoffeesBean implements SessionBean {

    public CoffeesBean() {
    }

    public void ejbCreate() throws CreateException {
        try {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("jdbc/CoffeesDB");
        } catch (Exception e) {
            throw new CreateException();
        }
    }

    public RowSet getCoffees() throws SQLException {

        Connection con = null;

        try {
            con = ds.getConnection("webCustomer", "webPassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from coffees");

            CachedRowSet rset = null;//new CachedRowSet();
            rset.populate(rs);

            rs.close();
            stmt.close();

            return rset;
        } finally {
            if (con != null) con.close();
        }
    }

    public void placeOrder(String cofName, int quantity, String MgrId)
            throws SQLException {

        Connection con = null;

        try {
            con = ds.getConnection("webCustomer", "webPassword");
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into orders values (?, ?, ?)");
            pstmt.setString(1, cofName);
            pstmt.setInt(2, quantity);
            pstmt.setString(3, MgrId);
            pstmt.executeUpdate();

            pstmt.close();
        } finally {
            if (con != null) con.close();
        }
    }

    //
    // Methods inherited from SessionBean
    //

    public void setSessionContext(SessionContext sc) {
        this.sc = sc;
    }

    public void ejbRemove() {
    }

    public void ejbPassivate() {
    }

    public void ejbActivate() {
    }

    private SessionContext sc = null;
    private Context ctx = null;
    private DataSource ds = null;
}
