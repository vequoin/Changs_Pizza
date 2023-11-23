package com.example.ru_pizza.model;


public class Meatzza extends Pizza{

    public Meatzza(){
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.HAM);
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.BEEF);
        setSauce(Sauce.TOMATO);
    }
    @Override
    public double price() {
        return 16.99;
    }
}
