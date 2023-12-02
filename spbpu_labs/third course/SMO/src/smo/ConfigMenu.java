package com.example.smo;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class ConfigMenu extends VBox {
    FlowPane layout = new FlowPane(Orientation.VERTICAL);
    TextField textFieldSources;
    TextField textFieldRequests;
    TextField textFieldInstruments;
    TextField textFieldBuffer;
    TextField textFieldAlpha;
    TextField textFieldBeta;
    TextField textFieldLambda;
    Button configure = new Button("Configure");
    Button simulate = new Button("Simulate");

    public ConfigMenu() {
        addConfigureObjects();
        setButtons();

        simulate.setVisible(false);
        FlowPane buttonsLayout = new FlowPane(Orientation.HORIZONTAL, configure, simulate);
        buttonsLayout.setHgap(60);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.getChildren().addAll(buttonsLayout);

        getChildren().addAll(layout);
    }
    private void setButtons() {
        configure.setOnAction(event -> {
            try {
                int countOfSources = Integer.parseInt(textFieldSources.getText());
                int countOfRequests = Integer.parseInt(textFieldRequests.getText());
                int countOfInstruments = Integer.parseInt(textFieldInstruments.getText());
                int sizeOfBuffer = Integer.parseInt(textFieldBuffer.getText());
                double alpha = Double.parseDouble(textFieldAlpha.getText());
                double beta = Double.parseDouble(textFieldBeta.getText());
                double lambda = Double.parseDouble(textFieldLambda.getText());

                if (beta < alpha) { throw new NumberFormatException(); }

                MainApplication.mainMenu.setDisable(true);

                MainApplication.launcher = new Launcher(countOfSources, countOfRequests, countOfInstruments,
                        sizeOfBuffer, alpha, beta, lambda);
                MainApplication.statistics = MainApplication.launcher.run();

                simulate.setVisible(true);
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect data!");
                alert.showAndWait();
            }
        });

        simulate.setOnAction(event -> {
            MainApplication.autoMenu = new AutoMenu();
            MainApplication.stepMenu = new StepMenu();
            MainApplication.mainMenu.setDisable(false);
            MainApplication.setContent(MainApplication.autoMenu);
        });
    }
    private void addConfigureObjects() {
        Label labelSources = new Label("Number of sources: ");
        textFieldSources = new TextField("5");
        Label labelRequests = new Label("Number of requests: ");
        textFieldRequests = new TextField("1000");
        Label labelInstruments = new Label("Number of instruments: ");
        textFieldInstruments = new TextField("5");
        Label labelBuffer = new Label("Size of buffer: ");
        textFieldBuffer = new TextField("100"); //100
        Label labelAlpha = new Label("Alpha: ");
        textFieldAlpha = new TextField("190"); //199
        Label labelBeta = new Label("Beta: ");
        textFieldBeta = new TextField("200");
        Label labelLambda = new Label("Lambda: ");
        textFieldLambda = new TextField("0.001");

        FlowPane sourcesLayout = new FlowPane(Orientation.HORIZONTAL, labelSources, textFieldSources);
        FlowPane requestsLayout = new FlowPane(Orientation.HORIZONTAL, labelRequests, textFieldRequests);
        FlowPane instrumentsLayout = new FlowPane(Orientation.HORIZONTAL, labelInstruments, textFieldInstruments);
        FlowPane bufferLayout = new FlowPane(Orientation.HORIZONTAL, labelBuffer, textFieldBuffer);
        FlowPane alphaLayout = new FlowPane(Orientation.HORIZONTAL, labelAlpha, textFieldAlpha);
        FlowPane betaLayout = new FlowPane(Orientation.HORIZONTAL, labelBeta, textFieldBeta);
        FlowPane lambdaLayout = new FlowPane(Orientation.HORIZONTAL, labelLambda, textFieldLambda);

        layout.getChildren().addAll(sourcesLayout, requestsLayout, instrumentsLayout, bufferLayout, alphaLayout, betaLayout, lambdaLayout);
        layout.setVgap(8);
        layout.setHgap(15);
        layout.setAlignment(Pos.CENTER);
    }
}
