module org.example.javafxxpostgresql {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires java.desktop;


    opens org.example.javafxxpostgresql to javafx.fxml;
    exports org.example.javafxxpostgresql;
}