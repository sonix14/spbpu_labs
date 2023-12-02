package com.example.smo;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenu extends MenuBar {
    Menu modes = new Menu("Modes");
    Menu configurations = new Menu("Configurations");
    MenuItem autoMode = new Menu("Auto mode");
    MenuItem stepMode = new Menu("Step mode");
    MenuItem confItem = new MenuItem("Configurations");

    public MainMenu() {
        modes.getItems().addAll(autoMode, stepMode);
        configurations.getItems().add(confItem);
        this.getMenus().addAll(configurations, modes);

        autoMode.setOnAction(event -> MainApplication.setContent(MainApplication.autoMenu));
        stepMode.setOnAction(event -> MainApplication.setContent(MainApplication.stepMenu));
        confItem.setOnAction(event -> MainApplication.setContent(MainApplication.configMenu));
    }
}
