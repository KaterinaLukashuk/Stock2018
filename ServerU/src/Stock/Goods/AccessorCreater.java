package Stock.Goods;

public class AccessorCreater implements Creator{

    @Override
    public Product CreateProduct() {
        Accessor ob =new Accessor();
        return ob;
    }
}
