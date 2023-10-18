package autopark.tableViews;

import autopark.HibernateUtil;
import autopark.model.Books;
import autopark.model.Journal;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Objects;

public class BooksTableView extends AbstractTableView<Books> {
    TableColumn<Books, Integer> idColumn;
    TableColumn<Books, String> nameColumn;
    TableColumn<Books, String> cntColumn;
    TableColumn<Books, String> typeColumn;

    @Override
    public void createColumns() {
        nameColumn = new TableColumn<>("Название");
        cntColumn = new TableColumn<>("Количество");
        typeColumn = new TableColumn<>("Тип");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cntColumn.setCellValueFactory(new PropertyValueFactory<>("cnt"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(cntColumn);
        table.getColumns().add(typeColumn);
    }

    @Override
    public AbstractAddPanel<Books> getAddPanel() {
        return new BooksAddPanel();
    }

    @Override
    public Class<Books> getTClass() {
        return Books.class;
    }

    @Override
    public boolean isPossibleToDelete(Books data) {
        return HibernateUtil.getAll(Journal.class).stream()
                .noneMatch(journal -> Objects.equals(journal.getBook(), data));
    }
}
