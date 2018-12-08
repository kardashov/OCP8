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
import java.util.*;
     
public class CreateNewType {

	public static void main(String [] args) {
		String url = "jdbc:mySubprotocol:myDataSource";

        Connection con;
        Statement stmt;
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

			String typeToCreate = null; 	
			String prompt = "Enter 's' to create a structured type " +
							"or 'd' to create a distinct type\n" +
							"and hit Return: ";
			do {
				typeToCreate = getInput(prompt) + " ";
				typeToCreate = typeToCreate.toLowerCase().substring(0, 1);
			} while ( !(typeToCreate.equals("s") || typeToCreate.equals("d")) );

			Vector dataTypes = getDataTypes(con, typeToCreate);

			String typeName;
			String attributeName;
			String sqlType;
			prompt = "Enter the new type name and hit Return: ";
			typeName = getInput(prompt);
			String createTypeString = "create type " + typeName;
			if ( typeToCreate.equals("d") )
				createTypeString += " as ";
			else	
				createTypeString += " (";
			
			String commaAndSpace = ", ";
			boolean firstTime = true;
			while (true){
				System.out.println("");
				prompt = "Enter an attribute name " + 
					"(or nothing when finished) \nand hit Return: ";
				attributeName = getInput(prompt);
				if (firstTime) {
					if (attributeName.length() == 0) {
						System.out.print("Need at least one attribute;");
						System.out.println(" please try again");
						continue;
					} else {
						createTypeString += attributeName + " ";
						firstTime = false;
					}
				} else if (attributeName.length() == 0) {
						break;
				} else {
					createTypeString += commaAndSpace
						+ attributeName + " "; 
				}
	
				String localTypeName = null;
				String paramString = "";
				while (true) {
					System.out.println("");
					System.out.println("LIST OF TYPES YOU MAY USE:  ");
					boolean firstPrinted = true;
					int length = 0;
					for (int i = 0; i < dataTypes.size(); i++) {
						DataType dataType = (DataType)dataTypes.get(i);
						if (!dataType.needsToBeSet()) {
							if (!firstPrinted)
								System.out.print(commaAndSpace);
							else
								firstPrinted = false;
							System.out.print(dataType.getSQLType());
							length += dataType.getSQLType().length();
							if ( length > 50 ) {
								System.out.println("");
								length = 0;
								firstPrinted = true;
							}
						}	
					}
					System.out.println("");
		
					int index;
					prompt = "Enter an attribute type " + 
						"from the list and hit Return:  ";
					sqlType = getInput(prompt);
					for (index = 0; index < dataTypes.size(); index++) {
						DataType dataType = (DataType)dataTypes.get(index);
						if (dataType.getSQLType().equalsIgnoreCase(
														sqlType) && 
							!dataType.needsToBeSet()) {
							break;
						}
					}

					localTypeName = null;
					paramString = "";
					if (index < dataTypes.size()) { // there was a match
						String params;
						DataType dataType = (DataType)dataTypes.get(index);
						params = dataType.getParams();
						localTypeName = dataType.getLocalType();
						if (params != null) {
							prompt = "Enter " + params + ":  ";
							paramString = "(" + getInput(prompt) + ")";
						} 
						break;
					}
					else {  						// use the name as given
						prompt = "Are you sure?  " +
							"Enter 'y' or 'n' and hit Return:  ";
						String check = getInput(prompt) + " ";
						check = check.toLowerCase().substring(0,1);
						if (check.equals("n")) 
							continue;
						else {
							localTypeName = sqlType;
							break;
						}
					}
				}
				
				createTypeString += localTypeName + paramString;

				if ( typeToCreate.equals("d") ) break;
			}
	
			if ( typeToCreate.equals("s") ) createTypeString += ")";
			System.out.println("");
			System.out.print("Your CREATE TYPE statement as ");
			System.out.println("sent to your DBMS:  ");
			System.out.println(createTypeString);
			System.out.println("");
	
	   		stmt.executeUpdate(createTypeString);
	
			stmt.close();
			con.close();
	
		} catch(SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}	
	}

	private static Vector getDataTypes(Connection con, String typeToCreate ) 
								throws SQLException {
		String structName = null, 
			   distinctName = null, 
			   javaName = null;

		// create a vector of class DataType initialized with
		// the SQL code, the SQL type name, and two null entries
		// for the local type name and the creation parameter(s)
	
		Vector dataTypes = new Vector();
		dataTypes.add(new DataType(java.sql.Types.BIT, "BIT"));
		dataTypes.add(new DataType(java.sql.Types.TINYINT, "TINYINT"));
		dataTypes.add(new DataType(java.sql.Types.SMALLINT, "SMALLINT"));
		dataTypes.add(new DataType(java.sql.Types.INTEGER, "INTEGER"));
		dataTypes.add(new DataType(java.sql.Types.BIGINT, "BIGINT"));
		dataTypes.add(new DataType(java.sql.Types.FLOAT, "FLOAT"));
		dataTypes.add(new DataType(java.sql.Types.REAL, "REAL"));
		dataTypes.add(new DataType(java.sql.Types.DOUBLE, "DOUBLE"));
		dataTypes.add(new DataType(java.sql.Types.NUMERIC, "NUMERIC"));
		dataTypes.add(new DataType(java.sql.Types.DECIMAL, "DECIMAL"));
		dataTypes.add(new DataType(java.sql.Types.CHAR, "CHAR"));
		dataTypes.add(new DataType(java.sql.Types.VARCHAR, "VARCHAR"));
		dataTypes.add(new DataType(java.sql.Types.LONGVARCHAR, "LONGVARCHAR"));
		dataTypes.add(new DataType(java.sql.Types.DATE, "DATE"));
		dataTypes.add(new DataType(java.sql.Types.TIME,"TIME"));
		dataTypes.add(new DataType(java.sql.Types.TIMESTAMP, "TIMESTAMP"));
		dataTypes.add(new DataType(java.sql.Types.BINARY, "BINARY"));
		dataTypes.add(new DataType(java.sql.Types.VARBINARY, "VARBINARY"));
		dataTypes.add(new DataType(java.sql.Types.LONGVARBINARY, 
			"LONGVARBINARY"));
		dataTypes.add(new DataType(java.sql.Types.NULL, "NULL"));
		dataTypes.add(new DataType(java.sql.Types.OTHER, "OTHER"));
		dataTypes.add(new DataType(java.sql.Types.BLOB, "BLOB"));
		dataTypes.add(new DataType(java.sql.Types.CLOB, "CLOB"));

		DatabaseMetaData dbmd = con.getMetaData();
		ResultSet rs = dbmd.getTypeInfo();
		while (rs.next()) {
			int codeNumber = rs.getInt("DATA_TYPE");
			String dbmsName = rs.getString("TYPE_NAME");
			String createParams = rs.getString("CREATE_PARAMS");

			if ( codeNumber == Types.STRUCT && structName == null )
				structName = dbmsName;
			else if ( codeNumber == Types.DISTINCT && distinctName == null ) 
				distinctName = dbmsName;
			else if ( codeNumber == Types.JAVA_OBJECT && javaName == null )  
				javaName = dbmsName;
			else { 
				for (int i = 0; i < dataTypes.size(); i++) {
					// find entry that matches the SQL code, 
					// and if local type and params are not already set,
					// set them
					DataType type = (DataType)dataTypes.get(i);
					if (type.getCode() == codeNumber) {
						type.setLocalTypeAndParams(dbmsName, createParams);
					}
				}
			}
		}

		if (typeToCreate.equals("s")) {
			int[] types = {Types.STRUCT, Types.DISTINCT, Types.JAVA_OBJECT}; 
			rs = dbmd.getUDTs(null, "%", "%", types); 
			while (rs.next()) {
				String typeName = null;
				DataType dataType = null;

				if ( dbmd.isCatalogAtStart() )
					typeName = rs.getString(1) + dbmd.getCatalogSeparator() +
						rs.getString(2) + "." + rs.getString(3);
				else 
					typeName = rs.getString(2) + "." + rs.getString(3) + 
						dbmd.getCatalogSeparator() + rs.getString(1);

				switch (rs.getInt(5)) {
				case Types.STRUCT:
					dataType = new DataType(Types.STRUCT, typeName);
					dataType.setLocalTypeAndParams(structName, null);
					break;
				case Types.DISTINCT:
					dataType = new DataType(Types.DISTINCT, typeName);
					dataType.setLocalTypeAndParams(distinctName, null);
					break;
				case Types.JAVA_OBJECT:
					dataType = new DataType(Types.JAVA_OBJECT, typeName);
					dataType.setLocalTypeAndParams(javaName, null);
					break;
				}
				dataTypes.add(dataType);
			}
		}

		return dataTypes;
	}			

	private static String getInput(String prompt) throws SQLException {
 
		 System.out.print(prompt);
		 System.out.flush();
	
		 try {
			 java.io.BufferedReader bin;
			 bin = new java.io.BufferedReader(
					new java.io.InputStreamReader(System.in));
										  
   		  	String result = bin.readLine();
			return result;

		} catch(java.io.IOException ex) {
			System.out.println("Caught java.io.IOException:");
			System.out.println(ex.getMessage());
			return "";
		}
	}
}

