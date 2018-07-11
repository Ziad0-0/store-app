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

public class Test extends Application {

    private Stage mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = primaryStage;

        mainStage.setTitle("الواجهة الرئيسية");
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

        });
        
        String suppliesButtonText = "التوريدات";
        Button suppliesButton = new Button(suppliesButtonText);
        suppliesButton.setPrefSize(120, 50);

        suppliesButton.setOnAction(actionEvent ->  {

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

        String showSalesButtonText = "عرض المبيعات";
        Button showSalesButton = new Button(showSalesButtonText);
        showSalesButton.setPrefSize(120, 30);

        String addSaleButtonText = "إضافة بيع";
        Button addSaleButton = new Button(addSaleButtonText);
        addSaleButton.setPrefSize(120, 30);

        String editSaleButtonText = "تعديل بيع";
        Button editSaleButton = new Button(editSaleButtonText);
        editSaleButton.setPrefSize(120, 30);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(homeButton);


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
        borderPane.setBottom(hbox);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;
    }

    private Scene getOrdersScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);

        String showOrdersButtonText = "عرض الطلبيات";
        Button showOrdersButton = new Button(showOrdersButtonText);
        showOrdersButton.setPrefSize(120, 30);

        String addOrderButtonText = "إضافة طلب";
        Button addOrderButton = new Button(addOrderButtonText);
        addOrderButton.setPrefSize(120, 30);

        String editOrderButtonText = "تعديل طلب";
        Button editOrderButton = new Button(editOrderButtonText);
        editOrderButton.setPrefSize(120, 30);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(homeButton);


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
        borderPane.setBottom(hbox);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;
    }

    private Scene getCategoriesScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);

        String showCategoriesButtonText = "عرض الأنواع";
        Button showCategoriesButton = new Button(showCategoriesButtonText);
        showCategoriesButton.setPrefSize(120, 30);

        String addCategoryButtonText = "إضافة نوع";
        Button addCategoryButton = new Button(addCategoryButtonText);
        addCategoryButton.setPrefSize(120, 30);

        String editCategoryButtonText = "تعديل نوع";
        Button editCategoryButton = new Button(editCategoryButtonText);
        editCategoryButton.setPrefSize(120, 30);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(homeButton);


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
        borderPane.setBottom(hbox);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;

    }

    private Scene getBillsScene() {

        String homeButtonText = "العودة إلي الواجهة الرئيسية";
        Button homeButton = new Button(homeButtonText);
        homeButton.setPrefSize(180,30);

        String showBillsButtonText = "عرض الفواتير";
        Button showBillsButton = new Button(showBillsButtonText);
        showBillsButton.setPrefSize(120, 30);

        String addBillButtonText = "إضافة فاتورة";
        Button addBillButton = new Button(addBillButtonText);
        addBillButton.setPrefSize(120, 30);

        String editBillButtonText = "تعديل فاتورة";
        Button editBillButton = new Button(editBillButtonText);
        editBillButton.setPrefSize(120, 30);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.BOTTOM_RIGHT);
        hbox.getChildren().add(homeButton);


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
        borderPane.setBottom(hbox);
        borderPane.setCenter(gridPane);
        
        Scene scene = new Scene(borderPane);
        
        return scene;

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    
}

