package rupizza.ru_pizza;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class CurrentOrderController {

    @FXML
    private TextField onum;
    @FXML
    private TextField stotal;
    @FXML
    private TextField sTax;
    @FXML
    private TextField oTotal;
    @FXML
    private Button removePizzaButton;
    @FXML
    private Button placeOrderButton;
    @FXML
    private ListView<String> oDetail;

    private Order currentOrder;

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
        updateOrderDetails(currentOrder);
    }

    @FXML
    private void initialize() {
        //removePizzaButton.setOnAction(event -> removeSelectedPizza());
        //placeOrderButton.setOnAction(event -> placeOrder());
        //oDetail.setOnMouseClicked(this::handleListViewClick);

    }

    private void handleListViewClick(MouseEvent event) {
        // Enable or disable buttons based on selection
        boolean pizzaSelected = !oDetail.getSelectionModel().getSelectedItems().isEmpty();
        removePizzaButton.setDisable(!pizzaSelected);
    }

    public void updateOrderDetails(Order currentOrder) {
        onum.setText(String.valueOf(currentOrder.getOrderNumber()));
        stotal.setText(String.format("%.2f", currentOrder.calculateOrderTotal()));
        double tax=Double.parseDouble(sTax.getText());
        oTotal.setText(String.format("%.2f", currentOrder.calculateOrderWithTax(tax)));
        updateOrderListView();
    }

    public void updateOrderListView() {
        oDetail.setItems(FXCollections.observableArrayList(currentOrder.getPizzasAsString()));
    }
    public void removePizza(String pizzaString) {
        // Find the pizza in the list based on some identifier (e.g., name or toString() representation) and remove it
        // Implement this logic based on how you uniquely identify pizzas

        // Example: Assuming the pizzaString represents the pizza name
        currentOrder.getPizzas().removeIf(pizza -> pizza.toString().equals(pizzaString));
    }

    private void removeSelectedPizza() {
        String selectedPizzaString = oDetail.getSelectionModel().getSelectedItem();
        if (selectedPizzaString != null) {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Remove Pizza");
            confirmation.setHeaderText(null);
            confirmation.setContentText("Are you sure you want to remove the selected pizza?");
            Optional<ButtonType> result = confirmation.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                removePizza(selectedPizzaString);
                updateOrderDetails(currentOrder);
            }
        }
    }


    private void placeOrder() {
        if (currentOrder.getPizzas().isEmpty()) {
            showInformationDialog("Empty Order", "Please add pizzas to the order before placing it.");
            return;
        }
        //currentOrder.clearOrder();
        updateOrderDetails(currentOrder);
    }

    private void showInformationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
