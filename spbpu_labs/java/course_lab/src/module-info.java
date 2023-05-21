module course_lab {
	requires javafx.controls;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
}
