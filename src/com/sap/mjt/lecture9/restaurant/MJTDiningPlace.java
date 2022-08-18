package com.sap.mjt.lecture9.restaurant;

import com.sap.mjt.lecture9.restaurant.customer.AbstractCustomer;
import com.sap.mjt.lecture9.restaurant.customer.Customer;
import com.sap.mjt.lecture9.restaurant.customer.VipCustomer;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MJTDiningPlace implements Restaurant {
    private static final int CHEFS_NUMBER = 20;
    private static final int CLIENTS_NUMBER = 60;

    private static AtomicInteger ordersCount = new AtomicInteger(0);

    private Random random = new Random();

    private static boolean isClosingTime = false;

    private Chef[] chefs = new Chef[CHEFS_NUMBER];
    private List<AbstractCustomer> customers = new ArrayList<>();
    private PriorityQueue<Order> orders = new PriorityQueue<>();

    public MJTDiningPlace() {
        for (int i = 0; i < CHEFS_NUMBER; i++) {
            Chef chef = new Chef(i, this);
            chef.start();
            chefs[i] = chef;
        }

        initCustomers();
    }

    private void initCustomers() {
        int vipCustomersCounter = 0;

        for (int i = 0; i < CLIENTS_NUMBER; i++) {
            AbstractCustomer customer = new Customer(this);
            int randomNumberOfVipCustomers = random.nextInt(CLIENTS_NUMBER);

            if (vipCustomersCounter < randomNumberOfVipCustomers) {
                customer = new VipCustomer(this);
                vipCustomersCounter++;
            }
            customer.start();

            customers.add(customer);
        }
    }


    @Override
    public void submitOrder(Order order) {
        ordersCount.addAndGet(1);

        synchronized (this) {
            orders.add(order);
        }
    }

    @Override
    public synchronized Order nextOrder() {
        while (orders.isEmpty() && !isClosingTime) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return orders.poll();
    }

    @Override
    public int getOrdersCount() {
        return ordersCount.get();
    }

    @Override
    public Chef[] getChefs() {
        return this.chefs;
    }

    @Override
    public void close() {
        isClosingTime = true;
    }
}
