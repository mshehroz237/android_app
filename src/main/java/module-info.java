module rupizza.ru_pizza {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens rupizza.ru_pizza to javafx.fxml;
    exports rupizza.ru_pizza;
}