package nubahome.databse;

import java.util.ArrayList;
import java.util.Date;

public class Store {

    private String name;
    private StoreDatabase database;

    private int currentUserType;

    public Store(String name) {
        this.name = name;
    }

    public void setupDatabase(String driverURL, String databaseURL) {
        database = new StoreDatabase(driverURL, databaseURL);
    }

    public void connectToDatabase() {
        database.connect();
    }

    public void initializeUserTypes() {
        database.initializeUserTypes();
    }

    public void disconnectFromDatabase() {
        database.disconnect();
    }

    public Integer userType(String type) {
        return database.userType(type);
    }

    public Integer login(String userName, String userPassword) {

        currentUserType = database.login(userName,userPassword);
        return currentUserType;
    }

    public boolean addCategory(String categoryName) {
        boolean done = false;
        done = database.addCategory(categoryName);
        return done;
    }

    public ArrayList<String> getCategories() {
        return database.getCategories();
    }

    public ArrayList<String> getSuppliers() {
        return database.getSuppliers();
    }

    public boolean addProduct(String productName, int productCategory, int availableQuantity, Double productPrice) {
        boolean done = false;
        done = database.addProduct(productName,productCategory, availableQuantity, productPrice);
        return  done;
    }


    public ArrayList<String> getUserTypes() {
        return database.getUserTypes();
    }

    public boolean addUser(String userName, String userPassword, int userType) {
        boolean done = false;
        done = database.addUser(userName,userPassword,userType);
        return done;
    }

    public boolean addSupplier(String supplierName) {
        boolean done = false;
        done = database.addSupplier(supplierName);
        return done;
    }

    public boolean addBill(String buyerName, ArrayList<Integer> soldProductsIDs, ArrayList<Integer> soldQuantities, ArrayList<Double> sellingPrices, double totalCost) {
        boolean done = false;
        done = database.addBill(buyerName, soldProductsIDs, soldQuantities, sellingPrices, totalCost);
        return done;
    }

    public boolean addInstalment(String buyerName, ArrayList<Integer> soldProductsIDs, ArrayList<Integer> soldQuantities, ArrayList<Double> sellingPrices, double totalCost, String guarantorName, double paidMoney,  double instalmentAmount, Date startDate, Date endDate){
        boolean done = false;
        done = database.addInstalment(buyerName, soldProductsIDs, soldQuantities, sellingPrices, totalCost, guarantorName, paidMoney, instalmentAmount, startDate, endDate);
        return done;
    }

    public boolean addSupply(String supplierName, ArrayList<Integer> boughtProductsIDs, ArrayList<Integer> boughtQuantities, ArrayList<Double> buyingPrices, double totalCost) {
        boolean done = false;
        done = database.addSupply(supplierName, boughtProductsIDs, boughtQuantities, buyingPrices, totalCost);
        return done;
    }

    public ArrayList<Product> getProductsInCategory(int categoryID) {
        return database.getProductsInCategory(categoryID);
    }
    public ArrayList<User> getAllUsers() {
        return database.getAllUsers();
    }

    public void deleteUser(String userName) {
        database.deleteUser(userName);
    }

    public void updateUserName(User user, String newName) {
        database.updateUserName(user, newName);
    }

    public void updateUserPassword(User user, String newPassword) {
        database.updateUserPassword(user, newPassword);
    }

    public void updateUserType(User user, int newType) {
        database.updateUserType(user, newType);
    }
}
