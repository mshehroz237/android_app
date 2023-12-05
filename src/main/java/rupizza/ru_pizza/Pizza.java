package rupizza.ru_pizza;
import java.util.ArrayList;
/**
 * Abstract class representing a pizza with common properties and methods.
 */
public abstract class Pizza {

    protected ArrayList<Topping> toppings; // Topping is an enum class
    protected Size size; // Size is an enum class
    protected Sauce sauce; // Sauce is an enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Abstract method to calculate the price of the pizza. Implemented in subclasses.
     *
     * @return The price of the pizza.
     */
    public abstract double price(); // polymorphism
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
