package mk.ukim.finki.wp.lab2.Web.Controllers;

import mk.ukim.finki.wp.lab2.Model.Course;
import mk.ukim.finki.wp.lab2.Model.Student;
import mk.ukim.finki.wp.lab2.Service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/studentEnrollmentSummary")
public class StudentEnrollmentSummary {
    public final CourseService courseService;

    public StudentEnrollmentSummary(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    @RequestMapping("/{courseId}")
    public String getAllStudentsOnCourse(@PathVariable Long courseId, Model model){
        //da go najdeme kursot i da mu gi ispechatime studentite na toj kurs
        Course course = courseService.findById(courseId);
        List<Student> studentsList= course.getStudents();
        model.addAttribute("course",course);
        model.addAttribute("students",studentsList);
        for(Student s: studentsList){
            System.out.println(s.getName());
        }
        return "studentsInCourse";
    }
}
