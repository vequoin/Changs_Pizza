package com.example.ru_pizza.model;

/**
 * Class for Pepperoni pizza.
 * @author Digvijay Singh
 * @author Arun Felix
 */
public class Pepperoni extends Pizza{
    /**
     * constructor to create Pepperoni object.
     */
    public Pepperoni(){
        super();
        addTopping(Topping.PEPPERONI);
        setSauce(Sauce.TOMATO);
    }

    /**
     * returns price based on size and checkboxes set by user.
     * @return price of pizza
     */
    @Override
    public double price() {

        double price = 10.99;
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
