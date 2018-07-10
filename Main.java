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

    private Scene homeScene, salesScene, ordersScene, pricesScene, productsScene, billsScene, suppliesScene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Button salesButton = new Button("المبيعات");
        salesButton.setPrefSize(100, 50);
        salesButton.setOnAction(actionEvent ->  {

            //Go to sales window
            primaryStage.close();
           
            Button homeButton = new Button("العودة إلي الصفحة الرئيسية");
            homeButton.setPrefSize(180,30);
            
            homeButton.setOnAction(event ->  { 

                primaryStage.close();
                try {

                    primaryStage.setTitle("الواجهة الرئيسية");
                    primaryStage.setScene(homeScene);
                    primaryStage.setMaximized(true);
                    primaryStage.show();
                } catch (Exception e) {
                    //TODO: handle exception
                }});
           
            

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);
            
            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            salesScene = new Scene(borderPane);
            primaryStage.setTitle("واجهة المبيعات");
            primaryStage.setScene(salesScene);
            primaryStage.setMaximized(true);
            primaryStage.show();

        });

        Button ordersButton = new Button("الطلبيات");
        ordersButton.setPrefSize(100, 50);

        ordersButton.setOnAction(actionEvent ->  {

            primaryStage.close();

            Button homeButton = new Button("العودة إلي الصفحة الرئيسية");
            homeButton.setPrefSize(180,30);

            homeButton.setOnAction(event ->  { 

                primaryStage.close();
                try {
                    
                    primaryStage.setTitle("الواجهة الرئيسية");
                    primaryStage.setScene(homeScene);
                    primaryStage.setMaximized(true);
                    primaryStage.show();
                } catch (Exception e) {
                    //TODO: handle exception
                }});
           

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);


            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);       
            

            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));


            ordersScene = new Scene(borderPane);
            primaryStage.setTitle("واجهة الطلبيات");
            primaryStage.setScene(ordersScene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        });


        Button pricesButton = new Button("أسعار الأصناف");
        pricesButton.setPrefSize(100, 50);
        pricesButton.setOnAction(actionEvent ->  {
            //Go to prices window   

            primaryStage.close();
            
            Button homeButton = new Button("العودة إلي الصفحة الرئيسية");
            homeButton.setPrefSize(180,30);

            homeButton.setOnAction(event ->  { 

                primaryStage.close();
                try {
                    
                    primaryStage.setTitle("الواجهة الرئيسية");
                    primaryStage.setScene(homeScene);
                    primaryStage.setMaximized(true);
                    primaryStage.show();
                } catch (Exception e) {
                    //TODO: handle exception
                }});
           
            

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);
            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            pricesScene = new Scene(borderPane);

            primaryStage.setTitle("واجهة الأسعار");
            primaryStage.setScene(pricesScene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        });

        Button billsButton = new Button("الفواتير");
        billsButton.setPrefSize(100, 50);
        billsButton.setOnAction(actionEvent ->  {
            //Go to bills window

            primaryStage.close();

            Button homeButton = new Button("العودة إلي الصفحة الرئيسية");
            homeButton.setPrefSize(180,30);

            homeButton.setOnAction(event ->  { 

                primaryStage.close();
                try {
                    
                    primaryStage.setTitle("الواجهة الرئيسية");
                    primaryStage.setScene(homeScene);
                    primaryStage.setMaximized(true);
                    primaryStage.show();
                } catch (Exception e) {
                    //TODO: handle exception
                }});
           

            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);       
            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            billsScene = new Scene(borderPane);

            primaryStage.setTitle("واجهة الفواتير");
            primaryStage.setScene(billsScene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        });

        Button productsButton = new Button("الأصناف");
        productsButton.setPrefSize(100, 50);
        productsButton.setOnAction(actionEvent ->  {
            //Go to products window

            primaryStage.close();

            Button homeButton = new Button("العودة إلي الصفحة الرئيسية");
            homeButton.setPrefSize(180,30);

            homeButton.setOnAction(event ->  { 


                primaryStage.close();
                try {
                    
                    primaryStage.setTitle("الواجهة الرئيسية");
                    primaryStage.setScene(homeScene);
                    primaryStage.setMaximized(true);
                    primaryStage.show();
                } catch (Exception e) {
                    //TODO: handle exception
                }});
           
        
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);

            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);
            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            productsScene = new Scene(borderPane);


            
            primaryStage.setTitle("واجهة الأصناف");
            primaryStage.setScene(productsScene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        });

        Button suppliesButton = new Button("التوريدات");
        suppliesButton.setPrefSize(100, 50);
        suppliesButton.setOnAction(actionEvent ->  {
            //Go to supplies window  

            primaryStage.close();
      
            Button homeButton = new Button("العودة إلي الصفحة الرئيسية");
            homeButton.setPrefSize(180,30);

            homeButton.setOnAction(event ->  { 

                primaryStage.close();
                try {
                    
                    primaryStage.setTitle("الواجهة الرئيسية");
                    primaryStage.setScene(homeScene);
                    primaryStage.setMaximized(true);
                    primaryStage.show();
                } catch (Exception e) {
                    //TODO: handle exception
                }});
           
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.BOTTOM_RIGHT);
            hbox.getChildren().add(homeButton);


            GridPane gridPane = new GridPane();
            gridPane.setPadding(new Insets(30, 30, 30, 30)); 
            gridPane.setHgap(15);
            gridPane.setVgap(15);
            gridPane.setAlignment(Pos.CENTER);
            
            BorderPane borderPane = new BorderPane();
            borderPane.setBottom(hbox);
            borderPane.setCenter(gridPane);
            borderPane.setPadding(new Insets(20, 20, 20, 20));

            suppliesScene = new Scene(borderPane);

            primaryStage.setTitle("واجهة التوريدات");
            primaryStage.setScene(suppliesScene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        });
        
        
        GridPane gridPane = new GridPane();
       
        gridPane.add(salesButton,0,0);
        gridPane.add(ordersButton,1,0);
        gridPane.add(pricesButton,2,0);
        gridPane.add(billsButton,0,1);
        gridPane.add(productsButton,1,1);
        gridPane.add(suppliesButton,2,1);
        gridPane.setPadding(new Insets(30, 30, 30, 30)); 
        gridPane.setHgap(15);
        gridPane.setVgap(15);
       

        gridPane.setAlignment(Pos.CENTER);
       
        homeScene = new Scene(gridPane);
        
        primaryStage.setTitle("الواجهة الرئيسية");
        primaryStage.setScene(homeScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        
    }


    public static void main(String[] args) {
        Application.launch(args);
    }

}