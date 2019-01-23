package Stock.Goods;

import Stock.Orders.Order;
import Stock.Stock;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class Product extends Stock implements Serializable {
    protected int id;
    protected String name;     // наиминование
    protected double prise;// цена за единицу продукта
    protected String Brand; // производитель
    protected int amount;
    public Product(int id, String name, double prise, String brand,int amount) {
        this.id = id;
        this.name = name;
        this.prise = prise;
        this.Brand = brand;
        this.amount = amount;
    }

    public Product() {
    }
    public int removeByID() {
        try {
            System.out.println(this.id );
            statment.executeUpdate("DELETE FROM GOODS WHERE PRODUCT_ID =" + this.id + ";");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int remove_prod() {
        return 0;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrise(double prise) {
        this.prise = prise;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getName() {
        return name;
    }

    public double getPrise() {
        return prise;
    }

    public String getBrand() {
        return Brand;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Override
    public ArrayList<Order> ListRes() throws SQLException {
        return null;
    }
    public abstract ArrayList<Product> FiltrByPrice(double min, double max);
    public abstract ArrayList<Product> FiltrByAmount(double min, double max);
    public abstract int ProdAmount();
    //    @Override
//    public ResultSet show() {
//        try {
//            result = statment.executeQuery("SELECT * FROM GOODS;");
//        }
//        catch (SQLException e){ System.out.println("SQLException");}
//        return result;
//    }
}
