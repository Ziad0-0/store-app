package nubahome.databse;

public class Supply {
    private int ID;
    private String date;
    private String supplierName;
    private double transportationFees;
    private double productsTotalCost;
    private double supplyTotalCost;

    public Supply(int ID, String date, String supplierName, double transportationFees, double productsTotalCost, double supplyTotalCost) {
        this.ID = ID;
        this.date = date;
        this.supplierName = supplierName;
        this.transportationFees = transportationFees;
        this.productsTotalCost = productsTotalCost;
        this.supplyTotalCost = supplyTotalCost;
    }

    public int getID() {
        return ID;
    }

    public String getDate() {
        return date;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public double getTransportationFees() {
        return transportationFees;
    }

    public double getProductsTotalCost() {
        return productsTotalCost;
    }

    public double getSupplyTotalCost() {
        return supplyTotalCost;
    }
}
