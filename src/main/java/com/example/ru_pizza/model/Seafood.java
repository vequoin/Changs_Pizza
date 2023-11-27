package com.example.ru_pizza.model;

/**
 * The Seafood class represents a pizza with seafood toppings, extending the Pizza class.
 * It includes toppings such as shrimp, crab meats, and squid, with Alfredo sauce as the default sauce.
 * The price of the Seafood pizza depends on its size and optional extras, such as extra cheese and extra sauce.
 * @author Digvijay Singh
 * @author Arun Felix
 */
public class Seafood extends Pizza {

    /**
     * Constructs a Seafood pizza with default toppings (shrimp, crab meats, and squid) and Alfredo sauce.
     * The constructor also sets the default pizza size.
     */
    public Seafood() {
        super();
        addTopping(Topping.SHRIMP);
        addTopping(Topping.CRAB_MEATS);
        addTopping(Topping.SQUID);
        setSauce(Sauce.ALFREDO);
    }

    /**
     * Sets the sauce for the Seafood pizza.
     * Overrides the setSauce method in the Pizza class.
     *
     * @param sauce The sauce to set for the pizza.
     */
    @Override
    public void setSauce(Sauce sauce) {
        super.setSauce(sauce);
    }

    /**
     * Calculates the price of the Seafood pizza based on its size and optional extras.
     * Overrides the price method in the Pizza class.
     *
     * @return The total price of the Seafood pizza.
     */
    @Override
    public double price() {
        double price = 17.99;

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
