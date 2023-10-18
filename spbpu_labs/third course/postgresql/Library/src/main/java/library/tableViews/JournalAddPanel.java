package autopark.tableViews;

import autopark.HibernateUtil;
import autopark.model.Books;
import autopark.model.Journal;
import autopark.model.Clients;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import tornadofx.control.DateTimePicker;


public class JournalAddPanel extends AbstractAddPanel<Journal> {

    Label dateBegLabel;
    DateTimePicker dateBegDateTimePicker;
    Label dateEndLabel;
    DateTimePicker dateEndDateTimePicker;
    Label dateRetLabel;
    DateTimePicker dateRetDateTimePicker;
    Label bookLabel;
    ComboBox<Books> bookComboBox;
    Label clientLabel;
    ComboBox<Clients> clientComboBox;

    @Override
    public void onRefresh() {
        idTextField.setText(String.valueOf(data.getId()));
        dateBegDateTimePicker.setDateTimeValue(data.getDateBeg());
        dateEndDateTimePicker.setDateTimeValue(data.getDateEnd());
        dateRetDateTimePicker.setDateTimeValue(data.getDateRet());
        bookComboBox.setItems(new ObservableListWrapper<>(HibernateUtil.getAll(Books.class)));
        bookComboBox.setValue(data.getBook());
        clientComboBox.setItems(new ObservableListWrapper<>(HibernateUtil.getAll(Clients.class)));
        clientComboBox.setValue(data.getClient());
    }

    @Override
    public void addComponents() {
        dateBegLabel = new Label("Начало");
        dateBegDateTimePicker = new DateTimePicker();
        dateEndLabel = new Label("Окончание");
        dateEndDateTimePicker = new DateTimePicker();
        dateRetLabel = new Label("Возврат");
        dateRetDateTimePicker = new DateTimePicker();
        bookLabel = new Label("Книга");
        bookComboBox = new ComboBox<>();
        clientLabel = new Label("Клиент");
        clientComboBox = new ComboBox<>();

        root.getChildren().add(new HBox(dateBegLabel, dateBegDateTimePicker));
        root.getChildren().add(new HBox(dateEndLabel, dateEndDateTimePicker));
        root.getChildren().add(new HBox(dateRetLabel, dateRetDateTimePicker));
        root.getChildren().add(new HBox(bookLabel, bookComboBox));
        root.getChildren().add(new HBox(clientLabel, clientComboBox));
    }

    @Override
    public Journal formData() {
        return new Journal(
                Integer.parseInt(idTextField.getText()),
                dateBegDateTimePicker.getDateTimeValue(),
                dateEndDateTimePicker.getDateTimeValue(),
                dateRetDateTimePicker.getDateTimeValue(),
                bookComboBox.getValue(),
                clientComboBox.getValue()
        );
    }

    @Override
    public Journal emptyData() {
        return new Journal(0, null, null, null, null, null);
    }
}
