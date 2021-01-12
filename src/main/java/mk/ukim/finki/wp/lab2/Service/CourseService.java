package mk.ukim.finki.wp.lab2.Service;

import mk.ukim.finki.wp.lab2.Model.Course;
import mk.ukim.finki.wp.lab2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAllCourses();
    Course findById(Long id);
    Course save(Course course);
    void delete(Course course);
}
