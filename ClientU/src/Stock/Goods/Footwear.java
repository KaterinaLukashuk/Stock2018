package Stock.Goods;
import Stock.Stock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Footwear extends Product // обувь
{
    protected int heel; // каблук
    protected int size; // размер

    public Footwear(int id, String name, double prise, String brand, int amount, int size, int heel) {
        super(id, name, prise, brand, amount);
        this.heel = heel;
        this.size = size;
    }

    public Footwear() {
        super();
    }

    @Override
    public ArrayList<Product> FiltrByPrice(double min, double max) {
        Footwear obj;
        ArrayList<Product> array = new ArrayList<Product>();
        String s = "SELECT * FROM GOODS WHERE TYPE_NAME= 'FOOTWEAR' AND PRICE BETWEEN "+ min + " AND " + max + ";";
        try {statment = Stock.connection.createStatement();
            result = statment.executeQuery(s);
            while (result.next())
            {
                obj = new Footwear();
                obj.setId(result.getInt(1));
                obj.setName(result.getString(3));
                obj.setPrise(result.getDouble(4));
                obj.setBrand(result.getString(5));
                obj.setAmount(result.getInt(6));
                obj.setSize(result.getInt(7));
                obj.setHeel(result.getInt(8));
                array.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }

    @Override
    public ArrayList<Product> FiltrByAmount(double min, double max) {
        return null;
    }

    public void setHeel(int heel) {
        this.heel = heel;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getHeel() {
        return heel;
    }

    public int getSize() {
        return size;
    }
    @Override
    public void add() {
        try {
            // TODO: если товаар уже есть в таблице, увеличить количество
            String str = "INSERT INTO GOODS ( TYPE_NAME,NAME,PRICE,BRAND, AMOUNT,SIZE, HEEL)VALUES ('FOOTWEAR', " + " '"  + super.name +"' "+" , " +super.prise+ " , "+ " '"+ super.Brand+ "', "+ super.amount+" , "+ size+" , "+ heel+" ); ";
            statment = Stock.connection.createStatement();
            statment.executeUpdate(str);
        }
        catch (SQLException e){ System.out.println("SQLException!!!");}
    }

    @Override
    public ResultSet show() {
        try {statment = Stock.connection.createStatement();
            result = statment.executeQuery("SELECT * FROM GOODS where TYPE_NAME = 'FOOTWEAR';");
        }
        catch (SQLException e){ System.out.println("SQLException");}
        return result;
    }

    @Override
    public int remove() {
        String str = "DELETE FROM GOODS WHERE TYPE_NAME= 'FOOTWEAR' AND NAME = '"+ super.name + "'  AND BRAND = '"+ super.Brand +"'AND SIZE = '"+ this.size +"';";
        String s =  "SELECT * FROM GOODS WHERE TYPE_NAME= 'FOOTWEAR' AND NAME = '"+ super.name + "'  AND BRAND = '"+ super.Brand +"'AND SIZE = '"+ this.size +"';";
      //  int x = 0;
        //UPDATE GOODS SET AMOUNT = 1000 WHERE TYPE_NAME = 'CLOTHES';
       try {statment = Stock.connection.createStatement();
            result = statment.executeQuery(s);

            // если удаляемое количество = имеющимуся в наличии количеству
            if (result.next()) {
                if (super.amount == result.getInt(6)) {
                    statment.executeUpdate(str);
                    //System.out.println("1____________" + result.getInt(6));
                    return -1;
                }
                // если удаляемое количество < имеющимуся в наличии количеству
                if (super.amount < result.getInt(6)) {
                    //TODO: уменьшить количество в таблице
                    int x = result.getInt(6) - super.amount;
                    String s1 = "UPDATE GOODS SET AMOUNT = " + x + " WHERE TYPE_NAME= 'FOOTWEAR' AND NAME = '"+ super.name + "'  AND BRAND = '"+ super.Brand +"'AND SIZE = '"+ this.size +"';";

                    statment.executeUpdate(s1);
                  //  System.out.println("1____________" + result.getInt(6));
                    return -1;
                }
                // если удаляемое количество > имеющимуся в наличии количеству
                else if (super.amount > result.getInt(6)) {
                    //TODO: сообщить о ошибке
             //       System.out.println("1____________" + result.getInt(6));
                    return result.getInt(6);
                }
                }

            } catch(SQLException e){
                e.printStackTrace();
            }

        return 0;
    }

    @Override
    public boolean redact() {
        String s = "UPDATE GOODS SET NAME = '" + super.name + "', PRICE = " + super.prise + " , BRAND = '"+ super.Brand +"', AMOUNT = "+ super.amount + " , SIZE = " + this.size +", HEEL = " + this.heel +" WHERE PRODUCT_ID = " + super.id + ";";
        try {
            statment = Stock.connection.createStatement();
            statment.executeUpdate(s);

        } catch (SQLException e) {
            return false;
            // e.printStackTrace();
        }
        return true;
    }
    @Override
    public int ProdAmount() {
        int a = 0;
        String s = "SELECT SUM(AMOUNT) AS PR_AMOUNT FROM GOODS WHERE TYPE_NAME = 'FOOTWEAR'";
        try {
            statment = Stock.connection.createStatement();
            result = statment.executeQuery(s);
            if (result.next())
                a = result.getInt("PR_AMOUNT");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
}
