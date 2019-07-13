package nubahome.databse;

import java.sql.*;
import java.util.ArrayList;


public class StoreDatabase {

    private static String databaseURL;
    private static Connection databaseConnection;
    private static User currentUser;
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
            String query = "insert into suppliers" + "(supplier_name)" + "values (?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, supplier.supplierName);

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
                supplier =  new Supplier(supplierID, supplierName);
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

                suppliers.add(new Supplier(supplierID, supplierName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
    }

    public static boolean addSupply(Supply supply) {
        try {
            String query = "insert into supplies"
                    + "(supplier_id, supply_date, transportation_fees, products_total_cost, supply_total_cost)"
                    + "values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, supply.supplier.supplierID);
            preparedStatement.setString(2, supply.supplyDate);
            preparedStatement.setDouble(3, supply.transportationFees);
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
                preparedStatement.setDouble(4, x.supplyPrice);

                preparedStatement.addBatch();

            }

            int[] updateCounts = preparedStatement.executeBatch();

            for (int i = 0; i < updateCounts.length; i++)
                if (updateCounts[i] <= 0)
                    return false;


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
                double transportationFees = resultSet.getDouble("transportation_fees");
                double productsTotalCost = resultSet.getDouble("products_total_cost");
                double supplyTotalCost = resultSet.getDouble("supply_total_cost");
                ArrayList<BoughtProduct> boughtProducts = getBoughtProducts(supplyID);
                supplies.add(new Supply(supplyID, supplyDate, supplier, transportationFees, productsTotalCost, supplyTotalCost, boughtProducts));
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
                    + "(buyer_name, bill_date, products_total_cost, bill_total_cost, payment_method)"
                    + "values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setString(1, bill.buyerName);
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
                query = "insert into instalments_payments"
                        + "(bill_id, guarantor_name, initial_payment, remaining_money, instalment_amount, first_instalment_date, last_instalment_date)"
                        + "values (?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = databaseConnection.prepareStatement(query);

                preparedStatement.setInt(1, bill.billID);
                preparedStatement.setString(2, bill.getInstalmentsPayment().guarantorName);
                preparedStatement.setDouble(3, bill.getInstalmentsPayment().initialPayment);
                preparedStatement.setDouble(4, bill.getInstalmentsPayment().remainingMoney);
                preparedStatement.setDouble(5, bill.getInstalmentsPayment().initialPayment);
                preparedStatement.setString(6, bill.getInstalmentsPayment().firstInstalmentDate);
                preparedStatement.setString(7, bill.getInstalmentsPayment().lastInstalmentDate);

                numOfRows = preparedStatement.executeUpdate();

                if(numOfRows <= 0)
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
                String buyerName = resultSet.getString("buyer_name");
                double productsTotalCost = resultSet.getDouble("products_total_cost");
                double billTotalCost = resultSet.getDouble("bill_total_cost");
                String paymentMethod = resultSet.getString("payment_method");
                ArrayList<SoldProduct> soldProducts = getSoldProducts(billID);
                if(paymentMethod.equals("كاش"))
                    bills.add(new Bill(billID, billDate, buyerName,  productsTotalCost, billTotalCost, paymentMethod,soldProducts));
                else
                {
                    InstalmentsPayment instalmentsPayment = getInstalmentsPayment(billID);
                    bills.add(new Bill(billID, billDate, buyerName,  productsTotalCost, billTotalCost, paymentMethod,soldProducts, instalmentsPayment));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return bills;
        }

        return bills;
    }
    
    public static InstalmentsPayment getInstalmentsPayment(int billID) {
        InstalmentsPayment instalmentsPayment = null;

        try {
            String query = "select * from instalments_payments where bill_id = ?";
            PreparedStatement preparedStatement = databaseConnection.prepareStatement(query);
            preparedStatement.setInt(1, billID);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {
                String guarantorName = resultSet.getString("guarantor_name");
                double initialPayment = resultSet.getDouble("initial_payment");
                double remainingMoney = resultSet.getDouble("remaining_money");
                double instalmentAmount = resultSet.getDouble("instalment_amount");
                String firstInstalmentDate = resultSet.getString("first_instalment_date");
                String lastInstalmentDate = resultSet.getString("last_instalment_date");

                instalmentsPayment = new InstalmentsPayment(guarantorName, initialPayment, remainingMoney, instalmentAmount, firstInstalmentDate, lastInstalmentDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return instalmentsPayment;
        }
        return instalmentsPayment;
    }


}