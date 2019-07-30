package nubahome.databse;

import java.util.ArrayList;

public class Instalment {
    int instalmentID;
    int billID;
    double paidMoney;
    double remainingMoney;
    String instalmentDueDate;
    String instalmentPaymentDate;
    String instalmentState;
    ArrayList<InstalmentPayment> instalmentPayments;

    public Instalment(int instalmentID, int billID, double paidMoney, double remainingMoney, String instalmentDueDate, String instalmentPaymentDate, String instalmentState, ArrayList<InstalmentPayment> instalmentPayments) {
        this.instalmentID = instalmentID;
        this.billID = billID;
        this.paidMoney = paidMoney;
        this.remainingMoney = remainingMoney;
        this.instalmentDueDate = instalmentDueDate;
        this.instalmentPaymentDate = instalmentPaymentDate;
        this.instalmentState = instalmentState;
        this.instalmentPayments = instalmentPayments;
    }


    public Instalment(int instalmentID, int billID, double paidMoney, double remainingMoney, String instalmentDueDate, String instalmentPaymentDate, String instalmentState) {
        this.instalmentID = instalmentID;
        this.billID = billID;
        this.paidMoney = paidMoney;
        this.remainingMoney = remainingMoney;
        this.instalmentDueDate = instalmentDueDate;
        this.instalmentPaymentDate = instalmentPaymentDate;
        this.instalmentState = instalmentState;
        this.instalmentPayments = new ArrayList<>();
    }

    public Instalment(int billID, double paidMoney, double remainingMoney, String instalmentDueDate, String instalmentPaymentDate, String instalmentState) {
        this.billID = billID;
        this.paidMoney = paidMoney;
        this.remainingMoney = remainingMoney;
        this.instalmentDueDate = instalmentDueDate;
        this.instalmentPaymentDate = instalmentPaymentDate;
        this.instalmentState = instalmentState;
        this.instalmentPayments = new ArrayList<>();
    }

    public int getBillID() {
        return billID;
    }

    public int getInstalmentID() {
        return instalmentID;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public double getRemainingMoney() {
        return remainingMoney;
    }

    public String getInstalmentDueDate() {
        return instalmentDueDate;
    }

    public String getInstalmentPaymentDate() {
        return instalmentPaymentDate;
    }

    public String getInstalmentState() {
        return instalmentState;
    }

    public ArrayList<InstalmentPayment> getInstalmentPayments() {
        return instalmentPayments;
    }
}
