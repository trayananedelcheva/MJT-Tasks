package com.sap.mjt.lecture9.restaurant.customer;

import com.sap.mjt.lecture9.restaurant.Meal;
import com.sap.mjt.lecture9.restaurant.Order;
import com.sap.mjt.lecture9.restaurant.Restaurant;

import java.util.Random;

public abstract class AbstractCustomer extends Thread {
    private Restaurant restaurant;

    public AbstractCustomer(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(new Random().nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        restaurant.submitOrder(new Order(Meal.chooseFromMenu(), this));
    }

    public abstract boolean hasVipCard();
}
