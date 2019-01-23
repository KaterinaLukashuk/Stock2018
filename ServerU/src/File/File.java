package File;

import Stock.Goods.Product;
import Stock.Orders.Order;
import Stock.Supplies.Supplie;

import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class File {
    FileOutputStream fos;
    ObjectOutputStream oos;
    FileInputStream fis;
    ObjectInputStream ios;
    Date date;
    public int readPort() throws IOException, ClassNotFoundException {
        int port = 0;
        fis = new FileInputStream("C:\\Katerin\\Универ\\ПСП\\КП\\КП2\\ServerU\\settings.out");
        ios = new ObjectInputStream(fis);
        port = (int) ios.readObject();
        return port;
    }
    public void writePort(int port) throws IOException {
     //   fos = new FileOutputStream("C:\\Katerin\\Универ\\ПСП\\КП\\КП2\\ServerU\\settings.out");
        fos = new FileOutputStream("C:\\Katerin\\Универ\\ПСП\\КП\\КП2\\ServerU\\settings.out");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(port);
        oos.flush();
        oos.close();
    }
    public void writeOrders(ArrayList<Order> orders) throws IOException {
        date = new Date();
        fos = new FileOutputStream("src/Data/Orders" + date.getDate()+"."+date.getMonth()+"." +date.getYear()+ ".doc");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(orders);
        oos.flush();
        oos.close();
    }
    public void writeSuppls(ArrayList<Supplie> supplies) throws IOException {
        date = new Date();
        fos = new FileOutputStream("src/Data/Supplies" + date.getDate()+"."+date.getMonth()+"." +date.getYear()+ ".doc");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(supplies);
        oos.flush();
        oos.close();
    }
    public void writeGoods(ArrayList<Product> goods) throws IOException {
        fos = new FileOutputStream("src/Data/Goods.doc");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(goods);
        oos.flush();
        oos.close();
    }
}
