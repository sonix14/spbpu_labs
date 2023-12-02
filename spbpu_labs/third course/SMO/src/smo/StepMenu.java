package com.example.smo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class StepMenu extends VBox {
    BorderPane layout = new BorderPane();
    FlowPane top = new FlowPane(Orientation.HORIZONTAL);
    FlowPane bottom = new FlowPane(Orientation.HORIZONTAL);
    FlowPane buttons = new FlowPane(Orientation.HORIZONTAL);
    FlowPane stepsPane = new FlowPane(Orientation.VERTICAL);
    TableView<TableViewStepSources> sourcesTable = new TableView<>();
    ObservableList<TableViewStepSources> sourcesData = FXCollections.observableArrayList();
    TableView<TableViewStepInstruments> instrumentsTable = new TableView<>();
    ObservableList<TableViewStepInstruments> instrumentsData = FXCollections.observableArrayList();
    TableView<TableViewStepBuffer> bufferTable = new TableView<>();
    ObservableList<TableViewStepBuffer> bufferData = FXCollections.observableArrayList();
    Text eventText;
    //Text timeText;
    Text stepText;
    Text allStepsText;
    TextField enterStepText;
    Button nextStep = new Button("Next step");
    Button backStep = new Button("Previous step");
    Button goToStep = new Button("Go");

    public StepMenu() {
        setTablesData(1);
        setTablesContent();
        addEventObjects();
        setEventPane(1);

        setButtons();

        top.getChildren().addAll(stepsPane, sourcesTable);
        bottom.getChildren().addAll(bufferTable, instrumentsTable);
        bottom.setHgap(20);
        bottom.setVgap(20);

        layout.setTop(top);
        layout.setCenter(bottom);
        layout.setBottom(buttons);

        getChildren().add(layout);
    }
    private void setButtons() {
        nextStep.setOnAction(event -> {
            int nextStep = Integer.parseInt(stepText.getText()) + 1;
            if (nextStep <= MainApplication.statistics.getStepCount()) {
                stepText.setText(Integer.toString(nextStep));
                setTablesData(nextStep);
                setEventPane(nextStep);
            }
        });

        backStep.setOnAction(event -> {
            int previousStep = Integer.parseInt(stepText.getText()) - 1;
            if (previousStep > 0) {
                stepText.setText(Integer.toString(previousStep));
                setTablesData(previousStep);
                setEventPane(previousStep);
            }
        });

        buttons.getChildren().addAll(backStep, nextStep);
        buttons.setHgap(50);

        goToStep.setOnAction(event -> {
            try {
                int step = Integer.parseInt(enterStepText.getText());
                if (step <= MainApplication.statistics.getStepCount() && step > 0) {
                    stepText.setText(Integer.toString(step));
                    setTablesData(step);
                    setEventPane(step);
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect data!");
                alert.showAndWait();
            }
        });
    }
    private void setEventPane(int step) {
        EventPairs pair = MainApplication.statistics.getEventsMap().get(step);
        eventText.setText(pair.getEventText());
        //timeText.setText(Double.toString(pair.getTime()));
    }
    private void setTablesData(int step) {
        setSourcesData(step);
        setBufferData(step);
        setInstrumentsData(step);
    }
    private void setTablesContent() {
        setSourcesContent();
        setBufferContent();
        setInstrumentsContent();
    }
    private void addEventObjects() {
        Label eventLabel = new Label("Event: ");
        eventText = new Text();
        eventText.setX(20);
        eventText.setDisable(true);
        //Label timeLabel = new Label("Time: ");
        //timeText = new Text("0.0");
        //timeText.setDisable(true);
        Label stepLabel = new Label("Step: ");
        stepText = new Text("1");
        stepText.setDisable(true);
        Label allStepsLabel = new Label("All steps: ");
        allStepsText = new Text(Integer.toString(MainApplication.statistics.getStepCount()));
        allStepsText.setDisable(true);
        Label enterStepLabel = new Label("Enter the step: ");
        enterStepText = new TextField();

        FlowPane eventLayout = new FlowPane(Orientation.HORIZONTAL, eventLabel, eventText);
        //FlowPane timeLayout = new FlowPane(Orientation.HORIZONTAL, timeLabel, timeText);
        FlowPane stepLayout = new FlowPane(Orientation.HORIZONTAL, stepLabel, stepText);
        FlowPane allStepsLayout = new FlowPane(Orientation.HORIZONTAL, allStepsLabel, allStepsText);
        FlowPane enterStepLayout = new FlowPane(Orientation.HORIZONTAL, enterStepLabel, enterStepText, goToStep);

        //stepsPane.getChildren().addAll(eventLayout, timeLayout, stepLayout, allStepsLayout, enterStepLayout);
        stepsPane.getChildren().addAll(eventLayout, stepLayout, allStepsLayout, enterStepLayout);
        stepsPane.setVgap(8);
        stepsPane.setHgap(15);
        stepsPane.setPrefWrapLength(300);
    }
    private void setSourcesContent() {
        TableColumn<TableViewStepSources, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sourcesTable.getColumns().add(nameColumn);

        TableColumn<TableViewStepSources, Integer> requestCountColumn = new TableColumn<>("Request count");
        requestCountColumn.setCellValueFactory(new PropertyValueFactory<>("requestCount"));
        sourcesTable.getColumns().add(requestCountColumn);

        TableColumn<TableViewStepSources, Integer> rejectionCountColumn = new TableColumn<>("Reject count");
        rejectionCountColumn.setCellValueFactory(new PropertyValueFactory<>("rejectCount"));
        sourcesTable.getColumns().add(rejectionCountColumn);
    }
    private void setBufferContent() {
        TableColumn<TableViewStepBuffer, String> pointerColumn = new TableColumn<>("Pointer");
        pointerColumn.setCellValueFactory(new PropertyValueFactory<>("pointer"));
        bufferTable.getColumns().add(pointerColumn);

        TableColumn<TableViewStepBuffer, Integer> placeColumn = new TableColumn<>("Place");
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("placeNum"));
        bufferTable.getColumns().add(placeColumn);

        TableColumn<TableViewStepBuffer, String> stateColumn = new TableColumn<>("  State  ");
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        bufferTable.getColumns().add(stateColumn);

        TableColumn<TableViewStepBuffer, String> requestIdColumn = new TableColumn<>("Request ID");
        requestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        bufferTable.getColumns().add(requestIdColumn);

        TableColumn<TableViewStepBuffer, String> sourceIdColumn = new TableColumn<>("Source ID");
        sourceIdColumn.setCellValueFactory(new PropertyValueFactory<>("sourceId"));
        bufferTable.getColumns().add(sourceIdColumn);
    }
    private void setInstrumentsContent() {
        TableColumn<TableViewStepInstruments, String> pointerColumn = new TableColumn<>("Pointer");
        pointerColumn.setCellValueFactory(new PropertyValueFactory<>("pointer"));
        instrumentsTable.getColumns().add(pointerColumn);

        TableColumn<TableViewStepInstruments, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        instrumentsTable.getColumns().add(nameColumn);

        TableColumn<TableViewStepInstruments, String> stateColumn = new TableColumn<>("State");
        stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        instrumentsTable.getColumns().add(stateColumn);

        TableColumn<TableViewStepInstruments, String> requestIdColumn = new TableColumn<>("Request ID");
        requestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        instrumentsTable.getColumns().add(requestIdColumn);

        TableColumn<TableViewStepInstruments, String> sourceIdColumn = new TableColumn<>("Source ID");
        sourceIdColumn.setCellValueFactory(new PropertyValueFactory<>("sourceId"));
        instrumentsTable.getColumns().add(sourceIdColumn);
    }
    private void setSourcesData(int step) {
        sourcesData.clear();

        ArrayList<ArrayList<String>> fullData = MainApplication.statistics.getSourcesSteps().get(step);
        for (ArrayList<String> data : fullData) {
            sourcesData.add(new TableViewStepSources(data));
        }

        sourcesTable.setItems(sourcesData);
        sourcesTable.setPrefWidth(300);
        sourcesTable.setPrefHeight(300);
    }
    private void setBufferData(int step) {
        bufferData.clear();

        ArrayList<ArrayList<String>> fullData = MainApplication.statistics.getBufferSteps().get(step);
        for (ArrayList<String> data : fullData) {
            bufferData.add(new TableViewStepBuffer(data));
        }

        bufferTable.setItems(bufferData);
        bufferTable.setPrefWidth(380);
        bufferTable.setMaxHeight(300);
    }
    private void setInstrumentsData(int step) {
        instrumentsData.clear();

        ArrayList<ArrayList<String>> fullData = MainApplication.statistics.getInstrumentsSteps().get(step);
        for (ArrayList<String> data : fullData) {
            instrumentsData.add(new TableViewStepInstruments(data));
        }

        instrumentsTable.setItems(instrumentsData);
        instrumentsTable.setPrefWidth(352);
        instrumentsTable.setMaxHeight(300);
    }
}
