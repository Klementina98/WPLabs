package mk.ukim.finki.wp.lab2.Web.Controllers;

import mk.ukim.finki.wp.lab2.Model.Course;
import mk.ukim.finki.wp.lab2.Model.Student;
import mk.ukim.finki.wp.lab2.Service.CourseService;
import mk.ukim.finki.wp.lab2.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/addStudent")
public class ListStudentsController {

    public final StudentService studentService;
    public final CourseService courseService;

    public ListStudentsController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }
    @GetMapping
    @RequestMapping("/{id}")
    public String getStudentsPage(@PathVariable Long id, Model model){
        List<Student> allStudents=studentService.listAll();
        Course course=courseService.findById(id);

        List<Student> studentsOnCourse=course.getStudents();

        allStudents.removeAll(studentsOnCourse);

        model.addAttribute("students",allStudents);

        model.addAttribute("course",course);
        return "listStudents";
    }

    @PostMapping
    public String addStudentToCourse(@RequestParam String username, @RequestParam Long courseId){
        Student studentToAdd = studentService.findById(username);
        Course course= courseService.findById(courseId);
        //da go dodadaveme na listata na studenti na soodvetniot kurs
        course.getStudents().add(studentToAdd);
        courseService.save(course);
        return "redirect:/studentEnrollmentSummary/" + courseId;
    }
}
