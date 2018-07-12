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
    private Scene homeScene, salesScene, ordersScene, categoriesScene, productsScene, billsScene, suppliesScene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        mainStage = primaryStage;
        
        Button salesButton = new Button("المبيعات");
        salesButton.setPrefSize(120, 50);
        salesButton.setOnAction(actionEvent ->  {
            
            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180,30);
            homeButton.setOnAction(event ->  { 

                switchScene(homeScene, "الواجهة الرئيسية");
            });

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);
            
            
            Button showSalesButton = new Button("عرض المبيعات");
            showSalesButton.setPrefSize(120, 30);
            Button addSaleButton = new Button("إضافة بيع");
            addSaleButton.setPrefSize(120, 30);
            Button editSaleButton = new Button("تعديل بيع");
            editSaleButton.setPrefSize(120, 30);

            gridPane.add(editSaleButton,0,0);
            gridPane.add(addSaleButton,1,0);
            gridPane.add(showSalesButton,2,0);
        
            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            salesScene = new Scene(borderPane);

            switchScene(salesScene, "واجهة المبيعات");            
        });

        Button ordersButton = new Button("الطلبيات");
        ordersButton.setPrefSize(120, 50);
        ordersButton.setOnAction(actionEvent ->  {
           
            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180,30);   
            homeButton.setOnAction(event ->  { 
                    
                switchScene(homeScene, "الواجهة الرئيسية");
            });

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);

            Button showOrdersButton = new Button("عرض الطلبيات");
            showOrdersButton.setPrefSize(120, 30);
            Button addOrderButton = new Button("إضافة طلبية");
            addOrderButton.setPrefSize(120, 30);
            Button editOrderButton = new Button("تعديل طلبية");
            editOrderButton.setPrefSize(120, 30);

            gridPane.add(editOrderButton,0,0);
            gridPane.add(addOrderButton,1,0);
            gridPane.add(showOrdersButton,2,0);
        
            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            ordersScene = new Scene(borderPane);

            switchScene(ordersScene, "واجهة الطلبيات");            
        });

        Button categoriesButton = new Button("الأنواع");
        categoriesButton.setPrefSize(120, 50);
        categoriesButton.setOnAction(actionEvent ->  {
           
            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180,30);
            homeButton.setOnAction(event ->  { 
   
                switchScene(homeScene, "الواجهة الرئيسية");
            });

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);

            Button showCategoriesButton = new Button("عرض الأنواع");
            showCategoriesButton.setPrefSize(120, 30);

            Button addCategoryButton = new Button("إضافة نوع");
            addCategoryButton.setPrefSize(120, 30);
            addCategoryButton.setOnAction(event ->  { 
                
            });

            Button editCategoryButton = new Button("تعديل نوع");
            editCategoryButton.setPrefSize(120, 30);

            gridPane.add(editCategoryButton,0,0);
            gridPane.add(addCategoryButton,1,0);           
            gridPane.add(showCategoriesButton,2,0);

            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            categoriesScene = new Scene(borderPane);

            switchScene(categoriesScene, "واجهة الأنواع");
          
        });

        Button billsButton = new Button("الفواتير");
        billsButton.setPrefSize(120, 50);
        billsButton.setOnAction(actionEvent ->  {
            

            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180,30);      
            homeButton.setOnAction(event ->  { 
                
                switchScene(homeScene, "الواجهة الرئيسية");
            });

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);    
            
            Button showBillsButton = new Button("عرض الفواتير");
            showBillsButton.setPrefSize(120, 30);
            Button addBillButton = new Button("إضافة فاتورة");
            addBillButton.setPrefSize(120, 30);
            Button editBillButton = new Button("تعديل فاتورة");
            editBillButton.setPrefSize(120, 30);

            gridPane.add(editBillButton,0,0);
            gridPane.add(addBillButton,1,0);
            gridPane.add(showBillsButton,2,0);
        
            
            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            billsScene = new Scene(borderPane);
            switchScene(billsScene, "واجهة الفواتير");
            
        });

        Button productsButton = new Button("المنتجات");
        productsButton.setPrefSize(120, 50);
        productsButton.setOnAction(actionEvent ->  {
            
            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180,30);
            homeButton.setOnAction(event ->  { 
                
                switchScene(homeScene, "الواجهة الرئيسية");
            });

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);

            Button showProductsButton = new Button("عرض المنتجات");
            showProductsButton.setPrefSize(120, 30);
            Button addProductButton = new Button("إضافة منتج");
            addProductButton.setPrefSize(120, 30);
            Button editProductButton = new Button("تعديل منتج");
            editProductButton.setPrefSize(120, 30);

            gridPane.add(editProductButton,0,0);
            gridPane.add(addProductButton,1,0);
            gridPane.add(showProductsButton,2,0);
            

            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            productsScene = new Scene(borderPane);

            switchScene(productsScene,"واجهة المنتجات");
            
        });

        Button suppliesButton = new Button("التوريدات");
        suppliesButton.setPrefSize(120, 50);
        suppliesButton.setOnAction(actionEvent ->  {
            
            Button homeButton = new Button("العودة إلي الواجهة الرئيسية");
            homeButton.setPrefSize(180,30);    
            homeButton.setOnAction(event ->  { 
                
                switchScene(homeScene, "الواجهة الرئيسية");
            });

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);

            Button showSuppliesButton = new Button("عرض التوريدات");
            showSuppliesButton.setPrefSize(120, 30);
            Button addSupplyButton = new Button("إضافة توريد");
            addSupplyButton.setPrefSize(120, 30);
            Button editSupplyButton = new Button("تعديل توريد");
            editSupplyButton.setPrefSize(120, 30);

            gridPane.add(editSupplyButton,0,0);
            gridPane.add(addSupplyButton,1,0);
            gridPane.add(showSuppliesButton,2,0);
            
            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            suppliesScene = new Scene(borderPane);

            switchScene(suppliesScene,"واجهة التوريدات");

        });
                
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(salesButton,0,0);
        gridPane.add(ordersButton,1,0);
        gridPane.add(categoriesButton,2,0);
        gridPane.add(billsButton,0,1);
        gridPane.add(productsButton,1,1);
        gridPane.add(suppliesButton,2,1);
        gridPane.setPadding(new Insets(30, 30, 30, 30)); 
        gridPane.setHgap(15);
        gridPane.setVgap(15);       
       
        homeScene = new Scene(gridPane);
        
        mainStage.setTitle("الواجهة الرئيسية");
        mainStage.setScene(homeScene);
        mainStage.setMaximized(true);
        mainStage.show();
        
    }

    private void switchScene(Scene scene, String title) {

        mainStage.close();
        mainStage.setScene(scene);
        mainStage.setTitle(title);
        mainStage.show();
        
    }

    public static void main(String[] args) {
        Application.launch(args);
    }


}

