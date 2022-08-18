package com.sap.mjt.lecture9.restaurant;

import com.sap.mjt.lecture9.restaurant.customer.AbstractCustomer;

public record Order(Meal meal, AbstractCustomer customer) {
}
