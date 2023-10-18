package autopark.tableViews;

import autopark.model.BookTypes;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.math.BigDecimal;

public class BookTypesAddPanel extends AbstractAddPanel<BookTypes> {
    Label nameLabel;
    TextField nameTextField;
    Label cntLabel;
    TextField cntTextField;
    Label fineLabel;
    TextField fineTextField;
    Label dayCountLabel;
    TextField dayCountTextField;

    @Override
    public void onRefresh() {
        idTextField.setText(String.valueOf(data.getId()));
        nameTextField.setText(data.getName());
        cntTextField.setText(String.valueOf(data.getCnt()));
        fineTextField.setText(String.valueOf(data.getFine()));
        dayCountTextField.setText(String.valueOf(data.getDayCount()));
    }

    @Override
    public void addComponents() {
        nameLabel = new Label("Имя");
        nameTextField = new TextField();
        cntLabel = new Label("Количество");
        cntTextField = new TextField();
        fineLabel = new Label("Штраф");
        fineTextField = new TextField();
        dayCountLabel = new Label("Ограничение");
        dayCountTextField = new TextField();

        root.getChildren().add(new HBox(nameLabel, nameTextField));
        root.getChildren().add(new HBox(cntLabel, cntTextField));
        root.getChildren().add(new HBox(fineLabel, fineTextField));
        root.getChildren().add(new HBox(dayCountLabel, dayCountTextField));
    }

    @Override
    public BookTypes formData() {
        return new BookTypes(
                Integer.parseInt(idTextField.getText()),
                nameTextField.getText(),
                Integer.parseInt(cntTextField.getText()),
                BigDecimal.valueOf(Double.parseDouble(fineTextField.getText())),
                Integer.parseInt(dayCountTextField.getText())
        );
    }

    @Override
    public BookTypes emptyData() {
        return new BookTypes(0, "", 0, BigDecimal.valueOf(0.0), 0);
    }
}
