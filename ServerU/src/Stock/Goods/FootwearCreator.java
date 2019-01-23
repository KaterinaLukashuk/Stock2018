package Stock.Goods;

public class FootwearCreator implements Creator{
    @Override
    public Product CreateProduct() {
        Footwear ob = new Footwear();
        return ob;
    }
}
