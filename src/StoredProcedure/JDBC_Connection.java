package StoredProcedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC_Connection {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.Driver.OracleDriver");
        return DriverManager.getConnection("jdbc.oracle.thin.1521.orcl","scott","tiger");
//        return DriverManager.getConnection("jdbc.oracle.thin.1521.orcl","scott","tiger");
    }
}

