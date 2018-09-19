package nubahome.databse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


class StoreDatabase extends Database {

    private HashMap<String, Integer> userTypes;

    StoreDatabase(String driverURL, String databaseURL) {
        super(driverURL, databaseURL);

    }
    void initializeUserTypes() {
        userTypes = new HashMap<>();
        ArrayList<String> types = getUserTypes();
        for(int i = 0, s = types.size(); i<s; i++)
            userTypes.put(types.get(i),i);
    }
    Integer userType(String type) {
        int user_type  =  userTypes.get(type);
        return user_type;
    }
    Integer login(String userName, String userPassword) {

        Integer user_type  = userTypes.get("NOT_REGISTERED");
        ResultSet resultSet = executeQuery("select * from users where user_name='"+userName+"';");

        try {
            if(resultSet.next())
            {
                String databasePassword = resultSet.getString("user_password");
                if(databasePassword.equals(userPassword))
                    user_type = resultSet.getInt("user_type");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_type;
    }

    boolean addCategory(String categoryName) {
        boolean done = false;
        done = executeInsertion("insert into categories (category_name) values ('"+categoryName+"');");

        return done;
    }

    boolean addProduct(String productName, int productCategory, int availableQuanity, Double productPrice) {
        boolean done = false;
        done = executeInsertion("insert into products (product_name, category_id, available_quantity, buying_price) " +
                                "values ('"+productName+"', " + productCategory + ", " + availableQuanity + ", " + productPrice +
                                ");");
        return done;
    }

    boolean addUser(String userName, String userPassword, int userType) {
        boolean done = false;
        done = executeInsertion("insert into users (user_name, user_password, user_type) " +
                                "values ('"+ userName + "', '"+userPassword + "', "+ userType +");");
        return done;
    }

    ArrayList<String> getCategories() {

        ArrayList<String> catogeries = new ArrayList<>();
        ResultSet resultSet = executeQuery("select category_name from categories;");

        try {
            while(resultSet.next())
                catogeries.add(resultSet.getString("category_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catogeries;

    }

    ArrayList<String> getUserTypes() {

        ArrayList<String> types = new ArrayList<>();
        ResultSet resultSet = executeQuery("select type from users_types;");

        try {
            while(resultSet.next())
               types.add(resultSet.getString("type"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }

    ArrayList<String> getSuppliers() {
        ArrayList<String> suppliers = new ArrayList<>();
        ResultSet resultSet = executeQuery("select supplier_name from  suppliers;");

        try {
            while(resultSet.next())
                suppliers.add(resultSet.getString("supplier"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    boolean addSupplier(String supplierName) {
        boolean done = false;

        done = executeInsertion("insert into suppliers (supplier_name) values ('"+supplierName+"');");

        return done;
    }

    boolean addBill(String buyerName, ArrayList<Integer> soldProductsIDs, ArrayList<Integer> soldQuantities, ArrayList<Double> sellingPrices, double totalCost) {

        boolean done = false;
        Timestamp billDate = new Timestamp(new java.util.Date().getTime());
        done = executeInsertion("insert into bills (bill_date,buyer_name, bill_total_cost) " +
                "values ('"+ billDate + "', '"+ buyerName +"', "+ totalCost + ");");

        ResultSet resultSet = executeQuery("select bill_id from bills order by bill_id desc limit 1;");
        int lastBillID = 0;
        try {
            if(resultSet.next())
                lastBillID = resultSet.getInt("bill_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //ToDo: get all the unit prices refer to database to more info

        for(int i =0,s = soldQuantities.size();i<s;i++)
            done = executeInsertion("insert into bills_details (bill_id, product_id, sold_quantity, selling_price) " +
                                    "values ("+ lastBillID +", " + soldProductsIDs.get(i) + ", " +  soldQuantities.get(i) + ", " + sellingPrices.get(i) +");");
        return done;
    }

    boolean addInstalment(String buyerName, ArrayList<Integer> soldProductsIDs, ArrayList<Integer> soldQuantities, ArrayList<Double> sellingPrices, double totalCost, String guarantorName, double paidMoney, double instalmentAmount, Date startDate, Date endDate){
        boolean done = false;

        done = addBill(buyerName, soldProductsIDs, soldQuantities, sellingPrices, totalCost);

        ResultSet resultSet = executeQuery("select bill_id from bills order by bill_id desc limit 1;");
        int lastBillID = 0;
        try {
            if(resultSet.next())
                lastBillID = resultSet.getInt("bill_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double remainingMoney = totalCost - paidMoney;

        done = executeInsertion("insert into instalments (bill_id, guarantor_name, paid_money, remaining_money, instalment_amount, start_date, end_date)" +
                                "values ("+ lastBillID+ ", '"+ guarantorName+ "', "+ paidMoney + ", " +  remainingMoney + ", " + instalmentAmount + ", " +  startDate + ", " +  endDate +");");
        return done;
    }

    boolean addSupply(String supplierName, ArrayList<Integer> boughtProductsIDs, ArrayList<Integer> boughtQuantities, ArrayList<Double> buyingPrices, double totalCost) {
        boolean done = false;
        Timestamp supplyDate = new Timestamp(new java.util.Date().getTime());
        done = executeInsertion("insert into supplies (supply_date, supplier_name, supply_total_cost) " +
                "values ('"+ supplyDate + "', '"+ supplierName +"', "+ totalCost + ");");

        ResultSet resultSet = executeQuery("select supply_id from supplies order by supply_id desc limit 1;");
        int lastSupplyID = 0;
        try {
            if(resultSet.next())
                lastSupplyID = resultSet.getInt("supply_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(int i =0,s = boughtQuantities.size();i<s;i++)
            done = executeInsertion("insert into supplies_details (supply_id, product_id, bought_quantity, buying_price) " +
                    "values ("+ lastSupplyID +", " + boughtProductsIDs.get(i) + ", " +  boughtQuantities.get(i) + ", " + buyingPrices.get(i) +");");
        return done;
    }

    ArrayList<Product> getProductsInCategory(int categoryID) {
        ArrayList<Product> products = new ArrayList<>();

        ResultSet resultSet = executeQuery("select * from products where category_id="+categoryID+";");

        try {
            while(resultSet.next())
            {
                int productID = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                int availableQuantity = resultSet.getInt("available_quantity");
                double buyingPrice = resultSet.getDouble("buying_price");

                products.add(new Product(productID,productName,categoryID,availableQuantity,buyingPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;

    }

    int getSoldQuantity(int productID, Date startDate, Date endDate) {
        int soldQuantity = 0;

        ResultSet resultSet = executeQuery("select sum(sold_quantity) from bills_details where product_id = "+productID +
                                           "AND bill_id in ( select bill_id from bills where bill_date between '" + startDate + "' AND '"+ endDate + "');");

        try {
            if(resultSet.next())
                soldQuantity += resultSet.getInt("sum(sold_quantity)");
            return soldQuantity;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soldQuantity;
    }

   double getProfit(int productID, Date startDate, Date endDate) {

        double totalSellingPrice = 0;
        ResultSet resultSet = executeQuery("select sum(selling_price) from bills_details where product_id = "+productID +
                                           "AND bill_id in ( select bill_id from bills where bill_date between '" + startDate + "' AND '"+ endDate + "');");
        try {
            if(resultSet.next())
                totalSellingPrice += resultSet.getDouble("sum(selling_price)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double totalUnitsPrices = 0;
        resultSet = executeQuery("select sum(unit_price) from bills_details where product_id = "+productID +
                "AND bill_id in ( select bill_id from bills where bill_date between '" + startDate + "' AND '"+ endDate + "');");
        try {
            if(resultSet.next())
                totalUnitsPrices += resultSet.getDouble("sum(unit_price)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double profit =  totalSellingPrice - totalUnitsPrices;

        return profit;
    }

    ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        ResultSet resultSet = executeQuery("select * from users;");

        try {
            while(resultSet.next())
            {
                String userName = resultSet.getString("user_name");
                String userPassword = resultSet.getString("user_password");
                int userType = resultSet.getInt("user_type");
                users.add(new User(userName, userPassword, userType));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;

    }

    void updateUser(String userName, String userPassword, int userType) {
        executeUpdate("update users set user_name='" +userName +"', user_password='"+ userPassword + "', user_type="+ userType  + "where user_name='"+userName+"';");


    }

    void deleteUser(String userName) {
        executeDelete("delete from users where user_name= '"+userName+"';");
    }

   void updateUserName(User user, String newName) {
        String oldName = user.getName();
        executeUpdate("update users set user_name='"+ newName + "' where user_name='"+ oldName + "';");
   }

   void updateUserPassword(User user, String newPassword) {
        String userName = user.getName();
       executeUpdate("update users set user_password='"+ newPassword + "' where user_name='"+ userName + "';");
   }

   void updateUserType(User user, int newType) {
        String userName = user.getName();
        executeUpdate("update users set user_type="+newType+';');
    }


}
