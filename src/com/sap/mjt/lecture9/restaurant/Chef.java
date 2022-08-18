package com.sap.mjt.lecture9.restaurant;

public class Chef extends Thread {
    private final int id;
    private final Restaurant restaurant;

    private int totalNumberOfMealsCooked = 0;

    public Chef(int id, Restaurant restaurant) {
        this.id = id;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        Order order = null;

        while ((order = restaurant.nextOrder()) != null) {
            try {
                Thread.sleep(order.meal().getCookingTime());
                this.totalNumberOfMealsCooked++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the total number of meals that this chef has cooked.
     */
    public int getTotalCookedMeals() {
        return this.totalNumberOfMealsCooked;
    }
}
