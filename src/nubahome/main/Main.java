package nubahome.main;

import javafx.application.Application;
import javafx.stage.Stage;
import nubahome.databse.Store;
import nubahome.gui.GUI;


public class Main extends Application {

    public static Store myStore;

    @Override
    public void start(Stage primaryStage) {

        GUI.open(primaryStage);
    }

    public static void main(String[] args) {

        myStore = new Store("NubaHome");
        myStore.setupDatabase("com.mysql.jdbc.Driver","jdbc:sqlite:db/nuba_home.db");
        myStore.connectToDatabase();
        myStore.initializeUserTypes();
        Application.launch(args);
    }
}