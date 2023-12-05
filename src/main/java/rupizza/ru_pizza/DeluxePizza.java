package rupizza.ru_pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Deluxe pizza class representing a pizza with sausage, pepperoni, green pepper, onion, mushroom toppings, and tomato sauce.
 */
public class DeluxePizza extends Pizza {

    /**
     * Constructs a DeluxePizza object with the specified size, extra sauce, and extra cheese options.
     *
     * @param size       The size of the pizza (SMALL, MEDIUM, LARGE).
     * @param extraSauce  Indicates whether extra sauce is selected.
     * @param extraCheese Indicates whether extra cheese is selected.
     */
    public DeluxePizza(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = new ArrayList<>(List.of(Topping.SAUSAGE, Topping.PEPPERONI, Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM));
        this.sauce = Sauce.TOMATO;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
     * Calculates the price of the Deluxe pizza based on its size, selected options, and toppings.
     *
     * @return The total price of the Deluxe pizza.
     */
    @Override
    public double price() {
        double basePrice = switch (size) {
            case MEDIUM -> 14.99 + 2.0;
            case LARGE -> 14.99 + 4.0;
            default -> 14.99; // Small
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

