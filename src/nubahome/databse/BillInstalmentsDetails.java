package nubahome.databse;

public class BillInstalmentsDetails {
    Customer guarantor;
    double initialPayment;
    double remainingMoney;
    double instalmentAmount;
    String firstInstalmentDate;
    String lastInstalmentDate;
    int remainingInstalmentsNumber;

    public BillInstalmentsDetails(Customer guarantor, double initialPayment, double remainingMoney, double instalmentAmount, String firstInstalmentDate, String lastInstalmentDate, int remainingInstalmentsNumber) {
        this.guarantor = guarantor;
        this.initialPayment = initialPayment;
        this.remainingMoney = remainingMoney;
        this.instalmentAmount = instalmentAmount;
        this.firstInstalmentDate = firstInstalmentDate;
        this.lastInstalmentDate = lastInstalmentDate;
        this.remainingInstalmentsNumber = remainingInstalmentsNumber;
    }

    public Customer getGuarantor() {
        return guarantor;
    }

    public double getInitialPayment() {
        return initialPayment;
    }

    public double getRemainingMoney() {
        return remainingMoney;
    }

    public double getInstalmentAmount() {
        return instalmentAmount;
    }

    public String getFirstInstalmentDate() {
        return firstInstalmentDate;
    }

    public String getLastInstalmentDate() {
        return lastInstalmentDate;
    }

    public int getRemainingInstalmentsNumber() {
        return remainingInstalmentsNumber;
    }
}
