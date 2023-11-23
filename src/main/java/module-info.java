module com.example.ru_pizza {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ru_pizza to javafx.fxml;
    exports com.example.ru_pizza;
}