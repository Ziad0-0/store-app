package nubahome.databse;

public class Transaction {
    String transactionDate;
    String transactionSourceName;
    double transactionCost;
    String transactionType;

    public Transaction(String transactionDate, String transactionSourceName, double transactionCost, String transactionType) {
        this.transactionDate = transactionDate;
        this.transactionSourceName = transactionSourceName;
        this.transactionCost = transactionCost;
        this.transactionType = transactionType;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionSourceName() {
        return transactionSourceName;
    }

    public double getTransactionCost() {
        return transactionCost;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
