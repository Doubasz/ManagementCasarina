module org.example.casarinagestion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.casarinagestion to javafx.fxml;
    exports org.example.casarinagestion;
}