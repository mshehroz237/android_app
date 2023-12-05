package rupizza.ru_pizza;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class StoreOrderController {

    @FXML
    private ChoiceBox<String> oNum;

    @FXML
    private TextField oTotal;

    @FXML
    private ListView<String> sOrders;

    @FXML
    private Button cancel;

    @FXML
    private Button export;

    @FXML
    private Label storeOrdersLabel;

    // You may need to add a reference to your data model or service here

    @FXML
    protected void initialize() {
        // Initialize the ChoiceBox with order numbers (you need to provide the data)
        oNum.setItems(FXCollections.observableArrayList("Order1", "Order2", "Order3"));

        // Add event handlers for the buttons
        cancel.setOnAction(event -> onCancelButtonClick());
        export.setOnAction(event -> onExportButtonClick());


        sOrders.setItems(FXCollections.observableArrayList("Store Order 1", "Store Order 2"));
    }

    // Define methods for button actions
    private void onCancelButtonClick() {

    }

    private void onExportButtonClick() {

    }
}
