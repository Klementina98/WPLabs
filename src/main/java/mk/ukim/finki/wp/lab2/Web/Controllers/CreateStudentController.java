package mk.ukim.finki.wp.lab2.Web.Controllers;

import mk.ukim.finki.wp.lab2.Model.Course;
import mk.ukim.finki.wp.lab2.Service.CourseService;
import mk.ukim.finki.wp.lab2.Service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/createStudent")
public class CreateStudentController {

    public final StudentService studentService;
    public final CourseService courseService;

    public CreateStudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }


    @GetMapping
    @RequestMapping("/{id}")
    public String CreateStudent(@PathVariable Long id,Model model){
        Course course= courseService.findById(id);
        model.addAttribute("course",course);
        return "createStudent";
    }
    @PostMapping
    public String createStudent(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam Long courseId){
        studentService.save(username,password,name,surname);
        return "redirect:/addStudent/" + courseId;
    }
}
