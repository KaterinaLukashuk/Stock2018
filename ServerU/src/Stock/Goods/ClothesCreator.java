package Stock.Goods;

public class ClothesCreator implements Creator{

    @Override
    public Product CreateProduct() {
        Clothes ob = new Clothes();
        return ob;
    }
}
