package com.example.ru_pizza;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages pizza toppings for different pizza types.
 * @author Digvijay Singh
 * @author Arun Felix
 */
public class PizzaToppingsManager {

    private Map<String, List<String>> pizzaToppingsMap;

    /**
     * Constructs a new PizzaToppingsManager and initializes the toppings map.
     */
    public PizzaToppingsManager() {
        pizzaToppingsMap = new HashMap<>();
        initializeToppingsMap();
    }

    /**
     * Initializes the toppings map with predefined toppings for various pizza types.
     */
    private void initializeToppingsMap() {
        pizzaToppingsMap.put("Deluxe", List.of("Pepperoni", "Mushrooms", "Green Peppers", "Onions", "Sausage"));
        pizzaToppingsMap.put("Meatzza", List.of("Ham", "Sausage", "Pepperoni", "Beef"));
        pizzaToppingsMap.put("Pepperoni", List.of("Pepperoni"));
        pizzaToppingsMap.put("Supreme", List.of("Sausage", "Pepperoni", "Green Onions", "Onions", "Black Olives", "Mushrooms", "Ham"));
        pizzaToppingsMap.put("Seafood", List.of("Crab Meat", "Squid", "Shrimp"));
    }

    /**
     * Retrieves the list of toppings for a specific pizza type.
     *
     * @param pizzaType The type of pizza.
     * @return The list of toppings for the specified pizza type. An empty list if the pizza type is not found.
     */
    public List<String> getToppingsForPizza(String pizzaType) {
        return pizzaToppingsMap.getOrDefault(pizzaType, List.of());
    }
}
