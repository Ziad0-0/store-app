package nubahome.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import nubahome.main.Main;

public class GUI {

    private static Stage mainStage;

    public static void open(Stage primaryStage) {

        mainStage = primaryStage;
        mainStage.setMaximized(true);

        String sceneTitle = "تسجيل الدخول";
        mainStage.setTitle(sceneTitle);
        mainStage.setScene(GUI.getLoginScene());
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

        Scene scene = new Scene(borderPane);

        return scene;


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

        String instalmentsButtonText = "الأقساط";
        Button instalmentsButton = new Button(instalmentsButtonText);
        instalmentsButton.setPrefSize(150, 50);

        instalmentsButton.setOnAction(actionEvent -> {

            mainStage.close();
            String sceneTitle = "واجهة الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getInstalmentsScene());
            System.gc();
            mainStage.show();
        });



        String billsButtonText = "الفواتير";
        Button billsButton = new Button(billsButtonText);
        billsButton.setPrefSize(150, 50);

        billsButton.setOnAction(actionEvent -> {

            mainStage.close();
            String sceneTitle = "واجهة الفواتير";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsScene());
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

        String suppliersButtonText = "الموردين";
        Button suppliersButton = new Button(suppliersButtonText);
        suppliersButton.setPrefSize(150, 50);

        suppliersButton.setOnAction(actionEvent -> {

            mainStage.close();
            String sceneTitle = "واجهة الموردين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getsuppliersScene());
            System.gc();
            mainStage.show();

        });

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
        gridPane.add(instalmentsButton, 1, 0);
        gridPane.add(usersButton, 2, 0);
        gridPane.add(billsButton, 0, 1);
        gridPane.add(productsButton, 1, 1);
        gridPane.add(suppliersButton, 2, 1);
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);

        return scene;

    }

    private static Scene getInstalmentsScene() {

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

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.getChildren().add(homeButton);

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

        String addInstalmentButtonText = "إضافة قسط";
        Button addInstalmentButton = new Button(addInstalmentButtonText);
        addInstalmentButton.setPrefSize(150, 30);
        addInstalmentButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة إضافة ";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddInstalmentScene());
            System.gc();
            mainStage.show();
        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(editInstalmentButton, 0, 0);
        gridPane.add(addInstalmentButton, 1, 0);
        gridPane.add(showInstalmentsButton, 2, 0);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);

        return scene;

    }

    private static Scene getAddInstalmentScene() {

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
            String sceneTitle = "واجهة الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getInstalmentsScene());
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

        Scene scene = new Scene(borderPane);

        return scene;
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
            String sceneTitle = "واجهة الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getInstalmentsScene());
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

        Scene scene = new Scene(borderPane);

        return scene;
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
            String sceneTitle = "واجهة الأقساط";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getInstalmentsScene());
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

        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;

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

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);

        return scene;
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

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;
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


        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;
    }

    private static Scene getBillsScene() {

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
            mainStage.setScene(getShowBillsScene());
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

        HBox hBOx = new HBox();
        hBOx.setAlignment(Pos.BOTTOM_RIGHT);
        hBOx.getChildren().add(homeButton);


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(editBillButton, 0, 0);
        gridPane.add(addBillButton, 1, 0);
        gridPane.add(showBillsButton, 2, 0);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);

        return scene;

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
            String sceneTitle = "واجهة الفواتير";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsScene());
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

        Scene scene = new Scene(borderPane);

        return scene;
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
            String sceneTitle = "واجهة الفواتير";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsScene());
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

        Scene scene = new Scene(borderPane);

        return scene;
    }

    private static Scene getShowBillsScene() {

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
            String sceneTitle = "واجهة الفواتير";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsScene());
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

        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;
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


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);

        return scene;
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

        Scene scene = new Scene(borderPane);

        return scene;
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

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30));
        gridPane.setHgap(15);
        gridPane.setVgap(15);
        gridPane.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);

        return scene;
    }

    private static Scene getsuppliersScene() {

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

        String showsuppliersButtonText = "عرض الموردين";
        Button showsuppliersButton = new Button(showsuppliersButtonText);
        showsuppliersButton.setPrefSize(150, 30);
        showsuppliersButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة عرض الموردين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getShowsuppliersScene());
            System.gc();
            mainStage.show();
        });

        String addsupplierButtonText = "إضافة مورد";
        Button addsupplierButton = new Button(addsupplierButtonText);
        addsupplierButton.setPrefSize(150, 30);
        addsupplierButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة إضافة مورد";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddsupplierScene());
            System.gc();
            mainStage.show();
        });


        String editsupplierButtonText = "تعديل مورد";
        Button editsupplierButton = new Button(editsupplierButtonText);
        editsupplierButton.setPrefSize(150, 30);
        editsupplierButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل مورد";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditsupplierScene());
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
        gridPane.add(editsupplierButton, 0, 0);
        gridPane.add(addsupplierButton, 1, 0);
        gridPane.add(showsuppliersButton, 2, 0);


        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);

        return scene;
    }

    private static Scene getAddsupplierScene() {

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
            String sceneTitle = "واجهة الموردين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getsuppliersScene());
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

        Scene scene = new Scene(borderPane);

        return scene;
    }

    private static Scene getEditsupplierScene() {

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
            String sceneTitle = "واجهة الموردين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getsuppliersScene());
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

        Scene scene = new Scene(borderPane);

        return scene;
    }

    private static Scene getShowsuppliersScene() {

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
            String sceneTitle = "واجهة الموردين";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getsuppliersScene());
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

        Scene scene = new Scene(borderPane);

        return scene;
    }


}
