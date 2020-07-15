package nubahome.databse;

public class Customer {
    int customerID;
    String customerName;
    String customerTelephone;
    String customerAddress;

    public Customer(String customerName, String customerTelephone, String customerAddress) {
        this.customerName = customerName;
        this.customerTelephone = customerTelephone;
        this.customerAddress = customerAddress;
    }

    public Customer(int customerID, String customerName, String customerTelephone, String customerAddress) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerTelephone = customerTelephone;
        this.customerAddress = customerAddress;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerTelephone() {
        return customerTelephone;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }
}
