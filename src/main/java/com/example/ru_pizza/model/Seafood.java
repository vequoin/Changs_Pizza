package com.example.ru_pizza.model;

import java.util.ArrayList;

public class Seafood extends Pizza{

    public Seafood(){
        super();
        addTopping(Topping.SHRIMP);
        addTopping(Topping.CRAB_MEATS);
        addTopping(Topping.SQUID);
        setSauce(Sauce.ALFREDO);
    }

    @Override
    public void setSauce(Sauce sauce) {
        super.setSauce(sauce);
    }

    @Override
    public double price() {
        return 17.99;
    }
}
