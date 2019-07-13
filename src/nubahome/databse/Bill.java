package nubahome.databse;

import java.util.ArrayList;


public class Bill {
    int billID;
    String billDate;
    String buyerName;
    double productsTotalCost;
    double billTotalCost;
    String paymentMethod;
    ArrayList<SoldProduct> soldProducts;
    InstalmentsPayment instalmentsPayment;


    public Bill(String billDate, String buyerName, double productsTotalCost, double billTotalCost, String paymentMethod, ArrayList<SoldProduct> soldProducts) {
        this.billDate = billDate;
        this.buyerName = buyerName;
        this.productsTotalCost = productsTotalCost;
        this.billTotalCost = billTotalCost;
        this.paymentMethod = paymentMethod;
        this.soldProducts = soldProducts;
        this.instalmentsPayment = null;
    }

    public Bill(String billDate, String buyerName, double productsTotalCost, double billTotalCost, String paymentMethod, ArrayList<SoldProduct> soldProducts, InstalmentsPayment instalmentsPayment) {
        this.billDate = billDate;
        this.buyerName = buyerName;
        this.productsTotalCost = productsTotalCost;
        this.billTotalCost = billTotalCost;
        this.paymentMethod = paymentMethod;
        this.soldProducts = soldProducts;
        this.instalmentsPayment = instalmentsPayment;
    }

    public Bill(int billID, String billDate, String buyerName, double productsTotalCost, double billTotalCost, String paymentMethod, ArrayList<SoldProduct> soldProducts, InstalmentsPayment instalmentsPayment) {
        this.billID = billID;
        this.billDate = billDate;
        this.buyerName = buyerName;
        this.productsTotalCost = productsTotalCost;
        this.billTotalCost = billTotalCost;
        this.paymentMethod = paymentMethod;
        this.soldProducts = soldProducts;
        this.instalmentsPayment = instalmentsPayment;
    }

    public Bill(int billID, String billDate, String buyerName, double productsTotalCost, double billTotalCost, String paymentMethod, ArrayList<SoldProduct> soldProducts) {
        this.billID = billID;
        this.billDate = billDate;
        this.buyerName = buyerName;
        this.productsTotalCost = productsTotalCost;
        this.billTotalCost = billTotalCost;
        this.paymentMethod = paymentMethod;
        this.soldProducts = soldProducts;
        this.instalmentsPayment = null;
    }

    public int getBillID() {
        return billID;
    }

    public String getBillDate() {
        return billDate;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public double getProductsTotalCost() {
        return productsTotalCost;
    }

    public double getBillTotalCost() {
        return billTotalCost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public ArrayList<SoldProduct> getSoldProducts() {
        return soldProducts;
    }

    public InstalmentsPayment getInstalmentsPayment() {
        return instalmentsPayment;
    }

}
