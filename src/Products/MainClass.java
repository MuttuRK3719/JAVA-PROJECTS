package Products;

import EmployeesDept.EmployeeCrud;

import java.sql.SQLException;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) throws SQLException, Exception {

        productRelatedOperation();
        ProductCrudOperation.invoice();
    }
    static void productManagement() throws SQLException, ClassNotFoundException {
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Enter your choice: ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ProductCrudOperation.addProduct();
                    break;
                case 2:
                    ProductCrudOperation.updateProduct();
                    break;
                case 3:
                    ProductCrudOperation.deleteProduct();
                    break;
                case 4:
                    ProductCrudOperation.displayProducts();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }
    static void productRelatedOperation() throws SQLException, ClassNotFoundException, InterruptedException {
        do {
            try {
                System.out.println("Enter choice 1 for product Management ");
                Thread.sleep(2000);
                System.out.println("Enter choice 2 for view Products");
                Thread.sleep(2000);
                System.out.println("Enter choice 3 for place Order");
                Thread.sleep(2000);
                System.out.println("Enter choice 4 generate invoice ");
                Thread.sleep(2000);
                System.out.println("Enter choice 5 to exit:");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Enter your choice: ");
            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    productManagement();
                    break;
                case 2:
                    ProductCrudOperation.viewAllProducts();
                    break;
                case 3:
                    ProductCrudOperation.placeorder();
                    break;
                case 4:
                    ProductCrudOperation.invoice();
                    break;
                case 5:
                    return;
            }
        } while (true);
    }
}
