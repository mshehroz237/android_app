package rupizza.ru_pizza;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class BuildYourOwnTest {

    @Test
    void price() {
        // Test case 1: Small pizza with no toppings, no extra cheese, and no extra sauce
        BuildYourOwn pizza1 = new BuildYourOwn(Size.SMALL, false, false, new ArrayList<>(), Sauce.TOMATO);
        Assertions.assertEquals(8.99, pizza1.price(), 0.01);

        // Test case 2: Medium pizza with 7 toppings (maximum allowed), extra cheese, and extra sauce
        BuildYourOwn pizza2 = new BuildYourOwn(Size.MEDIUM, true, true,
                new ArrayList<>(List.of(Topping.SAUSAGE, Topping.ONION, Topping.MUSHROOM, Topping.PEPPERONI,
                        Topping.GREEN_PEPPER, Topping.BLACK_OLIVE, Topping.HAM)), Sauce.ALFREDO);
        Assertions.assertEquals(20.99, pizza2.price(), 0.01);

        // Test case 3: Large pizza with extra sauce only
        BuildYourOwn pizza3 = new BuildYourOwn(Size.LARGE, false, false, new ArrayList<>(), Sauce.ALFREDO);
        Assertions.assertEquals(12.99, pizza3.price(), 0.01);

        // Test case 4: Extra large pizza with extra cheese only
        BuildYourOwn pizza4 = new BuildYourOwn(Size.LARGE, false, true, new ArrayList<>(), Sauce.TOMATO);
        Assertions.assertEquals(12.99, pizza4.price(), 0.01);

        // Test case 5: Medium pizza with 4 toppings, no extra cheese, and extra sauce
        BuildYourOwn pizza5 = new BuildYourOwn(Size.MEDIUM, true, false,
                new ArrayList<>(List.of(Topping.SAUSAGE, Topping.ONION, Topping.MUSHROOM, Topping.PEPPERONI)), Sauce.ALFREDO);
        Assertions.assertEquals(14.99, pizza5.price(), 0.01);

        // Test case 6: Negative test - Small pizza with 8 toppings (one more than the allowed maximum)
        BuildYourOwn pizza6 = new BuildYourOwn(Size.SMALL, false, false,
                new ArrayList<>(List.of(Topping.SAUSAGE, Topping.ONION, Topping.MUSHROOM, Topping.PEPPERONI,
                        Topping.GREEN_PEPPER, Topping.BLACK_OLIVE, Topping.HAM)), Sauce.TOMATO);
        Assertions.assertEquals(8.99, pizza6.price(), 0.01);
    }
}
