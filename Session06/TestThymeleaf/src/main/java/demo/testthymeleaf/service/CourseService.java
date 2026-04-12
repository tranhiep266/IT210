package demo.testthymeleaf.service;

import demo.testthymeleaf.model.Course;
import demo.testthymeleaf.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id);
    }

    public List<Course> getFilteredCourses(String level, Double maxFee) {
        return courseRepository.findAll().stream()
                .filter(course -> {
                    boolean matchLevel = "ALL".equalsIgnoreCase(level)
                            || (course.getInstructor() != null && course.getInstructor().equalsIgnoreCase(level));
                    boolean matchFee = (maxFee == null) || (course.getPrice() <= maxFee);
                    return matchLevel && matchFee;
                })
                .collect(Collectors.toList());
    }

    public boolean deleteCourseById(String id) {
        Course course = courseRepository.findById(id);
        if (course == null) return false;

        // Giữ nguyên logic nghiệp vụ của bạn
        if (course.getMaxStudents() > 50) return false;

        courseRepository.deleteById(id);
        return courseRepository.findById(id) == null;
    }
}