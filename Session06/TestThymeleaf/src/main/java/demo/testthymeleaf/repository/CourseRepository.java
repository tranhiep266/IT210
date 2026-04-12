package demo.testthymeleaf.repository;

import demo.testthymeleaf.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public CourseRepository() {
        courses.add(new Course("C01", "Java Basic", "Học Java cơ bản", "Beginner", 5000000, 30, false, 20));
        courses.add(new Course("C02", "Spring Boot", "Xây dựng web với Spring Boot", "Intermediate", 7000000, 45, false, 15));
        courses.add(new Course("C03", "HTML CSS", "Thiết kế giao diện web", "Beginner", 4000000, 25, true, 30));
        courses.add(new Course("C04", "JavaScript", "JavaScript nâng cao", "Advanced", 6000000, 35, false, 10));
        courses.add(new Course("C05", "MySQL", "Quản lý cơ sở dữ liệu", "Intermediate", 4500000, 28, false, 12));
    }

    public Course findById(String id) {
        return courses.stream()
                .filter(c -> c.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteById(String id) {
        courses.removeIf(c -> c.getId().equalsIgnoreCase(id));
    }

    public List<Course> findAll() {
        return new ArrayList<>(courses);
    }
}