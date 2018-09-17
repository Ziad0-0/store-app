package nubahome.gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import nubahome.main.Main;
import nubahome.databse.Product;
import nubahome.databse.User;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class GUI {

    private static Stage mainStage;

    public static void open(Stage primaryStage) {

        mainStage = primaryStage;
        mainStage.setMaximized(true);

        String sceneTitle = "تسجيل الدخول";
        mainStage.setTitle(sceneTitle);
        mainStage.setScene(GUI.getHomeScene());
        mainStage.show();
    }

    private static Scene getLoginScene() {

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

            if(currentUserType.equals( Main.myStore.userType("NOT_REGISTERED")))
            {
                //ToDo: implement the error message
                System.out.println("not registered");
            }
            else if(currentUserType.equals(Main.myStore.userType("MANAGER")))
            {
                userNameTextField.clear();
                passwordTextField.clear();
                System.out.println("manager");
                mainStage.close();
                String sceneTitle = "الواجهة الرئيسية";
                mainStage.setTitle(sceneTitle);
                mainStage.setScene(getHomeScene());
                System.gc();
                mainStage.show();
            }
            else if(currentUserType.equals(Main.myStore.userType("CLERK")))
            {
                //ToDo: implement the clerk scene
                userNameTextField.clear();
                passwordTextField.clear();
                System.out.println("clerk");
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
        

        return new Scene(borderPane);


    }

    private static Scene getHomeScene() {

        String salesButtonText = "المبيعات";
        Button salesButton = new Button(salesButtonText);
        salesButton.setPrefSize(150, 50);

        salesButton.setOnAction(actionEvent -> {

            mainStage.close();
            String sceneTitle = "واجهة المبيعات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSalesScene());
            System.gc();
            mainStage.show();
        });

        /*String instalmentsButtonText = "الأقساط";
        Button instalmentsButton = new Button(instalmentsButtonText);
        instalmentsButton.setPrefSize(150, 50);

        instalmentsButton.setOnAction(actionEvent -> {

            mainStage.close();
            String sceneTitle = "واجهة الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getInstalmentsScene());
            System.gc();
            mainStage.show();
        });*/



        String billsAndInstalmentsButtonText = "الفواتير و الأقساط";
        Button billsAndInstalmentsButton = new Button(billsAndInstalmentsButtonText);
        billsAndInstalmentsButton.setPrefSize(150, 50);

        billsAndInstalmentsButton.setOnAction(actionEvent -> {

            mainStage.close();
            String sceneTitle = "واجهة الفواتير و الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsAndInstalmentsScene());
            System.gc();
            mainStage.show();
        });

        String productsButtonText = "المنتجات";
        Button productsButton = new Button(productsButtonText);
        productsButton.setPrefSize(150, 50);

        productsButton.setOnAction(actionEvent -> {

            mainStage.close();
            String sceneTitle = "واجهة المنتجات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getProductsScene());
            System.gc();
            mainStage.show();

        });

        /*String SuppliersButtonText = "الموردين";
        Button SuppliersButton = new Button(SuppliersButtonText);
        SuppliersButton.setPrefSize(150, 50);

        SuppliersButton.setOnAction(actionEvent -> {

            mainStage.close();
            String sceneTitle = "واجهة الموردين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSuppliersScene());
            System.gc();
            mainStage.show();

        });*/

        String usersButtonText = "المستخدمين";
        Button usersButton = new Button(usersButtonText);
        usersButton.setPrefSize(150, 50);

        usersButton.setOnAction(actionEvent -> {

            mainStage.close();
            String sceneTitle = "واجهة المستخدمين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getUsersScene());
            System.gc();
            mainStage.show();
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(salesButton, 0, 0);
        //gridPane.add(instalmentsButton, 1, 0);
        gridPane.add(usersButton, 1, 0);
        gridPane.add(billsAndInstalmentsButton, 0, 1);
        gridPane.add(productsButton, 1, 1);
        //gridPane.add(SuppliersButton, 2, 1);
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);
        

        return new Scene(borderPane);

    }

    private static Scene getEditInstalmentScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الفواتير و الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsAndInstalmentsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

        

        return new Scene(borderPane);
    }

    private static Scene getShowInstalmentsScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الفواتير و الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsAndInstalmentsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);


        return new Scene(borderPane);
    }

    private static Scene getUsersScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();

        });

        String showUsersButtonText = "عرض المستخدمين";
        Button showUsersButton = new Button(showUsersButtonText);
        showUsersButton.setPrefSize(150, 30);
        showUsersButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة عرض المستخدمين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getShowUsersScene());
            System.gc();
            mainStage.show();
        });


        String addUserButtonText = "إضافة مستخدم";
        Button addUserButton = new Button(addUserButtonText);
        addUserButton.setPrefSize(150, 30);
        addUserButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة إضافة مستخدم";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddUserScene());
            System.gc();
            mainStage.show();
        });


        String editUserButtonText = "تعديل مستخدم";
        Button editUserButton = new Button(editUserButtonText);
        editUserButton.setPrefSize(150, 30);
        editUserButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل مستخدم";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditUserScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.getChildren().add(homeButton);


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
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);

    }

    private static Scene getEditUserScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المستخدمين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getUsersScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

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

            ArrayList <User> undeletedUSers = Main.myStore.getAllUsers();
            ArrayList<String> undeletedusersNames = new ArrayList<>();
            for(User x : undeletedUSers)
                undeletedusersNames.add(x.getName());
            usersChoiceBox.setItems(FXCollections.observableList(undeletedusersNames));


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
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);


        return new Scene(borderPane);
    }

    private static Scene getAddUserScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المستخدمين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getUsersScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

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
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getShowUsersScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المستخدمين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getUsersScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getSalesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();

        });

        String showSalesButtonText = "عرض المبيعات";
        Button showSalesButton = new Button(showSalesButtonText);
        showSalesButton.setPrefSize(150, 30);
        showSalesButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة عرض المبيعات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getShowSalesScene());
            System.gc();
            mainStage.show();
        });


        String addSaleButtonText = "إضافة بيع";
        Button addSaleButton = new Button(addSaleButtonText);
        addSaleButton.setPrefSize(150, 30);
        addSaleButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة إضافة بيع";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddSaleScene());
            System.gc();
            mainStage.show();
        });


        String editSaleButtonText = "تعديل بيع";
        Button editSaleButton = new Button(editSaleButtonText);
        editSaleButton.setPrefSize(150, 30);
        editSaleButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل بيع";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditSaleScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.getChildren().add(homeButton);


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(editSaleButton, 0, 0);
        gridPane.add(addSaleButton, 1, 0);
        gridPane.add(showSalesButton, 2, 0);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getAddSaleScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المبيعات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSalesScene());
            System.gc();
            mainStage.show();
        });


        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

        TextField salesTextField = new TextField();
        salesTextField.setPrefSize(50, 20);

        String nodeText;

        nodeText = "حجم المبيعات";
        Label salesLabel = new Label(nodeText);

        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(100, 20);
        submitButton.setOnAction(event -> {


            //ToDo:
        });


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(salesTextField, 0, 1);
        gridPane.add(salesLabel, 1, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(messageLabel, 1, 2);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);
        

        return new Scene(borderPane);
    }

    private static Scene getEditSaleScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المبيعات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSalesScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);


        return new Scene(borderPane);
    }

    private static Scene getShowSalesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المبيعات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSalesScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

        

        return new Scene(borderPane);
    }

    private static Scene getAddCategoryScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المنتجات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getProductsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

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
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);


        

        return new Scene(borderPane);
    }

    private static Scene getEditCategoryScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المنتجات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getProductsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getShowCategoriesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المنتجات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getProductsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getBillsAndInstalmentsScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();

        });

        String showBillsButtonText = "عرض الفواتير";
        Button showBillsButton = new Button(showBillsButtonText);
        showBillsButton.setPrefSize(150, 30);
        showBillsButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة عرض الفواتير";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getShowBillsAndInstalmentsScene());
            System.gc();
            mainStage.show();

        });

        String addBillButtonText = "إضافة فاتورة";
        Button addBillButton = new Button(addBillButtonText);
        addBillButton.setPrefSize(150, 30);
        addBillButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة إضافة الفاتورة";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddBillScene());
            System.gc();
            mainStage.show();

        });

        String editBillButtonText = "تعديل فاتورة";
        Button editBillButton = new Button(editBillButtonText);
        editBillButton.setPrefSize(150, 30);
        editBillButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل الفاتورة";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditBillScene());
            System.gc();
            mainStage.show();

        });

        String showInstalmentsButtonText = "عرض الأقساط";
        Button showInstalmentsButton = new Button(showInstalmentsButtonText);
        showInstalmentsButton.setPrefSize(150, 30);
        showInstalmentsButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة عرض الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getShowInstalmentsScene());
            System.gc();
            mainStage.show();
        });

        String editInstalmentButtonText = "تعديل قسط";
        Button editInstalmentButton = new Button(editInstalmentButtonText);
        editInstalmentButton.setPrefSize(150, 30);
        editInstalmentButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل قسط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditInstalmentScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.getChildren().add(homeButton);


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
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);

    }

    private static Scene getAddBillScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الفواتير و الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsAndInstalmentsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        String nodeText;

        nodeText = "أسم العميل";
        Label buyuerNameLabel = new Label(nodeText);
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
            String pattern = "dd-MM-yyyy";
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
            String pattern = "dd-MM-yyyy";
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


        Label messageLabel = new Label();

        nodeText = "أدخل";
        Button submitButton = new Button(nodeText);
        submitButton.setPrefSize(50, 20);

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


                    gridPane.add(guarantorNameLabel,7,3);
                    gridPane.add(guarantorNameTextField,6,3);
                    gridPane.add(paidMoneyLabel,7,4);
                    gridPane.add(paidMoneyTextField,6,4);
                    gridPane.add(instalmentAmountTextField,6,5);
                    gridPane.add(instalmentAmountLabel,7,5);
                    gridPane.add(startDatePicker,6,6);
                    gridPane.add(startOfInstalmentLabel,7,6);
                    gridPane.add(endDatePicker,6,7);
                    gridPane.add(endOfInstalmentLabel,7,7);
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


                    gridPane.add(addMoreProductsButton,0,2);
                    gridPane.add(scrollPane,0,3);
                    gridPane.add(submitButton,0,4);
                    gridPane.add(messageLabel,0,5);


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


                Label categoryLabel = new Label("نوع المنتج");
                ChoiceBox categoriesChoiceBox = new ChoiceBox();
                ArrayList<String> categoriesList = Main.myStore.getCategories();
                categoriesChoiceBox.setItems(FXCollections.observableList(categoriesList));


                categoriesChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                        int categoryID = categoriesChoiceBox.getSelectionModel().getSelectedIndex()+1;

                        ArrayList<Product> products = Main.myStore.getProductsInCategory(categoryID);


                        ArrayList<String> productsNames = new ArrayList<>();

                        for(Product x : products)
                            productsNames.add(x.getProductName());

                        productsChoiceBox.setItems(FXCollections.observableList(productsNames));
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

                productsGridPane.add(sellingPricesTextFields.get(lastProductIndex),0,currentProductRow);
                productsGridPane.add(sellingPricesLabels.get(lastProductIndex),1,currentProductRow);
                productsGridPane.add(soldQuantitiesTextFields.get(lastProductIndex),2,currentProductRow);
                productsGridPane.add(soldQuantitiesLabels.get(lastProductIndex),3,currentProductRow);
                productsGridPane.add(productsChoiceBoxes.get(lastProductIndex),4,currentProductRow);
                productsGridPane.add(productsLabels.get(lastProductIndex),5,currentProductRow);
                productsGridPane.add(categoriesChoiceBoxes.get(lastProductIndex),6,currentProductRow);
                productsGridPane.add(categoriesLabels.get(lastProductIndex),7,currentProductRow);

            }



        });


        submitButton.setOnAction(event -> {
            //ToDo: complete the add product function

            String message = "";
            messageLabel.setText(message);

            String buyerName = buyerNameTextField.getText().trim();
            String paymentChoice = paymentChoiceBox.getValue();
            
            int numOfSoldProducts =  productsChoiceBoxes.size();
            
            ArrayList<Integer> soldProductsIDs = new ArrayList<>();
            
            for(int i = 0; i < numOfSoldProducts; i++)
            {
                int selectedCategoryID = categoriesChoiceBoxes.get(i).getSelectionModel().getSelectedIndex()+1;
                ArrayList <Product> productsInSelectedCategory = Main.myStore.getProductsInCategory(selectedCategoryID);

                int selectedProductIndex = productsChoiceBoxes.get(i).getSelectionModel().getSelectedIndex();
                Product selectedProduct = productsInSelectedCategory.get(selectedProductIndex);

                soldProductsIDs.add(selectedProduct.getID());
            }

            ArrayList<Integer> soldQuantities = new ArrayList<>();
            for(int i = 0; i < numOfSoldProducts; i++)
                soldQuantities.add(Integer.parseInt(soldQuantitiesTextFields.get(i).getText().trim()));


            ArrayList<Double> sellingPrices = new ArrayList<>();
            for(int i = 0; i < numOfSoldProducts; i++)
                sellingPrices.add(Double.parseDouble(sellingPricesTextFields.get(i).getText().trim()));

            double totalBill = 0;
            for(int i = 0; i < numOfSoldProducts; i++)
                totalBill += sellingPrices.get(i) * soldQuantities.get(i);

            boolean done = false;

            if(paymentChoice.equals("تقسيط"))
            {
                String guarantorName = guarantorNameTextField.getText().trim();
                double paidMoney = Double.parseDouble(paidMoneyTextField.getText().trim());

                double instalmentAmount = Double.parseDouble(instalmentAmountTextField.getText().trim());
                Date startDate = java.sql.Date.valueOf(startDatePicker.getValue());
                Date endDate = java.sql.Date.valueOf(endDatePicker.getValue());

                done = Main.myStore.addInstalment(buyerName, soldProductsIDs, soldQuantities, sellingPrices, totalBill, guarantorName, paidMoney, instalmentAmount, startDate, endDate);
            }
            else
                done = Main.myStore.addBill(buyerName, soldProductsIDs, soldQuantities, sellingPrices, totalBill);



            //done = Main.myStore.addBill();
            if(done)
            {
                message = "تم الإدخال بنجاح";
                messageLabel.setText(message);

            }

        });


        gridPane.add(buyuerNameLabel,7,0);
        gridPane.add(buyerNameTextField,6,0);
        gridPane.add(paymentMethodLabel,7,1);
        gridPane.add(paymentChoiceBox,6,1);
        gridPane.add(addMoreProductsButton,0,2);
        gridPane.add(scrollPane,0,3);
        gridPane.add(submitButton,0,4);
        gridPane.add(messageLabel,0,5);




        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getEditBillScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الفواتير و الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsAndInstalmentsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getShowBillsAndInstalmentsScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الفواتير و الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsAndInstalmentsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getProductsScene() {


        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();

        });


        String showProductsButtonText = "عرض المنتجات";
        Button showProductsButton = new Button(showProductsButtonText);
        showProductsButton.setPrefSize(150, 30);
        showProductsButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة عرض المنتجات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getShowProductsScene());
            System.gc();
            mainStage.show();

        });


        String addProductButtonText = "إضافة منتج";
        Button addProductButton = new Button(addProductButtonText);
        addProductButton.setPrefSize(150, 30);
        addProductButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة إضافة منتج";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddProductScene());
            System.gc();
            mainStage.show();

        });


        String editProductButtonText = "تعديل منتج";
        Button editProductButton = new Button(editProductButtonText);
        editProductButton.setPrefSize(150, 30);
        editProductButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل منتج";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditProductScene());
            System.gc();
            mainStage.show();

        });

        String showCategoriesButtonText = "عرض الأنواع";
        Button showCategoriesButton = new Button(showCategoriesButtonText);
        showCategoriesButton.setPrefSize(150, 30);
        showCategoriesButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة عرض الأنواع";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getShowCategoriesScene());
            System.gc();
            mainStage.show();
        });

        String addCategoryButtonText = "إضافة نوع";
        Button addCategoryButton = new Button(addCategoryButtonText);
        addCategoryButton.setPrefSize(150, 30);
        addCategoryButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة إضافة نوع";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddCategoryScene());
            System.gc();
            mainStage.show();
        });

        String editCategoryButtonText = "تعديل نوع";
        Button editCategoryButton = new Button(editCategoryButtonText);
        editCategoryButton.setPrefSize(150, 30);
        editCategoryButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل نوع";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditCategoryScene());
            System.gc();
            mainStage.show();
        });


        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.getChildren().add(homeButton);


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
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getAddProductScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المنتجات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getProductsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

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

        nodeText = "سعر المنتج";
        Label productPriceLabel = new Label(nodeText);
        TextField productPriceTextField = new TextField();
        productPriceTextField.setPrefSize(100, 20);

        nodeText = "الكمية المتاحة";
        Label productAvailableQuantityLabel = new Label(nodeText);
        TextField productAvailableQuantityTextField = new TextField();
        productAvailableQuantityTextField.setPrefSize(100, 20);

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
            if(!productPriceTextField.getText().trim().isEmpty())
                productPrice = Double.parseDouble(productPriceTextField.getText().trim());

            int availableQuantity = 0;
            if(!productAvailableQuantityTextField.getText().trim().isEmpty())
                availableQuantity = Integer.parseInt(productAvailableQuantityTextField.getText().trim());

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

        gridPane.add(productNameTextField,0,0);
        gridPane.add(productNameLabel,1,0);
        gridPane.add(categoriesChoiceBox,0,1);
        gridPane.add(productCategoryLabel,1,1);
        gridPane.add(productPriceTextField,0,2);
        gridPane.add(productPriceLabel,1,2);
        gridPane.add(productAvailableQuantityTextField,0,3);
        gridPane.add(productAvailableQuantityLabel,1,3);
        gridPane.add(submitButton,0,4);
        gridPane.add(messageLabel,0,5);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getEditProductScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المنتجات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getProductsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

         

        return new Scene(borderPane);
    }

    private static Scene getShowProductsScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180, 30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();
        });

        String previousButtonText = "العودة إلي الواجهة السابقة";
        Button previousButton = new Button(previousButtonText);
        previousButton.setPrefSize(180, 30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة المنتجات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getProductsScene());
            System.gc();
            mainStage.show();
        });

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.setSpacing(10);
        hBOx.getChildren().add(previousButton);
        hBOx.getChildren().add(homeButton);

        TableView table = new TableView();

        TableColumn productName = new TableColumn("أسم المنتج");
        TableColumn productBuyingPrice = new TableColumn("سعر الشراء");
        TableColumn productAvailableQuantity = new TableColumn("الكمية المتوافرة");
        TableColumn productCashPrice = new TableColumn("سعر البيع كاش");
        TableColumn productInstalmentPrice = new TableColumn("سعر البيع تقسيط");

        table.getColumns().add(productInstalmentPrice);
        table.getColumns().add(productCashPrice);
        table.getColumns().add(productAvailableQuantity);
        table.getColumns().add(productBuyingPrice);
        table.getColumns().add(productName);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER_RIGHT);

        gridPane.add(table,20,0);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(table);

        return new Scene(borderPane);
    }


}
