package nubahome.databse;

public class Instalment {
    private int billID;
    private String buyerName;
    private double billTotalCost;
    private double paidMoney;
    private double instalmentAmount;
    private String startDate;
    private String endDate;

    public Instalment(int billID, String buyerName, double billTotalCost, double paidMoney, double instalmentAmount, String startDate, String endDate) {
        this.billID = billID;
        this.buyerName = buyerName;
        this.billTotalCost = billTotalCost;
        this.paidMoney = paidMoney;
        this.instalmentAmount = instalmentAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getBillID() {
        return billID;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public double getBillTotalCost() {
        return billTotalCost;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public double getInstalmentAmount() {
        return instalmentAmount;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
