package nubahome.gui;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import nubahome.databse.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import java.util.concurrent.TimeUnit;


public class GUI {

    private static Stage mainStage;

    public static void open(Stage primaryStage) {

        mainStage = primaryStage;
        mainStage.setMaximized(true);

        String sceneTitle = "تسجيل الدخول";
        mainStage.setTitle(sceneTitle);
        Scene loginScene = new Scene(getClerkHomeSceneLayout());
        loginScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        mainStage.setScene(loginScene);
        mainStage.show();
    }

    private static Pane getLoginLayout() {

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

            //ToDo: complete the login function
            String userName = userNameTextField.getText();
            String password = passwordField.getText();

            boolean isUser =  StoreDatabase.login(userName,password);

            if(!isUser)
                messageLabel.setText("بيانات خاطئة! أعد محاولة الدخول.");
            else {
                Scene homeScene = null;
                UserRole currentUserRole = StoreDatabase.getCurrentUserRole();
                if (currentUserRole.getRoleName().equals("مدير"))
                {
                    System.out.println("مدير");
                    homeScene = new Scene(getManagerHomeSceneLayout());
                }
                else if (currentUserRole.getRoleName().equals("موظف")) {
                    System.out.println("موظف");
                    homeScene = new Scene(getClerkHomeSceneLayout());
                }

                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                userNameTextField.clear();
                passwordField.clear();
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
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

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;


    }

    private static Pane getManagerHomeSceneLayout() {

        String suppliesButtonText = "التوريدات";
        Button suppliesButton = new Button(suppliesButtonText);
        suppliesButton.setPrefSize(150, 50);

        suppliesButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane suppliesLayout = (BorderPane) getManagerSuppliesSceneLayout();
            suppliesLayout.setBottom(hBox);

            Scene suppliesScene = new Scene(suppliesLayout);
            suppliesScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة التوريدات");
            mainStage.setScene(suppliesScene);
            System.gc();
            mainStage.show();
        });

        String billsButtonText = "الفواتير";
        Button billsButton = new Button(billsButtonText);
        billsButton.setPrefSize(150, 50);

        billsButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane billsAndInstalmentLayout = (BorderPane) getManagerBillsSceneLayout();
            billsAndInstalmentLayout.setBottom(hBox);

            Scene billsAndInstalmentScene = new Scene(billsAndInstalmentLayout);
            billsAndInstalmentScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة الفواتير");
            mainStage.setScene(billsAndInstalmentScene);
            System.gc();
            mainStage.show();
        });

        String productsButtonText = "المنتجات";
        Button productsButton = new Button(productsButtonText);
        productsButton.setPrefSize(150, 50);

        productsButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane productsLayout = (BorderPane) getManagerProductsSceneLayout();
            productsLayout.setBottom(hBox);

            Scene productsScene = new Scene(productsLayout);
            productsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة المنتجات");
            mainStage.setScene(productsScene);
            System.gc();
            mainStage.show();
        });

        String SuppliersButtonText = "الموردين";
        Button SuppliersButton = new Button(SuppliersButtonText);
        SuppliersButton.setPrefSize(150, 50);

        SuppliersButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane suppliersLayout = (BorderPane) getManagerSuppliersSceneLayout();
           suppliersLayout.setBottom(hBox);

            Scene suppliersScene = new Scene(suppliersLayout);
            suppliersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة المنتجات");
            mainStage.setScene(suppliersScene);
            System.gc();
            mainStage.show();
        });

        String usersButtonText = "المستخدمين";
        Button usersButton = new Button(usersButtonText);
        usersButton.setPrefSize(150, 50);

        usersButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane usersLayout = (BorderPane) getManagerUsersSceneLayout();
            usersLayout.setBottom(hBox);

            Scene usersScene = new Scene(usersLayout);
            usersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة المستخدمين");
            mainStage.setScene(usersScene);
            System.gc();
            mainStage.show();
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(suppliesButton, 0, 0);
        gridPane.add(usersButton, 1, 0);
        gridPane.add(billsButton, 0, 1);
        gridPane.add(productsButton, 1, 1);
        gridPane.add(SuppliersButton, 0, 2);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        return borderPane;

    }

    private static Pane getManagerUsersSceneLayout() {
        String showUsersButtonText = "عرض المستخدمين";
        Button showUsersButton = new Button(showUsersButtonText);
        showUsersButton.setPrefSize(150, 30);
        showUsersButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();
                BorderPane usersLayout = (BorderPane) getManagerUsersSceneLayout();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                usersLayout.setBottom(hBox);

                Scene usersScene = new Scene(usersLayout);
                usersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة المستخدمين");
                mainStage.setScene(usersScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane showUsersSceneLayout = (BorderPane) getManagerShowUsersSceneLayout();
            showUsersSceneLayout.setBottom(hBox);

            Scene showUsersScene = new Scene(showUsersSceneLayout);
            showUsersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة عرض المستخدمين");
            mainStage.setScene(showUsersScene);
            System.gc();
            mainStage.show();
        });

        String addUserButtonText = "إضافة مستخدم";
        Button addUserButton = new Button(addUserButtonText);
        addUserButton.setPrefSize(150, 30);
        addUserButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();
                BorderPane usersLayout = (BorderPane) getManagerUsersSceneLayout();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                usersLayout.setBottom(hBox);

                Scene usersScene = new Scene(usersLayout);
                usersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة المستخدمين");
                mainStage.setScene(usersScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane addUserLayout = (BorderPane) getManagerAddUserSceneLayout();
            addUserLayout.setBottom(hBox);

            Scene addUserScene = new Scene(addUserLayout);
            addUserScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle( "واجهة إضافة مستخدم");
            mainStage.setScene(addUserScene);
            System.gc();
            mainStage.show();
        });


        String editUserButtonText = "تعديل مستخدم";
        Button editUserButton = new Button(editUserButtonText);
        editUserButton.setPrefSize(150, 30);
        editUserButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();
                BorderPane usersLayout = (BorderPane) getManagerUsersSceneLayout();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                usersLayout.setBottom(hBox);

                Scene usersScene = new Scene(usersLayout);
                usersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة المستخدمين");
                mainStage.setScene(usersScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane editUserLayout = (BorderPane) getManagerEditUserSceneLayout();
            editUserLayout.setBottom(hBox);

            Scene editUserScene = new Scene(editUserLayout);
            editUserScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة تعديل مستخدم");
            mainStage.setScene(editUserScene);
            System.gc();
            mainStage.show();
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(editUserButton, 0, 0);
        gridPane.add(addUserButton, 1, 0);
        gridPane.add(showUsersButton, 2, 0);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;

    }

    private static Pane getManagerEditUserSceneLayout() {
        Label usersLabel = new Label("اختار المستخدم");
        ChoiceBox usersChoiceBox = new ChoiceBox();
        usersChoiceBox.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User u) {
                return u.getUserName();
            }

            @Override
            public User fromString(String s) {
                return null;
            }
        });
        ArrayList<User> users = StoreDatabase.getAllUsers();
        usersChoiceBox.setItems(FXCollections.observableList(users));
        
        usersChoiceBox.setOnAction(actionEvent -> {
            User selectedUser = (User) usersChoiceBox.getValue();
            showEditUser(selectedUser);
        });
        Label messageLabel = new Label();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        

        gridPane.add(usersChoiceBox,1,0);
        gridPane.add(usersLabel,0,0);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerAddUserSceneLayout() {
        String nodeText;

        nodeText = "أسم المستخدم";
        Label userNameLabel = new Label(nodeText);
        TextField userNameTextField = new TextField();
        userNameTextField.setPrefSize(100,20);

        nodeText = "كلمة السر";
        Label userPasswordLabel = new Label(nodeText);
        TextField userPasswordTextField = new TextField();
        userPasswordTextField.setPrefSize(100,20);

        nodeText = "وظيفة المستخدم";
        Label userTypeLabel = new Label(nodeText);
        ChoiceBox usersRolesChoiceBox = new ChoiceBox();
        ArrayList<UserRole> usersRoles = StoreDatabase.getAllUsersRoles();
        usersRolesChoiceBox.setItems(FXCollections.observableList(usersRoles));
        usersRolesChoiceBox.setConverter(new StringConverter<UserRole>() {
            @Override
            public String toString(UserRole role) {
                return role.getRoleName();
            }

            @Override
            public UserRole fromString(String s) {
                return null;
            }
        });



        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
       submitButton.setOnAction(event -> {
            //ToDo: complete the add user function
            String message = "";
            messageLabel.setText(message);

            String userName = userNameTextField.getText().trim();
            String userPassword = userPasswordTextField.getText().trim();
            UserRole userRole = (UserRole) usersRolesChoiceBox.getValue();


            boolean done = StoreDatabase.addUser(new User(userName, userPassword, userRole));
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

        gridPane.add(userNameTextField,1,0);
        gridPane.add(userNameLabel,0,0);
        gridPane.add(userPasswordTextField,1,1);
        gridPane.add(userPasswordLabel,0,1);
        gridPane.add(usersRolesChoiceBox,1,2);
        gridPane.add(userTypeLabel,0,2);
        gridPane.add(submitButton,0,3);
        gridPane.add(messageLabel,0,4);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);



        return borderPane;
    }

    private static Pane getManagerShowUsersSceneLayout() {
        TableColumn userID = new TableColumn("رقم المستخدم");
        TableColumn userName = new TableColumn("أسم المستخدم");
        TableColumn userPassword = new TableColumn("كلمة سر المستخدم");
        TableColumn userRole = new TableColumn("وظيفة المستخدم");

        userID.setCellValueFactory(new PropertyValueFactory<User, Integer>("userID"));
        userName.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        userPassword.setCellValueFactory(new PropertyValueFactory<User, String>("userPassword"));
        userRole.setCellValueFactory(cellData -> {
            User user = (User) ((TableColumn.CellDataFeatures) cellData).getValue();
            return new SimpleStringProperty(user.getUserRole().getRoleName());
        });

        TableView usersTable = new TableView();
        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        usersTable.getColumns().add(userID);
        usersTable.getColumns().add(userName);
        usersTable.getColumns().add(userPassword);
        usersTable.getColumns().add(userRole);


        ArrayList<User> users = StoreDatabase.getAllUsers();
        usersTable.setItems(FXCollections.observableList(users));

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(usersTable);

        return borderPane;
    }

    private static Pane getManagerSuppliesSceneLayout()    {
        String showSuppliesButtonText = "عرض التوريدات";
        Button showSuppliesButton = new Button(showSuppliesButtonText);
        showSuppliesButton.setPrefSize(150, 30);
        showSuppliesButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane suppliesLayout = (BorderPane) getManagerSuppliesSceneLayout();
                suppliesLayout.setBottom(hBox);

                Scene suppliesScene = new Scene(suppliesLayout);
                suppliesScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة التوريدات");
                mainStage.setScene(suppliesScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane showSuppliesSceneLayout = (BorderPane) getManagerShowSuppliesSceneLayout();
            showSuppliesSceneLayout.setBottom(hBox);

            Scene showSuppliesScene = new Scene(showSuppliesSceneLayout);
            showSuppliesScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة عرض التوريدات");
            mainStage.setScene(showSuppliesScene);
            System.gc();
            mainStage.show();
        });


        String addSupplyButtonText = "إضافة توريد";
        Button addSupplyButton = new Button(addSupplyButtonText);
        addSupplyButton.setPrefSize(150, 30);
        addSupplyButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane suppliesLayout = (BorderPane) getManagerSuppliesSceneLayout();
                suppliesLayout.setBottom(hBox);

                Scene suppliesScene = new Scene(suppliesLayout);
                suppliesScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة التوريدات");
                mainStage.setScene(suppliesScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);


            BorderPane addSupplyLayout = (BorderPane) getManagerAddSupplySceneLayout();
            addSupplyLayout.setBottom(hBox);

            Scene addSupplyScene = new Scene(addSupplyLayout);
            addSupplyScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle( "واجهة إضافة توريد");
            mainStage.setScene(addSupplyScene);
            System.gc();
            mainStage.show();
        });


        String editSupplyButtonText = "تعديل توريد";
        Button editSupplyButton = new Button(editSupplyButtonText);
        editSupplyButton.setPrefSize(150, 30);
        editSupplyButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane suppliesLayout = (BorderPane) getManagerSuppliesSceneLayout();
                suppliesLayout.setBottom(hBox);

                Scene suppliesScene = new Scene(suppliesLayout);
                suppliesScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة التوريدات");
                mainStage.setScene(suppliesScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);


            BorderPane editSupplyLayout = (BorderPane) getManagerEditSupplySceneLayout();
            editSupplyLayout.setBottom(hBox);

            Scene editSupplyScene = new Scene(editSupplyLayout);
            editSupplyScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة تعديل توريد");
            mainStage.setScene(editSupplyScene);
            System.gc();
            mainStage.show();
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(editSupplyButton, 0, 0);
        gridPane.add(addSupplyButton, 1, 0);
        gridPane.add(showSuppliesButton, 2, 0);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerAddSupplySceneLayout() {
        Label messageLabel = new Label();

        Button submitButton = new Button("أدخل");
        submitButton.setPrefSize(100, 20);

        Label supplyDateLabel = new Label("تاريخ التوريد");
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

        Label supplierNameLabel = new Label("أسم المورد");
        ChoiceBox supplierChoiceBox = new ChoiceBox();
        supplierChoiceBox.setConverter(new StringConverter<Supplier>() {
            @Override
            public String toString(Supplier s) {
                return s.getSupplierName();
            }

            @Override
            public Supplier fromString(String s) {
                return null;
            }
        });
        ArrayList<Supplier> suppliers = StoreDatabase.getAllSuppliers();
        supplierChoiceBox.setItems(FXCollections.observableList(suppliers));

        Label transportationFessLabel = new Label( "مصاريف المواصلات");
        TextField transportationFeesTextField = new TextField();
        transportationFeesTextField.setPrefSize(100,20);

        Button addMoreProductsButton = new Button("أضف منتج للفاتورة");
        addMoreProductsButton.setPrefSize(120,20);

        Button addNewProductButton = new Button("أضف منتج جديد لقاعدة البيانات");
        addNewProductButton.setPrefSize(200,20);

        ArrayList<Label> productsLabels =new ArrayList<>();
        ArrayList<ChoiceBox> productsChoiceBoxes = new ArrayList<>();
        ArrayList<Label> categoriesLabels = new ArrayList<>();
        ArrayList<ChoiceBox> productsCategoriesChoiceBoxes = new ArrayList<>();
        ArrayList<Label> boughtQuantitiesLabels = new ArrayList<>();
        ArrayList<TextField> boughtQuantitiesTextFields = new ArrayList<>();
        ArrayList<Label> buyingsPricesLabels = new ArrayList<>();
        ArrayList<TextField> buyingsPricesTextFields = new ArrayList<>();

        ScrollPane scrollPane = new ScrollPane();
        GridPane productsGridPane = new GridPane();
        productsGridPane.setHgap(10);
        productsGridPane.setVgap(10);
        productsGridPane.setPadding(new Insets(10, 10, 10, 10));
        scrollPane.setContent(productsGridPane);

        addMoreProductsButton.setOnAction(event -> {

            Label productLabel = new Label("أسم المنتج");
            ChoiceBox productsChoiceBox = new ChoiceBox();
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


            productsCategoriesChoiceBox.setOnAction( actionEvent -> {
                ProductCategory selectedCategory = (ProductCategory) productsCategoriesChoiceBox.getValue();
                ArrayList<Product> productsInCategory = StoreDatabase.getProductsInCategory(selectedCategory);
                productsChoiceBox.setItems(FXCollections.observableList(productsInCategory));
            });




            Label boughtQuantityLabel = new Label("الكمية المشتراة");
            TextField boughtQuantityTextField = new TextField();
            boughtQuantityTextField.setPrefSize(100,20);


            Label buyingPriceLabel = new Label( "سعر الوحدة");
            TextField buyingPriceTextField = new TextField();
            buyingPriceTextField.setPrefSize(100,20);


            productsLabels.add(productLabel);
            productsChoiceBoxes.add(productsChoiceBox);
            categoriesLabels.add(categoryLabel);
            productsCategoriesChoiceBoxes.add(productsCategoriesChoiceBox);
            boughtQuantitiesLabels.add(boughtQuantityLabel);
            boughtQuantitiesTextFields.add(boughtQuantityTextField);
            buyingsPricesTextFields.add(buyingPriceTextField);
            buyingsPricesLabels.add(buyingPriceLabel);


            int currentProductRow = 0;
            int numOfProducts = productsLabels.size();
            currentProductRow += numOfProducts;

            int lastProductIndex = numOfProducts -1;

            productsGridPane.add(buyingsPricesTextFields.get(lastProductIndex),7,currentProductRow);
            productsGridPane.add(buyingsPricesLabels.get(lastProductIndex),6,currentProductRow);
            productsGridPane.add(boughtQuantitiesTextFields.get(lastProductIndex),5,currentProductRow);
            productsGridPane.add(boughtQuantitiesLabels.get(lastProductIndex),4,currentProductRow);
            productsGridPane.add(productsChoiceBoxes.get(lastProductIndex),3,currentProductRow);
            productsGridPane.add(productsLabels.get(lastProductIndex),2,currentProductRow);
            productsGridPane.add(productsCategoriesChoiceBoxes.get(lastProductIndex),1,currentProductRow);
            productsGridPane.add(categoriesLabels.get(lastProductIndex),0,currentProductRow);


        });

        submitButton.setOnAction(event -> {
            //ToDo: complete the add product function

            String message = "";
            messageLabel.setText(message);

            String supplyDate = supplyDatePicker.getValue().toString();
            Supplier supplier = (Supplier) supplierChoiceBox.getValue();
            double transportationFees = Double.parseDouble(transportationFeesTextField.getText().trim());


            ArrayList<BoughtProduct> boughtProducts = new ArrayList<>();
            double productsTotalCost = 0;
            int numOfBoughtProducts =  productsChoiceBoxes.size();
            for(int i = 0; i < numOfBoughtProducts; i++)
            {
                Product currentProduct = (Product) productsChoiceBoxes.get(i).getSelectionModel().getSelectedItem();
                int boughtQuantity = Integer.parseInt(boughtQuantitiesTextFields.get(i).getText().trim());
                double buyingPrice = Double.parseDouble(buyingsPricesTextFields.get(i).getText().trim());
                boughtProducts.add(new BoughtProduct(currentProduct, boughtQuantity, buyingPrice));

                productsTotalCost += (buyingPrice * boughtQuantity);
            }

            double supplyTotalCost = productsTotalCost + transportationFees;

            Supply supply = new Supply(supplyDate, supplier, transportationFees, productsTotalCost, supplyTotalCost, boughtProducts);
            boolean done = StoreDatabase.addSupply(supply);


            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");

        });

        addNewProductButton.setOnAction(actionEvent -> {
            BorderPane addProductLayout = (BorderPane) getManagerAddProductSceneLayout();

            Scene addProductScene = new Scene(addProductLayout);
            addProductScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            Stage secondaryStage = new Stage();
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.setTitle("واجهة إضافة منتج");
            secondaryStage.setScene(addProductScene);
            System.gc();
            secondaryStage.show();

        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(supplyDateLabel,0,0);
        gridPane.add(supplyDatePicker, 1,0);
        gridPane.add(supplierNameLabel,0,1);
        gridPane.add(supplierChoiceBox,1,1);
        gridPane.add(transportationFessLabel,0,2);
        gridPane.add(transportationFeesTextField,1,2);
        gridPane.add(addMoreProductsButton,0,3);
        gridPane.add(addNewProductButton, 1, 3);
        gridPane.add(scrollPane,0,4);
        gridPane.add(submitButton,0,5);
        gridPane.add(messageLabel,0,6);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerEditSupplySceneLayout() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerShowSuppliesSceneLayout() {

        Label startDateLabel = new Label("من");
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

        Label endDateLabel = new Label("إلي");
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

        Button showSuppliesButton = new Button("أعرض التوريدات");

        HBox topHBox = new HBox();
        topHBox.setSpacing(20);

        topHBox.getChildren().add(startDateLabel);
        topHBox.getChildren().add(startDatePicker);
        topHBox.getChildren().add(endDateLabel);
        topHBox.getChildren().add(endDatePicker);
        topHBox.getChildren().add(showSuppliesButton);

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

        TableView suppliesTable = new TableView();
        suppliesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        suppliesTable.setRowFactory(tableView -> {
            TableRow<Supply> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                    Supply clickedSupply = row.getItem();
                    showBoughtProducts(clickedSupply);

                }
            });
            return row ;
        });

        suppliesTable.getColumns().add(supplyID);
        suppliesTable.getColumns().add(supplyDate);
        suppliesTable.getColumns().add(supplierName);
        suppliesTable.getColumns().add(productsTotalCost);
        suppliesTable.getColumns().add(transportationFees);
        suppliesTable.getColumns().add(supplyTotalCost);

        showSuppliesButton.setOnAction(event -> {
            String queryStartDate = startDatePicker.getValue().toString();
            String queryEndDate = endDatePicker.getValue().toString();

            ArrayList<Supply> supplies = StoreDatabase.getSupplies(queryStartDate, queryEndDate);
            suppliesTable.setItems(FXCollections.observableList(supplies));
        });
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setTop(topHBox);
        borderPane.setCenter(suppliesTable);

        return borderPane;
    }

    private static Pane getManagerBillsSceneLayout() {
        String showBillsButtonText = "عرض الفواتير";
        Button showBillsButton = new Button(showBillsButtonText);
        showBillsButton.setPrefSize(150, 30);
        showBillsButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane billsLayout = (BorderPane) getManagerBillsSceneLayout();
                billsLayout.setBottom(hBox);

                Scene billsScene = new Scene(billsLayout);
                billsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الفواتير");
                mainStage.setScene(billsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane showBillsSceneLayout = (BorderPane) getManagerShowBillsSceneLayout();
            showBillsSceneLayout.setBottom(hBox);

            Scene showBillsScene = new Scene(showBillsSceneLayout);
            showBillsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة عرض الفواتير");
            mainStage.setScene(showBillsScene);
            System.gc();
            mainStage.show();

        });

        String addBillButtonText = "إضافة فاتورة";
        Button addBillButton = new Button(addBillButtonText);
        addBillButton.setPrefSize(150, 30);
        addBillButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane billsLayout = (BorderPane) getManagerBillsSceneLayout();
                billsLayout.setBottom(hBox);

                Scene billsScene = new Scene(billsLayout);
                billsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الفواتير");
                mainStage.setScene(billsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane addBillLayout = (BorderPane) getManagerAddBillSceneLayout();
            addBillLayout.setBottom(hBox);

            Scene addBillScene = new Scene(addBillLayout);
            addBillScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة إضافة الفاتورة");
            mainStage.setScene(addBillScene);
            System.gc();
            mainStage.show();
        });

        String editBillButtonText = "تعديل فاتورة";
        Button editBillButton = new Button(editBillButtonText);
        editBillButton.setPrefSize(150, 30);
        editBillButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane billsLayout = (BorderPane) getManagerBillsSceneLayout();
                billsLayout.setBottom(hBox);

                Scene billsScene = new Scene(billsLayout);
                billsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الفواتير");
                mainStage.setScene(billsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane editBillLayout = (BorderPane) getManagerEditBillSceneLayout();
            editBillLayout.setBottom(hBox);

            Scene editBillScene = new Scene(editBillLayout);
            editBillScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle( "واجهة تعديل الفاتورة");
            mainStage.setScene(editBillScene);
            System.gc();
            mainStage.show();

        });

        

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(showBillsButton, 0,0);
        gridPane.add(addBillButton, 1,0);
        gridPane.add(editBillButton,2,0);



        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;

    }

    private static Pane getManagerAddBillSceneLayout() {
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

        nodeText = "أسم العميل";
        Label buyerNameLabel = new Label(nodeText);
        TextField buyerNameTextField = new TextField();
        buyerNameTextField.setPrefSize(100,20);


        nodeText = "وسيلة الدفع";
        Label paymentMethodLabel = new Label(nodeText);
        ChoiceBox<String> paymentMethodBox = new ChoiceBox<>();
        ArrayList<String> paymentMethods = new ArrayList<>();
        paymentMethods.add("كاش");
        paymentMethods.add("تقسيط");
        paymentMethodBox.setItems(FXCollections.observableList(paymentMethods));

        nodeText = "أسم الضامن";
        Label guarantorNameLabel = new Label(nodeText);
        TextField guarantorNameTextField = new TextField();
        guarantorNameTextField.setPrefSize(100,20);

        nodeText = "المقدم";
        Label initialPaymentLabel = new Label(nodeText);
        TextField initialPaymentTextField = new TextField();
        initialPaymentTextField.setPrefSize(100,20);

        nodeText = "تاريخ القسط القادم";
        Label upcomingInstalmentDateLabel = new Label(nodeText);
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setConverter(new StringConverter<LocalDate>() {
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

        nodeText = "عدد الأقساط المتبقية";
        Label remainingInstalmentsNumberLabel = new Label(nodeText);
        TextField remainingInstalmentsNumberTextField = new TextField();
        remainingInstalmentsNumberTextField.setPrefSize(50,20);

        nodeText = "كمية القسط";
        Label instalmentAmountLabel = new Label(nodeText);
        TextField instalmentAmountTextField = new TextField();
        instalmentAmountTextField.setPrefSize(100,20);

        nodeText = "أضف منتج للفاتورة";
        Button addMoreProductsButton = new Button(nodeText);
        addMoreProductsButton.setPrefSize(120,20);

        ArrayList<Label> productsLabels =new ArrayList<>();
        ArrayList<ChoiceBox> productsChoiceBoxes = new ArrayList<>();
        ArrayList<Label> categoriesLabels = new ArrayList<>();
        ArrayList<ChoiceBox> productsCategoriesChoiceBoxes = new ArrayList<>();
        ArrayList<Label> soldQuantitiesLabels = new ArrayList<>();
        ArrayList<TextField> soldQuantitiesTextFields = new ArrayList<>();
        ArrayList<Label> sellingPricesLabels = new ArrayList<>();
        ArrayList<TextField> sellingPricesTextFields = new ArrayList<>();

        ScrollPane scrollPane = new ScrollPane();
        GridPane productsGridPane = new GridPane();
        productsGridPane.setHgap(10);
        productsGridPane.setVgap(10);
        productsGridPane.setPadding(new Insets(10, 10, 10, 10));
        scrollPane.setContent(productsGridPane);

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
        Label messageLabel = new Label();
        

        paymentMethodBox.setOnAction(actionEvent ->  {
                String paymentMethod= paymentMethodBox.getSelectionModel().getSelectedItem();
                if(paymentMethod.equals("تقسيط"))
                {
                    gridPane.getChildren().remove(addMoreProductsButton);
                    gridPane.getChildren().remove(scrollPane);
                    gridPane.getChildren().remove(submitButton);
                    gridPane.getChildren().remove(messageLabel);


                    gridPane.add(guarantorNameLabel,0,3);
                    gridPane.add(guarantorNameTextField,1,3);
                    gridPane.add(initialPaymentLabel,0,4);
                    gridPane.add(initialPaymentTextField,1,4);
                    gridPane.add(instalmentAmountLabel,0,5);
                    gridPane.add(instalmentAmountTextField,1,5);

                    gridPane.add(startDatePicker,1,6);
                    gridPane.add(upcomingInstalmentDateLabel,0,6);
                    gridPane.add(remainingInstalmentsNumberTextField,1,7);
                    gridPane.add(remainingInstalmentsNumberLabel,0,7);
                    gridPane.add(addMoreProductsButton,0,8);
                    gridPane.add(scrollPane,0,9);
                    gridPane.add(submitButton,0,10);
                    gridPane.add(messageLabel,0,11);


                }
                else
                {
                    gridPane.getChildren().remove(guarantorNameLabel);
                    gridPane.getChildren().remove(guarantorNameTextField);
                    gridPane.getChildren().remove(initialPaymentLabel);
                    gridPane.getChildren().remove(initialPaymentTextField);
                    gridPane.getChildren().remove(instalmentAmountTextField);
                    gridPane.getChildren().remove(instalmentAmountLabel);
                    gridPane.getChildren().remove(startDatePicker);
                    gridPane.getChildren().remove(upcomingInstalmentDateLabel);
                    gridPane.getChildren().remove(remainingInstalmentsNumberTextField);
                    gridPane.getChildren().remove(remainingInstalmentsNumberLabel);
                    gridPane.getChildren().remove(addMoreProductsButton);
                    gridPane.getChildren().remove(scrollPane);
                    gridPane.getChildren().remove(submitButton);
                    gridPane.getChildren().remove(messageLabel);


                    gridPane.add(addMoreProductsButton,0,3);
                    gridPane.add(scrollPane,0,4);
                    gridPane.add(submitButton,0,5);
                    gridPane.add(messageLabel,0,6);


                }

        });

        addMoreProductsButton.setOnAction(event -> {

            String paymentMethod = paymentMethodBox.getSelectionModel().getSelectedItem();

            if(paymentMethod == null)
                //ToDo : Show a warning that you must select payment method before choosing products
                System.out.println("You can't add product till you choose a payment method.");
            else
            {
                Label productLabel = new Label("أسم المنتج");
                ChoiceBox productsChoiceBox = new ChoiceBox();
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

                productsCategoriesChoiceBox.setOnAction( actionEvent -> {
                    ProductCategory selectedCategory = (ProductCategory) productsCategoriesChoiceBox.getValue();
                    ArrayList<Product> productsInCategory = StoreDatabase.getProductsInCategory(selectedCategory);
                    productsChoiceBox.setItems(FXCollections.observableList(productsInCategory));
                });
                

                Label soldQuantityLabel = new Label("الكمية المباعة");
                TextField soldQuantityTextField = new TextField();
                soldQuantityTextField.setPrefSize(100,20);


                Label sellingPriceLabel = new Label( "سعر البيع");
                TextField sellingPriceTextField = new TextField();
                sellingPriceTextField.setPrefSize(100,20);

                productsLabels.add(productLabel);
                productsChoiceBoxes.add(productsChoiceBox);
                categoriesLabels.add(categoryLabel);
                productsCategoriesChoiceBoxes.add(productsCategoriesChoiceBox);
                soldQuantitiesLabels.add(soldQuantityLabel);
                soldQuantitiesTextFields.add(soldQuantityTextField);
                sellingPricesTextFields.add(sellingPriceTextField);
                sellingPricesLabels.add(sellingPriceLabel);


                int currentProductRow = 0;
                int numOfProducts = productsLabels.size();
                currentProductRow += numOfProducts;


                int lastProductIndex = numOfProducts -1;

                productsGridPane.add(sellingPricesTextFields.get(lastProductIndex),7,currentProductRow);
                productsGridPane.add(sellingPricesLabels.get(lastProductIndex),6,currentProductRow);
                productsGridPane.add(soldQuantitiesTextFields.get(lastProductIndex),5,currentProductRow);
                productsGridPane.add(soldQuantitiesLabels.get(lastProductIndex),4,currentProductRow);
                productsGridPane.add(productsChoiceBoxes.get(lastProductIndex),3,currentProductRow);
                productsGridPane.add(productsLabels.get(lastProductIndex),2,currentProductRow);
                productsGridPane.add(productsCategoriesChoiceBoxes.get(lastProductIndex),1,currentProductRow);
                productsGridPane.add(categoriesLabels.get(lastProductIndex),0,currentProductRow);

            }



        });


        submitButton.setOnAction(event -> {
            //ToDo: complete the add bill function

            String message = "";
            messageLabel.setText(message);

            String billDate = billDatePicker.getValue().toString();
            String buyerName = buyerNameTextField.getText().trim();
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


            Bill bill;
            if(paymentMethod.equals("تقسيط"))
            {
                String guarantorName = guarantorNameTextField.getText().trim();
                double initialPayment = Double.parseDouble(initialPaymentTextField.getText().trim());
                double remainingMoney = billTotalCost - initialPayment;
                double instalmentAmount = Double.parseDouble(instalmentAmountTextField.getText().trim());
                String upcomingInstalmentDate = startDatePicker.getValue().toString();
                int remainingInstalmentsNumber = Integer.parseInt(remainingInstalmentsNumberTextField.getText().trim());
                BillInstalmentsDetails billInstalmentsDetails = new BillInstalmentsDetails(guarantorName, initialPayment, remainingMoney, instalmentAmount, upcomingInstalmentDate, remainingInstalmentsNumber);

                bill = new Bill(billDate, buyerName, productsTotalCost, billTotalCost, paymentMethod, soldProducts, billInstalmentsDetails);
            }
            else
                bill = new Bill(billDate, buyerName, productsTotalCost, billTotalCost, paymentMethod, soldProducts);


            boolean done = StoreDatabase.addBill(bill);


            //done = StoreDatabase.addBill();
            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");

        });


        gridPane.add(billDateLabel,0,0);
        gridPane.add(billDatePicker,1,0);
        gridPane.add(buyerNameLabel,0,1);
        gridPane.add(buyerNameTextField,1,1);
        gridPane.add(paymentMethodLabel,0,2);
        gridPane.add(paymentMethodBox,1,2);
        gridPane.add(addMoreProductsButton,0,3);
        gridPane.add(scrollPane,0,4);
        gridPane.add(submitButton,0,5);
        gridPane.add(messageLabel,0,6);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerEditBillSceneLayout() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerShowBillsSceneLayout() {
        Label startDateLabel = new Label("من");
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

        Label endDateLabel = new Label("إلي");
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

        Button showBillsButton = new Button("أعرض الفواتير");

        HBox topHBox = new HBox();
        topHBox.setSpacing(20);

        topHBox.getChildren().add(startDateLabel);
        topHBox.getChildren().add(startDatePicker);
        topHBox.getChildren().add(endDateLabel);
        topHBox.getChildren().add(endDatePicker);
        topHBox.getChildren().add(showBillsButton);


        TableView billsTable = new TableView();
        billsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        billsTable.setRowFactory(tableView -> {
            TableRow<Bill> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                    Bill clickedBill = row.getItem();
                    showSoldProducts(clickedBill);

                }
            });
            return row ;
        });

        TableColumn buyerName = new TableColumn("أسم العميل");
        TableColumn billID = new TableColumn("رقم الفاتورة");
        TableColumn billDate = new TableColumn("تاريخ الفاتورة");
        TableColumn billTotalCost = new TableColumn("التكلفة الكلية");
        TableColumn paymentMethod = new TableColumn("وسيلة الدفع");

        billID.setCellValueFactory( new PropertyValueFactory<Bill, Integer>("billID"));
        buyerName.setCellValueFactory( new PropertyValueFactory<Bill, String>("buyerName"));
        billDate.setCellValueFactory( new PropertyValueFactory<Bill, Date>("billDate"));
        billTotalCost.setCellValueFactory( new PropertyValueFactory<Bill, Double>("billTotalCost"));
        paymentMethod.setCellValueFactory(new PropertyValueFactory<Bill, String>("paymentMethod"));

        billsTable.getColumns().add(billID);
        billsTable.getColumns().add(billDate);
        billsTable.getColumns().add(buyerName);
        billsTable.getColumns().add(billTotalCost);
        billsTable.getColumns().add(paymentMethod);


        showBillsButton.setOnAction(event -> {
            String queryStartDate = startDatePicker.getValue().toString();
            String queryEndDate = endDatePicker.getValue().toString();

            ArrayList<Bill> bills = StoreDatabase.getBills(queryStartDate, queryEndDate);
            billsTable.setItems(FXCollections.observableList(bills));
        });


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setTop(topHBox);
        borderPane.setCenter(billsTable);

        return borderPane;
    }

    private static Pane getManagerProductsSceneLayout() {
        String showProductsButtonText = "عرض المنتجات";
        Button showProductsButton = new Button(showProductsButtonText);
        showProductsButton.setPrefSize(150, 30);
        showProductsButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane productsLayout = (BorderPane) getManagerProductsSceneLayout();
                productsLayout.setBottom(hBox);

                Scene productsScene = new Scene(productsLayout);
                productsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة المنتجات");
                mainStage.setScene(productsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane showProductsSceneLayout = (BorderPane) getManagerShowProductsSceneLayout();
            showProductsSceneLayout.setBottom(hBox);

            Scene showProductsScene = new Scene(showProductsSceneLayout);
            showProductsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle( "واجهة عرض المنتجات");
            mainStage.setScene(showProductsScene);
            System.gc();
            mainStage.show();

        });


        String addProductButtonText = "إضافة منتج";
        Button addProductButton = new Button(addProductButtonText);
        addProductButton.setPrefSize(150, 30);
        addProductButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane productsLayout = (BorderPane) getManagerProductsSceneLayout();
                productsLayout.setBottom(hBox);

                Scene productsScene = new Scene(productsLayout);
                productsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة المنتجات");
                mainStage.setScene(productsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane addProductLayout = (BorderPane) getManagerAddProductSceneLayout();
            addProductLayout.setBottom(hBox);

            Scene addProductScene = new Scene(addProductLayout);
            addProductScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle( "واجهة إضافة منتج");
            mainStage.setScene(addProductScene);
            System.gc();
            mainStage.show();
        });


        String editProductButtonText = "تعديل منتج";
        Button editProductButton = new Button(editProductButtonText);
        editProductButton.setPrefSize(150, 30);
        editProductButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane productsLayout = (BorderPane) getManagerProductsSceneLayout();
                productsLayout.setBottom(hBox);

                Scene productsScene = new Scene(productsLayout);
                productsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة المنتجات");
                mainStage.setScene(productsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane editProductLayout = (BorderPane) getManagerEditProductSceneLayout();
            editProductLayout.setBottom(hBox);

            Scene editProductScene = new Scene(editProductLayout);
            editProductScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة تعديل منتج");
            mainStage.setScene(editProductScene);
            System.gc();
            mainStage.show();
        });

        String showCategoriesButtonText = "عرض الأنواع";
        Button showCategoriesButton = new Button(showCategoriesButtonText);
        showCategoriesButton.setPrefSize(150, 30);
        showCategoriesButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane productsLayout = (BorderPane) getManagerProductsSceneLayout();
                productsLayout.setBottom(hBox);

                Scene productsScene = new Scene(productsLayout);
                productsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة المنتجات");
                mainStage.setScene(productsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane showProductsCategoriesSceneLayout = (BorderPane) getManagerShowProductsCategoriesSceneLayout();
            showProductsCategoriesSceneLayout.setBottom(hBox);

            Scene showProductsCategoriesScene = new Scene(showProductsCategoriesSceneLayout);
            showProductsCategoriesScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle( "واجهة عرض الأنواع");
            mainStage.setScene(showProductsCategoriesScene);
            System.gc();
            mainStage.show();
        });

        String addCategoryButtonText = "إضافة نوع";
        Button addCategoryButton = new Button(addCategoryButtonText);
        addCategoryButton.setPrefSize(150, 30);
        addCategoryButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane productsLayout = (BorderPane) getManagerProductsSceneLayout();
                productsLayout.setBottom(hBox);

                Scene productsScene = new Scene(productsLayout);
                productsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة المنتجات");
                mainStage.setScene(productsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane addCategoryLayout = (BorderPane) getManagerAddProductsCategorySceneLayout();
            addCategoryLayout.setBottom(hBox);

            Scene addCategoryScene = new Scene(addCategoryLayout);
            addCategoryScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

            mainStage.setTitle( "واجهة إضافة نوع");
            mainStage.setScene(addCategoryScene);
            System.gc();
            mainStage.show();
        });

        String editCategoryButtonText = "تعديل نوع";
        Button editCategoryButton = new Button(editCategoryButtonText);
        editCategoryButton.setPrefSize(150, 30);
        editCategoryButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            String previousButtonText = "العودة إلي الواجهة السابقة";
            Button previousButton = new Button(previousButtonText);
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                BorderPane productsLayout = (BorderPane) getManagerProductsSceneLayout();
                productsLayout.setBottom(hBox);

                Scene productsScene = new Scene(productsLayout);
                productsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة المنتجات");
                mainStage.setScene(productsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane editCategoryLayout = (BorderPane) getManagerEditProductsCategorySceneLayout();
            editCategoryLayout.setBottom(hBox);

            Scene editCategoryScene = new Scene(editCategoryLayout);
            editCategoryScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة تعديل نوع");
            mainStage.setScene(editCategoryScene);
            System.gc();
            mainStage.show();
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(editProductButton, 0, 0);
        gridPane.add(addProductButton, 1, 0);
        gridPane.add(showProductsButton, 2, 0);
        gridPane.add(editCategoryButton, 0, 1);
        gridPane.add(addCategoryButton, 1, 1);
        gridPane.add(showCategoriesButton, 2, 1);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerAddProductSceneLayout() {
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
            //ToDo: complete the add product function

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


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerEditProductSceneLayout() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        Label productLabel = new Label("أسم المنتج");
        ChoiceBox productsChoiceBox = new ChoiceBox();
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

        productsCategoriesChoiceBox.setOnAction(actionEvent -> {
            ProductCategory selectedCategory = (ProductCategory) productsCategoriesChoiceBox.getValue();
            ArrayList<Product> products = StoreDatabase.getProductsInCategory(selectedCategory);
            productsChoiceBox.setItems(FXCollections.observableList(products));
        });


       

        Button editProductButton = new Button("تعديل المنتج");
        editProductButton.setPrefSize(100,20);
        editProductButton.setOnAction(actionEvent -> {
            Product toEditProduct = (Product) productsChoiceBox.getValue();
            showEditProduct(toEditProduct);
        });
        gridPane.add(categoryLabel, 0,0);
        gridPane.add(productsCategoriesChoiceBox, 1,0);
        gridPane.add(productLabel,2,0);
        gridPane.add(productsChoiceBox,3,0);
        gridPane.add(editProductButton,0,1);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        return borderPane;
    }

    private static Pane getManagerShowProductsSceneLayout() {
        TableColumn productName = new TableColumn("أسم المنتج");
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        
        TableColumn productAvailableQuantity = new TableColumn("الكمية المتوفرة");
        productAvailableQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("availableQuantity"));
        
        TableColumn productSupplyPrice = new TableColumn("سعر توريد");
        productSupplyPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("supplyPrice"));
        
        TableColumn lastSupplyDate = new TableColumn("تاريخ أخر توريد");
        lastSupplyDate.setCellValueFactory(new PropertyValueFactory<Product, String>("lastSupplyDate"));
        
        TableColumn cashSellingPrice = new TableColumn("سعر بيع كاش");
        cashSellingPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("cashSellingPrice"));
        
        TableColumn instalmentSellingPrice = new TableColumn("سعر بيع تقسيط");
        instalmentSellingPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("instalmentSellingPrice"));

        TableView productsTable = new TableView();
        productsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        productsTable.getColumns().add(productName);
        productsTable.getColumns().add(productAvailableQuantity);
        productsTable.getColumns().add(productSupplyPrice);
        productsTable.getColumns().add(lastSupplyDate);
        productsTable.getColumns().add(cashSellingPrice);
        productsTable.getColumns().add(instalmentSellingPrice);





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
        
        productsCategoriesChoiceBox.setOnAction(actionEvent -> {
            ProductCategory selectedCategory = (ProductCategory) productsCategoriesChoiceBox.getValue();
            ArrayList<Product> products = StoreDatabase.getProductsInCategory(selectedCategory);
            productsTable.setItems(FXCollections.observableList(products));
        });


        HBox topHBox = new HBox();

        topHBox.setSpacing(20);
        topHBox.getChildren().add(productCategoryLabel);
        topHBox.getChildren().add(productsCategoriesChoiceBox);


        BorderPane borderPane = new BorderPane();

        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setTop(topHBox);
        borderPane.setCenter(productsTable);

        return borderPane;
    }

    private static Pane getManagerAddProductsCategorySceneLayout() {
        TextField categoryNameTextField = new TextField();
        categoryNameTextField.setPrefSize(100, 20);

        String nodeText = "أسم النوع";
        Label categoryNameLabel = new Label(nodeText);

        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
        submitButton.setOnAction(event -> {
            //ToDo: complete the add category function
            String message = "";
            messageLabel.setText(message);
            String categoryName = categoryNameTextField.getText();

            ProductCategory category = new ProductCategory(categoryName);
            boolean done = StoreDatabase.addProductCategory(category);

            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");


        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(categoryNameTextField, 0, 0);
        gridPane.add(categoryNameLabel, 1, 0);
        gridPane.add(submitButton, 0, 1);
        gridPane.add(messageLabel, 0, 2);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setCenter(gridPane);




        return borderPane;
    }

    private static Pane getManagerEditProductsCategorySceneLayout() {

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setCenter(gridPane);



        return borderPane;
    }

    private static Pane getManagerShowProductsCategoriesSceneLayout() {
        TableColumn categoryName = new TableColumn("");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));



        return borderPane;
    }

    private static Pane getManagerSuppliersSceneLayout() {
        String showSuppliersButtonText = "عرض الموردين";
        Button showSuppliersButton = new Button(showSuppliersButtonText);
        showSuppliersButton.setPrefSize(150, 30);
        showSuppliersButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });


            Button previousButton = new Button("العودة إلي الواجهة السابقة");
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();
                BorderPane suppliersLayout = (BorderPane) getManagerSuppliersSceneLayout();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                suppliersLayout.setBottom(hBox);

                Scene suppliersScene = new Scene(suppliersLayout);
                suppliersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الموردين");
                mainStage.setScene(suppliersScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane showSuppliersSceneLayout = (BorderPane) getManagerShowSuppliersSceneLayout();

            showSuppliersSceneLayout.setBottom(hBox);
            Scene showSuppliersScene = new Scene(showSuppliersSceneLayout);
            showSuppliersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة عرض الموردين");
            mainStage.setScene(showSuppliersScene);
            System.gc();
            mainStage.show();
        });

        String addSupplierButtonText = "إضافة مورد";
        Button addSupplierButton = new Button(addSupplierButtonText);
        addSupplierButton.setPrefSize(150, 30);
        addSupplierButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            Button previousButton = new Button("العودة إلي الواجهة السابقة");
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();
                BorderPane suppliersLayout = (BorderPane) getManagerSuppliersSceneLayout();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                suppliersLayout.setBottom(hBox);

                Scene suppliersScene = new Scene(suppliersLayout);
                suppliersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الموردين");
                mainStage.setScene(suppliersScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane addSupplierSceneLayout = (BorderPane) getManagerAddSupplierSceneLayout();

            addSupplierSceneLayout.setBottom(hBox);
            Scene addSuppliersScene = new Scene(addSupplierSceneLayout);
            addSuppliersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة إضافة مورد");
            mainStage.setScene(addSuppliersScene);
            System.gc();
            mainStage.show();
        });


        String editSupplierButtonText = "تعديل مورد";
        Button editSupplierButton = new Button(editSupplierButtonText);
        editSupplierButton.setPrefSize(150, 30);
        editSupplierButton.setOnAction(event -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(actionEvent -> {
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Pane homeLayout = getManagerHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            Button previousButton = new Button("العودة إلي الواجهة السابقة");
            previousButton.setPrefSize(180, 30);
            previousButton.setOnAction(actionEvent -> {
                mainStage.close();
                BorderPane suppliersLayout = (BorderPane) getManagerSuppliersSceneLayout();

                HBox hBox = new HBox();
                hBox.getChildren().add(homeButton);

                suppliersLayout.setBottom(hBox);

                Scene suppliersScene = new Scene(suppliersLayout);
                suppliersScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الموردين");
                mainStage.setScene(suppliersScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane editSupplierSceneLayout = (BorderPane) getManagerEditSupplierSceneLayout();

            editSupplierSceneLayout.setBottom(hBox);
            Scene editSupplierScene = new Scene(editSupplierSceneLayout);
            editSupplierScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة تعديل مورد");
            mainStage.setScene(editSupplierScene);
            System.gc();
            mainStage.show();
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(editSupplierButton, 0, 0);
        gridPane.add(addSupplierButton, 1, 0);
        gridPane.add(showSuppliersButton, 2, 0);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerAddSupplierSceneLayout() {
        String nodeText;

        nodeText = "أسم المورد";
        Label supplierNameLabel = new Label(nodeText);
        TextField supplierNameTextField = new TextField();
        supplierNameTextField.setPrefSize(200, 20);


        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {

            //ToDo: complete the add supplier function
            String message = "";
            messageLabel.setText(message);

            String supplierName = supplierNameTextField.getText();
            Supplier supplier = new Supplier(supplierName);
            boolean done = StoreDatabase.addSupplier(supplier);

            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(supplierNameLabel,0,0);
        gridPane.add(supplierNameTextField,1,0);
        gridPane.add(submitButton,0,1);
        gridPane.add(messageLabel,0,2);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        return borderPane;
    }

    private static Pane getManagerEditSupplierSceneLayout() {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getManagerShowSuppliersSceneLayout() {
        TableColumn supplierName = new TableColumn("أسم المورد");

        supplierName.setCellValueFactory(new PropertyValueFactory<User, String>("name"));

        TableView suppliersTable = new TableView();
        suppliersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        suppliersTable.getColumns().add(supplierName);


        ArrayList<Supplier> suppliers = StoreDatabase.getAllSuppliers();
        suppliersTable.setItems(FXCollections.observableList(suppliers));

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(suppliersTable);

        return borderPane;
    }

    private static void showSoldProducts(Bill bill) {
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
        secondaryStage.setTitle("واجهة إضافة منتج");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(billDetailsTable);

        Scene scene = new Scene(borderPane);
        scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        secondaryStage.setScene(scene);
        System.gc();
        secondaryStage.show();
    }

    private static void showBoughtProducts(Supply supply) {
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
        secondaryStage.setTitle("واجهة تفاصيل التوريدات");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(billDetailsTable);

        Scene scene = new Scene(borderPane);
        scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        secondaryStage.setScene(scene);
        System.gc();
        secondaryStage.show();
    }

    private static void showEditProduct(Product toEditProduct) {
        String nodeText;

        nodeText = "أسم المنتج";
        Label productNameLabel = new Label(nodeText);
        TextField productNameTextField = new TextField();
        productNameTextField.setPrefSize(100, 20);
        productNameTextField.setText(toEditProduct.getProductName());

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
        productsCategoriesChoiceBox.setValue(toEditProduct.getCategory());


        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);

        Stage secondaryStage = new Stage();

        submitButton.setOnAction(event -> {
            //ToDo: complete the add product function

            String message = "";
            messageLabel.setText(message);

            int productID = toEditProduct.getProductID();
            String productName = productNameTextField.getText().trim();
            ProductCategory productCategory = (ProductCategory) productsCategoriesChoiceBox.getValue();
            int availableQuantity = toEditProduct.getAvailableQuantity();
            double supplyPrice = toEditProduct.getSupplyPrice();
            String lastSupplyDate = toEditProduct.getLastSupplyDate();
            double cashSellingPrice = toEditProduct.getCashSellingPrice();
            double instalmentSellingPrice = toEditProduct.getInstalmentSellingPrice();

            Product toUpdateProduct = new Product(productID, productName, productCategory, availableQuantity, supplyPrice, lastSupplyDate, cashSellingPrice, instalmentSellingPrice);

            boolean done = StoreDatabase.updateProduct(toUpdateProduct);
            if(done)
            {
                message = "تم الإدخال بنجاح";
                messageLabel.setText(message);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                secondaryStage.close();
            }
            else
            {
                message = "حدث خطأ إثناء إدخال البيانات!";
                messageLabel.setText(message);
            }





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


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.setMaximized(true);
        secondaryStage.setTitle("واجهة تعديل منتج");

        Scene scene = new Scene(borderPane);
        scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        secondaryStage.setScene(scene);
        System.gc();
        secondaryStage.show();
    }

    private static void showEditUser(User toEditUser) {
    }
    
    private static Pane getClerkHomeSceneLayout() {
        Button addSuppliesButton = new Button("إضافة توريدات");
        addSuppliesButton.setPrefSize(150, 50);

        addSuppliesButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getClerkHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane addSupplyLayout = (BorderPane) getClerkAddSupplySceneLayout();
            addSupplyLayout.setBottom(hBox);

            Scene addSupplyScene = new Scene(addSupplyLayout);
            addSupplyScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة إضافة التوريدات");
            mainStage.setScene(addSupplyScene);
            System.gc();
            mainStage.show();
        });

        Button addBillsButton = new Button("إضافة فواتير");
        addBillsButton.setPrefSize(150, 50);

        addBillsButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getClerkHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane addBillLayout = (BorderPane) getClerkAddBillSceneLayout();
            addBillLayout.setBottom(hBox);

            Scene addBillScene = new Scene(addBillLayout);
            addBillScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة إضافة الفواتير");
            mainStage.setScene(addBillScene);
            System.gc();
            mainStage.show();
        });

        Button addProductsButton = new Button("إضافة منتجات");
        addProductsButton.setPrefSize(150, 50);

        addProductsButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getClerkHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane addProductLayout = (BorderPane) getClerkAddProductSceneLayout();
            addProductLayout.setBottom(hBox);

            Scene addProductScene = new Scene(addProductLayout);
            addProductScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة المنتجات");
            mainStage.setScene(addProductScene);
            System.gc();
            mainStage.show();
        });

        Button addSuppliersButton = new Button("إضافة موردين");
        addSuppliersButton.setPrefSize(150, 50);

        addSuppliersButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getClerkHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane addSupplierLayout = (BorderPane) getClerkAddSupplierSceneLayout();
            addSupplierLayout.setBottom(hBox);

            Scene addSupplierScene = new Scene(addSupplierLayout);
            addSupplierScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة المنتجات");
            mainStage.setScene(addSupplierScene);
            System.gc();
            mainStage.show();
        });

        Button addProductsCategoriesButton = new Button("إضافة أنواع منتجات");
        addProductsCategoriesButton.setPrefSize(150, 50);

        addProductsCategoriesButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getClerkHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane addProductsCategoryLayout = (BorderPane) getClerkAddProductsCategorySceneLayout();
            addProductsCategoryLayout.setBottom(hBox);

            Scene addProductsCategoryScene = new Scene(addProductsCategoryLayout);
            addProductsCategoryScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة المستخدمين");
            mainStage.setScene(addProductsCategoryScene);
            System.gc();
            mainStage.show();
        });

        Button showUpcomingInstalmentsButton = new Button("عرض الأقساط القادمة");
        showUpcomingInstalmentsButton.setPrefSize(150,50);

        showUpcomingInstalmentsButton.setOnAction(actionEvent -> {
            mainStage.close();

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180, 30);
            homeButton.setOnAction(event -> {
                mainStage.close();
                mainStage.setTitle("الواجهة الرئيسية");
                Pane homeLayout = getClerkHomeSceneLayout();
                Scene homeScene = new Scene(homeLayout);
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.getChildren().add(homeButton);

            BorderPane showUpcomingInstalmentsLayout = (BorderPane) getClerkShowUpcomingInstalmentsSceneLayout();
            showUpcomingInstalmentsLayout.setBottom(hBox);

            Scene showUpcomingInstalmentsScene = new Scene(showUpcomingInstalmentsLayout);
            showUpcomingInstalmentsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة الأقساط القادمة");
            mainStage.setScene(showUpcomingInstalmentsScene);
            System.gc();
            mainStage.show();
        });
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(addBillsButton, 0,0);
        gridPane.add(addSuppliesButton, 1,0);
        gridPane.add(addProductsButton, 2,0);
        gridPane.add(showUpcomingInstalmentsButton,0,1);
        gridPane.add(addProductsCategoriesButton,1,1);
        gridPane.add(addSuppliersButton, 2,1);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        return borderPane;

    }

    private static Pane getClerkAddProductsCategorySceneLayout() {
        TextField categoryNameTextField = new TextField();
        categoryNameTextField.setPrefSize(100, 20);

        String nodeText = "أسم النوع";
        Label categoryNameLabel = new Label(nodeText);

        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
        submitButton.setOnAction(event -> {
            //ToDo: complete the add category function
            String message = "";
            messageLabel.setText(message);
            String categoryName = categoryNameTextField.getText();

            ProductCategory category = new ProductCategory(categoryName);
            boolean done = StoreDatabase.addProductCategory(category);

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

        gridPane.add(categoryNameTextField, 1, 0);
        gridPane.add(categoryNameLabel, 0, 0);
        gridPane.add(submitButton, 0, 1);
        gridPane.add(messageLabel, 0, 2);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setCenter(gridPane);




        return borderPane;
    }

    private static Pane getClerkAddProductSceneLayout() {
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
            //ToDo: complete the add product function

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


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getClerkAddSupplierSceneLayout() {
        String nodeText;

        nodeText = "أسم المورد";
        Label supplierNameLabel = new Label(nodeText);
        TextField supplierNameTextField = new TextField();
        supplierNameTextField.setPrefSize(200, 20);


        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {

            //ToDo: complete the add supplier function
            String message = "";
            messageLabel.setText(message);

            String supplierName = supplierNameTextField.getText();
            Supplier supplier = new Supplier(supplierName);
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
        gridPane.add(submitButton,0,1);
        gridPane.add(messageLabel,0,2);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        return borderPane;
    }

    private static Pane getClerkAddBillSceneLayout() {
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

        nodeText = "أسم العميل";
        Label buyerNameLabel = new Label(nodeText);
        TextField buyerNameTextField = new TextField();
        buyerNameTextField.setPrefSize(100,20);


        nodeText = "وسيلة الدفع";
        Label paymentMethodLabel = new Label(nodeText);
        ChoiceBox<String> paymentMethodBox = new ChoiceBox<>();
        ArrayList<String> paymentMethods = new ArrayList<>();
        paymentMethods.add("كاش");
        paymentMethods.add("تقسيط");
        paymentMethodBox.setItems(FXCollections.observableList(paymentMethods));

        nodeText = "أسم الضامن";
        Label guarantorNameLabel = new Label(nodeText);
        TextField guarantorNameTextField = new TextField();
        guarantorNameTextField.setPrefSize(100,20);

        nodeText = "المقدم";
        Label initialPaymentLabel = new Label(nodeText);
        TextField initialPaymentTextField = new TextField();
        initialPaymentTextField.setPrefSize(100,20);

        nodeText = "تاريخ القسط القادم";
        Label upcomingInstalmentDateLabel = new Label(nodeText);
        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setConverter(new StringConverter<LocalDate>() {
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

        nodeText = "عدد الأقساط المتبقية";
        Label remainingInstalmentsNumberLabel = new Label(nodeText);
        TextField remainingInstalmentsNumberTextField = new TextField();
        remainingInstalmentsNumberTextField.setPrefSize(50,20);

        nodeText = "كمية القسط";
        Label instalmentAmountLabel = new Label(nodeText);
        TextField instalmentAmountTextField = new TextField();
        instalmentAmountTextField.setPrefSize(100,20);

        nodeText = "أضف منتج للفاتورة";
        Button addMoreProductsButton = new Button(nodeText);
        addMoreProductsButton.setPrefSize(120,20);

        ArrayList<Label> productsLabels =new ArrayList<>();
        ArrayList<ChoiceBox> productsChoiceBoxes = new ArrayList<>();
        ArrayList<Label> categoriesLabels = new ArrayList<>();
        ArrayList<ChoiceBox> productsCategoriesChoiceBoxes = new ArrayList<>();
        ArrayList<Label> soldQuantitiesLabels = new ArrayList<>();
        ArrayList<TextField> soldQuantitiesTextFields = new ArrayList<>();
        ArrayList<Label> sellingPricesLabels = new ArrayList<>();
        ArrayList<TextField> sellingPricesTextFields = new ArrayList<>();

        ScrollPane scrollPane = new ScrollPane();
        GridPane productsGridPane = new GridPane();
        productsGridPane.setHgap(10);
        productsGridPane.setVgap(10);
        productsGridPane.setPadding(new Insets(10, 10, 10, 10));
        scrollPane.setContent(productsGridPane);

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
        Label messageLabel = new Label();


        paymentMethodBox.setOnAction(actionEvent ->  {
            String paymentMethod= paymentMethodBox.getSelectionModel().getSelectedItem();
            if(paymentMethod.equals("تقسيط"))
            {
                gridPane.getChildren().remove(addMoreProductsButton);
                gridPane.getChildren().remove(scrollPane);
                gridPane.getChildren().remove(submitButton);
                gridPane.getChildren().remove(messageLabel);


                gridPane.add(guarantorNameLabel,0,3);
                gridPane.add(guarantorNameTextField,1,3);
                gridPane.add(initialPaymentLabel,0,4);
                gridPane.add(initialPaymentTextField,1,4);
                gridPane.add(instalmentAmountLabel,0,5);
                gridPane.add(instalmentAmountTextField,1,5);

                gridPane.add(startDatePicker,1,6);
                gridPane.add(upcomingInstalmentDateLabel,0,6);
                gridPane.add(remainingInstalmentsNumberTextField,1,7);
                gridPane.add(remainingInstalmentsNumberLabel,0,7);
                gridPane.add(addMoreProductsButton,0,8);
                gridPane.add(scrollPane,0,9);
                gridPane.add(submitButton,0,10);
                gridPane.add(messageLabel,0,11);


            }
            else
            {
                gridPane.getChildren().remove(guarantorNameLabel);
                gridPane.getChildren().remove(guarantorNameTextField);
                gridPane.getChildren().remove(initialPaymentLabel);
                gridPane.getChildren().remove(initialPaymentTextField);
                gridPane.getChildren().remove(instalmentAmountTextField);
                gridPane.getChildren().remove(instalmentAmountLabel);
                gridPane.getChildren().remove(startDatePicker);
                gridPane.getChildren().remove(upcomingInstalmentDateLabel);
                gridPane.getChildren().remove(remainingInstalmentsNumberTextField);
                gridPane.getChildren().remove(remainingInstalmentsNumberLabel);
                gridPane.getChildren().remove(addMoreProductsButton);
                gridPane.getChildren().remove(scrollPane);
                gridPane.getChildren().remove(submitButton);
                gridPane.getChildren().remove(messageLabel);


                gridPane.add(addMoreProductsButton,0,3);
                gridPane.add(scrollPane,0,4);
                gridPane.add(submitButton,0,5);
                gridPane.add(messageLabel,0,6);


            }

        });

        addMoreProductsButton.setOnAction(event -> {

            String paymentMethod = paymentMethodBox.getSelectionModel().getSelectedItem();

            if(paymentMethod == null)
                //ToDo : Show a warning that you must select payment method before choosing products
                System.out.println("You can't add product till you choose a payment method.");
            else
            {
                Label productLabel = new Label("أسم المنتج");
                ChoiceBox productsChoiceBox = new ChoiceBox();
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

                productsCategoriesChoiceBox.setOnAction( actionEvent -> {
                    ProductCategory selectedCategory = (ProductCategory) productsCategoriesChoiceBox.getValue();
                    ArrayList<Product> productsInCategory = StoreDatabase.getProductsInCategory(selectedCategory);
                    productsChoiceBox.setItems(FXCollections.observableList(productsInCategory));
                });


                Label soldQuantityLabel = new Label("الكمية المباعة");
                TextField soldQuantityTextField = new TextField();
                soldQuantityTextField.setPrefSize(100,20);


                Label sellingPriceLabel = new Label( "سعر البيع");
                TextField sellingPriceTextField = new TextField();
                sellingPriceTextField.setPrefSize(100,20);

                productsLabels.add(productLabel);
                productsChoiceBoxes.add(productsChoiceBox);
                categoriesLabels.add(categoryLabel);
                productsCategoriesChoiceBoxes.add(productsCategoriesChoiceBox);
                soldQuantitiesLabels.add(soldQuantityLabel);
                soldQuantitiesTextFields.add(soldQuantityTextField);
                sellingPricesTextFields.add(sellingPriceTextField);
                sellingPricesLabels.add(sellingPriceLabel);


                int currentProductRow = 0;
                int numOfProducts = productsLabels.size();
                currentProductRow += numOfProducts;


                int lastProductIndex = numOfProducts -1;

                productsGridPane.add(sellingPricesTextFields.get(lastProductIndex),7,currentProductRow);
                productsGridPane.add(sellingPricesLabels.get(lastProductIndex),6,currentProductRow);
                productsGridPane.add(soldQuantitiesTextFields.get(lastProductIndex),5,currentProductRow);
                productsGridPane.add(soldQuantitiesLabels.get(lastProductIndex),4,currentProductRow);
                productsGridPane.add(productsChoiceBoxes.get(lastProductIndex),3,currentProductRow);
                productsGridPane.add(productsLabels.get(lastProductIndex),2,currentProductRow);
                productsGridPane.add(productsCategoriesChoiceBoxes.get(lastProductIndex),1,currentProductRow);
                productsGridPane.add(categoriesLabels.get(lastProductIndex),0,currentProductRow);

            }



        });


        submitButton.setOnAction(event -> {
            //ToDo: complete the add bill function

            String message = "";
            messageLabel.setText(message);

            String billDate = billDatePicker.getValue().toString();
            String buyerName = buyerNameTextField.getText().trim();
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


            Bill bill;
            if(paymentMethod.equals("تقسيط"))
            {
                String guarantorName = guarantorNameTextField.getText().trim();
                double initialPayment = Double.parseDouble(initialPaymentTextField.getText().trim());
                double remainingMoney = billTotalCost - initialPayment;
                double instalmentAmount = Double.parseDouble(instalmentAmountTextField.getText().trim());
                String upcomingInstalmentDate = startDatePicker.getValue().toString();
                int remainingInstalmentsNumber = Integer.parseInt(remainingInstalmentsNumberTextField.getText().trim());
                BillInstalmentsDetails billInstalmentsDetails = new BillInstalmentsDetails(guarantorName, initialPayment, remainingMoney, instalmentAmount, upcomingInstalmentDate, remainingInstalmentsNumber);
                bill = new Bill(billDate, buyerName, productsTotalCost, billTotalCost, paymentMethod, soldProducts, billInstalmentsDetails);
            }
            else
                bill = new Bill(billDate, buyerName, productsTotalCost, billTotalCost, paymentMethod, soldProducts);


            boolean done = StoreDatabase.addBill(bill);


            //done = StoreDatabase.addBill();
            if(done)
                messageLabel.setText("!تم إدخال البيانات بنجاح");

        });


        gridPane.add(billDateLabel,0,0);
        gridPane.add(billDatePicker,1,0);
        gridPane.add(buyerNameLabel,0,1);
        gridPane.add(buyerNameTextField,1,1);
        gridPane.add(paymentMethodLabel,0,2);
        gridPane.add(paymentMethodBox,1,2);
        gridPane.add(addMoreProductsButton,0,3);
        gridPane.add(scrollPane,0,4);
        gridPane.add(submitButton,0,5);
        gridPane.add(messageLabel,0,6);

        ScrollPane pane = new ScrollPane(gridPane);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(pane);


        return borderPane;
    }

    private static Pane getClerkAddSupplySceneLayout() {
        Label messageLabel = new Label();

        Button submitButton = new Button("أدخل");
        submitButton.setPrefSize(100, 20);

        Label supplyDateLabel = new Label("تاريخ التوريد");
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

        Label supplierNameLabel = new Label("أسم المورد");
        ChoiceBox supplierChoiceBox = new ChoiceBox();
        supplierChoiceBox.setConverter(new StringConverter<Supplier>() {
            @Override
            public String toString(Supplier s) {
                return s.getSupplierName();
            }

            @Override
            public Supplier fromString(String s) {
                return null;
            }
        });
        ArrayList<Supplier> suppliers = StoreDatabase.getAllSuppliers();
        supplierChoiceBox.setItems(FXCollections.observableList(suppliers));

        Label transportationFessLabel = new Label( "مصاريف المواصلات");
        TextField transportationFeesTextField = new TextField();
        transportationFeesTextField.setPrefSize(100,20);

        Button addMoreProductsButton = new Button("أضف منتج للفاتورة");
        addMoreProductsButton.setPrefSize(120,20);

        Button addNewProductButton = new Button("أضف منتج جديد لقاعدة البيانات");
        addNewProductButton.setPrefSize(200,20);

        ArrayList<Label> productsLabels =new ArrayList<>();
        ArrayList<ChoiceBox> productsChoiceBoxes = new ArrayList<>();
        ArrayList<Label> categoriesLabels = new ArrayList<>();
        ArrayList<ChoiceBox> productsCategoriesChoiceBoxes = new ArrayList<>();
        ArrayList<Label> boughtQuantitiesLabels = new ArrayList<>();
        ArrayList<TextField> boughtQuantitiesTextFields = new ArrayList<>();
        ArrayList<Label> buyingsPricesLabels = new ArrayList<>();
        ArrayList<TextField> buyingsPricesTextFields = new ArrayList<>();

        ScrollPane scrollPane = new ScrollPane();
        GridPane productsGridPane = new GridPane();
        productsGridPane.setHgap(10);
        productsGridPane.setVgap(10);
        productsGridPane.setPadding(new Insets(10, 10, 10, 10));
        scrollPane.setContent(productsGridPane);

        addMoreProductsButton.setOnAction(event -> {

            Label productLabel = new Label("أسم المنتج");
            ChoiceBox productsChoiceBox = new ChoiceBox();
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


            productsCategoriesChoiceBox.setOnAction( actionEvent -> {
                ProductCategory selectedCategory = (ProductCategory) productsCategoriesChoiceBox.getValue();
                ArrayList<Product> productsInCategory = StoreDatabase.getProductsInCategory(selectedCategory);
                productsChoiceBox.setItems(FXCollections.observableList(productsInCategory));
            });




            Label boughtQuantityLabel = new Label("الكمية المشتراة");
            TextField boughtQuantityTextField = new TextField();
            boughtQuantityTextField.setPrefSize(100,20);


            Label buyingPriceLabel = new Label( "سعر الوحدة");
            TextField buyingPriceTextField = new TextField();
            buyingPriceTextField.setPrefSize(100,20);


            productsLabels.add(productLabel);
            productsChoiceBoxes.add(productsChoiceBox);
            categoriesLabels.add(categoryLabel);
            productsCategoriesChoiceBoxes.add(productsCategoriesChoiceBox);
            boughtQuantitiesLabels.add(boughtQuantityLabel);
            boughtQuantitiesTextFields.add(boughtQuantityTextField);
            buyingsPricesTextFields.add(buyingPriceTextField);
            buyingsPricesLabels.add(buyingPriceLabel);


            int currentProductRow = 0;
            int numOfProducts = productsLabels.size();
            currentProductRow += numOfProducts;

            int lastProductIndex = numOfProducts -1;

            productsGridPane.add(buyingsPricesTextFields.get(lastProductIndex),7,currentProductRow);
            productsGridPane.add(buyingsPricesLabels.get(lastProductIndex),6,currentProductRow);
            productsGridPane.add(boughtQuantitiesTextFields.get(lastProductIndex),5,currentProductRow);
            productsGridPane.add(boughtQuantitiesLabels.get(lastProductIndex),4,currentProductRow);
            productsGridPane.add(productsChoiceBoxes.get(lastProductIndex),3,currentProductRow);
            productsGridPane.add(productsLabels.get(lastProductIndex),2,currentProductRow);
            productsGridPane.add(productsCategoriesChoiceBoxes.get(lastProductIndex),1,currentProductRow);
            productsGridPane.add(categoriesLabels.get(lastProductIndex),0,currentProductRow);


        });

        submitButton.setOnAction(event -> {
            //ToDo: complete the add product function

            String message = "";
            messageLabel.setText(message);

            String supplyDate = supplyDatePicker.getValue().toString();
            Supplier supplier = (Supplier) supplierChoiceBox.getValue();
            double transportationFees = Double.parseDouble(transportationFeesTextField.getText().trim());


            ArrayList<BoughtProduct> boughtProducts = new ArrayList<>();
            double productsTotalCost = 0;
            int numOfBoughtProducts =  productsChoiceBoxes.size();
            for(int i = 0; i < numOfBoughtProducts; i++)
            {
                Product currentProduct = (Product) productsChoiceBoxes.get(i).getSelectionModel().getSelectedItem();
                int boughtQuantity = Integer.parseInt(boughtQuantitiesTextFields.get(i).getText().trim());
                double buyingPrice = Double.parseDouble(buyingsPricesTextFields.get(i).getText().trim());
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
            BorderPane addProductLayout = (BorderPane) getClerkAddProductSceneLayout();

            Scene addProductScene = new Scene(addProductLayout);
            addProductScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            Stage secondaryStage = new Stage();
            secondaryStage.initModality(Modality.APPLICATION_MODAL);
            secondaryStage.setTitle("واجهة إضافة منتج");
            secondaryStage.setScene(addProductScene);
            System.gc();
            secondaryStage.show();

        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(supplyDateLabel,0,0);
        gridPane.add(supplyDatePicker, 1,0);
        gridPane.add(supplierNameLabel,0,1);
        gridPane.add(supplierChoiceBox,1,1);
        gridPane.add(transportationFessLabel,0,2);
        gridPane.add(transportationFeesTextField,1,2);
        gridPane.add(addMoreProductsButton,0,3);
        gridPane.add(addNewProductButton, 1, 3);
        gridPane.add(scrollPane,0,4);
        gridPane.add(submitButton,0,5);
        gridPane.add(messageLabel,0,6);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getClerkShowUpcomingInstalmentsSceneLayout() {
        Label startDateLabel = new Label("من");
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

        Label endDateLabel = new Label("إلي");
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

        Button showInstalmentsButton = new Button("أعرض الأقساط");

        HBox topHBox = new HBox();
        topHBox.setSpacing(20);

        topHBox.getChildren().add(startDateLabel);
        topHBox.getChildren().add(startDatePicker);
        topHBox.getChildren().add(endDateLabel);
        topHBox.getChildren().add(endDatePicker);
        topHBox.getChildren().add(showInstalmentsButton);


        TableView instalmentsBillsTable = new TableView();
        instalmentsBillsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        instalmentsBillsTable.setRowFactory(tableView -> {
            TableRow<Bill> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                    Bill clickedBill = row.getItem();
                    showSoldProducts(clickedBill);

                }
            });
            return row ;
        });

        TableColumn<Bill, String> buyerNameColumn = new TableColumn<>("أسم العميل");
        TableColumn<Bill, Double> instalmentAmountColumn = new TableColumn<>("حجم القسط");
        TableColumn<Bill, String> instalmentDueDateColumn = new TableColumn<>("تاريخ إستحقاق القسط");
        TableColumn<Bill, Integer> remainingInstalmentsNumber = new TableColumn<>("عدد الأقساط المتبقية");

        buyerNameColumn.setCellValueFactory( new PropertyValueFactory<>("buyerName"));
        instalmentAmountColumn.setCellValueFactory(cellData -> {
            Bill instalmentBill = (Bill) ((TableColumn.CellDataFeatures) cellData).getValue();
            BillInstalmentsDetails billInstalmentsDetails = instalmentBill.getBillInstalmentsDetails();
            return new SimpleObjectProperty<>(billInstalmentsDetails.getInstalmentAmount());
        });
        instalmentDueDateColumn.setCellValueFactory(cellData -> {
            Bill instalmentBill = (Bill) ((TableColumn.CellDataFeatures) cellData).getValue();
            BillInstalmentsDetails billInstalmentsDetails = instalmentBill.getBillInstalmentsDetails();
            return new SimpleStringProperty(billInstalmentsDetails.getUpcomingInstalmentDate());
        });
        remainingInstalmentsNumber.setCellValueFactory(cellData -> {
            Bill instalmentBill = (Bill) ((TableColumn.CellDataFeatures) cellData).getValue();
            BillInstalmentsDetails billInstalmentsDetails = instalmentBill.getBillInstalmentsDetails();
            return new SimpleObjectProperty<>(billInstalmentsDetails.getRemainingInstalmentsNumber());
        });

        instalmentsBillsTable.getColumns().add(buyerNameColumn);
        instalmentsBillsTable.getColumns().add(instalmentAmountColumn);
        instalmentsBillsTable.getColumns().add(instalmentDueDateColumn);
        instalmentsBillsTable.getColumns().add(remainingInstalmentsNumber);

        
        showInstalmentsButton.setOnAction(event -> {
            String queryStartDate = startDatePicker.getValue().toString();
            String queryEndDate = endDatePicker.getValue().toString();

            ArrayList<Bill> upcomingInstalments = StoreDatabase.getUpcomingInstalments(queryStartDate, queryEndDate);
            instalmentsBillsTable.setItems(FXCollections.observableList(upcomingInstalments));
        });
        
        instalmentsBillsTable.setRowFactory(tableView -> {
            TableRow<Bill> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                    Bill clickedBill = row.getItem();
                    showPayInstalment(clickedBill);

                }
            });
            return row ;
        });
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setTop(topHBox);
        borderPane.setCenter(instalmentsBillsTable);

        return borderPane;
    }

    private static void showPayInstalment(Bill bill) {
        Label payInstalmentLabel = new Label("هل تريد دفع هذا القسط؟");
        Button yesButton = new Button("نعم");
        Button noButton = new Button("لا");
        Label messageLabel = new Label();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15));
        gridPane.add(payInstalmentLabel, 0,0);
        gridPane.add(yesButton,0, 1);
        gridPane.add(noButton, 2, 1);
        gridPane.add(messageLabel, 1,2);

        Scene scene = new Scene(gridPane);
        scene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);

        Stage secondaryStage = new Stage();

        yesButton.setOnAction(actionEvent -> {
            String instalmentDueDate = bill.getBillInstalmentsDetails().getUpcomingInstalmentDate();
            String paymentDate = LocalDate.now().toString();
            double paidAmount = bill.getBillInstalmentsDetails().getInstalmentAmount();

            PaidInstalment paidInstalment = new PaidInstalment(instalmentDueDate, paymentDate, paidAmount);
            boolean done = StoreDatabase.addPaidInstalment(paidInstalment, bill.getBillInstalmentsDetails(), bill.getBillID());
            if(done)
            {
                messageLabel.setText("");
                messageLabel.setText("تم إدخال البيانات بنجاح!");

            }
            else
            {
                messageLabel.setText("");
                messageLabel.setText("حدث خطأ أثناء إدخال البيانات!");
            }
        });
        
        noButton.setOnAction(actionEvent -> {
            secondaryStage.close();
        });
        


        secondaryStage.initModality(Modality.APPLICATION_MODAL);
        secondaryStage.initStyle(StageStyle.DECORATED);
        secondaryStage.setTitle("تأكيد دفع القسط");
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }



}
