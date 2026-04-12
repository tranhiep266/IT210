package com.restaurant.bai05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/bai05")
public class DishDetailController {
    private final OrderService service;
    public DishDetailController(OrderService service) {
        this.service = service;
    }
    @GetMapping("/order")
    public String showOrderPage(Model model) {
        model.addAttribute("dishes", service.getAllDishes());
        return "templates/bai05/order";
    }
    @PostMapping("/order")
    public String placeOrder(@RequestParam Map<String, String> params,
                             Model model) {

        Map<Integer, Integer> quantities = new HashMap<>();

        for (String key : params.keySet()) {
            if (key.startsWith("qty_")) {
                try {
                    int id = Integer.parseInt(key.substring(4));
                    int qty = Integer.parseInt(params.get(key));
                    if (qty < 0) {
                        throw new RuntimeException("Số lượng không hợp lệ");
                    }
                    quantities.put(id, qty);
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Dữ liệu không hợp lệ");
                }
            }
        }
        Order order = service.createOrder(quantities);
        model.addAttribute("order", order);
        return "templates/bai05/result";
    }
    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}