package nubahome.databse;

public class BoughtProduct {
    private String productName;
    private int boughtQuantity;
    private double buyingPrice;

    public BoughtProduct(String productName, int boughtQuantity, double buyingPrice) {
        this.productName = productName;
        this.boughtQuantity = boughtQuantity;
        this.buyingPrice = buyingPrice;
    }

    public String getProductName() {
        return productName;
    }

    public int getBoughtQuantity() {
        return boughtQuantity;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }


}
