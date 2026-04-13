package com.example.session07.bai3;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/food")
public class FoodController {
    private static final List<Food> foodList = new ArrayList<>();
    private static final String UPLOAD_DIR = "C:/RikkeiFood_Temp/";
    @GetMapping("/add")
    public String showForm() {
        return "bai3";
    }
    @PostMapping("/add")
    public String addFood(
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("price") double price,
            @RequestParam("image") MultipartFile file
    ) throws IOException {
        if (file.isEmpty()) {
            System.out.println(" Chưa chọn file ảnh");
            return "error";
        }
        String fileName = file.getOriginalFilename();
        if (fileName == null ||
                !(fileName.endsWith(".jpg") ||
                        fileName.endsWith(".png") ||
                        fileName.endsWith(".jpeg"))) {

            System.out.println("Sai định dạng file");
            return "error";
        }
        if (price < 0) {
            System.out.println("Giá không hợp lệ");
            return "error";
        }
        File dir = new File(UPLOAD_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String filePath = UPLOAD_DIR + fileName;
        file.transferTo(new File(filePath));
        Food food = new Food(name, category, price, filePath);
        foodList.add(food);
        System.out.println("Đã thêm món: " + name);
        System.out.println("Tổng số món: " + foodList.size());
        return "success";
    }
}
