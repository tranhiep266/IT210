package demo.testthymeleaf.controller;

import demo.testthymeleaf.model.Course;
import demo.testthymeleaf.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    // Sử dụng Constructor Injection theo đúng tiêu chuẩn
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 1. Hiển thị danh sách khóa học
    @GetMapping("/list")
    public String listCourses(
            @RequestParam(name = "level", defaultValue = "ALL") String level,
            @RequestParam(name = "maxFee", required = false) Double maxFee,
            Model model
    ) {
        Double searchFee = (maxFee == null) ? 999999999.0 : maxFee;
        List<Course> courses = courseService.getFilteredCourses(level, searchFee);

        model.addAttribute("courses", courses);
        model.addAttribute("selectedLevel", level);
        model.addAttribute("selectedMaxFee", maxFee); // Để null nếu người dùng chưa nhập

        if (courses.isEmpty()) {
            model.addAttribute("emptyMessage", "Hiện chưa có lớp học phù hợp với yêu cầu này");
        }

        // Trả về "list" -> Spring sẽ tìm tại /WEB-INF/views/list.html
        return "list";
    }

    // 2. Hiển thị chi tiết khóa học
    @GetMapping("/detail/{id}")
    public String detailCourse(@PathVariable(name = "id") String id, Model model) {
        Course course = courseService.getCourseById(id);

        if (course == null) {
            return "redirect:/course/list";
        }

        model.addAttribute("course", course);
        // Trả về "details" -> Spring sẽ tìm tại /WEB-INF/views/details.html
        return "details";
    }

    // 3. Xóa khóa học
    @PostMapping("/delete/{id}")
    public String deleteCourse(
            @PathVariable(name = "id") String id,
            RedirectAttributes redirectAttributes) {

        boolean canDelete = courseService.deleteCourseById(id);

        if (canDelete) {
            redirectAttributes.addFlashAttribute("success", "Xóa khóa học thành công!");
        } else {
            redirectAttributes.addFlashAttribute("error",
                    "Không thể xóa! Khóa học không tồn tại hoặc sĩ số quá lớn (>50).");
        }

        return "redirect:/course/list";
    }
}