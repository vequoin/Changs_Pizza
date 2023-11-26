package com.example.ru_pizza.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextOrderId = 1; // Static counter for unique order IDs
    private final int orderId;
    private final List<Pizza> pizzas;
    private double totalAmount; // Total amount before tax

    public Order() {
        this.orderId = nextOrderId++;
        this.pizzas = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        totalAmount += pizza.price();
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Pizza> getPizzas() {
        return this.pizzas;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double calculateTax() {
        final double TAX_RATE = 0.06625;
        return totalAmount * TAX_RATE;
    }

    public double getOrderTotal() {
        return totalAmount + calculateTax();
    }


}
