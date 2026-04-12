package com.restaurant.bai05;

import com.restaurant.common.Dish;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class OrderService {
    private final OrderRepository repo;
    public OrderService(OrderRepository repo) {
        this.repo = repo;
    }
    public List<Dish> getAllDishes() {
        return repo.findAll();
    }
    public Order createOrder(Map<Integer, Integer> quantities) {
        List<OrderItem> items = new ArrayList<>();
        double total = 0;
        for (Map.Entry<Integer, Integer> entry : quantities.entrySet()) {
            int dishId = entry.getKey();
            int qty = entry.getValue();
            if (qty <= 0) continue;
            Dish dish = repo.findById(dishId)
                    .orElseThrow(() -> new RuntimeException("Dish not found"));
            if (!dish.isAvailable()) {
                throw new RuntimeException("Món " + dish.getName() + " đã hết hàng");
            }
            OrderItem item = new OrderItem(dish, qty);
            total += dish.getPrice() * qty;
            items.add(item);
        }
        return new Order(items, total);
    }
}
