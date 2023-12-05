package rupizza.ru_pizza;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * StoreOrders class representing all the store orders.
 */
public class StoreOrders {

    private static final double NJ_TAX_RATE = 0.06625;
    private final List<Order> orders;

    /**
     * Constructs a StoreOrders object to keep track of store orders.
     */
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Adds an order to the store orders.
     *
     * @param order The order to be added.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Cancels an order by removing it from the store orders.
     *
     * @param orderNumber The order number of the order to be canceled.
     * @return True if the order was found and canceled; false otherwise.
     */
    public boolean cancelOrder(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                orders.remove(order);
                return true;
            }
        }
        return false;
    }

    /**
     * Exports the store orders to a text file.
     *
     * @param filename The name of the text file to export to.
     */
    public void export(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Order order : orders) {
                writer.println(order.toString()); // Use the Order's toString method
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the total number of orders in the store.
     *
     * @return The total number of orders.
     */
    public int getTotalOrders() {
        return orders.size();
    }

    /**
     * Retrieves the details of a specific order.
     *
     * @param orderNumber The order number of the desired order.
     * @return The details of the specified order.
     */
    public String getOrderDetails(int orderNumber) {
        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                return order.toString(); // Use the Order's toString method
            }
        }
        return "Order not found";
    }

    /**
     * Calculates and retrieves the total sales tax for all orders.
     *
     * @return The total sales tax amount.
     */
    public double calculateTotalSalesTax() {
        double totalSalesTax = 0;
        for (Order order : orders) {
            totalSalesTax += order.calculateTotalAmount() * NJ_TAX_RATE;
        }
        return totalSalesTax;
    }

    /**
     * Calculates and retrieves the total revenue, including sales tax, for all orders.
     *
     * @return The total revenue amount.
     */
    public double calculateTotalRevenue() {
        double totalRevenue = 0;
        for (Order order : orders) {
            totalRevenue += order.calculateTotalAmount() * (1 + NJ_TAX_RATE);
        }
        return totalRevenue;
    }
}
