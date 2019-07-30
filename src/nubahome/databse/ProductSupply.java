package nubahome.databse;

public class ProductSupply {
    int supplyID;
    String supplyDate;
    Supplier supplier;
    Product boughtProduct;
    int boughtQuantity;
    double buyingPrice;

    public ProductSupply(int supplyID, String supplyDate, Supplier supplier, Product boughtProduct, int boughtQuantity, double buyingPrice) {
        this.supplyID = supplyID;
        this.supplyDate = supplyDate;
        this.supplier = supplier;
        this.boughtProduct = boughtProduct;
        this.boughtQuantity = boughtQuantity;
        this.buyingPrice = buyingPrice;
    }

    public int getSupplyID() {
        return supplyID;
    }

    public String getSupplyDate() {
        return supplyDate;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Product getBoughtProduct() {
        return boughtProduct;
    }

    public int getBoughtQuantity() {
        return boughtQuantity;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }
}
