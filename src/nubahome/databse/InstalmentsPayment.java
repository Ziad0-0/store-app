package nubahome.databse;

public class InstalmentsPayment {
    String guarantorName;
    double initialPayment;
    double remainingMoney;
    double instalmentAmount;
    String firstInstalmentDate;
    String lastInstalmentDate;

    public InstalmentsPayment(String guarantorName, double initialPayment, double remainingMoney, double instalmentAmount, String firstInstalmentDate, String lastInstalmentDate) {
        this.guarantorName = guarantorName;
        this.initialPayment = initialPayment;
        this.remainingMoney = remainingMoney;
        this.instalmentAmount = instalmentAmount;
        this.firstInstalmentDate = firstInstalmentDate;
        this.lastInstalmentDate = lastInstalmentDate;
    }

    public String getGuarantorName() {
        return guarantorName;
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
}
