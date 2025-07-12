import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        try {
            // Load Oracle JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to Oracle DB
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:1521:orcl", "scott", "tiger");

            System.out.println("✅ Connected to Oracle DB successfully!");

            // Create statement
            Statement stmt = con.createStatement();

            // Execute query
            String query = "SELECT * FROM dept";
            ResultSet rs = stmt.executeQuery(query);

            // Print column headers
            System.out.printf("%-10s %-20s %-20s%n", "DEPTNO", "DNAME", "LOC");
            System.out.println("----------------------------------------------------------");

            // Loop through result set
            while (rs.next()) {
                int deptno = rs.getInt("deptNo");
                String dname = rs.getString("dName");
                String loc = rs.getString("loc");

                System.out.printf("%-10d %-20s %-20s%n", deptno, dname, loc);
            }

            // Close connection
            con.close();

        } catch (Exception e) {
            System.err.println("❌ Connection failed:");
            e.printStackTrace();
        }

    }
}