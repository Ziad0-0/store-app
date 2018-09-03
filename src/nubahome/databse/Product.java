package nubahome.databse;

public class Product {

    private int id;
    private String productName;
    private int categoryID;
    private int availableQuantity;
    private double buyingPrice;

    Product(int id, String productName, int categoryID, int availableQuantity, double buyingPrice) {
        this.id = id;
        this.productName = productName;
        this.categoryID = categoryID;
        this.availableQuantity = availableQuantity;
        this.buyingPrice = buyingPrice;
    }

    public int getID() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }
}
