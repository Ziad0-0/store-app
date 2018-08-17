package nubahome.main;

import javafx.application.Application;
import javafx.stage.Stage;
import nubahome.gui.GUI;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        GUI.start(primaryStage);
    }
}