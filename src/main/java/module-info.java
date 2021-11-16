module com.example.javafxco1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires json.simple;
    requires okhttp3;
    requires lombok;


    opens com.example.javafxco1 to javafx.fxml;
    exports com.example.javafxco1;
}