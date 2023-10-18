package autopark.tableViews;

import autopark.HibernateUtil;
//import autopark.model.BookTypes;
import autopark.model.Journal;
import autopark.model.Clients;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Objects;

public class ClientsTableView extends AbstractTableView<Clients> {
    TableColumn<Clients, Integer> idColumn;
    TableColumn<Clients, String> lastNameColumn;
    TableColumn<Clients, String> firstNameColumn;
    TableColumn<Clients, String> fatherNameColumn;
    TableColumn<Clients, String> passportSeriaColumn;
    TableColumn<Clients, String> passportNumColumn;

    @Override
    public void createColumns() {
        //idColumn = new TableColumn<>("ID");
        lastNameColumn = new TableColumn<>("Фамилия");
        firstNameColumn = new TableColumn<>("Имя");
        fatherNameColumn = new TableColumn<>("Отчество");
        passportSeriaColumn = new TableColumn<>("Серия паспорта");
        passportNumColumn = new TableColumn<>("Номер паспорта");

        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        fatherNameColumn.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        passportSeriaColumn.setCellValueFactory(new PropertyValueFactory<>("passportSeria"));
        passportNumColumn.setCellValueFactory(new PropertyValueFactory<>("passportNum"));

        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<Clients, Integer>("id"));

        table.getColumns().add(idColumn);
        table.getColumns().add(lastNameColumn);
        table.getColumns().add(firstNameColumn);
        table.getColumns().add(fatherNameColumn);
        table.getColumns().add(passportSeriaColumn);
        table.getColumns().add(passportNumColumn);
    }

    @Override
    public AbstractAddPanel<Clients> getAddPanel() {
        return new ClientsAddPanel();
    }

    @Override
    public Class<Clients> getTClass() {
        return Clients.class;
    }

    @Override
    public boolean isPossibleToDelete(Clients data) {
        return HibernateUtil.getAll(Journal.class).stream()
                .noneMatch(journal -> Objects.equals(journal.getClient(),data));
    }
}
