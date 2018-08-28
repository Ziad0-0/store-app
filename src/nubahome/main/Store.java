package nubahome.main;

import nubahome.databse.StoreDatabase;

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
}
