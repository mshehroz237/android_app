package rupizza.ru_pizza;

import java.util.ArrayList;

/**
 * BuildYourOwnPizza class representing a customizable pizza with selected toppings and sauce.
 */
public class BuildYourOwn extends Pizza {

    private static final int MAX_TOPPINGS = 7;
    private static final double BASE_PRICE = 8.99;

    /**
     * Constructs a BuildYourOwnPizza object with the specified size, extra sauce, extra cheese, and selected toppings.
     *
     * @param size        The size of the pizza (SMALL, MEDIUM, LARGE).
     * @param extraSauce  Indicates whether extra sauce is selected.
     * @param extraCheese Indicates whether extra cheese is selected.
     * @param toppings    The list of selected toppings for the pizza.
     * @param sauce       The sauce for the pizza (TOMATO or ALFREDO).
     */
    public BuildYourOwn(Size size, boolean extraSauce, boolean extraCheese, ArrayList<Topping> toppings, Sauce sauce) {
        this.size = size;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;

        if (toppings.size() > MAX_TOPPINGS) {
            throw new IllegalArgumentException("Exceeded the maximum number of toppings for Build Your Own Pizza.");
        }

        this.toppings = new ArrayList<>(toppings);
        this.sauce = sauce;
    }

    /**
     * Calculates the price of the BuildYourOwn pizza based on its size, selected options, and toppings.
     *
     * @return The total price of the BuildYourOwn pizza.
     */
    @Override
    public double price() {
        double basePrice = switch (size) {
            case MEDIUM -> BASE_PRICE + 2.0;
            case LARGE -> BASE_PRICE + 4.0;
            default -> BASE_PRICE; // Small
        };

        return basePrice + (extraSauce ? 1.0 : 0.0) + (extraCheese ? 1.0 : 0.0);
    }
    /**
     * Returns a string representation of the pizza, including type, toppings, size, sauce, and additional options.
     *
     * @return The string representation of the pizza.
     */
    @Override
    public String toString() {
        StringBuilder pizzaDetails = new StringBuilder();
        pizzaDetails.append("[").append(getClass().getSimpleName()).append("] ");
        pizzaDetails.append(toppingsToString()).append(", ");
        pizzaDetails.append(size).append(", ");
        pizzaDetails.append(sauce).append(extraSauce ? ", extra sauce" : "").append(extraCheese ? ", extra cheese" : "");
        pizzaDetails.append(", $").append(String.format("%.2f", price()));
        return pizzaDetails.toString();
    }

    /**
     * Returns a string representation of the toppings list.
     *
     * @return The string representation of toppings.
     */
    private String toppingsToString() {
        StringBuilder toppingsString = new StringBuilder();
        for (Topping topping : toppings) {
            toppingsString.append(topping).append(", ");
        }
        // Remove the trailing comma and space
        if (toppingsString.length() > 0) {
            toppingsString.setLength(toppingsString.length() - 2);
        }
        return toppingsString.toString();
    }

}
