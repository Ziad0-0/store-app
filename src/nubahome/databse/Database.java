package nubahome.databse;

import java.sql.*;


public class Database {

    private String databaseURL;
    private String driverURL;
    private Connection databaseConnection;

    Database(String driverURL, String databaseURL) {

        this.driverURL = driverURL;
        this.databaseURL = databaseURL;
    }

    private void loadDriver() {
        try {

            Class.forName(driverURL).newInstance();
        } catch (Exception ex) {
            // handle the error
            System.out.println(ex.getMessage());
        }
    }

    public void connect() {

        loadDriver();

        try {
            databaseConnection = DriverManager.getConnection(databaseURL);
            System.out.println("Connection to SQLite has been established.");
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }



    }

    public void disconnect() {

        try{
            if(databaseConnection!=null)
                databaseConnection.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    ResultSet executeQuery(String query) {

        ResultSet results = null;
        Statement statement;
        try {

            statement = databaseConnection.createStatement();
            results = statement.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            return results;
        }


    }

    void executeInsertion(String insertion) {

        Statement statement;
        try {
            statement = databaseConnection.createStatement();
            statement.execute(insertion);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void executeUpdate(String update) {

        Statement statement;
        try {
            statement = databaseConnection.createStatement();
            statement.execute(update);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void executeDelete(String deletion) {

        Statement statement;
        try {
            statement = databaseConnection.createStatement();
            statement.execute(deletion);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
