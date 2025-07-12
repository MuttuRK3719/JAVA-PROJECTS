package Products;

import java.sql.SQLException;
import java.util.Scanner;

public class ProductCrudOperation {
    static Product product=new Product();
    static Scanner sc=new Scanner(System.in);
    static String tableName="PRODUCT";
public static void addProduct() throws SQLException, ClassNotFoundException {
    System.out.println("Enter the product id: ");
    product.setProduct_id(sc.nextInt());
    sc.nextLine();
    System.out.println("Enter the product name: ");
    product.setProduct_name(sc.nextLine());
    System.out.println("Enter the product Price: ");
    product.setProductPrice(sc.nextDouble());
    System.out.println("Enter the product quantity: ");
    product.setProductQuantity(sc.nextInt());
    ProductDBOperation.addProduct(tableName,product);
}
public static void updateProduct() throws SQLException, ClassNotFoundException {
    System.out.println("Enter the product id: ");
    int productId=sc.nextInt();
    product.setProduct_id(productId);
    sc.nextLine();
    System.out.println("Enter the product name: ");
    product.setProduct_name(sc.nextLine());
    System.out.println("Enter the product Price: ");
    product.setProductPrice(sc.nextDouble());
    System.out.println("Enter the product quantity: ");
    product.setProductQuantity(sc.nextInt());
    ProductDBOperation.updateProduct(tableName,product,productId);
}
public static void displayProducts() throws SQLException, ClassNotFoundException {
    System.out.println("Enter the product id ");
    ProductDBOperation.display(tableName,sc.nextInt());
}
public static void deleteProduct() throws SQLException, ClassNotFoundException {
    System.out.println("Enter the product id");
    ProductDBOperation.deleteProduct(tableName,sc.nextInt());
}
public static void viewAllProducts() throws SQLException, ClassNotFoundException {
    ProductDBOperation.
            viewAllProducts(tableName);
}
    public static void placeorder() throws SQLException, ClassNotFoundException, InterruptedException {
        System.out.println("Enter the valid user id: ");
        int userId=sc.nextInt();
        System.out.println("Enter the product id");
        int productId=sc.nextInt();
        System.out.println("Enter the quantity of  product ");
        int quantity=sc.nextInt();
        ProductDBOperation.placeOrder(userId,productId,quantity);

    }
    public static void invoice() throws SQLException, ClassNotFoundException {
        System.out.println("Enter the customer id");
        int customerId=sc.nextInt();
        ProductDBOperation.invoice(customerId);
    }
}
