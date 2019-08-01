package nubahome.databse;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class StoreDatabase {

    public static String databaseURL;
    public static Connection databaseConnection;
    public static User currentUser;
    public static void setDatabaseURL(String dbURL) {
        databaseURL = dbURL;
    }

    public static void connect() {
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

    public static void disconnect() {

        try{
            if(databaseConnection!=null)
                databaseConnection.close();
        }catch(SQLException se){
            se.printStackTrace();
        }
    }

    public static boolean login(String userName, String userPassword) {
        boolean isUser = false;
        try {
            String query = "select * from users where user_name = ? and user_password = ?";
            PreparedStatement preparedStatement= databaseConnection.prepareStatement(query);

            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPassword);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                isUser = true;
                int userID = resultSet.getInt("user_id");
                int roleID = resultSet.getInt("user_role_id");
                UserRole userRole = getUserRole(roleID);

                currentUser = new User(userID, userName, userPassword, userRole);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return isUser;
    }

    public static UserRole getCurrentUserRole() {
        return currentUser.userRole;
    }

    public static UserRole getUserRole(int roleID) {
        UserRole userRole = null;
        try {
            Statement statement = databaseConnection.createStatement();
            String query = "select * from users_roles where role_id = ?";
            PreparedStatement preparedStatement= databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, roleID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                String roleName = resultSet.getString("role_name");
                userRole = new UserRole(roleID, roleName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return userRole;
    }

    public static ArrayList<UserRole> getAllUsersRoles() {
        ArrayList<UserRole> usersRoles = new ArrayList<>();
        try {
            Statement statement = databaseConnection.createStatement();
            String query = "select * from users_roles";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                int roleID = resultSet.getInt("role_id");
                String roleName = resultSet.getString("role_name");

                usersRoles.add(new UserRole(roleID, roleName));
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return usersRoles;
        }
        return usersRoles;
    }

    public static boolean addUser(User user) {
        boolean done = false;
        try {

            String query = "insert into users" + "(user_name, user_password, user_role_id)" + "values (?,?,?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, user.userName);
            preparedStatement.setString(2, user.userPassword);
            preparedStatement.setInt(3, user.userRole.roleID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows == 1)
                done = true;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;
        }

    }

    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Statement statement =databaseConnection.createStatement();
            String query = "select * from users";
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next())
            {
                int userID = resultSet.getInt("user_id");
                String userName = resultSet.getString("user_name");
                String userPassword = resultSet.getString("user_password");
                int userRoleID = resultSet.getInt("user_role_id");
                UserRole userRole = getUserRole(userRoleID);

                users.add(new User(userID, userName, userPassword,userRole));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return users;
        }

        return users;
    }
    
    public static Customer getCustomer(int customerID) {
        Customer customer = null;

        try {
            String query = "select * from customers where customer_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, customerID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String customerName = resultSet.getString("customer_name");
                String customerTelephone = resultSet.getString("customer_telephone");
                String customerAddress = resultSet.getString("customer_address");
                customer = new Customer(customerID, customerName, customerTelephone, customerAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return customer;
        }
        return customer;
    }

    public static ArrayList<Customer> getActiveCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            String query = "select * from customers "
                    + "inner join bills on customer_id = buyer_id "
                    + "inner join bills_instalments_details on bills.bill_id = bills_instalments_details.bill_id "
                    + "where remaining_instalments_number > 0";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                int customerID = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String customerTelephone = resultSet.getString("customer_telephone");
                String customerAddress = resultSet.getString("customer_address");
                customers.add(new Customer(customerID, customerName, customerTelephone, customerAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public static ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            String query = "select * from customers";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                int customerID = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String customerTelephone = resultSet.getString("customer_telephone");
                String customerAddress = resultSet.getString("customer_address");
                customers.add(new Customer(customerID, customerName, customerTelephone, customerAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public static boolean addProductCategory(ProductCategory productCategory) {
        boolean done = false;

        try {
            String query = "insert into products_categories" + "(category_name)" + "values (?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, productCategory.categoryName);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows == 1)
                done = true;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;
        }
    }

    public static ProductCategory getProductCategory(int categoryID) {
        ProductCategory category = null;

        try {
            String query = "select * from products_categories where category_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, categoryID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String categoryName = resultSet.getString("category_name");
                category = new ProductCategory(categoryID, categoryName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return category;
        }
        return category;
    }
    
    public static ArrayList<ProductCategory> getAllProductsCategories() {
        ArrayList<ProductCategory> productCategories = new ArrayList<>();
        try {
            Statement statement = databaseConnection.createStatement();
            String query = "select * from products_categories";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                int categoryID = resultSet.getInt("category_id");
                String categoryName = resultSet.getString("category_name");

                productCategories.add(new ProductCategory(categoryID, categoryName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return productCategories;
        }

        return productCategories;
    }

    public static boolean addProduct(Product product) {
        boolean done = false;

        try {
            String query = "insert into products"
                    + "(product_name, category_id, available_quantity, supply_price, last_supply_date, cash_selling_price, instalment_selling_price)"
                    + "values (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, product.productName);
            preparedStatement.setInt(2, product.category.categoryID);
            preparedStatement.setInt(3, product.availableQuantity);
            preparedStatement.setDouble(4, product.supplyPrice);
            preparedStatement.setString(5, product.lastSupplyDate);
            preparedStatement.setDouble(6, product.cashSellingPrice);
            preparedStatement.setDouble(7, product.instalmentSellingPrice);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows == 1)
                done = true;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;
        }
    }

    public static boolean updateProduct(Product updatedProduct) {
        boolean done = false;
        try {
            String query = "update products set category_id = ?, available_quantity = ?, supply_price = ?, last_supply_date = ?, cash_selling_price = ?, instalment_selling_price = ? where product_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, updatedProduct.category.categoryID);
            preparedStatement.setInt(2, updatedProduct.availableQuantity);
            preparedStatement.setDouble(3, updatedProduct.supplyPrice);
            preparedStatement.setString(4, updatedProduct.lastSupplyDate);
            preparedStatement.setDouble(5, updatedProduct.cashSellingPrice);
            preparedStatement.setDouble(6, updatedProduct.instalmentSellingPrice);
            preparedStatement.setInt(7, updatedProduct.productID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows == 1)
                done = true;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;
        }
    }

    public static boolean updateProducts(ArrayList<Product> updatedProducts) {
        boolean done = false;
        try {
            databaseConnection.setAutoCommit(false);
            String query = "update products set category_id = ?, available_quantity = ?, supply_price = ?, last_supply_date = ?, cash_selling_price = ?, instalment_selling_price = ? where product_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);

            for(Product x : updatedProducts)
            {
                preparedStatement.setInt(1, x.category.categoryID);
                preparedStatement.setInt(2, x.availableQuantity);
                preparedStatement.setDouble(3, x.supplyPrice);
                preparedStatement.setString(4, x.lastSupplyDate);
                preparedStatement.setDouble(5, x.cashSellingPrice);
                preparedStatement.setDouble(6, x.instalmentSellingPrice);
                preparedStatement.setInt(7, x.productID);

                preparedStatement.addBatch();
            }


            int[] updateCounts = preparedStatement.executeBatch();
            boolean allInserted = true;
            for (int i = 0; i < updateCounts.length; i++)
                if (updateCounts[i] <= 0) {
                    allInserted = false;
                    break;
                }

            if (allInserted)
                done = true;

            databaseConnection.setAutoCommit(true);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;
        }
    }

    public static ArrayList<Product> getProductsInCategory(ProductCategory category) {
        ArrayList<Product> products = new ArrayList<>();
        try
        {
            String query = "select * from products where category_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, category.categoryID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int productID = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                int availableQuantity = resultSet.getInt("available_quantity");
                double supplyPrice = resultSet.getDouble("supply_price");
                String lastSupplyDate = resultSet.getString("last_supply_date");
                double cashSellingPrice = resultSet.getDouble("cash_selling_price");
                double instalmentSellingPrice = resultSet.getDouble("instalment_selling_price");

                products.add(new Product(productID, productName, category, availableQuantity, supplyPrice, lastSupplyDate, cashSellingPrice, instalmentSellingPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return products;
        }

        return products;
    }

    public static Product getProduct(int productID) {
        Product product = null;

        try {
            String query = "select * from products where product_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, productID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String productName = resultSet.getString("product_name");
                int productCategoryID = resultSet.getInt("category_id");
                ProductCategory category = getProductCategory(productCategoryID);
                int availableQuantity = resultSet.getInt("available_quantity");
                double supplyPrice = resultSet.getDouble("supply_price");
                String lastSupplyDate = resultSet.getString("last_supply_date");
                double cashSellingPrice = resultSet.getDouble("cash_selling_price");
                double instalmentSellingPrice = resultSet.getDouble("instalment_selling_price");
                product = new Product(productID, productName, category, availableQuantity, supplyPrice, lastSupplyDate, cashSellingPrice, instalmentSellingPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return product;
        }
        return product;
    }

    public static boolean updateBoughtProduct(Supply supply, BoughtProduct updatedBoughtProduct, double updatedProductsTotalCost) {

        try {
            String query = "update bought_products set bought_quantity = ?, buying_price = ? where supply_id = ? and product_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);

            preparedStatement.setDouble(1, updatedBoughtProduct.boughtQuantity);
            preparedStatement.setDouble(2, updatedBoughtProduct.buyingPrice);
            preparedStatement.setInt(3, supply.supplyID);
            preparedStatement.setInt(4, updatedBoughtProduct.productID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows != 1)
                return false;

            query = "update supplies set products_total_cost = ?, supply_total_cost = ? where supply_id = ?";
            preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setDouble(1, updatedProductsTotalCost);
            preparedStatement.setDouble(2, supply.additionalFees + updatedProductsTotalCost);
            preparedStatement.setInt(3, supply.supplyID);


            numOfRows = preparedStatement.executeUpdate();
            if(numOfRows != 1)
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public static ArrayList<BoughtProduct> getBoughtProducts(int supplyID) {
        ArrayList<BoughtProduct> boughtProducts = new ArrayList<>();
        try
        {
            String query = "select * from bought_products where supply_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, supplyID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int productID = resultSet.getInt("product_id");
                Product product = getProduct(productID);
                int boughtQuantity = resultSet.getInt("bought_quantity");
                double buyingPrice = resultSet.getDouble("buying_price");

                boughtProducts.add(new BoughtProduct(product, boughtQuantity, buyingPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return boughtProducts;
        }

        return boughtProducts;
    }

    public static boolean updateSoldProduct(Bill bill, SoldProduct updatedSoldProduct, double updatedProductsTotalCost) {
        try {
            String query = "update sold_products set sold_quantity = ?, selling_price = ? where bill_id = ? and product_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);

            preparedStatement.setDouble(1, updatedSoldProduct.soldQuantity);
            preparedStatement.setDouble(2, updatedSoldProduct.sellingPrice);
            preparedStatement.setInt(3, bill.billID);
            preparedStatement.setInt(4, updatedSoldProduct.productID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows != 1)
                return false;

            query = "update bills set products_total_cost = ?, bill_total_cost = ? where bill_id = ?";
            preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setDouble(1, updatedProductsTotalCost);
            preparedStatement.setDouble(2, updatedProductsTotalCost);
            preparedStatement.setInt(3, bill.billID);


            numOfRows = preparedStatement.executeUpdate();
            if(numOfRows != 1)
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static ArrayList<SoldProduct> getSoldProducts(int billID) {
        ArrayList<SoldProduct> soldProducts = new ArrayList<>();
        try
        {
            String query = "select * from sold_products where bill_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, billID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int productID = resultSet.getInt("product_id");
                Product product = getProduct(productID);
                int soldQuantity = resultSet.getInt("sold_quantity");
                double sellingPrice = resultSet.getDouble("selling_price");

                soldProducts.add(new SoldProduct(product, soldQuantity, sellingPrice));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return soldProducts;
        }

        return soldProducts;
    }

    public static boolean addSupplier(Supplier supplier) {
        try {
            String query = "insert into suppliers" + "(supplier_name, supplier_telephone, supplier_address)" + "values (?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, supplier.supplierName);
            preparedStatement.setString(2, supplier.supplierTelephone);
            preparedStatement.setString(3, supplier.supplierAddress);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows <= 0)
                return false;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
    public static boolean updateSupplier(Supplier updatedSupplier) {
        boolean done = false;
        try {
            String query = "update suppliers set supplier_name = ?, supplier_telephone = ?, supplier_address = ? where supplier_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, updatedSupplier.supplierName);
            preparedStatement.setString(2, updatedSupplier.supplierTelephone);
            preparedStatement.setString(3, updatedSupplier.supplierAddress);
            preparedStatement.setInt(4, updatedSupplier.supplierID);


            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows == 1)
                done = true;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;
        }
    }

    public static Supplier getSupplier(int supplierID) {
        Supplier supplier = null;

        try {
            String query = "select * from suppliers where supplier_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, supplierID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                String supplierName = resultSet.getString("supplier_name");
                String supplierTelephone = resultSet.getString("supplier_telephone");
                String supplierAddress = resultSet.getString("supplier_address");
                supplier =  new Supplier(supplierID, supplierName, supplierTelephone, supplierAddress);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return supplier;
        }

        return supplier;
    }

    public static ArrayList<Supplier> getAllSuppliers() {
        ArrayList<Supplier> suppliers = new ArrayList<>();

        try {
            String query = "select * from suppliers";
            Statement statement = databaseConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                int supplierID = resultSet.getInt("supplier_id");
                String supplierName = resultSet.getString("supplier_name");
                String supplierTelephone = resultSet.getString("supplier_telephone");
                String supplierAddress = resultSet.getString("supplier_address");
                suppliers.add(new Supplier(supplierID, supplierName, supplierTelephone, supplierAddress));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public static boolean addSupply(Supply supply) {
        try {
            String query = "insert into supplies"
                    + "(supplier_id, supply_date, additional_fees, products_total_cost, supply_total_cost)"
                    + "values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, supply.supplier.supplierID);
            preparedStatement.setString(2, supply.supplyDate);
            preparedStatement.setDouble(3, supply.additionalFees);
            preparedStatement.setDouble(4, supply.productsTotalCost);
            preparedStatement.setDouble(5, supply.supplyTotalCost);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows <= 0)
                return false;

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(!resultSet.next())
                return false;

            databaseConnection.setAutoCommit(false);

            supply.supplyID = resultSet.getInt(1);
            query = "insert into bought_products"
                    + "(supply_id, product_id, bought_quantity, buying_price)"
                    + "values (?, ?, ?, ?)";

            for(BoughtProduct x : supply.boughtProducts)
            {
                preparedStatement = databaseConnection.prepareStatement(query);
                preparedStatement.setInt(1,supply.supplyID);
                preparedStatement.setInt(2, x.productID);
                preparedStatement.setInt(3, x.boughtQuantity);
                preparedStatement.setDouble(4, x.buyingPrice);

                preparedStatement.addBatch();

            }

            int[] updateCounts = preparedStatement.executeBatch();

            for (int i = 0; i < updateCounts.length; i++)
                if (updateCounts[i] <= 0)
                    return false;

            databaseConnection.setAutoCommit(true);
            
            ArrayList<Product> toUpdateProducts = new ArrayList<>();
            for(BoughtProduct x : supply.boughtProducts)
            {
                int productID = x.productID;
                String productName = x.productName;
                ProductCategory category = x.category;
                int availableQuantity = x.availableQuantity + x.boughtQuantity;
                double supplyPrice = x.buyingPrice;
                String lastSupplyDate = supply.supplyDate;
                double cashSellingPrice = x.cashSellingPrice;
                double instalmentSellingPrice = x.instalmentSellingPrice;

                toUpdateProducts.add(new Product(productID, productName, category, availableQuantity, supplyPrice, lastSupplyDate, cashSellingPrice, instalmentSellingPrice));
            }


            boolean areUpdated = updateProducts(toUpdateProducts);
            if(!areUpdated)
                return false;

            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        finally {
            try {
                databaseConnection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean updateSupply(Supply updatedSupply) {
        try {
            String query = "update supplies set supply_date = ?, supplier_id = ?, additional_fees = ?, products_total_cost = ?, supply_total_cost = ? where supply_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, updatedSupply.supplyDate);
            preparedStatement.setInt(2, updatedSupply.supplier.supplierID);
            preparedStatement.setDouble(3, updatedSupply.additionalFees);
            preparedStatement.setDouble(4, updatedSupply.productsTotalCost);
            preparedStatement.setDouble(5, updatedSupply.supplyTotalCost);
            preparedStatement.setInt(6, updatedSupply.supplyID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows != 1)
                return false;

            preparedStatement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public static boolean deleteSupply(Supply supply) {
        try {
            String query = "delete from bought_products where supply_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, supply.supplyID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows < 1)
                return false;


            query = "delete from supplies where supply_id = ?";
            preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, supply.supplyID);

            numOfRows = preparedStatement.executeUpdate();
            if(numOfRows != 1)
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public static ArrayList<Supply> getSupplies(String queryStartDate, String queryEndDate) {
        ArrayList<Supply> supplies = new ArrayList<>();
        try {
            String query = "select * from supplies where supply_date between ? and ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, queryStartDate);
            preparedStatement.setString(2, queryEndDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int supplyID = resultSet.getInt("supply_id");
                String supplyDate = resultSet.getString("supply_date");
                int supplierID = resultSet.getInt("supplier_id");
                Supplier supplier = getSupplier(supplierID);
                double additionalFees = resultSet.getDouble("additional_fees");
                double productsTotalCost = resultSet.getDouble("products_total_cost");
                double supplyTotalCost = resultSet.getDouble("supply_total_cost");
                ArrayList<BoughtProduct> boughtProducts = getBoughtProducts(supplyID);
                supplies.add(new Supply(supplyID, supplyDate, supplier, additionalFees, productsTotalCost, supplyTotalCost, boughtProducts));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return supplies;
        }

        return supplies;
    }

    public static ArrayList<Supply> getSupplierSupplies(Supplier supplier) {
        ArrayList<Supply> supplies = new ArrayList<>();
        try {
            String query = "select * from supplies where supplier_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, supplier.supplierID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int supplyID = resultSet.getInt("supply_id");
                String supplyDate = resultSet.getString("supply_date");
                int supplierID = resultSet.getInt("supplier_id");
                double additionalFees = resultSet.getDouble("additional_fees");
                double productsTotalCost = resultSet.getDouble("products_total_cost");
                double supplyTotalCost = resultSet.getDouble("supply_total_cost");
                ArrayList<BoughtProduct> boughtProducts = getBoughtProducts(supplyID);
                supplies.add(new Supply(supplyID, supplyDate, supplier, additionalFees, productsTotalCost, supplyTotalCost, boughtProducts));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return supplies;
        }

        return supplies;
    }

    public static boolean addBill(Bill bill) {
        try {
            //insert bill data
            String query = "insert into bills"
                    + "(buyer_id, bill_date, products_total_cost, bill_total_cost, payment_method)"
                    + "values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, bill.buyer.customerID);
            preparedStatement.setString(2, bill.billDate);
            preparedStatement.setDouble(3, bill.productsTotalCost);
            preparedStatement.setDouble(4, bill.billTotalCost);
            preparedStatement.setString(5, bill.paymentMethod);

            int numOfRows = preparedStatement.executeUpdate();

            if(numOfRows <= 0)
                return false;


            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(!resultSet.next())
                return false;

            bill.billID = resultSet.getInt(1);


            //insert instalment payment data
            if(bill.getPaymentMethod() == "تقسيط")
            {
                query = "insert into bills_instalments_details"
                        + "(bill_id, guarantor_id, initial_payment, remaining_money, instalment_amount, first_instalment_date, last_instalment_date, remaining_instalments_number)"
                        + "values (?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = databaseConnection.prepareStatement(query);

                preparedStatement.setInt(1, bill.billID);
                preparedStatement.setInt(2, bill.billInstalmentsDetails.guarantor.customerID);
                preparedStatement.setDouble(3, bill.billInstalmentsDetails.initialPayment);
                preparedStatement.setDouble(4, bill.billInstalmentsDetails.remainingMoney);
                preparedStatement.setDouble(5, bill.billInstalmentsDetails.initialPayment);
                preparedStatement.setString(6, bill.billInstalmentsDetails.firstInstalmentDate);
                preparedStatement.setString(7, bill.billInstalmentsDetails.lastInstalmentDate);
                preparedStatement.setInt(8, bill.billInstalmentsDetails.remainingInstalmentsNumber);

                numOfRows = preparedStatement.executeUpdate();

                if(numOfRows <= 0)
                    return false;

                ArrayList<Instalment> instalments = new ArrayList<>();
                int instalmentsNumber = bill.billInstalmentsDetails.remainingInstalmentsNumber;
                for(int i = 0; i < instalmentsNumber ; i++)
                {
                    int billID = bill.billID;
                    double paidMoney = 0;
                    double remainingMoney = bill.billInstalmentsDetails.remainingMoney;
                    String instalmentDueDate = LocalDate.parse(bill.billInstalmentsDetails.firstInstalmentDate).plusDays(30 * i).toString();
                    String instalmentPaymentDate = "";
                    String instalmentState = "غير مدفوع";

                    instalments.add(new Instalment(billID, paidMoney, remainingMoney, instalmentDueDate, instalmentPaymentDate, instalmentState));
                }

                boolean areInserted = addInstalments(instalments);
                if(!areInserted)
                    return false;

            }

            databaseConnection.setAutoCommit(false);

            query = "insert into sold_products"
                    + "(bill_id, product_id, sold_quantity, selling_price)"
                    + "values (?, ?, ?, ?)";

            preparedStatement = databaseConnection.prepareStatement(query);
            for (SoldProduct x : bill.soldProducts) {
                preparedStatement.setInt(1, bill.billID);
                preparedStatement.setInt(2, x.productID);
                preparedStatement.setInt(3, x.soldQuantity);
                preparedStatement.setDouble(4, x.sellingPrice);

                preparedStatement.addBatch();
            }

            int[] updateCounts = preparedStatement.executeBatch();

            for (int i = 0; i < updateCounts.length; i++)
                if (updateCounts[i] <= 0)
                    return false;

            databaseConnection.setAutoCommit(true);

            ArrayList<Product> toUpdateProducts = new ArrayList<>();
            for(SoldProduct x : bill.soldProducts)
            {
                int productID = x.productID;
                String productName = x.productName;
                ProductCategory category = x.category;
                int availableQuantity = x.availableQuantity - x.soldQuantity;
                double supplyPrice = x.supplyPrice;
                String lastSupplyDate = x.lastSupplyDate;
                double cashSellingPrice = x.cashSellingPrice;
                double instalmentSellingPrice = x.getInstalmentSellingPrice();

                toUpdateProducts.add(new Product(productID, productName, category, availableQuantity, supplyPrice, lastSupplyDate, cashSellingPrice, instalmentSellingPrice));
            }


            boolean areUpdated = updateProducts(toUpdateProducts);
            if(!areUpdated)
                return false;

            preparedStatement.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        finally {
            try {
                databaseConnection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean updateBill(Bill updatedBill) {
        try {
            String query = "update bills set bill_date = ?, supplier_id = ?, products_total_cost = ?, bill_total_cost = ?, payment_method = ? where bill_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, updatedBill.billDate);
            preparedStatement.setInt(2, updatedBill.buyer.customerID);
            preparedStatement.setDouble(3, updatedBill.productsTotalCost);
            preparedStatement.setDouble(4, updatedBill.billTotalCost);
            preparedStatement.setString(5, updatedBill.paymentMethod);
            preparedStatement.setInt(6, updatedBill.billID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows != 1)
                return false;

            if(updatedBill.paymentMethod.equals("تقسيط"))
            {
                query = "update bills_instalments_details set guarantor_id = ?, initial_payment = ?, remaining_money = ?, instalment_amount = ?, first_instalment_date = ?, last_instalment_date = ?, remaining_instalments_number = ? where bill_id = ?";
                preparedStatement = databaseConnection.prepareStatement(query);
                preparedStatement.setInt(1, updatedBill.billInstalmentsDetails.guarantor.customerID);
                preparedStatement.setDouble(2, updatedBill.billInstalmentsDetails.initialPayment);
                preparedStatement.setDouble(3, updatedBill.billInstalmentsDetails.remainingMoney);
                preparedStatement.setDouble(4, updatedBill.billInstalmentsDetails.instalmentAmount);
                preparedStatement.setString(5, updatedBill.billInstalmentsDetails.firstInstalmentDate);
                preparedStatement.setString(6, updatedBill.billInstalmentsDetails.lastInstalmentDate);
                preparedStatement.setInt(7, updatedBill.billInstalmentsDetails.remainingInstalmentsNumber);
                preparedStatement.setInt(8, updatedBill.billID);

                numOfRows = preparedStatement.executeUpdate();
                if(numOfRows != 1)
                    return false;

            }

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean deleteBill(Bill bill) {
        try {
            String query = "delete from sold_products where bill_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, bill.billID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows < 1)
                return false;

            if(bill.paymentMethod.equals("تقسيط"))
            {
                query = "delete from instalments_payments where instalment_id in (select instalment_id from instalments where bill_id = ?)";
                preparedStatement = databaseConnection.prepareStatement(query);
                preparedStatement.setInt(1, bill.billID);

                numOfRows = preparedStatement.executeUpdate();
                if(numOfRows < 0)
                    return false;

                query = "delete from instalments where bill_id = ?";
                preparedStatement = databaseConnection.prepareStatement(query);
                preparedStatement.setInt(1, bill.billID);

                numOfRows = preparedStatement.executeUpdate();
                if(numOfRows < 1)
                    return false;

                query = "delete from bills_instalments_details where bill_id = ?";
                preparedStatement = databaseConnection.prepareStatement(query);
                preparedStatement.setInt(1, bill.billID);

                numOfRows = preparedStatement.executeUpdate();
                if(numOfRows != 1)
                    return false;

            }

            query = "delete from bills where bill_id = ?";
            preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, bill.billID);

            numOfRows = preparedStatement.executeUpdate();
            if(numOfRows != 1)
                return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<Bill> getCustomerBills(Customer customer) {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            String query = "select * from bills where buyer_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, customer.customerID);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int billID = resultSet.getInt("bill_id");
                String billDate = resultSet.getString("bill_date");
                int buyerID = resultSet.getInt("buyer_id");
                Customer buyer = getCustomer(buyerID);
                double productsTotalCost = resultSet.getDouble("products_total_cost");
                double billTotalCost = resultSet.getDouble("bill_total_cost");
                String paymentMethod = resultSet.getString("payment_method");
                ArrayList<SoldProduct> soldProducts = getSoldProducts(billID);
                if(paymentMethod.equals("كاش"))
                    bills.add(new Bill(billID, billDate, buyer,  productsTotalCost, billTotalCost, paymentMethod,soldProducts));
                else
                {
                    BillInstalmentsDetails billInstalmentsDetails = getBillInstalmentsDetails(billID);
                    bills.add(new Bill(billID, billDate, buyer,  productsTotalCost, billTotalCost, paymentMethod,soldProducts, billInstalmentsDetails));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return bills;
        }

        return bills;
    }

    public static ArrayList<Bill> getBills(String queryStartDate, String queryEndDate) {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            String query = "select * from bills where bill_date between ? and ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, queryStartDate);
            preparedStatement.setString(2, queryEndDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int billID = resultSet.getInt("bill_id");
                String billDate = resultSet.getString("bill_date");
                int buyerID = resultSet.getInt("buyer_id");
                Customer buyer = getCustomer(buyerID);
                double productsTotalCost = resultSet.getDouble("products_total_cost");
                double billTotalCost = resultSet.getDouble("bill_total_cost");
                String paymentMethod = resultSet.getString("payment_method");
                ArrayList<SoldProduct> soldProducts = getSoldProducts(billID);
                if(paymentMethod.equals("كاش"))
                    bills.add(new Bill(billID, billDate, buyer,  productsTotalCost, billTotalCost, paymentMethod,soldProducts));
                else
                {
                    BillInstalmentsDetails billInstalmentsDetails = getBillInstalmentsDetails(billID);
                    bills.add(new Bill(billID, billDate, buyer,  productsTotalCost, billTotalCost, paymentMethod,soldProducts, billInstalmentsDetails));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return bills;
        }

        return bills;
    }

    public static ArrayList<Bill> getUpcomingInstalments(String queryStartDate, String queryEndDate) {
        ArrayList<Bill> bills = new ArrayList<>();
        try {
            String query = "select * from bills b inner join bills_instalments_details d on b.bill_id = d.bill_id where d.first_instalment_date between ? and ? and d.remaining_instalments_number > 0";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, queryStartDate);
            preparedStatement.setString(2, queryEndDate);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                int billID = resultSet.getInt("bill_id");
                String billDate = resultSet.getString("bill_date");
                int buyerID = resultSet.getInt("buyer_id");
                Customer buyer = getCustomer(buyerID);
                double productsTotalCost = resultSet.getDouble("products_total_cost");
                double billTotalCost = resultSet.getDouble("bill_total_cost");
                String paymentMethod = resultSet.getString("payment_method");
                ArrayList<SoldProduct> soldProducts = getSoldProducts(billID);
                int guarantorID = resultSet.getInt("guarantor_id");
                Customer guarantor = getCustomer(guarantorID);
                double initialPayment = resultSet.getDouble("initial_payment");
                double remainingMoney = resultSet.getDouble("remaining_money");
                double instalmentAmount = resultSet.getDouble("instalment_amount");
                String firstInstalmentDate = resultSet.getString("first_instalment_date");
                String lastInstalmentDate = resultSet.getString("last_instalment_date");
                int remainingInstalmentsNumber = resultSet.getInt("remaining_instalments_number");
                BillInstalmentsDetails billInstalmentsDetails = new BillInstalmentsDetails(guarantor, initialPayment, remainingMoney, instalmentAmount, firstInstalmentDate, lastInstalmentDate, remainingInstalmentsNumber);
                bills.add(new Bill(billID, billDate, buyer,  productsTotalCost, billTotalCost, paymentMethod,soldProducts, billInstalmentsDetails));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return bills;
        }

        return bills;
    }

    public static boolean updateBillInstalmentsDetails(BillInstalmentsDetails billInstalmentsDetails, int billID) {
        boolean done = false;
        try {
            String query = "update bills_instalments_details set guarantor_id = ?, initial_payment = ?, remaining_money = ?, instalment_amount = ?, first_instalment_date = ?, last_insalment_date = ?, remaining_instalments_number = ? where bill_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, billInstalmentsDetails.guarantor.customerID);
            preparedStatement.setDouble(2, billInstalmentsDetails.initialPayment);
            preparedStatement.setDouble(3, billInstalmentsDetails.remainingMoney);
            preparedStatement.setDouble(4, billInstalmentsDetails.initialPayment);
            preparedStatement.setString(5, billInstalmentsDetails.firstInstalmentDate);
            preparedStatement.setString(6, billInstalmentsDetails.lastInstalmentDate);
            preparedStatement.setInt(7, billInstalmentsDetails.remainingInstalmentsNumber);
            preparedStatement.setInt(8, billID);
            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows == 1)
                done = true;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;
        }
    }

    public static BillInstalmentsDetails getBillInstalmentsDetails(int billID) {
        BillInstalmentsDetails billInstalmentsDetails = null;

        try {
            String query = "select * from bills_instalments_details where bill_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, billID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                int guarantorID = resultSet.getInt("guarantor_id");
                Customer guarantor = getCustomer(guarantorID);
                double initialPayment = resultSet.getDouble("initial_payment");
                double remainingMoney = resultSet.getDouble("remaining_money");
                double instalmentAmount = resultSet.getDouble("instalment_amount");
                String firstInstalmentDate = resultSet.getString("first_instalment_date");
                String lastInstalmentDate = resultSet.getString("last_instalment_date");
                int remainingInstalmentsNumber = resultSet.getInt("remaining_instalments_number");

                billInstalmentsDetails = new BillInstalmentsDetails(guarantor, initialPayment, remainingMoney, instalmentAmount, firstInstalmentDate, lastInstalmentDate,remainingInstalmentsNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return billInstalmentsDetails;
        }
        return billInstalmentsDetails;
    }

    public static boolean addInstalments(ArrayList<Instalment> instalments) {
        boolean done = false;
        try {
            databaseConnection.setAutoCommit(false);
            String query = "insert into instalments"
                    + " (bill_id, paid_money, remaining_money, instalment_due_date, instalment_payment_date, instalment_state)"
                    + " values ( ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            for(Instalment i : instalments)
            {
                preparedStatement.setInt(1, i.billID);
                preparedStatement.setDouble(2, i.paidMoney);
                preparedStatement.setDouble(3, i.remainingMoney);
                preparedStatement.setString(4, i.instalmentDueDate);
                preparedStatement.setString(5, i.instalmentPaymentDate);
                preparedStatement.setString(6, i.instalmentState);

                preparedStatement.addBatch();
            }

            int[] updateCounts = preparedStatement.executeBatch();
            boolean allInserted = true;
            for (int i = 0; i < updateCounts.length; i++)
                if (updateCounts[i] <= 0) {
                    allInserted = false;
                    break;
                }

            if (allInserted)
                done = true;

            databaseConnection.setAutoCommit(true);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return done;
        }
        return done;
    }

    public static boolean updateInstalment(Instalment updatedInstalment) {
        try {
            String query = "update instalments set bill_id = ?, paid_money = ?, remaining_money = ?, instalment_due_date = ?, instalment_payment_date = ?, instalment_state = ? where instalment_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, updatedInstalment.billID);
            preparedStatement.setDouble(2, updatedInstalment.paidMoney);
            preparedStatement.setDouble(3, updatedInstalment.remainingMoney);
            preparedStatement.setString(4, updatedInstalment.instalmentDueDate);
            preparedStatement.setString(5, updatedInstalment.instalmentPaymentDate);
            preparedStatement.setString(6, updatedInstalment.instalmentState);
            preparedStatement.setInt(7, updatedInstalment.instalmentID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows != 1)
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static ArrayList<Instalment> getCustomerAllInstalments(Customer customer) {
        ArrayList<Instalment> instalments = new ArrayList<>();
        try {
            String query = "select * from instalments i inner join bills b on i.bill_id = b.bill_id where b.buyer_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, customer.customerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int instalmentID = resultSet.getInt("instalment_id");
                int billID = resultSet.getInt("bill_id");
                double paidMoney = resultSet.getDouble("paid_money");
                double remainingMoney = resultSet.getDouble("remaining_money");
                String instalmentDueDate = resultSet.getString("instalment_due_date");
                String instalmentPaymentDate = resultSet.getString("instalment_payment_date");
                String instalmentState = resultSet.getString("instalment_state");
                Instalment instalment = new Instalment(instalmentID, billID, paidMoney ,remainingMoney, instalmentDueDate, instalmentPaymentDate, instalmentState);
                instalment.instalmentPayments = getInstalmentPayments(instalment);
                instalments.add(instalment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return instalments;
        }

        return instalments;
    }

    public static ArrayList<Instalment> getCustomerUnpaidInstalments(Customer customer) {
        ArrayList<Instalment> instalments = new ArrayList<>();
        try {
            String query = "select * from instalments i inner join bills b on i.bill_id = b.bill_id where b.buyer_id = ? and i.instalment_state != 'مدفوع بالكامل' ";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, customer.customerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int instalmentID = resultSet.getInt("instalment_id");
                int billID = resultSet.getInt("bill_id");
                double paidMoney = resultSet.getDouble("paid_money");
                double remainingMoney = resultSet.getDouble("remaining_money");
                String instalmentDueDate = resultSet.getString("instalment_due_date");
                String instalmentPaymentDate = resultSet.getString("instalment_payment_date");
                String instalmentState = resultSet.getString("instalment_state");

                Instalment instalment = new Instalment(instalmentID, billID, paidMoney, remainingMoney, instalmentDueDate, instalmentPaymentDate, instalmentState);
                instalment.instalmentPayments = getInstalmentPayments(instalment);
                instalments.add(instalment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return instalments;
        }

        return instalments;
    }

    public static ArrayList<Instalment> getCustomerPaidInstalments(Customer customer) {
        ArrayList<Instalment> instalments = new ArrayList<>();
        try {
            String query = "select * from instalments i inner join bills b on i.bill_id = b.bill_id where b.buyer_id = ? and i.instalment_state = 'مدفوع بالكامل' ";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, customer.customerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int instalmentID = resultSet.getInt("instalment_id");
                int billID = resultSet.getInt("bill_id");
                double paidMoney = resultSet.getDouble("paid_money");
                double remainingMoney = resultSet.getDouble("remaining_money");
                String instalmentDueDate = resultSet.getString("instalment_due_date");
                String instalmentPaymentDate = resultSet.getString("instalment_payment_date");
                String instalmentState = resultSet.getString("instalment_state");

                Instalment instalment = new Instalment(instalmentID, billID, paidMoney, remainingMoney, instalmentDueDate, instalmentPaymentDate, instalmentState);
                instalment.instalmentPayments = getInstalmentPayments(instalment);
                instalments.add(instalment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return instalments;
        }

        return instalments;
    }

    public static boolean addInstalmentPayment(InstalmentPayment instalmentPayment) {
        boolean done = false;
        try {
            String query = "insert into instalments_payments"
                    + " (instalment_id, paid_money, payment_date)"
                    + " values (?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, instalmentPayment.instalmentID);
            preparedStatement.setDouble(2, instalmentPayment.paidMoney);
            preparedStatement.setString(3, instalmentPayment.paymentDate);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows == 1)
                done = true;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;

        }
    }

    public static boolean updateInstalmentPayment(InstalmentPayment instalmentPayment) {
        boolean done = false;
        try {
            String query = "update instalments_payments set paid_money = ?, payment_date = ? where payment_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setDouble(1, instalmentPayment.paidMoney);
            preparedStatement.setString(2, instalmentPayment.paymentDate);
            preparedStatement.setInt(3, instalmentPayment.paymentID);

            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows == 1)
                done = true;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;

        }
    }

    public static ArrayList<InstalmentPayment> getInstalmentPayments(Instalment instalment) {
        ArrayList<InstalmentPayment> instalmentPayments = new ArrayList<>();
        try {
            String query = "select * from instalments_payments where instalment_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, instalment.instalmentID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int paymentID = resultSet.getInt("payment_id");
                int instalmentID = resultSet.getInt("instalment_id");
                Double paidAmount = resultSet.getDouble("paid_money");
                String paymentDate = resultSet.getString("payment_date");

                instalmentPayments.add(new InstalmentPayment(paymentID, instalmentID, paidAmount, paymentDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return instalmentPayments;
        }

        return instalmentPayments;
    }

    public static boolean addCustomer(Customer customer) {
        try {
            String query = "insert into customers" + "(customer_name, customer_telephone, customer_address)" + "values (?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, customer.customerName);
            preparedStatement.setString(2, customer.customerTelephone);
            preparedStatement.setString(3, customer.customerAddress);
            
            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows <= 0)
                return false;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean updateCustomer(Customer updatedCustomer) {
        boolean done = false;
        try {
            String query = "update customers set customer_name = ?, customer_telephone = ?, customer_address = ? where customer_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, updatedCustomer.customerName);
            preparedStatement.setString(2, updatedCustomer.customerTelephone);
            preparedStatement.setString(3, updatedCustomer.customerAddress);
            preparedStatement.setInt(4, updatedCustomer.customerID);


            int numOfRows = preparedStatement.executeUpdate();
            if(numOfRows == 1)
                done = true;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        finally {
            return done;
        }
    }

    public static ArrayList<ProductSupply> getProductSupplies(Product product) {
        ArrayList<ProductSupply> productSupplies = new ArrayList<>();
        try {
            String query = "select * from supplies inner join bought_products on supplies.supply_id = bought_products.supply_id  inner join suppliers on supplies.supplier_id = suppliers.supplier_id where bought_products.product_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, product.productID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                int supplyID = resultSet.getInt("supply_id");
                String supplyDate = resultSet.getString("supply_date");
                int supplierID = resultSet.getInt("supplier_id");
                String supplierName = resultSet.getString("supplier_name");
                String supplierTelephone = resultSet.getString("supplier_telephone");
                String supplierAddress = resultSet.getString("supplier_address");
                Supplier supplier =  new Supplier(supplierID, supplierName, supplierTelephone, supplierAddress);
                int boughtQuantity = resultSet.getInt("bought_quantity");
                double buyingPrice = resultSet.getDouble("buying_price");

                productSupplies.add(new ProductSupply(supplyID, supplyDate, supplier, product, boughtQuantity, buyingPrice));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return productSupplies;
        }

        return productSupplies;
    }
}