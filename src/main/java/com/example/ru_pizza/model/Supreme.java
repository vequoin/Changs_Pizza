package com.example.ru_pizza.model;

public class Supreme extends Pizza{

    public Supreme(){
        super();
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.GREEN_PEPPERS);
        addTopping(Topping.ONIONS);
        addTopping(Topping.BLACK_OLIVES);
        addTopping(Topping.MUSHROOMS);
        addTopping(Topping.HAM);
        setSauce(Sauce.TOMATO);
    }


    @Override
    public void setSauce(Sauce sauce) {
        super.setSauce(sauce);
    }

    @Override
    public double price() {
        return 15.99;
    }
}
