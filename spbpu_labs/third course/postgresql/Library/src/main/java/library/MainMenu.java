package autopark;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MainMenu extends MenuBar {

    Menu journal = new Menu("Журнал");
    Menu reference = new Menu("Справочники");
    Menu reports = new Menu("Отчеты");

    MenuItem showJournal = new MenuItem("Журнал");
    MenuItem books = new MenuItem("Книги");
    MenuItem clients = new MenuItem("Читатели");
    MenuItem book_types = new MenuItem("Типы книг");
    MenuItem showReport = new MenuItem("Отчеты");

    public MainMenu() {
        reference.getItems().addAll(clients, book_types, books);
        journal.getItems().add(showJournal);
        reports.getItems().add(showReport);
        this.getMenus().addAll(journal, reference, reports);
        //this.getMenus().addAll(journal, reference);

        showJournal.setOnAction(event -> Main.setContent(Main.journalTableView));
        books.setOnAction(event -> Main.setContent(Main.booksTableView));
        clients.setOnAction(event -> Main.setContent(Main.clientsTableView));
        book_types.setOnAction(event -> Main.setContent(Main.bookTypesTableView));
        showReport.setOnAction(event -> Main.setContent(Main.reportsView));

    }
}
