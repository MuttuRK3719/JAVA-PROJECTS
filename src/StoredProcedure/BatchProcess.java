package StoredProcedure;

import EmployeesDept.MakeConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BatchProcess {
    public static void main(String[] args) throws Exception {
        Connection con= MakeConnection.getConnection();
//        con.setAutoCommit(false);
        String query="INSERT INTO BOOK"+
                "(BOOK_ID,AUTHOR_NAME,BOOK_NAME,PRICE)"+
                "VALUES(?,?,?,?)";
        PreparedStatement psmt=con.prepareStatement(query);
//        psmt.setInt(1,901);
//        psmt.setString(2,"kkkk");
//        psmt.setString(3,"mkmk");
//        psmt.setDouble(4,40000.0);
//        psmt.addBatch();
//        psmt.setInt(1,902);
//        psmt.setString(2,"llll");
//        psmt.setString(3,"nnnn");
//        psmt.setDouble(4,500.0);
//        psmt.addBatch();
        psmt.executeBatch();
        con.commit();

    }
}
