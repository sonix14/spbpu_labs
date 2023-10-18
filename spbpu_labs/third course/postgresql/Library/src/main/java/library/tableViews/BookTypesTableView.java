package autopark.tableViews;

import autopark.HibernateUtil;
import autopark.model.Books;
import autopark.model.BookTypes;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Objects;

public class BookTypesTableView extends AbstractTableView<BookTypes> {
    TableColumn<BookTypes, Integer> idColumn;
    TableColumn<BookTypes, String> nameColumn;
    TableColumn<BookTypes, String> cntColumn;
    TableColumn<BookTypes, String> fineColumn;
    TableColumn<BookTypes, String> dayCountColumn;

    @Override
    public void createColumns() {
        nameColumn = new TableColumn<>("Название");
        cntColumn = new TableColumn<>("Количество");
        fineColumn = new TableColumn<>("Штраф");
        dayCountColumn = new TableColumn<>("Ограничение по дням");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        cntColumn.setCellValueFactory(new PropertyValueFactory<>("cnt"));
        fineColumn.setCellValueFactory(new PropertyValueFactory<>("fine"));
        dayCountColumn.setCellValueFactory(new PropertyValueFactory<>("dayCount"));

        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<BookTypes, Integer>("id"));

        table.getColumns().add(idColumn);
        table.getColumns().add(nameColumn);
        table.getColumns().add(cntColumn);
        table.getColumns().add(fineColumn);
        table.getColumns().add(dayCountColumn);
    }

    @Override
    public AbstractAddPanel<BookTypes> getAddPanel() {
        return new BookTypesAddPanel();
    }

    @Override
    public Class<BookTypes> getTClass() {
        return BookTypes.class;
    }

    @Override
    public boolean isPossibleToDelete(BookTypes data) {
        return HibernateUtil.getAll(Books.class).stream()
                .noneMatch(auto -> Objects.equals(auto.getType(),data));
    }
}
