package nubahome.gui;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import java.util.Optional;
import java.util.Stack;


public class GUI {

    private static Stage mainStage;
    private static Pane homeSceneLayout;

    public static void open(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setMaximized(true);

        mainStage.setTitle("تسجيل الدخول");
        homeSceneLayout = getClerkHomeSceneLayout();
        Scene scene = new Scene(getClerkHomeSceneLayout());
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

        Button showTransactionsButton = new Button("عرض سجل المعاملات التجارية");
        showTransactionsButton.setPrefSize(150, 50);
        showTransactionsButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض المبيعات و المشتريات");
            mainStage.getScene().setRoot(getShowTransactionsSceneLayout());
        });

        Button showCurrentMonthInstalmentsButton = new Button("عرض أقساط الشهر الحالي");
        showCurrentMonthInstalmentsButton.setPrefSize(150, 50);
        showCurrentMonthInstalmentsButton.setOnAction(actionEvent -> {
            mainStage.setTitle("عرض أقساط الشهر الحالي");
            mainStage.getScene().setRoot(getShowCurrentMonthInstalments());
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
        gridPane.add(showTransactionsButton, 0, 3);
        gridPane.add(showCurrentMonthInstalmentsButton, 1, 3);

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
        MenuItem editData = new MenuItem("تعديل بيانات العميل");

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

        editData.setOnAction(actionEvent -> {
            Customer selectedCustomer = customersTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("تعديل بيانات عميل");
            mainStage.getScene().setRoot(getEditCustomerDataSceneLayout(selectedCustomer));
        });



        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(addBill, showBills, showInstalments, editData);

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
        MenuItem editData = new MenuItem("تعديل بيانات المورد");


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


        editData.setOnAction(actionEvent -> {
            Supplier selectedSupplier = suppliersTable.getSelectionModel().getSelectedItem();
            mainStage.setTitle("تعديل بيانات مورد");
            mainStage.getScene().setRoot(getEditSupplierDataSceneLayout(selectedSupplier));
        });


        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().addAll(addSupply, showSupplies, editData);

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

        Label additionalFeesLabel = new Label( "مصاريف إضافية");
        TextField additionalFeesTextField = new TextField();
        additionalFeesTextField.setPrefSize(100,20);

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


            Label boughtQuantityLabel = new Label("الكمية المشتراة");
            TextField boughtQuantityTextField = new TextField();
            boughtQuantityTextField.setPrefSize(100,20);


            Label buyingPriceLabel = new Label( "سعر الشراء");
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
            double additionalFees = Double.parseDouble(additionalFeesTextField.getText().trim());


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

            double supplyTotalCost = productsTotalCost + additionalFees;

            Supply supply = new Supply(supplyDate, supplier, additionalFees, productsTotalCost, supplyTotalCost, boughtProducts);
            boolean done = StoreDatabase.addSupply(supply);


            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات");

        });

        addNewProductButton.setOnAction(actionEvent -> {
            Pane addProductSceneLayout = getAddProductSceneLayout();
            //remove the navigation hBox which is the last node in the pane
            int lastNodeIndex = addProductSceneLayout.getChildren().size() -1;
            addProductSceneLayout.getChildren().remove(lastNodeIndex);

            Scene scene = new Scene(addProductSceneLayout);
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            Stage newStage = new Stage();
            newStage.setTitle("إضافة منتج");
            newStage.setScene(scene);
            newStage.show();
        });
        
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        

        gridPane.add(supplyDateLabel,0,0);
        gridPane.add(supplyDatePicker, 1,0);
        gridPane.add(supplierNameLabel,0,1);
        gridPane.add(additionalFeesLabel,0,2);
        gridPane.add(additionalFeesTextField,1,2);
        gridPane.add(addNewProductButton, 0, 3);
        gridPane.add(productsVBox,0,4);
        gridPane.add(addMoreProductsButton,0,5);
        gridPane.add(submitButton,0,6);
        gridPane.add(messageLabel,0,7);

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

    private static Pane getEditCustomerDataSceneLayout(Customer customer) {
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
        Label billsOfCustomerLabel = new Label("فواتير عميل: " + customer.getCustomerName());

        TableColumn billID = new TableColumn("مسلسل الفاتورة");
        TableColumn billDate = new TableColumn("تاريخ الفاتورة");
        TableColumn billTotalCost = new TableColumn("التكلفة الكلية");
        TableColumn paymentMethod = new TableColumn("وسيلة الدفع");

        billID.setCellValueFactory( new PropertyValueFactory<Bill, Integer>("billID"));
        billDate.setCellValueFactory( new PropertyValueFactory<Bill, Date>("billDate"));
        billTotalCost.setCellValueFactory( new PropertyValueFactory<Bill, Double>("billTotalCost"));
        paymentMethod.setCellValueFactory(new PropertyValueFactory<Bill, String>("paymentMethod"));

        TableView<Bill> billsTable = new TableView();
        billsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        billsTable.getColumns().add(billID);
        billsTable.getColumns().add(billDate);
        billsTable.getColumns().add(billTotalCost);
        billsTable.getColumns().add(paymentMethod);

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

            Scene scene = new Scene(getEditBillSceneLayout(selectedBill));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

            Stage newStage = new Stage();
            newStage.setTitle("تعديل فاتورة");
            newStage.setScene(scene);
            newStage.show();

        });

        MenuItem deleteBillOption = new MenuItem("حذف الفاتورة");
        deleteBillOption.setOnAction(actionEvent -> {
            Bill selectedBill = billsTable.getSelectionModel().getSelectedItem();

            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setResizable(true);
            deleteAlert.getDialogPane().getScene().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            deleteAlert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            deleteAlert.setTitle("تأكيد حذف الفاتورة");
            deleteAlert.setHeaderText(null);
            deleteAlert.setContentText("هل أنت متأكد من حذف الفاتورة؟");
            ButtonType yesButtonType = new ButtonType("نعم");
            ButtonType noButtonType = new ButtonType("لا", ButtonBar.ButtonData.CANCEL_CLOSE);

            deleteAlert.getButtonTypes().setAll(yesButtonType, noButtonType);

            Optional<ButtonType> result = deleteAlert.showAndWait();
            if(result.get() == yesButtonType)
            {
                boolean done = StoreDatabase.deleteBill(selectedBill);
                if(done)
                {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
                    successAlert.getDialogPane().getScene().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    successAlert.setResizable(true);
                    successAlert.setTitle("تم الحذف");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("تم حذف جميع بيانات الفاتورة بنجاح!");

                    result = successAlert.showAndWait();
                    if(result.get() == ButtonType.OK)
                        mainStage.getScene().setRoot(getShowCustomerBillsSceneLayout(customer));

                }
                else
                    System.out.println("false");
            }

        });


        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(showSoldProductsOption);

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
        borderPane.setTop(billsOfCustomerLabel);
        borderPane.setCenter(billsTable);
        borderPane.setBottom(navigationHBox);

        return borderPane;
    }

    private static Pane getShowSupplierSuppliesSceneLayout(Supplier supplier) {
        Label suppliesOfSupplierLabel = new Label("فواتير مورد: " + supplier.getSupplierName());

        TableColumn supplyID = new TableColumn("مسلسل التوريد");
        TableColumn supplyDate = new TableColumn("تاريخ التوريد");
        TableColumn additionalFees = new TableColumn("مصاريف إضافية");
        TableColumn productsTotalCost = new TableColumn("تكلفة المنتجات");
        TableColumn supplyTotalCost = new TableColumn("التكلفة الكاملة");

        supplyID.setCellValueFactory(new PropertyValueFactory<Supply, Integer>("supplyID"));
        supplyDate.setCellValueFactory(new PropertyValueFactory<Supply, String>("supplyDate"));
        productsTotalCost.setCellValueFactory(new PropertyValueFactory<Supply, Double>("productsTotalCost"));
        additionalFees.setCellValueFactory(new PropertyValueFactory<Supply, Double>("additionalFees"));
        supplyTotalCost.setCellValueFactory(new PropertyValueFactory<Supply, Double>("supplyTotalCost"));

        TableView<Supply> suppliesTable = new TableView();
        suppliesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        suppliesTable.getColumns().add(supplyID);
        suppliesTable.getColumns().add(supplyDate);
        suppliesTable.getColumns().add(productsTotalCost);
        suppliesTable.getColumns().add(additionalFees);
        suppliesTable.getColumns().add(supplyTotalCost);

        
        ArrayList<Supply> supplierSupplies = StoreDatabase.getSupplierSupplies(supplier);
        suppliesTable.setItems(FXCollections.observableList(supplierSupplies));

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

            Scene scene = new Scene(getEditSupplySceneLayout(selectedSupply));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

            Stage newStage = new Stage();
            newStage.setTitle("تعديل فاتورة");
            newStage.setScene(scene);
            newStage.show();

        });

        MenuItem deleteSupplyOption = new MenuItem("حذف الفاتورة");
        deleteSupplyOption.setOnAction(actionEvent -> {
            Supply selectedSupply = suppliesTable.getSelectionModel().getSelectedItem();

            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
            deleteAlert.setResizable(true);
            deleteAlert.getDialogPane().getScene().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            deleteAlert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            deleteAlert.setTitle("تأكيد حذف الفاتورة");
            deleteAlert.setHeaderText(null);
            deleteAlert.setContentText("هل أنت متأكد من حذف الفاتورة؟");
            ButtonType yesButtonType = new ButtonType("نعم");
            ButtonType noButtonType = new ButtonType("لا", ButtonBar.ButtonData.CANCEL_CLOSE);

            deleteAlert.getButtonTypes().setAll(yesButtonType, noButtonType);

            Optional<ButtonType> result = deleteAlert.showAndWait();
            if(result.get() == yesButtonType)
            {
                boolean done = StoreDatabase.deleteSupply(selectedSupply);
                if(done)
                {
                    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                    successAlert.getDialogPane().setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
                    successAlert.getDialogPane().getScene().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    successAlert.setResizable(true);
                    successAlert.setTitle("تم الحذف");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("تم حذف جميع بيانات الفاتورة بنجاح!");

                    result = successAlert.showAndWait();
                    if(result.get() == ButtonType.OK)
                        mainStage.getScene().setRoot(getShowSupplierSuppliesSceneLayout(supplier));
                }
                else
                    System.out.println("false");
            }

        });


        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(showBoughtProductsOption);
        contextMenu.getItems().add(editSupplyOption);
        contextMenu.getItems().add(deleteSupplyOption);

        suppliesTable.setContextMenu(contextMenu);

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
        borderPane.setTop(suppliesOfSupplierLabel);
        borderPane.setCenter(suppliesTable);
        borderPane.setBottom(navigationHBox);

        return borderPane;
    }

    private static Pane getShowCustomerInstalmentsSceneLayout(Customer customer) {
        Label showInstalmentsLabel = new Label("أعرض بيانات");

        ArrayList<String> options = new ArrayList<>();
        options.add("الأقساط الغير مدفوعة");
        options.add("الأقساط المدفوعة بالكامل");
        options.add("كل الأقساط");
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
        indexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(instalmentsTable.getItems().indexOf(column.getValue())+1));

        instalmentsTable.getColumns().addAll(indexColumn, dueDateColumn, paymentDateColumn, paidMoneyColumn, remainingMoneyColumn, instalmentStateColumn);

        optionsChoiceBox.setOnAction(actionEvent -> {
            String selectedOption = optionsChoiceBox.getValue();
            ArrayList<Instalment> instalments;

            if(selectedOption.equals("الأقساط غير المدفوعة"))
                instalments = StoreDatabase.getCustomerUnpaidInstalments(customer);
            else if(selectedOption.equals("الأقساط المدفوعة بالكامل"))
                instalments = StoreDatabase.getCustomerPaidInstalments(customer);
            else
                instalments = StoreDatabase.getCustomerAllInstalments(customer);

            instalmentsTable.setItems(FXCollections.observableList(instalments));
        });

        MenuItem payInstalmentOption = new MenuItem("دفع القسط");
        MenuItem showInstalmentPaymentsOption = new MenuItem("عرض بيانات المدفوع من القسط");

        payInstalmentOption.setOnAction(actionEvent -> {
            Instalment selectedInstalment = instalmentsTable.getSelectionModel().getSelectedItem();
            Stage newStage = new Stage();
            newStage.setTitle("دفع قسط");
            Scene scene = new Scene(getAddInstalmentPaymentSceneLayout(selectedInstalment));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            newStage.setScene(scene);
            newStage.show();
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
        borderPane.setTop(optionsChoiceBox);
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

        indexColumn.setCellValueFactory(column -> new ReadOnlyObjectWrapper<>(instalmentPaymentsTable.getItems().indexOf(column.getValue())+1));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        paidMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("paidMoney"));

        instalmentPaymentsTable.getColumns().addAll(indexColumn, paymentDateColumn, paidMoneyColumn);

        ContextMenu contextMenu = new ContextMenu();
        MenuItem editOption = new MenuItem("تعديل");
        editOption.setOnAction(actionEvent -> {
            InstalmentPayment instalmentPayment = instalmentPaymentsTable.getSelectionModel().getSelectedItem();
            Stage newStage = new Stage();
            newStage.setTitle("تعديل مدفوعات قسط");
            Scene scene = new Scene(getEditInstalmentPaymentSceneLayout(instalmentPayment));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            newStage.setScene(scene);
            newStage.show();
        });

        contextMenu.getItems().addAll(editOption);
        
        instalmentPaymentsTable.setContextMenu(contextMenu);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(instalmentPaymentsTable);


        return borderPane;
    }

    private static Pane getAddBillToCustomerSceneLayout(Customer customer) {
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
        ChoiceBox<String> paymentMethodChoiceBox = new ChoiceBox<>();
        ArrayList<String> paymentMethods = new ArrayList<>();
        paymentMethods.add("كاش");
        paymentMethods.add("تقسيط");
        paymentMethodChoiceBox.setItems(FXCollections.observableList(paymentMethods));

        nodeText = "أسم الضامن";
        Label guarantorNameLabel = new Label(nodeText);
        ChoiceBox<Customer> guarantorChoiceBox = new ChoiceBox<>();
        ArrayList<Customer> customers = StoreDatabase.getAllCustomers();
        guarantorChoiceBox.setItems(FXCollections.observableList(customers));
        guarantorChoiceBox.setConverter(new StringConverter<Customer>() {
            @Override
            public String toString(Customer c) {
                return c.getCustomerName();
            }

            @Override
            public Customer fromString(String s) {
                return null;
            }
        });

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

       
        
        GridPane instalmentsDetailsGridPane = new GridPane();
        instalmentsDetailsGridPane.setHgap(15);
        instalmentsDetailsGridPane.setVgap(15);
        

        instalmentsDetailsGridPane.add(guarantorNameLabel, 0, 0);
        instalmentsDetailsGridPane.add(guarantorChoiceBox, 1, 0);
        instalmentsDetailsGridPane.add(initialPaymentLabel, 0,1);
        instalmentsDetailsGridPane.add(initialPaymentTextField, 1,1);
        instalmentsDetailsGridPane.add(instalmentAmountLabel, 0,2);
        instalmentsDetailsGridPane.add(instalmentAmountTextField, 1, 2);
        instalmentsDetailsGridPane.add(remainingInstalmentsNumberLabel, 0,3);
        instalmentsDetailsGridPane.add(remainingInstalmentsNumberTextField, 1, 3);
        instalmentsDetailsGridPane.add(firstInstalmentDateLabel, 0, 4);
        instalmentsDetailsGridPane.add(startDatePicker, 1, 4);
        instalmentsDetailsGridPane.add(lastInstalmentDateLabel, 0, 5);
        instalmentsDetailsGridPane.add(endDatePicker, 1, 5);

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
            String paymentMethod = paymentMethodChoiceBox.getValue();


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


            Bill bill;
            if(paymentMethod.equals("كاش"))
                bill = new Bill(billDate, buyer, productsTotalCost, billTotalCost, paymentMethod, soldProducts);
            else
            {
                Customer guarantor = guarantorChoiceBox.getValue();
                double initialPayment = Double.parseDouble(initialPaymentTextField.getText().trim());
                double remainingMoney = billTotalCost - initialPayment;
                double instalmentAmount = Double.parseDouble(instalmentAmountTextField.getText().trim());
                String firstInstalmentDate = startDatePicker.getValue().toString();
                String lastInstalmentDate = endDatePicker.getValue().toString();
                int remainingInstalmentsNumber = Integer.parseInt(remainingInstalmentsNumberTextField.getText().trim());
                BillInstalmentsDetails billInstalmentsDetails = new BillInstalmentsDetails(guarantor, initialPayment, remainingMoney, instalmentAmount, firstInstalmentDate, lastInstalmentDate, remainingInstalmentsNumber);
                bill = new Bill(billDate, buyer, productsTotalCost, billTotalCost, paymentMethod, soldProducts, billInstalmentsDetails);
            }
            
            
            boolean done = StoreDatabase.addBill(bill);

            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات!");

        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
    
        paymentMethodChoiceBox.setOnAction(actionEvent -> {
            if(paymentMethodChoiceBox.getValue().equals("تقسيط"))
            {
                gridPane.getChildren().removeAll(productsVBox, addMoreProductsButton, submitButton, messageLabel);

                gridPane.add(instalmentsDetailsGridPane, 0, 3);
                gridPane.add(productsVBox,0,4);
                gridPane.add(addMoreProductsButton,0,5);
                gridPane.add(submitButton,0,6);
                gridPane.add(messageLabel,0,7);

            }
            else if(paymentMethodChoiceBox.getValue().equals("كاش"))
            {
                gridPane.getChildren().removeAll(instalmentsDetailsGridPane, productsVBox, addMoreProductsButton, submitButton, messageLabel);

                gridPane.add(productsVBox,0,3);
                gridPane.add(addMoreProductsButton,0,4);
                gridPane.add(submitButton,0,5);
                gridPane.add(messageLabel,0,6);;
            }
        });

        gridPane.add(billDateLabel,0,0);
        gridPane.add(billDatePicker,1,0);
        gridPane.add(buyerNameLabel,0,1);
        gridPane.add(paymentMethodLabel,0,2);
        gridPane.add(paymentMethodChoiceBox,1,2);



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
            if(done)
            {
                Instalment updatedInstalment;
                if(paidMoney.equals(instalment.getRemainingMoney()))
                    updatedInstalment = new Instalment(instalment.getInstalmentID(), instalment.getBillID(), instalment.getPaidMoney() + paidMoney, 0, instalment.getInstalmentDueDate(), paymentDate, "مدفوع بالكامل");
                else
                    updatedInstalment = new Instalment(instalment.getInstalmentID(), instalment.getBillID(), instalment.getPaidMoney() + paidMoney, instalment.getRemainingMoney() - paidMoney, instalment.getInstalmentDueDate(), "", "مدفوع جزئيا");

                done = StoreDatabase.updateInstalment(updatedInstalment);
            }
            messageLabel.setText("");
            if(!done)
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات!");
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

        TableView<SoldProduct> soldProductsTable = new TableView<>();
        soldProductsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        soldProductsTable.getColumns().setAll(productNameCol, soldQuantityCol, sellingPriceCol);

        soldProductsTable.setItems(FXCollections.observableList(bill.getSoldProducts()));

        MenuItem editOption = new MenuItem("تعديل");
        editOption.setOnAction(actionEvent -> {
            SoldProduct selectedSoldProducts = soldProductsTable.getSelectionModel().getSelectedItem();

            Scene scene = new Scene(getEditSoldProductDataSceneLayout(bill, selectedSoldProducts));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            Stage newStage = new Stage();
            newStage.setTitle("تعديل بيانات منتج مباع");
            newStage.setScene(scene);
            newStage.show();
        });

        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(editOption);
        soldProductsTable.setContextMenu(contextMenu);
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(soldProductsTable);


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

        TableView<BoughtProduct> boughtProductsTable = new TableView<>();
        boughtProductsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        boughtProductsTable.getColumns().setAll(productNameCol, boughtQuantityCol, buyingPriceCol);

        boughtProductsTable.setItems(FXCollections.observableList(supply.getBoughtProducts()));
        
        MenuItem editOption = new MenuItem("تعديل");
        editOption.setOnAction(actionEvent -> {
            BoughtProduct selectedBoughtProduct = boughtProductsTable.getSelectionModel().getSelectedItem();
            
            Scene scene = new Scene(getEditBoughtProductDataSceneLayout(supply, selectedBoughtProduct));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            Stage newStage = new Stage();
            newStage.setTitle("تعديل بيانات منتج مشتري");
            newStage.setScene(scene);
            newStage.show();
        });
        
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(editOption);
        boughtProductsTable.setContextMenu(contextMenu);
        
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(boughtProductsTable);

        return borderPane;
    }

    private static Pane getEditSupplierDataSceneLayout(Supplier supplier) {
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

        MenuItem showProductSupplyPrices = new MenuItem("عرض أسعار شراء المنتج");
        showProductSupplyPrices.setOnAction(actionEvent -> {
            Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

            Scene scene = new Scene(getShowProductSuppliesSceneLayout(selectedProduct));
            scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            Stage newStage = new Stage();
            newStage.setTitle("عرض أسعار شراء منتج");
            newStage.setScene(scene);
            newStage.show();
        });


        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(showProductSupplyPrices);

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

    private static Pane getShowProductSuppliesSceneLayout(Product product) {
        Label productNameLabel = new Label("أسم المنتج: " + product.getProductName());
        
        TableColumn<ProductSupply, Double> productPriceColumn = new TableColumn<>("سعر المنتج");
        TableColumn<ProductSupply, String> supplierNameColumn = new TableColumn<>("أسم المورد");
        TableColumn<ProductSupply, String> supplyDateColumn = new TableColumn<>("تاريخ الشراء");

        productPriceColumn.setCellValueFactory(cellData -> {
            ProductSupply supply = ((ProductSupply)((TableColumn.CellDataFeatures)cellData).getValue());
            return new SimpleObjectProperty<>(supply.getBuyingPrice());
        });

        supplierNameColumn.setCellValueFactory(cellData -> {
            ProductSupply supply = ((ProductSupply)((TableColumn.CellDataFeatures)cellData).getValue());
            return new SimpleStringProperty(supply.getSupplier().getSupplierName());
        });

        supplyDateColumn.setCellValueFactory(cellData -> {
            ProductSupply supply = ((ProductSupply)((TableColumn.CellDataFeatures)cellData).getValue());
            return new SimpleStringProperty(supply.getSupplyDate());
        });

        TableView<ProductSupply> productSuppliesTable = new TableView<>();
        productSuppliesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productSuppliesTable.getColumns().addAll(productPriceColumn, supplierNameColumn, supplyDateColumn);

        ArrayList<ProductSupply> productSupplies = StoreDatabase.getProductSupplies(product);
        productSuppliesTable.setItems(FXCollections.observableList(productSupplies));
        
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.getChildren().addAll(productNameLabel, productSuppliesTable);
        
        return vBox;
    }
    
    private static Pane getEditBillSceneLayout(Bill bill) {
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
        billDatePicker.setValue(LocalDate.parse(bill.getBillDate()));

        nodeText = "أسم العميل: " + bill.getBuyer().getCustomerName();
        Label buyerNameLabel = new Label(nodeText);



        nodeText = "وسيلة الدفع: " + bill.getPaymentMethod();
        Label paymentMethodLabel = new Label(nodeText);


        Label guarantorNameLabel = new Label();

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

        if(bill.getPaymentMethod().equals("تقسيط"))
        {
            guarantorNameLabel.setText("أسم الضامن: " + bill.getBillInstalmentsDetails().getGuarantor().getCustomerName());
            initialPaymentTextField.setText(bill.getBillInstalmentsDetails().getInitialPayment() + "");
            instalmentAmountTextField.setText(bill.getBillInstalmentsDetails().getInstalmentAmount() + "");
            remainingInstalmentsNumberTextField.setText(bill.getBillInstalmentsDetails().getRemainingInstalmentsNumber() + "");
            startDatePicker.setValue(LocalDate.parse(bill.getBillInstalmentsDetails().getFirstInstalmentDate()));
            endDatePicker.setValue(LocalDate.parse(bill.getBillInstalmentsDetails().getLastInstalmentDate()));
        }

        GridPane instalmentsDetailsGridPane = new GridPane();
        instalmentsDetailsGridPane.setHgap(15);
        instalmentsDetailsGridPane.setVgap(15);


        instalmentsDetailsGridPane.add(guarantorNameLabel, 0, 0);
        instalmentsDetailsGridPane.add(initialPaymentLabel, 0,1);
        instalmentsDetailsGridPane.add(initialPaymentTextField, 1,1);
        instalmentsDetailsGridPane.add(instalmentAmountLabel, 0,2);
        instalmentsDetailsGridPane.add(instalmentAmountTextField, 1, 2);
        instalmentsDetailsGridPane.add(remainingInstalmentsNumberLabel, 0,3);
        instalmentsDetailsGridPane.add(remainingInstalmentsNumberTextField, 1, 3);
        instalmentsDetailsGridPane.add(firstInstalmentDateLabel, 0, 4);
        instalmentsDetailsGridPane.add(startDatePicker, 1, 4);
        instalmentsDetailsGridPane.add(lastInstalmentDateLabel, 0, 5);
        instalmentsDetailsGridPane.add(endDatePicker, 1, 5);


        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
        Label messageLabel = new Label();
        submitButton.setOnAction(event -> {

            String message = "";
            messageLabel.setText(message);

            String billDate = billDatePicker.getValue().toString();
            Customer buyer = bill.getBuyer();
            String paymentMethod = bill.getPaymentMethod();

            ArrayList<SoldProduct> soldProducts = bill.getSoldProducts();
            double billTotalCost = bill.getBillTotalCost();
            double productsTotalCost = bill.getProductsTotalCost();

            Bill updatedBill;
            if(paymentMethod.equals("كاش"))
                updatedBill = new Bill(bill.getBillID(), billDate, buyer, productsTotalCost, billTotalCost, paymentMethod, soldProducts);
            else
            {
                Customer guarantor = bill.getBillInstalmentsDetails().getGuarantor();
                double initialPayment = Double.parseDouble(initialPaymentTextField.getText().trim());
                double remainingMoney = billTotalCost - initialPayment;
                double instalmentAmount = Double.parseDouble(instalmentAmountTextField.getText().trim());
                String firstInstalmentDate = startDatePicker.getValue().toString();
                String lastInstalmentDate = endDatePicker.getValue().toString();
                int remainingInstalmentsNumber = Integer.parseInt(remainingInstalmentsNumberTextField.getText().trim());
                BillInstalmentsDetails billInstalmentsDetails = new BillInstalmentsDetails(guarantor, initialPayment, remainingMoney, instalmentAmount, firstInstalmentDate, lastInstalmentDate, remainingInstalmentsNumber);
                updatedBill = new Bill(bill.getBillID(), billDate, buyer, productsTotalCost, billTotalCost, paymentMethod, soldProducts, billInstalmentsDetails);
            }

            boolean done = StoreDatabase.updateBill(updatedBill);

            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات!");

        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);


        gridPane.add(billDateLabel,0,0);
        gridPane.add(billDatePicker,1,0);
        gridPane.add(buyerNameLabel,0,1);
        gridPane.add(paymentMethodLabel,0,2);
        if(bill.getPaymentMethod().equals("تقسيط"))
        {
            gridPane.add(instalmentsDetailsGridPane, 0, 3);
            gridPane.add(submitButton,0,4);
            gridPane.add(messageLabel,0,5);

        }
        else
        {
            gridPane.add(submitButton,0,3);
            gridPane.add(messageLabel,0,4);;
        }


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getEditSupplySceneLayout(Supply supply) {
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
        supplyDatePicker.setValue(LocalDate.parse(supply.getSupplyDate()));

        Label supplierNameLabel = new Label("أسم المورد: " + supply.getSupplier().getSupplierName());


        Label additionalFeesLabel = new Label( "مصاريف إضافية");
        TextField additionalFeesTextField = new TextField();
        additionalFeesTextField.setPrefSize(100,20);
        additionalFeesTextField.setText(supply.getAdditionalFees() + "");
        
        
        submitButton.setOnAction(event -> {

            String message = "";
            messageLabel.setText(message);

            String supplyDate = supplyDatePicker.getValue().toString();
            Supplier supplier = supply.getSupplier();
            double additionalFees = Double.parseDouble(additionalFeesTextField.getText().trim());


            ArrayList<BoughtProduct> boughtProducts = supply.getBoughtProducts();
            double productsTotalCost = supply.getProductsTotalCost();

            double supplyTotalCost = productsTotalCost + additionalFees;

            Supply updatedSupply = new Supply(supply.getSupplyID() ,supplyDate, supplier, additionalFees, productsTotalCost, supplyTotalCost, boughtProducts);
            boolean done = StoreDatabase.updateSupply(updatedSupply);


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

        gridPane.add(supplyDateLabel,0,0);
        gridPane.add(supplyDatePicker, 1,0);
        gridPane.add(supplierNameLabel,0,1);
        gridPane.add(additionalFeesLabel,0,2);
        gridPane.add(additionalFeesTextField,1,2);
        gridPane.add(submitButton,0,3);
        gridPane.add(messageLabel,0,4);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);



        return borderPane;
    }
    
    private static Pane getEditBoughtProductDataSceneLayout(Supply supply, BoughtProduct boughtProduct) {
        Label productNameLabel = new Label("أسم المنتج: " + boughtProduct.getProductName());
        
        Label buyingPriceLabel = new Label("سعر الشراء");
        TextField buyingPriceTextField = new TextField();
        buyingPriceTextField.setPrefSize(100, 20);
        buyingPriceTextField.setText(boughtProduct.getBuyingPrice() + "");
        
        Label boughtQuantityLabel = new Label("الكمية المشتراة");
        TextField boughtQuantityTextField = new TextField();
        boughtQuantityTextField.setPrefSize(100, 20);
        boughtQuantityTextField.setText(boughtProduct.getBoughtQuantity() + "");
        
        Label messageLabel = new Label("");
        
        Button submitButton = new Button("أدخل");
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(actionEvent -> {
            double buyingPrice = Double.parseDouble(buyingPriceTextField.getText().trim());
            int boughtQuantity = Integer.parseInt(boughtQuantityTextField.getText().trim());
            
            double oldProductTotalCost = boughtProduct.getBoughtQuantity() * boughtProduct.getBuyingPrice();
            double newProductTotalCost = boughtQuantity * buyingPrice;
            
            double updatedProductsTotalCost;
            if(newProductTotalCost > oldProductTotalCost)
                updatedProductsTotalCost = supply.getProductsTotalCost() + (newProductTotalCost - oldProductTotalCost);
            else
                updatedProductsTotalCost = supply.getProductsTotalCost() - (oldProductTotalCost - newProductTotalCost);
            
            Product product = new Product(boughtProduct.getProductID(), boughtProduct.getProductName(), boughtProduct.getCategory(), boughtProduct.getAvailableQuantity(), boughtProduct.getSupplyPrice(), boughtProduct.getLastSupplyDate(), boughtProduct.getCashSellingPrice(), boughtProduct.getInstalmentSellingPrice());
            boolean done = StoreDatabase.updateBoughtProduct(supply, new BoughtProduct(product, boughtQuantity, buyingPrice), updatedProductsTotalCost);
            messageLabel.setText("");
            if(done)
                messageLabel.setText("تم إدخال البيانات بنجاح!");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات!");
        });
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        
        gridPane.add(productNameLabel, 0, 0);
        gridPane.add(boughtQuantityLabel, 0, 1);
        gridPane.add(boughtQuantityTextField, 1, 1);
        gridPane.add(buyingPriceLabel, 0, 2);
        gridPane.add(buyingPriceTextField, 1, 2);
        gridPane.add(submitButton, 0, 3);
        gridPane.add(messageLabel, 0, 4);
        
        return gridPane;
    }

    private static Pane getEditSoldProductDataSceneLayout(Bill bill, SoldProduct soldProduct) {
        Label productNameLabel = new Label("أسم المنتج: " + soldProduct.getProductName());

        Label sellingPriceLabel = new Label("سعر البيع");
        TextField sellingPriceTextField = new TextField();
        sellingPriceTextField.setPrefSize(100, 20);
        sellingPriceTextField.setText(soldProduct.getSellingPrice() + "");

        Label soldQuantityLabel = new Label("الكمية المباعة");
        TextField soldQuantityTextField = new TextField();
        soldQuantityTextField.setPrefSize(100, 20);
        soldQuantityTextField.setText(soldProduct.getSoldQuantity() + "");

        Label messageLabel = new Label("");

        Button submitButton = new Button("أدخل");
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(actionEvent -> {
            double sellingPrice = Double.parseDouble(sellingPriceTextField.getText().trim());
            int soldQuantity = Integer.parseInt(soldQuantityTextField.getText().trim());
            
            double oldProductTotalCost = soldProduct.getSoldQuantity() * soldProduct.getSellingPrice();
            double newProductTotalCost = soldQuantity * sellingPrice;

            double updatedProductsTotalCost;
            if(newProductTotalCost > oldProductTotalCost)
                updatedProductsTotalCost = bill.getProductsTotalCost() + (newProductTotalCost - oldProductTotalCost);
            else
                updatedProductsTotalCost = bill.getProductsTotalCost() - (oldProductTotalCost - newProductTotalCost);
            
            Product product = new Product(soldProduct.getProductID(), soldProduct.getProductName(), soldProduct.getCategory(), soldProduct.getAvailableQuantity(), soldProduct.getSupplyPrice(), soldProduct.getLastSupplyDate(), soldProduct.getCashSellingPrice(), soldProduct.getInstalmentSellingPrice());
            boolean done = StoreDatabase.updateSoldProduct(bill, new SoldProduct(product, soldQuantity, sellingPrice), updatedProductsTotalCost);

            messageLabel.setText("");
            if(done)
                messageLabel.setText("تم إدخال البيانات بنجاح!");
            else
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات!");
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(15);
        gridPane.setVgap(15);

        gridPane.add(productNameLabel, 0, 0);
        gridPane.add(soldQuantityLabel, 0, 1);
        gridPane.add(soldQuantityTextField, 1, 1);
        gridPane.add(sellingPriceLabel, 0, 2);
        gridPane.add(sellingPriceTextField, 1, 2);
        gridPane.add(submitButton, 0, 3);
        gridPane.add(messageLabel, 0, 4);

        return gridPane;
    }

    private static Pane getShowTransactionsSceneLayout() {
        Label monthLabel  = new Label("شهر: ");
        ChoiceBox<String> monthChoiceBox = new ChoiceBox<>();
        ArrayList<String> months = new ArrayList<>();
        months.add("يناير");
        months.add("فبراير");
        months.add("مارس");
        months.add("أبريل");
        months.add("مايو");
        months.add("يونيو");
        months.add("يوليو");
        months.add("أغسطس");
        months.add("سبتمبر");
        months.add("أكتوبر");
        months.add("نوفمبر");
        months.add("ديسمبر");
        monthChoiceBox.setItems(FXCollections.observableList(months));

        Label yearLabel = new Label("سنة: ");
        ChoiceBox<String> yearChoiceBox  = new ChoiceBox<>();
        ArrayList<String> years = new ArrayList<>();
        years.add("2018");
        years.add("2019");
        years.add("2020");
        years.add("2021");
        years.add("2022");
        yearChoiceBox.setItems(FXCollections.observableList(years));

        Button showButton = new Button("أعرض");
        showButton.setPrefSize(50, 30);

        HBox topHBox = new HBox();
        topHBox.setSpacing(10);
        topHBox.getChildren().add(monthLabel);
        topHBox.getChildren().add(monthChoiceBox);
        topHBox.getChildren().add(yearLabel);
        topHBox.getChildren().add(yearChoiceBox);
        topHBox.getChildren().add(showButton);

        TableView<Transaction> transactionsTable = new TableView<>();
        transactionsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Transaction, String> transactionDateColumn = new TableColumn<>("تاريخ");
        transactionDateColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));

        TableColumn<Transaction, String> transactionSourceNameColumn = new TableColumn<>("أسم");
        transactionSourceNameColumn.setCellValueFactory(new PropertyValueFactory<>("transactionSourceName"));

        TableColumn<Transaction, Double> transactionCostColumn = new TableColumn<>("تكلفة");
        transactionCostColumn.setCellValueFactory(new PropertyValueFactory<>("transactionCost"));

        TableColumn<Transaction, String> transactionTypeColumn = new TableColumn<>("نوع");
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));

        transactionsTable.getColumns().addAll(transactionDateColumn, transactionSourceNameColumn, transactionCostColumn, transactionTypeColumn);


        showButton.setOnAction(actionEvent -> {
            int selectedMonth = monthChoiceBox.getSelectionModel().getSelectedIndex() + 1;
            String selectedYear = yearChoiceBox.getValue();
            
            ArrayList<Transaction> transactions = StoreDatabase.getTransactions(selectedMonth, selectedYear);
            transactionsTable.setItems(FXCollections.observableList(transactions));
        });

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
        borderPane.setCenter(transactionsTable);
        borderPane.setBottom(navigationHBox);

        return borderPane;
    }

    private static Pane getShowCurrentMonthInstalments() {
        TableColumn<InstalmentSummary, Number> indexColumn = new TableColumn<InstalmentSummary, Number>("رقم القسط");
        TableColumn<InstalmentSummary, String> customerNameColumn = new TableColumn<>("أسم العميل");
        TableColumn<InstalmentSummary, String> dueDateColumn = new TableColumn<>("تاريخ الإستحقاق");
        TableColumn<InstalmentSummary, Double> remainingMoneyColumn = new TableColumn<>("الباقي");

        TableView<InstalmentSummary> instalmentsTable = new TableView<>();
        instalmentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        indexColumn.setCellValueFactory(column-> new ReadOnlyObjectWrapper<>(instalmentsTable.getItems().indexOf(column.getValue())+1));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        remainingMoneyColumn.setCellValueFactory(new PropertyValueFactory<>("remainingMoney"));


        instalmentsTable.getColumns().addAll(indexColumn, customerNameColumn, dueDateColumn, remainingMoneyColumn);

        ArrayList<InstalmentSummary> instalmentSummaries = StoreDatabase.getCurrentMonthInstalmentSummaries();
        instalmentsTable.setItems(FXCollections.observableList(instalmentSummaries));

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
        borderPane.setCenter(instalmentsTable);
        borderPane.setBottom(navigationHBox);

        return borderPane;
    }
}
