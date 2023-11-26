package com.example.ru_pizza.model;

import java.util.ArrayList;
import java.util.List;

public class StoreOrder {
    private final List<Order> orders;

    public StoreOrder() {
        orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }


}
