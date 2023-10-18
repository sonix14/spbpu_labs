module AutoPark {
    requires javafx.controls;
    requires javafx.base;
    requires javafx.web;
    requires java.sql;

	opens autopark to javafx.graphics, javafx.fxml;
    //exports com.sun.javafx.collections;
}