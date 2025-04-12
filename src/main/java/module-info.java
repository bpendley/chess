module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    opens com.example.chess to javafx.fxml;
    exports com.example.chess;
}