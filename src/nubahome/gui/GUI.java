package nubahome.gui;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import nubahome.databse.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class GUI {

    private static Stage mainStage;
    private static Pane homeSceneLayout;


    public static void open(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setMaximized(true);

        mainStage.setTitle("تسجيل الدخول");
        Scene scene = new Scene(getLoginSceneLayout());
        scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        mainStage.setScene(scene);
        mainStage.show();
    }

    private static Pane getLoginSceneLayout() {

        String nodeText;
        nodeText = "أسم المستخدم";
        Label userNameLabel = new Label(nodeText);
        TextField userNameTextField = new TextField();
        userNameTextField.setPrefSize(100, 20);

        nodeText = "كلمة السر";
        Label passwordLabel = new Label(nodeText);
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefSize(100, 20);

        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {


            String userName = userNameTextField.getText();
            String password = passwordField.getText();

            boolean isUser =  StoreDatabase.login(userName,password);

            if(!isUser)
                messageLabel.setText("بيانات خاطئة! أعد محاولة الدخول.");
            else
            {
                UserRole currentUserRole = StoreDatabase.getCurrentUserRole();
                if (currentUserRole.getRoleName().equals("مدير"))
                {
                    System.out.println("مدير");
                    //homeScene = new getManagerHomeSceneLayout();
                }
                else if (currentUserRole.getRoleName().equals("موظف")) {
                    System.out.println("موظف");
                    homeSceneLayout = getClerkHomeSceneLayout();
                }

                mainStage.setTitle("الواجهة الرئيسية");
                mainStage.getScene().setRoot(homeSceneLayout);
            }

        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(userNameTextField, 1, 0);
        gridPane.add(userNameLabel, 0, 0);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(passwordLabel, 0, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(messageLabel, 1, 2);

        return gridPane;

    }

    private static Pane getClerkHomeSceneLayout() {
        Button showCustomersButton = new Button("عرض بيانات العملاء");
        showCustomersButton.setPrefSize(150, 50);
        showCustomersButton.setOnAction(actionEvent -> {
            mainStage.setTitle("العملاء");
            mainStage.getScene().setRoot(getShowCustomersSceneLayout());
        });

        Button showSuppliersButton = new Button("عرض بيانات الموردين");
        showSuppliersButton.setPrefSize(150, 50);
        showSuppliersButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الموردين");
            mainStage.getScene().setRoot(getShowSuppliersSceneLayout());
        });

        Button addProductsButton = new Button("إضافة منتجات");
        addProductsButton.setPrefSize(150, 50);
        addProductsButton.setOnAction(actionEvent -> {
            mainStage.setTitle("المنتجات");
            mainStage.getScene().setRoot(getAddProductSceneLayout());
        });

        Button addSuppliersButton = new Button("إضافة مورد");
        addSuppliersButton.setPrefSize(150, 50);
        addSuppliersButton.setOnAction(actionEvent -> {
            mainStage.setTitle("إضافة مورد");
            mainStage.getScene().setRoot(getAddSupplierSceneLayout());
        });


        Button addCustomerButton = new Button("إضافة عميل");
        addCustomerButton.setPrefSize(150, 50);
        addCustomerButton.setOnAction(actionEvent -> {
            mainStage.setTitle("إضافة عميل");
            mainStage.getScene().setRoot(getAddCustomerSceneLayout());
        });

        Button showProductsButton = new Button("عرض بيانات المنتجات");
        showProductsButton.setPrefSize(150, 50);
        showProductsButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض بيانات المنتجات");
            mainStage.getScene().setRoot(getShowProductsSceneLayout());
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(showCustomersButton, 0,0);
        gridPane.add(showSuppliersButton, 1,0);
        gridPane.add(addProductsButton, 2,0);
        gridPane.add(addCustomerButton,0,1);
        gridPane.add(addSuppliersButton, 1,1);
        gridPane.add(showProductsButton, 2, 1);

        return gridPane;

    }

    private static Pane getShowCustomersSceneLayout() {
        Label showCustomersLabel = new Label("أعرض بيانات");

        ArrayList<String> options = new ArrayList<>();
        options.add("كل العملاء");
        options.add("العملاء النشيطين");
        ChoiceBox<String> optionsChoiceBox = new ChoiceBox<>();
        optionsChoiceBox.setItems(FXCollections.observableList(options));

        HBox topHBox = new HBox();
        topHBox.setSpacing(20);
        topHBox.getChildren().add(showCustomersLabel);
        topHBox.getChildren().add(optionsChoiceBox);

        TableColumn<Customer, String> customerNameColumn = new TableColumn<>("أسم العميل");
        TableColumn<Customer, String> customerTelephoneColumn = new TableColumn<>("رقم الهاتف");
        TableColumn<Customer, String> customerAddressColumn = new TableColumn<>("العنوان");

        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerTelephoneColumn.setCellValueFactory(new PropertyValueFactory<>("customerTelephone"));
        customerAddressColumn.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));

        TableView<Customer> customersTable = new TableView<>();
        customersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        customersTable.getColumns().setAll(customerNameColumn, customerTelephoneColumn, customerAddressColumn);

        optionsChoiceBox.setOnAction(actionEvent -> {
            String selectedOption = optionsChoiceBox.getValue();
            ArrayList<Customer> customers;
            if(selectedOption == "كل العملاء")
                customers = StoreDatabase.getAllCustomers();
            else
                customers = StoreDatabase.getActiveCustomers();

            customersTable.setItems(FXCollections.observableList(customers));
        });

        MenuItem addBill = new MenuItem("إضافة فاتورة");
        MenuItem showBills = new MenuItem("عرض مشتريات العميل");
        MenuItem showInstalments = new MenuItem("عرض أقساط العميل");
        MenuItem editInfo = new MenuItem("تعديل بيانات العميل");
        MenuItem deleteCustomer = new MenuItem("حذف العميل");

        addBill.setOnAction(actionEvent -> {
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("إضافة مشتريات عميل");
            mainStage.getScene().setRoot(getAddBillToCustomerSceneLayout(selectedCustomer));
        });

        showBills.setOnAction(actionEvent -> {
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("عرض بيانات مشتريات عميل");
            mainStage.getScene().setRoot(getShowCustomerBillsSceneLayout(selectedCustomer));
        });

        showInstalments.setOnAction(actionEvent -> {
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("عرض بيانات أفساط عميل");
            mainStage.getScene().setRoot(getShowCustomerInstalmentsSceneLayout(selectedCustomer));
        });

        editInfo.setOnAction(actionEvent -> {
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("تعديل بيانات عميل");
            mainStage.getScene().setRoot(getEditCustomerInfoSceneLayout(selectedCustomer));
        });

        deleteCustomer.setOnAction(actionEvent -> {
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();

        });

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(addBill, showBills, showInstalments, editInfo, deleteCustomer);

        customersTable.setContextMenu(contextMenu);
        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });


        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().add(homeButton);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setTop(topHBox);
        borderPane.setCenter(customersTable);
        borderPane.setBottom(navigationHBox);



        return borderPane;
    }

    private static Pane getShowSuppliersSceneLayout() {
        TableColumn<Supplier, String> supplierNameColumn = new TableColumn<>("أسم المورد");
        TableColumn<Supplier, String> supplierTelephoneColumn = new TableColumn<>("رقم الهاتف");
        TableColumn<Supplier, String> supplierAddressColumn = new TableColumn<>("العنوان");

        supplierNameColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierTelephoneColumn.setCellValueFactory(new PropertyValueFactory<>("supplierTelephone"));
        supplierAddressColumn.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));

        TableView<Supplier> suppliersTable = new TableView<>();
        suppliersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        suppliersTable.getColumns().setAll(supplierNameColumn, supplierTelephoneColumn, supplierAddressColumn);

        ArrayList<Supplier> suppliers = StoreDatabase.getAllSuppliers();
        suppliersTable.setItems(FXCollections.observableList(suppliers));

        MenuItem addSupply = new MenuItem("إضافة فاتورة");
        MenuItem showSupplies = new MenuItem("عرض مبيعات المورد");
        MenuItem editInfo = new MenuItem("تعديل بيانات المورد");
        MenuItem deleteSupplier = new MenuItem("حذف المورد");

        addSupply.setOnAction(actionEvent -> {
            Supplier selectedSupplier = suppliersTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("إضافة مبيعات مورد");
            mainStage.getScene().setRoot(getAddSupplyToSupplierSceneLayout(selectedSupplier));
        });

        showSupplies.setOnAction(actionEvent -> {
            Supplier selectedSupplier = suppliersTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("عرض بيانات مبيعات مورد");
            mainStage.getScene().setRoot(getShowSupplierSuppliesSceneLayout(selectedSupplier));
        });


        editInfo.setOnAction(actionEvent -> {
            Supplier selectedSupplier = suppliersTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("تعديل بيانات مورد");
            mainStage.getScene().setRoot(getEditSupplierInfoSceneLayout(selectedSupplier));
        });

        deleteSupplier.setOnAction(actionEvent -> {
            Supplier selectedSupplier = suppliersTable.getSelectionModel().getSelectedItem();

        });

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(addSupply, showSupplies, editInfo, deleteSupplier);

        suppliersTable.setContextMenu(contextMenu);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });


        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().add(homeButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(suppliersTable);
        borderPane.setBottom(navigationHBox);



        return borderPane;
    }

    private static Pane getAddCustomerSceneLayout() {
        String nodeText;

        nodeText = "أسم العميل";
        Label customerNameLabel = new Label(nodeText);
        TextField customerNameTextField = new TextField();
        customerNameTextField.setPrefSize(200, 20);

        Label customerTelephoneLabel = new Label("هاتف العميل");
        TextField customerTelephoneTextField = new TextField();
        customerNameTextField.setPrefSize(200, 20);

        Label customerAddressLabel = new Label("عنوان العميل");
        TextField customerAddressTextField = new TextField();
        customerAddressTextField.setPrefSize(400, 40);


        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {


            String message = "";
            messageLabel.setText(message);

            String customerName = customerNameTextField.getText().trim();
            String customerTelephone = customerTelephoneTextField.getText().trim();
            String customerAddress = customerAddressTextField.getText().trim();

            Customer customer = new Customer(customerName, customerTelephone, customerAddress);
            boolean done = StoreDatabase.addCustomer(customer);

            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات");
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(customerNameLabel,0,0);
        gridPane.add(customerNameTextField,1,0);
        gridPane.add(customerTelephoneLabel, 0,1);
        gridPane.add(customerTelephoneTextField, 1, 1);
        gridPane.add(customerAddressLabel, 0, 2);
        gridPane.add(customerAddressTextField, 1, 2);
        gridPane.add(submitButton,0,3);
        gridPane.add(messageLabel,0,4);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });


        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().add(homeButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);
        borderPane.setBottom(navigationHBox);

        return borderPane;
    }

    private static Pane getAddProductSceneLayout() {
        String nodeText;

        nodeText = "أسم المنتج";
        Label productNameLabel = new Label(nodeText);
        TextField productNameTextField = new TextField();
        productNameTextField.setPrefSize(100, 20);

        nodeText = "نوع المنتج";
        Label productCategoryLabel = new Label(nodeText);
        ChoiceBox productsCategoriesChoiceBox = new ChoiceBox();
        productsCategoriesChoiceBox.setConverter(new StringConverter<ProductCategory>() {
            @Override
            public String toString(ProductCategory c) {
                return c.getCategoryName();
            }

            @Override
            public ProductCategory fromString(String s) {
                return null;
            }
        });
        ArrayList<ProductCategory> productsCategories = StoreDatabase.getAllProductsCategories();
        productsCategoriesChoiceBox.setItems(FXCollections.observableList(productsCategories));


        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
        submitButton.setOnAction(event -> {


            String message = "";
            messageLabel.setText(message);

            String productName = productNameTextField.getText().trim();
            ProductCategory category = (ProductCategory) productsCategoriesChoiceBox.getValue();
            int availableQuantity = 0;
            double supplyPrice = 0;
            String lastSupplyDate = "";
            double cashSellingPrice = 0;
            double instalmentSellingPrice = 0;

            Product product = new Product(productName, category, availableQuantity, supplyPrice, lastSupplyDate, cashSellingPrice,instalmentSellingPrice);

            boolean done = StoreDatabase.addProduct(product);
            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات");


        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(productNameLabel,0,0);
        gridPane.add(productNameTextField,1,0);
        gridPane.add(productsCategoriesChoiceBox,1,1);
        gridPane.add(productCategoryLabel,0,1);
        gridPane.add(submitButton,0,4);
        gridPane.add(messageLabel,0,5);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });


        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().add(homeButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);
        borderPane.setBottom(navigationHBox);


        return borderPane;
    }

    private static Pane getAddSupplierSceneLayout() {
        String nodeText;

        nodeText = "أسم المورد";
        Label supplierNameLabel = new Label(nodeText);
        TextField supplierNameTextField = new TextField();
        supplierNameTextField.setPrefSize(200, 20);

        Label supplierTelephoneLabel = new Label("رقم الهاتف");
        TextField supplierTelephoneTextField = new TextField();
        supplierTelephoneTextField.setPrefSize(200, 20);

        Label supplierAddressLabel = new Label("العنوان");
        TextField supplierAddressTextField = new TextField();
        supplierAddressTextField.setPrefSize(400, 40);


        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {


            String message = "";
            messageLabel.setText(message);

            String supplierName = supplierNameTextField.getText().trim();
            String supplierTelephone = supplierTelephoneTextField.getText().trim();
            String supplierAddress = supplierAddressTextField.getText().trim();

            Supplier supplier = new Supplier(supplierName, supplierTelephone, supplierAddress);
            boolean done = StoreDatabase.addSupplier(supplier);

            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات");
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(supplierNameLabel,0,0);
        gridPane.add(supplierNameTextField,1,0);
        gridPane.add(supplierTelephoneLabel, 0,1);
        gridPane.add(supplierTelephoneTextField, 1, 1);
        gridPane.add(supplierAddressLabel, 0, 2);
        gridPane.add(supplierAddressTextField, 1, 2);
        gridPane.add(submitButton,0,3);
        gridPane.add(messageLabel,0,4);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });


        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().add(homeButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);
        borderPane.setBottom(navigationHBox);


        return borderPane;
    }

    private static Pane getAddSupplyToSupplierSceneLayout(Supplier supplier) {
        Label messageLabel = new Label();

        Button submitButton = new Button("أدخل");
        submitButton.setPrefSize(100, 20);

        Label supplyDateLabel = new Label("تاريخ الشراء");
        DatePicker supplyDatePicker = new DatePicker();
        supplyDatePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                supplyDatePicker.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        Label supplierNameLabel = new Label("أسم المورد: " + supplier.getSupplierName());


        Label transportationFessLabel = new Label( "مصاريف المواصلات");
        TextField transportationFeesTextField = new TextField();
        transportationFeesTextField.setPrefSize(100,20);

        Button addMoreProductsButton = new Button("أضف منتج للفاتورة");
        addMoreProductsButton.setPrefSize(120,20);

        Button addNewProductButton = new Button("أضف منتج جديد لقاعدة البيانات");
        addNewProductButton.setPrefSize(200,20);

        ArrayList<ChoiceBox> productsChoiceBoxes = new ArrayList<>();
        ArrayList<ChoiceBox> productsCategoriesChoiceBoxes = new ArrayList<>();
        ArrayList<TextField> boughtQuantitiesTextFields = new ArrayList<>();
        ArrayList<TextField> buyingPricesTextFields = new ArrayList<>();

        VBox productsVBox = new VBox();
        productsVBox.setSpacing(10);

        addMoreProductsButton.setOnAction(event -> {
            Label productLabel = new Label("أسم المنتج");
            ChoiceBox<Product> productsChoiceBox = new ChoiceBox<>();
            productsChoiceBox.setConverter(new StringConverter<Product>() {
                @Override
                public String toString(Product p) {
                    return p.getProductName();
                }

                @Override
                public Product fromString(String s) {
                    return null;
                }
            });

            Label categoryLabel = new Label("نوع المنتج");
            ChoiceBox<ProductCategory> productsCategoriesChoiceBox = new ChoiceBox<>();
            productsCategoriesChoiceBox.setConverter(new StringConverter<ProductCategory>() {
                @Override
                public String toString(ProductCategory c) {
                    return c.getCategoryName();
                }

                @Override
                public ProductCategory fromString(String s) {
                    return null;
                }
            });
            ArrayList<ProductCategory> productsCategories = StoreDatabase.getAllProductsCategories();
            productsCategoriesChoiceBox.setItems(FXCollections.observableList(productsCategories));

            productsCategoriesChoiceBox.setOnAction( actionEvent -> {
                ProductCategory selectedCategory = productsCategoriesChoiceBox.getValue();
                ArrayList<Product> productsInCategory = StoreDatabase.getProductsInCategory(selectedCategory);
                productsChoiceBox.setItems(FXCollections.observableList(productsInCategory));
            });


            Label boughtQuantityLabel = new Label("الكمية المباعة");
            TextField boughtQuantityTextField = new TextField();
            boughtQuantityTextField.setPrefSize(100,20);


            Label buyingPriceLabel = new Label( "سعر البيع");
            TextField buyingPriceTextField = new TextField();
            buyingPriceTextField.setPrefSize(100,20);

            productsChoiceBoxes.add(productsChoiceBox);
            productsCategoriesChoiceBoxes.add(productsCategoriesChoiceBox);
            boughtQuantitiesTextFields.add(boughtQuantityTextField);
            buyingPricesTextFields.add(buyingPriceTextField);

            HBox productHBox = new HBox();
            productHBox.setSpacing(10);

            productHBox.getChildren().add(categoryLabel);
            productHBox.getChildren().add(productsCategoriesChoiceBox);
            productHBox.getChildren().add(productLabel);
            productHBox.getChildren().add(productsChoiceBox);
            productHBox.getChildren().add(buyingPriceLabel);
            productHBox.getChildren().add(buyingPriceTextField);
            productHBox.getChildren().add(boughtQuantityLabel);
            productHBox.getChildren().add(boughtQuantityTextField);

            productsVBox.getChildren().add(productHBox);




        });

        submitButton.setOnAction(event -> {


            String message = "";
            messageLabel.setText(message);

            String supplyDate = supplyDatePicker.getValue().toString();
            double transportationFees = Double.parseDouble(transportationFeesTextField.getText().trim());


            ArrayList<BoughtProduct> boughtProducts = new ArrayList<>();
            double productsTotalCost = 0;
            int numOfBoughtProducts =  productsChoiceBoxes.size();
            for(int i = 0; i < numOfBoughtProducts; i++)
            {
                Product currentProduct = (Product) productsChoiceBoxes.get(i).getSelectionModel().getSelectedItem();
                int boughtQuantity = Integer.parseInt(boughtQuantitiesTextFields.get(i).getText().trim());
                double buyingPrice = Double.parseDouble(buyingPricesTextFields.get(i).getText().trim());
                boughtProducts.add(new BoughtProduct(currentProduct, boughtQuantity, buyingPrice));

                productsTotalCost += (buyingPrice * boughtQuantity);
            }

            double supplyTotalCost = productsTotalCost + transportationFees;

            Supply supply = new Supply(supplyDate, supplier, transportationFees, productsTotalCost, supplyTotalCost, boughtProducts);
            boolean done = StoreDatabase.addSupply(supply);


            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات");

        });

        addNewProductButton.setOnAction(actionEvent -> {


        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(supplyDateLabel,0,0);
        gridPane.add(supplyDatePicker, 1,0);
        gridPane.add(supplierNameLabel,0,1);
        gridPane.add(transportationFessLabel,0,2);
        gridPane.add(transportationFeesTextField,1,2);
        gridPane.add(addMoreProductsButton,0,3);
        gridPane.add(addNewProductButton, 1, 3);
        gridPane.add(productsVBox,0,4);
        gridPane.add(submitButton,0,5);
        gridPane.add(messageLabel,0,6);

        ScrollPane scrollPane = new ScrollPane(gridPane);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });

        Button backButton = new Button("العودة إلي الواجهة السابقة");
        backButton.setPrefSize(180, 30);
        backButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض بيانات الموردين");
            mainStage.getScene().setRoot(getShowSuppliersSceneLayout());
        });

        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().addAll(homeButton, backButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(scrollPane);
        borderPane.setBottom(navigationHBox);


        return borderPane;
    }

    private static Pane getEditCustomerInfoSceneLayout(Customer customer) {
        String nodeText;

        nodeText = "أسم العميل";
        Label customerNameLabel = new Label(nodeText);
        TextField customerNameTextField = new TextField();
        customerNameTextField.setPrefSize(200, 20);
        customerNameTextField.setText(customer.getCustomerName());

        Label customerTelephoneLabel = new Label("هاتف العميل");
        TextField customerTelephoneTextField = new TextField();
        customerTelephoneTextField.setPrefSize(200, 20);
        customerTelephoneTextField.setText(customer.getCustomerTelephone());

        Label customerAddressLabel = new Label("عنوان العميل");
        TextField customerAddressTextField = new TextField();
        customerAddressTextField.setPrefSize(400, 40);
        customerAddressTextField.setText(customer.getCustomerAddress());

        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {


            String message = "";
            messageLabel.setText(message);

            String customerName = customerNameTextField.getText().trim();
            String customerTelephone = customerTelephoneTextField.getText().trim();
            String customerAddress = customerAddressTextField.getText().trim();

            Customer updatedCustomer = new Customer(customer.getCustomerID(), customerName, customerTelephone, customerAddress);
            boolean done = StoreDatabase.updateCustomer(updatedCustomer);

            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات");
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(customerNameLabel,0,0);
        gridPane.add(customerNameTextField,1,0);
        gridPane.add(customerTelephoneLabel, 0,1);
        gridPane.add(customerTelephoneTextField, 1, 1);
        gridPane.add(customerAddressLabel, 0, 2);
        gridPane.add(customerAddressTextField, 1, 2);
        gridPane.add(submitButton,0,3);
        gridPane.add(messageLabel,0,4);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });

        Button backButton = new Button("العودة إلي الواجهة السابقة");
        backButton.setPrefSize(180, 30);
        backButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض بيانات العملاء");
            mainStage.getScene().setRoot(getShowCustomersSceneLayout());
        });

        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().addAll(homeButton, backButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);
        borderPane.setBottom(navigationHBox);


        return borderPane;
    }

    private static Pane getShowCustomerBillsSceneLayout(Customer customer) {
        TableColumn buyerName = new TableColumn("أسم العميل");
        TableColumn billID = new TableColumn("رقم الفاتورة");
        TableColumn billDate = new TableColumn("تاريخ الفاتورة");
        TableColumn billTotalCost = new TableColumn("التكلفة الكلية");
        TableColumn paymentMethod = new TableColumn("وسيلة الدفع");

        billID.setCellValueFactory( new PropertyValueFactory<Bill, Integer>("billID"));
        buyerName.setCellValueFactory( cellData -> {
            Bill bill = (Bill) ((TableColumn.CellDataFeatures) cellData).getValue();
            return new SimpleStringProperty(bill.getBuyer().getCustomerName());
        });
        billDate.setCellValueFactory( new PropertyValueFactory<Bill, Date>("billDate"));
        billTotalCost.setCellValueFactory( new PropertyValueFactory<Bill, Double>("billTotalCost"));
        paymentMethod.setCellValueFactory(new PropertyValueFactory<Bill, String>("paymentMethod"));

        TableView<Bill> billsTable = new TableView();
        billsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        billsTable.getColumns().add(billID);
        billsTable.getColumns().add(billDate);
        billsTable.getColumns().add(buyerName);
        billsTable.getColumns().add(billTotalCost);
        billsTable.getColumns().add(paymentMethod);

        MenuItem addProductToBillOption = new MenuItem("أضف منتج للفاتورة");
        addProductToBillOption.setOnAction(actionEvent -> {
            Bill selectedBill = billsTable.getSelectionModel().getSelectedItem();

        });

        MenuItem showSoldProductsOption = new MenuItem("عرض المنتجات المباعة");
        showSoldProductsOption.setOnAction(actionEvent -> {
            Bill selectedBill = billsTable.getSelectionModel().getSelectedItem();

            Scene scene = new Scene(getShowBillSoldProductsSceneLayout(selectedBill));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            Stage newStage = new Stage();
            newStage.setMaximized(true);
            newStage.setTitle("عرض بيانات المنتجات المباعة");
            newStage.setScene(scene);
            newStage.show();
        });

        MenuItem editBillOption = new MenuItem("تعديل الفاتورة");
        editBillOption.setOnAction(actionEvent -> {
            Bill selectedBill = billsTable.getSelectionModel().getSelectedItem();

        });

        MenuItem deleteBillOption = new MenuItem("حذف الفاتورة");
        deleteBillOption.setOnAction(actionEvent -> {
            Bill selectedBill = billsTable.getSelectionModel().getSelectedItem();

        });


        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(showSoldProductsOption);
        contextMenu.getItems().add(addProductToBillOption);
        contextMenu.getItems().add(editBillOption);
        contextMenu.getItems().add(deleteBillOption);

        billsTable.setContextMenu(contextMenu);


        ArrayList<Bill> customerBills = StoreDatabase.getCustomerBills(customer);
        billsTable.setItems(FXCollections.observableList(customerBills));

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });

        Button backButton = new Button("العودة إلي الواجهة السابقة");
        backButton.setPrefSize(180, 30);
        backButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض بيانات العملاء");
            mainStage.getScene().setRoot(getShowCustomersSceneLayout());
        });

        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().addAll(homeButton, backButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(billsTable);
        borderPane.setBottom(navigationHBox);

        return borderPane;
    }

    private static Pane getShowSupplierSuppliesSceneLayout(Supplier supplier) {
        TableColumn supplyID = new TableColumn("رقم التوريد");
        TableColumn supplyDate = new TableColumn("تاريخ التوريد");
        TableColumn supplierName = new TableColumn("اسم المورد");
        TableColumn transportationFees = new TableColumn("مصاريف المواصلات");
        TableColumn productsTotalCost = new TableColumn("تكلفة المنتجات");
        TableColumn supplyTotalCost = new TableColumn("التكلفة الكاملة");

        supplyID.setCellValueFactory(new PropertyValueFactory<Supply, Integer>("supplyID"));
        supplyDate.setCellValueFactory(new PropertyValueFactory<Supply, String>("supplyDate"));
        productsTotalCost.setCellValueFactory(new PropertyValueFactory<Supply, Double>("productsTotalCost"));
        transportationFees.setCellValueFactory(new PropertyValueFactory<Supply, Double>("transportationFees"));
        supplyTotalCost.setCellValueFactory(new PropertyValueFactory<Supply, Double>("supplyTotalCost"));
        supplierName.setCellValueFactory(cellData -> {
            Supply supply = (Supply) ((TableColumn.CellDataFeatures) cellData).getValue();
            return new SimpleStringProperty(supply.getSupplier().getSupplierName());
        });

        TableView<Supply> suppliesTable = new TableView();
        suppliesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        suppliesTable.getColumns().add(supplyID);
        suppliesTable.getColumns().add(supplyDate);
        suppliesTable.getColumns().add(supplierName);
        suppliesTable.getColumns().add(productsTotalCost);
        suppliesTable.getColumns().add(transportationFees);
        suppliesTable.getColumns().add(supplyTotalCost);


        ArrayList<Supply> suplierSupplies = StoreDatabase.getSupplierSupplies(supplier);
        suppliesTable.setItems(FXCollections.observableList(suplierSupplies));

        MenuItem addProductToSupplyOption = new MenuItem("أضف منتج للفاتورة");
        addProductToSupplyOption.setOnAction(actionEvent -> {
            Supply selectedSupply = suppliesTable.getSelectionModel().getSelectedItem();

        });

        MenuItem showBoughtProductsOption = new MenuItem("عرض المنتجات المباعة");
        showBoughtProductsOption.setOnAction(actionEvent -> {
            Supply selectedSupply = suppliesTable.getSelectionModel().getSelectedItem();

            Scene scene = new Scene(getShowSupplyBoughtProductsSceneLayout(selectedSupply));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            Stage newStage = new Stage();
            newStage.setMaximized(true);
            newStage.setTitle("عرض بيانات المنتجات المباعة");
            newStage.setScene(scene);
            newStage.show();
        });

        MenuItem editSupplyOption = new MenuItem("تعديل الفاتورة");
        editSupplyOption.setOnAction(actionEvent -> {
            Supply selectedSupply = suppliesTable.getSelectionModel().getSelectedItem();

        });

        MenuItem deleteSupplyOption = new MenuItem("حذف الفاتورة");
        deleteSupplyOption.setOnAction(actionEvent -> {
            Supply selectedSupply = suppliesTable.getSelectionModel().getSelectedItem();

        });


        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(showBoughtProductsOption);
        contextMenu.getItems().add(addProductToSupplyOption);
        contextMenu.getItems().add(editSupplyOption);
        contextMenu.getItems().add(deleteSupplyOption);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });

        Button backButton = new Button("العودة إلي الواجهة السابقة");
        backButton.setPrefSize(180, 30);
        backButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض بيانات العملاء");
            mainStage.getScene().setRoot(getShowSuppliersSceneLayout());
        });

        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().addAll(homeButton, backButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(suppliesTable);
        borderPane.setBottom(navigationHBox);

        return borderPane;
    }

    private static Pane getShowCustomerInstalmentsSceneLayout(Customer customer) {
        Label showInstalmentsLabel = new Label("أعرض بيانات");

        ArrayList<String> options = new ArrayList<>();
        options.add("كل الأقساط");
        options.add("الأقساط الغير مدفوعة");
        ChoiceBox<String> optionsChoiceBox = new ChoiceBox<>();
        optionsChoiceBox.setItems(FXCollections.observableList(options));

        HBox topHBox = new HBox();
        topHBox.setSpacing(20);
        topHBox.getChildren().add(showInstalmentsLabel);
        topHBox.getChildren().add(optionsChoiceBox);

        TableColumn<Instalment, Number> indexColumn = new TableColumn<Instalment, Number>("رقم القسط");
        TableColumn<Instalment, String> dueDateColumn = new TableColumn<>("تاريخ الإستحقاق");
        TableColumn<Instalment, String> paymentDateColumn = new TableColumn<>("تاريخ الدفع");
        TableColumn<Instalment, Double> paidMoneyColumn = new TableColumn<>("المدفوع");
        TableColumn<Instalment, Double> remainingMoneyColumn = new TableColumn<>("الباقي");
        TableColumn<Instalment, String> instalmentStateColumn = new TableColumn<>("الحالة");

        TableView<Instalment> instalmentsTable = new TableView<>();
        instalmentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("instalmentDueDate"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("instalmentPaymentDate"));
        paidMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("paidMoney"));
        remainingMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("remainingMoney"));
        instalmentStateColumn.setCellValueFactory(new PropertyValueFactory<>("instalmentState"));
        indexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(instalmentsTable.getItems().indexOf(column.getValue())));

        instalmentsTable.getColumns().addAll(indexColumn, dueDateColumn, paymentDateColumn, paidMoneyColumn, remainingMoneyColumn, instalmentStateColumn);

        optionsChoiceBox.setOnAction(actionEvent -> {
            String selectedOption = optionsChoiceBox.getValue();
            ArrayList<Instalment> instalments;

            if(optionsChoiceBox.getValue().equals("كل الأقساط"))
                instalments = StoreDatabase.getCustomerAllInstalments(customer);
            else
                instalments = StoreDatabase.getCustomerUnpaidInstalments(customer);

            instalmentsTable.setItems(FXCollections.observableList(instalments));
        });

        MenuItem payInstalmentOption = new MenuItem("دفع القسط");
        MenuItem showInstalmentPaymentsOption = new MenuItem("عرض بيانات المدفوع من القسط");

        payInstalmentOption.setOnAction(actionEvent -> {
            Instalment selectedInstalment = instalmentsTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("دفع قسط");
            mainStage.getScene().setRoot(getAddInstalmentPaymentSceneLayout(selectedInstalment));
        });

        showInstalmentPaymentsOption.setOnAction(actionEvent -> {
            Instalment selectedInstalment = instalmentsTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("عرض بيانات مدفوعات قسط");
            mainStage.getScene().setRoot(getShowInstalmentPaymentsSceneLayout(selectedInstalment));
        });

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(payInstalmentOption, showInstalmentPaymentsOption);

        instalmentsTable.setContextMenu(contextMenu);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });

        Button backButton = new Button("العودة إلي الواجهة السابقة");
        backButton.setPrefSize(180, 30);
        backButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض بيانات العملاء");
            mainStage.getScene().setRoot(getShowCustomersSceneLayout());
        });

        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().addAll(homeButton, backButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(instalmentsTable);
        borderPane.setBottom(navigationHBox);


        return borderPane;
    }

    private static Pane getShowInstalmentPaymentsSceneLayout(Instalment instalment) {
        TableView<InstalmentPayment> instalmentPaymentsTable = new TableView<>();
        instalmentPaymentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        instalmentPaymentsTable.setItems(FXCollections.observableList(instalment.getInstalmentPayments()));

        TableColumn<InstalmentPayment, Number> indexColumn = new TableColumn<>("الرقم");
        TableColumn<InstalmentPayment, String> paymentDateColumn = new TableColumn<>("تاريخ الدفع");
        TableColumn<InstalmentPayment, Double> paidMoneyColumn = new TableColumn<>("المدفوع");

        indexColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(instalmentPaymentsTable.getItems().indexOf(column.getValue())));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        paidMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("paidMoney"));

        instalmentPaymentsTable.getColumns().addAll(indexColumn, paymentDateColumn, paidMoneyColumn);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem editOption = new MenuItem("تعديل");
        editOption.setOnAction(actionEvent -> {
            InstalmentPayment instalmentPayment = instalmentPaymentsTable.getSelectionModel().getSelectedItem();
            Stage stage = new Stage();
            stage.setMaximized(true);
            stage.setTitle("تعديل مدفوعات قسط");
            Scene scene = new Scene(getEditInstalmentPaymentSceneLayout(instalmentPayment));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            stage.setScene(scene);
            stage.show();
        });

        contextMenu.getItems().addAll(editOption);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(instalmentPaymentsTable);


        return borderPane;
    }

    private static Pane getAddBillToCustomerSceneLayout(Customer customer) {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        String nodeText;

        Label billDateLabel = new Label("تاريخ الفاتورة");
        DatePicker billDatePicker = new DatePicker();
        billDatePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                billDatePicker.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        nodeText = "أسم العميل: " + customer.getCustomerName();
        Label buyerNameLabel = new Label(nodeText);

        nodeText = "وسيلة الدفع";
        Label paymentMethodLabel = new Label(nodeText);
        ChoiceBox<String> paymentMethodBox = new ChoiceBox<>();
        ArrayList<String> paymentMethods = new ArrayList<>();
        paymentMethods.add("كاش");
        paymentMethods.add("تقسيط");
        paymentMethodBox.setItems(FXCollections.observableList(paymentMethods));

        ArrayList<ChoiceBox> productsChoiceBoxes = new ArrayList<>();
        ArrayList<ChoiceBox> productsCategoriesChoiceBoxes = new ArrayList<>();
        ArrayList<TextField> soldQuantitiesTextFields = new ArrayList<>();
        ArrayList<TextField> sellingPricesTextFields = new ArrayList<>();

        VBox productsVBox = new VBox();
        productsVBox.setSpacing(10);


        nodeText = "أضف منتج للفاتورة";
        Button addMoreProductsButton = new Button(nodeText);
        addMoreProductsButton.setPrefSize(120,20);
        addMoreProductsButton.setOnAction(event -> {
            Label productLabel = new Label("أسم المنتج");
            ChoiceBox<Product> productsChoiceBox = new ChoiceBox<>();
            productsChoiceBox.setConverter(new StringConverter<Product>() {
                @Override
                public String toString(Product p) {
                    return p.getProductName();
                }

                @Override
                public Product fromString(String s) {
                    return null;
                }
            });

            Label categoryLabel = new Label("نوع المنتج");
            ChoiceBox<ProductCategory> productsCategoriesChoiceBox = new ChoiceBox<>();
            productsCategoriesChoiceBox.setConverter(new StringConverter<ProductCategory>() {
                @Override
                public String toString(ProductCategory c) {
                    return c.getCategoryName();
                }

                @Override
                public ProductCategory fromString(String s) {
                    return null;
                }
            });
            ArrayList<ProductCategory> productsCategories = StoreDatabase.getAllProductsCategories();
            productsCategoriesChoiceBox.setItems(FXCollections.observableList(productsCategories));

            productsCategoriesChoiceBox.setOnAction( actionEvent -> {
                ProductCategory selectedCategory = productsCategoriesChoiceBox.getValue();
                ArrayList<Product> productsInCategory = StoreDatabase.getProductsInCategory(selectedCategory);
                productsChoiceBox.setItems(FXCollections.observableList(productsInCategory));
            });


            Label soldQuantityLabel = new Label("الكمية المباعة");
            TextField soldQuantityTextField = new TextField();
            soldQuantityTextField.setPrefSize(100,20);


            Label sellingPriceLabel = new Label( "سعر البيع");
            TextField sellingPriceTextField = new TextField();
            sellingPriceTextField.setPrefSize(100,20);

            productsChoiceBoxes.add(productsChoiceBox);
            productsCategoriesChoiceBoxes.add(productsCategoriesChoiceBox);
            soldQuantitiesTextFields.add(soldQuantityTextField);
            sellingPricesTextFields.add(sellingPriceTextField);

            HBox productHBox = new HBox();
            productHBox.setSpacing(10);

            productHBox.getChildren().add(categoryLabel);
            productHBox.getChildren().add(productsCategoriesChoiceBox);
            productHBox.getChildren().add(productLabel);
            productHBox.getChildren().add(productsChoiceBox);
            productHBox.getChildren().add(sellingPriceLabel);
            productHBox.getChildren().add(sellingPriceTextField);
            productHBox.getChildren().add(soldQuantityLabel);
            productHBox.getChildren().add(soldQuantityTextField);

            productsVBox.getChildren().add(productHBox);




        });

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
        Label messageLabel = new Label();
        submitButton.setOnAction(event -> {


            String message = "";
            messageLabel.setText(message);

            String billDate = billDatePicker.getValue().toString();
            Customer buyer = customer;
            String paymentMethod = paymentMethodBox.getValue();


            ArrayList<SoldProduct> soldProducts = new ArrayList<>();

            int numOfSoldProducts =  productsChoiceBoxes.size();
            double productsTotalCost = 0;
            for(int i = 0; i < numOfSoldProducts; i++)
            {
                Product currentProduct = (Product) productsChoiceBoxes.get(i).getSelectionModel().getSelectedItem();
                int soldQuantity = Integer.parseInt(soldQuantitiesTextFields.get(i).getText().trim());
                double sellingPrice = Double.parseDouble(sellingPricesTextFields.get(i).getText().trim());
                soldProducts.add(new SoldProduct(currentProduct, soldQuantity, sellingPrice));

                productsTotalCost += (sellingPrice * soldQuantity);
            }


            double billTotalCost = productsTotalCost;


            Bill bill = new Bill(billDate, buyer, productsTotalCost, billTotalCost, paymentMethod, soldProducts);

            boolean done = StoreDatabase.addBill(bill);


            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات!");

        });

        Button addBillInstalmentsDetailsButton = new Button("أضف تفاصيل التفسيط");
        addBillInstalmentsDetailsButton.setPrefSize(180, 20);
        addBillInstalmentsDetailsButton.setOnAction(actionEvent -> {
            String billDate = billDatePicker.getValue().toString();
            Customer buyer = customer;
            String paymentMethod = paymentMethodBox.getValue();


            ArrayList<SoldProduct> soldProducts = new ArrayList<>();

            int numOfSoldProducts =  productsChoiceBoxes.size();
            double productsTotalCost = 0;
            for(int i = 0; i < numOfSoldProducts; i++)
            {
                Product currentProduct = (Product) productsChoiceBoxes.get(i).getSelectionModel().getSelectedItem();
                int soldQuantity = Integer.parseInt(soldQuantitiesTextFields.get(i).getText().trim());
                double sellingPrice = Double.parseDouble(sellingPricesTextFields.get(i).getText().trim());
                soldProducts.add(new SoldProduct(currentProduct, soldQuantity, sellingPrice));

                productsTotalCost += (sellingPrice * soldQuantity);
            }


            double billTotalCost = productsTotalCost;


            Bill bill = new Bill(billDate, buyer, productsTotalCost, billTotalCost, paymentMethod, soldProducts);

            mainStage.setTitle("إدخال تفاصيل التقسيط");
            mainStage.getScene().setRoot(getAddBillInstalmentsDetailsSceneLayout(bill));
        });


        gridPane.add(billDateLabel,0,0);
        gridPane.add(billDatePicker,1,0);
        gridPane.add(buyerNameLabel,0,1);
        gridPane.add(paymentMethodLabel,0,2);
        gridPane.add(paymentMethodBox,1,2);
        gridPane.add(addMoreProductsButton,0,3);
        gridPane.add(productsVBox,0,4);
        gridPane.add(submitButton,0,5);
        gridPane.add(messageLabel,0,6);

        ScrollPane pane = new ScrollPane(gridPane);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });

        Button backButton = new Button("العودة إلي الواجهة السابقة");
        backButton.setPrefSize(180, 30);
        backButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض بيانات العملاء");
            mainStage.getScene().setRoot(getShowCustomersSceneLayout());
        });

        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().addAll(homeButton, backButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(pane);
        borderPane.setBottom(navigationHBox);


        return borderPane;
    }

    private static Pane getAddInstalmentPaymentSceneLayout(Instalment instalment) {
        Label paymentDateLabel = new Label("تاريخ الدفع");
        DatePicker paymentDatePicker = new DatePicker();
        paymentDatePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                paymentDatePicker.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        Label paidMoneyLabel = new Label("المدفوع");
        TextField paidMoneyTextField = new TextField();
        paidMoneyLabel.setPrefSize(50, 20);

        Label messageLabel = new Label();

        Button submitButton = new Button("أدخل");
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {

            String paymentDate = paymentDatePicker.getValue().toString();
            Double paidMoney = Double.parseDouble(paidMoneyTextField.getText().trim());

            boolean done = StoreDatabase.addInstalmentPayment(new InstalmentPayment(instalment.getInstalmentID(), paidMoney, paymentDate));
            messageLabel.setText("");
            if(!done)
                messageLabel.setText("بيانات خاطئة! أعد محاولة الدخول.");
            else
                messageLabel.setText("تم الإدخال بنجاج!");
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(paymentDateLabel, 0, 0);
        gridPane.add(paymentDatePicker, 1, 0);
        gridPane.add(paidMoneyLabel, 0, 1);
        gridPane.add(paidMoneyTextField, 1, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(messageLabel, 0, 3);

        return gridPane;
    }

    private static Pane getEditInstalmentPaymentSceneLayout(InstalmentPayment instalmentPayment) {
        Label paymentDateLabel = new Label("تاريخ الدفع");
        DatePicker paymentDatePicker = new DatePicker();
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        paymentDatePicker.setConverter(new StringConverter<LocalDate>() {

            {
                paymentDatePicker.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        paymentDatePicker.setValue(LocalDate.parse(instalmentPayment.getPaymentDate(), dateFormatter));

        Label paidMoneyLabel = new Label("المدفوع");
        TextField paidMoneyTextField = new TextField();
        paidMoneyLabel.setPrefSize(50, 20);
        paidMoneyTextField.setText("" + instalmentPayment.getPaidMoney());

        Label messageLabel = new Label();

        Button submitButton = new Button("أدخل");
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {

            String paymentDate = paymentDatePicker.getValue().toString();
            Double paidMoney = Double.parseDouble(paidMoneyTextField.getText().trim());

            boolean done = StoreDatabase.updateInstalmentPayment(new InstalmentPayment(instalmentPayment.getPaymentID(),instalmentPayment.getInstalmentID(), paidMoney, paymentDate));
            messageLabel.setText("");
            if(!done)
                messageLabel.setText("بيانات خاطئة! أعد محاولة الدخول.");
            else
                messageLabel.setText("تم الإدخال بنجاج!");
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(paymentDateLabel, 0, 0);
        gridPane.add(paymentDatePicker, 1, 0);
        gridPane.add(paidMoneyLabel, 0, 1);
        gridPane.add(paidMoneyTextField, 1, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(messageLabel, 0, 3);

        return gridPane;
    }

    private static Pane getAddBillInstalmentsDetailsSceneLayout(Bill bill) {
        String nodeText;
        nodeText = "أسم الضامن";
        Label guarantorNameLabel = new Label(nodeText);
        ChoiceBox<Customer> guarantorChoiceBox = new ChoiceBox<>();
        ArrayList<Customer> customers = StoreDatabase.getAllCustomers();
        guarantorChoiceBox.setItems(FXCollections.observableList(customers));

        nodeText = "المقدم";
        Label initialPaymentLabel = new Label(nodeText);
        TextField initialPaymentTextField = new TextField();
        initialPaymentTextField.setPrefSize(100,20);

        nodeText = "تاريخ أول قسط";
        Label firstInstalmentDateLabel = new Label(nodeText);
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                startDatePicker.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        nodeText = "تاريخ أخر قسط";
        Label lastInstalmentDateLabel = new Label(nodeText);
        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setConverter(new StringConverter<LocalDate>() {
            String pattern = "yyyy-MM-dd";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
            {
                endDatePicker.setPromptText(pattern.toLowerCase());
            }

            @Override public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });

        nodeText = "عدد الأقساط المتبقية";
        Label remainingInstalmentsNumberLabel = new Label(nodeText);
        TextField remainingInstalmentsNumberTextField = new TextField();
        remainingInstalmentsNumberTextField.setPrefSize(50,20);

        nodeText = "قيمة القسط";
        Label instalmentAmountLabel = new Label(nodeText);
        TextField instalmentAmountTextField = new TextField();
        instalmentAmountTextField.setPrefSize(100,20);

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
        Label messageLabel = new Label();

        submitButton.setOnAction(actionEvent -> {
            messageLabel.setText("");
            Customer guarantor = guarantorChoiceBox.getValue();
            double initialPayment = Double.parseDouble(initialPaymentTextField.getText().trim());
            double remainingMoney = bill.getBillTotalCost() - initialPayment;
            double instalmentAmount = Double.parseDouble(instalmentAmountTextField.getText().trim());
            String firstInstalmentDate = startDatePicker.getValue().toString();
            String lastInstalmentDate = endDatePicker.getValue().toString();
            int remainingInstalmentsNumber = Integer.parseInt(remainingInstalmentsNumberTextField.getText().trim());
            BillInstalmentsDetails billInstalmentsDetails = new BillInstalmentsDetails(guarantor, initialPayment, remainingMoney, instalmentAmount, firstInstalmentDate, lastInstalmentDate, remainingInstalmentsNumber);
            Bill updatedBill = new Bill(bill.getBillDate(), bill.getBuyer(), bill.getProductsTotalCost(), bill.getBillTotalCost(), bill.getPaymentMethod(), bill.getSoldProducts(), billInstalmentsDetails);

            boolean done = StoreDatabase.addBill(updatedBill);
            if(done)
                messageLabel.setText("تم إدخال البيانات بنجاح!");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات!");
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(guarantorNameLabel, 0, 0);
        gridPane.add(guarantorChoiceBox, 1, 0);
        gridPane.add(initialPaymentLabel, 0,1);
        gridPane.add(initialPaymentTextField, 1,1);
        gridPane.add(instalmentAmountLabel, 0,2);
        gridPane.add(instalmentAmountTextField, 1, 2);
        gridPane.add(remainingInstalmentsNumberLabel, 0,3);
        gridPane.add(remainingInstalmentsNumberTextField, 1, 3);
        gridPane.add(firstInstalmentDateLabel, 0, 4);
        gridPane.add(startDatePicker, 1, 4);
        gridPane.add(lastInstalmentDateLabel, 0, 5);
        gridPane.add(endDatePicker, 0, 6);
        gridPane.add(submitButton, 0, 7);
        gridPane.add(messageLabel,0, 8);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });

        Button backButton = new Button("العودة إلي الواجهة السابقة");
        backButton.setPrefSize(180, 30);
        backButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض بيانات العملاء");
            mainStage.getScene().setRoot(getShowCustomersSceneLayout());
        });

        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().addAll(homeButton, backButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);
        borderPane.setBottom(navigationHBox);

        return borderPane;
    }

    private static Pane getShowBillSoldProductsSceneLayout(Bill bill) {
        TableColumn productNameCol = new TableColumn("أسم المنتج");
        productNameCol.setCellValueFactory(cellData -> {
            SoldProduct soldProduct = (SoldProduct) ((TableColumn.CellDataFeatures) cellData).getValue();
            return new SimpleStringProperty(soldProduct.getProductName());
        });

        TableColumn soldQuantityCol = new TableColumn("الكمية المباعة");
        soldQuantityCol.setCellValueFactory(new PropertyValueFactory<SoldProduct, Integer>("soldQuantity"));

        TableColumn sellingPriceCol = new TableColumn("سعر بيع الوحدة");
        sellingPriceCol.setCellValueFactory(new PropertyValueFactory<SoldProduct, Double>("sellingPrice"));

        TableView<SoldProduct> billDetailsTable = new TableView<>();
        billDetailsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        billDetailsTable.getColumns().setAll(productNameCol, soldQuantityCol, sellingPriceCol);

        billDetailsTable.setItems(FXCollections.observableList(bill.getSoldProducts()));

        Stage secondaryStage = new Stage();
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.setMaximized(true);
        secondaryStage.setTitle("إضافة منتج");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(billDetailsTable);


        return borderPane;
    }

    private static Pane getShowSupplyBoughtProductsSceneLayout(Supply supply) {
        TableColumn productNameCol = new TableColumn("أسم المنتج");
        productNameCol.setCellValueFactory(cellData -> {
            BoughtProduct boughtProduct = (BoughtProduct) ((TableColumn.CellDataFeatures) cellData).getValue();
            return new SimpleStringProperty(boughtProduct.getProductName());
        });

        TableColumn boughtQuantityCol = new TableColumn("الكمية المشتراة");
        boughtQuantityCol.setCellValueFactory(new PropertyValueFactory<BoughtProduct, Integer>("boughtQuantity"));

        TableColumn buyingPriceCol = new TableColumn("سعر شراء الوحدة");
        buyingPriceCol.setCellValueFactory(new PropertyValueFactory<BoughtProduct, Double>("buyingPrice"));

        TableView<BoughtProduct> billDetailsTable = new TableView<>();
        billDetailsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        billDetailsTable.getColumns().setAll(productNameCol, boughtQuantityCol, buyingPriceCol);

        billDetailsTable.setItems(FXCollections.observableList(supply.getBoughtProducts()));

        Stage secondaryStage = new Stage();
        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.setMaximized(true);
        secondaryStage.setTitle("تفاصيل المشتريات");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(billDetailsTable);



        return borderPane;
    }

    private static Pane getEditSupplierInfoSceneLayout(Supplier supplier) {
        String nodeText;

        nodeText = "أسم المورد";
        Label supplierNameLabel = new Label(nodeText);
        TextField supplierNameTextField = new TextField();
        supplierNameTextField.setPrefSize(200, 20);
        supplierNameTextField.setText(supplier.getSupplierName());

        Label supplierTelephoneLabel = new Label("رقم الهاتف");
        TextField supplierTelephoneTextField = new TextField();
        supplierTelephoneTextField.setPrefSize(200, 20);
        supplierTelephoneTextField.setText(supplier.getSupplierTelephone());

        Label supplierAddressLabel = new Label("العنوان");
        TextField supplierAddressTextField = new TextField();
        supplierAddressTextField.setPrefSize(400, 40);
        supplierAddressTextField.setText(supplier.getSupplierAddress());

        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {

            String message = "";
            messageLabel.setText(message);

            String supplierName = supplierNameTextField.getText().trim();
            String supplierTelephone = supplierTelephoneTextField.getText().trim();
            String supplierAddress = supplierAddressTextField.getText().trim();

            Supplier updatedSupplier = new Supplier(supplier.getSupplierID(), supplierName, supplierTelephone, supplierAddress);
            boolean done = StoreDatabase.updateSupplier(updatedSupplier);

            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات");
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(supplierNameLabel,0,0);
        gridPane.add(supplierNameTextField,1,0);
        gridPane.add(supplierTelephoneLabel, 0,1);
        gridPane.add(supplierTelephoneTextField, 1, 1);
        gridPane.add(supplierAddressLabel, 0, 2);
        gridPane.add(supplierAddressTextField, 1, 2);
        gridPane.add(submitButton,0,3);
        gridPane.add(messageLabel,0,4);

        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });

        Button backButton = new Button("العودة إلي الواجهة السابقة");
        backButton.setPrefSize(180, 30);
        backButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض بيانات الموردين");
            mainStage.getScene().setRoot(getShowSuppliersSceneLayout());
        });

        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().addAll(homeButton, backButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);
        borderPane.setBottom(navigationHBox);


        return borderPane;
    }

    private static Pane getShowProductsSceneLayout() {
        Label productCategoryLabel = new Label("نوع المنتج");
        ChoiceBox productsCategoriesChoiceBox = new ChoiceBox();
        productsCategoriesChoiceBox.setConverter(new StringConverter<ProductCategory>() {
            @Override
            public String toString(ProductCategory c) {
                return c.getCategoryName();
            }

            @Override
            public ProductCategory fromString(String s) {
                return null;
            }
        });
        ArrayList<ProductCategory> productsCategories = StoreDatabase.getAllProductsCategories();
        productsCategoriesChoiceBox.setItems(FXCollections.observableList(productsCategories));

        HBox topHBox = new HBox();

        topHBox.setSpacing(20);
        topHBox.getChildren().add(productCategoryLabel);
        topHBox.getChildren().add(productsCategoriesChoiceBox);

        TableColumn productName = new TableColumn("أسم المنتج");
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));

        TableColumn productAvailableQuantity = new TableColumn("الكمية المتوفرة");
        productAvailableQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("availableQuantity"));

        TableColumn cashSellingPrice = new TableColumn("سعر بيع كاش");
        cashSellingPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("cashSellingPrice"));

        TableColumn instalmentSellingPrice = new TableColumn("سعر بيع تقسيط");
        instalmentSellingPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("instalmentSellingPrice"));

        TableView<Product> productsTable = new TableView();
        productsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productsTable.getColumns().add(productName);
        productsTable.getColumns().add(productAvailableQuantity);
        productsTable.getColumns().add(cashSellingPrice);
        productsTable.getColumns().add(instalmentSellingPrice);

        productsCategoriesChoiceBox.setOnAction(actionEvent -> {
            ProductCategory selectedCategory = (ProductCategory) productsCategoriesChoiceBox.getValue();
            ArrayList<Product> products = StoreDatabase.getProductsInCategory(selectedCategory);
            productsTable.setItems(FXCollections.observableList(products));
        });

        MenuItem editProductOption = new MenuItem("تعديل بيانات المنتج");
        editProductOption.setOnAction(actionEvent -> {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        });

        MenuItem deleteProductOption = new MenuItem("حدف المنتج");
        deleteProductOption.setOnAction(actionEvent -> {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        });

        MenuItem showProductSupplyPrices = new MenuItem("عرض أسعار شراء المنتج");
        showProductSupplyPrices.setOnAction(actionEvent -> {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

        });

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(showProductSupplyPrices);
        contextMenu.getItems().add(editProductOption);
        contextMenu.getItems().add(deleteProductOption);

        productsTable.setContextMenu(contextMenu);


        Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(actionEvent -> {
            mainStage.setTitle("الواجهة الرئيسية");
            mainStage.getScene().setRoot(homeSceneLayout);
        });

        HBox navigationHBox = new HBox();
        navigationHBox.setSpacing(10);
        navigationHBox.getChildren().add(homeButton);
        BorderPane borderPane = new BorderPane();

        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setTop(topHBox);
        borderPane.setCenter(productsTable);
        borderPane.setBottom(navigationHBox);

        return borderPane;
    }

}
