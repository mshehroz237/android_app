package rupizza.ru_pizza;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class SpecialityPizzaController {

    @FXML
    private ChoiceBox<String> pType;
    @FXML
    private RadioButton size;
    @FXML
    private RadioButton size2;
    @FXML
    private RadioButton size3;
    @FXML
    private TextField sauce;
    @FXML
    private CheckBox eCheese;
    @FXML
    private CheckBox eSauce;
    @FXML
    private ListView<String> toppings;
    @FXML
    private TextField price;
    @FXML
    private Button order;
    @FXML
    private ToggleGroup t1;

    // Add a reference to the Order instance
    private Order currentOrder = new Order();
    private CurrentOrderController currentOrderController;
    public void setCurrentOrderController(CurrentOrderController currentOrderController) {
        this.currentOrderController = currentOrderController;
    }
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

    @FXML
    protected void initialize() {
        // Initialize the ChoiceBox with pizza types
        List<String> pizzaTypes = new ArrayList<>();
        pizzaTypes.add("Deluxe");
        pizzaTypes.add("Supreme");
        pizzaTypes.add("Meatzza");
        pizzaTypes.add("Seafood");
        pizzaTypes.add("Pepperoni");
        pType.getItems().addAll(pizzaTypes);
        size.setToggleGroup(t1);
        size2.setToggleGroup(t1);
        size3.setToggleGroup(t1);
        // Set "Deluxe" as the default pizza type
        pType.setValue("Deluxe");

        // Set "Small" as the default size
        size.setSelected(true);

        // Update toppings and sauce based on the default pizza type
        updateToppingsAndSauce("Deluxe");

        // Update price based on the default pizza type and size
        updatePrice("Deluxe");

        // Add listener for the size ToggleGroup
        t1.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Update price based on the selected pizza type and size
                updatePrice(pType.getValue());
            }
        });

        // Set a listener for the pizza type selection
        pType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Update toppings and sauce based on the selected pizza type
            updateToppingsAndSauce(newValue);
            // Update price based on the selected pizza type and size
            updatePrice(newValue);
        });

        // Add listener for eCheese checkbox
        eCheese.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Update price when the checkbox state changes
            updatePrice(pType.getValue());
        });

        // Add listener for eSauce checkbox
        eSauce.selectedProperty().addListener((observable, oldValue, newValue) -> {
            // Update price when the checkbox state changes
            updatePrice(pType.getValue());
        });

        // Add listener to calculate the price when the "Add to Order" button is clicked
        order.setOnAction(event -> addToOrder());
    }

    private void updateToppingsAndSauce(String pizzaType) {
        // Implement logic to update toppings and sauce based on the selected pizza type
        // For example, you can have a switch statement to handle different pizza types

        toppings.getItems().clear(); // Clear existing toppings

        switch (pizzaType) {
            case "Deluxe":
                toppings.getItems().addAll("Sausage", "Pepperoni", "Green_Pepper", "Onion", "Mushroom");
                sauce.setText("Tomato");
                break;
            case "Supreme":
                toppings.getItems().addAll("Sausage", "Pepperoni", "Ham", "Green_Pepper", "Onion", "Black_Olive", "Mushroom");
                sauce.setText("Tomato");
                break;
            case "Meatzza":
                toppings.getItems().addAll("Sausage", "Pepperoni", "Beef", "Ham");
                sauce.setText("Tomato");
                break;
            case "Seafood":
                toppings.getItems().addAll("Shrimp", "Squid", "Crab_Meat");
                sauce.setText("Alfredo");
                break;
            case "Pepperoni":
                toppings.getItems().addAll("Pepperoni");
                sauce.setText("Tomato");
                break;
            default:
                // Handle default case or other pizza types
                break;
        }
    }

    private void updatePrice(String pizzaType) {
        // Check if a size radio button is selected
        RadioButton selectedRadioButton = (RadioButton) t1.getSelectedToggle();
        if (selectedRadioButton == null) {
            // Handle the case where no size is selected
            return;
        }

        String sizeB = selectedRadioButton.getText();
        Size pizzaSize;

        switch (sizeB) {
            case "Small":
                pizzaSize = Size.SMALL;
                break;
            case "Medium":
                pizzaSize = Size.MEDIUM;
                break;
            case "Large":
                pizzaSize = Size.LARGE;
                break;
            default:
                pizzaSize = Size.SMALL; // Default to small
        }

        // Create pizza object based on the selected type
        Pizza pizza = PizzaMaker.createPizza(pizzaType, pizzaSize, eSauce.isSelected(), eCheese.isSelected(), getSelectedToppings(), getSauce());

        // Update the price TextField
        price.setText(String.format("%.2f", pizza.price()));
    }

    private List<Topping> getSelectedToppings() {
        List<Topping> selectedToppings = new ArrayList<>();
        for (String topping : toppings.getItems()) {
            selectedToppings.add(Topping.valueOf(topping.toUpperCase()));
        }
        return selectedToppings;
    }

    private Sauce getSauce() {
        String sauceText = sauce.getText().toUpperCase();
        try {
            return Sauce.valueOf(sauceText);
        } catch (IllegalArgumentException e) {
            return Sauce.TOMATO;  // Adjust to your specific needs
        }
    }


    @FXML
    private void addToOrder() {
        // Check if any toggle is selected in the toggle group
        if (t1.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) t1.getSelectedToggle();
            String sizeB = selectedRadioButton.getText();
            Size pizzaSize;

            switch (sizeB) {
                case "Small":
                    pizzaSize = Size.SMALL;
                    break;
                case "Medium":
                    pizzaSize = Size.MEDIUM;
                    break;
                case "Large":
                    pizzaSize = Size.LARGE;
                    break;
                default:
                    pizzaSize = Size.SMALL; // Default to small
            }

            // Create the pizza based on the selected type
            Pizza pizza = PizzaMaker.createPizza(pType.getValue(), pizzaSize, eSauce.isSelected(), eCheese.isSelected(), getSelectedToppings(), getSauce());

            // Add the pizza to the order
            currentOrder.addPizza(pizza);

            // Show information dialog
            showInformationDialog("Pizza Added", "The pizza has been added to the order.");
            if (currentOrderController != null) {
                currentOrderController.updateOrderDetails(currentOrder);
            }
            // Set "Deluxe" as the default pizza type
            pType.setValue("Deluxe");

            // Set "Small" as the default size
            size.setSelected(true);

            // Update toppings and sauce based on the default pizza type
            updateToppingsAndSauce("Deluxe");

            // Update price based on the default pizza type and size
            updatePrice("Deluxe");
        } else {
            // Handle the case where no size is selected
            showInformationDialog("Error", "Please select a pizza size.");
        }
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
