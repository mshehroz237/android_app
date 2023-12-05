package rupizza.ru_pizza;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BuildYourOwnController {

    @FXML
    private ChoiceBox<String> pType2;
    @FXML
    private RadioButton sauce1;
    @FXML
    private RadioButton sauce2;
    @FXML
    private CheckBox ec2;
    @FXML
    private CheckBox es3;
    @FXML
    private ListView<String> LV;
    @FXML
    private ListView<String> LV2;
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private TextField price2;
    @FXML
    private Button order2;
    @FXML
    private ToggleGroup t2;

    // Add a reference to the Order instance
    private Order currentOrder = new Order();
    private double basePrice = 0.0; // Initialize base price
    private CurrentOrderController currentOrderController;

    public void setCurrentOrderController(CurrentOrderController currentOrderController) {
        this.currentOrderController = currentOrderController;
    }

    @FXML
    protected void initialize() {
        // Initialize the ChoiceBox with pizza sizes
        List<String> pizzaSizes = new ArrayList<>();
        pizzaSizes.add("Small");
        pizzaSizes.add("Medium");
        pizzaSizes.add("Large");
        pType2.getItems().addAll(pizzaSizes);
        // Add listener for pizza size changes
        pType2.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Update the base price and recalculate the total price
            basePrice = calculateBasePrice(newValue);
            updatePrice();
        });
        // Set default pizza size to Small
        pType2.setValue("Small");

        // Add listener for the sauce ToggleGroup
        t2.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            // Update price based on the selected sauce
            updatePrice();
        });

        // Set default sauce to "Tomato Sauce"
        sauce1.setSelected(true);
// Add toppings from Topping enum to LV2
        List<String> allToppings = Stream.of(Topping.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        LV2.setItems(FXCollections.observableArrayList(allToppings));
        // Add listener for the add button
        add.setOnAction(event -> {
            // Check if maximum toppings limit is reached
            if (LV.getItems().size() >= 7) {
                showInformationDialog("Toppings Limit", "You can only add up to 7 toppings.");
                return;
            }

            // Add selected topping from LV2 to LV
            String selectedTopping = LV2.getSelectionModel().getSelectedItem();
            if (selectedTopping != null) {
                LV2.getItems().remove(selectedTopping);
                LV.getItems().add(selectedTopping);

                // Update price after adding topping
                updatePrice();
            }
        });

        // Add listener for the remove button
        remove.setOnAction(event -> {
            // Remove selected topping from LV and add it back to LV2
            String selectedTopping = LV.getSelectionModel().getSelectedItem();
            if (selectedTopping != null) {
                LV.getItems().remove(selectedTopping);
                LV2.getItems().add(selectedTopping);

                // Update price after removing topping
                updatePrice();
            }
        });
// Add listeners for ec2 and es3
        ec2.selectedProperty().addListener((observable, oldValue, newValue) -> updatePrice());
        es3.selectedProperty().addListener((observable, oldValue, newValue) -> updatePrice());
        // Add listener for order button
        order2.setOnAction(event -> addToOrder());
    }
    private double calculateBasePrice(String size) {
        switch (size) {
            case "Small":
                return 8.99;
            case "Medium":
                return 10.99;
            case "Large":
                return 12.99;
            default:
                return 8.99; // Default to small
        }
    }

    @FXML
    private void updatePrice() {
        // Calculate the base price based on the selected pizza size (if not initialized)
        if (basePrice == 0.0) {
            String selectedSize = pType2.getValue();
            switch (selectedSize) {
                case "Small":
                    basePrice = 8.99;
                    break;
                case "Medium":
                    basePrice = 10.99;
                    break;
                case "Large":
                    basePrice = 12.99;
                    break;
                default:
                    basePrice = 8.99; // Default to small
            }
        }

        // Calculate additional price for toppings
        int numToppings = LV.getItems().size();
        double additionalToppingsPrice = (numToppings > 3) ? (numToppings - 3) * 1.49 : 0.0;

        // Calculate additional price for extra sauce and extra cheese
        double extraSauceAndCheesePrice = 0.0;
        if (es3.isSelected()) {
            extraSauceAndCheesePrice += 1.0;
        }
        if (ec2.isSelected()) {
            extraSauceAndCheesePrice += 1.0;
        }

        // Update the price TextField
        price2.setText(String.format("%.2f", basePrice + additionalToppingsPrice + extraSauceAndCheesePrice));
    }


    @FXML
    private void addToOrder() {
        // Create Pizza instance using PizzaMaker
        Size pizzaSize = Size.valueOf(pType2.getValue().toUpperCase());
        Sauce selectedSauce = sauce1.isSelected() ? Sauce.TOMATO : Sauce.ALFREDO;
        List<Topping> selectedToppings = LV.getItems().stream()
                .map(Topping::valueOf)
                .collect(Collectors.toList());
        Pizza pizza = PizzaMaker.createPizza("BUILDYOUROWN", pizzaSize, es3.isSelected(), ec2.isSelected(), selectedToppings, selectedSauce);

        // Add the pizza to the order
        currentOrder.addPizza(pizza);

        // Show information dialog
        showInformationDialog("Pizza Added", "The pizza has been added to the order.");
        if (currentOrderController != null) {
            currentOrderController.updateOrderDetails(currentOrder);
        }
        // Clear the form or update UI as needed
        clearForm();
        // Initialize LV2 with all toppings
        initializeLV2();
    }
    // Add a method to initialize LV2 with all toppings
    private void initializeLV2() {
        List<String> allToppings = Stream.of(Topping.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        LV2.setItems(FXCollections.observableArrayList(allToppings));
    }
    // Add a method to clear the form
    private void clearForm() {
        pType2.setValue("Small");
        sauce1.setSelected(true);
        ec2.setSelected(false);
        es3.setSelected(false);
        LV.getItems().clear();
        LV2.getItems().clear();
        price2.clear();
    }

    // Add a method to show an information dialog
    private void showInformationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Add getter and setter for the Order instance
    public Order getOrderInstance() {
        return currentOrder;
    }

    public void setOrderInstance(Order orderInstance) {
        this.currentOrder = orderInstance;
    }
}
