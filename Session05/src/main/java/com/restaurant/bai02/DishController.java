package com.restaurant.bai02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping("/bai02/dishes")
    public String getDishes(Model model) {

        model.addAttribute("dishes", dishService.getAllDishes());

        return "dish-list";
    }
}