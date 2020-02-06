package nubahome.main;

import javafx.application.Application;
import javafx.stage.Stage;
import nubahome.databse.StoreDatabase;
import nubahome.gui.GUI;

import java.util.Locale;


public class Main extends Application {
    

    @Override
    public void start(Stage primaryStage) {
        GUI.open(primaryStage);
    }

    public static void main(String[] args) {
        //set app language to arabic
        Locale.setDefault(new Locale("ar"));

        StoreDatabase.setDatabaseURL("jdbc:sqlite:" + System.getProperty("user.dir") + "/db/nuba_home.db");
        StoreDatabase.connect();

        Application.launch(args);
    }


}