package autopark.tableViews;

import autopark.HibernateUtil;
import autopark.Main;
import autopark.model.WithId;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class AbstractTableView<T extends WithId> extends VBox {

    Button addButton = new Button("Добавить");
    Button removeButton = new Button("Удалить");
    Button editButton = new Button("Редактировать");
    TableView<T> table = new TableView<T>();


    final ObservableList<T> data = FXCollections.observableArrayList();


    public abstract void createColumns();
    public abstract AbstractAddPanel<T> getAddPanel();
    public abstract Class<T> getTClass();
    public abstract boolean isPossibleToDelete(T data);

    public List<T> getData(){
        return  HibernateUtil.getAll(getTClass());
    }

    public void onDelete(T record){
        if (isPossibleToDelete(record)) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            Object toDelete = session.get(getTClass(), record.getId());
            session.delete(toDelete);
            session.flush();
            transaction.commit();
            session.close();
            Main.refresh();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Невозможно удалить! Имеются зависимые объекты");
            alert.showAndWait();
        }
    }

    public AbstractTableView() {
        addButton.setOnAction(event -> getAddPanel().showAdd());
        editButton.setOnAction(event -> {
            T record = table.getSelectionModel().getSelectedItem();
            if (record != null) getAddPanel().showEdit(record);
        });

        removeButton.setOnAction(event -> {
            T record = table.getSelectionModel().getSelectedItem();
            if (record != null) onDelete(record);
        });

        table.setItems(data);
        createColumns();
        getChildren().add(table);
        getChildren().add(new HBox(addButton, editButton, removeButton));
        refresh();
    }

    public void refresh(){
        data.clear();
        data.addAll(getData());
        table.getSelectionModel().clearSelection();
    }
}
