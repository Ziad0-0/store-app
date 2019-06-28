package nubahome.databse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


class StoreDatabase extends Database {

    private HashMap<String, Integer> userTypes;

    StoreDatabase(String driverURL, String databaseURL) {
        super(driverURL, databaseURL);

    }

    void initializeUserTypes() {
        userTypes = new HashMap<>();
        ResultSet resultSet = executeQuery("select * from users_types;");
        String type;
        Integer type_id;
        try {
            while (resultSet.next()) {
                type = resultSet.getString("type");
                type_id = resultSet.getInt("type_id");
                userTypes.put(type, type_id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    Integer userType(String type) {
        int user_type = userTypes.get(type);
        return user_type;
    }

    Integer login(String userName, String userPassword) {

        Integer user_type = userTypes.get("NOT_REGISTERED");
        ResultSet resultSet = executeQuery("select * from users where user_name='" + userName + "';");

        try {
            if (resultSet.next()) {
                String databasePassword = resultSet.getString("user_password");
                if (databasePassword.equals(userPassword))
                    user_type = resultSet.getInt("user_type");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user_type;
    }

    boolean addCategory(String categoryName) {
        boolean done = false;
        done = executeInsertion("insert into categories (category_name) values ('" + categoryName + "');");

        return done;
    }

    boolean addSupplier(String supplierName) {
        boolean done = false;
        done = executeInsertion("insert into suppliers (supplier_name) values ('" + supplierName + "');");
        return done;
    }

    boolean addProduct(String productName, int productCategory, int availableQuantity, Double productPrice) {
        boolean done = false;
        done = executeInsertion("insert into products (product_name, category_id, available_quantity, buying_price) " +
                "values ('" + productName + "', " + productCategory + ", " + availableQuantity + ", " + productPrice +
                ");");
        return done;
    }

    boolean addUser(String userName, String userPassword, int userType) {
        boolean done = false;
        done = executeInsertion("insert into users (user_name, user_password, user_type) " +
                "values ('" + userName + "', '" + userPassword + "', " + userType + ");");
        return done;
    }

    ArrayList<String> getCategories() {

        ArrayList<String> catogeries = new ArrayList<>();
        ResultSet resultSet = executeQuery("select category_name from categories;");

        try {
            while (resultSet.next())
                catogeries.add(resultSet.getString("category_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return catogeries;

    }


    ArrayList<String> getSuppliers() {
        ArrayList<String> suppliers = new ArrayList<>();
        ResultSet resultSet = executeQuery("select supplier_name from  suppliers;");

        try {
            while (resultSet.next())
                suppliers.add(resultSet.getString("supplier_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    boolean addBill(String billDate, String buyerName, ArrayList<Integer> soldProductsIDs, ArrayList<Integer> soldQuantities, ArrayList<Double> sellingPrices, double totalCost, String paymentMethod) {

        boolean done = false;

        done = executeInsertion("insert into bills (bill_date,buyer_name, bill_total_cost, payment_method) " +
                "values ('" + billDate + "', '" + buyerName + "', " + totalCost + ", '" + paymentMethod + "');");

        ResultSet resultSet = executeQuery("select bill_id from bills order by bill_id desc limit 1;");
        int lastBillID = 0;
        try {
            if (resultSet.next())
                lastBillID = resultSet.getInt("bill_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (int i = 0, s = soldQuantities.size(); i < s; i++)
            done = executeInsertion("insert into bills_details (bill_id, product_id, sold_quantity, selling_price) " +
                    "values (" + lastBillID + ", " + soldProductsIDs.get(i) + ", " + soldQuantities.get(i) + ", " + sellingPrices.get(i) + ");");
        return done;
    }


    boolean addInstalment(String billDate, String buyerName, ArrayList<Integer> soldProductsIDs, ArrayList<Integer> soldQuantities, ArrayList<Double> sellingPrices, double totalCost, String guarantorName, double paidMoney, double instalmentAmount, String startDate, String endDate) {
        boolean done = false;

        done = addBill(billDate, buyerName, soldProductsIDs, soldQuantities, sellingPrices, totalCost, "تقسيط");

        ResultSet resultSet = executeQuery("select bill_id from bills order by bill_id desc limit 1;");
        int lastBillID = 0;
        try {
            if (resultSet.next())
                lastBillID = resultSet.getInt("bill_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double remainingMoney = totalCost - paidMoney;

        done = executeInsertion("insert into instalments (bill_id, guarantor_name, paid_money, remaining_money, instalment_amount, start_date, end_date)" +
                "values (" + lastBillID + ", '" + guarantorName + "', " + paidMoney + ", " + remainingMoney + ", " + instalmentAmount + ", " + startDate + ", " + endDate + ");");
        return done;
    }

    ArrayList<Product> getProductsInCategory(int categoryID) {
        ArrayList<Product> products = new ArrayList<>();

        ResultSet resultSet = executeQuery("select * from products where category_id=" + categoryID + ";");

        try {
            while (resultSet.next()) {
                int productID = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                int availableQuantity = resultSet.getInt("available_quantity");
                double buyingPrice = resultSet.getDouble("buying_price");
                String lastBuyingDate = resultSet.getString("last_buying_date");
                double sellingPrice = resultSet.getDouble("selling_price");
                double installmentPrice = resultSet.getDouble("installment_price");
                products.add(new Product(productID, productName, categoryID, availableQuantity, buyingPrice, lastBuyingDate, sellingPrice, installmentPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;

    }

    int getSoldQuantity(int productID, String startDate, String endDate) {
        int soldQuantity = 0;

        ResultSet resultSet = executeQuery("select sum(sold_quantity) from bills_details where product_id = " + productID +
                "AND bill_id in ( select bill_id from bills where bill_date between '" + startDate + "' AND '" + endDate + "');");

        try {
            if (resultSet.next())
                soldQuantity += resultSet.getInt("sum(sold_quantity)");
            return soldQuantity;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soldQuantity;
    }

    double getProfit(int productID, String startDate, String endDate) {

        double totalSellingPrice = 0;
        ResultSet resultSet = executeQuery("select sum(selling_price) from bills_details where product_id = " + productID +
                "AND bill_id in ( select bill_id from bills where bill_date between '" + startDate + "' AND '" + endDate + "');");
        try {
            if (resultSet.next())
                totalSellingPrice += resultSet.getDouble("sum(selling_price)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double totalUnitsPrices = 0;
        resultSet = executeQuery("select sum(unit_price) from bills_details where product_id = " + productID +
                "AND bill_id in ( select bill_id from bills where bill_date between '" + startDate + "' AND '" + endDate + "');");
        try {
            if (resultSet.next())
                totalUnitsPrices += resultSet.getDouble("sum(unit_price)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        double profit = totalSellingPrice - totalUnitsPrices;

        return profit;
    }

    ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();

        ResultSet resultSet = executeQuery("select * from users;");

        try {
            while (resultSet.next()) {
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
        executeUpdate("update users set user_name='" + userName + "', user_password='" + userPassword + "', user_type=" + userType + "where user_name='" + userName + "';");


    }

    void deleteUser(String userName) {
        executeDelete("delete from users where user_name= '" + userName + "';");
    }

    void updateUserName(User user, String newName) {
        String oldName = user.getName();
        executeUpdate("update users set user_name='" + newName + "' where user_name='" + oldName + "';");
    }

    void updateUserPassword(User user, String newPassword) {
        String userName = user.getName();
        executeUpdate("update users set user_password='" + newPassword + "' where user_name='" + userName + "';");
    }

    void updateUserType(User user, int newType) {
        String userName = user.getName();
        executeUpdate("update users set user_type=" + newType + ';');
    }

    ArrayList<Bill> getAllBills() {
        ArrayList<Bill> bills = new ArrayList<>();
        ResultSet resultSet = executeQuery("select * from bills;");
        try {
            while (resultSet.next()) {
                int billID = resultSet.getInt("bill_id");
                String billDate = resultSet.getString("bill_date");
                String buyerName = resultSet.getString("buyer_name");
                double billTotalCost = resultSet.getDouble("bill_total_cost");
                String paymentMethod = resultSet.getString("payment_method");
                Bill bill = new Bill(billID, billDate, buyerName, billTotalCost, paymentMethod);
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

    ArrayList<String> getUserTypes() {
        ArrayList<String> types = new ArrayList<>();
        for (String t : userTypes.keySet())
            types.add(t);

        return types;
    }

    ArrayList<Supply> getAllSupplies() {
        ArrayList<Supply> supplies = new ArrayList<>();
        ResultSet resultSet = executeQuery("select * from supplies;");
        try {
            while (resultSet.next()) {
                int supplyID = resultSet.getInt("supply_id");
                String supplyDate = resultSet.getString("supply_date");
                String supplierName = resultSet.getString("supplier_name");
                double transportationFees = resultSet.getDouble("transportation_fees");
                double productsTotalCost = resultSet.getDouble("products_total_cost");
                double supplyTotalCost = resultSet.getDouble("supply_total_cost");

                Supply supply = new Supply(supplyID, supplyDate, supplierName, transportationFees, productsTotalCost, supplyTotalCost);
                supplies.add(supply);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return supplies;
    }

    boolean addSupply(String supplyDate, String supplierName, ArrayList<Integer> boughtProductsIDs, ArrayList<Integer> boughtQuantities, ArrayList<Double> buyingPrices, double transportationFees, double productsTotalCost, double supplyTotalCost) {

        boolean done = false;

        done = executeInsertion("insert into supplies (supply_date, supplier_name, transportation_fees, products_total_cost, supply_total_cost) " +
                "values ('" + supplyDate + "', '" + supplierName + "', " + transportationFees + ", " + productsTotalCost + ", " + supplyTotalCost + ");");

        ResultSet resultSet = executeQuery("select supply_id from supplies order by supply_id desc limit 1;");
        int lastSupplyID = 0;
        try {
            if (resultSet.next())
                lastSupplyID = resultSet.getInt("supply_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }


        for (int i = 0, s = boughtQuantities.size(); i < s; i++)
            done = executeInsertion("insert into supplies_details (supply_id, product_id, bought_quantity, buying_price) " +
                    "values (" + lastSupplyID + ", " + boughtProductsIDs.get(i) + ", " + boughtQuantities.get(i) + ", " + buyingPrices.get(i) + ");");
        return done;
    }

    ArrayList<SoldProduct> getBillSoldProducts(int billID) {
        ArrayList<SoldProduct> soldProducts = new ArrayList<>();
        ResultSet resultSet = executeQuery("select products.product_name, bills_details.sold_quantity, bills_details.selling_price from bills_details left join products where bills_details.product_id = products.product_id and bills_details.bill_id=" + billID + ";");

        try {
            while (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                int soldQuantity = resultSet.getInt("sold_quantity");
                double sellingPrice = resultSet.getDouble("selling_price");
                soldProducts.add(new SoldProduct(productName, soldQuantity, sellingPrice));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return soldProducts;

    }

    ArrayList<BoughtProduct> getSupplyBoughtProducts(int supplyID) {
        ArrayList<BoughtProduct> boughtProducts = new ArrayList<>();
        ResultSet resultSet = executeQuery("select products.product_name, supplies_details.bought_quantity, supplies_details.buying_price from supplies_details left join products where supplies_details.product_id = products.product_id and supplies_details.supply_id=" + supplyID + ";");

        try {
            while (resultSet.next()) {
                String productName = resultSet.getString("product_name");
                int boughtQuantity = resultSet.getInt("bought_quantity");
                double buyingPrice = resultSet.getDouble("buying_price");
                boughtProducts.add(new BoughtProduct(productName, boughtQuantity, buyingPrice));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boughtProducts;

    }

}