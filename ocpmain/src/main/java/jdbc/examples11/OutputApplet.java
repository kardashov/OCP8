package jdbc.examples11;
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

/**
 * This is a demonstration JDBC applet.
 * It displays some simple standard output from the Coffee database.
 */

import java.applet.Applet;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class OutputApplet extends Applet implements Runnable {
    private Thread worker;
    private Vector queryResults;
    private String message = "Initializing";

    public synchronized void start() {
        // Every time "start" is called we create a worker thread to
        // re-evaluate the database query.
        if (worker == null) {
            message = "Connecting to database";
            worker = new Thread(this);
            worker.start();
        }
    }

    /**
     * The "run" method is called from the worker thread. Notice that because
     * this method is doing potentially slow databases accesses we avoid making
     * it a synchronized method.
     */

    public void run() {
        String url = "jdbc:mySubprotocol:myDataSource";
        String query = "select COF_NAME, PRICE from COFFEES";

        try {
            Class.forName("myDriver.ClassName");
        } catch (Exception ex) {
            setError("Can't find Database driver class: " + ex);
            return;
        }

        try {
            Vector results = new Vector();
            Connection con = DriverManager.getConnection(url, "myLogin", "myPassword");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String s = rs.getString("COF_NAME");
                float f = rs.getFloat("PRICE");
                String text = s + "     " + f;
                results.addElement(text);
            }

            stmt.close();
            con.close();

            setResults(results);

        } catch (SQLException ex) {
            setError("SQLException: " + ex);
        }
    }

    /**
     * The "paint" method is called by AWT when it wants us to display our
     * current state on the screen.
     */

    public synchronized void paint(Graphics g) {
        // If there are no results available, display the current message.
        if (queryResults == null) {
            g.drawString(message, 5, 50);
            return;
        }

        // Display the results.
        g.drawString("Prices of coffee per pound:  ", 5, 10);
        int y = 30;
        java.util.Enumeration enum1 = queryResults.elements();
        while (enum1.hasMoreElements()) {
            String text = (String) enum1.nextElement();
            g.drawString(text, 5, y);
            y = y + 15;
        }
    }

    /**
     * This private method is used to record an error message for later display.
     */

    private synchronized void setError(String mess) {
        queryResults = null;
        message = mess;
        worker = null;
        // And ask AWT to repaint this applet.
        repaint();
    }

    /**
     * This private method is used to record the results of a query, for later
     * display.
     */

    private synchronized void setResults(Vector results) {
        queryResults = results;
        worker = null;
        // And ask AWT to repaint this applet.
        repaint();
    }
}
