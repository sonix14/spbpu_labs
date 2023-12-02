package com.example.smo;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    public static VBox root = new VBox();
    public static Stage stage;
    public static MainMenu mainMenu = new MainMenu();
    public static ConfigMenu configMenu;
    public static AutoMenu autoMenu;
    public static StepMenu stepMenu;
    public static Launcher launcher;
    public static Statistics statistics;

    public static void setContent(Node node){
        root.getChildren().clear();
        root.getChildren().add(mainMenu);
        root.getChildren().add(node);
    }

    @Override
    public void start(Stage stage_) throws IOException {
        stage = stage_;

        mainMenu.setDisable(true);
        configMenu = new ConfigMenu();
        setContent(configMenu);

        Scene scene = new Scene(root, 1000, 800);
        stage.setTitle("SMO");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}