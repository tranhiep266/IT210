package com.example.session07.bai5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/combo")
public class ComboController {

    private static List<Combo> comboList = new ArrayList<>();

    @GetMapping("/create")
    public String showForm() {
        return "forward:/combo-form.html";
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveCombo(
            @RequestParam String name,
            @RequestParam(required = false) List<String> itemList,
            @RequestParam("banner") MultipartFile file
    ) {
        if (itemList == null || itemList.size() < 2) {
            return "Lỗi: Combo phải có ít nhất 2 món";
        }
        Combo combo = new Combo();
        combo.setName(name);
        combo.setItemList(itemList);

        try {
            if (!file.isEmpty()) {
                String uploadDir = "C:/RikkeiFood_Temp/";
                File dir = new File(uploadDir);
                if (!dir.exists()) dir.mkdirs();
                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                File dest = new File(uploadDir + fileName);
                file.transferTo(dest);
                combo.setBannerPath(dest.getAbsolutePath());
            }
            comboList.add(combo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Tạo combo thành công!";
    }
}
