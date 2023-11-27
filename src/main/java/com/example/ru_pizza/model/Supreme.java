package com.example.ru_pizza.model;

/**
 * The Supreme class represents a pizza with supreme toppings, extending the Pizza class.
 * It includes toppings such as sausage, pepperoni, green peppers, onions, black olives, mushrooms, and ham,
 * with tomato sauce as the default sauce.
 * The price of the Supreme pizza depends on its size and optional extras, such as extra cheese and extra sauce.
 * @author Digvijay Singh
 * @author Arun Felix
 */
public class Supreme extends Pizza {

    /**
     * Constructs a Supreme pizza with default toppings and tomato sauce.
     * The constructor also sets the default pizza size.
     */
    public Supreme() {
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

    /**
     * Sets the sauce for the Supreme pizza.
     * Overrides the setSauce method in the Pizza class.
     *
     * @param sauce The sauce to set for the pizza.
     */
    @Override
    public void setSauce(Sauce sauce) {
        super.setSauce(sauce);
    }

    /**
     * Calculates the price of the Supreme pizza based on its size and optional extras.
     * Overrides the price method in the Pizza class.
     *
     * @return The total price of the Supreme pizza.
     */
    @Override
    public double price() {
        double price = 15.99;

        switch (size) {
            case SMALL -> price += 0.0;
            case MEDIUM -> price += 2.00;
            case LARGE -> price += 4.00;
        }

        if (isExtraCheese()) {
            price += 1;
        }
        if (isExtraSauce()) {
            price += 1;
        }

        return price;
    }
}
