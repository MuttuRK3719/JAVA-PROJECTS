package EmployeesDept;

import java.net.IDN;
import java.sql.*;

public class EmployeeDB_Operation {
    static Connection con;
    static void save(String tableName,Employees emp) throws SQLException, ClassNotFoundException {
        con=MakeConnection.getConnection();
        String query="INSERT INTO "+tableName+
                    " (EMP_ID,EMP_NAME,EMP_SALARY,DEPT_ID,MANAGER_ID) "+
                    "VALUES(?,?,?,?,?)";
        PreparedStatement psmn=con.prepareStatement(query);
        psmn.setInt(1,emp.getEMP_ID());
        psmn.setString(2,emp.getEMP_NAME());
        psmn.setDouble(3,emp.getSalary());
        psmn.setInt(4,emp.getDepartment_ID());
        psmn.setInt(5,emp.getManager_id());
        psmn.setInt(5,emp.getEMP_ID());
        int result=psmn.executeUpdate();
        if(result>0) System.out.println("Employee add successfully: ");
        else System.out.println("Failed to add employee to table");
    }
    static void updateEmployeeDetails (String tableName,Employees emp,int id)throws SQLException,ClassNotFoundException{
        con=MakeConnection.getConnection();
        String query="UPDATE "+tableName+" SET "+
                    "EMP_NAME=?,EMP_SALARY=?,DEPT_ID=?,MANAGER_ID=?"+
                    "WHERE EMP_ID="+ id;
        PreparedStatement psmn=con.prepareStatement(query);
        psmn.setString(1,emp.getEMP_NAME());
        psmn.setInt(2,emp.getSalary());
        psmn.setInt(3,emp.getDepartment_ID());
        psmn.setInt(4,emp.getManager_id());
        boolean result=psmn.execute();
        if(!result) System.out.println("Employee update successfully ");
        else System.out.println("failed to update employee details please try once again");
    }
    static void deleteEmployee(String tableName,int EmployeeId) throws SQLException, ClassNotFoundException {
        con=MakeConnection.getConnection();
        String query="DELETE FROM "+tableName+" WHERE EMP_ID= "+EmployeeId;
        PreparedStatement psmn=con.prepareStatement(query);
        boolean result=psmn.execute();
        if(!result) System.out.println("The Employee deleted successfully ");
        else System.out.println("Failed to delete");
    }
    static void displayDetails(String tableName,int EmployeeId)throws SQLException,ClassNotFoundException{
        con=MakeConnection.getConnection();
        String query="SELECT * FROM "+tableName;
        PreparedStatement psmn=con.prepareStatement(query);
        ResultSet res=psmn.executeQuery();
        while(res.next()){
            System.out.println("Employee Id: "+res.getInt("EMP_ID"));
            System.out.println("Employee Name: "+res.getString("EMP_NAME"));
            System.out.println("Employee Salary :"+res.getDouble("EMP_SALARY"));
            System.out.println("Employee Dept Id:"+res.getInt("DEPT_ID"));
            System.out.println("Employee Manager Id :"+res.getInt("MANAGER_ID"));
        }
    }
}
