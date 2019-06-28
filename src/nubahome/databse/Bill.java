package nubahome.databse;

import java.util.Date;

public class Bill {
    private int ID;
    private String date;
    private String buyerName;
    private double totalCost;
    private String paymentMethod;

    public Bill(int ID, String date, String buyerName, double totalCost, String paymentMethod) {
        this.ID = ID;
        this.date = date;
        this.buyerName = buyerName;
        this.totalCost = totalCost;
        this.paymentMethod = paymentMethod;
    }

    public int getID() {
        return ID;
    }

    public String getDate() {
        return date;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}
