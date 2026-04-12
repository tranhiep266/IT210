package com.restaurant.bai05;

import com.restaurant.common.Dish;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class OrderRepository {

    private List<Dish> dishes = new ArrayList<>();

    public OrderRepository() {
        dishes.add(new Dish(1, "Phở bò", 40000, true));
        dishes.add(new Dish(2, "Bún chả", 35000, true));
        dishes.add(new Dish(3, "Cơm tấm", 30000, false)); // ví dụ hết hàng
    }

    public List<Dish> findAll() {
        return dishes;
    }

    public Optional<Dish> findById(int id) {
        return dishes.stream()
                .filter(d -> d.getId() == id)
                .findFirst();
    }
}