package EmployeesDept;

import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws SQLException,ClassNotFoundException{
       do {
           try {
               System.out.println("Enter choice 1 for add Employee ");
               Thread.sleep(2000);
               System.out.println("Enter choice 2 for update Employee details");
               Thread.sleep(2000);
               System.out.println("Enter choice 3 for delete Employee ");
               Thread.sleep(2000);
               System.out.println("Enter choice 4 for display Employee through id ");
                Thread.sleep(2000);
               System.out.println("Enter choice 5 to exit:");
               }
           catch(Exception e){
               System.out.println(e.getMessage());
           }

           System.out.println("Enter your choice: ");
           Scanner sc=new Scanner(System.in);
           int choice=sc.nextInt();
           switch (choice) {
               case 1: EmployeeCrud.insertOperation();break;
               case 2: EmployeeCrud.update();break;
               case 3:EmployeeCrud.deleteEmployee();break;
               case 4:EmployeeCrud.displayEmployeeDetails(); break;
               case 5 : return;
           }
       }while(true);
    }
}
