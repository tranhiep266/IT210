package com.restaurant.bai05;

import com.restaurant.common.Dish;

import com.restaurant.common.Dish;

public class OrderItem {
    private Dish dish;
    private int quantity;
    public OrderItem() {}
    public OrderItem(Dish dish, int quantity) {
        this.dish = dish;
        this.quantity = quantity;
    }
    public Dish getDish() {
        return dish;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setDish(Dish dish) {
        this.dish = dish;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}