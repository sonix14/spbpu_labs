package com.example.smo;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AutoMenu extends VBox {
    BorderPane scene = new BorderPane();
    ObservableList<TableViewAutoSources> sourcesData = FXCollections.observableArrayList();
    ObservableList<TableViewAutoInstruments> instrumentsData = FXCollections.observableArrayList();
    TableView<TableViewAutoSources> sourcesTable = new TableView<>();
    TableView<TableViewAutoInstruments> instrumentsTable = new TableView<>();

    public AutoMenu() {
        setSourcesData();
        setInstrumentsData();

        HBox sourcesBox = new HBox(sourcesTable);
        HBox instrumentsBox = new HBox(instrumentsTable);
        scene.setTop(sourcesBox);
        scene.setBottom(instrumentsBox);

        getChildren().addAll(scene);
    }
    private void setSourcesContent() {
        TableColumn<TableViewAutoSources, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sourcesTable.getColumns().add(nameColumn);

        TableColumn<TableViewAutoSources, Integer> requestCountColumn = new TableColumn<>("Request count");
        requestCountColumn.setCellValueFactory(new PropertyValueFactory<>("requestCount"));
        sourcesTable.getColumns().add(requestCountColumn);

        TableColumn<TableViewAutoSources, Integer> rejectionCountColumn = new TableColumn<>("Reject count");
        rejectionCountColumn.setCellValueFactory(new PropertyValueFactory<>("rejectCount"));
        sourcesTable.getColumns().add(rejectionCountColumn);

        TableColumn<TableViewAutoSources, Double> timeInSystemColumn = new TableColumn<>("Time in system");
        timeInSystemColumn.setCellValueFactory(new PropertyValueFactory<>("timeInSystem"));
        sourcesTable.getColumns().add(timeInSystemColumn);

        TableColumn<TableViewAutoSources, Double> processingTimeColumn = new TableColumn<>("Processing time");
        processingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("processingTime"));
        sourcesTable.getColumns().add(processingTimeColumn);

        TableColumn<TableViewAutoSources, Double> waitingTimeColumn = new TableColumn<>("Waiting time");
        waitingTimeColumn.setCellValueFactory(new PropertyValueFactory<>("waitingTime"));
        sourcesTable.getColumns().add(waitingTimeColumn);

        TableColumn<TableViewAutoSources, Double> dispTOWColumn = new TableColumn<>("Disp. of waiting time");
        dispTOWColumn.setCellValueFactory(new PropertyValueFactory<>("dispTOW"));
        sourcesTable.getColumns().add(dispTOWColumn);

        TableColumn<TableViewAutoSources, Double> dispTOPColumn = new TableColumn<>("Disp. of processing time");
        dispTOPColumn.setCellValueFactory(new PropertyValueFactory<>("dispTOP"));
        sourcesTable.getColumns().add(dispTOPColumn);

        TableColumn<TableViewAutoSources, Integer> rejectionPercentColumn = new TableColumn<>("Reject percent, %");
        rejectionPercentColumn.setCellValueFactory(new PropertyValueFactory<>("rejectionPercent"));
        sourcesTable.getColumns().add(rejectionPercentColumn);
    }
    private void setInstrumentsContent() {
        TableColumn<TableViewAutoInstruments, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        instrumentsTable.getColumns().add(nameColumn);

        TableColumn<TableViewAutoInstruments, Double> coefficientColumn = new TableColumn<>("Coefficient");
        coefficientColumn.setCellValueFactory(new PropertyValueFactory<>("coefficient"));
        instrumentsTable.getColumns().add(coefficientColumn);
    }
    private void setSourcesData() {
        for (Source source : MainApplication.statistics.getSourcesList()) {
            sourcesData.add(new TableViewAutoSources(source, MainApplication.statistics));
        }
        sourcesTable.setItems(sourcesData);
        setSourcesContent();

        sourcesTable.setPrefWidth(1000);
        sourcesTable.setPrefHeight(250);
    }
    private void setInstrumentsData() {
        for (Instruments instrument : MainApplication.statistics.getInstrumentsList()) {
            instrumentsData.add(new TableViewAutoInstruments(instrument, MainApplication.statistics));
        }
        instrumentsTable = new TableView<>(instrumentsData);
        setInstrumentsContent();

        instrumentsTable.setPrefWidth(200);
        instrumentsTable.setPrefHeight(250);
    }
}
