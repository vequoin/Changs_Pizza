package com.example.ru_pizza.model;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * abstract class of the Pizza object.
 * @author Digvijay Singh
 * @author Arun Felix
 */
public abstract class Pizza {
    protected ArrayList<Topping> toppings; // Topping is an enum class
    protected Size size; // Size is an enum class
    protected Sauce sauce; // Sauce is an enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
     * Pizza Constructor initializing the ArrayList.
     */
    public Pizza() {
        this.toppings = new ArrayList<>();
    }

    /**
     * Abstract method to get price of pizza.
     * @return Price of pizza
     */
    public abstract double price(); // Polymorphism

    /**
     * getter method for toppings
     * @return toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * adds toppings
     * @param topping you want.
     */
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    /**
     * gets the size of the pizza
     * @return size of pizza
     */
    public Size getSize() {
        return size;
    }

    /**
     * sets size of pizza
     * @param size of pizza
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * gets the sauce on pizza.
     * @return sauce on pizza
     */
    public Sauce getSauce() {
        return sauce;
    }

    /**
     * sets the sauce on pizza.
     * @param sauce on pizza
     */
    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    /**
     * checks if pizza has extra sauce
     * @return true if pizza has extra sauce.
     */
    public boolean isExtraSauce() {
        return extraSauce;
    }

    /**
     * sets the extrasauce option on the object
     * @param extraSauce option selectod by user.
     */
    public void setExtraSauce(boolean extraSauce) {
        this.extraSauce = extraSauce;
    }

    /**
     * checks if there's extra cheese
     * @return true if extra cheese was selected
     */
    public boolean isExtraCheese() {
        return extraCheese;
    }

    /**
     * sets if extra cheese was selected by user
     * @param extraCheese by user in the pizza window
     */
    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }

    /**
     * converts pizza object to string.
     * @return string of the pizza object.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Pizza Size: ").append(size).append("\n");
        builder.append("Sauce: ").append(sauce).append("\n");

        if (!toppings.isEmpty()) {
            builder.append("Toppings: ");
            for (Topping topping : toppings) {
                builder.append(topping).append(", ");
            }
            // Remove the last comma and space
            builder.setLength(builder.length() - 2);
            builder.append("\n");
        } else {
            builder.append("Toppings: None\n");
        }

        if (extraSauce) {
            builder.append("Extra Sauce: Yes\n");
        } else {
            builder.append("Extra Sauce: No\n");
        }

        if (extraCheese) {
            builder.append("Extra Cheese: Yes\n");
        } else {
            builder.append("Extra Cheese: No\n");
        }

        builder.append("Price: $").append(String.format("%.2f", price()));

        return builder.toString();
    }

}
