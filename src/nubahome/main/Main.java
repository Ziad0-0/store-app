package nubahome.main;

import javafx.application.Application;
import javafx.stage.Stage;
import nubahome.databse.Store;
import nubahome.gui.GUI;

import java.util.ArrayList;

public class Main extends Application {

    public static Store myStore;

    @Override
    public void start(Stage primaryStage) throws Exception {

        GUI.open(primaryStage);
    }

    public static void main(String[] args) {

        myStore = new Store("NubaHome");
        myStore.setupDatabase("com.mysql.jdbc.Driver","jdbc:sqlite:db/nuba_home.db");
        myStore.connectToDatabase();
        myStore.initializeUserTypes();
        ArrayList<Integer> b = new ArrayList<Integer>();
        b.add(1);
        ArrayList<Integer> c = new ArrayList<Integer>();
        c.add(5);
        ArrayList<Double> d = new ArrayList<Double>();
        d.add(10.5);

        myStore.addBill("ziad",b,c,d);
        Application.launch(args);
    }
}