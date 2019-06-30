package nubahome.gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import javafx.util.StringConverter;

import nubahome.databse.*;
import nubahome.main.Main;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class GUI {

    private static Stage mainStage;

    public static void open(Stage primaryStage) {

        mainStage = primaryStage;
        mainStage.setMaximized(true);

        String sceneTitle = "تسجيل الدخول";
        mainStage.setTitle(sceneTitle);
        Scene loginScene = new Scene(getManagerHomeSceneLayout());
        loginScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        mainStage.setScene(loginScene);
        mainStage.show();
    }

    private static Pane getLoginLayout() {

        String nodeText;
        nodeText = "أسم المستخدم";
        Label userNameLabel = new Label(nodeText);
        TextField userNameTextField = new TextField();
        userNameTextField.setPrefSize(50, 20);

        nodeText = "كلمة السر";
        Label passwordLabel = new Label(nodeText);
        TextField passwordTextField = new TextField();
        passwordTextField.setPrefSize(50, 20);

        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {

            //ToDo: complete the login function
            String userName = userNameTextField.getText();
            String password = passwordTextField.getText();

            Integer currentUserType = Main.myStore.login(userName,password);

            if(currentUserType.equals(Main.myStore.userType("manager")))
            {
                userNameTextField.clear();
                passwordTextField.clear();
                System.out.println("manager");
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Scene homeScene = new Scene(getManagerHomeSceneLayout());
                homeScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setScene(homeScene);
                System.gc();
                mainStage.show();
            }
            else if(currentUserType.equals(Main.myStore.userType("clerk")))
            {
                //ToDo: implement the clerk scene
                userNameTextField.clear();
                passwordTextField.clear();
                System.out.println("clerk");
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                Scene homeScene = new Scene(getClerkHomeSceneLayout());
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

        gridPane.add(userNameTextField, 0, 0);
        gridPane.add(userNameLabel, 1, 0);
        gridPane.add(passwordTextField, 0, 1);
        gridPane.add(passwordLabel, 1, 1);
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

            BorderPane suppliesLayout = (BorderPane) getSuppliesSceneLayout();
            suppliesLayout.setBottom(hBox);

            Scene suppliesScene = new Scene(suppliesLayout);
            suppliesScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة التوريدات");
            mainStage.setScene(suppliesScene);
            System.gc();
            mainStage.show();
        });

        String billsAndInstalmentsButtonText = "الفواتير و الأقساط";
        Button billsAndInstalmentsButton = new Button(billsAndInstalmentsButtonText);
        billsAndInstalmentsButton.setPrefSize(150, 50);

        billsAndInstalmentsButton.setOnAction(actionEvent -> {
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

            BorderPane billsAndInstalmentLayout = (BorderPane) getBillsAndInstalmentsSceneLayout();
            billsAndInstalmentLayout.setBottom(hBox);

            Scene billsAndInstalmentScene = new Scene(billsAndInstalmentLayout);
            billsAndInstalmentScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة الفواتير و الأقساط");
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

            BorderPane productsLayout = (BorderPane) getProductsSceneLayout();
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

            BorderPane suppliersLayout = (BorderPane) getSuppliersSceneLayout();
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

            BorderPane usersLayout = (BorderPane) getUsersSceneLayout();
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
        gridPane.add(billsAndInstalmentsButton, 0, 1);
        gridPane.add(productsButton, 1, 1);
        gridPane.add(SuppliersButton, 0, 2);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        return borderPane;

    }

    private static Pane getClerkHomeSceneLayout() {
        Button addBillButton = new Button("إضافة فاتورة");
        addBillButton.setPrefSize(150,50);

        Button addSupplyButton = new Button("إضافة توريد");
        addSupplyButton.setPrefSize(150,50);

        Button showInstalmentsButton = new Button("عرض الأقساط");
        showInstalmentsButton.setPrefSize(150,50);

        addBillButton.setOnAction(actionEvent -> {
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

            BorderPane addBillLayout = (BorderPane) getAddBillSceneLayout();
            addBillLayout.setBottom(hBox);

            Scene addBillScene = new Scene(addBillLayout);
            addBillScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة إضافة الفاتورة");
            mainStage.setScene(addBillScene);
            System.gc();
            mainStage.show();
        });

        addSupplyButton.setOnAction(actionEvent -> {
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

            BorderPane addSupplyLayout = (BorderPane) getAddSupplySceneLayout();
            addSupplyLayout.setBottom(hBox);

            Scene addSupplyScene = new Scene(addSupplyLayout);
            addSupplyScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle( "واجهة إضافة توريد");
            mainStage.setScene(addSupplyScene);
            System.gc();
            mainStage.show();
        });

        showInstalmentsButton.setOnAction(actionEvent -> {
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

            BorderPane showInstalmentsSceneLayout = (BorderPane) getShowInstalmentsSceneLayout();
            showInstalmentsSceneLayout.setBottom(hBox);

            Scene showInstalmentsScene = new Scene(showInstalmentsSceneLayout);
            showInstalmentsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة عرض الأقساط");
            mainStage.setScene(showInstalmentsScene);
            System.gc();
            mainStage.show();
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(showInstalmentsButton,2,0);
        gridPane.add(addSupplyButton,1,0);
        gridPane.add(addBillButton,0,0);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        return borderPane;

    }

    private static Pane getUsersSceneLayout() {
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
                BorderPane usersLayout = (BorderPane) getUsersSceneLayout();

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

            BorderPane showUsersSceneLayout = (BorderPane) getShowUsersSceneLayout();
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
                BorderPane usersLayout = (BorderPane) getUsersSceneLayout();

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

            BorderPane addUserLayout = (BorderPane) getAddUserSceneLayout();
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
                BorderPane usersLayout = (BorderPane) getUsersSceneLayout();

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

            BorderPane editUserLayout = (BorderPane) getEditUserSceneLayout();
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

    private static Pane getEditUserSceneLayout() {
        ChoiceBox usersChoiceBox = new ChoiceBox();
        Label usersLabel = new Label("اختار المستخدم");
        ArrayList<User> users = Main.myStore.getAllUsers();
        ArrayList<String> usersNames = new ArrayList<>();

        for(User x : users)
            usersNames.add(x.getName());

        usersChoiceBox.setItems(FXCollections.observableList(usersNames));

        Button deleteUserButton = new Button("حذف المستخدم");
        deleteUserButton.setPrefSize(200,50);

        Button updateUserBtutton = new Button("تحديث بيانات المستخدم");
        updateUserBtutton.setPrefSize(200,50);

        Label messageLabel = new Label();

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        Button updatePasswordButton = new Button("تحديث كلمة السر");
        updatePasswordButton.setPrefSize(180,50);

        Button updateNameButton = new Button("تحديث أسم المستخدم");
        updateNameButton.setPrefSize(180,50);

        Button updateTypeButton = new Button("تحديث وظيفة المستخدم");
        updateTypeButton.setPrefSize(180,50);

        deleteUserButton.setOnAction(actionEvent -> {
            gridPane.getChildren().remove(updateNameButton);
            gridPane.getChildren().remove(updatePasswordButton);
            gridPane.getChildren().remove(updateTypeButton);
            gridPane.getChildren().remove(messageLabel);

            gridPane.add(messageLabel,0,2);

            String message = "";
            messageLabel.setText(message);

            User selectedUser = users.get(usersChoiceBox.getSelectionModel().getSelectedIndex());
            Main.myStore.deleteUser(selectedUser.getName());
            message = "تم الحذف بنجاح";
            messageLabel.setText(message);

            ArrayList <User> undeletedUsers = Main.myStore.getAllUsers();
            ArrayList<String> undeletedUsersNames = new ArrayList<>();
            for(User x : undeletedUsers)
                undeletedUsersNames.add(x.getName());
            usersChoiceBox.setItems(FXCollections.observableList(undeletedUsersNames));


            gridPane.getChildren().clear();

            gridPane.add(usersChoiceBox,0,0);
            gridPane.add(usersLabel,1,0);
            gridPane.add(deleteUserButton,0,1);
            gridPane.add(updateUserBtutton,1,1);
            gridPane.add(messageLabel,0,2);

        });

        updateUserBtutton.setOnAction(actionEvent -> {
            gridPane.getChildren().remove(messageLabel);

            gridPane.add(updatePasswordButton,0,2);
            gridPane.add(updateNameButton,1,2);
            gridPane.add(updateTypeButton,2,2);
        });

        updateNameButton.setOnAction(actionEvent -> {
            TextField newNameTextField = new TextField();
            newNameTextField.setPrefSize(100,20);

            Label newNameLabel = new Label("الأسم الجديد");

            Button submitButton = new Button("أدخل");

            submitButton.setOnAction(event -> {
                String message = "";
                messageLabel.setText(message);

                String newName = newNameTextField.getText().trim();
                User selectedUser = users.get(usersChoiceBox.getSelectionModel().getSelectedIndex());
                Main.myStore.updateUserName(selectedUser, newName);

                message = "تم التعديل بنجاح";
                messageLabel.setText(message);

                gridPane.getChildren().clear();

                gridPane.add(usersChoiceBox,0,0);
                gridPane.add(usersLabel,1,0);
                gridPane.add(deleteUserButton,0,1);
                gridPane.add(updateUserBtutton,1,1);
                gridPane.add(messageLabel,0,2);

            });

            gridPane.add(newNameTextField,0,3);
            gridPane.add(newNameLabel,1,3);
            gridPane.add(submitButton,0,4);
            gridPane.add(messageLabel,0,5);
        });

        updatePasswordButton.setOnAction(actionEvent -> {
            TextField newPasswordTextField = new TextField();
            newPasswordTextField.setPrefSize(100,20);

            Label newPasswordLabel = new Label("كلمة السر الجديدة");

            Button submitButton = new Button("أدخل");

            submitButton.setOnAction(event -> {
                String message = "";
                messageLabel.setText(message);

                String newPassword = newPasswordTextField.getText().trim();
                User selectedUser = users.get(usersChoiceBox.getSelectionModel().getSelectedIndex());
                Main.myStore.updateUserPassword(selectedUser, newPassword);

                message = "تم التعديل بنجاح";
                messageLabel.setText(message);

                gridPane.getChildren().clear();

                gridPane.add(usersChoiceBox,0,0);
                gridPane.add(usersLabel,1,0);
                gridPane.add(deleteUserButton,0,1);
                gridPane.add(updateUserBtutton,1,1);
                gridPane.add(messageLabel,0,2);
            });

            gridPane.add(newPasswordTextField,0,3);
            gridPane.add(newPasswordLabel,1,3);
            gridPane.add(submitButton,0,4);
            gridPane.add(messageLabel,0,5);
        });

        updateTypeButton.setOnAction(actionEvent -> {
            Label newPasswordLabel = new Label("الوظيفة الجديدة");

            ChoiceBox newTypeChoiceBox = new ChoiceBox();
            ArrayList<String> typesList = Main.myStore.getUserTypes();
            newTypeChoiceBox.setItems(FXCollections.observableList(typesList));

            Button submitButton = new Button("أدخل");
            submitButton.setOnAction(event->{
                String message = "";
                messageLabel.setText(message);

                int newUserType = newTypeChoiceBox.getSelectionModel().getSelectedIndex()+1;
                User selectedUser = users.get(usersChoiceBox.getSelectionModel().getSelectedIndex());
                Main.myStore.updateUserType(selectedUser, newUserType);

                message = "تم التعديل بنجاح";
                messageLabel.setText(message);

                gridPane.getChildren().clear();

                gridPane.add(usersChoiceBox,0,0);
                gridPane.add(usersLabel,1,0);
                gridPane.add(deleteUserButton,0,1);
                gridPane.add(updateUserBtutton,1,1);
                gridPane.add(messageLabel,0,2);
            });

            gridPane.add(newTypeChoiceBox,0,3);
            gridPane.add(newPasswordLabel,1,3);
            gridPane.add(submitButton,0,4);
            gridPane.add(messageLabel,0,5);
        });


        gridPane.add(usersChoiceBox,0,0);
        gridPane.add(usersLabel,1,0);
        gridPane.add(deleteUserButton,0,1);
        gridPane.add(updateUserBtutton,1,1);
        gridPane.add(messageLabel,0,2);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getAddUserSceneLayout() {
        String nodeText;

        nodeText = "أسم المستخدم";
        Label userNameLabel = new Label(nodeText);
        TextField userNameTextField = new TextField();
        userNameTextField.setPrefSize(100,20);

        nodeText = "كلمة السر";
        Label userPasswordLabel = new Label(nodeText);
        TextField userPasswordTextField = new TextField();
        userPasswordTextField.setPrefSize(100,20);

        nodeText = "نوع المستخدم";
        Label userTypeLabel = new Label(nodeText);
        ChoiceBox userTypesChoiceBox = new ChoiceBox();
        ArrayList<String> typesList = Main.myStore.getUserTypes();
        userTypesChoiceBox.setItems(FXCollections.observableList(typesList));

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
            int userType = userTypesChoiceBox.getSelectionModel().getSelectedIndex()+1;

            boolean done = false;
            done = Main.myStore.addUser(userName,userPassword,userType);
            if(done)
            {
                message = "تم الإدخال بنجاح";
                messageLabel.setText(message);

            }


        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(userNameTextField,0,0);
        gridPane.add(userNameLabel,1,0);
        gridPane.add(userPasswordTextField,0,1);
        gridPane.add(userPasswordLabel,1,1);
        gridPane.add(userTypesChoiceBox,0,2);
        gridPane.add(userTypeLabel,1,2);
        gridPane.add(submitButton,0,3);
        gridPane.add(messageLabel,0,4);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);



        return borderPane;
    }

    private static Pane getShowUsersSceneLayout() {
        TableColumn userName = new TableColumn("أسم المستخدم");
        TableColumn userType = new TableColumn("وظيفة المستخدم");

        TableView usersTable = new TableView();
        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        usersTable.getColumns().add(userType);
        usersTable.getColumns().add(userName);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(usersTable);

        return borderPane;
    }

    private static Pane getSuppliesSceneLayout()    {
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

                BorderPane suppliesLayout = (BorderPane) getSuppliesSceneLayout();
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

            BorderPane showSuppliesSceneLayout = (BorderPane) getShowSuppliesSceneLayout();
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

                BorderPane suppliesLayout = (BorderPane) getSuppliesSceneLayout();
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


            BorderPane addSupplyLayout = (BorderPane) getAddSupplySceneLayout();
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

                BorderPane suppliesLayout = (BorderPane) getSuppliesSceneLayout();
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


            BorderPane editSupplyLayout = (BorderPane) getEditSupplySceneLayout();
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

    private static Pane getAddSupplySceneLayout() {
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
        ArrayList<String> suppliers = Main.myStore.getSuppliers();
        supplierChoiceBox.setItems(FXCollections.observableList(suppliers));

        Label transportationFessLabel = new Label( "مصاريف المواصلات");
        TextField transportationFeesTextField = new TextField();
        transportationFeesTextField.setPrefSize(100,20);

        Button addMoreProductsButton = new Button("أضف منتج للفاتورة");
        addMoreProductsButton.setPrefSize(120,20);

        ArrayList<Label> productsLabels =new ArrayList<>();
        ArrayList<ChoiceBox> productsChoiceBoxes = new ArrayList<>();
        ArrayList<Label> categoriesLabels = new ArrayList<>();
        ArrayList<ChoiceBox> categoriesChoiceBoxes = new ArrayList<>();
        ArrayList<Label> boughtQuantitiesLabels = new ArrayList<>();
        ArrayList<TextField> boughtQuantitiesTextFields = new ArrayList<>();
        ArrayList<Label> unitsPricesLabels = new ArrayList<>();
        ArrayList<TextField> unitsPricesTextFields = new ArrayList<>();

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
            ChoiceBox categoriesChoiceBox = new ChoiceBox();
            ArrayList<String> categoriesList = Main.myStore.getCategories();
            categoriesChoiceBox.setItems(FXCollections.observableList(categoriesList));

            categoriesChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    int categoryID = categoriesChoiceBox.getSelectionModel().getSelectedIndex()+1;

                    ArrayList<Product> products = Main.myStore.getProductsInCategory(categoryID);

                    productsChoiceBox.setItems(FXCollections.observableList(products));
                }
            });

            productsChoiceBox.getSelectionModel().selectedItemProperty().addListener((ChangeListener<Product>) (observableValue, s, t1) -> {
                Product selectedProduct = (Product) productsChoiceBox.getSelectionModel().getSelectedItem();

                if(selectedProduct != null && selectedProduct.getProductName().equals("أخري"))
                {
                    BorderPane addProductLayout = (BorderPane) getAddProductSceneLayout();

                    Scene addProductScene = new Scene(addProductLayout);
                    addProductScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                    Stage secondaryStage = new Stage();
                    secondaryStage.setAlwaysOnTop(true);
                    secondaryStage.setTitle("واجهة إضافة منتج");
                    secondaryStage.setScene(addProductScene);
                    System.gc();
                    secondaryStage.show();

                    secondaryStage.setOnCloseRequest(windowEvent -> {
                        int categoryID = categoriesChoiceBox.getSelectionModel().getSelectedIndex() + 1;
                        ArrayList<Product> products = Main.myStore.getProductsInCategory(categoryID);

                        productsChoiceBox.setItems(FXCollections.observableList(products));
                        secondaryStage.close();
                    });


                }
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
            categoriesChoiceBoxes.add(categoriesChoiceBox);
            boughtQuantitiesLabels.add(boughtQuantityLabel);
            boughtQuantitiesTextFields.add(boughtQuantityTextField);
            unitsPricesTextFields.add(buyingPriceTextField);
            unitsPricesLabels.add(buyingPriceLabel);


            int currentProductRow = 0;
            int numOfProducts = productsLabels.size();
            currentProductRow += numOfProducts;

            int lastProductIndex = numOfProducts -1;

            productsGridPane.add(unitsPricesTextFields.get(lastProductIndex),7,currentProductRow);
            productsGridPane.add(unitsPricesLabels.get(lastProductIndex),6,currentProductRow);
            productsGridPane.add(boughtQuantitiesTextFields.get(lastProductIndex),5,currentProductRow);
            productsGridPane.add(boughtQuantitiesLabels.get(lastProductIndex),4,currentProductRow);
            productsGridPane.add(productsChoiceBoxes.get(lastProductIndex),3,currentProductRow);
            productsGridPane.add(productsLabels.get(lastProductIndex),2,currentProductRow);
            productsGridPane.add(categoriesChoiceBoxes.get(lastProductIndex),1,currentProductRow);
            productsGridPane.add(categoriesLabels.get(lastProductIndex),0,currentProductRow);


        });

        submitButton.setOnAction(event -> {
            //ToDo: complete the add product function

            String message = "";
            messageLabel.setText(message);

            String supplyDate = supplyDatePicker.getValue().toString();
            String supplierName = supplierChoiceBox.getSelectionModel().getSelectedItem().toString();

            int numOfBoughtProducts =  productsChoiceBoxes.size();

            ArrayList<Integer> boughtProductsIDs = new ArrayList<>();

            for(int i = 0; i < numOfBoughtProducts ; i++)
            {
                Product selectedProduct = (Product) productsChoiceBoxes.get(i).getSelectionModel().getSelectedItem();
                boughtProductsIDs.add(selectedProduct.getID());
            }

            ArrayList<Integer> boughtQuantities = new ArrayList<>();
            for(int i = 0; i < numOfBoughtProducts; i++)
                boughtQuantities.add(Integer.parseInt(boughtQuantitiesTextFields.get(i).getText().trim()));


            ArrayList<Double> unitsPrices = new ArrayList<>();
            for(int i = 0; i < numOfBoughtProducts; i++)
                unitsPrices.add(Double.parseDouble(unitsPricesTextFields.get(i).getText().trim()));

            double productsTotalCost = 0;
            for(int i = 0; i < numOfBoughtProducts; i++)
                productsTotalCost += unitsPrices.get(i) * boughtQuantities.get(i);

            double transportationFees = Double.parseDouble(transportationFeesTextField.getText().trim());
            double supplyTotalCost = productsTotalCost + transportationFees;
            boolean done = false;

            done = Main.myStore.addSupply(supplyDate, supplierName, boughtProductsIDs, boughtQuantities, unitsPrices, transportationFees, productsTotalCost, supplyTotalCost);

            if(done)
            {
                message = "تم الإدخال بنجاح";
                messageLabel.setText(message);

            }

        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(supplyDatePicker, 0,0);
        gridPane.add(supplierNameLabel,0,1);
        gridPane.add(supplierChoiceBox,1,1);
        gridPane.add(transportationFessLabel,0,2);
        gridPane.add(transportationFeesTextField,1,2);
        gridPane.add(addMoreProductsButton,0,3);
        gridPane.add(scrollPane,0,4);
        gridPane.add(submitButton,0,5);
        gridPane.add(messageLabel,0,6);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getEditSupplySceneLayout() {
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

    private static Pane getShowSuppliesSceneLayout() {

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

        supplyID.setCellValueFactory(new PropertyValueFactory<Supply, Integer>("ID"));
        supplyDate.setCellValueFactory(new PropertyValueFactory<Supply, String>("date"));
        supplierName.setCellValueFactory(new PropertyValueFactory<Supply, String>("supplierName"));
        productsTotalCost.setCellValueFactory(new PropertyValueFactory<Supply, Double>("productsTotalCost"));
        transportationFees.setCellValueFactory(new PropertyValueFactory<Supply, Double>("transportationFees"));
        supplyTotalCost.setCellValueFactory(new PropertyValueFactory<Supply, Double>("supplyTotalCost"));

        TableView suppliesTable = new TableView();
        suppliesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        suppliesTable.setRowFactory(tableView -> {
            TableRow<Supply> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

                    Supply clickedSupply = row.getItem();
                    showSupplyDetails(clickedSupply);

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
            ArrayList<Supply> supplies = Main.myStore.getAllSupplies();
            suppliesTable.setItems(FXCollections.observableList(supplies));
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));

        borderPane.setTop(topHBox);
        borderPane.setCenter(suppliesTable);

        return borderPane;
    }

    private static Pane getBillsAndInstalmentsSceneLayout() {
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

                BorderPane billsAndInstalmentsLayout = (BorderPane) getBillsAndInstalmentsSceneLayout();
                billsAndInstalmentsLayout.setBottom(hBox);

                Scene billsAndInstalmentsScene = new Scene(billsAndInstalmentsLayout);
                billsAndInstalmentsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الفواتير و الأقساط");
                mainStage.setScene(billsAndInstalmentsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane showBillsSceneLayout = (BorderPane) getShowBillsSceneLayout();
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

                BorderPane billsAndInstalmentsLayout = (BorderPane) getBillsAndInstalmentsSceneLayout();
                billsAndInstalmentsLayout.setBottom(hBox);

                Scene billsAndInstalmentsScene = new Scene(billsAndInstalmentsLayout);
                billsAndInstalmentsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الفواتير و الأقساط");
                mainStage.setScene(billsAndInstalmentsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane addBillLayout = (BorderPane) getAddBillSceneLayout();
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

                BorderPane billsAndInstalmentsLayout = (BorderPane) getBillsAndInstalmentsSceneLayout();
                billsAndInstalmentsLayout.setBottom(hBox);

                Scene billsAndInstalmentsScene = new Scene(billsAndInstalmentsLayout);
                billsAndInstalmentsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الفواتير و الأقساط");
                mainStage.setScene(billsAndInstalmentsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane editBillLayout = (BorderPane) getEditBillSceneLayout();
            editBillLayout.setBottom(hBox);

            Scene editBillScene = new Scene(editBillLayout);
            editBillScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle( "واجهة تعديل الفاتورة");
            mainStage.setScene(editBillScene);
            System.gc();
            mainStage.show();

        });

        String showInstalmentsButtonText = "عرض الأقساط";
        Button showInstalmentsButton = new Button(showInstalmentsButtonText);
        showInstalmentsButton.setPrefSize(150, 30);
        showInstalmentsButton.setOnAction(event -> {
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

                BorderPane billsAndInstalmentsLayout = (BorderPane) getBillsAndInstalmentsSceneLayout();
                billsAndInstalmentsLayout.setBottom(hBox);

                Scene billsAndInstalmentsScene = new Scene(billsAndInstalmentsLayout);
                billsAndInstalmentsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الفواتير و الأقساط");
                mainStage.setScene(billsAndInstalmentsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);

            BorderPane showInstalmentsSceneLayout = (BorderPane) getShowInstalmentsSceneLayout();
            showInstalmentsSceneLayout.setBottom(hBox);

            Scene showInstalmentsScene = new Scene(showInstalmentsSceneLayout);
            showInstalmentsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة عرض الأقساط");
            mainStage.setScene(showInstalmentsScene);
            System.gc();
            mainStage.show();
        });

        String editInstalmentButtonText = "تعديل قسط";
        Button editInstalmentButton = new Button(editInstalmentButtonText);
        editInstalmentButton.setPrefSize(150, 30);
        editInstalmentButton.setOnAction(event -> {
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

                BorderPane billsAndInstalmentsLayout = (BorderPane) getBillsAndInstalmentsSceneLayout();
                billsAndInstalmentsLayout.setBottom(hBox);

                Scene billsAndInstalmentsScene = new Scene(billsAndInstalmentsLayout);
                billsAndInstalmentsScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
                mainStage.setTitle("واجهة الفواتير و الأقساط");
                mainStage.setScene(billsAndInstalmentsScene);
                System.gc();
                mainStage.show();
            });

            HBox hBox = new HBox();
            hBox.setSpacing(10);
            hBox.getChildren().add(previousButton);
            hBox.getChildren().add(homeButton);


            BorderPane editInstalmentSceneLayout = (BorderPane) getEditInstalmentSceneLayout();
            editInstalmentSceneLayout.setBottom(hBox);

            Scene editInstalmentScene = new Scene(editInstalmentSceneLayout);
            editInstalmentScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle("واجهة تعديل قسط");
            mainStage.setScene(editInstalmentScene);
            System.gc();
            mainStage.show();
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(addBillButton, 2, 0);
        gridPane.add(showBillsButton, 1, 0);
        gridPane.add(showInstalmentsButton,0,0);
        gridPane.add(editInstalmentButton,1,1);
        gridPane.add(editBillButton, 2, 1);



        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;

    }

    private static Pane getAddBillSceneLayout() {
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
        ChoiceBox<String> paymentChoiceBox = new ChoiceBox<>();
        ArrayList<String> paymentChoices = new ArrayList<>();
        paymentChoices.add("كاش");
        paymentChoices.add("تقسيط");
        paymentChoiceBox.setItems(FXCollections.observableList(paymentChoices));

        nodeText = "أسم الضامن";
        Label guarantorNameLabel = new Label(nodeText);
        TextField guarantorNameTextField = new TextField();
        guarantorNameTextField.setPrefSize(100,20);

        nodeText = "المقدم";
        Label paidMoneyLabel = new Label(nodeText);
        TextField paidMoneyTextField = new TextField();
        paidMoneyTextField.setPrefSize(100,20);

        nodeText = "بداية القسط";
        Label startOfInstalmentLabel = new Label(nodeText);
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

        nodeText = "نهاية القسط";
        Label endOfInstalmentLabel = new Label(nodeText);
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
        ArrayList<ChoiceBox> categoriesChoiceBoxes = new ArrayList<>();
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



        paymentChoiceBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String paymentMethod= paymentChoiceBox.getSelectionModel().getSelectedItem();
                if(paymentMethod.equals("تقسيط"))
                {
                    gridPane.getChildren().remove(addMoreProductsButton);
                    gridPane.getChildren().remove(scrollPane);
                    gridPane.getChildren().remove(submitButton);
                    gridPane.getChildren().remove(messageLabel);


                    gridPane.add(guarantorNameLabel,0,3);
                    gridPane.add(guarantorNameTextField,1,3);
                    gridPane.add(paidMoneyLabel,0,4);
                    gridPane.add(paidMoneyTextField,1,4);
                    gridPane.add(instalmentAmountLabel,0,5);
                    gridPane.add(instalmentAmountTextField,1,5);

                    gridPane.add(startDatePicker,1,6);
                    gridPane.add(startOfInstalmentLabel,0,6);
                    gridPane.add(endDatePicker,1,7);
                    gridPane.add(endOfInstalmentLabel,0,7);
                    gridPane.add(addMoreProductsButton,0,8);
                    gridPane.add(scrollPane,0,9);
                    gridPane.add(submitButton,0,10);
                    gridPane.add(messageLabel,0,11);


                }
                else
                {
                    gridPane.getChildren().remove(guarantorNameLabel);
                    gridPane.getChildren().remove(guarantorNameTextField);
                    gridPane.getChildren().remove(paidMoneyLabel);
                    gridPane.getChildren().remove(paidMoneyTextField);
                    gridPane.getChildren().remove(instalmentAmountTextField);
                    gridPane.getChildren().remove(instalmentAmountLabel);
                    gridPane.getChildren().remove(startDatePicker);
                    gridPane.getChildren().remove(startOfInstalmentLabel);
                    gridPane.getChildren().remove(endDatePicker);
                    gridPane.getChildren().remove(endOfInstalmentLabel);
                    gridPane.getChildren().remove(addMoreProductsButton);
                    gridPane.getChildren().remove(scrollPane);
                    gridPane.getChildren().remove(submitButton);
                    gridPane.getChildren().remove(messageLabel);


                    gridPane.add(addMoreProductsButton,0,3);
                    gridPane.add(scrollPane,0,4);
                    gridPane.add(submitButton,0,5);
                    gridPane.add(messageLabel,0,6);


                }
            }
        });

        addMoreProductsButton.setOnAction(event -> {

            String paymentMethod = paymentChoiceBox.getSelectionModel().getSelectedItem();

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
                ChoiceBox categoriesChoiceBox = new ChoiceBox();
                ArrayList<String> categoriesList = Main.myStore.getCategories();

                categoriesChoiceBox.setItems(FXCollections.observableList(categoriesList));


                categoriesChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                        int categoryID = categoriesChoiceBox.getSelectionModel().getSelectedIndex()+1;

                        ArrayList<Product> products = Main.myStore.getProductsInCategory(categoryID);

                        productsChoiceBox.setItems(FXCollections.observableList(products));


                    }
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
                categoriesChoiceBoxes.add(categoriesChoiceBox);
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
                productsGridPane.add(categoriesChoiceBoxes.get(lastProductIndex),1,currentProductRow);
                productsGridPane.add(categoriesLabels.get(lastProductIndex),0,currentProductRow);

            }



        });


        submitButton.setOnAction(event -> {
            //ToDo: complete the add bill function

            String message = "";
            messageLabel.setText(message);

            String billDate = billDatePicker.getValue().toString();
            String buyerName = buyerNameTextField.getText().trim();
            String paymentChoice = paymentChoiceBox.getValue();

            int numOfSoldProducts =  productsChoiceBoxes.size();

            ArrayList<Integer> soldProductsIDs = new ArrayList<>();

            for(int i = 0; i < numOfSoldProducts; i++)
            {

                Product selectedProduct = (Product) productsChoiceBoxes.get(i).getSelectionModel().getSelectedItem();

                soldProductsIDs.add(selectedProduct.getID());
            }

            ArrayList<Integer> soldQuantities = new ArrayList<>();
            for(int i = 0; i < numOfSoldProducts; i++)
                soldQuantities.add(Integer.parseInt(soldQuantitiesTextFields.get(i).getText().trim()));


            ArrayList<Double> sellingPrices = new ArrayList<>();
            for(int i = 0; i < numOfSoldProducts; i++)
                sellingPrices.add(Double.parseDouble(sellingPricesTextFields.get(i).getText().trim()));

            double totalCost = 0;
            for(int i = 0; i < numOfSoldProducts; i++)
                totalCost += sellingPrices.get(i) * soldQuantities.get(i);

            boolean done = false;

            if(paymentChoice.equals("تقسيط"))
            {
                String guarantorName = guarantorNameTextField.getText().trim();
                double paidMoney = Double.parseDouble(paidMoneyTextField.getText().trim());

                double instalmentAmount = Double.parseDouble(instalmentAmountTextField.getText().trim());
                String startDate = startDatePicker.getValue().toString();
                String endDate = endDatePicker.getValue().toString();

                done = Main.myStore.addInstalment(billDate, buyerName, soldProductsIDs, soldQuantities, sellingPrices, totalCost, guarantorName, paidMoney, instalmentAmount, startDate, endDate);
            }
            else
                done = Main.myStore.addBill(billDate, buyerName, soldProductsIDs, soldQuantities, sellingPrices, totalCost, "كاش");



            //done = Main.myStore.addBill();
            if(done)
            {
                message = "تم الإدخال بنجاح";
                messageLabel.setText(message);

            }

        });


        gridPane.add(billDateLabel,0,0);
        gridPane.add(billDatePicker,1,0);
        gridPane.add(buyerNameLabel,0,1);
        gridPane.add(buyerNameTextField,1,1);
        gridPane.add(paymentMethodLabel,0,2);
        gridPane.add(paymentChoiceBox,1,2);
        gridPane.add(addMoreProductsButton,0,3);
        gridPane.add(scrollPane,0,4);
        gridPane.add(submitButton,0,5);
        gridPane.add(messageLabel,0,6);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getEditBillSceneLayout() {
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

    private static Pane getShowBillsSceneLayout() {

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
                    showBillDetails(clickedBill);

                }
            });
            return row ;
        });

        TableColumn buyerName = new TableColumn("أسم العميل");
        TableColumn billID = new TableColumn("رقم الفاتورة");
        TableColumn billDate = new TableColumn("تاريخ الفاتورة");
        TableColumn billTotalCost = new TableColumn("التكلفة الكلية");
        TableColumn paymentMethod = new TableColumn("وسيلة الدفع");

        billID.setCellValueFactory( new PropertyValueFactory<Bill, Integer>("ID"));
        buyerName.setCellValueFactory( new PropertyValueFactory<Bill, String>("buyerName"));
        billDate.setCellValueFactory( new PropertyValueFactory<Bill, Date>("date"));
        billTotalCost.setCellValueFactory( new PropertyValueFactory<Bill, Double>("totalCost"));
        paymentMethod.setCellValueFactory(new PropertyValueFactory<Bill, String>("paymentMethod"));

        billsTable.getColumns().add(billID);
        billsTable.getColumns().add(billDate);
        billsTable.getColumns().add(buyerName);
        billsTable.getColumns().add(billTotalCost);
        billsTable.getColumns().add(paymentMethod);


        showBillsButton.setOnAction(event -> {
            ArrayList<Bill> bills = Main.myStore.getAllBills();
            billsTable.setItems(FXCollections.observableList(bills));
        });


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setTop(topHBox);
        borderPane.setCenter(billsTable);

        return borderPane;
    }

    private static Pane getEditInstalmentSceneLayout() {
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

    private static Pane getShowInstalmentsSceneLayout() {
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

        TableView instalmentsTable = new TableView();
        instalmentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn buyerName = new TableColumn("أسم العميل");
        buyerName.setCellValueFactory(new PropertyValueFactory<Instalment, String>("buyerName"));

        TableColumn billID = new TableColumn("رقم الفاتورة");
        billID.setCellValueFactory( new PropertyValueFactory<Instalment, Integer>("billID"));

        TableColumn instalmentAmount = new TableColumn("حجم القسط");
        instalmentAmount.setCellValueFactory(new PropertyValueFactory<Instalment, Double>("instalmentAmount"));

        TableColumn endOfInstalments = new TableColumn("نهاية الأقساط");
        endOfInstalments.setCellValueFactory(new PropertyValueFactory<Instalment, String>("endDate"));

        instalmentsTable.getColumns().add(billID);
        instalmentsTable.getColumns().add(buyerName);
        instalmentsTable.getColumns().add(endOfInstalments);
        instalmentsTable.getColumns().add(instalmentAmount);


        showInstalmentsButton.setOnAction(actionEvent -> {
            ArrayList<Instalment> instalments = Main.myStore.getAllInstalments();
            System.out.println(instalments.size());
            instalmentsTable.setItems(FXCollections.observableList(instalments));
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setTop(topHBox);
        borderPane.setCenter(instalmentsTable);

        return borderPane;
    }

    private static Pane getProductsSceneLayout() {
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

                BorderPane productsLayout = (BorderPane) getProductsSceneLayout();
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

            BorderPane showProductsSceneLayout = (BorderPane) getShowProductsSceneLayout();
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

                BorderPane productsLayout = (BorderPane) getProductsSceneLayout();
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

            BorderPane addProductLayout = (BorderPane) getAddProductSceneLayout();
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

                BorderPane productsLayout = (BorderPane) getProductsSceneLayout();
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

            BorderPane editProductLayout = (BorderPane) getEditProductSceneLayout();
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

                BorderPane productsLayout = (BorderPane) getProductsSceneLayout();
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

            BorderPane showCategoriesSceneLayout = (BorderPane) getShowCategoriesSceneLayout();
            showCategoriesSceneLayout.setBottom(hBox);

            Scene showCategoriesScene = new Scene(showCategoriesSceneLayout);
            showCategoriesScene.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            mainStage.setTitle( "واجهة عرض الأنواع");
            mainStage.setScene(showCategoriesScene);
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

                BorderPane productsLayout = (BorderPane) getProductsSceneLayout();
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

            BorderPane addCategoryLayout = (BorderPane) getAddCategorySceneLayout();
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

                BorderPane productsLayout = (BorderPane) getProductsSceneLayout();
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

            BorderPane editCategoryLayout = (BorderPane) getEditCategorySceneLayout();
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

    private static Pane getAddProductSceneLayout() {
        String nodeText;

        nodeText = "أسم المنتج";
        Label productNameLabel = new Label(nodeText);
        TextField productNameTextField = new TextField();
        productNameTextField.setPrefSize(100, 20);

        nodeText = "نوع المنتج";
        Label productCategoryLabel = new Label(nodeText);
        ChoiceBox categoriesChoiceBox = new ChoiceBox();
        ArrayList<String> categoriesList = Main.myStore.getCategories();
        categoriesChoiceBox.setItems(FXCollections.observableList(categoriesList));



        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);
        submitButton.setOnAction(event -> {
            //ToDo: complete the add product function

            String message = "";
            messageLabel.setText(message);

            String productName = productNameTextField.getText().trim();

            int productCategory = categoriesChoiceBox.getSelectionModel().getSelectedIndex()+1;

            Double productPrice = 0.0;

            int availableQuantity = 0;

            boolean done = false;
            done = Main.myStore.addProduct(productName, productCategory, availableQuantity, productPrice);
            if(done)
            {
                message = "تم الإدخال بنجاح";
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
        gridPane.add(categoriesChoiceBox,1,1);
        gridPane.add(productCategoryLabel,0,1);
        gridPane.add(submitButton,0,4);
        gridPane.add(messageLabel,0,5);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);


        return borderPane;
    }

    private static Pane getEditProductSceneLayout() {
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

    private static Pane getShowProductsSceneLayout() {
        TableView table = new TableView();

        TableColumn productName = new TableColumn("أسم المنتج");
        TableColumn productBuyingPrice = new TableColumn("سعر شراء");
        TableColumn lastBuyingDate = new TableColumn("تاريخ أخر شراء");
        TableColumn productAvailableQuantity = new TableColumn("الكمية المتوافرة");
        TableColumn productSellingPrice = new TableColumn("سعر كاش");
        TableColumn productInstallmentPrice = new TableColumn("سعر تقسيط");

        table.getColumns().add(productName);
        table.getColumns().add(productAvailableQuantity);
        table.getColumns().add(productBuyingPrice);
        table.getColumns().add(lastBuyingDate);
        table.getColumns().add(productSellingPrice);
        table.getColumns().add(productInstallmentPrice);


        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);



        Label productCategoryLabel = new Label("نوع المنتج");
        ChoiceBox productsCategroriesChoiceBox = new ChoiceBox();
        ArrayList<String> productsCategories = Main.myStore.getCategories();
        productsCategroriesChoiceBox.setItems(FXCollections.observableList(productsCategories));

        productsCategroriesChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                int categoryID = number2.intValue() + 1;

                ObservableList<Product> productsList = FXCollections.observableList(Main.myStore.getProductsInCategory(categoryID));

                productName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
                productAvailableQuantity.setCellValueFactory(new PropertyValueFactory<Product, Integer>("availableQuantity"));
                productBuyingPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("buyingPrice"));
                lastBuyingDate.setCellValueFactory(new PropertyValueFactory<Product, String>("lastBuyingDate"));
                productSellingPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("sellingPrice"));
                productInstallmentPrice.setCellValueFactory(new PropertyValueFactory<Product, Double>("installmentPrice"));

                table.setItems(productsList);
            }
        });



        HBox topHBox = new HBox();

        topHBox.setSpacing(20);
        topHBox.getChildren().add(productCategoryLabel);
        topHBox.getChildren().add(productsCategroriesChoiceBox);


        BorderPane borderPane = new BorderPane();

        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setTop(topHBox);
        borderPane.setCenter(table);

        return borderPane;
    }

    private static Pane getAddCategorySceneLayout() {
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
            boolean done = false;
            done = Main.myStore.addCategory(categoryName);
            if(done)
            {
                message = "تم الإدخال بنجاح";
                messageLabel.setText(message);

            }


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

    private static Pane getEditCategorySceneLayout() {

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

    private static Pane getShowCategoriesSceneLayout() {
        TableColumn categoryName = new TableColumn("");

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));



        return borderPane;
    }

    private static Pane getSuppliersSceneLayout() {
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
                BorderPane suppliersLayout = (BorderPane) getSuppliersSceneLayout();

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

            BorderPane showSuppliersSceneLayout = (BorderPane) getShowSuppliersSceneLayout();

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
                BorderPane suppliersLayout = (BorderPane) getSuppliersSceneLayout();

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

            BorderPane addSupplierSceneLayout = (BorderPane) getAddSupplierSceneLayout();

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
                BorderPane suppliersLayout = (BorderPane) getSuppliersSceneLayout();

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

            BorderPane editSupplierSceneLayout = (BorderPane) getEditSupplierSceneLayout();

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

    private static Pane getAddSupplierSceneLayout() {
        String nodeText;

        nodeText = "أسم المورد";
        Label supplierNameLabel = new Label(nodeText);
        TextField supplierNameTextField = new TextField();
        supplierNameTextField.setPrefSize(50, 20);


        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {

            //ToDo: complete the add supplier function
            String message = "";
            messageLabel.setText(message);

            String supplierName = supplierNameTextField.getText();

            boolean done = false;
            done = Main.myStore.addSupplier(supplierName);
            if(done)
            {
                message = "تم الإدخال بنجاح";
                messageLabel.setText(message);
            }
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

    private static Pane getEditSupplierSceneLayout() {
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

    private static Pane getShowSuppliersSceneLayout() {
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

    private static void showBillDetails(Bill bill) {
        TableColumn productNameCol = new TableColumn("أسم المنتج");
        productNameCol.setCellValueFactory(new PropertyValueFactory<SoldProduct, String>("productName"));

        TableColumn soldQuantityCol = new TableColumn("الكمية المباعة");
        soldQuantityCol.setCellValueFactory(new PropertyValueFactory<SoldProduct, Integer>("soldQuantity"));

        TableColumn sellingPriceCol = new TableColumn("سعر بيع الوحدة");
        sellingPriceCol.setCellValueFactory(new PropertyValueFactory<SoldProduct, Double>("sellingPrice"));

        TableView<SoldProduct> billDetailsTable = new TableView<>();
        billDetailsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        billDetailsTable.getColumns().setAll(productNameCol, soldQuantityCol, sellingPriceCol);

        ArrayList<SoldProduct> soldProducts = Main.myStore.getBillSoldProducts(bill.getID());
        billDetailsTable.setItems(FXCollections.observableList(soldProducts));

        Stage secondaryStage = new Stage();
        secondaryStage.setAlwaysOnTop(true);
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

    private static void showSupplyDetails(Supply supply) {
        TableColumn productNameCol = new TableColumn("أسم المنتج");
        productNameCol.setCellValueFactory(new PropertyValueFactory<BoughtProduct, String>("productName"));

        TableColumn boughtQuantityCol = new TableColumn("الكمية المشتراة");
        boughtQuantityCol.setCellValueFactory(new PropertyValueFactory<BoughtProduct, Integer>("boughtQuantity"));

        TableColumn buyingPriceCol = new TableColumn("سعر شراء الوحدة");
        buyingPriceCol.setCellValueFactory(new PropertyValueFactory<BoughtProduct, Double>("buyingPrice"));

        TableView<BoughtProduct> billDetailsTable = new TableView<>();
        billDetailsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        billDetailsTable.getColumns().setAll(productNameCol, boughtQuantityCol, buyingPriceCol);

        ArrayList<BoughtProduct> boughtProducts = Main.myStore.getSupplyBoughtProducts(supply.getID());
        billDetailsTable.setItems(FXCollections.observableList(boughtProducts));

        Stage secondaryStage = new Stage();
        secondaryStage.setAlwaysOnTop(true);
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

}
