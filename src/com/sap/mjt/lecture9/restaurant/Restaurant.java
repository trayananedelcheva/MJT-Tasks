package com.sap.mjt.lecture9.restaurant;

public interface Restaurant {
    /**
     * Adds an order.
     */
    void submitOrder(Order order);

    /**
     * Returns the next order to be cooked
     * and removes it from the pending orders.
     */
    Order nextOrder();

    /**
     * Returns total number of submitted orders.
     */
    int getOrdersCount();

    /**
     * Returns the restaurant's chefs.
     */
    Chef[] getChefs();

    /**
     * Prepares the restaurant for closing. When this method is called,
     * the chefs complete any pending orders and finish work.
     */
    void close();
}
