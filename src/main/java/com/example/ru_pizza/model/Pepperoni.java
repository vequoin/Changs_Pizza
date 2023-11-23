package com.example.ru_pizza.model;

public class Pepperoni extends Pizza{

    public Pepperoni(){
        super();
        addTopping(Topping.PEPPERONI);
        setSauce(Sauce.TOMATO);
    }


    @Override
    public double price() {
        return 10.99;
    }
}
