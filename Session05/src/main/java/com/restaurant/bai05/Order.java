package com.restaurant.bai05;
import java.util.List;

public class Order {
    private List<OrderItem> items;
    private double total;
    public Order() {}
    public Order(List<OrderItem> items, double total) {
        this.items = items;
        this.total = total;
    }
    public List<OrderItem> getItems() {
        return items;
    }
    public double getTotal() {
        return total;
    }
    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    public void setTotal(double total) {
        this.total = total;
    }
}