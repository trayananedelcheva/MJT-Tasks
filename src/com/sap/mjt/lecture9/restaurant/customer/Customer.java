package com.sap.mjt.lecture9.restaurant.customer;

import com.sap.mjt.lecture9.restaurant.Restaurant;

public class Customer extends AbstractCustomer {
    public Customer(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public boolean hasVipCard() {
        return false;
    }
}
