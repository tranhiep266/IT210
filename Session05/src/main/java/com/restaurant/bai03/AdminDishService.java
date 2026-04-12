package com.restaurant.bai03;

import com.restaurant.common.Dish;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AdminDishService {

    private List<Dish> dishes = new ArrayList<>();
    public AdminDishService() {
        dishes.add(new Dish(1, "Phở bò", 40000, true));
        dishes.add(new Dish(2, "Bún chả", 35000, false));
        dishes.add(new Dish(3, "Cơm tấm", 30000, true));
    }

    public List<Dish> findAll() {
        return dishes;
    }

    public Dish findById(int id) {
        return dishes.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void update(Dish updatedDish) {
        for (Dish d : dishes) {
            if (d.getId() == updatedDish.getId()) {
                d.setName(updatedDish.getName());
                d.setPrice(updatedDish.getPrice());
            }
        }
    }
}