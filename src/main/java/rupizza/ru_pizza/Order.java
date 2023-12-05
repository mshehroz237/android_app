package rupizza.ru_pizza;

import java.util.ArrayList;


/**
 * Order class representing an order with a unique order number and a list of pizzas.
 */
public class Order {

    private static int nextOrderNumber = 1;
    private static final double TAX_RATE = 0.06625; // 6.625%

    private int orderNumber;
    private ArrayList<Pizza> pizzas;
    /**
     * Constructs an Order with a unique order number and a list of pizzas.
     *
     *
     */
    public Order() {
        this.orderNumber = nextOrderNumber++;
        this.pizzas = new ArrayList<>();
    }
    /**
     * Gets the order number.
     *
     * @return The order number.
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Gets the list of pizzas in the order.
     *
     * @return The list of pizzas.
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Adds a pizza to the order.
     *
     * @param pizza The pizza to add.
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a pizza from the order.
     *
     * @param pizza The pizza to remove.
     */
    public void removePizza(Pizza pizza) {
        pizzas.remove(pizza);
    }

    /**
     * Calculates the total cost of all pizzas in the order.
     *
     * @return The total order amount.
     */
    public double calculateOrderTotal() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.price();
        }
        return total;
    }

    /**
     * Calculates the sales tax amount based on the total order amount.
     *
     * @return The sales tax amount.
     */
    public double calculateSalesTax(double tax) {
        return calculateOrderTotal() * tax;
    }

    /**
     * Calculates the total order amount including sales tax.
     *
     * @return The total order amount with sales tax.
     */
    public double calculateOrderWithTax(double tax)
    {
        return calculateOrderTotal() + calculateSalesTax(tax);
    }
    /**
     * Returns a string representation of the order, including order number, details of each pizza, and total amount.
     *
     * @return The string representation of the order.
     */
    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Order #").append(orderNumber).append("\n");

        for (Pizza pizza : pizzas) {
            orderDetails.append(pizza.toString()).append("\n");
        }

        orderDetails.append("Total Amount: $").append(String.format("%.2f", calculateTotalAmount()));

        return orderDetails.toString();
    }
    /**
     * Calculates the total amount of the order, including the price of each pizza.
     *
     * @return The total amount of the order.
     */
    public double calculateTotalAmount() {
        double totalAmount = 0;
        for (Pizza pizza : pizzas) {
            totalAmount += pizza.price();
        }
        return totalAmount;
    }

    /**
     * Returns a list of strings representing pizzas in the order.
     *
     * @return The list of strings representing pizzas.
     */
    public ArrayList<String> getPizzasAsString() {
        ArrayList<String> pizzasAsString = new ArrayList<>();

        for (Pizza pizza : pizzas) {
            pizzasAsString.add(pizza.toString());
        }

        return pizzasAsString;
    }


}
