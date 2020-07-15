package nubahome.databse;

public class InstalmentSummary {
    String customerName;
    String dueDate;
    double remainingMoney;

    public InstalmentSummary(String customerName, String dueDate, double remainingMoney) {
        this.customerName = customerName;
        this.dueDate = dueDate;
        this.remainingMoney = remainingMoney;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDueDate() {
        return dueDate;
    }

    public double getRemainingMoney() {
        return remainingMoney;
    }
}
