package nubahome.databse;

import java.util.ArrayList;

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

    public boolean addBill(String buyerName, ArrayList<Integer> boughtProductsIDs, ArrayList<Integer> boughtQuantities, ArrayList<Double> productsPrices) {
        boolean done = false;
        done = database.addBill(buyerName,boughtProductsIDs,boughtQuantities,productsPrices);
        return done;
    }
}
