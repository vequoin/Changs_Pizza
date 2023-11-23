package com.example.ru_pizza;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PizzaToppingsManager {
    private Map<String, List<String>> pizzaToppingsMap;

    public PizzaToppingsManager() {
        pizzaToppingsMap = new HashMap<>();
        initializeToppingsMap();
    }

    private void initializeToppingsMap() {
        pizzaToppingsMap.put("Deluxe", List.of("Pepperoni", "Mushrooms", "Green Peppers", "Onions", "Sausage"));
        pizzaToppingsMap.put("Meatzza", List.of("Ham", "Sausage", "Pepperoni", "Beef"));
        pizzaToppingsMap.put("Pepperoni", List.of("Pepperoni"));
        pizzaToppingsMap.put("Supreme", List.of("Sausage", "Pepperoni", "Green Onions", "Onions", "Black Olives", "Mushrooms", "Ham"));
        pizzaToppingsMap.put("Seafood", List.of("Crab Meat", "Squid", "Shrimp"));
    }

    public List<String> getToppingsForPizza(String pizzaType) {
        return pizzaToppingsMap.getOrDefault(pizzaType, List.of());
    }
}
