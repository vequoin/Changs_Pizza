package com.example.ru_pizza.model;

public class Deluxe extends Pizza{


    public Deluxe(){
        super();
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.MUSHROOMS);
        addTopping(Topping.GREEN_PEPPERS);
        addTopping(Topping.ONIONS);
        setSauce(Sauce.TOMATO);
    }

    @Override
    public double price() {
        return 14.99;
    }
}
