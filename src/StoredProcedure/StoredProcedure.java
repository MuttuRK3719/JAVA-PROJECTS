package StoredProcedure;

import EmployeesDept.MakeConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Scanner;

public class StoredProcedure {
    public static void main(String[] args) throws Exception {
        Connection con= MakeConnection.getConnection();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the book Id");
        int book_id=sc.nextInt();
        System.out.println("Enter the price want increase");
        double updatePrice=sc.nextDouble();
        String procedure_cal="{call BOOK_PRICE_INCRESE(?,?)}";
        CallableStatement clst=con.prepareCall(procedure_cal);
        clst.setInt(1,book_id);
        clst.setDouble(2,updatePrice);
        clst.execute();
    }
}
