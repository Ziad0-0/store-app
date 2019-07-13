package nubahome.databse;

public class BoughtProduct extends Product{
    int boughtQuantity;
    double buyingPrice;

    public BoughtProduct(Product product, int boughtQuantity, double buyingPrice) {
        super(product.productID, product.productName, product.category, product.availableQuantity, product.supplyPrice, product.lastSupplyDate, product.cashSellingPrice, product.instalmentSellingPrice);

        this.boughtQuantity = boughtQuantity;
        this.buyingPrice = buyingPrice;
    }
    

    public int getBoughtQuantity() {
        return boughtQuantity;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }
}
