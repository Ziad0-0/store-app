package nubahome.databse;

public class InstalmentPayment {
    int paymentID;
    int instalmentID;
    double paidMoney;
    String paymentDate;

    public InstalmentPayment(int instalmentID, double paidMoney, String paymentDate) {
        this.instalmentID = instalmentID;
        this.paidMoney = paidMoney;
        this.paymentDate = paymentDate;
    }

    public InstalmentPayment(int paymentID, int instalmentID, double paidMoney, String paymentDate) {
        this.paymentID = paymentID;
        this.instalmentID = instalmentID;
        this.paidMoney = paidMoney;
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public int getInstalmentID() {
        return instalmentID;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public String getPaymentDate() {
        return paymentDate;
    }
}
