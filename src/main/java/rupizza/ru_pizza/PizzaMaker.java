package rupizza.ru_pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * PizzaMaker class responsible for creating instances of Pizza based on the chosen pizza type.
 * Uses the Factory Method design pattern.
 */
public class PizzaMaker {

    // Create an instance of subclasses based on the chosen pizza type
    public static Pizza createPizza(String pizzaType, Size size, boolean extraSauce, boolean extraCheese, List<Topping> toppings, Sauce sauce) {
        switch (pizzaType.toUpperCase()) {
            case "DELUXE":
                return new DeluxePizza(size, extraSauce, extraCheese);
            case "SUPREME":
                return new SupremePizza(size, extraSauce, extraCheese);
            case "MEATZZA":
                return new MeatzzaPizza(size, extraSauce, extraCheese);
            case "SEAFOOD":
                return new SeafoodPizza(size, extraSauce, extraCheese);
            case "PEPPERONI":
                return new PepperoniPizza(size, extraSauce, extraCheese);
            case "BUILDYOUROWN":
                return new BuildYourOwn(size, extraSauce, extraCheese, new ArrayList<>(toppings), sauce);
            default:
                throw new IllegalArgumentException("Invalid pizza type: " + pizzaType);
        }
    }
}
