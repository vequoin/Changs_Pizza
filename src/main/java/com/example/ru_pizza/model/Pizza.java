package com.example.ru_pizza.model;

import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Topping> toppings; // Topping is an enum class
    protected Size size; // Size is an enum class
    protected Sauce sauce; // Sauce is an enum class
    protected boolean extraSauce;
    protected boolean extraCheese;

    public Pizza() {
        toppings = new ArrayList<>();
    }

    public abstract double price(); // Polymorphism

    // Getters and Setters
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Sauce getSauce() {
        return sauce;
    }

    public void setSauce(Sauce sauce) {
        this.sauce = sauce;
    }

    public boolean isExtraSauce() {
        return extraSauce;
    }

    public void setExtraSauce(boolean extraSauce) {
        this.extraSauce = extraSauce;
    }

    public boolean isExtraCheese() {
        return extraCheese;
    }

    public void setExtraCheese(boolean extraCheese) {
        this.extraCheese = extraCheese;
    }
}
