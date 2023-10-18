package autopark.tableViews;

import autopark.HibernateUtil;
import autopark.Main;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;


public abstract class AbstractAddPanel<T> extends Stage {

    VBox root = new VBox();
    T data;
    boolean add = true;
    Label idLabel = new Label("ID");
    TextField idTextField = new TextField();

    Button okButton = new Button("OK");
    Button cancelButton = new Button("Отмена");

    public abstract void onRefresh();
    public abstract void addComponents();
    public abstract T formData();
    public abstract T emptyData();

    private void refresh(){
        idTextField.setVisible(!add);
        idLabel.setVisible(!add);
        onRefresh();
    }

    public void onOk(){
        data = formData();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        if (add) {
            session.save(data);
        } else {
            session.update(data);
        }

        session.flush();
        transaction.commit();
        session.close();

        Main.refresh();
        close();
    }

    public AbstractAddPanel() {
        idTextField.setDisable(true);

        cancelButton.setOnAction(event -> close());
        okButton.setOnAction(event -> onOk());

        setScene(new Scene(root, 400, 200));
        initOwner(Main.primaryStage);
        initModality(Modality.APPLICATION_MODAL);
        root.getChildren().add(new HBox(idLabel, idTextField));
        addComponents();
        root.getChildren().add(new HBox(okButton, cancelButton));
    }

    public void showAdd(){
        add = true;
        data = emptyData();
        refresh();
        showAndWait();
    }

    public void showEdit(T t){
        add = false;
        data = t;
        refresh();
        showAndWait();
    }
}
