package com.example.ru_pizza.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains all the orders placed by user.
 */
public class Order {
    private static int nextOrderId = 1; // Static counter for unique order IDs
    private final int orderId;
    private final List<Pizza> pizzas;
    private double totalAmount; // Total amount before tax

    /**
     * Constructor for order object.
     */
    public Order() {
        this.orderId = nextOrderId++;
        this.pizzas = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    /**
     * adds a pizza to the orderslist
     * @param pizza
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        totalAmount += pizza.price();
    }

    /**
     * checks if the orderlist is empty.
     * @return
     */
    public boolean isEmpty(){
        return pizzas.isEmpty();
    }

    /**
     * gets the orderID
     * @return
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * returns the list order is holding pizzas with.
     * @return list of pizzas
     */
    public List<Pizza> getPizzas() {
        return this.pizzas;
    }

    /**
     * returns the total cost of order
     * @return cost of order
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * calculates the tax
     * @return taxrate
     */
    public double calculateTax() {
        final double TAX_RATE = 0.06625;
        return totalAmount * TAX_RATE;
    }

    /**
     * gets the total cost of order
     * @return total cost of order with tax
     */
    public double getOrderTotal() {
        return totalAmount + calculateTax();
    }

    /**
     * method making string representation of the order.
     * @return String of the order
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Pizzas in Order: \n");

        for (Pizza pizza : pizzas) {
            sb.append(pizza).append("\n"); 
        }

        sb.append("Total Amount: ").append(String.format("%.2f", totalAmount)).append("\n");
        sb.append("Sales Tax: ").append(String.format("%.2f", calculateTax())).append("\n");
        sb.append("Order Total (with Tax): ").append(String.format("%.2f", getOrderTotal())).append("\n");

        return sb.toString();
    }


}
