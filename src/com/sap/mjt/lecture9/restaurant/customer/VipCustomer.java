package com.sap.mjt.lecture9.restaurant.customer;

import com.sap.mjt.lecture9.restaurant.Restaurant;

public class VipCustomer extends AbstractCustomer{
    public VipCustomer(Restaurant restaurant) {
        super(restaurant);
    }

    @Override
    public boolean hasVipCard() {
        return true;
    }
}
