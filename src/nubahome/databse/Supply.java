package nubahome.databse;

import java.util.ArrayList;

public class Supply {
    int supplyID;
    String supplyDate;
    Supplier supplier;
    double transportationFees;
    double productsTotalCost;
    double supplyTotalCost;
    ArrayList<BoughtProduct> boughtProducts;

    public Supply(String supplyDate, Supplier supplier, double transportationFees, double productsTotalCost, double supplyTotalCost, ArrayList<BoughtProduct> boughtProducts) {
        this.supplyDate = supplyDate;
        this.supplier = supplier;
        this.transportationFees = transportationFees;
        this.productsTotalCost = productsTotalCost;
        this.supplyTotalCost = supplyTotalCost;
        this.boughtProducts = boughtProducts;
    }

    public Supply(int supplyID, String supplyDate, Supplier supplier, double transportationFees, double productsTotalCost, double supplyTotalCost, ArrayList<BoughtProduct> boughtProducts) {
        this.supplyID = supplyID;
        this.supplyDate = supplyDate;
        this.supplier = supplier;
        this.transportationFees = transportationFees;
        this.productsTotalCost = productsTotalCost;
        this.supplyTotalCost = supplyTotalCost;
        this.boughtProducts = boughtProducts;
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

    public double getTransportationFees() {
        return transportationFees;
    }

    public double getProductsTotalCost() {
        return productsTotalCost;
    }

    public double getSupplyTotalCost() {
        return supplyTotalCost;
    }

    public ArrayList<BoughtProduct> getBoughtProducts() {
        return boughtProducts;
    }
}
