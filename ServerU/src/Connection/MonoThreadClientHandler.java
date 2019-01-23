package Connection;

import java.io.*;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

import Stock.Goods.*;
import Stock.Orders.Order;
import Stock.Supplies.Supplie;
import File.File;

public class MonoThreadClientHandler  implements Runnable{
    File datafile;
    // для БД
    ResultSet result;
    private static String password = "fdflf";
    private static String username = "root";
    private static String conURL  = "jdbc:mysql://localhost:3306/stock2?useUnicode=true&characterEncoding=UTF-8";
    private static Connection connection = null;
    Statement statment = null;
    // мои объекты
    Clothes clothes = null;
    Footwear footwear = null;
    Accessor accessor = null;
    Creator create = null;
    Order order = null;
    Supplie supplie = null;
    ArrayList <Product> products;
    private Socket clientDialog;
    public MonoThreadClientHandler(Socket client) {
        this.clientDialog = client;
    }

    @Override
    public void run() {
        try{
            connection = DriverManager.getConnection(conURL, username, password);
            System.out.println("Database connection established.");
        }catch (SQLException e)
        {

        }
        try {

            ObjectInputStream in = new ObjectInputStream(clientDialog.getInputStream());
            System.out.println("Objin done");

            ObjectOutputStream out = new ObjectOutputStream(clientDialog.getOutputStream());
            System.out.println("Objout done");

            while (!clientDialog.isClosed()) {
                System.out.println("Server reading from channel");
                String entry = null;
                try {
                    entry = (String) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                // и выводит в консоль
                System.out.println("READ from clientDialog message - " + entry);
                if (entry.equalsIgnoreCase("quit")) {

                    System.out.println("Client initialize connections suicide ...");
                    out.writeObject("Server reply - " + entry + " - OK");
                    Thread.sleep(1000);
                    break;
                }
                try{
                    statment = connection.createStatement();
                }
                catch (SQLException e) {}
                switch (entry)
                {
                    case "avtoriz":

                        out.writeObject("ok");
                        //получить логин и пароль
                        try {

                            String log = null;
                            try {
                                log = (String) in.readObject();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            out.writeObject("ok");
                            String pass = null;
                            try {
                                pass = (String) in.readObject();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                         //   System.out.println(log + " "+ pass);
                            ResultSet result = statment.executeQuery("SELECT * FROM USERS;");
                            while (result.next()) {
                               // System.out.println("________________" + " " + result.getString(1) + " " + result.getString(2));
                                if(log.equals(result.getString(1))&&pass.equals(result.getString(2)))
                                {
                                    if (result.getBoolean(3))
                                        out.writeObject("admin");
                                    else
                                        out.writeObject("user");
                                    break;
                                }
                            }
                            if (result.isAfterLast())out.writeObject("no");
                        } catch (SQLException e){ System.out.println("SQLException");out.writeObject("no");}
                        break;
                    case "addUser":
                        boolean s3 = (boolean) in.readObject();
                        String s1 = (String) in.readObject();
                        String s2 = (String) in.readObject();
                        try{
                        statment.executeUpdate("INSERT INTO USERS VALUES ('" + s1 + "' , '"+ s2 + "' , " + s3 + ");");
                        out.writeObject("ok");
                        }catch (SQLException e) {
                            out.writeObject("no");}
                        break;
                    case "ShowAllProd":
                        create = new ClothesCreator();
                        Product obj = create.CreateProduct();
                        result = obj.show();// clothes.show();
                        Clothes cl = null;
                        ArrayList<Product> clths = new ArrayList<Product>();
                        try {
                            while(result.next())
                            {
                                cl = new Clothes(result.getInt(1) , result.getString(3),result.getDouble(4),result.getString(5), result.getInt(6), result.getInt(7));
                                clths.add(cl);
                                cl.print();
                            }
                            out.writeObject(clths);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case"FiltrGoodsByPr":
                        int min = (int) in.readObject();
                        int max = (int) in.readObject();
                        clothes = new Clothes();
                        out.writeObject( clothes.FiltrByPrice(min,max));
                        break;
                    case"FiltrGoodsByAm":
                         min = (int) in.readObject();
                         max = (int) in.readObject();
                        clothes = new Clothes();
                        out.writeObject( clothes.FiltrByAmount(min,max));
                        break;
                    case"FiltrGoodsByPr2":
                        min = (int) in.readObject();
                        max = (int) in.readObject();
                        footwear = new Footwear();
                        out.writeObject( footwear.FiltrByPrice(min,max));
                        break;
                    case"FiltrGoodsByAm2":
                        min = (int) in.readObject();
                        max = (int) in.readObject();
                        footwear = new Footwear();
                        out.writeObject( footwear.FiltrByAmount(min,max));
                        break;
                    case"FiltrGoodsByPr3":
                        min = (int) in.readObject();
                        max = (int) in.readObject();
                        accessor = new Accessor();
                        out.writeObject( accessor.FiltrByPrice(min,max));
                        break;
                    case"FiltrGoodsByAm3":
                        min = (int) in.readObject();
                        max = (int) in.readObject();
                        accessor = new Accessor();
                        out.writeObject( accessor.FiltrByAmount(min,max));
                        break;
                    case "ShowAllProd2":
                        create = new FootwearCreator();
                        Product obj2 = create.CreateProduct();
                        result = obj2.show();// clothes.show();
                        Footwear fw = null;
                        ArrayList<Product> ftwr = new ArrayList<Product>();
                        try {
                            while(result.next())
                            {
                                fw = new Footwear(result.getInt(1) ,result.getString(3),result.getDouble(4),result.getString(5), result.getInt(6), result.getInt(7), result.getInt(8));
                                ftwr.add(fw);
                               // fw.print();
                            }
                            out.writeObject(ftwr);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ShowAllProd3":
                        create = new AccessorCreater();
                        Product obj3 = create.CreateProduct();
                        result = obj3.show();// clothes.show();
                        Accessor ac = null;
                        ArrayList<Product> accsr = new ArrayList<Product>();
                        try {
                            while(result.next())
                            {
                                ac = new Accessor(result.getInt(1) ,result.getString(3),result.getDouble(4),result.getString(5), result.getInt(6));
                                accsr.add(ac);
                                // fw.print();
                            }
                            out.writeObject(accsr);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "AddProd":
                        try {
                            clothes = (Clothes) in.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        clothes.add();
                        out.writeObject("ok");
                        break;
                    case "AddProd2":
                        try {
                            footwear = (Footwear) in.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        footwear.add();
                        out.writeObject("ok");
                        break;
                    case "AddProd3":
                        try {
                            accessor = (Accessor) in.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        accessor.add();
                        out.writeObject("ok");
                        break;
                    case "DelProd":
                        try {
                            clothes = (Clothes) in.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        int x = clothes.remove();
                        if (x == - 1)
                        {out.writeObject("ok");}
                        else out.writeObject("В наличии имеется только " + x + " единиц данного товара");
                        break;
                    case "DelProd2":
                        try {
                            footwear = (Footwear) in.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        x = footwear.remove();
                        if (x == - 1)
                        {out.writeObject("ok");}
                        else out.writeObject("В наличии имеется только " + x + " единиц данного товара");
                        break;
                    case "DelProd3":
                        try {
                            accessor = (Accessor) in.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        x = accessor.remove();
                        if (x == - 1)
                        {out.writeObject("ok");}
                        else out.writeObject("В наличии имеется только " + x + " единиц данного товара");
                        break;
                    case "AddOrder":
                        // todo: получить данные обьект заказ
                        try {
                            order = (Order) in.readObject();
                            // todo:  получить массив товаров
                            products = (ArrayList<Product>) in.readObject();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        // todo: добавить
                        int code = order.add(products);
                        // todo: отправить код товара
                        out.writeObject(code);
                        break;
                    case "ChangeProd1":
                        try {
                            clothes = (Clothes) in.readObject();
                            boolean flag = clothes.redact();
                            if (flag == true) out.writeObject("ok");
                            else out.writeObject("no");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ChangeProd2":
                        try {
                            footwear = (Footwear) in.readObject();
                            boolean flag = footwear.redact();
                            if (flag == true) out.writeObject("ok");
                            else out.writeObject("no");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ChangeProd3":
                        try {
                            accessor = (Accessor) in.readObject();
                            boolean flag = accessor.redact();
                            if (flag == true) out.writeObject("ok");
                            else out.writeObject("no");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ShowAllOrders":
                        order = new Order();
                        ArrayList<Order> ordr = new ArrayList<Order>();
                        ordr = order.ListRes();
                        out.writeObject(ordr);
                        break;
                    case "DelOrder":
                        try {
                            order = (Order) in.readObject();
                            if (order.remove() == 1)
                                out.writeObject("ok");
                            else out.writeObject("no");
                        } catch (ClassNotFoundException e) {
                            out.writeObject("no");
                            e.printStackTrace();
                        }
                        break;
                    case "DelFromOrder":
                        try {
                            order = (Order) in.readObject();
                            if (order.remove_prod() == 1)
                                out.writeObject("ok");
                            else out.writeObject("no");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ChangeOrder":
                        try {
                            order = (Order) in.readObject();
                            boolean flag = order.redact();
                            if (flag == true) out.writeObject("ok");
                            else out.writeObject("no");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ShowOrderByID":
                        int orderid = (int) in.readObject();
                        order = new Order();
                        out.writeObject(order.GetOrderByID(orderid));
                        order.setId(orderid);
                        out.writeObject(order.ColculateOrderPrice());
                        order.setId(orderid);
                        out.writeObject(order.ColculateOrderAmount());
                        break;
                    case "ShowSupplByID":
                        orderid = (int) in.readObject();
                        supplie = new Supplie();
                        out.writeObject(supplie.GetSupplrByID(orderid));
                        supplie.setId(orderid);
                        out.writeObject(supplie.ColculateSupplPrice());
                        supplie.setId(orderid);
                        out.writeObject(supplie.ColculateSupplAmount());
                        break;
                        case "AddSupplie":
                            // todo: получить данные обьект заказ
                            try {
                                supplie = (Supplie) in.readObject();
                                // todo:  получить массив товаров
                                products = (ArrayList<Product>) in.readObject();
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            // todo: добавить
                            code = supplie.add(products);
                            // todo: отправить код товара
                            out.writeObject(code);
                            break;
                        case "ShowAllSuppl":
                                supplie = new Supplie();
                                out.writeObject(supplie.SuplRes());
                                break;
                        case "ChangeSuppl":
                            try {
                                supplie = (Supplie) in.readObject();
                                boolean flag = supplie.redact();
                                if (flag == true) out.writeObject("ok");
                                else out.writeObject("no");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        break;
                    case "DelSuppl":
                        try {
                            supplie = (Supplie) in.readObject();
                            if (supplie.remove() == 1)
                                out.writeObject("ok");
                            else out.writeObject("no");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "DelFromSuppl":
                        try {
                            supplie = (Supplie) in.readObject();
                            if (supplie.remove_prod() == 1)
                                out.writeObject("ok");
                            else out.writeObject("no");
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "DelProdID1":
                        clothes = (Clothes) in.readObject();
                        if (clothes.removeByID() ==1)
                            out.writeObject("ok");
                        else out.writeObject("no");
                        break;
                    case "DelProdID2":
                        footwear = (Footwear) in.readObject();
                        if (footwear.removeByID() ==1)
                            out.writeObject("ok");
                        else out.writeObject("no");
                        break;
                    case "DelProdID3":
                        accessor = (Accessor) in.readObject();
                        if (accessor.removeByID() ==1)
                            out.writeObject("ok");
                        else out.writeObject("no");
                        break;
                    case "grafic":
                        clothes = new Clothes();
                        footwear = new Footwear();
                        accessor = new Accessor();
                        out.writeObject(clothes.ProdAmount());
                        out.writeObject(footwear.ProdAmount());
                        out.writeObject(accessor.ProdAmount());
                        break;
                    case "SaveData":
                        order = new Order();
                        supplie = new Supplie();
                        datafile = new File();
                        datafile.writeOrders(order.ListRes());
                        datafile.writeSuppls(supplie.SuplRes());
                        break;

                    case "AllOrdersID":
                        order = new Order();
                        out.writeObject(order.GetAllID());
                        break;
                    case "AllSupplID":
                        supplie = new Supplie();
                        out.writeObject(supplie.GetAllID());
                        break;
                    default: break;
                }
                out.flush();
            }

            System.out.println("Client disconnected");
            System.out.println("Closing connections & channels.");
            in.close();
            out.close();
           clientDialog.close();

            System.out.println("Closing connections & channels - DONE.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
