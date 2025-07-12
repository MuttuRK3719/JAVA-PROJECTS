package Products;

import EmployeesDept.OutOfStackException;

import java.sql.*;
import java.time.LocalDate;

public class ProductDBOperation {
    static Connection con;
    static String orderTable="ProductOrder";
    public static void addProduct(String tableName,Product p) throws SQLException, ClassNotFoundException {
        con=MakeConnection.getConncetion();
        String query="INSERT INTO "+tableName+" " +
                "(product_id,product_name,product_price,product_quantity)"+
                " values(?,?,?,?)";
        PreparedStatement psmt=con.prepareStatement(query);
        psmt.setInt(1,p.getProduct_id());
        psmt.setString(2,p.getProduct_name());
        psmt.setDouble(3,p.getProductPrice());
        psmt.setInt(4,p.getProductQuantity());
        boolean result=psmt.execute();
        if(!result) System.out.println("product added successfully😊😊😊");
        else System.out.println("Failed to add product");
    }
    public static void updateProduct(String tableName,Product pro,int productId) throws SQLException, ClassNotFoundException {
        con=MakeConnection.getConncetion();
        String query="UPDATE "+tableName+" SET  PRODUCT_NAME=?,PRODUCT_PRICE=?,PRODUCT_QUANTITY=? " +
                "WHERE PRODUCT_ID="+productId;
        PreparedStatement psmt=con.prepareStatement(query);
        psmt.setString(1,pro.getProduct_name());
        psmt.setDouble(2,pro.getProductPrice());
        psmt.setInt(3,pro.getProductQuantity());
        boolean result=psmt.execute();
        if(!result) System.out.println("The Product is updated successfully ");
        else System.out.println("Failed to update the product🙁🙁🙁");
    }
    public static void display(String tableName,int productId) throws SQLException, ClassNotFoundException {
        con=MakeConnection.getConncetion();
        String query="SELECT * FROM "+tableName+" WHERE PRODUCT_ID="+productId;
        PreparedStatement psmt=con.prepareStatement(query);
        ResultSet res=psmt.executeQuery();
        while(res.next()){
            System.out.println("Product id is: "+res.getInt("PRODUCT_ID"));
            System.out.println("Product name is :"+res.getString("PRODUCT_NAME"));
            System.out.println("Product price is :"+res.getInt("PRODUCT_PRICE"));
            System.out.println("Product available  stack is :"+res.getInt("Product_Quantity"));
        }

    }

    public static void deleteProduct(String tableName, int productId) throws SQLException, ClassNotFoundException {
        String query="DELETE FROM "+tableName+" WHERE PRODUCT_ID="+productId;
        con=MakeConnection.getConncetion();
        PreparedStatement psmt=con.prepareStatement(query);
        boolean result=psmt.execute();
        if(!result) System.out.println("The product is deleted successfully 🤩🤩🤩🤩");
        else System.out.println("Failed to delete the product 😒😒");
    }
    public static void viewAllProducts(String tableName) throws SQLException, ClassNotFoundException {
        con=MakeConnection.getConncetion();
        String query="SELECT * FROM "+tableName;
        PreparedStatement ps=con.prepareStatement(query);
        ResultSet res=ps.executeQuery();
        System.out.println(String.format("%-15s", "PRODUCT_ID") +
                String.format("%-25s", "PRODUCT_NAME") +
                String.format("%-20s","PRODUCT_QUANTITY") +
                String.format("%-30s", "PRODUCT_PRICE"));
        while (res.next()) {
            System.out.println("-----------------------------------------------------------------------");
            System.out.println(String.format("%-15d", res.getInt("PRODUCT_ID")) +
                    String.format("%-30s", res.getString("PRODUCT_NAME")) +
                            String.format("%-15d", res.getInt("PRODUCT_QUANTITY")) +
                            String.format("%-10.3f", res.getDouble("PRODUCT_PRICE")));
        }

    }
    public static void placeOrder(int userId, int productId, int quantity) throws SQLException, ClassNotFoundException, InterruptedException {
        con=MakeConnection.getConncetion();


        String query="INSERT INTO "+orderTable+" (orderId,product_id,order_date,customer_id,QUANTITY) VALUES (?,?,?,?,?)";
        PreparedStatement ps=con.prepareStatement(query);
        int ordernumber=orderNumber();
        int availableStack=availableStack(productId);
        try{
            if(quantity>availableStack)
            throw new OutOfStackException();
        }
        catch (OutOfStackException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            Thread.sleep(500);
            return;
        }
        ps.setInt(1,ordernumber);
        ps.setInt(2,productId);
        ps.setDate(3,java.sql.Date.valueOf(LocalDate.now()));
        ps.setInt(4,userId);
        ps.setInt(5,quantity);

        String procedure_cal="{call updateTable(?,?)}";
        CallableStatement cs=con.prepareCall(procedure_cal);
        cs.setInt(1,productId);
        cs.setInt(2,quantity);
        cs.execute();
        boolean result=ps.execute();
        if(!result) System.out.println("Product is placed successfully ");
    }

    private static int availableStack(int productId) throws SQLException, ClassNotFoundException {
        con=MakeConnection.getConncetion();
        PreparedStatement ps=con.prepareStatement("SELECT product_quantity FROM PRODUCT WHERE PRODUCT_ID="+productId);
        ResultSet rs=ps.executeQuery();
        if(rs.next()) return rs.getInt("product_quantity");
        return 0;
    }
    private static int orderNumber() throws SQLException, ClassNotFoundException {
        con=MakeConnection.getConncetion();
        PreparedStatement ps2=con.prepareStatement("SELECT orderId  FROM ( SELECT orderId FROM ProductOrder ORDER BY orderId DESC) WHERE ROWNUM = 1");
        ResultSet re=ps2.executeQuery();
        if(re.next())return  re.getInt("orderId")+1;
        return 0;
    }
    public static void invoice(int customerId) throws SQLException, ClassNotFoundException {
        con=MakeConnection.getConncetion();
       String query = " SELECT custumer.custumer_id AS CID," +
               "    custumer.customer_name AS CNAME," +
                "pro.product_id AS PID, pro.product_name AS PNAME, " +
                "pro.product_price AS PRICE, pro_Ord.QUANTITY AS PQ ," +
                " pro_Ord.order_date AS DATE1 "+
                " FROM ProductOrder pro_Ord " +
                " JOIN custumer custumer ON custumer.custumer_id = pro_Ord.customer_id " +
                "JOIN product pro ON pro.product_id = pro_Ord.product_id " +
                "WHERE custumer.custumer_id = ?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setInt(1,customerId);
        ResultSet res=ps.executeQuery();
        System.out.println();
        Double totalPrice=0.0;
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        System.out.println(String.format(
                "%-15s%-15s%-15s%-25s%-15s%-10s%-15s%-15s",
                "Customer ID", "Customer Name", "Product ID", "Product Name",
                "Product Price", "Quantity", "Total Price", "Order Date"
        ));
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");


        while (res.next()) {
            System.out.println(
                    String.format("%-15d", res.getInt("CID")) +
                            String.format("%-20s", res.getString("CNAME")) +
                            String.format("%-10d", res.getInt("PID")) +
                            String.format("%-25s", res.getString("PNAME")) +
                            String.format("%-15.3f", res.getDouble("PRICE")) +
                            String.format("%-10d", res.getInt("PQ")) +
                            String.format("%-15.3f", res.getInt("PQ") * res.getDouble("PRICE")) +
                            String.format("%-15s", res.getDate("DATE1"))
            );

            totalPrice += res.getInt("PQ") * res.getDouble("PRICE");
        }

        while(res.next()){
            System.out.println(String.format("%-10d",res.getInt("CID"))+String.format("%-15s",res.getString("CNAME"))+
                    String.format("%-10d",res.getInt("PID"))+String.format("%-20s",res.getString("PNAME"))+
                    String.format("%-15.3f",res.getDouble("PRICE"))
                    +String.format("%-10d",res.getInt("PQ")) +String.format("%-10.3f ",res.getInt("PQ")*res.getDouble("PRICE"))
                    +String.format("%-15s", res.getDate("DATE1")));
                totalPrice+=res.getInt("PQ")*res.getDouble("PRICE");
        }
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");
        double cgst=(totalPrice*0.09);
        double sgst=(totalPrice*0.09);
        System.out.println(String.format("CGST%100.3f ",cgst));
        System.out.println(String.format("SGST%100.3f ",sgst));
        System.out.println(String.format("Total bill is %91.3f ",totalPrice));
        System.err.println(String.format("%80s","Thank you visit again 🥰🥰🥰🥰🥰"));

    }
}
