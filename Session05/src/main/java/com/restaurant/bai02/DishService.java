package com.restaurant.bai02;

import com.restaurant.common.Dish;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {
    public List<Dish> getAllDishes() {
        List<Dish> list = new ArrayList<>();
        list.add(new Dish(1, "Phở bò", 40000, true));
        list.add(new Dish(2, "Bún chả", 35000, false));
        list.add(new Dish(3, "Cơm tấm", 30000, true));
        return list;
    }
}