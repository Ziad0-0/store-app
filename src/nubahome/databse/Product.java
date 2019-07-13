package nubahome.databse;

public class Product {
    int productID;
    String productName;
    ProductCategory category;
    int availableQuantity;
    double supplyPrice;
    String lastSupplyDate;
    double cashSellingPrice;
    double instalmentSellingPrice;

    public Product(int productID, String productName, ProductCategory category, int availableQuantity, double supplyPrice, String lastSupplyDate, double cashSellingPrice, double instalmentSellingPrice) {
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.availableQuantity = availableQuantity;
        this.supplyPrice = supplyPrice;
        this.lastSupplyDate = lastSupplyDate;
        this.cashSellingPrice = cashSellingPrice;
        this.instalmentSellingPrice = instalmentSellingPrice;
    }

    public Product(String productName, ProductCategory category, int availableQuantity, double supplyPrice, String lastSupplyDate, double cashSellingPrice, double instalmentSellingPrice) {
        this.productName = productName;
        this.category = category;
        this.availableQuantity = availableQuantity;
        this.supplyPrice = supplyPrice;
        this.lastSupplyDate = lastSupplyDate;
        this.cashSellingPrice = cashSellingPrice;
        this.instalmentSellingPrice = instalmentSellingPrice;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public double getSupplyPrice() {
        return supplyPrice;
    }

    public String getLastSupplyDate() {
        return lastSupplyDate;
    }

    public double getCashSellingPrice() {
        return cashSellingPrice;
    }

    public double getInstalmentSellingPrice() {
        return instalmentSellingPrice;
    }
}
