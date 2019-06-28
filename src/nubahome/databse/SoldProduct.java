package nubahome.databse;

public class SoldProduct {
    private String productName;
    private int soldQuantity;
    private double sellingPrice;

    public SoldProduct(String productName, int soldQuantity, double sellingPrice) {
        this.productName = productName;
        this.soldQuantity = soldQuantity;
        this.sellingPrice = sellingPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }


}
