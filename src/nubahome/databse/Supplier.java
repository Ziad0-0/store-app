package nubahome.databse;

public class Supplier {
    int supplierID;
    String supplierName;

    public Supplier(int supplierID, String supplierName) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
    }

    public Supplier(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public int getSupplierID() {
        return supplierID;
    }
}
