package com.restaurant.bai03;

import com.restaurant.common.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bai03")
public class AdminDishController {
    @Autowired
    private AdminDishService service;
    @GetMapping("/dishes")
    public String list(Model model, @RequestParam(value = "error",required = false) String error) {
        model.addAttribute("dishes", service.findAll());
        if ("notfound".equals(error)) {
            model.addAttribute("message", "Không tìm thấy món ăn yêu cầu!");
        }
        return "dish-list";
    }
    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable("id") int id, Model model) {
        Dish dish = service.findById(id);
        if (dish == null) {
            return "redirect:/bai03/dishes?error=notfound";
        }
        model.addAttribute("dish", dish);
        return "edit-dish";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Dish dish) {
        service.update(dish);
        return "redirect:/bai02/dishes";
    }
}