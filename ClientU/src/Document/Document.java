package Document;


import Stock.Orders.Order;
import Stock.Supplies.Supplie;
import sample.Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Document {
   FileWriter writer;
   Date date;
   public void write(ArrayList<Order> orders , double pr)
   {
       try {
           date = new Date();
           writer = new FileWriter("Order"+orders.get(0).getId() + "." + date.getDate()+"."+date.getMonth()+"." +date.getYear()+ ".doc", false);
           writer.write(orders.get(0).getCustInfo()+"\n\t");
           for (int i= 0; i < orders.size(); i++)
           {
               writer.write(orders.get(i).toString()+"\n\t");
           }
          writer.write("Общая сумма: "+ pr + " д.е.\n\t");
           writer.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
       // writer.write(+"\n\t");
   }
    public void writeSuppl(ArrayList<Supplie> orders , double pr)
    {
        try {
            date = new Date();
            writer = new FileWriter("Supplie"+orders.get(0).getId()+ "." + date.getDate()+"."+date.getMonth()+"." +date.getYear()+ ".doc", false);
            writer.write(orders.get(0).getSupplInfo()+"\n\t");
            for (int i= 0; i < orders.size(); i++)
            {
                writer.write(orders.get(i).toString()+"\n\t");
            }
            writer.write("Общая сумма: "+ pr + " д.е.\n\t");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // writer.write(+"\n\t");
    }
}
