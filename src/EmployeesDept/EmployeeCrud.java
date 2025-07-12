package EmployeesDept;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeCrud {
    static Employees emp=new Employees();
    static Scanner sc=new Scanner(System.in);
    static String tableName="EMPLOYEES";
    static void insertOperation() throws SQLException,ClassNotFoundException {
        System.out.println("Enter the Employee Id: ");
        emp.setEMP_ID(sc.nextInt());
        System.out.println("Enter the Employee name: ");
        emp.setEMP_NAME(sc.next());
        System.out.println("Enter the Employee Dept ID: ");
        emp.setDepartment_ID(sc.nextInt());
        System.out.println("Enter the Employee Manager Id");
        emp.setManager_id(sc.nextInt());
        System.out.println("Enter the Employee salary");
        emp.setSalary(sc.nextInt());
        EmployeeDB_Operation.save(tableName,emp);
    }
    static void update() throws SQLException,ClassNotFoundException{
        System.out.println("Enter the Employee Id: ");
        int id=sc.nextInt();
        emp.setEMP_ID(id);
        System.out.println("Enter the Employee name: ");
        emp.setEMP_NAME(sc.next());
        System.out.println("Enter the Employee Dept ID: ");
        emp.setDepartment_ID(sc.nextInt());
        System.out.println("Enter the Employee Manager Id");
        emp.setManager_id(sc.nextInt());
        System.out.println("Enter the Employee salary");
        emp.setSalary(sc.nextInt());
        EmployeeDB_Operation.updateEmployeeDetails(tableName,emp,id);
    }
    static void deleteEmployee() throws SQLException, ClassNotFoundException {
        System.out.println("Enter the Employee Id u want delete that:");
        EmployeeDB_Operation.deleteEmployee(tableName,sc.nextInt());
    }
    static  void displayEmployeeDetails() throws SQLException, ClassNotFoundException {
        System.out.println("Enter the Employee Id: ");
        EmployeeDB_Operation.displayDetails(tableName,sc.nextInt());
    }
}
