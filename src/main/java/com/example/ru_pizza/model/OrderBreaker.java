package com.example.ru_pizza.model;

/**
 * This breaker allows us to access single objects across classes
 * @author Arun Felix
 * @author Digvijay Singh
 */
public class OrderBreaker {
    private static Order order;
    private static StoreOrder storeOrder;

    /**
     * private constructor so object cannot be created
     */
    private OrderBreaker(){

    }

    /**
     * this will get the instance of the order saved to OrderBreaker class
     * @return Order object
     */
    public static Order getOrder(){
        if(order == null){
            order = new Order();
        }
        return order;
    }

    /**
     * erases old order and creates new one
     * @return now order object
     */
    public static Order createNewOrder(){
        if(order != null){
            order = new Order();
        }
        return order;
    }

    /**
     * gets the storeorder object saved in class
     * @return StoreOrder object.
     */
    public static StoreOrder getStoreOrder(){
        if(storeOrder == null){
            storeOrder = new StoreOrder();
        }
        return storeOrder;
    }

    /**
     * replaces old StoreOrder object with now StoreOrder object.
     * @return
     */
    public static StoreOrder createNewStoreOrder(){
        if(storeOrder != null){
            storeOrder = new StoreOrder();
        }
        return storeOrder;
    }
}
