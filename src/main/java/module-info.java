module com.example.chess {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires lombok;
    requires java.desktop;

    opens com.example.chess to javafx.fxml;
    exports com.example.chess;
    exports com.example.chess.controller;
    opens com.example.chess.controller to javafx.fxml;
}