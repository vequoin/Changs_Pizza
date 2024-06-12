package com.example.ru_pizza.model;

/**
 * Class for the Deluxe pizza type
 * @author Digvijay Singh
 * @author Arun Felix
 */
public class Deluxe extends Pizza{

    /**
     * deluxe constructor.
     */
    public Deluxe(){
        super();
        addTopping(Topping.PEPPERONI);
        addTopping(Topping.SAUSAGE);
        addTopping(Topping.MUSHROOMS);
        addTopping(Topping.GREEN_PEPPERS);
        addTopping(Topping.ONIONS);
        setSauce(Sauce.TOMATO);
    }

    /**
     * calculates price based on size and user preferences.
     * @return price of pizza.
     */
    @Override
    public double price() {
        double price = 14.99;
        switch (size){
            case SMALL -> price += 0.0;
            case MEDIUM -> price += 2.00;
            case LARGE -> price += 4.00;
        }
        if(isExtraCheese()){
            price += 1;
        }
        if(isExtraSauce()){
            price += 1;
        }
        return price;
    }
}
