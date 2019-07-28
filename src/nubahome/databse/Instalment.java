package nubahome.databse;

import java.util.ArrayList;

public class Instalment {
    int instalmentID;
    double paidMoney;
    double remainingMoney;
    String instalmentDueDate;
    String instalmentPaymentDate;
    String instalmentState;
    ArrayList<InstalmentPayment> instalmentPayments;

    public Instalment(double paidMoney, double remainingMoney, String instalmentDueDate, String instalmentPaymentDate, String instalmentState, ArrayList<InstalmentPayment> instalmentPayment) {
        this.paidMoney = paidMoney;
        this.remainingMoney = remainingMoney;
        this.instalmentDueDate = instalmentDueDate;
        this.instalmentPaymentDate = instalmentPaymentDate;
        this.instalmentState = instalmentState;
        this.instalmentPayments = instalmentPayments;
    }

    public Instalment(int instalmentID, double paidMoney, double remainingMoney, String instalmentDueDate, String instalmentPaymentDate, String instalmentState, ArrayList<InstalmentPayment> instalmentPayments) {
        this.instalmentID = instalmentID;
        this.paidMoney = paidMoney;
        this.remainingMoney = remainingMoney;
        this.instalmentDueDate = instalmentDueDate;
        this.instalmentPaymentDate = instalmentPaymentDate;
        this.instalmentState = instalmentState;
        this.instalmentPayments = instalmentPayments;
    }

    public Instalment(int instalmentID, double paidMoney, double remainingMoney, String instalmentDueDate, String instalmentPaymentDate, String instalmentState) {
        this.instalmentID = instalmentID;
        this.paidMoney = paidMoney;
        this.remainingMoney = remainingMoney;
        this.instalmentDueDate = instalmentDueDate;
        this.instalmentPaymentDate = instalmentPaymentDate;
        this.instalmentState = instalmentState;
    }

    public Instalment(double paidMoney, double remainingMoney, String instalmentDueDate, String instalmentPaymentDate, String instalmentState) {
        this.paidMoney = paidMoney;
        this.remainingMoney = remainingMoney;
        this.instalmentDueDate = instalmentDueDate;
        this.instalmentPaymentDate = instalmentPaymentDate;
        this.instalmentState = instalmentState;
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
