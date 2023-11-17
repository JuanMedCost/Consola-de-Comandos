module com.example.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.proyecto to javafx.fxml;
    exports com.example.proyecto;
}