package com.example.ru_pizza.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildYourOwnTest {
    /**
     * The 3 methods below test the price given the size differences.
     */
    /**
     * tests price of 3 toppings for a small pizza.
     */
    @Test
    void threetoppingssmall() {
        BuildYourOwn buildYourOwn = new BuildYourOwn();
        buildYourOwn.toppings.add(Topping.PINEAPPLE);
        buildYourOwn.toppings.add(Topping.SAUSAGE);
        buildYourOwn.toppings.add(Topping.HAM);
        buildYourOwn.size = Size.SMALL;
        //testing toppings with sizes
        assertEquals(8.99,buildYourOwn.price());
        buildYourOwn.size = Size.MEDIUM;
        assertEquals(10.99,buildYourOwn.price());
    }
    /**
     * testing price of medium size with same toppings as threetoppingssmall.
     */
    @Test
    void threetoppingsmedium() {
        BuildYourOwn buildYourOwn = new BuildYourOwn();
        buildYourOwn.toppings.add(Topping.PINEAPPLE);
        buildYourOwn.toppings.add(Topping.SAUSAGE);
        buildYourOwn.toppings.add(Topping.HAM);
        buildYourOwn.size = Size.MEDIUM;
        assertEquals(10.99,buildYourOwn.price());
    }

    /**
     * testing price of large size with same toppings.
     */
    @Test
    void threetoppingslarge() {
        BuildYourOwn buildYourOwn = new BuildYourOwn();
        buildYourOwn.toppings.add(Topping.PINEAPPLE);
        buildYourOwn.toppings.add(Topping.SAUSAGE);
        buildYourOwn.toppings.add(Topping.HAM);
        buildYourOwn.size = Size.LARGE;
        assertEquals(12.99,buildYourOwn.price());
    }
    /**
     * testing one extra topping.
     *
     */
    @Test
    void oneextratoppinglarge() {
        BuildYourOwn buildYourOwn = new BuildYourOwn();
        buildYourOwn.toppings.add(Topping.PINEAPPLE);
        buildYourOwn.toppings.add(Topping.SAUSAGE);
        buildYourOwn.toppings.add(Topping.HAM);
        buildYourOwn.toppings.add(Topping.PEPPERONI);
        buildYourOwn.size = Size.LARGE;
        assertEquals((12.99 + 1.49),buildYourOwn.price());
    }

    /**
     * testing two extra toppings
     */
    @Test
    void maxextratoppinglarge() {
        BuildYourOwn buildYourOwn = new BuildYourOwn();
        buildYourOwn.toppings.add(Topping.PINEAPPLE);
        buildYourOwn.toppings.add(Topping.SAUSAGE);
        buildYourOwn.toppings.add(Topping.HAM);
        buildYourOwn.toppings.add(Topping.PEPPERONI);
        buildYourOwn.toppings.add(Topping.MUSHROOMS);
        buildYourOwn.toppings.add(Topping.ONIONS);
        buildYourOwn.toppings.add(Topping.BEEF);
        buildYourOwn.size = Size.LARGE;
        assertEquals((12.99 + (4 * 1.49)),buildYourOwn.price());
    }
    @Test
    void extrasauce(){
        BuildYourOwn buildYourOwn = new BuildYourOwn();
        buildYourOwn.toppings.add(Topping.PINEAPPLE);
        buildYourOwn.toppings.add(Topping.SAUSAGE);
        buildYourOwn.toppings.add(Topping.HAM);
        buildYourOwn.toppings.add(Topping.PEPPERONI);
        buildYourOwn.toppings.add(Topping.MUSHROOMS);
        buildYourOwn.toppings.add(Topping.ONIONS);
        buildYourOwn.toppings.add(Topping.BEEF);
        buildYourOwn.size = Size.LARGE;
        buildYourOwn.setExtraSauce(true);
        assertEquals(19.95,buildYourOwn.price());
    }
    @Test
    void extracheese(){
        BuildYourOwn buildYourOwn = new BuildYourOwn();
        buildYourOwn.toppings.add(Topping.PINEAPPLE);
        buildYourOwn.toppings.add(Topping.SAUSAGE);
        buildYourOwn.toppings.add(Topping.HAM);
        buildYourOwn.toppings.add(Topping.PEPPERONI);
        buildYourOwn.toppings.add(Topping.MUSHROOMS);
        buildYourOwn.toppings.add(Topping.ONIONS);
        buildYourOwn.toppings.add(Topping.BEEF);
        buildYourOwn.size = Size.LARGE;
        buildYourOwn.setExtraCheese(true);
        assertEquals(19.95,buildYourOwn.price());
    }

    @Test
    void extracheesensauce(){
        BuildYourOwn buildYourOwn = new BuildYourOwn();
        buildYourOwn.toppings.add(Topping.PINEAPPLE);
        buildYourOwn.toppings.add(Topping.SAUSAGE);
        buildYourOwn.toppings.add(Topping.HAM);
        buildYourOwn.toppings.add(Topping.PEPPERONI);
        buildYourOwn.toppings.add(Topping.MUSHROOMS);
        buildYourOwn.toppings.add(Topping.ONIONS);
        buildYourOwn.toppings.add(Topping.BEEF);
        buildYourOwn.size = Size.LARGE;
        buildYourOwn.setExtraCheese(true);
        buildYourOwn.setExtraSauce(true);
        assertEquals(20.95,buildYourOwn.price());
    }


}