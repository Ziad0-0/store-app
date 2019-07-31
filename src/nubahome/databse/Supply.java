package nubahome.databse;

import java.util.ArrayList;

public class Supply {
    int supplyID;
    String supplyDate;
    Supplier supplier;
    double aditionalFees;
    double productsTotalCost;
    double supplyTotalCost;
    ArrayList<BoughtProduct> boughtProducts;

    public Supply(String supplyDate, Supplier supplier, double aditionalFees, double productsTotalCost, double supplyTotalCost, ArrayList<BoughtProduct> boughtProducts) {
        this.supplyDate = supplyDate;
        this.supplier = supplier;
        this.aditionalFees = aditionalFees;
        this.productsTotalCost = productsTotalCost;
        this.supplyTotalCost = supplyTotalCost;
        this.boughtProducts = boughtProducts;
    }

    public Supply(int supplyID, String supplyDate, Supplier supplier, double aditionalFees, double productsTotalCost, double supplyTotalCost, ArrayList<BoughtProduct> boughtProducts) {
        this.supplyID = supplyID;
        this.supplyDate = supplyDate;
        this.supplier = supplier;
        this.aditionalFees = aditionalFees;
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

    public double getAditionalFees() {
        return aditionalFees;
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
