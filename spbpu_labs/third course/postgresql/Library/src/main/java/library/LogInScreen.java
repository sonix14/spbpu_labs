package autopark;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LogInScreen extends VBox {

    Label usernameLabel = new Label("Пользователь");
    Label passwordLabel = new Label("Пароль");
    TextField usernameTextField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button logInButton = new Button("Войти");

    public LogInScreen() {
        logInButton.setOnAction(event -> {
            if (Main.securityManager.logIn(usernameTextField.getText(), passwordField.getText())) {
                Main.setContent(Main.journalTableView);
                Main.mainMenu.setDisable(false);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("Неверное имя пользователя или пароль!");
                alert.showAndWait();
            }
        });

        getChildren().add(new HBox(usernameLabel, usernameTextField));
        getChildren().add(new HBox(passwordLabel, passwordField));
        getChildren().add(logInButton);
    }
}
