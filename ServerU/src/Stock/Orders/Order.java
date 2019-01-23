package Stock.Orders;

import Stock.Goods.Product;
import Stock.Stock;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

public class Order extends Stock  implements Serializable {
    protected int id; //код заказа
    protected int product_id;
    protected int amount;
    protected String orderDate; // дата
    protected String costumer;  // заказчик
    protected String address;
    protected String phone;  // контакт заказчика
    protected String email;  // контакт заказчика

    public String getCustInfo()
    {
        String s = "Заказчик: " + this.costumer + " Дата доставки: " + this.orderDate + " Адрес доставки: " +  this.address + " Телефон: " + this.phone + " E-mail: " + this.email;
        return s;
    }
    @Override
    public String toString() {
        String s = " Код продукта: "+ this.product_id + " Количество: " + this.amount;
        return s;
    }

    public Order() {
        super();
    }

    public Order(int id, int product_id, int amount, String orderDate, String costumer, String address) {
        this.id = id;
        this.product_id = product_id;
        this.amount = amount;
        this.orderDate = orderDate;
        this.costumer = costumer;
        this.address = address;
    }

    @Override
    public void add() {

    }

    @Override
    public ResultSet show() throws SQLException {
        statment = Stock.connection.createStatement();
        result = statment.executeQuery("SELECT * FROM ORDERS;");
        return result;
    }

    @Override
    public int remove() {
        try {
            statment = Stock.connection.createStatement();
            System.out.println(this.id );
            statment.executeUpdate("DELETE FROM ORDERS WHERE ORDER_ID =" + this.id + ";");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int remove_prod() {
        try {
            statment = Stock.connection.createStatement();
            System.out.println(this.id );
            statment.executeUpdate("DELETE FROM ORDERS WHERE ORDER_ID =" + this.id +" AND PRODUCT_ID = "+ this.product_id+";");
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean redact() {
      //  String s = "UPDATE GOODS SET NAME = '" + super.name + "', PRICE = " + super.prise + " , BRAND = '"+ super.Brand +"', AMOUNT = "+ super.amount + " , SIZE = " + this.size + " WHERE PRODUCT_ID = " + super.id + ";";
      String s = "UPDATE ORDERS SET AMOUNT = " + this.amount + ", ADDRESS = '"+ this.address + "', ORDER_DATE = '" + this.orderDate+"' WHERE ORDER_ID = " + this.id + " AND PRODUCT_ID = "+ this.product_id +";";
       String s1 = "UPDATE CUSTOMERS SET PHONE = '" + this.phone + "', EMAIL = '" + this.email + "' WHERE CUSTOMERS_NAME = '" + this.costumer + "';";
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
        ArrayList<Order> orderlist = new ArrayList<Order>();
        String cust;
        Order ord;
        try {
            statment = Stock.connection.createStatement();
            result = statment.executeQuery("SELECT * FROM ORDERS");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        while (result.next())
        {
           // cust = result.getString(4);
            //r1 = statment.executeQuery("SELECT * FROM CUSTOMERS WHERE CUSTOMERS_NAME = '"+ cust + "';");
            ord = new Order(result.getInt(1),result.getInt(2),result.getInt(3),result.getString(6),result.getString(4),result.getString(5));
            orderlist.add(ord);
        }
        for (int i=0; i< orderlist.size(); i++)
        {
            cust = orderlist.get(i).costumer;
            statment = Stock.connection.createStatement();
            result = statment.executeQuery("SELECT * FROM CUSTOMERS WHERE CUSTOMERS_NAME = '"+ cust + "';");
            if (result.next())
            {   orderlist.get(i).phone = result.getString(2);
            orderlist.get(i).email = result.getString(3);}
        }
        return orderlist;
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

    public String getOrderDate() {
        return orderDate;
    }

    public String getCostumer() {
        return costumer;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) { this.id =id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setCostumer(String costumer) {
        this.costumer = costumer;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int add(ArrayList<Product> goods)
    {
        String s = "INSERT INTO CUSTOMERS (CUSTOMERS_NAME, PHONE, EMAIL) VALUES ('"+ this.costumer + "' , '" + this.phone + "' , '"+ this.email+ "');" ;
        int code = this.hashCode();
        try {
            statment = Stock.connection.createStatement();
            statment.executeUpdate(s);

        for (int i=0; i < goods.size(); i++ )
        {
            this.product_id = goods.get(i).getId();
            this.amount = goods.get(i).getAmount();
            System.out.println(this.product_id + " "+  this.amount);
            s = "INSERT INTO ORDERS  VALUES (" + code + " , "+ this.product_id + "," + this.amount + ",'" +  this.costumer + "','" + this.address+ "','"+this.orderDate +"' );";
           //(PRODUCT_ID , AMOUNT, CUSTOMERS_NAME, ADDRESS)
            statment.executeUpdate(s);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code;
    }

    public ArrayList<Order> GetOrderByID( int ID)
    { ArrayList<Order> orderlist = new ArrayList<Order>();
        String cust;
        Order ord;

        try {
            statment = Stock.connection.createStatement();
            result = statment.executeQuery("SELECT * FROM ORDERS WHERE ORDER_ID =" + ID + ";");

        while (result.next())
        {
            ord = new Order(result.getInt(1),result.getInt(2),result.getInt(3),result.getString(6),result.getString(4),result.getString(5));
            orderlist.add(ord);
        }
        for (int i=0; i< orderlist.size(); i++)
        {
            cust = orderlist.get(i).costumer;
            statment = Stock.connection.createStatement();
            result = statment.executeQuery("SELECT * FROM CUSTOMERS WHERE CUSTOMERS_NAME = '"+ cust + "';");
            if (result.next())
            {   orderlist.get(i).phone = result.getString(2);
                orderlist.get(i).email = result.getString(3);}
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderlist;
    }
  // TODO: если заказ выполнен, просто удалить по коду
        // TODO: если нет, увеличить количество соответствующих товаров и удалить заказы

    // TODO: метод получения общей цены заказа                                                +все тоже самое для поставок
    // TODO: метод получения среднуй цены заказа
    // TODO: посчитать общее количество продуктоов в заказе
    // TODO: посчитать среднее количество продуктоов в заказе
    public double ColculateOrderPrice()  // цена заказа
    {
        double OrderPrice = 0;
        int prid=0;
        String s1 = "SELECT * FROM ORDERS WHERE ORDER_ID = " + this.id + " ;";
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
    public int ColculateOrderAmount()
    {
        int OrderAmount =0;
        String s1 = "SELECT SUM(AMOUNT) AS ORDER_AMOUNT FROM ORDERS WHERE ORDER_ID = " + this.id + " ;";
        try {
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
        String s = "select distinct ORDER_ID from orders;";
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
