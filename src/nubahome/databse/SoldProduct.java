package nubahome.databse;

public class SoldProduct extends Product{
    int soldQuantity;
    double sellingPrice;

    public SoldProduct(Product product, int soldQuantity, double sellingPrice) {
        super(product.productID, product.productName, product.category, product.availableQuantity, product.supplyPrice, product.lastSupplyDate, product.cashSellingPrice, product.instalmentSellingPrice);

        this.soldQuantity = soldQuantity;
        this.sellingPrice = sellingPrice;
    }


    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }


}
