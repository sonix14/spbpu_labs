package autopark.tableViews;

import autopark.HibernateUtil;
import autopark.model.Books;
import autopark.model.BookTypes;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class BooksAddPanel extends AbstractAddPanel<Books> {

    Label nameLabel;
    TextField nameTextField;
    Label cntLabel;
    TextField cntTextField;
    Label typeLabel;
    ComboBox<BookTypes> typeComboBox;


    @Override
    public void onRefresh() {
        idTextField.setText(String.valueOf(data.getId()));
        nameTextField.setText(data.getName());
        cntTextField.setText(String.valueOf(data.getCnt()));
        typeComboBox.setItems(new ObservableListWrapper<>(HibernateUtil.getAll(BookTypes.class)));
        typeComboBox.setValue(data.getType());
    }

    @Override
    public void addComponents() {
        nameLabel = new Label("Название");
        nameTextField = new TextField();
        cntLabel = new Label("Количество");
        cntTextField = new TextField();
        typeLabel = new Label("Тип");
        typeComboBox = new ComboBox<>();

        root.getChildren().add(new HBox(nameLabel, nameTextField));
        root.getChildren().add(new HBox(cntLabel, cntTextField));
        root.getChildren().add(new HBox(typeLabel, typeComboBox));
    }

    @Override
    public Books formData() {
        return new Books(
                Integer.parseInt(idTextField.getText()),
                nameTextField.getText(),
                Integer.parseInt(cntTextField.getText()),
                typeComboBox.getValue()
        );
    }

    @Override
    public Books emptyData() {
        return new Books(0, "", 0, null);
    }
}
