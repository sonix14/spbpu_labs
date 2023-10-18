package autopark.tableViews;

import autopark.HibernateUtil;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import javax.persistence.*;
import java.util.List;
import java.util.Objects;

public class ReportsView extends VBox {
    Label resultLabel = new Label("Результат");
    TextField resultTextField = new TextField();
    Label inputLabel = new Label("Введите имя читателя");
    TextField inputTextField = new TextField();
    String flag = "";
    Button countBooksButton = new Button("Посчитать число книг у заданного клиента");
    Button maxFineButton = new Button("Посчитать самый большой штраф");
    Button clientsFineButton = new Button("Посчитать штраф клиента");
    Button topBooksButton = new Button("Вывести самые популярные книги");
    Button confirmButton = new Button("Подтвердить");
    //final ObservableList<T> data = FXCollections.observableArrayList();//new ObservableListWrapper<>(new ArrayList<>());

    public ReportsView() {
        resultLabel.setVisible(false);
        resultTextField.setVisible(false);
        resultTextField.setEditable(false);
        inputLabel.setVisible(false);
        inputTextField.setVisible(false);
        confirmButton.setVisible(false);

        countBooksButton.setOnAction(event -> {
            resultLabel.setVisible(false);
            resultTextField.setVisible(false);
            inputTextField.clear();

            flag = "countBooks";
            inputLabel.setVisible(true);
            inputTextField.setVisible(true);
            confirmButton.setVisible(true);
        });

        maxFineButton.setOnAction(event -> {
            inputLabel.setVisible(false);
            inputTextField.setVisible(false);
            confirmButton.setVisible(false);

            callMaxFineProcedure();
        });

        clientsFineButton.setOnAction(event -> {
            resultLabel.setVisible(false);
            resultTextField.setVisible(false);
            inputTextField.clear();

            flag = "clientsFine";
            inputLabel.setVisible(true);
            inputTextField.setVisible(true);
            confirmButton.setVisible(true);
        });

        topBooksButton.setOnAction(event -> {
            inputLabel.setVisible(false);
            inputTextField.setVisible(false);
            confirmButton.setVisible(false);

            callTopBooksProcedure();
        });

        confirmButton.setOnAction(event -> {
            String[] splitted = inputTextField.getText().split(" ");
            if (splitted.length != 3) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Неверный формат имени читателя!");
                alert.showAndWait();
            } else {
                if (Objects.equals(flag, "countBooks")) {
                    callCountBooksProcedure(splitted[0], splitted[1], splitted[2]);
                } else if (Objects.equals(flag, "clientsFine")) {
                    callClientsFineProcedure(splitted[0], splitted[1], splitted[2]);
                }
            }
        });

        getChildren().add(new HBox(countBooksButton));
        getChildren().add(new HBox(maxFineButton));
        getChildren().add(new HBox(clientsFineButton));
        getChildren().add(new HBox(topBooksButton));
        getChildren().add(new HBox(inputLabel, inputTextField));
        getChildren().add(new HBox(confirmButton));
        getChildren().add(new HBox(resultLabel, resultTextField));
        refresh();
    }

    public void refresh(){
        //data.clear();
        //data.addAll(getData());
        //table.getSelectionModel().clearSelection();
    }

    private void callCountBooksProcedure(String lastName, String firstName, String fatherName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("countBooksStoreProcedure")
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName)
                .setParameter("fatherName", fatherName);

        List result = query.getResultList();
        if (result.get(0) != null) {
            resultTextField.setText(result.get(0).toString());
            resultTextField.setPrefColumnCount(10);
        } else {
            resultTextField.setText("0");
            resultTextField.setPrefColumnCount(30);
        }
        resultTextField.setVisible(true);
        resultLabel.setVisible(true);

        session.close();
    }

    private void callMaxFineProcedure() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("maxFineStoreProcedure");

        List result = query.getResultList();
        if (result.get(0) != null) {
            resultTextField.setText(result.get(0).toString());
            resultTextField.setPrefColumnCount(10);
        } else {
            resultTextField.setText("0.0");
            resultTextField.setPrefColumnCount(30);
        }
        resultTextField.setVisible(true);
        resultLabel.setVisible(true);

        session.close();
    }

    private void callClientsFineProcedure(String lastName, String firstName, String fatherName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("clientFineStoreProcedure")
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName)
                .setParameter("fatherName", fatherName);

        List result = query.getResultList();
        if (result.get(0) != null) {
            resultTextField.setText(result.get(0).toString());
            resultTextField.setPrefColumnCount(10);
        } else {
            resultTextField.setText("0.0");
            resultTextField.setPrefColumnCount(30);
        }
        resultTextField.setVisible(true);
        resultLabel.setVisible(true);

        session.close();
    }

    private void callTopBooksProcedure() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.getNamedQuery("booksTopStoreProcedure");

        List result = query.getResultList();
        if (result.get(0) != null)
            resultTextField.setText(result.get(0).toString());
        else
            resultTextField.setText("Нет подходящего результата");
        resultTextField.setPrefColumnCount(40);
        resultTextField.setVisible(true);
        resultLabel.setVisible(true);

        session.close();
    }

}
