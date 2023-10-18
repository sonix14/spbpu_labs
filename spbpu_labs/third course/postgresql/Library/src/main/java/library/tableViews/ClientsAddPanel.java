package autopark.tableViews;

import autopark.model.Clients;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class ClientsAddPanel extends AbstractAddPanel<Clients> {
    Label lastNameLabel;
    TextField lastNameTextField;
    Label firstNameLabel;
    TextField firstNameTextField;
    Label fatherNameLabel;
    TextField fatherNameTextField;
    Label passportSeriaLabel;
    TextField passportSeriaTextField;
    Label passportNumLabel;
    TextField passportNumTextField;

    @Override
    public void onRefresh() {
        idTextField.setText(String.valueOf(data.getId()));
        lastNameTextField.setText(data.getLastName());
        firstNameTextField.setText(data.getFirstName());
        fatherNameTextField.setText(data.getFatherName());
        passportSeriaTextField.setText(data.getPassportSeria());
        passportNumTextField.setText(data.getPassportNum());
    }

    @Override
    public void addComponents() {
        lastNameLabel = new Label("Фамилия");
        lastNameTextField = new TextField();
        firstNameLabel = new Label("Имя");
        firstNameTextField = new TextField();
        fatherNameLabel = new Label("Отчество");
        fatherNameTextField = new TextField();
        passportSeriaLabel = new Label("Серия паспорта");
        passportSeriaTextField = new TextField();
        passportNumLabel = new Label("Номер паспорта");
        passportNumTextField = new TextField();

        root.getChildren().add(new HBox(lastNameLabel, lastNameTextField));
        root.getChildren().add(new HBox(firstNameLabel, firstNameTextField));
        root.getChildren().add(new HBox(fatherNameLabel, fatherNameTextField));
        root.getChildren().add(new HBox(passportSeriaLabel, passportSeriaTextField));
        root.getChildren().add(new HBox(passportNumLabel, passportNumTextField));
    }

    @Override
    public Clients formData() {
        return new Clients(
                Integer.parseInt(idTextField.getText()),
                lastNameTextField.getText(),
                firstNameTextField.getText(),
                fatherNameTextField.getText(),
                passportSeriaTextField.getText(),
                passportNumTextField.getText()
        );
    }

    @Override
    public Clients emptyData() {
        return new Clients(0, "", "", "", "", "");
    }
}
