package autopark;

import autopark.tableViews.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.security.NoSuchAlgorithmException;

public class Main extends Application {

    public static MainMenu  mainMenu = new MainMenu();
    public static VBox root = new VBox();
    public static ClientsTableView clientsTableView = new ClientsTableView();
    public static BookTypesTableView bookTypesTableView = new BookTypesTableView();
    public static BooksTableView booksTableView = new BooksTableView();
    public static JournalTableView journalTableView = new JournalTableView();
    public static SecurityManager securityManager;
    public static LogInScreen logInScreen = new LogInScreen();
    public static ReportsView reportsView = new ReportsView();

    public static Stage primaryStage;

    public static void refresh(){
        clientsTableView.refresh();
        bookTypesTableView.refresh();
        booksTableView.refresh();
        journalTableView.refresh();
    }

    public static void setContent(Node node){
        root.getChildren().clear();
        root.getChildren().add(mainMenu);
        root.getChildren().add(node);
    }

    public void start(Stage primaryStage) throws NoSuchAlgorithmException {
        mainMenu.setDisable(true);
        securityManager = new SecurityManager();
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Library");
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });

        setContent(logInScreen);

        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();

    }
}
