package com.example.session07.bai4;

import com.example.session07.bai3.Food;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/food4")
public class FoodControllerBai4 {

    private static final List<Food> foodList = new ArrayList<>();
    private static final String UPLOAD_DIR = "C:/RikkeiFood_Temp/";

    @GetMapping("/add4")
    public String showForm() {
        return "bai3";
    }

    @PostMapping("/add4")
    public String addFood(
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam double price,
            @RequestParam("image") MultipartFile file,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        if (file.isEmpty()) {
            return "error";
        }
        if (file.getSize() > 2 * 1024 * 1024) {
            return "error";
        }
        String originalName = file.getOriginalFilename();
        if (originalName == null ||
                !(originalName.endsWith(".jpg") ||
                        originalName.endsWith(".png") ||
                        originalName.endsWith(".jpeg"))) {
            return "error";
        }
        String uniqueName = UUID.randomUUID() + "_" + originalName;
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = UPLOAD_DIR + uniqueName;
        file.transferTo(new File(filePath));
        Food food = new Food(name, category, price, filePath);
        foodList.add(food);
        redirectAttributes.addFlashAttribute("food", food);
        redirectAttributes.addFlashAttribute("message", "Thêm món thành công!");
        return "redirect:/food4/detail";
    }

    @GetMapping("/detail")
    public String showDetail() {
        return "food-detail";
    }
}