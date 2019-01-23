package Stock;

import Stock.Orders.Order;

import java.sql.*;
import java.util.ArrayList;

public abstract class Stock {
    private static String password = "fdflf";
    private static String username = "root";
    private static String conURL  = "jdbc:mysql://localhost:3306/stock2?characterEncoding=UTF-8";
  //  private static Connection connection = null;
    protected static Connection connection = null;
    protected Statement statment = null;
    protected ResultSet result = null;

    public Stock() {
        try{
            connection = DriverManager.getConnection(conURL, username, password);
        //    System.out.println("Database connection established.");
            statment = connection.createStatement();
        }catch (SQLException e){}

    }

    public abstract void add();
    public abstract ResultSet show() throws SQLException;
    public abstract int remove();
    public abstract boolean redact();
    public abstract ArrayList<Order> ListRes() throws SQLException;
    public abstract int remove_prod();
}
