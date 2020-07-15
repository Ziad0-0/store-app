package nubahome.databse;

public class Supplier {
    int supplierID;
    String supplierName;
    String supplierTelephone;
    String supplierAddress;

    public Supplier(String supplierName, String supplierTelephone, String supplierAddress) {
        this.supplierName = supplierName;
        this.supplierTelephone = supplierTelephone;
        this.supplierAddress = supplierAddress;
    }

    public Supplier(int supplierID, String supplierName, String supplierTelephone, String supplierAddress) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.supplierTelephone = supplierTelephone;
        this.supplierAddress = supplierAddress;
    }

    public int getSupplierID() {
        return supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public String getSupplierTelephone() {
        return supplierTelephone;
    }

    public String getSupplierAddress() {
        return supplierAddress;
    }
}
