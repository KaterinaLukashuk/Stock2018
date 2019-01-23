package Stock.Supplies;

import Stock.Goods.Product;
import Stock.Orders.Order;
import Stock.Stock;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Supplie extends Stock implements Serializable {
    protected int id;
    protected int product_id;
    protected int amount;
    protected String supplDate;
    protected String  supplier;
    protected String phone;
    protected String email;

    public String getSupplInfo()
    {
        String s = "Поставщик: " + this.supplier + " Дата поставки: " + this.supplDate  + " Телефон: " + this.phone + " E-mail: " + this.email;
        return s;
    }
    @Override
    public String toString() {
        String s = " Код продукта: "+ this.product_id + " Количество: " + this.amount;
        return s;
    }
    public Supplie(int id, int product_id, int amount,  String supplier, String supplDate) {
        this.id = id;
        this.product_id = product_id;
        this.amount = amount;
        this.supplDate = supplDate;
        this.supplier = supplier;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setSupplDate(String supplDate) {
        this.supplDate = supplDate;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getAmount() {
        return amount;
    }

    public String getSupplDate() {
        return supplDate;
    }

    public String getSupplier() {
        return supplier;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public void add() {

    }

    @Override
    public ResultSet show() throws SQLException {
        return null;
    }

    @Override
    public int remove() {
        try {
            statment = Stock.connection.createStatement();
            System.out.println(this.id );
            statment.executeUpdate("DELETE FROM SUPPLIES WHERE SUPPLIES_ID =" + this.id +";");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean redact() {

        String s = "UPDATE SUPPLIES SET AMOUNT = " + this.amount + " , SUPPLIER_DATE = '" + this.supplDate +"' WHERE SUPPLIES_ID = " + this.id + " AND PRODUCT_ID = "+ this.product_id +";";
        String s1 = "UPDATE SUPPLIERS SET PHONE = '" + this.phone + "', EMAIL = '" + this.email + "' WHERE SUPPLIERS_NAME = '" + this.supplier + "';";
        try {
            statment = Stock.connection.createStatement();
            statment.executeUpdate(s);
            statment.executeUpdate(s1);

        } catch (SQLException e) {
            return false;
            // e.printStackTrace();
        }
        return true;
    }

    @Override
    public ArrayList<Order> ListRes() throws SQLException {
        return null;
    }

    @Override
    public int remove_prod() {
        try {
            System.out.println(this.id );
            statment = Stock.connection.createStatement();
            statment.executeUpdate("DELETE FROM SUPPLIES WHERE SUPPLIES_ID =" + this.id +" AND PRODUCT_ID = "+ this.product_id+";");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Supplie() {
        super();
    }

    public int add(ArrayList<Product> goods)
    {
        String s = "INSERT INTO SUPPLIERS VALUES ('"+ this.supplier + "' , '" + this.phone + "' , '"+ this.email+ "');" ;
        int code = this.hashCode();
        try {
            statment = Stock.connection.createStatement();
            statment.executeUpdate(s);

            for (int i=0; i < goods.size(); i++ )
            {
                s = "SELECT * FROM GOODS WHERE NAME = '"+ goods.get(i).getName()+ "' AND BRAND = '" + goods.get(i).getBrand()  + "';";
                //this.product_id = goods.get(i).getId();
                result= statment.executeQuery(s);
                if (result.next())
                this.product_id = result.getInt(1);
                this.amount = goods.get(i).getAmount();
                System.out.println(this.product_id + " "+  this.amount);
                s = "INSERT INTO SUPPLIES  VALUES (" + code + " , "+ this.product_id + " , " + this.amount + " , '" +  this.supplier + "' , '"+this.supplDate +"' );";
                //(PRODUCT_ID , AMOUNT, CUSTOMERS_NAME, ADDRESS)
                statment.executeUpdate(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return code;
    }
    public ArrayList<Supplie> SuplRes()
    {
        ArrayList<Supplie> suppllist = new ArrayList<Supplie>();
        String splr;
        Supplie suppl;
        try {
            statment = Stock.connection.createStatement();
            result = statment.executeQuery("SELECT * FROM SUPPLIES");

        while (result.next())
        {
            // cust = result.getString(4);
            //r1 = statment.executeQuery("SELECT * FROM CUSTOMERS WHERE CUSTOMERS_NAME = '"+ cust + "';");
            suppl = new Supplie(result.getInt(1),result.getInt(2),result.getInt(3),result.getString(4),result.getString(5));
            suppllist.add(suppl);
        }
        for (int i=0; i< suppllist.size(); i++)
        {
            splr = suppllist.get(i).supplier;
            result = statment.executeQuery("SELECT * FROM SUPPLIERS WHERE SUPPLIERS_NAME = '"+ splr + "';");
            if (result.next())
            {   suppllist.get(i).phone = result.getString(2);
                suppllist.get(i).email = result.getString(3);}
        }} catch (SQLException e) {
            e.printStackTrace();
        }
        return suppllist;
    }
    public ArrayList<Supplie> GetSupplrByID(int ID)
    { ArrayList<Supplie> orderlist = new ArrayList<Supplie>();
        String cust;
        Supplie ord;
        try {
            result = statment.executeQuery("SELECT * FROM SUPPLIES WHERE SUPPLIES_ID =" + ID + ";");

            while (result.next())
            {
                ord = new Supplie(result.getInt(1),result.getInt(2),result.getInt(3),result.getString(4),result.getString(5));
                orderlist.add(ord);
            }
            for (int i=0; i< orderlist.size(); i++)
            {
                statment = Stock.connection.createStatement();
                cust = orderlist.get(i).supplier;
                result = statment.executeQuery("SELECT * FROM SUPPLIERS WHERE SUPPLIERS_NAME = '"+ cust + "';");
                if (result.next())
                {   orderlist.get(i).phone = result.getString(2);
                    orderlist.get(i).email = result.getString(3);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderlist;
    }
    public double ColculateSupplPrice()  // цена заказа
    {
        double OrderPrice = 0;
        int prid=0;
        String s1 = "SELECT * FROM SUPPLIES WHERE SUPPLIES_ID = " + this.id + " ;";
        String s2 = "SELECT * FROM GOODS WHERE PRODUCT_ID = " + this.product_id + " ;";
        ArrayList<Integer> array = new ArrayList<Integer>();
        ArrayList<Integer> array2 = new ArrayList<Integer>();
        int i = 0;
        try {
            statment = Stock.connection.createStatement();
            result = statment.executeQuery(s1);
            while (result.next())
            {
                array.add(result.getInt(2));
                array2.add(result.getInt(3));
            }
            for (i=0; i < array.size(); i++)
            {

                this.product_id = array.get(i);
                this.amount = array2.get(i);
                s2 = "SELECT * FROM GOODS WHERE PRODUCT_ID = " + this.product_id + " ;";
                result = statment.executeQuery(s2);
                if (result.next())
                    OrderPrice  += (this.amount*result.getDouble(4));
                System.out.println(OrderPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return OrderPrice;
    }
    public int ColculateSupplAmount()
    {
        int OrderAmount =0;
        String s1 = "SELECT SUM(AMOUNT) AS ORDER_AMOUNT FROM SUPPLIES WHERE SUPPLIES_ID = " + this.id + " ;";
        try {
            statment = Stock.connection.createStatement();
            result = statment.executeQuery(s1);
            if (result.next())
                OrderAmount = result.getInt("ORDER_AMOUNT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return OrderAmount;
    }
    public ArrayList<Integer> GetAllID()
    {
        ArrayList<Integer> IDs = new ArrayList<Integer>();
        String s = "select distinct SUPPLIES_ID from SUPPLIES;";
        try {
            statment = Stock.connection.createStatement();
            result = statment.executeQuery(s);
            while (result.next())
                IDs.add(result.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return IDs;
    }
}
