package mk.ukim.finki.wp.lab2.Service.Impl;

import mk.ukim.finki.wp.lab2.Exceptions.CourseNotFoundException;
import mk.ukim.finki.wp.lab2.Exceptions.InvalidArgumentException;
import mk.ukim.finki.wp.lab2.Exceptions.StudentNotFoundException;
import mk.ukim.finki.wp.lab2.Exceptions.TeacherNotFoundException;
import mk.ukim.finki.wp.lab2.Model.Course;
import mk.ukim.finki.wp.lab2.Model.Student;
import mk.ukim.finki.wp.lab2.Model.Teacher;
import mk.ukim.finki.wp.lab2.Repository.CourseRepository;
import mk.ukim.finki.wp.lab2.Repository.StudentRepository;
import mk.ukim.finki.wp.lab2.Service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements mk.ukim.finki.wp.lab2.Service.CourseService {
    public final CourseRepository courseRepository;
    public final StudentRepository studentRepository;
    public final TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, TeacherService teacherService) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherService = teacherService;
    }


    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Course course= courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException(courseId));
        return course.getStudents();
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        //first find the course into you want to add student
        Course course= courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException(courseId));
        //then find the student you want to add in the course
        Student student = studentRepository.findById(username).orElseThrow(()-> new StudentNotFoundException(username));

        //sega na ovoj kurs listata od studentite treba da se dodade nov student
        course.getStudents().add(student);
        return course;
    }

    @Override
    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).orElseThrow(()->new CourseNotFoundException(id));
    }

    @Override
    public Course save(Course course) {

        return courseRepository.save(course);
    }

    @Override
    public void delete(Course course) {
        courseRepository.delete(course);
    }


}
