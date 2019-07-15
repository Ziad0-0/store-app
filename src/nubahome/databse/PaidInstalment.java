package nubahome.databse;

public class PaidInstalment {
    private int paymentID;
    private String instalmentDueDate;
    private String paymentDate;
    private Double paidAmount;

    public PaidInstalment(String instalmentDueDate, String paymentDate, Double paidAmount) {
        this.instalmentDueDate = instalmentDueDate;
        this.paymentDate = paymentDate;
        this.paidAmount = paidAmount;
    }

    public PaidInstalment(int paymentID, String instalmentDueDate, String paymentDate, Double paidAmount) {
        this.paymentID = paymentID;
        this.instalmentDueDate = instalmentDueDate;
        this.paymentDate = paymentDate;
        this.paidAmount = paidAmount;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public String getInstalmentDueDate() {
        return instalmentDueDate;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }
}
