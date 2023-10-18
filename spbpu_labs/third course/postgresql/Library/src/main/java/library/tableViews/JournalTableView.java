package autopark.tableViews;

import autopark.model.Journal;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class JournalTableView extends AbstractTableView<Journal> {
    TableColumn<Journal, Integer> idColumn;
    TableColumn<Journal, String> dateBegColumn;
    TableColumn<Journal, String> dateEndColumn;
    TableColumn<Journal, String> dateRetColumn;
    TableColumn<Journal, String> bookColumn;
    TableColumn<Journal, String> clientColumn;

    @Override
    public void createColumns() {
        idColumn = new TableColumn<>("ID");
        dateBegColumn = new TableColumn<>("Дата выдачи");
        dateEndColumn = new TableColumn<>("Дата окончания");
        dateRetColumn = new TableColumn<>("Дата возврата");
        bookColumn = new TableColumn<>("Книга");
        clientColumn = new TableColumn<>("Клиент");

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateBegColumn.setCellValueFactory(new PropertyValueFactory<>("dateBeg"));
        dateEndColumn.setCellValueFactory(new PropertyValueFactory<>("dateEnd"));
        dateRetColumn.setCellValueFactory(new PropertyValueFactory<>("dateRet"));
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("book"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));

        table.getColumns().add(idColumn);
        table.getColumns().add(dateBegColumn);
        table.getColumns().add(dateEndColumn);
        table.getColumns().add(dateRetColumn);
        table.getColumns().add(bookColumn);
        table.getColumns().add(clientColumn);
    }

    @Override
    public AbstractAddPanel<Journal> getAddPanel() {
        return new JournalAddPanel();
    }

    @Override
    public Class<Journal> getTClass() {
        return Journal.class;
    }

    @Override
    public boolean isPossibleToDelete(Journal data) {
        return dateRetColumn.getCellValueFactory() != null;
    }
}
