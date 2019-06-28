package nubahome.databse;

public class Product {

    private int id;
    private String productName;
    private int categoryID;
    private int availableQuantity;
    private double buyingPrice;
    private String lastBuyingDate;
    private double sellingPrice;
    private double installmentPrice;

    Product(int id, String productName, int categoryID, int availableQuantity, double buyingPrice, String lastBuyingDate, double sellingPrice, double installmentPrice) {
        this.id = id;
        this.productName = productName;
        this.categoryID = categoryID;
        this.availableQuantity = availableQuantity;
        this.buyingPrice = buyingPrice;
        this.lastBuyingDate = lastBuyingDate;
        this.sellingPrice = sellingPrice;
        this.installmentPrice = installmentPrice;
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

    public String getLastBuyingDate() {
        return lastBuyingDate;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getInstallmentPrice() {
        return installmentPrice;
    }
}
