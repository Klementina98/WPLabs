package mk.ukim.finki.wp.lab2.Service;

import mk.ukim.finki.wp.lab2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    List<Student> listAll();
    List<Student> searchByNameOrSurname(String text);
    Student save(String username, String password, String name, String surname);
    Student findById(String username);
}
