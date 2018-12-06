package jdbc.examples2.ejbExampleCode;

import javax.ejb.*;
import javax.sql.RowSet;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface Coffees extends EJBObject {
    RowSet getCoffees() throws RemoteException, SQLException;

    void placeOrder(String cofName, int quantity, String MgrId)
            throws RemoteException, SQLException;
}
