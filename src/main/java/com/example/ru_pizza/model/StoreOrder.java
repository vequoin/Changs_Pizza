package com.example.ru_pizza.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The StoreOrder class manages pizza orders for a store. It provides methods to add orders,
 * retrieve all orders, export orders to a file, and generate the next order number.
 * @author Digvijay Singh
 * @author Arun Felix
 */
public class StoreOrder {
    private static int nextOrderNumber = 1;
    private List<Order> orders;

    /**
     * Constructs a StoreOrder object with an empty list of orders.
     */
    public StoreOrder() {
        orders = new ArrayList<>();
    }

    /**
     * Adds an order to the list of orders.
     *
     * @param order The order to be added.
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Retrieves all orders stored in the system.
     *
     * @return A list of all orders.
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Exports orders to a specified file in a human-readable format.
     *
     * @param filename The name of the file to export orders to.
     */
    public void exportToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Order order : orders) {
                writer.write("Order Number: " + order.getOrderId() + "\n");
                writer.write("Order Details: \n");
                for (Pizza pizza : order.getPizzas()) {
                    writer.write(pizza.toString() + "\n");
                }
                writer.write("Total Amount: " + order.getTotalAmount() + "\n");
                writer.write("Sales Tax: " + order.calculateTax() + "\n");
                writer.write("Order Total: " + order.getOrderTotal() + "\n\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates the next order number in sequence.
     *
     * @return The next order number.
     */
    public static int getNextOrderNumber() {
        return nextOrderNumber++;
    }
}
