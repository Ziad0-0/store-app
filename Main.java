import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = primaryStage;
        
        String sceneTitle = "الواجهة الرئيسية";
        mainStage.setTitle(sceneTitle);
        mainStage.setScene(getHomeScene());
        mainStage.setMaximized(true);
        mainStage.show();
        
    }

    private Scene getHomeScene() {

        String salesButtonText = "المبيعات";
        Button salesButton = new Button(salesButtonText);
        salesButton.setPrefSize(120, 50);

        salesButton.setOnAction(actionEvent ->  {

            mainStage.close();
            String sceneTitle = "واجهة المبيعات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSalesScene());
            System.gc();
            mainStage.show();
        });

        String orderButtonText = "الطلبيات";
        Button ordersButton = new Button(orderButtonText);
        ordersButton.setPrefSize(120, 50);

        ordersButton.setOnAction(actionEvent ->  {
        
            mainStage.close();
            String sceneTitle = "واجهة الطلبيات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getOrdersScene());
            System.gc();
            mainStage.show();
        });

        String categoriesButtonText = "الأنواع";
        Button categoriesButton = new Button(categoriesButtonText);
        categoriesButton.setPrefSize(120, 50);

        categoriesButton.setOnAction(actionEvent ->  {

            mainStage.close();
            String sceneTitle = "واجهة الأنواع";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getCategoriesScene());
            System.gc();
            mainStage.show();
        });

        String billsButtonText = "الفواتير";
        Button billsButton = new Button(billsButtonText);
        billsButton.setPrefSize(120, 50);

        billsButton.setOnAction(actionEvent ->  {

            mainStage.close();
            String sceneTitle = "واجهة الفواتير";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getBillsScene());
            System.gc();
            mainStage.show();
        });

        String productsButtonText = "المنتجات";
        Button productsButton = new Button(productsButtonText);
        productsButton.setPrefSize(120, 50);

        productsButton.setOnAction(actionEvent ->  {

            mainStage.close();
            String sceneTitle = "واجهة المنتجات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getProductsScene());
            System.gc();
            mainStage.show();

        });
        
        String suppliesButtonText = "التوريدات";
        Button suppliesButton = new Button(suppliesButtonText);
        suppliesButton.setPrefSize(120, 50);

        suppliesButton.setOnAction(actionEvent ->  {

            mainStage.close();
            String sceneTitle = "واجهة التوريدات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSuppliesScene());
            System.gc();
            mainStage.show();

        });

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(30, 30, 30, 30)); 
        gridPane.setHgap(15);
        gridPane.setVgap(15); 
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(salesButton,0,0);
        gridPane.add(ordersButton,1,0);
        gridPane.add(categoriesButton,2,0);
        gridPane.add(billsButton,0,1);
        gridPane.add(productsButton,1,1);
        gridPane.add(suppliesButton,2,1);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(gridPane);

        Scene scene = new Scene(borderPane);
        
        return scene;
        
    }

    private Scene getSalesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        showSalesButton.setPrefSize(120, 30);
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
        addSaleButton.setPrefSize(120, 30);
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
        editSaleButton.setPrefSize(120, 30);
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
        gridPane.add(editSaleButton,0,0);
        gridPane.add(addSaleButton,1,0);
        gridPane.add(showSalesButton,2,0);
    
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;
    }

    private Scene getAddSaleScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
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

    private Scene getEditSaleScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
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

    private Scene getShowSalesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
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

    private Scene getOrdersScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();

        });

        String showOrdersButtonText = "عرض الطلبيات";
        Button showOrdersButton = new Button(showOrdersButtonText);
        showOrdersButton.setPrefSize(120, 30);
        showOrdersButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة عرض الطلبيات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getShowOrdersScene());
            System.gc();
            mainStage.show();
        });

        String addOrderButtonText = "إضافة طلب";
        Button addOrderButton = new Button(addOrderButtonText);
        addOrderButton.setPrefSize(120, 30);
        addOrderButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة إضافة طلب";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddOrderScene());
            System.gc();
            mainStage.show();
        });

        String editOrderButtonText = "تعديل طلب";
        Button editOrderButton = new Button(editOrderButtonText);
        editOrderButton.setPrefSize(120, 30);
        editOrderButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل طلب";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditOrderScene());
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
        gridPane.add(editOrderButton,0,0);
        gridPane.add(addOrderButton,1,0);
        gridPane.add(showOrdersButton,2,0);
    
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;
    }

    private Scene getAddOrderScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الطلبيات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getOrdersScene());
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

    private Scene getEditOrderScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الطلبيات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getOrdersScene());
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

    private Scene getShowOrdersScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الطلبيات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getOrdersScene());
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
    
    private Scene getCategoriesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();

        });

        String showCategoriesButtonText = "عرض الأنواع";
        Button showCategoriesButton = new Button(showCategoriesButtonText);
        showCategoriesButton.setPrefSize(120, 30);
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
        addCategoryButton.setPrefSize(120, 30);
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
        editCategoryButton.setPrefSize(120, 30);
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
        gridPane.add(editCategoryButton,0,0);
        gridPane.add(addCategoryButton,1,0);
        gridPane.add(showCategoriesButton,2,0);
    
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;

    }

    private Scene getAddCategoryScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الأنواع";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getCategoriesScene());
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

    private Scene getEditCategoryScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الأنواع";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getCategoriesScene());
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

    private Scene getShowCategoriesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة الأنواع";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getCategoriesScene());
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
    
    private Scene getBillsScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        showBillsButton.setPrefSize(120, 30);
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
        addBillButton.setPrefSize(120, 30);
        addBillButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle ="واجهة إضافة الفاتورة";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddBillScene());
            System.gc();
            mainStage.show();

        });

        String editBillButtonText = "تعديل فاتورة";
        Button editBillButton = new Button(editBillButtonText);
        editBillButton.setPrefSize(120, 30);
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
        gridPane.add(editBillButton,0,0);
        gridPane.add(addBillButton,1,0);
        gridPane.add(showBillsButton,2,0);
    
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;

    }
    
    private Scene getAddBillScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
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

    private Scene getEditBillScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
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

    private Scene getShowBillsScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
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
    
    private Scene getProductsScene() {


        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        showProductsButton.setPrefSize(120, 30);
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
        addProductButton.setPrefSize(120, 30);
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
        editProductButton.setPrefSize(120, 30);
        editProductButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل منتج";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditProductScene());
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
        gridPane.add(editProductButton,0,0);
        gridPane.add(addProductButton,1,0);
        gridPane.add(showProductsButton,2,0);
    
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;
    }

    private Scene getAddProductScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
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

    private Scene getEditProductScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
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

    private Scene getShowProductsScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
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

    private Scene getSuppliesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
        homeButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "الواجهة الرئيسية";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getHomeScene());
            System.gc();
            mainStage.show();

        });

        String showSuppliesButtonText = "عرض التوريدات";
        Button showSuppliesButton = new Button(showSuppliesButtonText);
        showSuppliesButton.setPrefSize(120, 30);
        showSuppliesButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة عرض التوريدات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getShowSuppliesScene());
            System.gc();
            mainStage.show();
        });

        String addSupplyButtonText = "إضافة توريد";
        Button addSupplyButton = new Button(addSupplyButtonText);
        addSupplyButton.setPrefSize(120, 30);
        addSupplyButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة إضافة توريد";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getAddSupplyScene());
            System.gc();
            mainStage.show();
        });


        String editSupplyButtonText = "تعديل توريد";
        Button editSupplyButton = new Button(editSupplyButtonText);
        editSupplyButton.setPrefSize(120, 30);
        editSupplyButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة تعديل توريد";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getEditSupplyScene());
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
        gridPane.add(editSupplyButton,0,0);
        gridPane.add(addSupplyButton,1,0);
        gridPane.add(showSuppliesButton,2,0);
    
        
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setBottom(hBOx);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;
    }

    private Scene getAddSupplyScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة التوريدات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSuppliesScene());
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

    private Scene getEditSupplyScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة التوريدات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSuppliesScene());
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

    private Scene getShowSuppliesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);
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
        previousButton.setPrefSize(180,30);
        previousButton.setOnAction(event -> {

            mainStage.close();
            String sceneTitle = "واجهة التوريدات";
            mainStage.setTitle(sceneTitle);
            mainStage.setScene(getSuppliesScene());
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

    public static void main(String[] args) {
        Application.launch(args);
    }

    
}

