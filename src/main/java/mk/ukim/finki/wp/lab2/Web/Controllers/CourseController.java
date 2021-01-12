package mk.ukim.finki.wp.lab2.Web.Controllers;

import mk.ukim.finki.wp.lab2.Model.Course;
import mk.ukim.finki.wp.lab2.Model.Teacher;
import mk.ukim.finki.wp.lab2.Model.Type;
import mk.ukim.finki.wp.lab2.Service.CourseService;
import mk.ukim.finki.wp.lab2.Service.TeacherService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class CourseController {
    public final CourseService courseService;
    public final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursePage(@RequestParam(required = false) String error, Model model){
        //dali vo error ima neshto
        if (error!=null && !error.isEmpty()){
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        //za da gi prikaze site kursevi vo radiobutton list, treba da se dodadat vo modelot
        List<Course> courses = courseService.listAllCourses();
        model.addAttribute("courses",courses);
        //model.addAttribute("bodyContent",courses); return "master-template";
        return "listCourses";

    }
    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCourse(Model model){
        System.out.println("ide li du tuka");
        // treba da se otvori strana za dodaanje nov kurs
        // ama zatoa treba da se najdat site teachers
        List<Teacher> teachers=teacherService.findAll();
        model.addAttribute("teachers",teachers);
        model.addAttribute("types", Type.values());
        return "add-courseForm";
    }
    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editCourse(@PathVariable Long id, Model model){
        //osven teachers treba da se najde i koj course e bidejki e edit id, mora da mu go prosledime
        System.out.println("ide li du tuka");
        Course course= courseService.findById(id);
        List<Teacher> teachers=teacherService.findAll();
        model.addAttribute("teachers",teachers);
        model.addAttribute("course",course);
        model.addAttribute("types", Type.values());
        return "add-courseForm";
    }
    @PostMapping("/add")
    public String addCourse(@RequestParam Long id,
                            @RequestParam String name,
                            @RequestParam String description,
                            @RequestParam Long teacherId,
                            @RequestParam Type type){
        //bidejki ovoj saveCourse metod go koristi i pri edit i pri add, pri edit treba da proveram dali id-to mi e razlichno od null i da ja iskoristam istot post mapping
        Teacher teacher = teacherService.findById(teacherId);
        if (id==null) {
            //dodavanje
            courseService.save(new Course(name,description,teacher,type));
        }else{
            //editiranje
            courseService.save(new Course(id,name,description,teacher,type));
        }
        return "redirect:/courses";

    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id){
        Course courseToDelete= courseService.findById(id);
        courseService.delete(courseToDelete);
        return "redirect:/courses";
    }

}
