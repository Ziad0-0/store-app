package nubahome.databse;

public class BillInstalmentsDetails {
    String guarantorName;
    double initialPayment;
    double remainingMoney;
    double instalmentAmount;
    String upcomingInstalmentDate;
    int remainingInstalmentsNumber;

    public BillInstalmentsDetails(String guarantorName, double initialPayment, double remainingMoney, double instalmentAmount, String upcomingInstalmentDate, int remainingInstalmentsNumber) {
        this.guarantorName = guarantorName;
        this.initialPayment = initialPayment;
        this.remainingMoney = remainingMoney;
        this.instalmentAmount = instalmentAmount;
        this.upcomingInstalmentDate = upcomingInstalmentDate;
        this.remainingInstalmentsNumber = remainingInstalmentsNumber;
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

    public String getUpcomingInstalmentDate() {
        return upcomingInstalmentDate;
    }

    public int getRemainingInstalmentsNumber() {
        return remainingInstalmentsNumber;
    }
}
