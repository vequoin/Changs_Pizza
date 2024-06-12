package com.example.ru_pizza.model;

/**
 * The PizzaMaker class is responsible for creating instances of Pizza subclasses based on the provided pizza type.
 * It provides a static method {@code createPizza} that takes a pizza type as a parameter and returns the corresponding
 * Pizza object.
 * @author Digvijay Singh
 * @author Arun Felix
 */
public class PizzaMaker {

    /**
     * Creates a Pizza object based on the provided pizza type.
     *
     * @param pizzaType The type of pizza to create.
     * @return A Pizza object corresponding to the specified pizza type.
     * @throws IllegalArgumentException If the provided pizza type is unknown.
     */
    public static Pizza createPizza(String pizzaType) {
        return switch (pizzaType) {
            case "Deluxe" -> new Deluxe();
            case "Supreme" -> new Supreme();
            case "Meatzza" -> new Meatzza();
            case "Pepperoni" -> new Pepperoni();
            case "Seafood" -> new Seafood();
            case "BuildYourOwn" -> new BuildYourOwn();
            default -> throw new IllegalArgumentException("Unknown pizza type: " + pizzaType);
        };
    }
}
